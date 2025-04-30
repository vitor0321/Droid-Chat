package com.example.droidchat.data.di


import com.example.droidchat.data.manager.TokenManager
import com.example.droidchat.data.model.exception.NetworkException
import com.example.droidchat.data.network.HttpConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenManager: TokenManager,
    ): HttpClient {
        return HttpClient(CIO) {
            // retorna um erro se a requisição não for bem sucedida, direto da API
            expectSuccess = true

            install(Logging) {
                logger = Logger.ANDROID  // Usa o Logcat para exibir logs
                level = LogLevel.ALL     // Captura TODOS os logs (headers, corpo, status, etc.)
                filter { request -> true } // Aplica o log para todas as requisições
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        // formata o JSON para ser mais legível
                        prettyPrint = true
                        // capaz de lidar com campos desconhecidos. Se voltar um "1" ele é capaz de entender que é um Int
                        isLenient = true
                        // ignora campos desconhecidos. Só mapea o necessário
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 15000
            }

            defaultRequest {
                // deixar a URL base para ser usada em todas as requisições
                url(HttpConfig.API.value)
                contentType(ContentType.Application.Json)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->
                    handleClientRequestException(cause = cause, request = request)
                }

                handleResponseException { cause, request ->
                    handleClientRequestException(cause = cause, request = request)
                }
            }
        }.apply {
            plugin(HttpSend).intercept { request ->
                val accessToken = tokenManager.accessToken.firstOrNull()
                accessToken?.let {
                    request.headers.append(HttpConfig.AUTHORIZATION.value, "${HttpConfig.BEARER.value} $accessToken")
                }
                execute(request)
            }
        }
    }
}

private suspend fun handleClientRequestException(cause: Throwable, request: HttpRequest) {
    val exception = cause as? ClientRequestException ?: return

    val response = exception.response
    val errorBody = response.bodyAsText()

    Logger.ANDROID.log(
        """
            ❌ HTTP ERROR:
            ├── Response URL: ${response.request.url}
            ├── Request Method: ${request.method.value} URL: ${request.url}
            ├── Response Method: ${response.request.method.value}
            ├── Response Status: ${response.status}
            ├── Response Headers: ${response.headers.entries().joinToString("\n│    ") { "${it.key}: ${it.value}" }}
            ├── Response Response Body: $errorBody
        """.trimIndent()
    )

    throw when (response.status) {
        HttpStatusCode.NotFound -> NetworkException.NotFoundException(cause)
        HttpStatusCode.Conflict -> NetworkException.ConflictException(cause)
        HttpStatusCode.BadRequest -> NetworkException.BadRequestException(cause)
        HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(cause)
        HttpStatusCode.InternalServerError -> NetworkException.ServerErrorException(cause)
        HttpStatusCode.UnprocessableEntity -> NetworkException.UnprocessableEntityException(cause)
        else -> NetworkException.UnknownException(cause)
    }
}