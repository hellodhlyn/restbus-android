package com.lynlab.restbus.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private var fragments: List<Fragment> = ArrayList()

    fun addItem(fragment: Fragment) {
        fragments += fragment
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}