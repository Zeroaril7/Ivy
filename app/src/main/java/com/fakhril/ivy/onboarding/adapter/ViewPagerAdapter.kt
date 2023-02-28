package com.fakhril.ivy.onboarding.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fakhril.ivy.R
import com.fakhril.ivy.onboarding.fragment.FirstBoarding

class ViewPagerAdapter (fragmentActivity: FragmentActivity,
                        private val context: Context
) :
FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstBoarding.newInstance(
                context.resources.getString(R.string.title_onboarding),
                context.resources.getString(R.string.description_onboarding),
                R.drawable.ivy_logo
            )
            1 -> FirstBoarding.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.drawable.on_board_place
            )
            else -> FirstBoarding.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.drawable.on_board_item
            )
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}