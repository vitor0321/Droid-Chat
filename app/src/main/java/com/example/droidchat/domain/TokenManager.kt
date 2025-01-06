package com.example.droidchat.domain

internal interface TokenManager {

    suspend fun saveToken(token: String)

    suspend fun getToken(): String?

    suspend fun clearToken()
}