package com.example.map_domain.di

import com.example.map_domain.repository.MapRepository
import com.example.map_domain.use_case.MapUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MapDomainModule {

    @Provides
    fun provideMapUseCase(repository: MapRepository): MapUseCase {
        return MapUseCase(repository)
    }
}