package com.example.locationuzbekistan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passregistr.Utils.Back.isHome

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        isHome = false
    }
    override fun onBackPressed() {
        if (isHome) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}