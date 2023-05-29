package com.example.xchange.network

import android.content.Context
import android.util.Log
import com.example.xchange.GlobalNavigator
import com.example.xchange.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

class AuthInterceptor (context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestDefault = chain.request()
        val accessToken = sessionManager.fetchAccessToken()
        val refreshToken = sessionManager.fetchRefreshToken()
        val shouldRemoveAuthHeaders = requestDefault.headers["isAuthorizable"] == "false"

        var requestAccess = requestDefault
        if (accessToken != null && refreshToken != null && !shouldRemoveAuthHeaders) {
            requestAccess = requestDefault.newBuilder().header("X-Access-Token", "$accessToken").build()
        }

        val responseAccess = chain.proceed(requestAccess)
        if (responseAccess.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            responseAccess.close()
            val requestRefresh = requestDefault.newBuilder().header("X-Refresh-Token", "$refreshToken").build()
            val responseRefresh = chain.proceed(requestRefresh)
            if(responseRefresh.code != HttpURLConnection.HTTP_UNAUTHORIZED) {
                responseRefresh.close()
                val newAccessToken = responseRefresh.headers["X-Access-Token"]
                return if (newAccessToken == null) {
                    GlobalNavigator.logout()
                    responseRefresh
                } else {
                    sessionManager.saveAccessToken(newAccessToken)
                    val requestNewAccess = requestDefault.newBuilder().header("X-Access-Token", "$newAccessToken").build()
                    chain.proceed(requestNewAccess)
                }
            }
            else {
                responseRefresh.close()
                GlobalNavigator.logout()
                return responseRefresh
            }
        }
        else if (responseAccess.code == HttpURLConnection.HTTP_OK && (accessToken == null || refreshToken == null)) {
            sessionManager.saveAccessToken(responseAccess.headers["X-Access-Token"]!!)
            sessionManager.saveRefreshToken(responseAccess.headers["X-Refresh-Token"]!!)
        }
        return responseAccess
    }

}