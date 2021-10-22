package com.topchu.aeon_test_app.data.remote.models

data class TokenModel(
    val token: String?
)

data class LoginResponseModel(
    val success: String?,
    val response: TokenModel?
)