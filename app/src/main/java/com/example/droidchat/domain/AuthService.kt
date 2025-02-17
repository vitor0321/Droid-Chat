package com.example.droidchat.domain

import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image

internal interface AuthService {

    suspend fun getAccessToken(): String?

    suspend fun clearAccessToken()

    suspend fun signIn(email: String, password: String): Result<Unit>

    suspend fun signUp(createAccount: CreateAccount): Result<Unit>

    suspend fun uploadProfilePicture(filePath: String): Result<Image>

    suspend fun authenticate(): Result<Unit>
}