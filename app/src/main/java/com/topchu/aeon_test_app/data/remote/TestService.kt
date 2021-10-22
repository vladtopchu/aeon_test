package com.topchu.aeon_test_app.data.remote

import com.topchu.aeon_test_app.data.remote.models.LoginModel
import com.topchu.aeon_test_app.data.remote.models.LoginResponseModel
import com.topchu.aeon_test_app.data.remote.models.PaymentsResponseModel
import retrofit2.Call
import retrofit2.http.*

interface TestService {
    @Headers("app-key:12345", "v:1")
    @POST("login")
    fun login(@Body loginBody: LoginModel): Call<LoginResponseModel>

    @Headers("app-key:12345", "v:1")
    @GET("payments")
    fun getPayments(@Query("token") token: String): Call<PaymentsResponseModel>
}