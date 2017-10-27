package com.katyusha.aron.goods;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.katyusha.aron.goods.databinding.ActivityGoodsBinding;
import com.katyusha.aron.library.constant.PagePath;
import com.katyusha.aron.library.utils.BLToast;
import com.katyusha.aron.library.utils.BenLaiToast;

@Route(path = PagePath.Goods, extras = 0)
public class GoodsActivity extends AppCompatActivity {

    private ActivityGoodsBinding binding;

    @Autowired
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_goods);
        ARouter.getInstance().inject(this);
//        getIntentData();
        init();
    }

    private void getIntentData() {
        url = getIntent().getStringExtra("url");
    }

    private void init() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(PagePath.Cart)
                        .withString("url", url)
                        .navigation();
                BLToast.makeText(GoodsActivity.this,"jump to cart", BenLaiToast.LENGTH_SHORT).show();
            }
        });

        WebSettings settings = binding.content.getSettings();
        settings.setJavaScriptEnabled(true);
        binding.content.setWebViewClient(new WebViewClient(){
            @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        binding.content.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.content.canGoBack()) {
            binding.content.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
