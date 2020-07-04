package com.example.diagonalscroll

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.GestureDetectorCompat
import java.util.*

class DiagonalScrollView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): FrameLayout(context, attrs, defStyle), GestureDetector.OnGestureListener {

    private var mHorizontalScrollView =
        MyHorizontalScrollView(context)
    private var mVerticalScrollView =
        MyVerticalScrollView(context)
    private val mGestureDetectorCompat: GestureDetectorCompat = GestureDetectorCompat(context, this)
    private val mListeners: MutableList<OnScrollListener> = ArrayList()

    fun attach(child: View): View {
        mHorizontalScrollView.addView(child)
        mVerticalScrollView.addView(mHorizontalScrollView)
        this.addView(mVerticalScrollView)
        return this
    }

    fun addOnScrollListener(listener: OnScrollListener) {
        mListeners.add(listener)
    }

    fun removeOnScrollListener(listener: OnScrollListener): Boolean {
        return mListeners.remove(listener)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        this.onTouch(event)
        this.performClick()
        return true
    }

    override fun onDown(e: MotionEvent): Boolean {
        // catch down action
        return false
    }

    override fun onShowPress(e: MotionEvent) {
        // do nothing here
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        // catch click action
        return false
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        // catch scroll action
        mVerticalScrollView.smoothScrollBy(distanceX.toInt(), distanceY.toInt())
        mHorizontalScrollView.smoothScrollBy(distanceX.toInt(), distanceY.toInt())
        mListeners.forEach { listener -> listener.onScrollChanged(distanceX, distanceY) }
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        // catch long click action, do nothing here
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        // catch fling action
        mVerticalScrollView.fling(-velocityY.toInt())
        mHorizontalScrollView.fling(-velocityX.toInt())
        return false
    }

    private fun onTouch(event: MotionEvent): Boolean {
        return mGestureDetectorCompat.onTouchEvent(event)
    }
}