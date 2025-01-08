package com.example.droidchat.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

internal val Context.tokenDataStore by preferencesDataStore(name = "token_store")

internal object TokensKeys {
    val ACCESS_TOKEN = stringPreferencesKey("accessToken")
}