package com.fanc.wheretoplay.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.datamodel.Content;
import com.fanc.wheretoplay.datamodel.OrderPayoffModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.view.PayView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @author admin
 * @date 2018/1/15
 */

public class PayPresenter implements BasePresenter {
    public PayView payView;
    public BaseActivity activity;

    public PayPresenter(BaseActivity activity, PayView payView) {
        this.activity = activity;
        this.payView = payView;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void payOff(String token, String order_id, String money, String type, String remain, String display_balance, String fee, String coupon_id,String password) {


        List<MultipartBody.Part> lists = new ArrayList<>();
        lists.add(MultipartBody.Part.createFormData("token", token));
        lists.add(MultipartBody.Part.createFormData("order_id", order_id));
        lists.add(MultipartBody.Part.createFormData("money", money));
        lists.add(MultipartBody.Part.createFormData("type", type));
        lists.add(MultipartBody.Part.createFormData("remain", remain));
        lists.add(MultipartBody.Part.createFormData("display_balance", display_balance));

        lists.add(MultipartBody.Part.createFormData("code", password));

        lists.add(MultipartBody.Part.createFormData("fee", fee));
        if (!TextUtils.isEmpty(coupon_id)) {
            lists.add(MultipartBody.Part.createFormData("coupon_id", coupon_id));
        }


        Retrofit_RequestUtils.getRequest()
                .order_payoff(lists)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderPayoffModel>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(OrderPayoffModel orderPayoffModel) {
                        if ("0".equals(orderPayoffModel.code)) {
                            payView.setPayOffData(orderPayoffModel.content);
                        } else {
                            Toast.makeText(activity, orderPayoffModel.message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        activity.closeProgress();
                        Toast.makeText(activity, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
