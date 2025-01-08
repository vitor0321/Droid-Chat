package com.example.droidchat.data.network

import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.data.network.model.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

internal class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : NetWorkDataSource {

    override suspend fun signUp(request: CreateAccountRequest) =
        httpClient.post("signup") { setBody(request) }.body<Unit>()

    override suspend fun signIn(request: AuthRequest): TokenResponse =
        httpClient.post("signin") { setBody(request) }.body()
}