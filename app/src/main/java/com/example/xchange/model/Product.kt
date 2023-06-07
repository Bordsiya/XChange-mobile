package com.example.xchange.model

import com.example.xchange.utils.Utils
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
) {
    override fun toString(): String {
        var productStr = "$title "
        for (property in propertyList) {
            if (property.value != "COUNTRY") productStr += property.value + " "
            else productStr += Utils.convertCurrencyToEmoji(property.value) + " "
        }
        for (supplierPricesElem in supplierPrices) {
            productStr += supplierPricesElem.price.amount + " "
            productStr += supplierPricesElem.price.currency + "\n"
        }
        return productStr
    }
}
