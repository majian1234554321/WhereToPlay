package com.fanc.wheretoplay.datamodel

import com.google.gson.annotations.SerializedName
import kotlin.collections.List


/**
 *
 * @author admin
 * @date 2017/12/23
 */


data class CouponCountModel(
        @SerializedName("code") var code: Int = 0, //0
        @SerializedName("message") var message: String = "", //请求成功
        @SerializedName("content") var content: Content = Content()
)

data class Content(
        @SerializedName("list") var list: ListBean = ListBean()
)

data class ListBean(
        @SerializedName("available") var available: Int = 0, //2
        @SerializedName("used") var used: Int = 0, //2
        @SerializedName("expired") var expired: Int = 0 //3
)