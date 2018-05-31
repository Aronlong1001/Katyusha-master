package com.katyusha.aron.kotlin.productdetail.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.FragmentItemDetailWebBinding

/**
 * Created by jixiaolong on 2018/1/2.
 */
class PrdChildWebFragment:Fragment() {

    private lateinit var binding:FragmentItemDetailWebBinding
    private var url:String?=null
    private var from: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_item_detail_web, null)
        binding = DataBindingUtil.bind(view)!!
        initData()
        initWebView()
        return view
    }

    private fun initData() {
        if (arguments != null) {
            from = arguments!!.getInt("from")
            url = if (from == 1) {
                (this.parentFragment as ProductDetailFragment).getDetailUrl()
            } else {
                (this.parentFragment as ProductInfoFragment).getDetailUrl()
            }
        }
    }

    private fun initWebView() {
        var webSetting = binding.x5WebView.settings
        var userAgent = webSetting.userAgentString
        webSetting.userAgentString = userAgent + " benlai_Android/3.5.0"
        binding.x5WebView.loadUrl(url)
    }
}