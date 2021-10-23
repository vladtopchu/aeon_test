package com.topchu.aeon_test_app.presentation.payments

import com.topchu.aeon_test_app.data.remote.models.PaymentModel
import com.topchu.aeon_test_app.data.remote.models.PaymentsResponseModel

data class PaymentsState(
    val isLoading: Boolean = false,
    val payments: List<PaymentModel>? = null,
    val error: String = ""
)