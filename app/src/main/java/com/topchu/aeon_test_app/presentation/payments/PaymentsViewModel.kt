package com.topchu.aeon_test_app.presentation.payments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.topchu.aeon_test_app.data.remote.TestService
import com.topchu.aeon_test_app.data.remote.models.PaymentModel
import com.topchu.aeon_test_app.data.remote.models.PaymentsResponseModel
import com.topchu.aeon_test_app.domain.PaymentsUseCase
import com.topchu.aeon_test_app.presentation.auth.AuthState
import com.topchu.aeon_test_app.utils.Resource
import com.topchu.aeon_test_app.utils.asLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val paymentsUseCase: PaymentsUseCase
) : ViewModel() {

    private var _state: MutableLiveData<PaymentsState> = MutableLiveData(PaymentsState())
    var state = _state.asLiveData()

    fun getPayments(token: String) {
        paymentsUseCase(token).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PaymentsState(payments = result.data)
                }
                is Resource.Error -> {
                    _state.value = PaymentsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PaymentsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}