package com.example.pexelsapp.di

import retrofit2.Retrofit

inline fun <reified Api> provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
