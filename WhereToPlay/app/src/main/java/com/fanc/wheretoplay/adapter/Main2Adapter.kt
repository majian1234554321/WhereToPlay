package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.*
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
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.main2adapter.view.*
import okhttp3.MultipartBody
import java.util.concurrent.TimeUnit


/**
 *
 * @author admin
 * @date 2018/1/16
 */
class Main2Adapter(val mContext: Context, private val lists: ArrayList<BookListModel.ContentBean.ListBean>) : RecyclerView.Adapter<Main2Adapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

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
            //if (lists[position].room_type != null)
            tv_room.text = lists[position].room_type?.plus(lists[position].room_number)

            tv_name.text = lists[position].order_name
            tv_phone.text = lists[position].order_mobile
            tv_reserve_list_item_state.text = lists[position].statusdesc
            val time = if (!TextUtils.isEmpty(DateFormatUtil.stampToDate3(lists[position].arrival_time))) DateFormatUtil.stampToDate3(lists[position].arrival_time) + "前" else ""

            tv_arrTime.text = "到店时间 ".plus(time)



            var statues = ""

            if (lists[position].order_function != null) {
                when (lists[position].order_function) {
                    "1" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.yufu)
                        /*  holder.tvPayItemTitle.setText("预订类型：预付预订")


                          holder.tvPayItemPrice.setText("总价：" + lists[position].prepay)*/
                        statues = "预订类型：预付预订"
                    }
                    "2" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.xinyong)
                        /*  holder.tvPayItemTitle.setText("预订类型：信用预订")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/

                        statues = "预订类型：信用预订"
                    }
                    "3" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.zhifu)
                        /* holder.tvPayItemTitle.setText("预订类型：充值")
                         holder.tvPayItemPrice.setText("总价：" + lists[position].total)
                         holder.tvPayItemTitle.setText("预订类型：结单支付")
                         holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/

                        statues = "预订类型：充值"

                    }
                    "5" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.zhifu)
                        /*  holder.tvPayItemTitle.setText("预订类型：结单支付")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/

                        statues = "预订类型：结单支付"

                    }

                    "6" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.taocan)
                        /* holder.tvPayItemTitle.setText("预订类型：套餐")
                         holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/

                        statues = "预订类型：套餐"
                    }
                    "7" -> {
                        iv_reserve_list_item_way.setBackgroundResource(R.drawable.youhui)
                        /*  holder.tvPayItemTitle.setText("预订类型：优惠预订")
                          holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/

                        statues = "预订类型：优惠预订"
                    }
                    else -> {
                        /*   holder.tvPayItemTitle.setText("预订类型：...")
                           holder.tvPayItemPrice.setText("总价：" + lists[position].total)*/

                        statues = "预订类型：..."
                    }
                }
            }


            val listss = mutableListOf<Button>()

            listss.add(btn0)
            listss.add(btn1)
            listss.add(btn2)
            listss.add(btn3)



            lists[position].buttonlist.forEachIndexed { index, buttonlistBean ->
                listss[index].text = buttonlistBean.title
                listss[index].visibility = View.VISIBLE

                listss[index].setOnClickListener({


                    val intent = Intent()
                    when (buttonlistBean.buttonid) {

                        "0" -> {
                            AlertDialog(mContext)
                                    .setTitle("提示")
                                    .setContent("确定取消订单吗")
                                    .setBtnOnClickListener(object : AlertDialog.OnBtnClickListener {
                                        override fun onBtnClick(view: View, input: String) {
                                            val requestFileA = MultipartBody.Part.createFormData("token", SPUtils(context).user.token)

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
                                                               // lists.removeAt(position)
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

                        "1" -> {
                            pay(position)
                        }
                        "2" -> {

                            intent.putExtra("flag", "预订支付")
                            intent.putExtra("order_id", lists.get(position).order_id)
                            intent.putExtra("store_name", lists.get(position).name)

                            intent.putExtra("pay_type", "1")
                            intent.putExtra("pay_Action", "转预付")
                            intent.putExtra("address", lists.get(position).address)
                            intent.putExtra("arrival_time", lists.get(position).arrival_time)
                            intent.putExtra("prepay", lists.get(position).book_price)


                            intent.setClass(context, DownPaymentActivity::class.java)
                            mContext.startActivity(intent)
                        }
                        "3" -> {
                            intent.putExtra("store_name", lists.get(position).name)
                            intent.putExtra("address", lists.get(position).address)
                            intent.putExtra("order_id", lists.get(position).order_id)
                            intent.putExtra("store_id", lists.get(position).store_id)
                            intent.putExtra("discount", lists.get(position).discount)
                            intent.putExtra("address", lists.get(position).address)

                            intent.putExtra("total", lists.get(position).total)
                            if (lists.get(position).order_action != null) {
                                intent.putExtra("status", lists.get(position).order_action)
                            }
                            intent.setClass(context, PublicationEvaluationActivity::class.java)
                            mContext.startActivity(intent)
                        }
                        "4" -> {

                            intent.putExtra("discount", lists.get(position).discount)
                            intent.putExtra("order_id", lists.get(position).order_id)
                            intent.putExtra("store_id", lists.get(position).store_id)
                            intent.putExtra("storeName", lists.get(position).name)
                            intent.putExtra("total", lists.get(position).total)
                            intent.putExtra("address", lists.get(position).address)
                            intent.putExtra("image", lists.get(position).cover)
                             intent.putExtra("statues", statues)
                            if (lists.get(position).order_action != null) {
                                intent.putExtra("status", lists.get(position).order_action)
                            }
                            if ("6" == lists.get(position).order_function || "7" == lists.get(position).order_function) {
                                intent.putExtra("DISPLAYTYPE", "PackageDetailsFragment")

                                intent.putExtra("order_function", lists.get(position).order_function)


                                intent.setClass(context, DisplayActivity::class.java)
                            } else {
                                intent.setClass(context, DetailsOrderActivity::class.java)
                            }

                            mContext.startActivity(intent)

                        }
                        "5" -> {

                        }
                        "6" -> {


                        }
                        "7" -> {
                            intent.setClass(context, PayBillActivity::class.java)


                            if ("预订类型：预付预订" == statues) {


                                intent.putExtra("money211", lists.get(position).origin_prepay)
                                intent.putExtra("money", lists.get(position).prepay)
                            } else {
                                intent.putExtra("displayMoney", lists.get(position).total)
                            }


                            intent.putExtra(Constants.STORE_ID, lists.get(position).store_id)

                            intent.putExtra("storeName", lists.get(position).name)

                            intent.putExtra("discount", lists.get(position).discount)
                            intent.putExtra("order_id", lists.get(position).order_id)
                            intent.putExtra("address", lists.get(position).address)
                            intent.putExtra(Constants.PAGE, "支付再支付")
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)

                        }
                        "8" -> {
                            intent.putExtra("DISPLAYTYPE", "CancelOrderFragment")
                            intent.putExtra("order_id", lists.get(position).order_id)
                            intent.putExtra("storeName", lists.get(position).name)
                            intent.putExtra("image", lists.get(position).cover)
                            intent.putExtra("book_sn", lists.get(position).book_sn)
                            intent.putExtra("room_type", lists.get(position).room_type)
                            intent.putExtra("total", lists.get(position).total)
                            intent.putExtra("statues", statues)


                            intent.setClass(context, DisplayActivity::class.java)
                            mContext.startActivity(intent)
                        }


                        else -> {
                        }
                    }
                })
            }




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(View.inflate(mContext, R.layout.main2adapter, null))

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