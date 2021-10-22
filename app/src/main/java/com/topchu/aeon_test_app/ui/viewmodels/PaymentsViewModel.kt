package com.topchu.aeon_test_app.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.topchu.aeon_test_app.data.remote.TestService
import com.topchu.aeon_test_app.data.remote.models.PaymentModel
import com.topchu.aeon_test_app.data.remote.models.PaymentsResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(private val service: TestService) : ViewModel() {

    private var payments: MutableLiveData<List<PaymentModel>?> = MutableLiveData()

    fun getPaymentsObserver(): MutableLiveData<List<PaymentModel>?> {
        return payments
    }

    fun getPayments(token: String) {
        val call = service.getPayments(token)
        call.enqueue(object: Callback<PaymentsResponseModel> {
            override fun onFailure(call: Call<PaymentsResponseModel>, t: Throwable) {
                Log.d("TESTTEST", t.message.toString())
                payments.postValue(null)
            }

            override fun onResponse(call: Call<PaymentsResponseModel>, response: Response<PaymentsResponseModel>) {
                if(response.isSuccessful) {
                    payments.postValue(response.body()?.response)
                } else {
                    payments.postValue(null)
                }
            }
        })
    }
}