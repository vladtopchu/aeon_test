package com.topchu.aeon_test_app.domain

import android.util.Log
import com.topchu.aeon_test_app.data.remote.TestService
import com.topchu.aeon_test_app.data.remote.models.AuthModel
import com.topchu.aeon_test_app.data.remote.models.AuthResponseModel
import com.topchu.aeon_test_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val service: TestService
) {
    operator fun invoke(authBody: AuthModel): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val authResponse = service.login(authBody)
            if(authResponse.success != null
                && authResponse.success == "true"
                && authResponse.response != null
                && authResponse.response.token != null) {
                emit(Resource.Success(authResponse.response.token))
            } else if(authResponse.success == "false"){
                emit(Resource.Error("User doesn't exist or password is incorrect"))
            } else {
                emit(Resource.Error("An unexpected error occurred"))
            }
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection. Message: ".plus(e.message)))
        }
    }
}