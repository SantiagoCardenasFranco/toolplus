package com.uco.pdm.toolplus.integration

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET()
    fun getPrice(@Url url:String): Call<PriceResponse>
}