package com.fanc.wheretoplay.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.PayPayActivity
import com.fanc.wheretoplay.adapter.PackageDateAdapter
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.PackageModel
import com.fanc.wheretoplay.rx.DisposableSubscriber2
import com.fanc.wheretoplay.rx.FlowableTransformer2
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils


import kotlinx.android.synthetic.main.packagektvfragment.*
import okhttp3.MultipartBody

@SuppressLint("ValidFragment")
/**
 *
 * @author admin
 * @date 2017/12/23
 */

class PackageKTVFragment(private val idValue: String, private val storeIdValue: String, val discountValue: String,
                         val storeName: String, val address: String, val phonevalue: String) : BaseFragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_pay -> {
                val intent = Intent(mContext, PayPayActivity::class.java)
                intent.putExtra("type", "套餐详情")
                intent.putExtra("storeIdValue", storeIdValue)
                intent.putExtra("packageIdValue", idValue)
                intent.putExtra("value0", tv_storeName.text.toString().trim())
                intent.putExtra("value1", tv_storeName.text.toString().trim())
                intent.putExtra("value2", tv_introduce.text.toString().trim())
                intent.putExtra("value3", piv1.getButtomText())
                intent.putExtra("value4", tv_realMoney.text.toString().trim())
                intent.putExtra("value5", tv_falseMoney.text.toString().trim())

                intent.putExtra("discountValue", discountValue)
                intent.putExtra("storeName", storeName)
                intent.putExtra("address", address)

                startActivity(intent)
            }
            R.id.iv_phone -> {
                val intent2 = Intent(Intent.ACTION_DIAL)

                val uri = Uri.parse("tel:" + if (TextUtils.isEmpty(phonevalue)) "4000051179" else phonevalue)
                intent2.data = uri
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent2)
                } else {
                    Toast.makeText(mContext, "请到设置页面开启拨打电话权限", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    val list = arrayListOf<String>()
    val values = arrayOf("18:00", "18:30", "19:00", "19:30",
            "20:00", "20:30", "21:00", "21:30",
            "22:00", "22:30", "23:00", "23:30",
            "00:00", "00:30", "01:00", "01:30",
            "02:00", "02:30", "03:00", "03:30"
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.packagektvfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.clear()
        values.forEach { value -> list.add(value) }


        tbv.setTv_title("套餐详情")
        piv1.setTopText("有效期")
        piv2.setTopText("预约信息")
        piv3.setTopText("规则提醒")
        piv4.setTopText("温馨提示")
        piv11.setTopText("除外日期")

        recycle.layoutManager = GridLayoutManager(mContext, 5)
        val adapter = PackageDateAdapter(mContext, list)
        recycle.adapter = adapter

        tv_pay.setOnClickListener(this)
        iv_phone.setOnClickListener(this)

        loadData()

    }

    private fun loadData() {
        val requestFileA = MultipartBody.Part.createFormData("token", mUser.token)
        val requestFileB = MultipartBody.Part.createFormData("id", idValue)

        val list = arrayListOf<MultipartBody.Part>()
        list.add(requestFileA)
        list.add(requestFileB)
        Retrofit_RequestUtils.getRequest()
                .packageDetail(list)
                .compose(FlowableTransformer2())
                .subscribe(object : DisposableSubscriber2<PackageModel.ContentBean>() {
                    override fun successful(content: PackageModel.ContentBean?) {
                        setData(content!!)
                    }

                    override fun failed(t: String?) {
                        Toast.makeText(context, t, Toast.LENGTH_LONG).show()
                    }
                })
    }

    private fun setData(content: PackageModel.ContentBean) {
        if (content.pic_list.size > 0) {
            Glide.with(mContext).load(content.pic_list[0]).placeholder(R.drawable.default_rect).into(iv)
        }
        tv_address.text = address
        tv_storeName.text = content.name
        tv_storeName2.text = content.name
        tv_introduce.text = content.introduce
        tv_realMoney.text = content.discount_price
        tv_falseMoney.text = "￥ ".plus(content.origin_price)


        piv1.setButtomText(content.buy_notice?.effect_date)
        piv2.setButtomText(content.buy_notice?.booking_info)
        piv3.setButtomText(content.buy_notice?.rule_remind)
        piv4.setButtomText(content.buy_notice?.tip)

        piv11.setButtomText(content.buy_notice?.effect_date_desc)

        tv_falseMoney.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

        tv_detail.text = content.detail.replace("/n", "\n")


    }


}