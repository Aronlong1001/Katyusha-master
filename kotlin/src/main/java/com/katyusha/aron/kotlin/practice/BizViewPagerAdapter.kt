package com.katyusha.aron.kotlin.practice

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by jixiaolong on 2018/2/6.
 */
class BizViewPagerAdapter(fm: FragmentManager, private var titleList: Array<String>, private var fragmentList: ArrayList<Fragment>): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }
}