package com.katyusha.aron.kotlin.practice

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.ItemBizListBinding

/**
 * Created by jixiaolong on 2018/2/7.
 */
class BizListAdapter(var context: Context, private var dataList: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var viewHolder = holder as BizListHolder
        viewHolder.bind(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_biz_list, parent, false)
        return BizListHolder(view)
    }

    class BizListHolder(view: View): RecyclerView.ViewHolder(view) {
        var binding : ItemBizListBinding = DataBindingUtil.bind(view)

        fun bind(data : String) {
            binding.tvBiz.text = data
        }
    }

}