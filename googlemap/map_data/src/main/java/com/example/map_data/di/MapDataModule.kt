package com.example.map_data.di

import com.example.map_data.local.LocationDao
import com.example.map_data.repository.MapRepoImpl
import com.example.map_domain.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MapDataModule {

    @Provides
    @Singleton
    fun provideMapRepository(dao: LocationDao):MapRepository{
        return MapRepoImpl(dao)
    }

}