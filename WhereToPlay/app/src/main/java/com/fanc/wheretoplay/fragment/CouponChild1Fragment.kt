package com.fanc.wheretoplay.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.CouponChildAdapter

import com.fanc.wheretoplay.base.BaseLazyFragment
import com.fanc.wheretoplay.datamodel.BookListModel
import com.fanc.wheretoplay.datamodel.CouponListModel
import com.fanc.wheretoplay.datamodel.StoreDetailModel
import com.fanc.wheretoplay.divider.RecycleViewDivider
import com.fanc.wheretoplay.presenter.CouponChildPresent
import com.fanc.wheretoplay.presenter.CouponChildView
import com.fanc.wheretoplay.rx.DisposableSubscriber2
import com.fanc.wheretoplay.rx.FlowableTransformer2
import com.fanc.wheretoplay.rx.ObservableTransformer2
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import io.reactivex.Flowable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.android.synthetic.main.activity_evaluation_success.*
import okhttp3.MultipartBody
import java.util.*

/**
 * @author admin
 * @date 2017/12/14
 */

class CouponChild1Fragment : BaseLazyFragment(), CouponChildView {
    var type = "1"
    override fun setCouponChildDate(content: CouponListModel.ContentBean) {
        val adapter = CouponChildAdapter(mContext, content.list, type)
        recycle?.adapter = adapter
    }

    var recycle: RecyclerView? = null
    override fun initPrepare() {

    }

    override fun onInvisible() {

    }

    override fun initData() {
        CouponChildPresent(mContext, this, mUser.token, type).loadData()

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(inflater.context, R.layout.couponchildfragment, null)
        recycle = view.findViewById<RecyclerView>(R.id.recycle) as RecyclerView
        recycle?.layoutManager = LinearLayoutManager(inflater.context)
        val viewDivider = RecycleViewDivider(mContext, LinearLayout.HORIZONTAL)
        recycle?.addItemDecoration(viewDivider)
        return view
    }
}




