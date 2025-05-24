package com.aihomeworkhelper.user

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class Homepage : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null
    var materialToolbar: MaterialToolbar? = null
//    lateinit var mAdView : AdView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)

        val shortcon = findViewById<LinearLayout>(R.id.shortcon) // Assuming shortcon is your LinearLayout ID

        shortcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Change MainActivity to your desired activity
            intent.putExtra("SOME_KEY", "300")
            startActivity(intent)
            finish() // If you want to finish the current activity after navigating
        }
        val mediumcon= findViewById<LinearLayout>(R.id.mediumcontent) // Assuming shortcon is your LinearLayout ID

        mediumcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Change MainActivity to your desired activity
            intent.putExtra("SOME_KEY", "600")
            startActivity(intent)
            finish() // If you want to finish the current activity after navigating
        }

        val longcon= findViewById<LinearLayout>(R.id.longcon) // Assuming shortcon is your LinearLayout ID

        longcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Change MainActivity to your desired activity
            intent.putExtra("SOME_KEY", "1200")
            startActivity(intent)
            finish() // If you want to finish the current activity after navigating
        }
        val customcon= findViewById<LinearLayout>(R.id.customcon) // Assuming shortcon is your LinearLayout ID

        customcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Change MainActivity to your desired activity
            intent.putExtra("SOME_KEY", "0")
            startActivity(intent)
            finish() // If you want to finish the current activity after navigating
        }
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        materialToolbar = findViewById<MaterialToolbar>(R.id.materialtoolbar)

        navigationView = findViewById<NavigationView>(R.id.navigationview)
        val toggle = ActionBarDrawerToggle(
            this@Homepage,
            drawerLayout,
            materialToolbar,
            R.string.close_drawer,
            R.string.open_drawer
        )




//        mAdView = findViewById(R.id.adView)
//
//        // Load the ad
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
//        drawerLayout?.addDrawerListener(toggle)
        val navigationView = findViewById<NavigationView>(R.id.navigationview)

// Clear existing menu items

// Clear existing menu items
        navigationView.menu.clear()

// Create a custom view for each item
        // Replace '5' with the number of items you want

// Create a custom view for each item
        // Replace '5' with the number of items you want
        val customItemView: View =
            layoutInflater.inflate(R.layout.navigationlayout, navigationView, false)

        // Find the view with the ID "about" and set a click listener for it

        // Find the view with the ID "about" and set a click listener for it
//        val aboutItemView = customItemView.findViewById<View>(R.id.Home)
//        aboutItemView?.setOnClickListener {
//            // Handle the click event for the "About" item
////            val intent: Intent = Intent(
////                this@Homepage,
////                MainActivity::class.java
////            )
////            startActivity(intent)
//
//            showToast("Hello, Toast!")
//        }
        val settingsItemView = customItemView.findViewById<View>(R.id.shareapp)
        settingsItemView?.setOnClickListener { // Handle the click event for the "About" item
            showToast("Comming Soon")
//            val appLink = "This is AI content generator App download the App. This is Link"+" https://play.google.com/store/apps/details?id=com.contentbot.user"
//
//            // Create the Intent
//
//            // Create the Intent
//            val shareIntent = Intent(Intent.ACTION_SEND)
//            shareIntent.setType("text/plain")
//            shareIntent.putExtra(Intent.EXTRA_TEXT, appLink)
//
//            // Launch the Intent to share
//
//            // Launch the Intent to share
//            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
        val safetyItemView = customItemView.findViewById<View>(R.id.Privacypolicy)
        safetyItemView?.setOnClickListener { // Handle the click event for the "About" item
            // Handle the click event for the "Privacy Policy" item
//            val intent = Intent(this, Privacypolicy::class.java)
//            startActivity(intent)
        }
        val supportItemView = customItemView.findViewById<View>(R.id.rating)
        supportItemView?.setOnClickListener { // Handle the click event for the "About" item
//            val appPackageName = "com.contentbot.user"
//            try {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
//            } catch (e: android.content.ActivityNotFoundException) {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
//            }
            showToast("Comming Soon")
        }


        navigationView.addView(customItemView)

    }
    fun Context.showToast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}