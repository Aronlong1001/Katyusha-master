package com.katyusha.aron.kotlin.productlist

import android.content.Context
import com.katyusha.aron.library.constant.Urls
import com.katyusha.aron.library.http.FailureType
import com.katyusha.aron.library.http.HttpParams
import com.katyusha.aron.library.http.request.BLRequest
import com.katyusha.aron.library.http.subscriber.CommonSubscriber
import com.katyusha.aron.library.model.BaseResponse
import com.katyusha.aron.library.model.BaseVM

/**
 * Created by jixiaolong on 2017/12/13.
 */
class ProductListVm(context: Context, val contract: ProductListContract) : BaseVM(context) {

     fun requestProductListData() {
         showLoadingDialog()
        val subscriber = object : CommonSubscriber<BaseResponse<*>>(){
            override fun onSuccess(response: BaseResponse<*>?) {
                dismissLoadingDialog()
               var resp = response as ProductListResponse
                contract.onSuccess(resp)
            }

            override fun onFailure(type: Int, response: BaseResponse<*>?) {
                dismissLoadingDialog()
                if (type == FailureType.ABNORMAL) {
                    contract.onFailure(response?.errorInfo)
                } else if (type == FailureType.NETWORK) {
                    contract.onFailure(null)
                }
            }
        }

        var params = HttpParams()
        params.put("channel", "benlai")
        params.put("source", "3")
        params.put("limit", "20")
        params.put("sort", "0")
        params.put("c1", "2451")
        params.put("c2", "2452")
        params.put("c3", "2460")

        BLRequest.getInstance().createApi(ProductListResponse::class.java)
                .get(Urls.PRODUCT_LIST, params.urlParamsMap)
                .subscribe(subscriber)
        addSubscription(subscriber)
    }





}