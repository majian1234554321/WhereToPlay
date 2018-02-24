package com.fanc.wheretoplay.presenter

import android.content.Context
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.view.CancelOrderView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

/**
 *
 * @author admin
 * @date 2018/2/23
 */
class CancelOrderPresent(val context: Context,val cancelOrderView: CancelOrderView) : BasePresenter {
    override fun subscribe() = Unit

    override fun unsubscribe() = Unit

    fun cancelOrder(order_idValue: String?, cancle_reason: String?) {

        val requestFileA = MultipartBody.Part.createFormData("cancle_reason", cancle_reason)
        val requestFileB = MultipartBody.Part.createFormData("order_id", order_idValue)

        val requestFileC = MultipartBody.Part.createFormData("token", SPUtils(context).user.token)

        val list = arrayListOf<MultipartBody.Part>()
        list.add(requestFileA)
        list.add(requestFileB)
        list.add(requestFileC)

        Retrofit_RequestUtils.getRequest()
                .submitCancleOrder(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<AccessOrderIdModel> {
                    override fun onComplete() = Unit

                    override fun onSubscribe(p0: Disposable) {
                        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(p0: AccessOrderIdModel) =
                            if (p0.code != null && "0" == p0.code) {
                                cancelOrderView.success()
                            } else {
                                cancelOrderView.failed(p0.message)
                            }

                    override fun onError(p0: Throwable) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        cancelOrderView.failed(p0.toString())
                    }


                })

    }
}