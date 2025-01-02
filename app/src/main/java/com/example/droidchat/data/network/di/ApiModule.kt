package com.example.droidchat.data.network.di

import com.example.droidchat.data.network.model.NetworkException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            // retorna um erro se a requisição não for bem sucedida, direto da API
            expectSuccess = true

            install(Logging) {
                // Configuração do log que vai aparecer no console Logcat
                logger = Logger.SIMPLE
                level = LogLevel.ALL
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

            defaultRequest {
                // deixar a URL base para ser usada em todas as requisições
                url("https://chat-api.androidmoderno.com.br/")
                contentType(ContentType.Application.Json)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->
                    // se a causa do erro for uma exceção de requisição do cliente, isso de forma global para todas as requisições
                    throw if (cause is ClientRequestException) {
                        val errorMessage = cause.response.bodyAsText()
                        NetworkException.ApiException(errorMessage, cause.response.status.value)
                    } else {
                        NetworkException.UnknownNetworkException(cause)
                    }
                }
            }
        }
    }
}