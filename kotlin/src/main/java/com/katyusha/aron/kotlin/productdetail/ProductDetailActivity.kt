package com.katyusha.aron.kotlin.productdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.katyusha.aron.kotlin.R

class ProductDetailActivity : AppCompatActivity() {

    private var sysNo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        initData()
        initView()
        initRequest()
    }

    private fun initData() {
        sysNo = intent.getStringExtra("sysNo")
    }

    private fun initRequest() {
        val prdDetailVM = ProductDetailVM(this, sysNo)
        prdDetailVM.requestProductDetailData()
    }

    private fun initView() {

    }

}
