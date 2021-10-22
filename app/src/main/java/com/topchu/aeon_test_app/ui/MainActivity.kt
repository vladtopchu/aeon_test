package com.topchu.aeon_test_app.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.topchu.aeon_test_app.R
import com.topchu.aeon_test_app.databinding.ActivityMainBinding
import com.topchu.aeon_test_app.utils.Constants.TOKEN_KEY
import com.topchu.aeon_test_app.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host)

        if(sharedPref.getUserToken() != null) {
            navController.navigate(R.id.paymentsFragment)
        }
    }
}