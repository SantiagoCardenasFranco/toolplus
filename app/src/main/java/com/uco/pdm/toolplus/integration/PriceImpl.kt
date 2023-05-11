package com.uco.pdm.toolplus.integration

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PriceImpl {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://murmuring-ravine-57242.herokuapp.com/api/v1/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Cambiar el localhost por la ruta local, en un cmd ingresa ipconfig y encuentras el ip local ej: 127.xxx.xxx.x

    fun operatePrice(cantidad: Int, nombresImpuestos: String, valor: Int): PriceResponse {
        val apiService = getRetrofit().create(APIService::class.java)
        val token =
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmEiLCJleHAiOjE2ODM3ODI0NDcsImlhdCI6MTY4Mzc2NDQ0N30.MFr8rpD-1Ld0VHeaJCYf6rEfQVZvs5H8meyR3f-XgHVj60IYgT_S3Qp8SsofWtzht0V9SbFLw2IpqznXNpxO0A"

        val priceResponseDeferred = runBlocking(Dispatchers.IO) {
            async {
                val call = apiService.getPrice(
                    "impuestos?cantidad=$cantidad&nombresImpuestos=$nombresImpuestos&valor=$valor",
                    token
                ).execute()
                call.body() ?: throw IllegalStateException("Price response body is null")

            }

        }
        return runBlocking {
            priceResponseDeferred.await()
        }
    }

    /*fun operateAut(cantidad: Int, nombresImpuestos: String, valor: Int): PriceResponse {
        val apiService = getRetrofit().create(APIService::class.java)
        val token =
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmEiLCJleHAiOjE2ODM3ODE1NzAsImlhdCI6MTY4Mzc2MzU3MH0.jd1M5_-3EYfP3pEmoyOTK_mjEr4S7BJTRCPihWNzHIbyiL30cgHm7NfTBudY3NaiPKbkDJrgElUpLcVd-2GzaQ"

        val priceResponseDeferred = runBlocking(Dispatchers.IO) {
            async {
                val call = apiService.getPrice(
                    "impuestos?cantidad=$cantidad&nombresImpuestos=$nombresImpuestos&valor=$valor",
                    token
                ).execute()
                call.body() ?: throw IllegalStateException("Price response body is null")

            }

        }
        return runBlocking {
            priceResponseDeferred.await()
        }
    }*/

}