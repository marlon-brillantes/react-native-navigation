package com.reactnativenavigation.playground

import com.reactnativenavigation.NavigationActivity
import android.os.Bundle
import android.widget.ImageView
import com.reactnativenavigation.playground.R
import com.reactnativenavigation.react.ReactGateway

class MainActivity : NavigationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashLayout()
    }

    private fun setSplashLayout() {
        val img = ImageView(this)
        img.setImageDrawable(getDrawable(R.drawable.ic_android))
        setContentView(img)
    }

    override fun getReactGateway(): ReactGateway {
        return (application as MainApplication).reactGateway
    }
}