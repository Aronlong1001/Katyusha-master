package com.katyusha.aron.kotlin.practice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.ActivityBizListBinding
import com.katyusha.aron.library.constant.PagePath

/**
 * Created by jixiaolong on 2018/2/6.
 */
@Route(path = PagePath.BIZ_LIST)
class BizListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBizListBinding
    private var title = arrayOf("待送货", "待支付", "已送货")
    private var fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_biz_list)
        initViews()
    }

    private fun initViews() {
        for (i in title.indices) {
            var fragment = BizFragment()
            var bundle = Bundle()
            bundle.putString("type", title[i])
            fragment.arguments = bundle
            fragmentList.add(fragment)
        }
        var adapter = BizViewPagerAdapter(supportFragmentManager, title, fragmentList)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 1
        binding.tab.setupWithViewPager(binding.viewPager)

        //可以循环更改标题
        binding.tab.getTabAt(0)?.text = "待送货（12）"

    }
}