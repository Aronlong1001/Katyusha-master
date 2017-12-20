package com.katyusha.aron.kotlin.productdetail

import android.content.Context
import com.katyusha.aron.kotlin.productlist.ProductListResponse
import com.katyusha.aron.library.constant.Urls
import com.katyusha.aron.library.http.HttpParams
import com.katyusha.aron.library.http.request.BLRequest
import com.katyusha.aron.library.http.subscriber.CommonSubscriber
import com.katyusha.aron.library.model.BaseResponse
import com.katyusha.aron.library.model.BaseVM

/**
 * Created by jixiaolong on 2017/12/15.
 */
class ProductDetailVM(context: Context, private val sysNo: String): BaseVM(context) {

    fun requestProductDetailData() {
        val subscriber = object : CommonSubscriber<BaseResponse<*>>() {
            override fun onSuccess(response: BaseResponse<*>?) {
                var resp = response as ProductDetailResponse
            }

            override fun onFailure(type: Int, response: BaseResponse<*>) {

            }

        }

        var params = HttpParams()
        params.put("channel", "benlai")
        params.put("source", "3")
        params.put("productSysNo",sysNo)

        BLRequest.getInstance().createApi(ProductDetailResponse::class.java)
                .get(Urls.PRODUCT_DETAIL, params.urlParamsMap)
                .subscribe(subscriber)
        addSubscription(subscriber)
    }
}