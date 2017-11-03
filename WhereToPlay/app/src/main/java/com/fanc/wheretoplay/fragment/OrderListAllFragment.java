package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.OrdersAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.datamodel.BookListModel;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.PullableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxHelper;
import com.fanc.wheretoplay.rx.RxSubscribe;

/**
 * Created by admin on 2017/11/1.
 */

public class OrderListAllFragment extends BaseFragment implements PullToRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_pay)
    PullableRecyclerView mRvOrder;
    Unbinder unbinder;
    @BindView(R.id.ptrl_pay_reserve)
    PullToRefreshLayout ptrlPayReserve;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(inflater.getContext(), R.layout.orderlistallfragment, null);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvOrder.setLayoutManager(lm);
        List orders = new ArrayList<>();

        mRvOrder.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.getResources().getColor(R.color.pay_reserve_list_divider_white)));

        mRvOrder.setItemAnimator(new DefaultItemAnimator());

        mRvOrder.setCanPullDown(true);
        mRvOrder.setCanPullUp(true);

        ptrlPayReserve.setOnRefreshListener(this);



        loadData();




        return view;

    }

    private void loadData() {
        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());


        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("page", "0");

        MultipartBody.Part requestFileD =
                MultipartBody.Part.createFormData("size", "10");

        Retrofit_RequestUtils.getRequest()
                .bookList(requestFileA,requestFileC,requestFileD)
                .compose(RxHelper.<BookListModel.ContentBean>handleResult())
                .subscribe(new RxSubscribe<BookListModel.ContentBean>() {
                    @Override
                    protected void _onNext(BookListModel.ContentBean dataBean) {
                        Log.i("MODEL", "ERRCODE" + dataBean.list.get(0).book_sn);
                        if (dataBean.list!=null&&dataBean.list.size()>0){
                            OrdersAdapter orderAdapter = new OrdersAdapter(mContext,OrderListAllFragment.this,dataBean);
                            mRvOrder.setAdapter(orderAdapter);
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        Log.i("MODEL", "关闭dialog");
                        Log.i("MODEL", "ERRCODE" + message);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            Toast.makeText(mContext, "12121121", Toast.LENGTH_SHORT).show();
        }



    }


}
