package com.katyusha.aron.library.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aron on 2017/8/1.
 */

public class BenLaiToast {

    private WindowManager manger;
    private Long time = Long.valueOf(2000L);
    private static View contentView;
    private WindowManager.LayoutParams params;
    private static Timer timer;
    private Toast toast;
    private static Toast oldToast;
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    private static Handler handler;
    private CharSequence text;

    public BenLaiToast(Context context, CharSequence text, int HIDE_DELAY) {
        manger = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        this.text = text;

        if(HIDE_DELAY == BenLaiToast.LENGTH_SHORT)
            this.time = 2000L;
        else if(HIDE_DELAY == BenLaiToast.LENGTH_LONG)
            this.time = 3500L;

        if(oldToast == null){
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            contentView = toast.getView();

            params = new WindowManager.LayoutParams();
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = -1;
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            params.setTitle("BenLaiToast");
            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            params.y = 200;
        }
        if(handler == null){
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    BenLaiToast.this.cancel();
                }
            };
        }
    }

    public static BenLaiToast makeText(Context context, String text, int HIDE_DELAY) {
        BenLaiToast toast = new BenLaiToast(context, text, HIDE_DELAY);
        return toast;
    }

    public static BenLaiToast makeText(Context context, int resId, int HIDE_DELAY) {
        return makeText(context, context.getText(resId).toString(), HIDE_DELAY);
    }

    public void show() {
        if(oldToast == null) {
            oldToast = this.toast;
            this.manger.addView(contentView, this.params);
            timer = new Timer();
        } else {
            timer.cancel();
            oldToast.setText(this.text);
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                BenLaiToast.handler.sendEmptyMessage(1);
            }
        }, this.time.longValue());
    }

    public void cancel() {
        try {
            this.manger.removeView(contentView);
        } catch (IllegalArgumentException var2) {
            ;
        }

        timer.cancel();
        oldToast.cancel();
        timer = null;
        this.toast = null;
        oldToast = null;
        contentView = null;
        handler = null;
    }

    public void setText(CharSequence s) {
        this.toast.setText(s);
    }
}
