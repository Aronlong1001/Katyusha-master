package com.katyusha.aron.library.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Created by aron on 2017/4/24.
 */

public class ScrollSpeedUtils {
    //private static ScrollSpeed mScroller = null;
    public static void controlViewPagerSpeed(ViewPager viewpager){
        if(viewpager!=null)
            controlViewPagerSpeed(viewpager.getContext(),viewpager,500);
    }
    /**
     * 设置ViewPager的滑动时间
     * @param context
     * @param viewpager ViewPager控件
     * @param DurationSwitch 滑动延时
     */
    public static void controlViewPagerSpeed(Context context, ViewPager viewpager, int DurationSwitch) {
        try {
            Field mField;

            mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);

            ScrollSpeed mScroller = new ScrollSpeed(context,
                    new AccelerateInterpolator());
            mScroller.setmDuration(DurationSwitch);
            mField.set(viewpager, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
