package com.fanc.wheretoplay.presenter;

/**
 * Created by admin on 2017/11/3.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.datamodel.BookListModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxHelper;
import com.fanc.wheretoplay.rx.RxSubscribe;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.view.OrderListFragmentView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by DELL on 2017/6/13.
 */

public class OrdelListFragmentPresenter implements BasePresenter {
    public Context context;
    public OrderListFragmentView orderListFragmentView;
    private final CompositeSubscription mSubscriptions;
    PullToRefreshLayout ptrlPayReserve;

    public BaseFragment baseFragment ;



    public OrdelListFragmentPresenter(Context context, OrderListFragmentView orderListFragmentView,PullToRefreshLayout ptrlPayReserve, BaseFragment baseFragment) {
        mSubscriptions = new CompositeSubscription();
        this.context = context;
        this.orderListFragmentView = orderListFragmentView;
        this.ptrlPayReserve = ptrlPayReserve;
        this.baseFragment = baseFragment;


    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }


    public void getOrdelListData(String typeValue, final int currentPageValue, final String action) {

        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(context).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("page", currentPageValue+"");
        MultipartBody.Part requestFileD =
                MultipartBody.Part.createFormData("size", "10");

        MultipartBody.Part requestFileB =
                MultipartBody.Part.createFormData("type", typeValue);



        mSubscriptions.add(Retrofit_RequestUtils.getRequest().bookList(requestFileA,requestFileC,requestFileB,requestFileD)
                .compose(RxHelper.<BookListModel.ContentBean>handleResult())
                .subscribe(new RxSubscribe<BookListModel.ContentBean>() {
                    @Override
                    protected void _onNext(BookListModel.ContentBean contentBean) {
                        if (contentBean!=null) {
                            if (action.equals("onRefresh")) {
                                ptrlPayReserve.refreshFinish(0);
                            }else {
                                ptrlPayReserve.loadmoreFinish(0);
                            }


                            orderListFragmentView.setOrderListFragmentData(contentBean, action);
                        } else {
                            if (action.equals("onRefresh")) {
                                ptrlPayReserve.refreshFinish(5);
                            }else {
                                ptrlPayReserve.loadmoreFinish(5);
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        if (action.equals("onRefresh")) {
                            ptrlPayReserve.refreshFinish(5);
                        }else {
                            ptrlPayReserve.loadmoreFinish(5);
                        }

                    }
                }));
    }




}
