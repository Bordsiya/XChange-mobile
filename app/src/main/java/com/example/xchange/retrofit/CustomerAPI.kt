package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.ChangeNotificationSubscriptionRequest
import com.example.xchange.model.Product
import com.example.xchange.model.SearchRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CustomerAPI {
    @Headers("isAuthorizable: false")
    @POST(Constants.STUFF_SEARCH_BY_TEXT_URL)
    fun searchProducts(@Body searchRequest: SearchRequest): Call<List<Product>>

    @POST(Constants.NOTIFICATION_STUFF_URL)
    fun changeNotificationSubscription(
        @Body changeNotificationSubscriptionRequest: ChangeNotificationSubscriptionRequest): Call<Void>

}