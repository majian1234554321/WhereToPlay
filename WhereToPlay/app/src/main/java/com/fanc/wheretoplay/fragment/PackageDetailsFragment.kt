package com.fanc.wheretoplay.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.adapter.PackageAdapter
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.OrderDetailModel
import com.fanc.wheretoplay.datamodel.PackageModel
import com.fanc.wheretoplay.presenter.DetailsOrderPresenter
import com.fanc.wheretoplay.util.DateFormatUtil
import com.fanc.wheretoplay.view.DetailsOrderView
import kotlinx.android.synthetic.main.packagedetailsfragment.*
import android.graphics.Color
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.SpannableStringBuilder
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.uuzuche.lib_zxing.activity.CodeUtils


@SuppressLint("ValidFragment")
/**
 *
 * @author admin
 * @date 2017/12/22
 */
class PackageDetailsFragment(val order_idValue: String?, val order_functionValue: String) : BaseFragment(), DetailsOrderView, View.OnClickListener {
    var phoneNum: String? = null
    var address: String? = null
    var discountValue: String? = null
    var storeName: String? = null
    var storeid: String? = null
    var idValue: String? = null
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
            R.id.rl -> {
                val intent = Intent()
                intent.setClass(context, DisplayActivity::class.java)
                if ("6" == order_functionValue) {
                    intent.putExtra("DISPLAYTYPE", "PackageFragment")

                } else {
                    intent.putExtra("DISPLAYTYPE", "PackageKTVFragment")


                    intent.putExtra("date", DateFormatUtil.getDD())
                    intent.putExtra("desc", tv003.text.toString().trim())
                    intent.putExtra("weekType", DateFormatUtil.getWeek())

                }

                intent.putExtra("idValue", idValue)
                intent.putExtra("storeIdValue", storeid)

                intent.putExtra("discountValue", discountValue)
                intent.putExtra("storeName", storeName)
                intent.putExtra("address", address)
                intent.putExtra("phonevalue", phoneNum)
                mContext.startActivity(intent)
            }
        }
    }

    override fun setDetailsOrderViewData(contentBean: OrderDetailModel.ContentBean?) {

        address = contentBean?.address
        discountValue = contentBean?.discount
        storeName = contentBean?.store_name
        storeid = contentBean?.store_id
        if ("6" == order_functionValue) {
            idValue = contentBean?.package_detail?.id
            tv_000.text = "套餐详情"
            lll.visibility = View.GONE
        } else {
            idValue = contentBean?.package_id
            tv_000.text = "优惠预订详情"
            lll.visibility = View.VISIBLE
            tv001.text = contentBean?.book_start_date.plus(" "+contentBean?.book_start_time_desc).plus("    (7小时)")

            tv003.text = contentBean?.package_detail?.room_name
        }

        val qrCodeText = com.fanc.wheretoplay.network.Network.QRCODE + storeid + "/order/" + order_idValue
        val mBitmap = CodeUtils.createImage(qrCodeText, 400, 400, BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
        iv_qrcode.setImageBitmap(mBitmap)



        Glide.with(mContext).load(contentBean?.package_detail?.pic_list?.get(0)).placeholder(R.drawable.default_square).into(iv001)
        tv_storeName00.text = contentBean?.store_name
        tv_desc.text = contentBean?.package_detail?.introduce
        tv_realMoney.text = "￥" + contentBean?.package_detail?.origin_price
        tv_endtime.text = "有效期至：" + DateFormatUtil.stampToDate(contentBean?.package_end_time)
        tv_orderNumber.text = "订  单   号：" + contentBean?.order_sn
        tv_phone.text = "手  机   号：" + contentBean?.mobile
        tv_payTime.text = "付款时间：".plus(DateFormatUtil.stampToDate(contentBean?.finish_time))
        tv_realMoney02.text = "订单总价：" + "￥" + contentBean?.package_detail?.origin_price + "元"


        val style1 = SpannableStringBuilder("实际付款：" + "￥" + contentBean?.package_detail?.discount_price + "元")
        style1.setSpan(ForegroundColorSpan(Color.parseColor("#C4483C")), 5, style1.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        tv_realMoney03.text = style1


        tv_storeName2.text = contentBean?.store_name
        tv_address.text = contentBean?.address

        phoneNum = contentBean?.phone

        tv_falseMoney1.text = contentBean?.package_detail?.origin_price + "元"
        tv_realMoney1.text = contentBean?.package_detail?.discount_price + "元"
        tv_detail.text = contentBean?.package_detail?.detail
        piv1.setTopText("有效期")
        piv11.setTopText("除外日期")
        piv2.setTopText("预约信息")
        piv3.setTopText("规则提醒")
        piv4.setTopText("温馨提示")

        piv1.setButtomText(contentBean?.package_detail?.buy_notice?.effect_date)
        piv11.setButtomText(contentBean?.package_detail?.buy_notice?.effect_date_desc)
        piv2.setButtomText(contentBean?.package_detail?.buy_notice?.booking_info)
        piv3.setButtomText(contentBean?.package_detail?.buy_notice?.rule_remind?.replace("\r", "\n"))
        piv4.setButtomText(contentBean?.package_detail?.buy_notice?.tip)


        val lists = arrayListOf<PackageModel.ContentBean.ProductListBean>()

        val list = contentBean?.package_detail?.product_list
        list?.forEachIndexed { _, productListBean ->
            val model = PackageModel.ContentBean.ProductListBean(productListBean.name, productListBean.price, productListBean.num, productListBean.unit)
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
        rl.setOnClickListener(this)


    }
}