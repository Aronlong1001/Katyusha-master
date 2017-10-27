package com.katyusha.aron.demo.adapter;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.bean.HomeItemBean;
import com.katyusha.aron.demo.databinding.ItemBhBannerDraweeBinding;
import com.katyusha.aron.library.constant.Constant;
import com.katyusha.aron.library.utils.Pool;


import java.util.List;

/**
 * Created by aron on 2017/6/14.
 */

public class BhBannerAdapter extends PagerAdapter {

    private final Pool<ItemBhBannerDraweeBinding, ViewGroup> pool;
    private List<HomeItemBean> data;

    public BhBannerAdapter(List<HomeItemBean> data) {
        this.data = data;
        this.pool = new Pool<ItemBhBannerDraweeBinding, ViewGroup>() {
            @Override
            protected ItemBhBannerDraweeBinding newInstance(ViewGroup viewGroup) {
                return DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_bh_banner_drawee,viewGroup,false);
            }
        };
    }

    public void setData(List<HomeItemBean> data){
        this.data = data;
        notifyDataSetChanged();
    }
    public int getIndex(int position){
        return position % data.size();
    }

    @Override
    public int getCount() {
        int size = getReallySize();
        return size>1 ? size* Constant.MULTIPLE_OF_SIZE : size;
    }
    private int getReallySize(){
        return data==null?0:data.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ItemBhBannerDraweeBinding binding = pool.get(container);
        final HomeItemBean unit = data.get(getIndex(position));
        if(!TextUtils.isEmpty(unit.getImg())) {
            binding.blBannerDraweeview.setImageURI(Uri.parse(unit.getImg()));
        }
//        binding.blBannerDraweeview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                BHResItemClickEvent event = new BHResItemClickEvent(unit);
//                EventBus.getDefault().post(event);
//            }
//        });
        View child = binding.getRoot();
        container.addView(child);
        child.setTag(binding);
        return child;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Object tag = ((View) object).getTag();
        if (tag != null && tag instanceof ItemBhBannerDraweeBinding) {
            pool.put((ItemBhBannerDraweeBinding) tag);
        }
        container.removeView((View) object);
    }
}
