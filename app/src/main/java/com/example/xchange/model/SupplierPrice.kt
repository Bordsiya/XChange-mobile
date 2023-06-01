package com.example.xchange.model

import com.google.gson.annotations.SerializedName

data class SupplierPrice(
    @SerializedName("supplierId")
    val supplierId: Long,
    @SerializedName("price")
    val price: Price
)
