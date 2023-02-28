package com.fakhril.ivy.onboarding.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.fakhril.ivy.R
import com.fakhril.ivy.onboarding.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.utils.Animatoo

private lateinit var mViewPager: ViewPager2
private lateinit var textSkip: TextView

class OnBoarding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        supportActionBar!!.hide()

        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = ViewPagerAdapter(this, this)

        TabLayoutMediator(findViewById(R.id.pageIndicator), mViewPager) { _, _ -> }.attach()
        textSkip = findViewById(R.id.text_skip)
        textSkip.setOnClickListener {
            finish()
            val intent =
                Intent(applicationContext, FinishOnBoarding::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        val btnNextStep: Button = findViewById(R.id.btn_next_step)

        btnNextStep.setOnClickListener {
            if (getItem() > mViewPager.childCount) {
                finish()
                val intent =
                    Intent(applicationContext, FinishOnBoarding::class.java)
                startActivity(intent)
                Animatoo.animateSlideLeft(this)
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }

    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }


}