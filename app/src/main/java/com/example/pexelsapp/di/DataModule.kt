package com.example.pexelsapp.di

import com.example.data.remote.image.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi {
        return provideApi(retrofit)
    }
}
