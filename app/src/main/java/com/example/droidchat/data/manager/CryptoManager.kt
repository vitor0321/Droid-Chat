package com.example.droidchat.data.manager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.io.IOException
import java.security.GeneralSecurityException

internal object CryptoManager {

    private const val PREFS_NAME = "encrypted_prefs"
    private const val TAG = "CryptoManager"

    /**
     * Encrypts sensitive data using EncryptedSharedPreferences.
     *
     * @param context The application context.
     * @param key The key under which the data will be stored.
     * @param data The data to be encrypted.
     * @return The encrypted data, or null if an error occurred.
     */
    fun encryptData(context: Context, key: String, data: String): String? {
        return try {
            val sharedPreferences = getEncryptedSharedPreferences(context)
            sharedPreferences.edit { putString(key, data) }
            data
        } catch (e: GeneralSecurityException) {
            Log.e(TAG, "Error GeneralSecurityException encrypting data: ${e.message}", e)
            handleKeyInvalidation(context)
            null
        } catch (e: IOException) {
            Log.e(TAG, "Error IOException encrypting data: ${e.message}", e)
            null
        }
    }

    /**
     * Decrypts stored data.
     *
     * @param context The application context.
     * @param key The key under which the data is stored.
     * @return The decrypted data, or an empty string if the key is not found or an error occurred.
     */
    fun decryptData(context: Context, key: String): String {
        return try {
            val sharedPreferences = getEncryptedSharedPreferences(context)
            sharedPreferences.getString(key, "") ?: ""
        } catch (e: GeneralSecurityException) {
            Log.e(TAG, "Error GeneralSecurityException decrypting data: ${e.message}", e)
            handleKeyInvalidation(context)
            ""
        } catch (e: IOException) {
            Log.e(TAG, "Error IOException decrypting data: ${e.message}", e)
            ""
        }
    }

    /**
     * Gets an instance of EncryptedSharedPreferences.
     *
     * @param context The application context.
     * @return An instance of EncryptedSharedPreferences.
     * @throws GeneralSecurityException if there is a problem with the security of the encryption.
     * @throws IOException if there is a problem with the file system.
     */
    @Throws(GeneralSecurityException::class, IOException::class)
    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    /**
     * Creates a master key in the Keystore.
     *
     * @param context The application context.
     * @return The master key.
     * @throws GeneralSecurityException if there is a problem with the security of the key.
     * @throws IOException if there is a problem with the file system.
     */
    @Throws(GeneralSecurityException::class, IOException::class)
    private fun getMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    /**
     * Handles key invalidation by clearing the existing EncryptedSharedPreferences and creating a new master key.
     *
     * @param context The application context.
     */
    private fun handleKeyInvalidation(context: Context) {
        Log.w(TAG, "Handling key invalidation. Recreating EncryptedSharedPreferences.")
        try {
            // Delete the existing shared preferences file.
            context.deleteSharedPreferences(PREFS_NAME)

            // Attempt to create a new master key.
            getMasterKey(context)

            Log.i(TAG, "Successfully recreated EncryptedSharedPreferences.")
        } catch (e: Exception) {
            Log.e(TAG, "Error handling key invalidation: ${e.message}", e)
        }
    }
}