package com.aihomeworkhelper.user

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
//    lateinit var btnCopy: Button
//    lateinit var mAdView : AdView
//    lateinit var lottieAnimationView : LottieAnimationView
    lateinit var spinner: Spinner

    lateinit var selectedOutputType :String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#000000")
        //icon color -> white
        //icon color -> white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
        }

        // Initialize the ProgressBar
//        progressBar = findViewById(R.id.progress)
        val eTPrompt= findViewById<EditText>(R.id.eTPrompt)
        val btnSubmit= findViewById<Button>(R.id.btnSubmit)
//        val tVResult= findViewById<TextView>(R.id.tVResult)
//      lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)

//
//        mAdView = findViewById(R.id.adView)
//
//        // Load the ad
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)

        spinner = findViewById<Spinner>(R.id.spinner)


        // Get the selected item from Spinner


        // Assuming you have a List<String> items containing your spinner items

        // Assuming you have a List<String> items containing your spinner items
        val items: MutableList<String> = ArrayList()
        items.add("Practice")
        items.add("Preparation")
        items.add("Research")
        items.add("Integration")
        items.add("Extension")
// Add more items as needed
// Set your desired item color and list background color
        // Add more items as needed
// Set your desired item color and list background color
        val hexItemColor = "#000000" // blue

        val hexListBackgroundColor = "#FFFFFF" // white


// Convert hex color values to integer

// Convert hex color values to integer
        val itemColor = Color.parseColor(hexItemColor)
        val listBackgroundColor = Color.parseColor(hexListBackgroundColor)


        // Create and set the custom adapter


        // Create and set the custom adapter
        val adapter = CustomSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items,
            itemColor,
            listBackgroundColor
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner =findViewById<Spinner>(R.id.spinner)

        spinner.adapter = adapter

        // Set a listener to handle item selections

        // Set a listener to handle item selections
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item as a String
                val item = parentView.getItemAtPosition(position).toString()
               selectedOutputType = spinner.selectedItem.toString()
                // Do something with the selected item (e.g., set it to a TextView or use it in your logic)
                // For example, you can set it to a TextView:
                // textView.setText(item);

                // If you still need to convert it to an integer, make sure it's a valid integer value
                try {

                } catch (e: NumberFormatException) {
                    // Handle the case where the selected item is not a valid integer
                    e.printStackTrace()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }


// Add more items as needed
// Set your desired item color and list background color
        // Add more items as needed
// Set your desired item color and list background color
        val hexItemColor2 = "#000000" // blue

        val hexListBackgroundColor2 = "#FFFFFF" // white


// Convert hex color values to integer

// Convert hex color values to integer
        val itemColor2 = Color.parseColor(hexItemColor2)
        val listBackgroundColor2 = Color.parseColor(hexListBackgroundColor2)

        val items2: MutableList<String> = ArrayList()
        items2.add("Short Essay")
        items2.add("Long Essay")
        // Create and set the custom adapter


        // Create and set the custom adapter
        val adapter2 = CustomSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items2,
            itemColor2,
            listBackgroundColor2
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        val spinner: Spinner =findViewById<Spinner>(R.id.spinner)
//        val spinner2: Spinner =findViewById<Spinner>(R.id.spinner2)


//         btnCopy = findViewById<Button>(R.id.btnCopy)
//        progressBar.visibility = View.GONE
//        btnCopy.setOnClickListener {
//            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText("text", tVResult.text)
//            clipboardManager.setPrimaryClip(clipData)
//            // Optionally, you can provide feedback to the user
//            // For example, you can show a toast message
//            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
//        }
        btnSubmit.setOnClickListener {
//            progressBar.visibility = View.VISIBLE
//            lottieAnimationView.visibility=View.GONE
            val userInput = eTPrompt.text.toString()
            // Check if userInput is null or empty
            if (userInput.isNullOrEmpty()) {
                // Handle the case where userInput is null or empty
                // For example, show a toast message to the user
                Toast.makeText(this@MainActivity, "Please enter a prompt.", Toast.LENGTH_SHORT).show()
            } else {
                // Get the selected language from Spinner


                // Get the selected output type from Spinner
                val selectedOutputType = spinner.selectedItem.toString()

                // Combine the data into a single string
                val combinedData = "This is a AI Homework Generator app which help to generate homework this is user prompt: $userInput. You have to write the solution in $selectedOutputType. Homework size random "

                // Create an Intent to start the next activity
                val intent = Intent(this@MainActivity, MainActivity3::class.java)

                // Pass the user input to MainActivity3 using Intent extras
                intent.putExtra("userInput", combinedData)

                // Start MainActivity3
                startActivity(intent)
            }
        }


    }

    private fun fun1(prompt: String, resultTextView: TextView) {
        GlobalScope.launch(Dispatchers.Main) {
            val responseText = fetchContentFromAPI(prompt)
            resultTextView.text = responseText
//            progressBar.visibility = View.GONE
//            btnCopy.visibility=View.VISIBLE
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
            Log.e(TAG, "Error fetching data: ${e.message}", e)
            // Return an error message
            return "Error fetching data: ${e.message}"
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        // Create an Intent to start the MainActivity
        val intent = Intent(this, Homepage::class.java)
        // Add flags to clear the back stack so that MainActivity becomes the only activity
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // Start MainActivity
        startActivity(intent)
        // Finish the current activity
        finish()
    }

}