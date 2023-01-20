package com.example.tummoc.di

import android.content.Context
import androidx.room.Room
import com.example.common_utils.Constants.DATABASE_NAME
import com.example.common_utils.Navigator
import com.example.map_data.local.LocationDao
import com.example.map_data.local.LocationCallback
import com.example.map_data.local.LocationDatabase
import com.example.tummoc.navigation.DefaultNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun providesArticleDatabase(
        @ApplicationContext context: Context,
        provider: Provider<LocationDao>,
    ): LocationDatabase {
        return Room.databaseBuilder(context, LocationDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(LocationCallback(context, provider))
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: LocationDatabase): LocationDao {
        return database.getLocationDao()
    }

    @Provides
    @Singleton
    fun provideProvider(): Navigator.Provider{
        return DefaultNavigator()
    }

}