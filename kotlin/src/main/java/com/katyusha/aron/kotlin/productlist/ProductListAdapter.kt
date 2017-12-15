package com.katyusha.aron.kotlin.productlist

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.ItemPrdListBinding

/**
 * Created by jixiaolong on 2017/12/14.
 */
class ProductListAdapter(context: Context, dataList: ArrayList<ProductListResponse.ProductListBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listData : ArrayList<ProductListResponse.ProductListBean> =dataList
    private val mContext : Context =context

    override fun getItemCount(): Int {
        return  listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ProductListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_prd_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val mHolder = holder as ProductListHolder
        mHolder.bind(listData[position])
    }

    class ProductListHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val binding : ItemPrdListBinding = DataBindingUtil.bind(itemView)

        fun bind(productListBean: ProductListResponse.ProductListBean) {
            binding.product = productListBean
            val textPaint = binding.tvProductPriceOrig.paint
            textPaint.flags = Paint.STRIKE_THRU_TEXT_FLAG or textPaint.flags
        }
    }
    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(imageView: ImageView, url : String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

}