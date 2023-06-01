package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.Notification
import com.example.xchange.model.UploadProductsRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SupplierAPI {
    @Headers("Accept: application/json")
    @POST(Constants.SUPPLIER_STUFF_URL)
    fun uploadProducts(@Body uploadProductsRequest: UploadProductsRequest): Call<Void>
    @Headers("Accept: application/json")
    @GET(Constants.NOTIFICATION_LIST_URL)
    fun getNotifications(): Call<List<Notification>>
    @Headers("Accept: application/json")
    @GET(Constants.SUPPLIER_STUFF_URL)
    fun viewProducts(): Call<String>
}