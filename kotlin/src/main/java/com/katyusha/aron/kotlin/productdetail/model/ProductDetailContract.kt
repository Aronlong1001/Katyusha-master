package com.katyusha.aron.kotlin.productdetail.model

import com.katyusha.aron.library.model.ErrorInfo

/**
 * Created by jixiaolong on 2017/12/28.
 */
interface ProductDetailContract {
    fun onDetailSuccess(response: ProductDetailResponse)
    fun onFailure(errorInfo: ErrorInfo)
    fun onRecommendSuccess(response: RecommendPrdResponse)
}