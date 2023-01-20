package com.example.tummoc.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.map_data.local.Converters
import com.example.map_data.local.LocationDao
import com.example.map_domain.model.LocationsItem

@Database(entities = [LocationsItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao
}