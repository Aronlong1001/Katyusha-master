package com.katyusha.aron.demo.widget;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.andview.refreshview.callback.IHeaderCallBack;
import com.katyusha.aron.demo.R;
import com.katyusha.aron.library.utils.UnitUtils;

public class BlPullHeader extends RelativeLayout implements Animator.AnimatorListener,IHeaderCallBack {
    private AnimatorSet setStart;
    private View blue,orange;
    private boolean isRunning;
    private final static int LENGTH = 100;//轨道长度
    private final static int WIDTH = 15;//圆宽度
    private final static int HEIGHT = 50;//头部总高度
    private final static int DURATION = 1000;//周期

    private int width;
    private int height;
    private int type = 0;
    private int moveY = 0;

    public BlPullHeader(Context context) {
        super(context);
        init();
    }

    public BlPullHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlPullHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BlPullHeader enableWebView(){
        type = 1;
        return this;
    }

    private int dp2px(int dp){
        return  (int)(getResources().getDisplayMetrics().density*dp+0.5);
    }

    private void init() {
        height = UnitUtils.dip2px(HEIGHT);
        width = UnitUtils.dip2px(WIDTH);
        LayoutInflater.from(getContext()).inflate(R.layout.bh_pull_header, this);
        blue = findViewById(R.id.pull_blue);
        orange = findViewById(R.id.pull_orange);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int padding = (screenWidth - dp2px(LENGTH))/2;
        int lenth = screenWidth - padding;
        ObjectAnimator play = ObjectAnimator.ofFloat(blue,"X",lenth,padding,lenth);
        ObjectAnimator with = ObjectAnimator.ofFloat(orange,"X",padding,lenth,padding);
        play.setDuration(DURATION);
        play.setRepeatCount(-1);
        with.setDuration(DURATION);
        with.setRepeatCount(-1);
        with.addListener(this);
        play.setInterpolator(new DecelerateInterpolator());
        with.setInterpolator(new DecelerateInterpolator());
        setStart = new AnimatorSet();
        setStart.play(play).with(with);
    }

    public void start() {
        setStart.start();
    }

    public void end() {
        setStart.end();
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        //Bhlog.e("xx","-----------------ending=");
        if(isRunning) {
            blue.setX(blue.getLeft());
            orange.setX(orange.getLeft());
            isRunning = false;
            moveEvent(0);
        }
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onStateNormal() {
        //Bhlog.e("xx","-----------------normal");
    }

    @Override
    public void onStateReady() {
        //Bhlog.e("xx","-----------------ready");
    }

    @Override
    public void onStateRefreshing() {//
        if(moveY <= 0){
            moveEvent(height);
        }
        isRunning = true;
        start();
        //Bhlog.e("xx","-----------------start");
    }

    @Override
    public void onStateFinish(boolean success) {//
        //Bhlog.e("xx","-----------------finish=");
        if(1 == type){
            setStart.end();
        }
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {//
        //Bhlog.e("xx","-----------------move="+offsetY);
        if(offsetY < height){
            moveEvent(offsetY);
        }
    }

    private void moveEvent(int y){//水平和垂直移动
        if(isRunning){
            if(0 == y){
                setStart.end();
            }
            return;
        }
        moveY = y;
        LayoutParams param1 = (LayoutParams)blue.getLayoutParams();
        LayoutParams param2 = (LayoutParams)orange.getLayoutParams();
        int size = y>width ? width:y;
        int top = height - y;
        int pad = y-width;
        if(pad>0){
            top += pad/2;
        }
        int margin = (y-width)*LENGTH/(HEIGHT-WIDTH);
        if(margin<0){
            margin = 0;
        }
        param1.height = size;
        param1.width = size;
        param1.topMargin = top;
        param1.rightMargin = margin;
        blue.setLayoutParams(param1);

        param2.height = size;
        param2.width = size;
        param2.topMargin = top;
        param2.leftMargin = margin;
        orange.setLayoutParams(param2);
    }

    @Override
    public void setRefreshTime(long lastRefreshTime) {

    }

    @Override
    public void hide() {
        setVisibility(GONE);
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}