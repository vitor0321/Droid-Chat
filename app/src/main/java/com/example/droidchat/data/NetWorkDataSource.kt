package com.example.droidchat.data

import com.example.droidchat.data.network.model.PaginationParams
import com.example.droidchat.data.network.model.request.AuthRequest
import com.example.droidchat.data.network.model.request.CreateAccountRequest
import com.example.droidchat.data.network.model.response.ImageResponse
import com.example.droidchat.data.network.model.response.PaginatedChatResponse
import com.example.droidchat.data.network.model.response.TokenResponse
import com.example.droidchat.data.network.model.response.UserResponse

internal interface NetWorkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(): UserResponse

    suspend fun getChats(paginationParams: PaginationParams): PaginatedChatResponse
}