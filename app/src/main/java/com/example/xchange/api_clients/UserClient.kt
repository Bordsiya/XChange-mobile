package com.example.xchange.api_clients

import android.content.Context
import com.example.xchange.Constants
import com.example.xchange.network.AuthInterceptor
import com.example.xchange.retrofit.UserAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class UserClient {
    private lateinit var userAPI: UserAPI

    fun getUserAPI(context: Context): UserAPI {
        if (!::userAPI.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient(context))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            userAPI = retrofit.create(UserAPI::class.java)
        }
        return userAPI
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}