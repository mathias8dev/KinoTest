package com.mathias8dev.kinotest.domain.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathias8dev.kinotest.domain.data.KVUiState
import com.mathias8dev.kinotest.domain.data.KVehicle
import com.mathias8dev.kinotest.domain.data.Strings
import com.mathias8dev.kinotest.domain.data.VehicleListResponse
import com.mathias8dev.kinotest.domain.service.KVehicleApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class KVehicleViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<KVUiState>(KVUiState.Empty)
    val uiState: StateFlow<KVUiState> = _uiState

    private val _vehiclesList = mutableStateListOf<KVehicle>()
    val vehiclesList: List<KVehicle>
        get() = _vehiclesList


    fun getVehicleList() {
        viewModelScope.launch {
            onLoadingData()
            val apiService = KVehicleApiService.getInstance()
            try {
                _vehiclesList.clear()
                val response = apiService.getVehicleList().vehicleList
                onDataFetchedSuccessfully(response)

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        if (e.code() == 503) onErrorOccurred(Strings.serviceUnavailable)
                        else Log.e(TAG, "Une erreur de code ${e.code()} et dont le message est ${e.message()} est survenue")
                    }

                    else -> {
                        if (e.message!!.lowercase().contains("host")) onErrorOccurred(Strings.serviceUnavailable)
                        else onErrorOccurred(e.message!!)
                    }
                }
            }
        }
    }

    private fun onLoadingData() {
        _uiState.value = KVUiState.Loading
    }
    private fun onErrorOccurred(message: String) {
        _uiState.value = KVUiState.Error(message)
    }

    private fun onDataFetchedSuccessfully(data: VehicleListResponse) {
        if(data.status.lowercase() != "ok") onErrorOccurred(Strings.dataNotSuccessfulFetchedError)
        else _uiState.value = KVUiState.Loaded(data.vehicles)
    }

    companion object {
        const val TAG = "KVEHICLE_VIEW_MODEL"
    }
}