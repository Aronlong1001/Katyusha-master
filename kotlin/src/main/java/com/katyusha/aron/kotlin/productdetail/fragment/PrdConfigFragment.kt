package com.katyusha.aron.kotlin.productdetail.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.FragmentItemConfigBinding

/**
 * Created by jixiaolong on 2018/1/2.
 */
class PrdConfigFragment: Fragment() {

    private lateinit var binding: FragmentItemConfigBinding
    private var url:String?=null
    private var from: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_item_config, null)
        binding = DataBindingUtil.bind(view)
        initData()
        initWebView()
        return view
    }

    private fun initData() {
        from = arguments.getInt("from")
        url = if (from == 1) {
            (this.parentFragment as ProductDetailFragment).getConfigUrl()
        }else {
            (this.parentFragment as ProductInfoFragment).getConfigUrl()
        }
    }

    private fun initWebView() {
        var webSetting = binding.configX5WebView.settings
        var userAgent = webSetting.userAgentString
        webSetting.userAgentString = userAgent + " benlai_Android/3.5.0"
        binding.configX5WebView.loadUrl(url)
    }
}