package com.example.pexelsapp.di

import retrofit2.Retrofit

internal inline fun <reified Api> provideApi(retrofit: Retrofit): Api =
    retrofit.create(Api::class.java)
