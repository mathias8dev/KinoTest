package com.mathias8dev.kinotest.domain.service


import com.mathias8dev.kinotest.domain.data.KVehicle
import com.mathias8dev.kinotest.domain.data.Strings
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface KVehicleApiService {
    @GET("vehicle/list?icon=1&lang=fr-gb&forceStandard=1&outputFormat=json&appToken=8qohg5a9c6q6x58szpyxizvp91yary3setxdxutl10dugtel1syjs6gmrp33oo40a356j2cxt6vdcpzg095drsym5blnyen0hi4bdq32j61clfux2i9vtuhr")
    fun getVehicleList(): List<KVehicle>

    companion object {
        var apiService: KVehicleApiService? = null
        fun getInstance(): KVehicleApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(Strings.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(KVehicleApiService::class.java)
            }
            return apiService!!
        }
    }
}




