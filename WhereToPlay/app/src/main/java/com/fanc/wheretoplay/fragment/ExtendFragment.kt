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
    var type: String? = null
    var storeid: String? = null

    var discountValue: String? = null
    var storeName: String? = null
    var address: String? = null
    var phonevalue: String? = null
    var list: List<MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean>? = null


    constructor(packageX: MerchantDetailModel.ContentBean.StoreBean.PackageBean, type: String, storeid: String?, discountValue: String, storeName: String, address: String, phonevalue: String) : this() {
        this.packageX = packageX
        this.type = type
        this.storeid = storeid
        this.discountValue = discountValue
        this.storeName = storeName
        this.address = address
        this.phonevalue = phonevalue
    }


    lateinit var adapter: ExtendAdapter
    var flag: Boolean = false

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_more -> {
                adapter.change(!flag)
                adapter.notifyDataSetChanged()
                if (flag) {
                    tv_more.text = "更多" + (list!!.size - 2) + "个套餐 "
                } else {
                    tv_more.text = "收起"

                }

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

        if (type == "3") {//KTV

        } else {//酒吧

        }
        tv_title.text = "套餐（" + packageX!!.count + ")"
        list = packageX?.pkg_list
        if (list?.isNotEmpty()!! && list!!.size > 2) {
            tv_more.visibility = View.VISIBLE
        } else {
            tv_more.visibility = View.GONE
        }
        recycle.addItemDecoration(RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.resources.getColor(R.color.pay_reserve_list_divider_white)))
        recycle.layoutManager = LinearLayoutManager(mContext)
        adapter = ExtendAdapter(mContext, flag, list!!, storeid!!, discountValue!!, storeName!!, address!!, phonevalue!!)
        recycle.adapter = adapter
        if (list!!.size > 2) {
            tv_more.text = "更多" + (list!!.size - 2) + "个套餐 "
        } else {
            tv_more.visibility = View.GONE
        }

        tv_more.setOnClickListener(this)


    }
}
