package com.fanc.wheretoplay.presenter;

/**
 * Created by admin on 2017/11/3.
 */

import android.content.Context;
import android.widget.Toast;


import okhttp3.MultipartBody;


import com.fanc.wheretoplay.base.BaseFragment;

import com.fanc.wheretoplay.datamodel.BookListModel;


import com.fanc.wheretoplay.rx.DisposableSubscriber2;
import com.fanc.wheretoplay.rx.FlowableTransformer2;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.view.OrderListFragmentView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;


/**
 * Created by DELL on 2017/6/13.
 */

public class OrdelListFragmentPresenter implements BasePresenter {
    public Context context;
    public OrderListFragmentView orderListFragmentView;

    PullToRefreshLayout ptrlPayReserve;

    public BaseFragment baseFragment;


    public OrdelListFragmentPresenter(Context context, OrderListFragmentView orderListFragmentView, PullToRefreshLayout ptrlPayReserve, BaseFragment baseFragment) {

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

    }


    public void getOrdelListData(String typeValue, final int currentPageValue, final String action) {

        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(context).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("page", currentPageValue + "");
        MultipartBody.Part requestFileD =
                MultipartBody.Part.createFormData("size", "10");

        MultipartBody.Part requestFileB =
                MultipartBody.Part.createFormData("type", typeValue);


        Retrofit_RequestUtils.getRequest()
                .bookList(requestFileA, requestFileC, requestFileB, requestFileD)
                .compose(new FlowableTransformer2<BookListModel.ContentBean>())
                .subscribe(new DisposableSubscriber2<BookListModel.ContentBean>() {
                    @Override
                    protected void successful(BookListModel.ContentBean content) {
                        if (action.equals("onRefresh")) {
                            ptrlPayReserve.refreshFinish(0);
                        } else {
                            ptrlPayReserve.loadmoreFinish(0);
                        }
                        orderListFragmentView.setOrderListFragmentData(content, action);
                    }

                    @Override
                    public void failed(String t) {
                        Toast.makeText(context, t, Toast.LENGTH_SHORT).show();
                        if (action.equals("onRefresh")) {
                            ptrlPayReserve.refreshFinish(5);
                        } else {
                            ptrlPayReserve.loadmoreFinish(5);
                        }
                    }
                });


    }


}
