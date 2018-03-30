package com.fanc.wheretoplay.datamodel
import com.google.gson.annotations.SerializedName


/**
 *
 * @author admin
 * @date 2018/3/30
 */



data class Modelsss(
		@SerializedName("code") var code: String = "0",
		@SerializedName("message") var message: String = "",
		@SerializedName("content") var content: Content1 = Content1()
)

data class Content1(
		@SerializedName("contents") var contents: List<Content2> = listOf()
)

data class Content2(
		@SerializedName("application_id") var applicationId: String = "",
		@SerializedName("now_address") var nowAddress: String = "",
		@SerializedName("mobile") var mobile: String = "",
		@SerializedName("name") var name: String = "",
		@SerializedName("profession") var profession: String = ""
)
