package com.topchu.aeon_test_app.data.remote.models

data class TokenModel(
    val token: String?
)

data class AuthResponseModel(
    val success: String?,
    val response: TokenModel?
)