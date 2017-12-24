package com.fanc.wheretoplay.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.ExtendAdapter

import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.MerchantDetailModel
import com.fanc.wheretoplay.divider.RecycleViewDivider
import com.fanc.wheretoplay.util.UIUtils
import kotlinx.android.synthetic.main.extendfragment.*

/**
 * @author admin
 * @date 2017/12/24
 */
@SuppressLint("ValidFragment")
class ExtendFragment() : BaseFragment(), View.OnClickListener {
    private var packageX: MerchantDetailModel.ContentBean.StoreBean.PackageBean? = null


    constructor(packageX: MerchantDetailModel.ContentBean.StoreBean.PackageBean) : this() {
        this.packageX = packageX
    }


    lateinit var adapter: ExtendAdapter
    var flag: Boolean = false

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_more -> {
                adapter.change(!flag)
                adapter.notifyDataSetChanged()
                flag = !flag
            }
            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.extendfragment, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_title.text = "套餐（" + packageX!!.count + ")"
        val list = packageX?.pkg_list

        recycle.addItemDecoration(RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.resources.getColor(R.color.pay_reserve_list_divider_white)))
        recycle.layoutManager = LinearLayoutManager(mContext)
        adapter = ExtendAdapter(mContext, flag, list!!)
        recycle.adapter = adapter

        tv_more.setOnClickListener(this)


    }
}
