package com.katyusha.aron.kotlin.productdetail.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.FragmentProductDetailBinding
import com.katyusha.aron.kotlin.productdetail.activity.ProductDetailActivity
import com.katyusha.aron.kotlin.productdetail.model.ProductDetailResponse

/**
 * Created by jixiaolong on 2017/12/21.
 */
class ProductDetailFragment: Fragment(), View.OnClickListener{

    private lateinit var binding : FragmentProductDetailBinding
    private lateinit var mActivity : ProductDetailActivity
    private lateinit var prdConfigFragment: PrdConfigFragment
    private lateinit var prdChildWebFragment: PrdChildWebFragment
    private var fragmentList = ArrayList<Fragment>()
    private var detailLink= ArrayList<ProductDetailResponse.OtherContentLink>()
    private var detailUrl: String? = null
    private var configUrl: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_product_detail, null)
        binding = DataBindingUtil.bind(view)!!
        initData()
        return view
    }

    private fun initData() {
        prdChildWebFragment = PrdChildWebFragment()
        prdConfigFragment = PrdConfigFragment()
        var bundle = Bundle()
        bundle.putInt("from", 1)
        prdChildWebFragment.arguments = bundle
        prdConfigFragment.arguments = bundle
        fragmentList.add(prdChildWebFragment)
        fragmentList.add(prdConfigFragment)
        binding.rootItem?.tabDetail?.setOnClickListener(this)
        binding.rootItem?.tabParams?.setOnClickListener(this)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            loadData()
            switchFragment(0)
        }
    }

    private fun loadData() {
        detailLink = mActivity.getInfoFragment().getDetailData()
        if (detailLink.size == 1) {
            detailUrl = detailLink[0].linksUrl
            configUrl = detailLink[0].linksUrl
        } else {
            detailUrl = detailLink[0].linksUrl
            configUrl = detailLink[1].linksUrl
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as ProductDetailActivity
    }

    override fun onClick(v: View) {
        when {
            v.id == R.id.tab_detail -> {
                switchFragment(0)
                binding.rootItem?.tabDetail?.setTextColor(resources.getColor(R.color.bl_color_green))
                binding.rootItem?.tabParams?.setTextColor(resources.getColor(R.color.bl_color_gray2))
            }
            v.id == R.id.tab_params -> {
                switchFragment(1)
                binding.rootItem?.tabDetail?.setTextColor(resources.getColor(R.color.bl_color_gray2))
                binding.rootItem?.tabParams?.setTextColor(resources.getColor(R.color.bl_color_green))
            }
        }
    }

    private fun switchFragment(index: Int) {
        var ft = childFragmentManager.beginTransaction()
        var currentFragment = fragmentList[index]
        if (!currentFragment.isAdded) {
            ft.add(R.id.fl_content, currentFragment)
        }
        for (i in fragmentList.indices) {
            if (i == index) {
                ft.show(fragmentList[i])
            }else {
                ft.hide(fragmentList[i])
            }
        }
        ft.commitAllowingStateLoss()
    }

    fun getDetailUrl(): String? {
        return detailUrl
    }

    fun getConfigUrl(): String? {
        return configUrl
    }
}