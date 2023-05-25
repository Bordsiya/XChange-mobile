package com.example.xchange.model

data class TokensRoleResponse(
    val access_token: String,
    val refresh_token: String,
    val role: String
)
