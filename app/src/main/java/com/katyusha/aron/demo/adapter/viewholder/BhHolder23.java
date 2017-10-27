package com.katyusha.aron.demo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.katyusha.aron.demo.adapter.BhGridAdapter;
import com.katyusha.aron.demo.bean.HomeBean;
import com.katyusha.aron.demo.bean.HomeItemBean;
import com.katyusha.aron.demo.databinding.Holder23LayoutBinding;

import java.util.ArrayList;

/**
 * Created by aron on 2017/6/14.
 */

public class BhHolder23 extends RecyclerView.ViewHolder{

    private Holder23LayoutBinding binding;
    private BhGridAdapter adapter;

    public BhHolder23(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        GridLayoutManager manager = new GridLayoutManager(binding.getRoot().getContext(), 4);
        binding.gridView.setLayoutManager(manager);
        adapter = new BhGridAdapter(binding.getRoot().getContext(), new ArrayList<HomeItemBean>());
        binding.gridView.setAdapter(adapter);
    }
    public void bind(HomeBean homeBean){
        if (homeBean != null){
            adapter.setData(homeBean.getList());
        }
    }
}
