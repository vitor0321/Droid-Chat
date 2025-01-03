package com.example.droidchat.domain

import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.data.network.model.ImageResponse
import com.example.droidchat.data.network.model.TokenResponse

internal interface NetWorkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse
}