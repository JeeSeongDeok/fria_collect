package com.fria.collect.di

import com.fria.collect.domain.data.repository.YoutubeRepository
import com.fria.collect.network.remote.YoutubeApiService
import com.fria.collect.repository.YoutubeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideYoutubeRepository(api:YoutubeApiService) : YoutubeRepository {
        return YoutubeRepositoryImpl(api)
    }
}