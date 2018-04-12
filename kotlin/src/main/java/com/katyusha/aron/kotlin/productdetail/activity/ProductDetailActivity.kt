package com.katyusha.aron.kotlin.productdetail.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.ActivityProductDetailBinding
import com.katyusha.aron.kotlin.productdetail.adapter.ItemTitlePageAdapter
import com.katyusha.aron.kotlin.productdetail.fragment.ProductCommentFragment
import com.katyusha.aron.kotlin.productdetail.fragment.ProductDetailFragment
import com.katyusha.aron.kotlin.productdetail.fragment.ProductInfoFragment
import com.katyusha.aron.library.constant.PagePath

@Route(path = PagePath.ProductDetail)
class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailBinding
    private var sysNo: Int = 0
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var productInfoFragment: ProductInfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        initData()
        initView()
    }

    private fun initData() {
        sysNo = intent.getIntExtra("sysNo", 0)
        productInfoFragment = ProductInfoFragment()
        fragmentList.add(productInfoFragment)
        fragmentList.add(ProductDetailFragment())
        fragmentList.add(ProductCommentFragment())
    }

    private fun initView() {
        binding.prdDetailBack.setOnClickListener { finish() }
        binding.prdDetailViewPager.adapter = ItemTitlePageAdapter(supportFragmentManager, fragmentList, arrayOf("商品", "详情", "评价"))
        binding.prdDetailViewPager.offscreenPageLimit = 3
        binding.pstTabs.setViewPager(binding.prdDetailViewPager)
    }

    fun getSysNo(): Int {
        return sysNo
    }

    fun getInfoFragment(): ProductInfoFragment {
        return productInfoFragment
    }

}
