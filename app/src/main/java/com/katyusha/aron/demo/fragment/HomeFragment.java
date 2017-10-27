package com.katyusha.aron.demo.fragment;

import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.databinding.ActivityMainBinding;

/**
 * Created by aron on 2017/7/13.
 */

public class HomeFragment extends BaseFragment<ActivityMainBinding> {
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(ActivityMainBinding binding) {
//        binding.blHomeContent
//        binding.blHomeRefresh
    }
}
