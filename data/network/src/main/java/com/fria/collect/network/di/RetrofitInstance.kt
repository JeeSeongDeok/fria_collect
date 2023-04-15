package com.fria.collect.network.di

import com.fria.collect.network.remote.YoutubeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {
    private val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    @Provides
    fun provideRetrofitApi(): YoutubeApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(YoutubeApiService::class.java)
}