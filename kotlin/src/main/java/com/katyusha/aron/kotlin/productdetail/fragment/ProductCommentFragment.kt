package com.katyusha.aron.kotlin.productdetail.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katyusha.aron.kotlin.R

/**
 * Created by jixiaolong on 2017/12/21.
 */
class ProductCommentFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_product_comment, null)
        return view
    }
}