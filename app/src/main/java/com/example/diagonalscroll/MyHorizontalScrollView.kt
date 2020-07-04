package com.example.diagonalscroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView

internal class MyHorizontalScrollView : HorizontalScrollView {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        performClick()
        return false
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}