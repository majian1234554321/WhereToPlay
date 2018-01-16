package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.PayBillActivity
import com.fanc.wheretoplay.activity.PayPayActivity
import com.fanc.wheretoplay.datamodel.BookListModel
import com.fanc.wheretoplay.datamodel.CancleOrderModel
import com.fanc.wheretoplay.rx.BaseResponseModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.rx.RxBus
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.DateFormatUtil
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.util.ToastUtils
import com.fanc.wheretoplay.view.AlertDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.main2adapter.view.*
import okhttp3.MultipartBody


/**
 *
 * @author admin
 * @date 2018/1/16
 */
class Main2Adapter(val mContext: Context, private val lists: ArrayList<BookListModel.ContentBean.ListBean>) : RecyclerView.Adapter<Main2Adapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        with(holder?.itemView!!) {
            tv_storeName.text = lists[position].name

            if (lists[position].discount.isNotEmpty()) {
                val text = SpannableString(lists[position].discount + "折")
                text.setSpan(TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                text.setSpan(TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length - 1, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                tv_discount.setText(text, TextView.BufferType.SPANNABLE)
                tv_discount.visibility = View.VISIBLE
            } else {
                tv_discount.visibility = View.GONE
            }

            tv_room.text = lists[position].room_type.plus(lists[position].room_number)

            tv_name.text = lists[position].order_name
            tv_phone.text = lists[position].order_mobile
            val time = if (!TextUtils.isEmpty(DateFormatUtil.stampToDate(lists[position].arrival_time))) DateFormatUtil.stampToDate(lists[position].arrival_time) + "前" else ""

            tv_arrTime.text = "到店时间 ".plus(time)




            if (lists[position].order_function != null) {
                when (lists[position].order_function) {
                    "1" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.yufu)
                        /*  holder.tvPayItemTitle.setText("预订类型：预付预订")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].prepay)*/
                    }
                    "2" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.xinyong)
                        /*  holder.tvPayItemTitle.setText("预订类型：信用预订")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/
                    }
                    "3" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.zhifu)
                        /* holder.tvPayItemTitle.setText("预订类型：充值")
                         holder.tvPayItemPrice.setText("总价：" + lists[position].total)
                         holder.tvPayItemTitle.setText("预订类型：结单支付")
                         holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/
                    }
                    "5" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.zhifu)
                        /*  holder.tvPayItemTitle.setText("预订类型：结单支付")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/
                    }

                    "6" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.taocan)
                        /* holder.tvPayItemTitle.setText("预订类型：套餐")
                         holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/
                    }
                    "7" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.youhui)
                        /*  holder.tvPayItemTitle.setText("预订类型：优惠预订")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/
                    }
                    else -> {
                        /*   holder.tvPayItemTitle.setText("预订类型：...")
                           holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/
                    }
                }
            }



            btn_reserve_list_item_buy.setOnClickListener {
                pay(position)
            }


            btn_reserve_list_item_cancel.setOnClickListener {
                AlertDialog(mContext)
                        .setTitle("提示")
                        .setContent("确定取消订单吗")
                        .setBtnOnClickListener(object : AlertDialog.OnBtnClickListener {
                            override fun onBtnClick(view: View, input: String) {
                                val requestFileA = MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken())

                                val requestFileC = MultipartBody.Part.createFormData("id", lists[position].order_id)

                                Retrofit_RequestUtils.getRequest().cancle_order(requestFileA, requestFileC)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(object : Observer<BaseResponseModel<CancleOrderModel.ContentBean>> {

                                            override fun onSubscribe(disposable: Disposable) {

                                            }

                                            override fun onNext(contentBeanBaseResponseModel: BaseResponseModel<CancleOrderModel.ContentBean>) {
                                                if (contentBeanBaseResponseModel.success() && contentBeanBaseResponseModel.content.is_cancle) {
                                                    ToastUtils.showShortToast(context, "取消订单成功")
                                                    lists.removeAt(position)
                                                    notifyDataSetChanged()

                                                } else {
                                                    ToastUtils.showShortToast(context, "取消订单失败")
                                                }
                                            }

                                            override fun onError(throwable: Throwable) {
                                                ToastUtils.showShortToast(context, throwable.toString())
                                            }

                                            override fun onComplete() {

                                            }
                                        })


                            }
                        })
                        .setCanceledOnTouchOutside(true)
                        .show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(View.inflate(mContext, R.layout.main2adapter, null))

    override fun getItemCount(): Int = if (lists.size > 0) {
        lists.size
    } else 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun append(list: ArrayList<BookListModel.ContentBean.ListBean>) {
        lists.addAll(list)
        notifyDataSetChanged()
    }


    private fun pay(position: Int) {
        val intent = Intent()


        when (lists[position].order_function) {
            "1" -> {
                intent.setClass(mContext, PayBillActivity::class.java)
                intent.putExtra("pay_Action", "预订类型：预付预订")
                intent.putExtra("money", lists[position].prepay)
                intent.putExtra("order_id", lists[position].order_id)
            }
            "5" -> {
                intent.setClass(mContext, PayBillActivity::class.java)
                intent.putExtra("pay_Action", "预订类型：结单支付")
                intent.putExtra("money", lists[position].total)
                intent.putExtra("order_id", lists[position].order_id)
            }
            "6" -> {
                intent.setClass(mContext, PayPayActivity::class.java)
                intent.putExtra("type", "套餐详情")
                intent.putExtra("storeIdValue", lists[position].store_id)

                intent.putExtra("value0", lists[position].name)
                intent.putExtra("value1", lists[position].name)
                intent.putExtra("value2", lists[position].package_introduce)
                intent.putExtra("value3", DateFormatUtil.stampToDate(lists[position].finish_time))
                intent.putExtra("value4", lists[position].total)
                intent.putExtra("value5", lists[position].origin_price)
                intent.putExtra("order_id", lists[position].order_id)

                intent.putExtra("discountValue", lists[position].discount)
            }
            else//优惠预订
            -> {
                intent.setClass(mContext, PayPayActivity::class.java)
                intent.putExtra("type", "优惠预订")
                intent.putExtra("storeIdValue", lists[position].store_id)

                intent.putExtra("value0", lists[position].name)
                intent.putExtra("value1", lists[position].name)
                intent.putExtra("value2", lists[position].package_introduce)
                intent.putExtra("value3", DateFormatUtil.stampToDate(lists[position].finish_time))
                intent.putExtra("value4", lists[position].total)
                intent.putExtra("value5", lists[position].origin_price)
                intent.putExtra("order_id", lists[position].order_id)


                intent.putExtra("discountValue", lists[position].discount)
            }
        }


        intent.putExtra("address", lists[position].address)
        intent.putExtra(Constants.STORE_ID, lists[position].store_id)
        intent.putExtra("storeName", lists[position].name)
        intent.putExtra("displayMoney", lists[position].total)
        intent.putExtra("discount", lists[position].discount)
        intent.putExtra(Constants.PAGE, "商家详情支付")

        mContext.startActivity(intent)


    }

}