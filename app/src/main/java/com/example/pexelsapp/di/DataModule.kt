package com.example.pexelsapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.BookMarkDao
import com.example.data.local.BookMarkDatabase
import com.example.data.local.BookMarkRepositoryImpl
import com.example.data.remote.collection.CollectionApi
import com.example.data.remote.collection.repository.CollectionRepositoryImpl
import com.example.data.remote.photos.PhotoApi
import com.example.data.remote.photos.repository.PhotoRepositoryImpl
import com.example.domain.bookmark.repository.BookMarkRepository
import com.example.domain.categories.repository.CollectionRepository
import com.example.domain.photos.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): PhotoApi = provideApi(retrofit)

    @Provides
    @Singleton
    fun provideImageRepository(photoApi: PhotoApi): PhotoRepository = PhotoRepositoryImpl(photoApi)

    @Provides
    @Singleton
    fun provideCollectionApi(retrofit: Retrofit): CollectionApi = provideApi(retrofit)

    @Provides
    @Singleton
    fun provideCollectionRepository(collectionApi: CollectionApi): CollectionRepository =
        CollectionRepositoryImpl(collectionApi)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BookMarkDatabase {
        return Room.databaseBuilder(
            appContext,
            BookMarkDatabase::class.java,
            "book_mark_database"
        ).build()
    }

    @Provides
    fun provideBookMarkDao(database: BookMarkDatabase): BookMarkDao =
        database.bookMarkDao()

    @Provides
    fun provideBookMarkRepository(dao: BookMarkDao): BookMarkRepository =
        BookMarkRepositoryImpl(dao)
}
