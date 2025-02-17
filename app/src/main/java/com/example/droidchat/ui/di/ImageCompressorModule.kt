package com.example.droidchat.ui.di

import com.example.droidchat.util.image.ImageCompressor
import com.example.droidchat.util.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface ImageCompressorModule {

    @Binds
    fun bindImageCompressor(imageCompressor: ImageCompressorImpl): ImageCompressor
}