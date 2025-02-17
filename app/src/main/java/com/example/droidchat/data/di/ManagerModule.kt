package com.example.droidchat.data.di

import com.example.droidchat.data.SelfUserManager
import com.example.droidchat.data.TokenManager
import com.example.droidchat.data.manager.selfuser.SelfUserManagerImpl
import com.example.droidchat.data.manager.token.SecureTokenMangerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ManagerModule {

    @Binds
    @Singleton
    fun bindTokenManager(tokenManager: SecureTokenMangerImpl): TokenManager

    @Binds
    @Singleton
    fun bindSelfUserManager(selfUserManager: SelfUserManagerImpl): SelfUserManager
}