package com.katyusha.aron.kotlin.productlist

import com.katyusha.aron.library.model.BaseResponse
import com.google.gson.annotations.SerializedName


/**
 * Created by jixiaolong on 2017/12/13.
 */
class ProductListResponse: BaseResponse<ArrayList<ProductListResponse.ProductListBean>>() {

data class ProductListBean(
        @SerializedName("sysNo") var sysNo: Int, //57087
        @SerializedName("productName") var productName: String, //越南进口红心火龙果（2.2-2.5kg）
        @SerializedName("imageUrl") var imageUrl: String, //http://image3.benlailife.com/ProductImages/000/000/057/087/medium/81e45552-5b01-4c0f-9462-aa4b14f2c480.jpg
        @SerializedName("promotionWord") var promotionWord: String, //富含花青素 皮薄味甜
        @SerializedName("status") var status: Int, //1
        @SerializedName("isCanDelivery") var isCanDelivery: Boolean, //true
        @SerializedName("isArrivalDay") var isArrivalDay: Boolean, //false
        @SerializedName("isInventory") var isInventory: Boolean, //true
        @SerializedName("price") var price: Price,
        @SerializedName("productTag") var productTag: Any, //null
        @SerializedName("productTagImg") var productTagImg: List<Any>,
        @SerializedName("productPropertyImg") var productPropertyImg: Any, //null
        @SerializedName("productTag2Imgs") var productTag2Imgs: List<String>,
        @SerializedName("review") var review: String, //6676条评论
        @SerializedName("favorableRate") var favorableRate: String, //好评99%
        @SerializedName("promotionsTags") var promotionsTags: List<Any>,
        @SerializedName("specification") var specification: String, //2.2-2.5kg
        @SerializedName("startSaleNumber") var startSaleNumber: Int //1
)

data class Price(
		@SerializedName("startTime") var startTime: Any, //null
		@SerializedName("endTime") var endTime: Any, //null
		@SerializedName("hasOrigPrice") var hasOrigPrice: Boolean, //true
		@SerializedName("price") var price: String, //69
		@SerializedName("priceName") var priceName: String, //促销价
		@SerializedName("origPrice") var origPrice: String, //99.6
		@SerializedName("origPriceName") var origPriceName: String //本来价
)

}

