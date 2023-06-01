package com.example.xchange.model

data class BuyProductsRequest(
    val supplier_id: Long,
    val stuff_type: String,
    val model_id: String,
    val count: Long
)
