package com.katyusha.aron.cart;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.katyusha.aron.cart.databinding.ActivityTipBinding;
import com.katyusha.aron.library.constant.PagePath;
import com.sina.weibo.sdk.share.WbShareHandler;

/**
 * Created by aron on 2017/7/18.
 */

@Route(path = PagePath.Tips, extras = 0)
public class TipActivity extends AppCompatActivity {

    private ActivityTipBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip);
        init();
    }

    private void init() {
        binding.cartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(666);
                finish();
            }
        });
        binding.weiboShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WbShareHandler handler = new WbShareHandler(TipActivity.this);
                handler.registerApp();
            }
        });
    }

    public static void launch(Activity c){
        ARouter.getInstance().build(PagePath.Tips).navigation(c);
    }
}
