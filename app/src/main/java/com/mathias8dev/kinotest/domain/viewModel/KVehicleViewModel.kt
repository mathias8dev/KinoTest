package com.mathias8dev.kinotest.domain.viewModel

import androidx.compose.runtime.*
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathias8dev.kinotest.domain.data.KVehicle
import com.mathias8dev.kinotest.domain.service.KVehicleApiService
import kotlinx.coroutines.launch
import java.util.*

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
                val response = apiService.getVehicleList().vehicleList
                if (response.status.lowercase() != "ok") errorMessage = "Api is not able to return successful response"
                else _vehiclesList.addAll(response.vehicles)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}