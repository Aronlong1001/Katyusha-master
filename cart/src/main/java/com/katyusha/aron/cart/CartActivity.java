package com.katyusha.aron.cart;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.katyusha.aron.cart.databinding.ActivityCartBinding;
import com.katyusha.aron.library.BaseApplication;
import com.katyusha.aron.library.constant.PagePath;
import com.katyusha.aron.library.utils.BLToast;
import com.katyusha.aron.library.utils.BenLaiToast;

@Route(path = PagePath.Cart)
public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;

    @Autowired
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
  //      BLToast.makeText(CartActivity.this,"this is a test toast!", BenLaiToast.LENGTH_SHORT).show();
        ARouter.getInstance().inject(this);
 //       getIntentData();
        init();
    }

    private void getIntentData() {
        url = getIntent().getStringExtra("url");
    }

    private void init() {
        binding.goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(PagePath.Main).navigation();
            }
        });
        binding.goGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(PagePath.Goods)
                        .withString("url", url)
                        .navigation();
            }
        });
        binding.goTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TipActivity.launch(CartActivity.this);
                ARouter.getInstance().build(PagePath.Tips).navigation(CartActivity.this, 1001);
                BLToast.makeText(BaseApplication.getAppContext(), "jump to tips", BLToast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1001:
                BLToast.makeText(CartActivity.this, "I come back!", BLToast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
