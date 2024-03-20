package com.example.pexelsapp.di

import com.example.data.remote.image.ImageApi
import com.example.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideImageRepository(api: ImageApi): ImageRepository {
        return ImageRepository(api)
    }
}
