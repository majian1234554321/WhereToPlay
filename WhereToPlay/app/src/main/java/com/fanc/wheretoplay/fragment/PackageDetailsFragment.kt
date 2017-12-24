package com.fanc.wheretoplay.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.PackageAdapter
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.OrderDetailModel
import com.fanc.wheretoplay.datamodel.PackageModel
import com.fanc.wheretoplay.network.Network
import com.fanc.wheretoplay.presenter.DetailsOrderPresenter
import com.fanc.wheretoplay.util.DateFormatUtil
import com.fanc.wheretoplay.view.DetailsOrderView
import kotlinx.android.synthetic.main.packagedetailsfragment.*


@SuppressLint("ValidFragment")
/**
 *
 * @author admin
 * @date 2017/12/22
 */
class PackageDetailsFragment(val order_idValue: String?) : BaseFragment(), DetailsOrderView, View.OnClickListener {
    var phoneNum: String? = null
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.iv_phone -> {
                //直接拨号
                val uri = Uri.parse("tel:" + phoneNum)
                val intent = Intent(Intent.ACTION_CALL, uri)
                //此处不判断就会报错
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent)
                } else {
                    Toast.makeText(mContext, "请到设置页面开启拨打电话权限", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
            }
        }
    }

    override fun setDetailsOrderViewData(contentBean: OrderDetailModel.ContentBean?) {

        Glide.with(mContext).load(contentBean?.package_detail?.pic_list?.get(0)).placeholder(R.drawable.default_square).into(iv001)
        tv_storeName00.text = contentBean?.store_name
        tv_desc.text = contentBean?.package_detail?.introduce
        tv_realMoney.text = "￥" + contentBean?.package_detail?.origin_price
        tv_endtime.text = "有效期至" + DateFormatUtil.stampToDate(contentBean?.package_end_time)
        tv_orderNumber.text = "订  单   号：" + contentBean?.order_sn
        tv_phone.text = "手  机   号：" + contentBean?.mobile
        tv_payTime.text = "付款时间：" + DateFormatUtil.stampToDate(contentBean?.package_detail?.create_time)
        tv_realMoney02.text = "订单总价：" + "￥" + contentBean?.package_detail?.origin_price
        tv_realMoney03.text = "实际付款：" + "￥" + contentBean?.package_detail?.discount_price


        tv_storeName2.text = contentBean?.store_name
        tv_address.text = contentBean?.address

        phoneNum = contentBean?.phone

        tv_falseMoney1.text = contentBean?.package_detail?.origin_price + "元"
        tv_realMoney1.text = contentBean?.package_detail?.discount_price + "元"
        tv_detail.text = contentBean?.package_detail?.detail
        piv1.setTopText("有效期")
        piv2.setTopText("预约信息")
        piv3.setTopText("规则提醒")
        piv4.setTopText("温馨提示")

        piv1.setButtomText(contentBean?.package_detail?.buy_notice?.effect_date!!)
        piv2.setButtomText(contentBean?.package_detail?.buy_notice?.booking_info!!)
        piv3.setButtomText(contentBean?.package_detail?.buy_notice?.rule_remind!!)
        piv4.setButtomText(contentBean?.package_detail?.buy_notice?.tip!!)


        val lists = arrayListOf<PackageModel.ContentBean.ProductListBean>()

        val list = contentBean.package_detail?.product_list
        list?.forEachIndexed { index, productListBean ->
            val model = PackageModel.ContentBean.ProductListBean(productListBean.name, productListBean.price, productListBean.num)
            lists.add(model)
        }

        val adapter = PackageAdapter(mContext, lists)
        recycle.adapter = adapter

    }

    override fun cancelOrderAction() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.packagedetailsfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tbv.setTv_title("订单详情")
        iv_phone.setOnClickListener(this)
        recycle.layoutManager = LinearLayoutManager(mContext)
        val detailsOrderPresenter = DetailsOrderPresenter(mContext, order_idValue, this)
        detailsOrderPresenter.getDetailsOrderData()


    }
}