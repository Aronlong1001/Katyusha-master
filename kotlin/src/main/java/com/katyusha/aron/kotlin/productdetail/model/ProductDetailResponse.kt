package com.katyusha.aron.kotlin.productdetail.model

import com.katyusha.aron.library.model.BaseResponse
import com.google.gson.annotations.SerializedName


/**
 * Created by jixiaolong on 2017/12/15.
 */
class ProductDetailResponse : BaseResponse<ProductDetailResponse.ProductDetailBean>() {


    data class ProductDetailBean(
            @SerializedName("sysNo") var sysNo: Int, //57087
            @SerializedName("productId") var productId: String, //P0000057087TC
            @SerializedName("appImg") var appImg: List<String>,
            @SerializedName("hasInventory") var hasInventory: Boolean, //true
            @SerializedName("inventoryMsg") var inventoryMsg: String, //现在有货
            @SerializedName("canDelivery") var canDelivery: Boolean, //true
            @SerializedName("productName") var productName: String, //越南进口红心火龙果（2.2-2.5kg）
            @SerializedName("promotionWord") var promotionWord: String, //富含花青素 皮薄味甜
            @SerializedName("productPrice") var productPrice: ProductPrice,
            @SerializedName("warrantyTags") var warrantyTags: ArrayList<Any>,
            @SerializedName("hasCustomerRankPrice") var hasCustomerRankPrice: Boolean, //false
            @SerializedName("customerRankPrice") var customerRankPrice: Any, //null
            @SerializedName("productTags") var productTags: ArrayList<ProductTag>,
            @SerializedName("productAttributes") var productAttributes: ArrayList<String>, //null
            @SerializedName("isArrivalDay") var isArrivalDay: Boolean, //false
            @SerializedName("arrivalDayMsg") var arrivalDayMsg: String,
            @SerializedName("isFreeShip") var isFreeShip: Boolean, //true
            @SerializedName("shipMsg") var shipMsg: String, //上海市满60.00元包邮
            @SerializedName("isWish") var isWish: Boolean, //false
            @SerializedName("newPromotions") var newPromotions: ArrayList<NewPromotion>,
            @SerializedName("tips") var tips: String,
            @SerializedName("otherContentLinks") var otherContentLinks: ArrayList<OtherContentLink>,
            @SerializedName("status") var status: Int, //1
            @SerializedName("cdStartTime") var cdStartTime: String, //-2147483648
            @SerializedName("cdEndTime") var cdEndTime: String, //-2147483648
            @SerializedName("isLogin") var isLogin: Boolean, //false
            @SerializedName("productLink") var productLink: String, //https://m.benlai.com/bj/product/57087
            @SerializedName("productQRCodeUrl") var productQRCodeUrl: String, //https://m.benlai.com/bj/product/57087?Method=ScanQRCode
            @SerializedName("applyRangeType") var applyRangeType: Int, //-999999
            @SerializedName("shareImageUrl") var shareImageUrl: String, //https://image5.benlailife.com/ProductImages/000/000/057/087/medium/81e45552-5b01-4c0f-9462-aa4b14f2c480.jpg
            @SerializedName("cpsProduct") var cpsProduct: CpsProduct,
            @SerializedName("presellTip") var presellTip: String,
            @SerializedName("productBasicSysNo") var productBasicSysNo: Int, //722
            @SerializedName("isJoinPresent") var isJoinPresent: Boolean, //false
            @SerializedName("productionDate") var productionDate: String,
            @SerializedName("shelfTime") var shelfTime: String,
            @SerializedName("isWillExpire") var isWillExpire: Int, //0
            @SerializedName("seoTitle") var seoTitle: String, //越南进口红心火龙果（2.2-2.5kg） 富含花青素 皮薄味甜
            @SerializedName("seoKeyword") var seoKeyword: String, //进口肉筋19.9一斤，越南红心火龙果，红心火龙果，越南火龙果，火龙果，进口火龙果，冲击味蕾，本来甄选，果蔬色拉，热带风情，火龙果，水果色拉，同事好友，合家欢享，717满减
            @SerializedName("seoDescription") var seoDescription: String, //越南进口红心火龙果（2.2-2.5kg） 富含花青素 皮薄味甜,富含花青素 皮薄味甜,进口肉筋19.9一斤，越南红心火龙果，红心火龙果，越南火龙果，火龙果，进口火龙果，冲击味蕾，本来甄选，果蔬色拉，热带风情，火龙果，水果色拉，同事好友，合家欢享，717满减
            @SerializedName("keywords") var keywords: Any, //null
            @SerializedName("coupons") var coupons: ArrayList<Coupon>,
            @SerializedName("productLabel") var productLabel: String,
            @SerializedName("productArea") var productArea: ProductArea,
            @SerializedName("recommends") var recommends: Any, //null
            @SerializedName("needSiteChangeMsg") var needSiteChangeMsg: Any, //null
            @SerializedName("startSaleNumber") var startSaleNumber: Int, //1
            @SerializedName("isEasyHome") var isEasyHome: Int, //0
            @SerializedName("productTagList") var productTagList: ArrayList<ProductTagList>
    )

    data class ProductArea(
            @SerializedName("name") var name: String, //产地
            @SerializedName("area") var area: String, //越南
            @SerializedName("nationalFlag") var nationalFlag: String //https://image4.benlailife.com//AppIcons/flag/22_4
    )

    data class ProductTag(
            @SerializedName("type") var type: Int, //2
            @SerializedName("tagName") var tagName: String, //全面质检
            @SerializedName("description") var description: String //支持43项检测，确保安全无农残。
    )

    data class OtherContentLink(
            @SerializedName("productSaleType") var productSaleType: Int, //0
            @SerializedName("sysNo") var sysNo: Int, //-999999
            @SerializedName("name") var name: String, //图文详情
            @SerializedName("imageUrl") var imageUrl: String,
            @SerializedName("linksUrl") var linksUrl: String, //https://www.benlai.com/Product/AppProductDetails?id=57087
            @SerializedName("isShow") var isShow: Boolean, //false
            @SerializedName("createTime") var createTime: String, //1900-01-01 00:00:00
            @SerializedName("showImage") var showImage: Boolean, //false
            @SerializedName("sort") var sort: Int //-999999
    )

    data class ProductPrice(
            @SerializedName("hasOrigPrice") var hasOrigPrice: Boolean, //true
            @SerializedName("price") var price: String, //69
            @SerializedName("priceName") var priceName: String, //促销价
            @SerializedName("origPrice") var origPrice: String, //99.6
            @SerializedName("origPriceName") var origPriceName: String //本来价
    )

    data class ProductTagList(
            @SerializedName("tagType") var tagType: Int, //1
            @SerializedName("tag") var tag: String, //https://image4.benlailife.com//Product/Tag/ef716b3a-1416-4724-996d-0117a020d8ae.jpg
            @SerializedName("position") var position: Int //1
    )

    data class CpsProduct(
            @SerializedName("cpsReturnMoney") var cpsReturnMoney: String,
            @SerializedName("title") var title: String,
            @SerializedName("content") var content: String,
            @SerializedName("contentLink") var contentLink: String //http://m.benlai.com/earn/myEarn
    )

    data class Coupon(
            @SerializedName("batchNo") var batchNo: String, //118884
            @SerializedName("promotionMsg") var promotionMsg: String //满188减109
    )

    data class NewPromotion(
            @SerializedName("sysNo") var sysNo: Int, //232266
            @SerializedName("promotionsType") var promotionsType: Int, //7
            @SerializedName("promotionTypeName") var promotionTypeName: String, //换购
            @SerializedName("promotionalLanguage") var promotionalLanguage: String, //购买水果满19元即可参与超值换购 部分商品除外
            @SerializedName("appConditionList") var appConditionList: List<Any>,
            @SerializedName("isLink") var isLink: Int, //1
            @SerializedName("hasGift") var hasGift: Boolean //true
    )
}