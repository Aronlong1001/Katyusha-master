package com.katyusha.aron.demo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.katyusha.aron.demo.bean.HomeBean;
import com.katyusha.aron.demo.databinding.Holder3LayoutBinding;

/**
 * Created by aron on 2017/6/14.
 */

public class BhHolder3 extends RecyclerView.ViewHolder {

    private Holder3LayoutBinding binding;

    public BhHolder3(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        int width=itemView.getResources().getDisplayMetrics().widthPixels;
        int height=width*300/750;
        binding.singleImg.setLayoutParams(new FrameLayout.LayoutParams(width,height));
    }

    public void bind(HomeBean homeBean){
        if (homeBean != null){
            binding.singleImg.setImageURI(Uri.parse(homeBean.getList().get(0).getImg()));
        }
    }
}
