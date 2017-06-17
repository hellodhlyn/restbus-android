package com.lynlab.restbus.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lynlab.restbus.R
import com.lynlab.restbus.view.adapter.MainViewPagerAdapter
import com.lynlab.restbus.view.fragment.ArrivalFragment
import com.lynlab.restbus.view.fragment.SearchRouteFragment
import com.lynlab.restbus.view.fragment.StationListFragment
import com.lynlab.restbus.view.viewgroup.LockableViewPager
import io.reactivex.subjects.PublishSubject

class MainActivity : AppCompatActivity() {

    var viewPager: LockableViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViewPager()
    }

    /**
     * 서버에서 헬로 월드 메시지를 불러와 화면상에 띄운다.
     */
    private fun setViewPager() {
        viewPager = findViewById(R.id.activity_main_viewpager) as LockableViewPager
        val pageSubject : PublishSubject<Any?> = PublishSubject.create()
        pageSubject.subscribe({ viewPager!!.currentItem = viewPager!!.currentItem + 1 })

        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addItem(SearchRouteFragment(pageSubject))
        adapter.addItem(StationListFragment(pageSubject))
        adapter.addItem(ArrivalFragment(pageSubject))

        viewPager!!.locked = true
        viewPager!!.adapter = adapter
    }

    override fun onBackPressed() {
        if (viewPager != null && viewPager!!.currentItem > 0) {
            viewPager!!.currentItem = viewPager!!.currentItem - 1
        } else {
            super.onBackPressed()
        }
    }

}
