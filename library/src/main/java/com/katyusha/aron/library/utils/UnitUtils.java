package com.katyusha.aron.library.utils;

/**
 * Created by user on 2017/6/13.
 */

import android.content.res.Resources;
import android.util.DisplayMetrics;

/***
 * @类名：UnitUtils
 * @功能说明：单位转为工具类
 * @作者：aron
 * @创建时间：2015-10-8
 * @修改内容：
 * @修改人：
 * @修改时间：
 */
public class UnitUtils {

    /***
     * dip或dp:设备独立像素，与设备屏幕有关
     * px:像素 ,就是屏幕上实际的像素点单位
     * sp:类似dp,主要处理文字大小
     * dpi:屏幕像素密度，每英寸多少像素
     */

    private static DisplayMetrics displayMetrics = null;

    static {
        displayMetrics = Resources.getSystem().getDisplayMetrics();
    }

    /***
     * dip(dp)转为px
     * @param value
     * @return
     */
    public static int dip2px(float value) {
        final float scale = displayMetrics.density;
        return (int) (value * scale + 0.5f);
    }

    /***
     * px转为dip(dp)
     * @param value
     * @return
     */
    public static int px2dip(float value) {
        final float scale = displayMetrics.density;
        return (int) (value / scale + 0.5f);
    }

    /**
     * sp转px
     * @param value
     * @return
     */
    public static int sp2px(float value) {
        float fontScale = displayMetrics.scaledDensity;
        return (int) (value * fontScale + 0.5f);
    }

    /**
     * px转sp
     * @param value
     * @return
     */
    public static int px2sp(float value) {
        float fontScale = displayMetrics.scaledDensity;
        return (int) (value / fontScale + 0.5f);
    }
}