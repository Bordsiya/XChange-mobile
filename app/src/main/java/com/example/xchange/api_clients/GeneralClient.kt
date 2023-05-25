package com.example.xchange.api_clients

import android.content.Context
import com.example.xchange.Constants
import com.example.xchange.network.AuthInterceptor
import com.example.xchange.retrofit.GeneralAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeneralClient {
    private lateinit var generalAPI: GeneralAPI

    fun getGeneralAPI(context: Context): GeneralAPI {
        if (!::generalAPI.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            generalAPI = retrofit.create(GeneralAPI::class.java)
        }
        return generalAPI
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}