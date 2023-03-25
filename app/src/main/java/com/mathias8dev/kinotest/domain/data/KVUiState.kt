package com.mathias8dev.kinotest.domain.data

sealed class KVUiState {
    object Start : KVUiState()
    object Loading : KVUiState()
    class Error(val message: String): KVUiState()
    class Loaded(val data: List<KVehicle>): KVUiState()

}