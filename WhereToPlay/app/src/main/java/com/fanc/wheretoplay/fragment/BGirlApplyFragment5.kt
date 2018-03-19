package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import kotlinx.android.synthetic.main.bgirlapplyfragment5.*
import kotlinx.android.synthetic.main.bgirlinfo.*
import kotlinx.android.synthetic.main.bgirltitle.*

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment5 : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment5, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("从业申请")
        tv5.setTextColor(Color.parseColor("#c4483c"))

        eev1.setTv("单位名称", false)
        eev2.setTv("姓名", false)
        eev3.setTv("性别", false)
        eev4.setTv("出生日期", false)
        eev5.setTv("民族", false)
        eev6.setTv("证件类型", false)
        eev7.setTv("证件号", false)
        eev8.setTv("户籍地址", false)
        eev9.setTv("详细地址", false)
        eev10.setTv("现住地址", false)
        eev11.setTv("收货地址", false)
        eev12.setTv("本人手机号", false)
        eev13.setTv("紧急联系人", false)
        eev14.setTv("联系人电话", false)
        eev15.setTv("行业", false)
        eev16.setTv("职务", false)


    }

    companion object {
        fun newInstance(): BGirlApplyFragment5 {
            return BGirlApplyFragment5()
        }
    }
}