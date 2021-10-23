package com.topchu.aeon_test_app.presentation.auth

data class AuthState(
    val isLoading: Boolean = false,
    val token: String? = null,
    val error: String = ""
)