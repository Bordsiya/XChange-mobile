package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.BuyProductsRequest
import com.example.xchange.model.Notification
import com.example.xchange.model.UserInfoResponse
import com.example.xchange.model.NotificationSubscriptionRequest
import com.example.xchange.model.Product
import com.example.xchange.model.SearchRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CustomerAPI {
    @Headers("Accept: application/json")
    @POST(Constants.STUFF_SEARCH_BY_TEXT_URL)
    fun searchProducts(@Body searchRequest: SearchRequest): Call<List<Product>>
    @Headers("Accept: application/json")
    @POST(Constants.NOTIFICATION_STUFF_URL)
    fun changeNotificationSubscription(
        @Body notificationSubscriptionRequest: NotificationSubscriptionRequest): Call<Void>
    @Headers("Accept: application/json")
    @POST(Constants.STUFF_BUY_REQUEST)
    fun buyProducts(@Body buyProductsRequest : BuyProductsRequest): Call<Void>
    @Headers("Accept: application/json")
    @GET(Constants.NOTIFICATION_LIST_URL)
    fun getSubscriptions(): Call<List<Notification>>
}