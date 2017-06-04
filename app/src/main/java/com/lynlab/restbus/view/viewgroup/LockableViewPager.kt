package com.lynlab.restbus.view.viewgroup

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * 스와이프 잠금이 가능한 커스텀 ViewPager
 */
class LockableViewPager(context: Context, attrs: AttributeSet?): ViewPager(context, attrs) {

    var locked = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (locked) {
            return false
        }

        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (locked) {
            return false
        }

        return super.onInterceptTouchEvent(ev)
    }

}