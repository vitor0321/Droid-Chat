package com.example.droidchat.data.di

import com.example.droidchat.data.service.AuthServiceImpl
import com.example.droidchat.data.service.ChatServiceImpl
import com.example.droidchat.domain.AuthService
import com.example.droidchat.domain.ChatService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindAuthRepository(repository: AuthServiceImpl): AuthService

    @Binds
    fun bindChatRepository(repository: ChatServiceImpl): ChatService
}