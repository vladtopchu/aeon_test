package com.topchu.aeon_test_app.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.topchu.aeon_test_app.data.remote.TestService
import com.topchu.aeon_test_app.data.remote.models.LoginModel
import com.topchu.aeon_test_app.data.remote.models.LoginResponseModel
import com.topchu.aeon_test_app.data.remote.models.PaymentModel
import com.topchu.aeon_test_app.data.remote.models.PaymentsResponseModel
import com.topchu.aeon_test_app.utils.SharedPref
import com.topchu.aeon_test_app.utils.asLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val service: TestService, private val sharedPref: SharedPref) : ViewModel() {

    private var _success: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _error: MutableLiveData<Boolean> = MutableLiveData(false)

    val success = _success.asLiveData()
    val error = _error.asLiveData()

    fun login(loginData: LoginModel) {
        val call = service.login(loginData)
        call.enqueue(object: Callback<LoginResponseModel> {
            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.d("TESTTEST", t.message.toString())
            }

            override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                if(response.isSuccessful) {
                    if(response.body()?.success == "true"){
                        sharedPref.setUserToken(response.body()!!.response!!.token!!)
                        _success.postValue(true)
                    } else {
                        _error.postValue(true)
                    }
                } else {
                    Log.d("TESTTEST", "Response wasn't successful")
                }
            }
        })
    }
}