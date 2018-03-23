package com.fanc.wheretoplay.datamodel

/**
 * Created by Joenny on 2018/3/23.
 */


data class EmplStoreModel(
		val code: String,
		val message: String,
		val content: List<Contents>
)

data class Contents(
		val name: String
)