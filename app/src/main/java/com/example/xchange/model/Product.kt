package com.example.xchange.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("stuffType")
    val stuffType: String,
    @SerializedName("modelId")
    val modelId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("properties")
    val propertyList: List<Property>,
    @SerializedName("supplierPrices")
    val supplierPrices: List<SupplierPrice>
)
