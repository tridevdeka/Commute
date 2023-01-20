package com.example.map_domain.use_case

import androidx.lifecycle.LiveData
import com.example.map_domain.model.LocationsItem
import com.example.map_domain.repository.MapRepository


class MapUseCase(private val mapRepository: MapRepository) {

    operator fun invoke(): LiveData<List<LocationsItem>> {
        return mapRepository.getLocations()
    }
}
