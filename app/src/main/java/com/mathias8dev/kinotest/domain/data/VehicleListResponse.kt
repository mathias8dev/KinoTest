package com.mathias8dev.kinotest.domain.data

import com.google.gson.annotations.SerializedName



data class VehicleListResponse(
    val status: String,
    @SerializedName("response")
    val vehicles: List<KVehicle>
)

