package com.katyusha.aron.demo.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.bean.HomeItemDetailBean;
import com.katyusha.aron.demo.databinding.ItemBhListBinding;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by aron on 2017/6/15.
 */

public class BhListAdapter extends RecyclerView.Adapter<BhListAdapter.BhItemHolder22>{

    private WeakReference<Context> mCtx;
    private List<HomeItemDetailBean> data;

    public BhListAdapter(Context mCtx, List<HomeItemDetailBean> data) {
        this.mCtx = new WeakReference<Context>(mCtx);
        this.data = data;
    }

    public void setData(List<HomeItemDetailBean> homeItemDetailBeen){
        this.data = homeItemDetailBeen;
        notifyDataSetChanged();
    }
    @Override
    public BhItemHolder22 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BhItemHolder22(LayoutInflater.from(mCtx.get())
                .inflate(R.layout.item_bh_list, parent, false));
    }

    @Override
    public void onBindViewHolder(BhItemHolder22 holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class BhItemHolder22 extends RecyclerView.ViewHolder{

        private ItemBhListBinding binding;

        public BhItemHolder22(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bind(HomeItemDetailBean data){
            binding.setProduct(data.getProduct());
            TextPaint paint = binding.origPrice.getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG | paint.getFlags());
        }
    }
    //该方法必须是静态的
    @BindingAdapter("productImgUrl")
    public static void loadImage(SimpleDraweeView view, String url) {
        try {
            if (!TextUtils.isEmpty(url))
                view.setImageURI(Uri.parse(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
