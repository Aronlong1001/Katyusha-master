package com.katyusha.aron.demo.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.bean.HomeItemBean;
import com.katyusha.aron.demo.databinding.ItemBhGridDraweeBinding;
import com.katyusha.aron.demo.event.BhClickEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by aron on 2017/6/14.
 */

public class BhGridAdapter extends RecyclerView.Adapter<BhGridAdapter.BhItemHoder23> {

    private WeakReference<Context> mCxt;
    private List<HomeItemBean> data;

    public BhGridAdapter(Context cxt, List<HomeItemBean> data) {
        this.mCxt = new WeakReference<Context>(cxt);
        this.data = data;
    }

    public void setData(List<HomeItemBean> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public BhItemHoder23 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BhItemHoder23(LayoutInflater.from(mCxt.get())
                .inflate(R.layout.item_bh_grid_drawee, parent, false));
    }

    @Override
    public void onBindViewHolder(BhItemHoder23 holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public class BhItemHoder23 extends RecyclerView.ViewHolder {
        private ItemBhGridDraweeBinding binding;

        public BhItemHoder23(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bind(final HomeItemBean homeItemBean){
//            binding.blhImFastIcon.setImageURI(Uri.parse(homeItemBean.getImg()));
            binding.setItem(homeItemBean);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BhClickEvent event = new BhClickEvent(homeItemBean.getValue());
                    EventBus.getDefault().post(event);
                }
            });
        }
    }

    //该方法必须是静态的
    @BindingAdapter("goodsImgUrl")
    public static void loadImage(SimpleDraweeView view, String url) {
        try {
            if (!TextUtils.isEmpty(url))
                view.setImageURI(Uri.parse(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
