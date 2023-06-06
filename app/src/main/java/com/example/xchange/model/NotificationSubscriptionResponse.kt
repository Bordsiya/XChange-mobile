package com.example.xchange.model

import com.google.gson.annotations.SerializedName

data class NotificationSubscriptionResponse(
    @SerializedName("model_id")
    val modelId: String,
    @SerializedName("type")
    val type: String
)
