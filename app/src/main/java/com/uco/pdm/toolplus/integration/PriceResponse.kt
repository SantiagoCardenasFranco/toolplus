package com.uco.pdm.toolplus.integration

import com.google.gson.annotations.SerializedName

data class PriceResponse (
    @SerializedName("tipoImpuesto") var tipoImpuesto: List<String>,
    @SerializedName("resultado") var resultado:Int
)