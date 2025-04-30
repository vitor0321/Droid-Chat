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

internal interface NetWorkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(): UserResponse

    suspend fun getChats(paginationParams: PaginationParams): PaginatedChatResponse

    suspend fun getUser(userId: Int): UserResponse

    suspend fun getUsers(paginationParams: PaginationParams): PaginatedUserResponse

    suspend fun getMessages(receiverId: Int, paginationParams: PaginationParams): PaginatedMessageResponse
}