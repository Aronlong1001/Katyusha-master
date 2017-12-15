package com.katyusha.aron.kotlin.productlist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.ActivityKotlinListBinding
import com.katyusha.aron.library.constant.PagePath
import com.katyusha.aron.library.model.ErrorInfo

/**
 * Created by jixiaolong on 2017/12/13.
 */
@Route(path = PagePath.KotlinList)
class ProductListActivity : AppCompatActivity(), ProductListContract {

    private lateinit var binding : ActivityKotlinListBinding
    private lateinit var productList: ArrayList<ProductListResponse.ProductListBean>
    private lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin_list)
        initView()
        initRequest()
    }

    private fun initRequest() {
        var productVm = ProductListVm(this, this)
        productVm.requestProductListData()
    }

    private fun initView() {
        binding.ivKotlinBack.setOnClickListener {
            finish()
        }
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.recyclerView.layoutManager = manager

        productList = ArrayList()
        adapter = ProductListAdapter(this, productList)
        binding.recyclerView.adapter = adapter

    }

    override fun onSuccess(response: ProductListResponse) {
        productList.addAll(response.data)
        adapter.notifyDataSetChanged()
    }

    override fun onFailure(errorInfo: ErrorInfo) {

    }
}