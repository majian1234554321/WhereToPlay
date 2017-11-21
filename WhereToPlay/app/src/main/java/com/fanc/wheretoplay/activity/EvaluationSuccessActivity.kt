package com.fanc.wheretoplay.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fanc.wheretoplay.MainActivity
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.EvaluationSuccessAdapter
import com.fanc.wheretoplay.base.BaseActivity
import com.fanc.wheretoplay.datamodel.StoreDetailModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils

import kotlinx.android.synthetic.main.activity_evaluation_success.*
import okhttp3.MultipartBody
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EvaluationSuccessActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.menu_iv_lefticon -> {

                finish()
            }
            R.id.tv_back -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_evaluation_success)
        tv_title.text = "评价成功"

        val idValue = intent.getStringExtra("store_id")

        menu_iv_lefticon.setOnClickListener(this)
        tv_back.setOnClickListener(this)


        recycle.layoutManager = LinearLayoutManager(this)

        loadData(idValue)


    }

    private fun loadData(idValue: String?) {

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val requestFileA = MultipartBody.Part.createFormData("id", idValue)

        val subscription = Retrofit_RequestUtils.getRequest()
                .storeDetail(requestFileA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


                .subscribe(object : Subscriber<StoreDetailModel>() {
                    override fun onCompleted() {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(t: StoreDetailModel?) {
                        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                        val list = t?.content?.store?.list
                        val evaluationSuccessAdapter = EvaluationSuccessAdapter(list!!)
                        recycle.adapter = evaluationSuccessAdapter

                    }

                    override fun onError(e: Throwable?) {
                        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                    }

                })

        compositeSubscription.add(subscription)


    }


}
