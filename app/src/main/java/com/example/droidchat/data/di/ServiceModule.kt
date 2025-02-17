package com.example.droidchat.data.di

import com.example.droidchat.data.service.AuthServiceImpl
import com.example.droidchat.data.service.ChatServiceImpl
import com.example.droidchat.data.service.UserServiceImpl
import com.example.droidchat.domain.AuthService
import com.example.droidchat.domain.ChatService
import com.example.droidchat.domain.UserService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface ServiceModule {

    @Binds
    fun bindAuthService(service: AuthServiceImpl): AuthService

    @Binds
    fun bindChatService(service: ChatServiceImpl): ChatService

    @Binds
    fun bindUserService(service: UserServiceImpl): UserService
}