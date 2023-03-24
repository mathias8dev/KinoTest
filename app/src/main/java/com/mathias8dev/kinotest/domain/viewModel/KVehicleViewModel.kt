package com.mathias8dev.kinotest.domain.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathias8dev.kinotest.domain.data.KVehicle
import com.mathias8dev.kinotest.domain.service.KVehicleApiService
import kotlinx.coroutines.launch

class KVehicleViewModel: ViewModel() {

    private val _vehiclesList = mutableStateListOf<KVehicle>()
    var errorMessage: String by mutableStateOf("")
    val vehiclesList: List<KVehicle>
        get() = _vehiclesList

    fun getVehicleList() {
        viewModelScope.launch {
            val apiService = KVehicleApiService.getInstance()
            try {
                _vehiclesList.clear()
                _vehiclesList.addAll(apiService.getVehicleList())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}