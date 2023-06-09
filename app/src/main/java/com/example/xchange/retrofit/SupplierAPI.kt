package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.Notification
import com.example.xchange.model.Product
import com.example.xchange.model.UploadProductsRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SupplierAPI {
    @POST(Constants.SUPPLIER_STUFF_URL)
    fun uploadProducts(@Body uploadProductsRequest: UploadProductsRequest): Call<Void>
    @GET(Constants.NOTIFICATION_LIST_URL)
    fun getNotifications(): Call<List<Notification>>
    @GET(Constants.SUPPLIER_STUFF_URL)
    fun viewProducts(): Call<List<Product>>
}