package com.topchu.aeon_test_app.data.remote.models

data class PaymentModel(
    val desc: String? = "",
    val amount: Float? = 0F,
    val currency: String? = "",
    val created: Long? = 0L,
)

data class PaymentsResponseModel(
    val success: String? = "",
    val response: List<PaymentModel>? = listOf()
)