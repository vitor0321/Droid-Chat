package com.example.droidchat.domain

import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.data.network.model.TokenResponse

internal interface NetWorkDataSource {

    suspend fun signUp(response: CreateAccountRequest)

    suspend fun signIn(response: AuthRequest): TokenResponse
}