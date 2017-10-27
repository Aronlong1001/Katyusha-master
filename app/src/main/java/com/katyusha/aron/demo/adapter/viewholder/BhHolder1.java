package com.katyusha.aron.demo.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.katyusha.aron.demo.adapter.BhBannerAdapter;
import com.katyusha.aron.demo.adapter.BhBannerHandler;
import com.katyusha.aron.demo.bean.HomeBean;
import com.katyusha.aron.demo.bean.HomeItemBean;
import com.katyusha.aron.demo.databinding.Holder1LayoutBinding;
import com.katyusha.aron.demo.widget.BhCircleNavigator;
import com.katyusha.aron.library.utils.ScrollSpeedUtils;

import java.util.ArrayList;

/**
 * Created by aron on 2017/6/14.
 */

public class BhHolder1 extends RecyclerView.ViewHolder{

    private Holder1LayoutBinding binding;
    private BhBannerAdapter adapter;
    private BhBannerHandler mHanlder;

    public BhHolder1(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        int width=itemView.getResources().getDisplayMetrics().widthPixels;
        int height=width*300/750;
        binding.blHomeBannerContent.setLayoutParams(new FrameLayout.LayoutParams(width,height));
        adapter = new BhBannerAdapter(new ArrayList<HomeItemBean>());
        ScrollSpeedUtils.controlViewPagerSpeed(binding.blHomeBannerContent);
        binding.blHomeBannerContent.setAdapter(adapter);
    }

    public void bind(HomeBean homeBean){
        adapter.setData(homeBean.getList());
        int size = homeBean.getList() == null ? 0 : homeBean.getList().size();
        if (size >1 ) {
            BhCircleNavigator circleNavigator = new BhCircleNavigator(binding.getRoot().getContext());
            circleNavigator.setCircleCount(size);
            binding.blHomeBannerDot.setVisibility(View.VISIBLE);
            binding.blHomeBannerDot.setNavigator(circleNavigator);
            if (null == mHanlder)
                mHanlder = new BhBannerHandler(binding.blHomeBannerContent);
            binding.blHomeBannerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    binding.blHomeBannerDot.onPageScrolled(adapter.getIndex(position), positionOffset, positionOffsetPixels);
                }

                public void onPageSelected(int position) {
                    binding.blHomeBannerDot.onPageSelected(adapter.getIndex(position));
                    mHanlder.removeBannerDelay();
                }

                public void onPageScrollStateChanged(int state) {
                    binding.blHomeBannerDot.onPageScrollStateChanged(state);
                }
            });

            int index = adapter.getCount() / 2 - adapter.getCount() / 2 % size;
            binding.blHomeBannerContent.setCurrentItem(index);
        }else {
            binding.blHomeBannerDot.setVisibility(View.GONE);
        }

    }
}
