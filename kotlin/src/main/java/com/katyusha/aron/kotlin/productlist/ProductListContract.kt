package com.katyusha.aron.kotlin.productlist

import com.katyusha.aron.library.model.ErrorInfo

/**
 * Created by jixiaolong on 2017/12/14.
 */
interface ProductListContract {
    fun onSuccess(response: ProductListResponse)
    fun onFailure(errorInfo: ErrorInfo?)
}