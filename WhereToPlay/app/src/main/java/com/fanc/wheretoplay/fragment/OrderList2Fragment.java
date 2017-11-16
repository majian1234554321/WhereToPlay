package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.adapter.OrdersAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.base.BaseLazyFragment;
import com.fanc.wheretoplay.datamodel.BookListModel;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.presenter.OrdelListFragmentPresenter;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.OrderListFragmentView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.PullableRecyclerView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/11/1.
 */

public class OrderList2Fragment extends BaseLazyFragment implements PullToRefreshLayout.OnRefreshListener,OrderListFragmentView {
    @BindView(R.id.rv_pay)
    PullableRecyclerView mRvOrder;
    Unbinder unbinder;
    @BindView(R.id.ptrl_pay_reserve)
    PullToRefreshLayout ptrlPayReserve;
    public static final String TYPE = "2";

    public int currentPage ;
    private OrdelListFragmentPresenter ordelListFragmentPresenter;
    private OrdersAdapter myAdapter;



    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(inflater.getContext(), R.layout.orderlistallfragment, null);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvOrder.setLayoutManager(lm);


        mRvOrder.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.getResources().getColor(R.color.pay_reserve_list_divider_white)));
        mRvOrder.setItemAnimator(new DefaultItemAnimator());

        mRvOrder.setCanPullDown(true);
        mRvOrder.setCanPullUp(true);

        ptrlPayReserve.setOnRefreshListener(this);
        currentPage = 0;

        ordelListFragmentPresenter = new OrdelListFragmentPresenter(mContext,this,ptrlPayReserve,OrderList2Fragment.this);
        ordelListFragmentPresenter.getOrdelListData(TYPE,currentPage,"onRefresh");


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        isPullDown = true;
        currentPage=0;
        ordelListFragmentPresenter.getOrdelListData(TYPE,currentPage,"onRefresh");
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        isPullUp = true;
        if (myAdapter!=null&&myAdapter.getItemCount() >= 10) {
            currentPage++;
            ordelListFragmentPresenter.getOrdelListData(TYPE,currentPage,"onLoadMore");
        }else {
            ptrlPayReserve.loadmoreFinish(0);
            Toast.makeText(mContext, "暂无更多的数据加载", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001){
           // Toast.makeText(mContext, "1001", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setOrderListFragmentData(BookListModel.ContentBean contentBean, String action) {



        if (contentBean.list != null) {
            if ("onRefresh".equals(action)) {
                if ("onLoadMore".equals(action) && myAdapter != null) {
                    myAdapter.notifyDataSetChanged();
                } else {
                    myAdapter = new OrdersAdapter(mContext,OrderList2Fragment.this,contentBean);
                    mRvOrder.setAdapter(myAdapter);
                }
            } else if ("onLoadMore".equals(action)) {
                if (contentBean.list.size() > 0) {
                 //   loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                    myAdapter.append(contentBean.list);

                } else {
               //     loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                }

            }
        } else {
            //loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
    }
}
