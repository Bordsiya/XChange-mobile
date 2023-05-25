package com.example.xchange.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)
