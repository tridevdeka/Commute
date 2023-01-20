package com.example.map_data.repository

import androidx.lifecycle.LiveData
import com.example.map_data.local.LocationDao
import com.example.map_domain.model.LocationsItem
import com.example.map_domain.repository.MapRepository

class MapRepoImpl(private val dao: LocationDao):MapRepository {
    override fun getLocations(): LiveData<List<LocationsItem>> {
        return try {
            dao.getAllLocations()
        } catch (e: Exception) {
            dao.getAllLocations()
        }
    }

}