package com.example.tummoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.map_domain.model.LocationsItem
import com.example.map_domain.use_case.MapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val useCase: MapUseCase) : ViewModel() {

    fun getAllLocations(): LiveData<List<LocationsItem>> {
        return useCase()
    }


}