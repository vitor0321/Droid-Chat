package com.example.droidchat.data.manager.token

import kotlinx.coroutines.flow.Flow

internal interface TokenManager {

    val accessToken: Flow<String>

    suspend fun saveAccessToken(token: String)

    suspend fun clearAccessToken()
}