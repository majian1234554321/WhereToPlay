package com.fanc.wheretoplay.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.Extend0Adapter
import com.fanc.wheretoplay.adapter.ExtendAdapter

import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.BookPackageListModel
import com.fanc.wheretoplay.datamodel.MerchantDetailModel
import com.fanc.wheretoplay.divider.RecycleViewDivider
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.DateFormatUtil
import com.fanc.wheretoplay.util.UIUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.extendfragment.*
import okhttp3.MultipartBody
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author admin
 * @date 2017/12/24
 */
@SuppressLint("ValidFragment")
class ExtendFragment(
        val packageX: MerchantDetailModel.ContentBean.StoreBean.PackageBean?, val type: String
        , val storeid: String, val discountValue: String, val storeName: String, val address: String, val phonevalue: String
) : BaseFragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    var dateType: String = ""
    val mDate = (0..6).map { Date(System.currentTimeMillis() + it * DAY) }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        var weekType: String = ""

        when (p1) {

            R.id.rb1 -> {
                weekType = rb1.text.substring(rb1.text.lastIndex)
                dateType = rb1.text.split("\n")[0]
            }
            R.id.rb2 -> {
                weekType = rb2.text.substring(rb2.text.lastIndex)
                dateType = rb2.text.split("\n")[0]
            }
            R.id.rb3 -> {
                weekType = rb3.text.substring(rb3.text.lastIndex)
                dateType = rb3.text.split("\n")[0]
            }
            R.id.rb4 -> {
                weekType = rb4.text.substring(rb4.text.lastIndex)
                dateType = rb4.text.split("\n")[0]
            }
            R.id.rb5 -> {
                weekType = rb5.text.substring(rb5.text.lastIndex)
                dateType = rb5.text.split("\n")[0]
            }
            R.id.rb6 -> {
                weekType = rb6.text.substring(rb6.text.lastIndex)
                dateType = rb6.text.split("\n")[0]
            }
            R.id.rb7 -> {
                weekType = rb7.text.substring(rb7.text.lastIndex)
                dateType = rb7.text.split("\n")[0]
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
        loadKTVData(weekType)
    }


    lateinit var list: MutableList<MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean>


    lateinit var adapter: ExtendAdapter

    var adapter0: Extend0Adapter? = null

    var flag: Boolean = false

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_more -> {
                adapter.change(!flag)
                adapter.notifyDataSetChanged()
                if (flag) {
                    tv_more.text = "更多".plus(list.size.minus(2)).plus("个套餐 ")
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
            val weekType: String = DateFormatUtil.getCustomDayOfWeek(mDate[0]).substring(2)
            dateType = DateFormatUtil.getCustomDay(mDate[0]).toString()
            loadKTVData(weekType)
        } else {//酒吧
            ll00.visibility = View.GONE
        }



        if (packageX != null) {

            if (!TextUtils.isEmpty(packageX.count.toString())) {
                tv_title.text = "套餐(" + packageX.count + ")"
            }

            list = packageX.pkg_list as MutableList<MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean>
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
                tv_more.text = "更多".plus(list.size.minus(2)).plus("个套餐 ")
            } else {
                tv_more.visibility = View.GONE
            }
        }else {
            tv_title.visibility = View.GONE
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


    }

    private fun loadKTVData(weekType: String) {
        val requestFileA = MultipartBody.Part.createFormData("token", mUser.token)
        val requestFileB = MultipartBody.Part.createFormData("store_id", storeid)
        val requestFilec = MultipartBody.Part.createFormData("week_day", weekType)

        val list = arrayListOf<MultipartBody.Part>()
        list.add(requestFileA)
        list.add(requestFileB)
        list.add(requestFilec)

        Retrofit_RequestUtils
                .getRequest()
                .bookPackageList(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookPackageListModel> {
                    override fun onSubscribe(p0: Disposable) {

                    }

                    override fun onError(p0: Throwable) {
                        Toast.makeText(mContext, p0.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onNext(p0: BookPackageListModel) {
                        if (p0.content.isNotEmpty()) {
                            rb21.text = p0.content[0].room_name
                            adapter0 = Extend0Adapter(mContext, flag, p0.content[0].package_list as ArrayList<BookPackageListModel.ContentBean.PackageListBean>
                                    , storeid, discountValue, storeName, address, phonevalue, dateType, p0.content[0].room_name, weekType)
                            recycle0.adapter = adapter0
                        } else {
                            adapter0?.clearAll()
                            rb21.visibility = View.GONE
                            // Toast.makeText(mContext, "", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onComplete() {

                    }

                })
    }
}
