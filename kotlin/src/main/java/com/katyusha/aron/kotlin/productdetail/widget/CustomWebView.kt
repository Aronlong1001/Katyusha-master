package com.katyusha.aron.kotlin.productdetail.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.katyusha.aron.library.x5.X5WebView

/**
 * Created by jixiaolong on 2018/1/10.
 */
class CustomWebView(context: Context, attrs: AttributeSet): X5WebView(context, attrs) {
    private var oldY: Float = 0.0f
    var t: Int = 0
    private var oldX: Float = 0.0f

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when {
            ev.action == MotionEvent.ACTION_MOVE -> {
                var y = ev.y
                var Ys = y - oldY
                if (Ys > 0 && t == 0) {
                    parent.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            ev.action == MotionEvent.ACTION_DOWN -> {
                parent.parent.requestDisallowInterceptTouchEvent(true)
                oldY = ev.y
                oldX = ev.x
            }
            ev.action == MotionEvent.ACTION_UP -> {
                parent.parent.requestDisallowInterceptTouchEvent(true)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        this.t = t
        super.onScrollChanged(l, t, oldl, oldt)
    }
}