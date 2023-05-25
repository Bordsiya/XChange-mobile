package com.example.xchange.api_clients

import android.content.Context
import com.example.xchange.Constants
import com.example.xchange.network.AuthInterceptor
import com.example.xchange.retrofit.SupplierAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SupplierClient {
    private lateinit var supplierAPI: SupplierAPI

    fun getSupplierAPI(context: Context): SupplierAPI {
        if (!::supplierAPI.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            supplierAPI = retrofit.create(SupplierAPI::class.java)
        }
        return supplierAPI
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}