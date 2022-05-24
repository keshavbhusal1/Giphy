package com.keshav.giphy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keshav.giphy.fragment.one.OneFragment
import com.keshav.giphy.fragment.two.TwoFragment

private const val NUM_TABS = 2

 class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OneFragment.newInstance()
            1 -> TwoFragment.newInstance()
            else -> OneFragment.newInstance()
        }
    }
}