package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.OrderNewAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentPayBinding;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.OrderList;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.PullableRecyclerView;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/12.
 */

public class PayFragment extends BaseFragment {
    FragmentPayBinding payBinding;

    TopMenu mTmPay;
    PullableRecyclerView mRvPayReserve;

//    List<BookList.Book> books;
//    PayReserveAdapter payReserveAdapter;

    int page, count = 9, size = count;
    Receiver receiver;

    MainActivity.MyOnTouchListener onTouchListener;
    /**
     * 订单新接口
     */
    List<OrderList.Order> orders;
    OrderNewAdapter orderNewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        payBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false);
        initViews();
        init();
        setListeners();
        return payBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getOrderList(page, size);
    }

    private void initViews() {
        mTmPay = payBinding.tmPay;
        mPtrl = payBinding.ptrlPayReserve;
        mRvPayReserve = payBinding.rvPay;
    }

    private void init() {
        mTmPay.setTitle(R.string.pay);
        mTmPay.setTitleColor(getResources().getColor(R.color.white));
        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvPayReserve.setLayoutManager(lm);
//        books = new ArrayList<>();
//        payReserveAdapter = new PayReserveAdapter(mContext, books);

        orders = new ArrayList<>();
        orderNewAdapter = new OrderNewAdapter(mContext, orders);

//        mRvPayReserve.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
//                UIUtils.dp2Px(5), mContext.getResources().getColor(R.color.bg)));
        mRvPayReserve.setAdapter(orderNewAdapter);

        mRvPayReserve.setCanPullDown(true);
        mRvPayReserve.setCanPullUp(true);
        registerBroadcastReceiver();
    }

    private void setListeners() {
        mPtrl.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                isPullDown = true;
                page = 0;
                size = count;
                getOrderList(page, size);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                isPullUp = true;
                size = count;
                page ++;
//                if (orders.size() < 10) {
//                    page = 0;
//                } else {
//                    page++;
//                }
                getOrderList(page, size);
            }
        });
        orderNewAdapter.setOnCancelClickListener(new OrderNewAdapter.OnCancelClickListener() {
            @Override
            public void onCancelClick(final OrderList.Order order, final int position) {
                new AlertDialog(mContext)
                        .setTitle("提示")
                        .setContent("确定取消订单吗")
                        .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                            @Override
                            public void onBtnClick(View view, String input) {
                                cancelOrder(order, position);
                            }
                        })
                        .setCanceledOnTouchOutside(true)
                        .show();
            }
        });
//        mRvPayReserve.setOnScrollChangedListener(new PullableRecyclerView.OnScrollChangedListener() {
//            @Override
//            public void onScroll(RecyclerView recyclerView, int l, int t, int oldl, int oldt) {
//                Log.w("aaa", "onScrollChanged: l = " + l + ",  t = " + t + ",  oldl = " + oldl + ",  oldt = " + oldt);
//                if (oldt > t) {// 上滑
//                    payReserveAdapter.setScrollOrientation(payReserveAdapter.SCROLL_UP);
//                } else {// 下滑
//                    payReserveAdapter.setScrollOrientation(payReserveAdapter.SCROLL_DOWN);
//                }
//            }
//        });
        mRvPayReserve.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    payReserveAdapter.setScrollOrientation(payReserveAdapter.SCROLL_DOWN);
//                } else {
//                    payReserveAdapter.setScrollOrientation(payReserveAdapter.SCROLL_UP);
//                }
                if (dy > 0) {
                    orderNewAdapter.setScrollOrientation(orderNewAdapter.SCROLL_DOWN);
                } else {
                    orderNewAdapter.setScrollOrientation(orderNewAdapter.SCROLL_UP);
                }
            }
        });
        onTouchListener = new MainActivity.MyOnTouchListener() {
            float lastX = 0, lastY = 0, x, y;

            @Override
            public boolean onTouch(MotionEvent ev) {

                int action = ev.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = ev.getX();
                        lastY = ev.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x = ev.getX();
                        y = ev.getY();
                        float dx = Math.abs(x - lastX);
                        float dy = Math.abs(y - lastY);
                        // x轴移动的距离大于y轴的一半就不能下滑，横滑事不能下滑
                        // 反之，当y轴移动距离大于x轴的一半，才能下滑
                        if (dx >= dy / 2) {
                            mRvPayReserve.setCanPullDown(false);
                        } else {
                            mRvPayReserve.setCanPullDown(true);
                        }
                        break;
                }

                return false;
            }
        };
        ((MainActivity) mContext).registerMyOnTouchListener(onTouchListener);

    }


    private void getOrderList(int page, int size) {
        OkHttpUtils.post()
                .url(Network.User.USER_NEW_ORDERLIST)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.PAGE, String.valueOf(page))
                .addParams(Network.Param.SIZE, String.valueOf(size))
                .addParams(Network.Param.TYPE, String.valueOf(1))// 今日订单，不含历史订单
                .build()
                .execute(new DCallback<OrderList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onResponse(OrderList response) {
                        if (isSuccess(response)) {
                            if (response.list != null) {
                                showOrderList(response.list);
                            } else {
                                refreshOrLoadFail();
                            }
                        } else {
                            refreshOrLoadFail();
                        }
                    }
                });
    }
//    private void getOrderList(int page, int size) {
//        OkHttpUtils.post()
//                .url(Network.User.USER_NEW_ORDERLIST)
//                .addParams(Network.Param.TOKEN, mUser.getToken())
//                .addParams(Network.Param.PAGE, String.valueOf(page))
//                .addParams(Network.Param.SIZE, String.valueOf(size))
//                .addParams(Network.Param.TYPE, String.valueOf(1))// 今日订单，不含历史订单
//                .build()
//                .execute(new DCallback<BookList>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        connectError();
//                        refreshOrLoadFail();
//                    }
//
//                    @Override
//                    public void onResponse(BookList response) {
//                        if (isSuccess(response)) {
//                            if (response.getList() != null) {
//                                showBookList(response.getList());
//                            } else {
//                                refreshOrLoadFail();
//                            }
//                        } else {
//                            refreshOrLoadFail();
//                        }
//                    }
//                });
//    }

//    private void showBookList(List<BookList.Book> books) {
//        if (isPullDown) {
//            this.books.clear();
//            this.books.addAll(books);
//            payReserveAdapter.notifyDataSetChanged();
//            refreshAndLoadMoreSuccess();
//        } else if (isPullUp) {
//            if (books.size() < 1) {
//                ToastUtils.makePicTextShortToast(mContext, "没有更多了哦");
//                refreshOrLoadFail();
//                return;
//            }
//            if (this.books.size() < 10) {
//                this.books.clear();
//            }
//            for (int i = 0; i < books.size(); i++) {
//                this.books.add(books.get(i));
//                payReserveAdapter.notifyItemChanged(this.books.size() + i);
//            }
////            this.books.addAll(books);
////            payReserveAdapter.notifyDataSetChanged();
//            refreshAndLoadMoreSuccess();
//        } else {
//            this.books.clear();
//            this.books.addAll(books);
//            payReserveAdapter.notifyDataSetChanged();
//        }
//    }

    private void showOrderList(List<OrderList.Order> orders) {
        if (isPullDown) {   // 下拉刷新
            this.orders.clear();
            this.orders.addAll(orders);
            orderNewAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else if (isPullUp) {   // 上拉加载
            //集合为0，则显示“没有更多数据”
            if (orders.size() == 0) {
                refreshOrLoadFail();
                return;
            }
//            if (this.orders.size() < 10) {
//                this.orders.clear();
//            }
            for (int i = 0; i < orders.size(); i++) {
                this.orders.add(orders.get(i));
                orderNewAdapter.notifyItemChanged(this.orders.size() + i);
            }
//            this.orders.addAll(orders);
//            payReserveAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else {
            this.orders.clear();
            this.orders.addAll(orders);
            orderNewAdapter.notifyDataSetChanged();
            if (orders.size() == 0) {
                ToastUtils.showShortToast(mContext, "没有订单");
            }
        }
    }

    private void cancelOrder(final OrderList.Order order, final int position) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_CANCLE_ORDER)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ID, order.order_id)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            if (response.isIs_cancle()) {
                                // 取消成功，改变状态，通知UI
                                order.status = "1";
                                orderNewAdapter.notifyItemChanged(position);
                                ToastUtils.makePicTextShortToast(mContext, "订单取消成功");
                            }
                        }
                    }
                });
    }
//    private void cancelOrder(final BookList.Book order, final int position) {
//        showProgress();
//        OkHttpUtils.post()
//                .url(Network.User.USER_CANCLE_ORDER)
//                .addParams(Network.Param.TOKEN, mUser.getToken())
//                .addParams(Network.Param.ID, order.getOrder_id())
//                .build()
//                .execute(new DCallback<IsOk>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        connectError();
//                    }
//
//                    @Override
//                    public void onResponse(IsOk response) {
//                        if (isSuccess(response)) {
//                            if (response.isIs_cancle()) {
//                                // 取消成功，改变状态，通知UI
//                                order.setStatus("1");
//                                payReserveAdapter.notifyItemChanged(position);
//                                ToastUtils.makePicTextShortToast(mContext, "订单取消成功");
//                            }
//                        }
//                    }
//                });
//    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_PAY_SUCCESS);
        filter.addAction(Constants.ACTION_WXPAY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            getOrderList(0, 0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
        ((MainActivity) mContext).unregisterMyOnTouchListener(onTouchListener);
    }
}
