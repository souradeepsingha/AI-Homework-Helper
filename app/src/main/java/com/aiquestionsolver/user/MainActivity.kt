package com.aiquestionsolver.user

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
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
//    lateinit var btnCopy: Button
    lateinit var mAdView : AdView
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


        mAdView = findViewById(R.id.adView)

        // Load the ad
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        spinner = findViewById<Spinner>(R.id.spinner)
        val codingLanguage =findViewById<EditText>(R.id.codelang)

        // Get the selected item from Spinner


        // Assuming you have a List<String> items containing your spinner items

        // Assuming you have a List<String> items containing your spinner items
        val items: MutableList<String> = ArrayList()
        items.add("Generate Code")
        items.add("Generate code with Explainer")

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

val lang=codingLanguage.text.toString()
            // Get the selected item from Spinner
            val selectedOutputType = spinner.selectedItem.toString()

            // Combine the data into a single string
            val combinedData ="This is a coding question"+ userInput+"you have to generate code in "+lang+"you have to "+selectedOutputType

            // Create an Intent to start the next activity
            val intent = Intent(this@MainActivity, MainActivity3::class.java)

            // Pass the user input to MainActivity3 using Intent extras
            intent.putExtra("userInput", combinedData)
            // Start MainActivity3
            startActivity(intent)
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

}