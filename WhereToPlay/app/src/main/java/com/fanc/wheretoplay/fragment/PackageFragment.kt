package com.fanc.wheretoplay.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.view.PackageItemView
import com.fanc.wheretoplay.view.TitleBarView

/**
 *
 * @author admin
 * @date 2017/12/23
 */
class PackageFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(inflater.context, R.layout.packagefragment, null)

        view.findViewById<TitleBarView>(R.id.tbv).setTv_title("套餐详情")
        val packageItemView1 = view.findViewById(R.id.piv1) as PackageItemView
        val packageItemView2 = view.findViewById(R.id.piv2) as PackageItemView
        val packageItemView3 = view.findViewById(R.id.piv3) as PackageItemView
        val packageItemView4 = view.findViewById(R.id.piv4) as PackageItemView

        packageItemView1.setTopText("有效期")
        packageItemView2.setTopText("预约信息")
        packageItemView3.setTopText("规则提醒")
        packageItemView4.setTopText("温馨提示")


        return view
    }


}