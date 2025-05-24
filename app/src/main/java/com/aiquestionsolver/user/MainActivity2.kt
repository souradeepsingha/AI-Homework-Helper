package com.aiquestionsolver.user

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking
val tVResult=""
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val tVResult = findViewById<TextView>(R.id.tVResult)

        // Receive the prompt text from MainActivity
        val prompt = intent.getStringExtra("prompt")

        val generativeModel = GenerativeModel(
            // For text-only input, use the gemini-pro model
            modelName = "gemini-pro",
            apiKey = "AIzaSyD2b6YvKBAClXqsHDoKUPlRb2zbHFsO_PA"
            // ENTER YOUR KEY
        )

        // Delay function to introduce a delay of 1 second
        fun delayedExecution(delayMillis: Long = 1000, action: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                action()
            }, delayMillis)
        }

// Your code snippet
        delayedExecution {
            runBlocking {
                val response = generativeModel.generateContent(prompt.toString())
                var responseText = response.text

                // Remove "**" from the response text
                responseText = responseText?.replace("**", "")
                responseText = responseText?.replace("*", "")

                // Check if the response contains a space (" ") and make the entire response bold
                if (responseText != null) {
                    if (responseText.contains("**")) {
                        val spannable = SpannableString(responseText)
                        spannable.setSpan(
                            StyleSpan(Typeface.BOLD),
                            0,
                            responseText.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )

                        tVResult.text = spannable
                    } else {
                        tVResult.text = responseText
                    }
                }
            }
        }
    }


}