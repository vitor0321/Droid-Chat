package com.example.droidchat.data.manager.selfuser

import android.content.Context
import com.example.droidchat.SelfUser
import com.example.droidchat.data.datastore.selfUserStore
import com.example.droidchat.data.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SelfUserManagerImpl @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SelfUserManager {

    private val selfUserDataStore = context.selfUserStore

    override val selfUserFlow: Flow<SelfUser>
        get() = selfUserDataStore.data

    override suspend fun saveSelfUserData(
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        email: String
    ) {
        withContext(ioDispatcher) {
            selfUserDataStore.updateData { preferences ->
                preferences.toBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setProfilePictureUrl(profilePictureUrl)
                    .setEmail(email)
                    .build()
            }
        }
    }

    override suspend fun clearSelfUserData() {
        withContext(ioDispatcher) {
            selfUserDataStore.updateData { preferences ->
                preferences.toBuilder()
                    .clearFirstName()
                    .clearLastName()
                    .clearProfilePictureUrl()
                    .clearEmail()
                    .build()
            }
        }
    }
}