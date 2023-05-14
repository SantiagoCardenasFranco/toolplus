package com.uco.pdm.toolplus.integration.response

import com.google.gson.annotations.SerializedName

data class TokenResponse (
    @SerializedName("token") var token: String
)