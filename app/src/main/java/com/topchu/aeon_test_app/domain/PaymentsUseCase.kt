package com.topchu.aeon_test_app.domain

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.topchu.aeon_test_app.data.remote.TestService
import com.topchu.aeon_test_app.data.remote.models.PaymentModel
import com.topchu.aeon_test_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PaymentsUseCase @Inject constructor(
    private val service: TestService
) {
    operator fun invoke(token: String): Flow<Resource<List<PaymentModel>>> = flow {
        try {
            emit(Resource.Loading())
            val paymentsResponse = service.getPayments(token)
            if(paymentsResponse.success != null
                && paymentsResponse.success == "true"
                && paymentsResponse.response != null) {
                emit(Resource.Success(paymentsResponse.response))
            } else{
                emit(Resource.Error("An unexpected error occurred"))
            }
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred. Message: ".plus(e.message())))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection. Message: ".plus(e.message)))
        }
    }
}