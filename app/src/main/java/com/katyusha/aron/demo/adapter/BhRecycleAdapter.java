package com.katyusha.aron.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katyusha.aron.demo.R;
import com.katyusha.aron.demo.adapter.viewholder.BhHolder1;
import com.katyusha.aron.demo.adapter.viewholder.BhHolder18;
import com.katyusha.aron.demo.adapter.viewholder.BhHolder22;
import com.katyusha.aron.demo.adapter.viewholder.BhHolder23;
import com.katyusha.aron.demo.adapter.viewholder.BhHolder3;
import com.katyusha.aron.demo.bean.HomeBean;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by aron on 2017/6/13.
 */

public class BhRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private WeakReference<Context> mCtx;
    private List<HomeBean> floors;

    private static final int TYPE_1 = 1;
    private static final int TYPE_3 = 3;
    private static final int TYPE_23 = 23;
    private static final int TYPE_22 = 22;
    private static final int TYPE_18 = 18;

    public BhRecycleAdapter(Context ctx, List<HomeBean> floors) {
        this.mCtx = new WeakReference<Context>(ctx);
        this.floors = floors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new BhHolder1(getInflate(parent, R.layout.holder1_layout));
            case TYPE_23:
                return new BhHolder23(getInflate(parent, R.layout.holder23_layout));
            case TYPE_3:
                return new BhHolder3(getInflate(parent, R.layout.holder3_layout));
            case TYPE_22:
                return new BhHolder22(getInflate(parent, R.layout.holder22_layout));
            case TYPE_18:
                return new BhHolder18(getInflate(parent, R.layout.holder18_layout));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (floors == null || position > floors.size())
            return;
        HomeBean floor = floors.get(position);
        if (holder instanceof BhHolder1){
            ((BhHolder1) holder).bind(floor);
        }
        if (holder instanceof BhHolder23){
            ((BhHolder23) holder).bind(floor);
        }
        if (holder instanceof BhHolder3){
            ((BhHolder3) holder).bind(floor);
        }
        if (holder instanceof BhHolder22){
            ((BhHolder22) holder).bind(floor);
        }
        if (holder instanceof BhHolder18){
            ((BhHolder18) holder).bind(floor);
        }
    }

    private View getInflate(ViewGroup parent, int item_layout) {
        return LayoutInflater.from(mCtx.get()).inflate(item_layout, parent, false);
    }

    @Override
    public int getItemCount() {
        if (floors == null){
            return 0;
        }
        return floors.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (floors == null || position > floors.size())
            return super.getItemViewType(position);
        return floors.get(position).getLotType();
    }
}
