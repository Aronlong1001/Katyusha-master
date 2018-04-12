package com.katyusha.aron.library.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

/**
 * Created by jixiaolong on 2018/1/9.
 */

public class NoTouchRecycleView extends RecyclerView {
    public NoTouchRecycleView(Context context) {
        super(context);
    }

    public NoTouchRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ViewParent parent =this;
        while(!((parent = parent.getParent()) instanceof ViewPager)){
            // 循环查找viewPager
            parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }
}
