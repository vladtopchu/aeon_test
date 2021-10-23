package com.topchu.aeon_test_app.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.topchu.aeon_test_app.data.remote.TestService
import com.topchu.aeon_test_app.utils.Constants.BASE_URL
import com.topchu.aeon_test_app.utils.Constants.TOKEN_KEY
import com.topchu.aeon_test_app.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context)
            = SharedPref(context)

    @Provides
    @Singleton
    fun provideApi(): TestService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TestService::class.java)
}