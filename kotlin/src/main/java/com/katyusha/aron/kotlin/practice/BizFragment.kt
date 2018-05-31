package com.katyusha.aron.kotlin.practice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.FragmentItemBizBinding

/**
 * Created by jixiaolong on 2018/2/6.
 */
class BizFragment: Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: FragmentItemBizBinding
    private lateinit var type: String
    private var dataList = ArrayList<String>()
    private lateinit var adapter: BizListAdapter
    private var isFirst = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_item_biz, null)
        binding = DataBindingUtil.bind(rootView)!!
        init()
        return rootView
    }

    //执行顺序高于生命周期
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (arguments != null) {
            type = arguments!!.getString("type")
        }
        if (isVisibleToUser) {
            initData()
            if (!isFirst) {
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun init() {
        isFirst = false
        if (type == "已送货") {
            binding.tabChild.visibility = View.VISIBLE
            for (i in 0..4) {
                binding.tabChild.addTab(binding.tabChild.newTab().setText("商品编号" + i))
            }
        }else {
            binding.tabChild.visibility = View.GONE
        }
        var manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.VERTICAL
        binding.rcvBiz.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        binding.rcvBiz.layoutManager = manager

        adapter = BizListAdapter(activity!!, dataList)
        binding.rcvBiz.adapter = adapter

        binding.tabChild.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                dataList.clear()
                for (i in 0..20) {
                    dataList.add(type + tab?.text + i)
                }
                adapter.notifyDataSetChanged()
            }

        })
    }

    private fun initData() {
        if (dataList != null) {
            dataList.clear()
        }
        for (i in 0..15) {
            Log.e("jixiaolong", i.toString() + type)
            dataList.add(type + i)
        }
    }
}