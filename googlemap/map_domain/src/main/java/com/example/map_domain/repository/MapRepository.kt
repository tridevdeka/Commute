package com.example.map_domain.repository

import androidx.lifecycle.LiveData
import com.example.map_domain.model.LocationsItem


interface MapRepository {
    fun getLocations(): LiveData<List<LocationsItem>>
}