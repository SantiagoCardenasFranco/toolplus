package com.uco.pdm.toolplus.integration

import com.uco.pdm.toolplus.integration.request.LoginRequest
import com.uco.pdm.toolplus.integration.response.PriceResponse
import com.uco.pdm.toolplus.integration.response.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIImpl {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://murmuring-ravine-57242.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Cambiar el localhost por la ruta local, en un cmd ingresa ipconfig y encuentras el ip local ej: 127.xxx.xxx.x

    fun operatePrice(cantidad: Int, nombresImpuestos: String, valor: Int, TOKEN: String): PriceResponse {
        val apiService = getRetrofit().create(APIService::class.java)
        val token =
            "Bearer $TOKEN"

        val priceResponseDeferred = runBlocking(Dispatchers.IO) {
            async {
                val call = apiService.getPrice(
                    "api/v1/rest/impuestos?cantidad=$cantidad&nombresImpuestos=$nombresImpuestos&valor=$valor",
                    token
                ).execute()
                call.body() ?: throw IllegalStateException("Price response body is null")

            }

        }
        return runBlocking {
            priceResponseDeferred.await()
        }
    }

    fun operateAut(username: String, password: String): TokenResponse {
        val apiService = getRetrofit().create(APIService::class.java)
        val loginRequest = LoginRequest(username, password)
        val tokenResponseDeferred = runBlocking(Dispatchers.IO) {
            async {
                val call = apiService.getToken(
                    "authenticate", loginRequest).execute()
                call.body() ?: throw IllegalStateException("Token response body is null")

            }

        }
        return runBlocking {
            tokenResponseDeferred.await()
        }
    }

}