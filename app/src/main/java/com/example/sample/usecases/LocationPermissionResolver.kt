package com.example.sample.usecases

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sample.MainActivity

class LocationPermissionResolver(private val context: MainActivity) {

    fun invoke(action: () -> Unit) {
        val launcher =
            context.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { action() }

        launcher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
    }
}