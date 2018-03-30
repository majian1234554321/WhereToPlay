package com.fanc.wheretoplay.presenter

import android.content.Context
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.view.BGirlFragmentView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

/**
 * Created by Joenny on 2018/3/22.
 */
class BGirlFragmentPresent(val context: Context, val bGirlFragmentView: BGirlFragmentView, val appid: String) {


    fun emplApplicationStatus(type: String) {

        var observable: Observable<AccessOrderIdModel>? = null
        when (type) {
            "emplApplicationStatus" -> {
                observable = Retrofit_RequestUtils.getRequest()
                        .emplApplicationStatus(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
            }

            "emplYearReviewStatus" -> {
                observable = Retrofit_RequestUtils.getRequest()
                        .emplYearReviewStatus(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken())
                                , MultipartBody.Part.createFormData("application_id", appid)
                        )
            }


            "emplPatchCardStatus" -> {
                observable = Retrofit_RequestUtils.getRequest()
                        .emplPatchCardStatus(MultipartBody.Part.createFormData("application_id", SPUtils(context).getUser().getToken())
                                , MultipartBody.Part.createFormData("application_id", appid)
                        )
            }

            else -> {
            }
        }

        observable
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<AccessOrderIdModel> {
                    override fun onComplete() = Unit

                    override fun onSubscribe(d: Disposable) = Unit

                    override fun onNext(t: AccessOrderIdModel) {
                        if (t.code == "0") {
                            bGirlFragmentView.setSuccessData(type, t.content, appid)
                        } else {
                            bGirlFragmentView.setFailedData(t.message)
                        }
                    }

                    override fun onError(e: Throwable) {
                        bGirlFragmentView.setFailedData(e.toString())
                    }

                })
    }
}