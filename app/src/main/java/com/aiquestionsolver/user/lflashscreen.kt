package com.aiquestionsolver.user

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.aiquestionsolver.user.databinding.ActivityLflashscreenBinding

class lflashscreen : AppCompatActivity() {

    private lateinit var binding: ActivityLflashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLflashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#000000")
        //icon color -> white
        //icon color -> white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        // Set a timer for the splash screen duration (e.g., 2 seconds)
        val splashScreenDuration = 2000 // in milliseconds
        val timerThread = object : Thread() {
            override fun run() {
                try {
                    sleep(splashScreenDuration.toLong())
                    // Start the main activity after the splash screen duration
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Close the splash screen activity
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        timerThread.start()
    }
}