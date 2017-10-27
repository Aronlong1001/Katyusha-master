package com.katyusha.aron.demo.adapter;


import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.katyusha.aron.library.constant.Constant;

import java.lang.ref.WeakReference;

/**
 * @author aron  on 2017/3/30.
 */

public  final class BhBannerHandler extends Handler {

    private final static int DELY_TIME = 3500;//3.5s
    private final static int MSG_PLAY = -900002;
    private final static int LAST_DELAY = -900003;

    private WeakReference<ViewPager> mPager;
    private WeakReference<Runnable> mRun;
    private WeakReference<Runnable> lastRun;

    private int mDelay;

    public BhBannerHandler(ViewPager viewPager) {
        this(viewPager,DELY_TIME);
    }

    public BhBannerHandler(ViewPager viewPager, int delayMillis) {
        mDelay = delayMillis;
        mPager = new WeakReference<ViewPager>(viewPager);
        mRun = new WeakReference<Runnable>(new Runnable() {
            @Override
            public void run() {
                obtainMessage(MSG_PLAY).sendToTarget();
            }
        });
        lastRun =new WeakReference<Runnable>(new Runnable() {
            @Override
            public void run() {
                obtainMessage(LAST_DELAY).sendToTarget();
            }
        });
        postDelayed(mRun.get(), mDelay);
    }

    @Override
    public void handleMessage(Message msg) {
        ViewPager viewPager = mPager.get();
        switch (msg.what){
            case MSG_PLAY:
                if(null != viewPager){
                    int index = viewPager.getCurrentItem()+1;
                    viewPager.setCurrentItem(index,true);
                }
                break;
            case LAST_DELAY:
                if (viewPager!=null) {
                    int count = viewPager.getAdapter().getCount();
                    int even = Constant.MULTIPLE_OF_SIZE % 2;
                    int half = 0;
                    if (even != 0) {
                        half = (Constant.MULTIPLE_OF_SIZE + 1) / 2;
                    } else {
                        half = Constant.MULTIPLE_OF_SIZE / 2;
                    }
                    int index = (count / Constant.MULTIPLE_OF_SIZE) * half - 1;
                    viewPager.setCurrentItem(index, false);
                }
                break;
        }
    }

    public void removeBannerDelay(){
        if(mRun.get()!=null) {
            removeCallbacks(mRun.get());
            postDelayed(mRun.get(), mDelay);
        }
        if (lastRun.get()!=null)
            removeCallbacks(lastRun.get());
        ViewPager vp = mPager.get();
        if (vp!=null){
            int currentItem = vp.getCurrentItem();
            int count = vp.getAdapter().getCount();
            if (currentItem==count-1){
                if (lastRun.get()==null){
                    lastRun=new WeakReference<Runnable>(new Runnable() {
                        @Override
                        public void run() {
                            obtainMessage(LAST_DELAY).sendToTarget();
                        }
                    });
                }
                postDelayed(lastRun.get(),500);
            }
        }
    }
}
