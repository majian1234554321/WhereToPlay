package com.fanc.wheretoplay.datamodel
import com.google.gson.annotations.SerializedName


/**
 * Created by Joenny on 2018/3/23.
 */

data class EmplStoreModel(
		@SerializedName("code") var code: Int = 0,
		@SerializedName("message") var message: String = "",
		@SerializedName("content") var content: Contents = Contents()
)

data class Contents(
		@SerializedName("store_json") var storeJson: List<StoreJson> = listOf()
)

data class StoreJson(
		@SerializedName("id") var id: Int = 0,
		@SerializedName("name") var name: String = ""
)