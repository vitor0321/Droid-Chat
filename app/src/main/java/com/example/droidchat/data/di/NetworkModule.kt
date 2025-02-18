package com.example.droidchat.data.di


import com.example.droidchat.data.TokenManager
import com.example.droidchat.data.network.HttpUrl
import com.example.droidchat.data.service.model.exception.NetworkException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
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
                url(HttpUrl.API.value)
                contentType(ContentType.Application.Json)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->

                    // se a causa do erro for uma exceção de requisição do cliente, isso de forma global para todas as requisições
                    handleResponseExceptionWithRequest { cause, _ ->
                        val exception = cause as? ClientRequestException ?: return@handleResponseExceptionWithRequest

                        // Captura informações da resposta HTTP
                        val response = exception.response
                        val errorBody = response.bodyAsText()

                        // Log completo da resposta com erro
                        Logger.ANDROID.log(
                            """
                                ❌ HTTP ERROR:
                                ├── URL: ${response.request.url}
                                ├── Method: ${response.request.method.value}
                                ├── Status: ${response.status}
                                ├── Headers: ${response.headers.entries().joinToString("\n│    ") { "${it.key}: ${it.value}" }}
                                ├── Response Body: $errorBody
                            """.trimIndent()
                        )

                        throw when (exception.response.status) {
                            HttpStatusCode.NotFound -> NetworkException.NotFoundException(cause)
                            HttpStatusCode.Conflict -> NetworkException.ConflictException(cause)
                            HttpStatusCode.BadRequest -> NetworkException.BadRequestException(cause)
                            HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(cause)
                            HttpStatusCode.InternalServerError -> NetworkException.ServerErrorException(cause)
                            HttpStatusCode.UnprocessableEntity -> NetworkException.UnprocessableEntityException(cause)
                            else -> NetworkException.UnknownException(cause)
                        }
                    }
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = tokenManager.accessToken.firstOrNull()
                        accessToken?.let {
                            BearerTokens(it, "")
                        }
                    }
                }
            }
        }
    }
}