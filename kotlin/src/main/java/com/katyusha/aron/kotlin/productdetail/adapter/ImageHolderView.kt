package com.katyusha.aron.kotlin.productdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.library.utils.GlideLoader

/**
 * Created by jixiaolong on 2017/12/28.
 */
class ImageHolderView: Holder<String> {

    lateinit var image: ImageView
    override fun createView(context: Context?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_head_img, null)
        image = view.findViewById(R.id.iv_head)
        return view
    }

    override fun UpdateUI(context: Context?, position: Int, data: String?) {
        GlideLoader.loadImage(context,data,image)
    }

}