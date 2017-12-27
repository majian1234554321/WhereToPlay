package com.fanc.wheretoplay.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.Extend0Adapter
import com.fanc.wheretoplay.adapter.ExtendAdapter

import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.MerchantDetailModel
import com.fanc.wheretoplay.divider.RecycleViewDivider
import com.fanc.wheretoplay.util.DateFormatUtil
import com.fanc.wheretoplay.util.UIUtils
import kotlinx.android.synthetic.main.extendfragment.*
import java.util.*

/**
 * @author admin
 * @date 2017/12/24
 */
@SuppressLint("ValidFragment")
class ExtendFragment(
        val packageX: MerchantDetailModel.ContentBean.StoreBean.PackageBean?, val type: String
        , val storeid: String?, val discountValue: String, val storeName: String, val address: String, val phonevalue: String
) : BaseFragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when (p1) {
            R.id.rb1 -> {
            }
            R.id.rb2 -> {
            }
            R.id.rb3 -> {
            }
            R.id.rb4 -> {
            }
            R.id.rb5 -> {
            }
            R.id.rb6 -> {
            }
            R.id.rb7 -> {
            }


            R.id.rb21 -> {
            }

            R.id.rb22 -> {
            }
            R.id.rb23 -> {
            }
            else -> {
            }
        }
    }


    lateinit var list: MutableList<MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean>


    lateinit var adapter: ExtendAdapter
    var flag: Boolean = false

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_more -> {
                adapter.change(!flag)
                adapter.notifyDataSetChanged()
                if (flag) {
                    tv_more.text = "更多".plus(list.size?.minus(2)).plus("个套餐 ")
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

    val DAY = 1000 * 60 * 60 * 24
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type == "3") {//KTV
            ll00.visibility = View.VISIBLE
        } else {//酒吧
            ll00.visibility = View.GONE
        }

        tv_title.text = "套餐（" + packageX?.count + ")"
        list = packageX?.pkg_list as MutableList<MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean>

        if (list.size > 2) {
            tv_more.visibility = View.VISIBLE
        } else {
            tv_more.visibility = View.GONE
        }



        recycle.addItemDecoration(RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.resources.getColor(R.color.pay_reserve_list_divider_white)))
        recycle.layoutManager = LinearLayoutManager(mContext)
        adapter = ExtendAdapter(mContext, flag, list, storeid, discountValue, storeName, address, phonevalue)
        recycle.adapter = adapter


        if (list.size > 2) {
            tv_more.text = "更多".plus(list?.size?.minus(2)).plus("个套餐 ")
        } else {
            tv_more.visibility = View.GONE
        }

        tv_more.setOnClickListener(this)


        val mDate = (0..6).map { Date(System.currentTimeMillis() + it * DAY) }

        recycle0.addItemDecoration(RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.resources.getColor(R.color.pay_reserve_list_divider_white)))
        recycle0.layoutManager = LinearLayoutManager(mContext)


        rg.setOnCheckedChangeListener(this)
        rb1.text = DateFormatUtil.getCustomDay(mDate[0]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[0]))
        rb2.text = DateFormatUtil.getCustomDay(mDate[1]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[1]))
        rb3.text = DateFormatUtil.getCustomDay(mDate[2]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[2]))
        rb4.text = DateFormatUtil.getCustomDay(mDate[3]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[3]))
        rb5.text = DateFormatUtil.getCustomDay(mDate[4]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[4]))
        rb6.text = DateFormatUtil.getCustomDay(mDate[5]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[5]))
        rb7.text = DateFormatUtil.getCustomDay(mDate[6]).toString().plus("\n").plus(DateFormatUtil.getCustomDayOfWeek(mDate[6]))


        rg2.setOnCheckedChangeListener(this)


        val adapter0 = Extend0Adapter(mContext, flag, list, storeid, discountValue, storeName, address, phonevalue)

        recycle0.adapter = adapter0


    }
}
