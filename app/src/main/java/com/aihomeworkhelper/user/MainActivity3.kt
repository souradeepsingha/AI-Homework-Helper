package com.aihomeworkhelper.user

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    lateinit var btnCopy: Button
//    lateinit var mAdView : AdView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        progressBar = findViewById(R.id.progress)
        btnCopy = findViewById<Button>(R.id.btnCopy)
        // Receive the prompt text from MainActivity
        val prompt =intent.getStringExtra("userInput")
        val tVResult = findViewById<TextView>(R.id.tVResult)
//        mAdView = findViewById(R.id.adView)
//
////         Load the ad
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
        btnCopy.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", tVResult.text)
            clipboardManager.setPrimaryClip(clipData)
            // Optionally, you can provide feedback to the user
            // For example, you can show a toast message
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
        }
        fun1(prompt.toString(), tVResult)
    }
    private fun fun1(prompt: String, resultTextView: TextView) {
        GlobalScope.launch(Dispatchers.Main) {
            val responseText = fetchContentFromAPI(prompt)
            resultTextView.text = responseText
            progressBar.visibility = View.GONE
            btnCopy.visibility= View.VISIBLE
        }
    }

    private suspend fun fetchContentFromAPI(prompt: String): String {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyD2b6YvKBAClXqsHDoKUPlRb2zbHFsO_PA"
        )

        try {
            val response = generativeModel.generateContent(prompt)
            var responseText = response.text

            // Remove "**" and "*" from the response text
            responseText = responseText?.replace("**", "")?.replace("*", "")

            // Check if the response contains "**" and make the entire response bold
            if (responseText != null && responseText.contains("**")) {
                val spannable = SpannableString(responseText)
                spannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    responseText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                return spannable.toString()
            }

            return responseText ?: ""
        } catch (e: Exception) {
            // Log the error
            Log.e(ContentValues.TAG, "Error fetching data: ${e.message}", e)
            // Return an error message
            return "Error fetching data: ${e.message}"
        }
    }
}