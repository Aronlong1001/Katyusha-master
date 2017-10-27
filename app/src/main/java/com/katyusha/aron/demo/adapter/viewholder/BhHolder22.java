package com.katyusha.aron.demo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.katyusha.aron.demo.adapter.BhListAdapter;
import com.katyusha.aron.demo.bean.HomeBean;
import com.katyusha.aron.demo.bean.HomeItemDetailBean;
import com.katyusha.aron.demo.databinding.Holder22LayoutBinding;

import java.util.ArrayList;

/**
 * Created by aron on 2017/6/15.
 */

public class BhHolder22 extends RecyclerView.ViewHolder {

    private Holder22LayoutBinding binding;
    private BhListAdapter adapter;

    public BhHolder22(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
//        int width=itemView.getResources().getDisplayMetrics().widthPixels;
//        int height=width*1/3;
//        binding.headerImg.setLayoutParams(new FrameLayout.LayoutParams(width,height));
        LinearLayoutManager manager = new LinearLayoutManager(binding.getRoot().getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.productList.setLayoutManager(manager);
        adapter = new BhListAdapter(binding.getRoot().getContext(), new ArrayList<HomeItemDetailBean>());
        binding.productList.setAdapter(adapter);
    }

    public void bind(HomeBean homeBean){
        if (homeBean != null) {
            binding.headerImg.setImageURI(Uri.parse(homeBean.getBanner().getImg()));
            adapter.setData(homeBean.getModelSEDetail());
        }

    }
}
