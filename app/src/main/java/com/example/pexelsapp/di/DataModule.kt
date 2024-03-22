package com.example.pexelsapp.di

import com.example.data.remote.photos.PhotoApi
import com.example.data.remote.photos.repository.PhotoRepositoryImpl
import com.example.domain.photos.repository.PhotoRepository
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
    fun provideImageApi(retrofit: Retrofit): PhotoApi {
        return provideApi(retrofit)
    }

    @Provides
    @Singleton
    fun provideImageRepository(photoApi: PhotoApi): PhotoRepository {
        return PhotoRepositoryImpl(photoApi)
    }
}
