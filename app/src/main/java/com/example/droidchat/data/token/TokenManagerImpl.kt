package com.example.droidchat.data.token

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.domain.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TokenManagerImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : TokenManager {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val prefs = EncryptedSharedPreferences.create(
        "secure_auth_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveToken(token: String) =
        withContext(ioDispatcher) {
            prefs.edit().putString("jwt_token", token).apply()
        }

    override suspend fun getToken(): String? =
        withContext(ioDispatcher) {
            prefs.getString("jwt_token", null)
        }

    override suspend fun clearToken() =
        withContext(ioDispatcher) {
            prefs.edit().remove("jwt_token").apply()
        }
}