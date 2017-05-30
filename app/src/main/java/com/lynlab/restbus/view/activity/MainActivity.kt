package com.lynlab.restbus.view.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.lynlab.restbus.R
import com.lynlab.restbus.view.adapter.MainViewPagerAdapter
import com.lynlab.restbus.view.fragment.SearchRouteFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViewPager()
    }

    /**
     * 서버에서 헬로 월드 메시지를 불러와 화면상에 띄운다.
     */
    private fun setViewPager() {
        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addItem(SearchRouteFragment())

        val viewPager = findViewById(R.id.activity_main_viewpager) as ViewPager
        viewPager.adapter = adapter
    }

}
