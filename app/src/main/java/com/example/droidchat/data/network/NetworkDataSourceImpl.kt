package com.example.droidchat.data.network

import com.example.droidchat.data.NetWorkDataSource
import com.example.droidchat.data.service.model.request.AuthRequest
import com.example.droidchat.data.service.model.request.CreateAccountRequest
import com.example.droidchat.data.service.model.request.PaginationParams
import com.example.droidchat.data.service.model.response.ImageResponse
import com.example.droidchat.data.service.model.response.PaginatedChatResponse
import com.example.droidchat.data.service.model.response.PaginatedUserResponse
import com.example.droidchat.data.service.model.response.TokenResponse
import com.example.droidchat.data.service.model.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import java.io.File
import javax.inject.Inject

internal class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : NetWorkDataSource {

    override suspend fun signUp(request: CreateAccountRequest) =
        httpClient.post(HttpUrl.SIGN_UP.value) { setBody(request) }.body<Unit>()

    override suspend fun signIn(request: AuthRequest): TokenResponse =
        httpClient.post(HttpUrl.SIGN_IN.value) { setBody(request) }.body()

    override suspend fun uploadProfilePicture(filePath: String): ImageResponse {
        val file = File(filePath)
        return httpClient.submitFormWithBinaryData(
            url = HttpUrl.UPLOADING.value,
            formData = formData {
                append("image", file.readBytes(), Headers.build {
                    append(HttpHeaders.ContentType, "image/${file.extension}")
                    append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                })
            }
        ).body()
    }

    override suspend fun authenticate(): UserResponse =
        httpClient.get(HttpUrl.AUTHENTICATE.value).body()

    override suspend fun getChats(
        paginationParams: PaginationParams
    ): PaginatedChatResponse =
        httpClient.get(HttpUrl.CONVERSATIONS.value) {
            url { appendPaginationParams(paginationParams) }
        }.body()

    override suspend fun getUsers(
        paginationParams: PaginationParams
    ): PaginatedUserResponse =
        httpClient.get(HttpUrl.CONVERSATIONS.value) {
            url { appendPaginationParams(paginationParams) }
        }.body()

    private fun URLBuilder.appendPaginationParams(paginationParams: PaginationParams) {
        parameters.append("offset", paginationParams.offset)
        parameters.append("limit", paginationParams.limit)
    }
}