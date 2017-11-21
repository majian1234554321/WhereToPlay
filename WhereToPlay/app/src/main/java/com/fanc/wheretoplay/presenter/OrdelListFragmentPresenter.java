package com.fanc.wheretoplay.presenter;

/**
 * Created by admin on 2017/11/3.
 */

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;



import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.datamodel.BookListModel;
import com.fanc.wheretoplay.rx.BaseResponseModel;
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

    public BaseFragment baseFragment ;



    public OrdelListFragmentPresenter(Context context, OrderListFragmentView orderListFragmentView,PullToRefreshLayout ptrlPayReserve, BaseFragment baseFragment) {

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
                MultipartBody.Part.createFormData("page", currentPageValue+"");
        MultipartBody.Part requestFileD =
                MultipartBody.Part.createFormData("size", "10");

        MultipartBody.Part requestFileB =
                MultipartBody.Part.createFormData("type", typeValue);



       Retrofit_RequestUtils.getRequest().bookList(requestFileA,requestFileC,requestFileB,requestFileD)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseModel<BookListModel.ContentBean>>(){
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(BaseResponseModel<BookListModel.ContentBean> contentBeanBaseResponseModel) {
                        if (contentBeanBaseResponseModel!=null) {
                            if (action.equals("onRefresh")) {
                                ptrlPayReserve.refreshFinish(0);
                            }else {
                                ptrlPayReserve.loadmoreFinish(0);
                            }


                            orderListFragmentView.setOrderListFragmentData(contentBeanBaseResponseModel.content, action);
                        } else {
                            if (action.equals("onRefresh")) {
                                ptrlPayReserve.refreshFinish(5);
                            }else {
                                ptrlPayReserve.loadmoreFinish(5);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (action.equals("onRefresh")) {
                            ptrlPayReserve.refreshFinish(5);
                        }else {
                            ptrlPayReserve.loadmoreFinish(5);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                });


}


}
