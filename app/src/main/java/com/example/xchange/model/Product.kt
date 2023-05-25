package com.example.xchange.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("stuff_type")
    val stuffType: String,
    @SerializedName("model_id")
    val modelId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("property_list")
    val propertyList: List<Property>,
    @SerializedName("supplier_prices")
    val supplierPrices: List<SupplierPrice>
)
