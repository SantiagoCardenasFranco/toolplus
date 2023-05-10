package com.uco.pdm.toolplus.integration

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PriceImpl {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun operatePrice(cantidad: Int, nombresImpuestos: String, valor: Int): PriceResponse {
        val call =
            getRetrofit().create(APIService::class.java).getPrice("impuestos?cantidad=$cantidad&nombresImpuestos=$nombresImpuestos&valor=$valor")
                .execute()
        println("raw "+ call.raw())
        println("header "+ call.headers())
        println("message "+call.message())
        println("body " + call.body())
        return call.body() as PriceResponse


    }

}