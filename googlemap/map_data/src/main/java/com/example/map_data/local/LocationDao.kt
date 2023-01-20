package com.example.map_data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.map_domain.model.LocationsItem

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locationsItem: LocationsItem)

    @Query("Select * from location_item")
    fun getAllLocations():LiveData<List<LocationsItem>>


}