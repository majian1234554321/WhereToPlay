package com.fanc.wheretoplay.view

import com.fanc.wheretoplay.datamodel.AccessOrderIdModel

/**
 * Created by Joenny on 2018/3/22.
 */
interface BGirlFragmentView {
    fun setSuccessData(type: String, content: AccessOrderIdModel.ContentBean,applicationId:String)
    fun setFailedData(value: String)
}