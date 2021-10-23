package com.topchu.aeon_test_app.data.remote

import com.topchu.aeon_test_app.data.remote.models.AuthModel
import com.topchu.aeon_test_app.data.remote.models.AuthResponseModel
import com.topchu.aeon_test_app.data.remote.models.PaymentsResponseModel
import retrofit2.http.*

interface TestService {
    @Headers("app-key:12345", "v:1")
    @POST("login")
    suspend fun login(@Body authBody: AuthModel): AuthResponseModel

    @Headers("app-key:12345", "v:1")
    @GET("payments")
    suspend fun getPayments(@Query("token") token: String): PaymentsResponseModel
}