package com.fanc.wheretoplay.presenter

import android.content.Context
import android.widget.Toast
import com.fanc.wheretoplay.adapter.CouponChildAdapter
import com.fanc.wheretoplay.datamodel.CouponListModel
import com.fanc.wheretoplay.rx.DisposableSubscriber2
import com.fanc.wheretoplay.rx.FlowableTransformer2
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import okhttp3.MultipartBody

/**
 * @author admin
 * @date 2017/12/23
 */

class CouponChildPresent(val context: Context, val view: CouponChildView, val token: String, val type: String) : BasePresenter {

    override fun subscribe() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun loadData() {
        val requestFileA = MultipartBody.Part.createFormData("token", token)
        val requestFileB = MultipartBody.Part.createFormData("type", type)

        val list = arrayListOf<MultipartBody.Part>()
        list.add(requestFileA)
        list.add(requestFileB)
        Retrofit_RequestUtils.getRequest().couponList(list)
                .compose(FlowableTransformer2())
                .subscribe(object : DisposableSubscriber2<CouponListModel.ContentBean>() {
                    override fun successful(content: CouponListModel.ContentBean) {

                        view.setCouponChildDate(content)
                    }

                    override fun failed(t: String) {
                        Toast.makeText(context, t, Toast.LENGTH_LONG).show()
                    }
                })
    }


}
