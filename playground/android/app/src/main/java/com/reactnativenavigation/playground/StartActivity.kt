package com.reactnativenavigation.playground

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val button = findViewById<Button>(R.id.startButton)
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}