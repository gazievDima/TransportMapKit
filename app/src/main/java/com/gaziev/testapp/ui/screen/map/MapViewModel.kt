package com.gaziev.testapp.ui.screen.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaziev.testapp.ui.model.Coordinates
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {

    private var _coordinates: MutableLiveData<Coordinates> = MutableLiveData()
    val coordinates: LiveData<Coordinates> = _coordinates

    fun saveCoordinates(name: String, lat: Double, lon: Double) {
        _coordinates.value = Coordinates(name, lat, lon)
    }
}