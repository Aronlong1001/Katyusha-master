package com.katyusha.aron.kotlin.productdetail.fragment

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bumptech.glide.Glide
import com.katyusha.aron.kotlin.R
import com.katyusha.aron.kotlin.databinding.FragmentProductInfoBinding
import com.katyusha.aron.kotlin.productdetail.activity.ProductDetailActivity
import com.katyusha.aron.kotlin.productdetail.adapter.ImageHolderView
import com.katyusha.aron.kotlin.productdetail.adapter.RecommendAdapter
import com.katyusha.aron.kotlin.productdetail.model.ProductDetailContract
import com.katyusha.aron.kotlin.productdetail.model.ProductDetailResponse
import com.katyusha.aron.kotlin.productdetail.model.ProductDetailVM
import com.katyusha.aron.kotlin.productdetail.model.RecommendPrdResponse
import com.katyusha.aron.library.model.ErrorInfo
import com.katyusha.aron.library.utils.BLToast
import com.katyusha.aron.library.utils.GlideLoader
import com.katyusha.aron.library.widget.SlideDetailsLayout
import java.util.*

/**
 * Created by jixiaolong on 2017/12/21.
 */
class ProductInfoFragment : Fragment(), ProductDetailContract, View.OnClickListener, SlideDetailsLayout.OnSlideDetailsListener {

    private lateinit var mActivity: ProductDetailActivity
    private lateinit var binding: FragmentProductInfoBinding
    private var sysNo: Int = 0
    private var detailLink = ArrayList<ProductDetailResponse.OtherContentLink>()
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var prdConfigFragment: PrdConfigFragment
    private lateinit var prdChildWebFragment: PrdChildWebFragment
    private var detailUrl: String? = null
    private var configUrl: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_product_info, null)
        binding = DataBindingUtil.bind(view)
        initData()
        initRequest()
        return view
    }

    private fun initData() {
        sysNo = mActivity.getSysNo()
        binding.rootTab?.tabDetail?.setOnClickListener(this)
        binding.rootTab?.tabParams?.setOnClickListener(this)
        binding.fabUp.setOnClickListener(this)
        binding.svSwitch.setOnSlideDetailsListener(this)
    }

    private fun initRequest() {
        val prdDetailVM = ProductDetailVM(mActivity, this, sysNo)
        prdDetailVM.requestProductDetailData()
        prdDetailVM.requestRecommendProduct()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as ProductDetailActivity
    }

    override fun onResume() {
        super.onResume()
        binding.prdDetailVp.startTurning(5000)
    }

    override fun onPause() {
        super.onPause()
        binding.prdDetailVp.stopTurning()
    }

    override fun onDetailSuccess(response: ProductDetailResponse) {
        showPrdDetail(response.data)
    }

    override fun onRecommendSuccess(response: RecommendPrdResponse) {
        showRecommendPrd(response.data)
    }

    override fun onFailure(errorInfo: ErrorInfo) {
        BLToast.makeText(mActivity,"网络加载失败", BLToast.LENGTH_SHORT).show()
    }

    private fun showPrdDetail(data: ProductDetailResponse.ProductDetailBean) {
        binding.product = data
        detailLink = data.otherContentLinks
        binding.prdOriginPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.fabUp.hide()
        addPromotionInfo(data.newPromotions)
        addProductTag(data.productTags)
        setDetailLink()
        setLoopView(data?.appImg)
    }

    private fun addPromotionInfo(newPromotions: ArrayList<ProductDetailResponse.NewPromotion>) {
        if (newPromotions == null || newPromotions.size == 0 ) {
            return
        }
        binding.llNewPromotions.removeAllViews()

        //实际上这里应该for循环，为了方便显示效果，这里只取第一条数据
        var tvName = TextView(mActivity)
        tvName.text = newPromotions[0].promotionTypeName
        tvName.textSize = 10f
        tvName.setTextColor(resources.getColor(R.color.bl_color_orange))
        tvName.background = resources.getDrawable(R.drawable.bg_promotion)

        var tvDesc = TextView(mActivity)
        tvDesc.text = newPromotions[0].promotionalLanguage
        tvDesc.textSize = 14f
        tvDesc.maxLines = 1
        tvDesc.ellipsize = TextUtils.TruncateAt.END
        tvDesc.setPadding(15, 0, 0, 0)

        binding.llNewPromotions.addView(tvName)
        binding.llNewPromotions.addView(tvDesc)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun addProductTag(productTags: ArrayList<ProductDetailResponse.ProductTag>) {
        if (productTags == null || productTags.size == 0) {
            return
        }
        binding.llPrdTag.removeAllViews()
        for (i in productTags.indices) {
            var tv = TextView(mActivity)
            tv.text = productTags[i].tagName
            tv.textSize = 12f
            tv.setTextColor(resources.getColor(R.color.bl_color_gray2))
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.icon_prd_detail_tag, 0, 0, 0)
            tv.compoundDrawablePadding = 2
            val itemLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            itemLp.rightMargin = 12
            binding.llPrdTag.addView(tv, itemLp)
        }
    }

    private fun setDetailLink() {
        if (detailLink.size == 1) {
            detailUrl = detailLink[0].linksUrl
            configUrl = detailLink[0].linksUrl
        } else {
            detailUrl = detailLink[0].linksUrl
            configUrl = detailLink[1].linksUrl
        }
        prdChildWebFragment = PrdChildWebFragment()
        prdConfigFragment = PrdConfigFragment()
        var bundle = Bundle()
        bundle.putInt("from", 0)
        prdChildWebFragment.arguments = bundle
        prdConfigFragment.arguments = bundle
        fragmentList.add(prdChildWebFragment)
        fragmentList.add(prdConfigFragment)
        switchFragment(0)
    }

    //展示推荐商品
    private fun showRecommendPrd(data: ArrayList<RecommendPrdResponse.RecommendPrdBean>) {
        if (data == null || data.isEmpty()) {
            binding.llRecommend.visibility = View.GONE
            return
        }
        var manager = LinearLayoutManager(mActivity)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        binding.prdDetailRecycleView.layoutManager = manager
        var adapter = RecommendAdapter(mActivity, data)
        binding.prdDetailRecycleView.adapter = adapter
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
            } else {
                ft.hide(fragmentList[i])
            }
        }
        ft.commitAllowingStateLoss()
    }

    private fun setLoopView(list: List<String>?) {
        binding.prdDetailVp.setPages({ ImageHolderView() }, list)
        binding.prdDetailVp.setPageIndicator(intArrayOf(R.drawable.head_index_white, R.drawable.head_index_green))
        binding.prdDetailVp.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
    }

    override fun onClick(v: View) {
        when {
            v.id == R.id.tab_detail -> {
                switchFragment(0)
                binding.rootTab?.tabDetail?.setTextColor(resources.getColor(R.color.bl_color_green))
                binding.rootTab?.tabParams?.setTextColor(resources.getColor(R.color.bl_color_gray2))
            }
            v.id == R.id.tab_params -> {
                switchFragment(1)
                binding.rootTab?.tabDetail?.setTextColor(resources.getColor(R.color.bl_color_gray2))
                binding.rootTab?.tabParams?.setTextColor(resources.getColor(R.color.bl_color_green))
            }
            v.id == R.id.fab_up -> {
                binding.prdDetailSv.smoothScrollTo(0, 0)
                binding.svSwitch.smoothClose(true)
            }
        }
    }

    override fun onStatusChanged(status: SlideDetailsLayout.Status?) {
        if (status == SlideDetailsLayout.Status.OPEN) { //当前为图文详情页
            binding.fabUp.show()
            mActivity.binding.prdDetailViewPager.setNoScroll(true)
            mActivity.binding.prdDetailTitle.visibility = View.VISIBLE
            mActivity.binding.pstTabs.visibility = View.GONE
        } else {
            binding.fabUp.hide()
            mActivity.binding.prdDetailViewPager.setNoScroll(false)
            mActivity.binding.prdDetailTitle.visibility = View.GONE
            mActivity.binding.pstTabs.visibility = View.VISIBLE
        }
    }

    fun getDetailData(): ArrayList<ProductDetailResponse.OtherContentLink> {
        return detailLink
    }

    fun getDetailUrl(): String? {
        return detailUrl
    }

    fun getConfigUrl(): String? {
        return configUrl
    }

    companion object {
        @JvmStatic
        @BindingAdapter("areaImageUrl")
        fun loadAreaImage(imageView: ImageView, url : String?) {
            GlideLoader.loadImage(imageView.context, url + "@0x.png", imageView)
        }
    }
}