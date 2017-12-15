package com.katyusha.aron.library.basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.katyusha.aron.library.model.BaseVM;
import com.katyusha.aron.library.utils.BLToast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by aron on 2017/6/13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static EventBus eventBus;
    public BaseVM viewModel;

    protected abstract void init();
    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        initView();
        init();
    }

    public void showShortText(String text){
        BLToast.makeText(BaseActivity.this, text, BLToast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
        if (viewModel !=null) {
            viewModel.getCompositeSubscription().unsubscribe();
        }
    }
}
