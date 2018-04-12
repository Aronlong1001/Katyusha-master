package com.katyusha.aron.kotlin.productdetail.adapter

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.ItemRecommendPrdBinding
import com.katyusha.aron.kotlin.productdetail.model.RecommendPrdResponse
import com.katyusha.aron.library.utils.GlideLoader

/**
 * Created by jixiaolong on 2018/1/11.
 */
class RecommendAdapter(var context: Context, private var dataList: ArrayList<RecommendPrdResponse.RecommendPrdBean>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_recommend_prd, parent, false)
        return RecommendHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var mHolder = holder as RecommendHolder
        mHolder.bind(dataList[position])
    }

    class RecommendHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding: ItemRecommendPrdBinding = DataBindingUtil.bind(itemView)
        fun bind(bean: RecommendPrdResponse.RecommendPrdBean) {
            binding.product = bean
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:recdImageUrl")
        fun loadRecdImage(imageView: ImageView, url : String) {
            GlideLoader.loadImage(imageView.context,url,imageView)
        }
    }
}