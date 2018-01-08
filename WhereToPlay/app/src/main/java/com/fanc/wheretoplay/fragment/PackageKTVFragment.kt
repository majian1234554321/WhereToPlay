package com.fanc.wheretoplay.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Color.*
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.PayPayActivity
import com.fanc.wheretoplay.adapter.PackageAdapter
import com.fanc.wheretoplay.adapter.PackageDateAdapter
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.BookPackageDetailModel
import com.fanc.wheretoplay.datamodel.PackageModel
import com.fanc.wheretoplay.rx.DisposableSubscriber2
import com.fanc.wheretoplay.rx.FlowableTransformer2
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.DateFormatUtil


import kotlinx.android.synthetic.main.packagektvfragment.*
import okhttp3.MultipartBody

@SuppressLint("ValidFragment")
/**
 *
 * @author admin
 * @date 2017/12/23
 */

class PackageKTVFragment(private val idValue: String, private val storeIdValue: String, val discountValue: String,
                         val storeName: String, val address: String, val phonevalue: String
                         , val dateValue: String?,
                         val descValue: String?,
                         val weekValue: String?) : BaseFragment(), View.OnClickListener, AdapterView.OnItemClickListener {

    var adapter: PackageDateAdapter? = null
    var listTime: List<String>? = null

    var stamp: String? = null
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        adapter?.action(p2)


        setTextValue(listTime?.get(p2).toString())

        val dateValues = if (dateValue!!.length > 1) {
            dateValue
        } else {
            "0".plus(dateValue)
        }
        //yyyy-MM-dd HH:mm:ss

         stamp = DateFormatUtil.dateToStamp2(DateFormatUtil.getYY() + "-" + DateFormatUtil.getMM() + "-" + dateValues + " " + listTime?.get(p2).toString()+ ":00")


    }

    fun setTextValue(value: String) {
        val endTime: String
        when (value) {
            "18:00" -> {
                endTime = "次日00:00"

            }
            "18:30" -> {
                endTime = "次日00:30"
            }
            "19:00" -> {
                endTime = "次日01:00"
            }
            "19:30" -> {
                endTime = "次日01:30"
            }

            else -> {
                endTime = "次日02:00"
            }
        }





        tv_date.text = DateFormatUtil.getMM().toString().plus("月")
                .plus(if (dateValue!!.length > 1) {
                    dateValue
                } else {
                    "0".plus(dateValue)
                })
                .plus("日  ")
                .plus(value).plus("-").plus(endTime).plus("(6小时)")

    }



    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_pay -> {
                val intent = Intent(mContext, PayPayActivity::class.java)
                intent.putExtra("type", "优惠预订")
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







                intent.putExtra("book_week_day", weekValue)
                intent.putExtra("discount_book_hour", "6")
                intent.putExtra("book_start_time", "1515055228")





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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.packagektvfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        tbv.setTv_title("KTV套餐详情")
        piv1.setTopText("有效期")
        piv2.setTopText("预约信息")
        piv3.setTopText("规则提醒")
        piv4.setTopText("温馨提示")
        piv11.setTopText("除外日期")


        tv_desc.text = descValue

        recycle.layoutManager = LinearLayoutManager(mContext)

        myGridView.onItemClickListener = this
        myGridView.selector = ColorDrawable(TRANSPARENT)
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
                .bookPackageDetail(list)
                .compose(FlowableTransformer2())
                .subscribe(object : DisposableSubscriber2<BookPackageDetailModel.ContentBean>() {
                    override fun successful(content: BookPackageDetailModel.ContentBean) {
                        setData(content)
                    }

                    override fun failed(t: String?) {

                        Toast.makeText(context, t, Toast.LENGTH_LONG).show()
                    }
                })
    }


    private fun setData(content: BookPackageDetailModel.ContentBean?) {
        if (content != null) {


            if (content?.pic_list?.size!! > 0) {
                Glide.with(mContext).load(content.pic_list?.get(0)).placeholder(R.drawable.default_rect).into(iv)
            }
            tv_address.text = address
            tv_storeName.text = content.name
            tv_storeName2.text = content.name
            tv_introduce.text = content.introduce
            tv_realMoney.text = content.discount_price
            tv_falseMoney.text = "￥ ".plus(content.origin_price)
            tv_realMoney1.text = content.discount_price + "元"
            tv_falseMoney1.text = content.origin_price + "元"


            if (content.time_list != null) {
                setTextValue(content.time_list[0])


                listTime = content.time_list

                val dateValues = if (dateValue!!.length > 1) {
                    dateValue
                } else {
                    "0".plus(dateValue)
                }
                //yyyy-MM-dd HH:mm:ss

                stamp = DateFormatUtil.dateToStamp2(DateFormatUtil.getYY() + "-" + DateFormatUtil.getMM() + "-" + dateValues + " " + listTime?.get(0).toString()+ ":00")




                adapter = PackageDateAdapter(mContext, content.time_list, 0)
                myGridView.adapter = adapter
            }

            piv1.setButtomText(content.buy_notice?.effect_date)
            piv2.setButtomText(content.buy_notice?.booking_info)
            piv3.setButtomText(content.buy_notice?.rule_remind)

            if (content.buy_notice?.tip != null) {
                piv4.setButtomText(content.buy_notice?.tip?.replace("/r", "." + "\n"))
            }

            piv11.setButtomText(content.buy_notice?.effect_date_desc)
            tv_falseMoney.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

            if (content.detail != null && content.detail.contains("/r")) {
                tv_detail.text = content.detail.replace("/r", "." + "\n")
            } else {
                tv_detail.text = content.detail
            }


            val lists2 = arrayListOf<PackageModel.ContentBean.ProductListBean>()
            content.product_list.forEach { value ->
                lists2.add(PackageModel.ContentBean.ProductListBean(value.name
                        , value.price
                        , value.num
                        , value.unit))
            }


            val adapter2 = PackageAdapter(mContext, lists2)
            recycle.adapter = adapter2
        }
    }

}

