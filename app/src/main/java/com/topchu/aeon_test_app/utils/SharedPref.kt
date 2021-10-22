package com.topchu.aeon_test_app.utils

import android.content.Context
import android.content.SharedPreferences
import com.topchu.aeon_test_app.utils.Constants.FILENAME_KEY
import com.topchu.aeon_test_app.utils.Constants.TOKEN_KEY

class SharedPref(context: Context) {
    private var sharedPreferences: SharedPreferences = context
        .getSharedPreferences(FILENAME_KEY, Context.MODE_PRIVATE)

    fun wipeUserToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    fun setUserToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getUserToken(): String? = sharedPreferences.getString(TOKEN_KEY, null)
}
