package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.Notification
import retrofit2.Call
import retrofit2.http.GET

interface GeneralAPI {

    @GET(Constants.NOTIFICATION_LIST_URL)
    fun getNotifications(): Call<List<Notification>>

}