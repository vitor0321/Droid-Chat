package com.example.droidchat.data.network

import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.data.network.model.TokenResponse
import com.example.droidchat.domain.NetWorkDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

internal class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : NetWorkDataSource {

    override suspend fun signUp(response: CreateAccountRequest) {
        httpClient.post("signup") {
            setBody(response)
        }.body<Unit>()
    }

    override suspend fun signIn(response: AuthRequest): TokenResponse {
        return httpClient.post("signin") {
            setBody(response)
        }.body()
    }
}