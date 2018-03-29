package com.fanc.wheretoplay.datamodel

/**
 * Created by Joenny on 2018/3/27.
 */

data class EmplDetailModel(
		val code: String,
		val message: String,
		val content: EmplDetailModelContents
)

data class EmplDetailModelContents(
		val id: String,
		val user_id: String,
		val application_id: String,
		val store_id: String,
		val name: String,
		val mobile: String,
		val password: String,
		val user_sn: String,
		val id_address: String,
		val id_number: String,
		val id_type: String,
		val start_time: String,
		val year_review_status: String,
		val year_status_time: String,
		val patch_card_time: String,
		val patch_card_status: String,
		val takeup_time: String,
		val personal_path: String,
		val urgent_man: String,
		val urgent_mobile: String,
		val sex: String,
		val birthday: String,
		val now_address: String,
		val now_detail_address: String,
		val harvest_address: String,
		val birth_address: String,
		val birth_detail_address: String,
		val profession: String,
		val position: String,
		val ethnic: String,
		val store_name: String,
		val ic_pic_path1: String,
		val ic_pic_path2: String
)