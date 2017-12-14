package com.fanc.wheretoplay.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fanc.wheretoplay.R

import com.fanc.wheretoplay.base.BaseLazyFragment
import com.fanc.wheretoplay.divider.RecycleViewDivider
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_evaluation_success.*
import okhttp3.MultipartBody

/**
 * @author admin
 * @date 2017/12/14
 */

class CouponChild1Fragment : BaseLazyFragment() {
    override fun initPrepare() {

    }

    override fun onInvisible() {

    }

    override fun initData() {

        val requestFileA = MultipartBody.Part.createFormData("token", mUser.token)
        val requestFileB = MultipartBody.Part.createFormData("type", "1")

       val  list =  arrayListOf<MultipartBody.Part>();

//        Retrofit_RequestUtils.getRequest().couponList(list).compose( ObservableTransformer {  })
//                .compose()

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(inflater.context, R.layout.couponchildfragment, null)
        val recycle = view.findViewById<RecyclerView>(R.id.recycle)
        recycle.layoutManager = LinearLayoutManager(inflater.context)
        val viewDivider = RecycleViewDivider(mContext, LinearLayout.HORIZONTAL)
        recycle.addItemDecoration(viewDivider)
        return view
    }
}
