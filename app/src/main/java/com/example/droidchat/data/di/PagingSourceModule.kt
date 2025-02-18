package com.example.droidchat.data.di

import androidx.paging.PagingSource
import com.example.droidchat.data.NetWorkDataSource
import com.example.droidchat.data.service.pagingSource.UserPagingSource
import com.example.droidchat.domain.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PagingSourceModule {

    @Provides
    @Singleton
    fun provideUserPagingSource(
        netWorkDataSource: NetWorkDataSource
    ): PagingSource<Int, User> = UserPagingSource(netWorkDataSource = netWorkDataSource)
}