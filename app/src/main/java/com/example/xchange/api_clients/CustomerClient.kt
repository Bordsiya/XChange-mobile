package com.example.xchange.api_clients

import android.content.Context
import com.example.xchange.Constants
import com.example.xchange.network.AuthInterceptor
import com.example.xchange.retrofit.CustomerAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomerClient {
    private lateinit var customerAPI: CustomerAPI

    fun getCustomerAPI(context: Context): CustomerAPI {
        if (!::customerAPI.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            customerAPI = retrofit.create(CustomerAPI::class.java)
        }
        return customerAPI
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}