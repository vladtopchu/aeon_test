package com.topchu.aeon_test_app.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.topchu.aeon_test_app.data.remote.models.AuthModel
import com.topchu.aeon_test_app.domain.AuthUseCase
import com.topchu.aeon_test_app.utils.Resource
import com.topchu.aeon_test_app.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private var _state: MutableLiveData<AuthState> = MutableLiveData(AuthState())
    var state = _state.asLiveData()

    fun login(authBody: AuthModel) {
        authUseCase(authBody).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuthState(token = result.data)
                }
                is Resource.Error -> {
                    _state.value = AuthState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}