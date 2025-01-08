package com.example.droidchat.data.manager

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

internal object CryptoManager {

    private const val PREFS_NAME = "encrypted_prefs"

    // Criptografa dados sensíveis usando o EncryptedSharedPreferences
    fun encryptData(context: Context, key: String, data: String): String {
        val sharedPreferences = getEncryptedSharedPreferences(context)
        sharedPreferences.edit().putString(key, data).apply()
        return sharedPreferences.getString(key, "").orEmpty()
    }

    // Descriptografa os dados armazenados
    fun decryptData(context: Context, key: String): String {
        val sharedPreferences = getEncryptedSharedPreferences(context)
        return sharedPreferences.getString(key, "").orEmpty()
    }

    // Obtém uma instância de EncryptedSharedPreferences
    private fun getEncryptedSharedPreferences(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    // Cria uma chave mestra no Keystore
    private fun getMasterKey(context: Context): MasterKey =
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
}