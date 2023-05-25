package com.example.xchange.model

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("status")
    val status: String,
    @SerializedName("text")
    val text: String
)
