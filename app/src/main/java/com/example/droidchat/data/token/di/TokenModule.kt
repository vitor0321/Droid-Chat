package com.example.droidchat.data.token.di

import com.example.droidchat.data.token.TokenManagerImpl
import com.example.droidchat.domain.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface TokenModule {

    @Binds
    fun bindTokenManager(tokenManager: TokenManagerImpl): TokenManager
}