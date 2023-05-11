package com.uco.pdm.toolplus.integration

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Url

interface APIService {
    @GET()
    fun getPrice(@Url url:String, @Header("Authorization") token: String): Call<PriceResponse>
}