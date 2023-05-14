package com.uco.pdm.toolplus.integration

import com.uco.pdm.toolplus.integration.request.LoginRequest
import com.uco.pdm.toolplus.integration.response.PriceResponse
import com.uco.pdm.toolplus.integration.response.TokenResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
    @POST()
    fun getToken(@Url url:String, @Body loginRequest: LoginRequest): Call<TokenResponse>

    @GET()
    fun getPrice(@Url url:String, @Header("Authorization") token: String): Call<PriceResponse>
}