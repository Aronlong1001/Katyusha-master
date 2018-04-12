package com.katyusha.aron.kotlin.productdetail.model

import android.content.Context
import com.katyusha.aron.library.constant.Urls
import com.katyusha.aron.library.http.HttpParams
import com.katyusha.aron.library.http.request.BLRequest
import com.katyusha.aron.library.http.subscriber.CommonSubscriber
import com.katyusha.aron.library.model.BaseResponse
import com.katyusha.aron.library.model.BaseVM

/**
 * Created by jixiaolong on 2017/12/15.
 */
class ProductDetailVM(context: Context, val contract: ProductDetailContract, private var sysNo: Int): BaseVM(context) {

    fun requestProductDetailData() {
        val subscriber = object : CommonSubscriber<BaseResponse<*>>() {
            override fun onSuccess(response: BaseResponse<*>?) {
                var resp = response as ProductDetailResponse
                contract.onDetailSuccess(resp)
            }

            override fun onFailure(type: Int, response: BaseResponse<*>) {
                contract.onFailure(response.errorInfo)
            }

        }

        var params = HttpParams()
        params.put("channel", "benlai")
        params.put("source", "3")
        params.put("productSysNo",sysNo.toString())

        BLRequest.getInstance().createApi(ProductDetailResponse::class.java)
                .get(Urls.PRODUCT_DETAIL, params.urlParamsMap)
                .subscribe(subscriber)
        addSubscription(subscriber)
    }

    fun requestRecommendProduct() {
        val subscriber = object : CommonSubscriber<BaseResponse<*>>() {
            override fun onSuccess(response: BaseResponse<*>?) {
                var resp = response as RecommendPrdResponse
                contract.onRecommendSuccess(resp)
            }

            override fun onFailure(type: Int, response: BaseResponse<*>) {
                contract.onFailure(response.errorInfo)
            }
        }

        var params = HttpParams()
        params.put("channel", "benlai")
        params.put("source", "3")
        params.put("productSysNo",sysNo.toString())

        BLRequest.getInstance().createApi(RecommendPrdResponse::class.java)
                .get(Urls.RECOMMEND_PRODUCT, params.urlParamsMap)
                .subscribe(subscriber)
        addSubscription(subscriber)
    }
}