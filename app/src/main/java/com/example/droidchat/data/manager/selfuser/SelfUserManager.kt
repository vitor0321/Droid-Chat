package com.example.droidchat.data.manager.selfuser

import com.example.droidchat.SelfUser
import kotlinx.coroutines.flow.Flow

internal interface SelfUserManager {

    val selfUserFlow: Flow<SelfUser>

    suspend fun saveSelfUserData(
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        email: String,
    )

    suspend fun clearSelfUserData()
}