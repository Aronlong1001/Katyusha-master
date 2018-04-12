package com.katyusha.aron.kotlin.productdetail.model

import com.katyusha.aron.library.model.BaseResponse
import com.google.gson.annotations.SerializedName


/**
 * Created by jixiaolong on 2018/1/11.
 */
class RecommendPrdResponse : BaseResponse<ArrayList<RecommendPrdResponse.RecommendPrdBean>>(){

data class RecommendPrdBean(
		@SerializedName("sysNo") var sysNo: Int, //278671
		@SerializedName("productName") var productName: String, //越南进口红心火龙果1kg以上
		@SerializedName("imageUrl") var imageUrl: String, //https://image.benlailife.com/ProductImages/000/000/278/671/medium/766bef67-7106-4555-b138-49a71ee25a73.jpg
		@SerializedName("promotionWord") var promotionWord: String, //富含花青素 皮薄味甜
		@SerializedName("status") var status: Int, //1
		@SerializedName("isCanDelivery") var isCanDelivery: Boolean, //true
		@SerializedName("isArrivalDay") var isArrivalDay: Boolean, //false
		@SerializedName("isInventory") var isInventory: Boolean, //true
		@SerializedName("price") var price: Price,
		@SerializedName("productTag") var productTag: String,
		@SerializedName("productTagImg") var productTagImg: List<Any>,
		@SerializedName("productPropertyImg") var productPropertyImg: Any, //null
		@SerializedName("productTag2Imgs") var productTag2Imgs: List<Any>,
		@SerializedName("review") var review: String, //2157条评论
		@SerializedName("favorableRate") var favorableRate: String, //好评98%
		@SerializedName("promotionsTags") var promotionsTags: List<List<String>>,
		@SerializedName("specification") var specification: String, //1kg以上
		@SerializedName("startSaleNumber") var startSaleNumber: Int //1
)

data class Price(
		@SerializedName("startTime") var startTime: Any, //null
		@SerializedName("endTime") var endTime: Any, //null
		@SerializedName("hasOrigPrice") var hasOrigPrice: Boolean, //true
		@SerializedName("price") var price: String, //29.9
		@SerializedName("priceName") var priceName: String, //促销价
		@SerializedName("origPrice") var origPrice: String, //42.8
		@SerializedName("origPriceName") var origPriceName: String //本来价
)
}