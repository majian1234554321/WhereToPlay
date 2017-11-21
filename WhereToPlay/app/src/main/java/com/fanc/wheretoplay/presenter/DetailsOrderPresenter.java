package com.fanc.wheretoplay.presenter;

import android.content.Context;

import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.datamodel.OrderDetailModel;
import com.fanc.wheretoplay.rx.BaseResponseModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.DetailsOrderView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;


/**
 * Created by admin on 2017/11/15.
 */

public class DetailsOrderPresenter implements BasePresenter {
    public Context mContext;
    public String order_idValue;
    public DetailsOrderView detailsOrderView ;
    private final CompositeDisposable compositeSubscription;

    public DetailsOrderPresenter(Context mContext, String order_idValue,DetailsOrderView detailsOrderView) {
        this.mContext = mContext;
        this.order_idValue = order_idValue;
        this.detailsOrderView = detailsOrderView;
        compositeSubscription = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeSubscription.clear();
    }

    public void getDetailsOrderData() {
        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("order_id", order_idValue);
        Retrofit_RequestUtils.getRequest()
                .orderDetail(requestFileA, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseModel<OrderDetailModel.ContentBean>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(BaseResponseModel<OrderDetailModel.ContentBean> contentBeanBaseResponseModel) {
                        detailsOrderView.setDetailsOrderViewData(contentBeanBaseResponseModel.content);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }


                });

    }


    public void  cancelOrder (){
        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("id", order_idValue);
       Retrofit_RequestUtils.getRequest().cancle_order(requestFileA, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseModel<CancleOrderModel.ContentBean>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.showShortToast(mContext, throwable.toString());
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(BaseResponseModel<CancleOrderModel.ContentBean> contentBean) {
                        if (contentBean.success() && contentBean.content.is_cancle) {
                           detailsOrderView.cancelOrderAction();
                        } else {
                            ToastUtils.showShortToast(mContext, "取消订单失败");
                        }
                    }
                });

    }

}
