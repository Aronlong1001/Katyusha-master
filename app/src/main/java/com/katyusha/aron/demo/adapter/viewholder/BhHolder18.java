package com.katyusha.aron.demo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.katyusha.aron.demo.bean.HomeBean;
import com.katyusha.aron.demo.databinding.Holder18LayoutBinding;

/**
 * Created by aron on 2017/7/10.
 */

public class BhHolder18 extends RecyclerView.ViewHolder {

    private Holder18LayoutBinding binding;

    public BhHolder18(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        int width=itemView.getResources().getDisplayMetrics().widthPixels;
        int height=width*300/750;
        binding.holder18SingleImg.setLayoutParams(new FrameLayout.LayoutParams(width,height));
    }

    public void bind(HomeBean homeBean){
        if (homeBean != null){
            binding.holder18SingleImg.setImageURI(Uri.parse(homeBean.getList().get(0).getImg()));
        }
    }
}
