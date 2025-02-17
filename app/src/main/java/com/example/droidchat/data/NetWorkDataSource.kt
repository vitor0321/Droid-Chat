package com.example.droidchat.data

import com.example.droidchat.data.service.model.request.AuthRequest
import com.example.droidchat.data.service.model.request.CreateAccountRequest
import com.example.droidchat.data.service.model.request.PaginationParams
import com.example.droidchat.data.service.model.response.ImageResponse
import com.example.droidchat.data.service.model.response.PaginatedChatResponse
import com.example.droidchat.data.service.model.response.PaginatedUserResponse
import com.example.droidchat.data.service.model.response.TokenResponse
import com.example.droidchat.data.service.model.response.UserResponse

internal interface NetWorkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(): UserResponse

    suspend fun getChats(paginationParams: PaginationParams): PaginatedChatResponse

    suspend fun getUsers(paginationParams: PaginationParams): PaginatedUserResponse
}