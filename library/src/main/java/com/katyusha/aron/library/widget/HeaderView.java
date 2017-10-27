package com.katyusha.aron.library.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.andview.refreshview.callback.IHeaderCallBack;
import com.katyusha.aron.library.R;

/**
 * Created by aron on 2017/10/24.
 */

public class HeaderView extends LinearLayout implements IHeaderCallBack {

    private final static int HEIGHT = 50;//头部总高度
    private int width;
    private int height;
    private int moveY = 0;
    private boolean finished = false;
    private boolean refreshing = false;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ProgressBar progressBar;

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.header_layout, this);
        progressBar = (ProgressBar) view.findViewById(R.id.loading_bar);
    }

    @Override
    public void onStateNormal() {
        finished = false;
    }

    @Override
    public void onStateReady() {

    }

    @Override
    public void onStateRefreshing() {
        refreshing = true;
        start();
    }

    private void start() {
        if (!refreshing) {
            return;
        }
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onStateFinish(boolean success) {
        refreshing = false;
        finished = true;
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {

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
