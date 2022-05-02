package com.reactnativenavigation.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentPlaceHolder, StartFragment(), StartFragment.TAG)
        ft.commit()
    }
}