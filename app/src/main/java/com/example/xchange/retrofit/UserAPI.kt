package com.example.xchange.retrofit

import com.example.xchange.Constants
import com.example.xchange.model.AuthCredentials
import com.example.xchange.model.TokensRoleResponse
import com.example.xchange.model.RegisterCredentials
import com.example.xchange.model.ResponseMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserAPI {
    @Headers("isAuthorizable: false")
    @POST(Constants.LOGIN_URL)
    fun login(@Body authCredentials: AuthCredentials): Call<Void>

    @POST(Constants.LOGIN_URL)
    fun loginWithAccessToken(): Call<Void>

    @Headers("isAuthorizable: false")
    @POST(Constants.REGISTER_URL)
    fun register(@Body registerCredentials: RegisterCredentials): Call<Void>

}