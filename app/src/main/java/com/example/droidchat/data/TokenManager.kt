package com.example.droidchat.data

import kotlinx.coroutines.flow.Flow

internal interface TokenManager {

    val accessToken: Flow<String>

    suspend fun saveAccessToken(token: String)

    suspend fun clearAccessToken()
}