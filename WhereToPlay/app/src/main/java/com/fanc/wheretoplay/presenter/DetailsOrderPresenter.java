package com.fanc.wheretoplay.presenter;

import android.content.Context;
import android.widget.Toast;

import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.datamodel.OrderDetailModel;
import com.fanc.wheretoplay.rx.BaseResponseModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxHelper;
import com.fanc.wheretoplay.rx.RxSubscribe;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.DetailsOrderView;

import cn.jiguang.api.BasePreferenceManager;
import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 2017/11/15.
 */

public class DetailsOrderPresenter implements BasePresenter {
    public Context mContext;
    public String order_idValue;
    public DetailsOrderView detailsOrderView ;
    private final CompositeSubscription compositeSubscription;

    public DetailsOrderPresenter(Context mContext, String order_idValue,DetailsOrderView detailsOrderView) {
        this.mContext = mContext;
        this.order_idValue = order_idValue;
        this.detailsOrderView = detailsOrderView;
        compositeSubscription = new CompositeSubscription();
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
        Subscription subscription = Retrofit_RequestUtils.getRequest()
                .orderDetail(requestFileA, requestFileC)
                .compose(RxHelper.<OrderDetailModel.ContentBean>handleResult())
                .subscribe(new RxSubscribe<OrderDetailModel.ContentBean>() {
                    @Override
                    protected void _onNext(OrderDetailModel.ContentBean contentBean) {
                        detailsOrderView.setDetailsOrderViewData(contentBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
        compositeSubscription.add(subscription);
    }


    public void  cancelOrder (){
        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("id", order_idValue);
    Subscription subscription =     Retrofit_RequestUtils.getRequest().cancle_order(requestFileA, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponseModel<CancleOrderModel.ContentBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.showShortToast(mContext, throwable.toString());
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
        compositeSubscription.add(subscription);
    }

}
