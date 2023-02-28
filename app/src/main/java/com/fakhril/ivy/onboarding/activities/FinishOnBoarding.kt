package com.fakhril.ivy.onboarding.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.fakhril.ivy.MainActivity
import com.fakhril.ivy.R

class FinishOnBoarding : AppCompatActivity() {
    private lateinit var btnStart: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_on_boarding)

        supportActionBar!!.hide()

        btnStart = findViewById(R.id.layout_start)
        btnStart.setOnClickListener {
            onBoardingFinished()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun onBoardingFinished() {
        val sharedPref = applicationContext.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}