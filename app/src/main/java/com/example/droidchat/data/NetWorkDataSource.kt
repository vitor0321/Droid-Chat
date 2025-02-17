package com.example.droidchat.data

import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.data.network.model.ImageResponse
import com.example.droidchat.data.network.model.PaginatedChatResponse
import com.example.droidchat.data.network.model.PaginationParams
import com.example.droidchat.data.network.model.TokenResponse
import com.example.droidchat.data.network.model.UserResponse

internal interface NetWorkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(): UserResponse

    suspend fun getChats(paginationParams: PaginationParams): PaginatedChatResponse
}