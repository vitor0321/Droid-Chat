package com.example.droidchat.data.manager.token

import android.content.Context
import com.example.droidchat.data.TokenManager
import com.example.droidchat.data.datastore.TokensKeys
import com.example.droidchat.data.datastore.tokenDataStore
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.manager.CryptoManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SecureTokenMangerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : TokenManager {

    private val tokenDataStore = context.tokenDataStore

    override val accessToken: Flow<String>
        get() = flowOf(CryptoManager.decryptData(context = context, TokensKeys.ACCESS_TOKEN.name))

    override suspend fun saveAccessToken(token: String) {
        withContext(ioDispatcher) {
            CryptoManager.encryptData(context = context, key = TokensKeys.ACCESS_TOKEN.name, data = token)
        }
    }

    override suspend fun clearAccessToken() {
        withContext(ioDispatcher) {
            CryptoManager.encryptData(context = context, key = TokensKeys.ACCESS_TOKEN.name, data = "")
        }
    }
}