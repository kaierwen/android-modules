package com.github.kaierwen.androiddevlibrary.openlibrary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.github.kaierwen.androiddevlibrary.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        textView.setText("Hello, world!")
        button.setText("Kotlin Test")
        button2.setText("Button 2")
    }

    override fun onResume() {
        Logger.d("onResume")
        super.onResume()
    }

    override fun onPause() {
        Logger.d("onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Logger.d("onDestroy")
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getTest() {

    }
}
