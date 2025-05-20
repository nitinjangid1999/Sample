package com.example.sample

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sample.usecases.LocationPermissionResolver
import com.example.sample.usecases.SendEventToBureau

class MainActivity : AppCompatActivity() {

    private lateinit var btnSendEvent: Button
    private val sendEvent by lazy { SendEventToBureau(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSendEvent = findViewById<Button>(R.id.btnSendEvent)

        btnSendEvent.setOnClickListener { sendEvent.invoke() }

        val locationPermissionResolver = LocationPermissionResolver(this@MainActivity)
        locationPermissionResolver.invoke()
    }
}