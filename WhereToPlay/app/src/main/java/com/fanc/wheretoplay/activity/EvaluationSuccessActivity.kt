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
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_evaluation_success.*
import okhttp3.MultipartBody


class EvaluationSuccessActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
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
        val titleType = intent.getStringExtra("titleType")
        when (titleType) {
            "支付成功" -> {
                tv_title.text = "支付成功"
                tv001.text = "支付成功"
                tv002.text = "快去店里消费吧～"
            }
            else -> {
                tv_title.text = "评价成功"
                tv001.text = "评价成功"
                tv002.text = "你的评价将是其他用户选择的重要参考!"
            }
        }


        val idValue = intent.getStringExtra("store_id")

        menu_iv_lefticon.setOnClickListener(this)
        tv_back.setOnClickListener(this)


        recycle.layoutManager = LinearLayoutManager(this)

        loadData(idValue)


    }

    private fun loadData(idValue: String?) {

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val requestFileA = MultipartBody.Part.createFormData("id", idValue)

        Retrofit_RequestUtils.getRequest()
                .storeDetail(requestFileA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<StoreDetailModel> {
                    override fun onSubscribe(p0: Disposable) = Unit

                    override fun onError(p0: Throwable) {
                        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(p0: StoreDetailModel) {
                        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        val list = p0.content?.store?.list
                        val evaluationSuccessAdapter = EvaluationSuccessAdapter(list!!)
                        recycle.adapter = evaluationSuccessAdapter
                    }

                    override fun onComplete() = Unit


                })

    }
}
