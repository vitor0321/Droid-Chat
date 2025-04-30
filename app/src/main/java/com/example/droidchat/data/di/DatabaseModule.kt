package com.example.droidchat.data.di

import android.content.Context
import androidx.room.Room
import com.example.droidchat.data.database.DroidChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): DroidChatDatabase = Room.databaseBuilder(
        context = context,
        klass = DroidChatDatabase::class.java,
        name = "droidchat_database"
    ).build()
}