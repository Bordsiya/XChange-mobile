package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.BuyProductsRequest
import com.example.xchange.model.Notification
import com.example.xchange.model.NotificationSubscriptionRequest
import com.example.xchange.model.NotificationSubscriptionResponse
import com.example.xchange.model.Product
import com.example.xchange.model.SearchRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CustomerAPI {
    @POST(Constants.STUFF_SEARCH_BY_TEXT_URL)
    fun searchProducts(@Body searchRequest: SearchRequest): Call<List<Product>>
    @POST(Constants.NOTIFICATION_STUFF_URL)
    fun changeNotificationSubscription(
        @Body notificationSubscriptionRequest: NotificationSubscriptionRequest): Call<Void>
    @POST(Constants.STUFF_BUY_REQUEST)
    fun buyProducts(@Body buyProductsRequest : BuyProductsRequest): Call<Void>
    @GET(Constants.NOTIFICATION_LIST_URL)
    fun getNotifications(): Call<List<Notification>>
    @GET(Constants.NOTIFICATION_SUBSCRIPTION_LIST)
    fun getUsersSubscriptions(): Call<List<NotificationSubscriptionResponse>>
}