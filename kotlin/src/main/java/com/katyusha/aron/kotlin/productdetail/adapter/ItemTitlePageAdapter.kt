package com.katyusha.aron.kotlin.productdetail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by jixiaolong on 2017/12/21.
 */
class ItemTitlePageAdapter(fm: FragmentManager, var list: ArrayList<Fragment>, var title: Array<String>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return title.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title[position]
    }
}