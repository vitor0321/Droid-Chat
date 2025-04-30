package com.example.droidchat.data.network

import com.example.droidchat.data.model.request.AuthRequest
import com.example.droidchat.data.model.request.CreateAccountRequest
import com.example.droidchat.data.model.request.PaginationParams
import com.example.droidchat.data.model.response.ImageResponse
import com.example.droidchat.data.model.response.PaginatedChatResponse
import com.example.droidchat.data.model.response.PaginatedMessageResponse
import com.example.droidchat.data.model.response.PaginatedUserResponse
import com.example.droidchat.data.model.response.TokenResponse
import com.example.droidchat.data.model.response.UserResponse
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
        httpClient.post(HttpConfig.SIGN_UP.value) { setBody(request) }.body<Unit>()

    override suspend fun signIn(request: AuthRequest): TokenResponse =
        httpClient.post(HttpConfig.SIGN_IN.value) { setBody(request) }.body()

    override suspend fun uploadProfilePicture(filePath: String): ImageResponse {
        val file = File(filePath)
        return httpClient.submitFormWithBinaryData(
            url = HttpConfig.UPLOADING.value,
            formData = formData {
                append("image", file.readBytes(), Headers.build {
                    append(HttpHeaders.ContentType, "image/${file.extension}")
                    append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                })
            }
        ).body()
    }

    override suspend fun authenticate(): UserResponse =
        httpClient.get(HttpConfig.AUTHENTICATE.value).body()

    override suspend fun getChats(
        paginationParams: PaginationParams
    ): PaginatedChatResponse =
        httpClient.get(HttpConfig.CONVERSATIONS.value) {
            url { appendPaginationParams(paginationParams) }
        }.body()

    override suspend fun getUser(userId: Int): UserResponse =
        httpClient.get(HttpConfig.USERS.value + "/$userId").body()

    override suspend fun getUsers(
        paginationParams: PaginationParams
    ): PaginatedUserResponse =
        httpClient.get(HttpConfig.USERS.value) {
            url { appendPaginationParams(paginationParams) }
        }.body()

    override suspend fun getMessages(receiverId: Int, paginationParams: PaginationParams): PaginatedMessageResponse =
        httpClient.get(HttpConfig.MESSAGES.value + "/$receiverId") {
            url { appendPaginationParams(paginationParams) }
        }.body()

    private fun URLBuilder.appendPaginationParams(paginationParams: PaginationParams) {
        parameters.append("offset", paginationParams.offset)
        parameters.append("limit", paginationParams.limit)
    }
}