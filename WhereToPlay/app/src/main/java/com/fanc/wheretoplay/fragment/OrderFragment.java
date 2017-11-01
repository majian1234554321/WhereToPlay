package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.OrderAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentPayBinding;
import com.fanc.wheretoplay.datamodel.BookList;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
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

public class OrderFragment extends BaseFragment {
    /**
     * 订单可删除的状态
     */
    private final String CANCELED = "1";// 已取消
    private final String COMPLETE_STATEMENT_OF_ACCOUNT = "4";// 已jiedan

    FragmentPayBinding payBinding;
    /**
     * 界面控件
     */
    TopMenu mTmOrder;
    PullableRecyclerView mRvOrder;
    /**
     * 列表
     */
    List<BookList.Book> orders;
    OrderAdapter orderAdapter;
    /**
     * 刷新加载、控制变量
     */
    int page, size;
    /**
     * 删除状态
     */
    boolean isDeleting;
    List<Integer> deleteIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        payBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false);
        initViews();
        init();
        setListeners();
        return payBinding.getRoot();
    }

    private void initViews() {
        mTmOrder = payBinding.tmPay;
        mRvOrder = payBinding.rvPay;
        mPtrl = payBinding.ptrlPayReserve;
    }

    private void init() {
        mTmOrder.setLeftIcon(R.drawable.left);
        mTmOrder.setTitle(R.string.order);
        mTmOrder.setRightIcon(R.drawable.delete);
        mTmOrder.setTitleColor(getResources().getColor(R.color.white));

        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvOrder.setLayoutManager(lm);
        orders = new ArrayList<>();
        orderAdapter = new OrderAdapter(mContext, orders,this);
        mRvOrder.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(1), mContext.getResources().getColor(R.color.pay_reserve_list_divider_white)));
        mRvOrder.setAdapter(orderAdapter);
        mRvOrder.setItemAnimator(new DefaultItemAnimator());

        mRvOrder.setCanPullDown(true);
        mRvOrder.setCanPullUp(true);

        getOrderList(page, size);
    }

    private void setListeners() {
        mTmOrder.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTmOrder.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIsDelete()) {
                    if (isDeleting) {// 是删除状态，执行删除操作
                        final String orderIds = getSelectedOrderId();
                        if (orderIds != null) {
                            new AlertDialog(mContext)
                                    .setTitle("提示")
                                    .setContent("确定要删除订单吗")
                                    .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                                        @Override
                                        public void onBtnClick(View view, String input) {
                                            deleteOrder(orderIds);
                                        }
                                    })
                                    .setCanceledOnTouchOutside(false)
                                    .show();
                        } else {
                            orderAdapter.setDeleting(false);
                        }
//                    orderAdapter.setDeleting(false);
                    } else {// 非删除状态，进入删除状态
                        orderAdapter.setDeleting(true);
                        ToastUtils.makePicTextShortToast(mContext, "请选择要删除的订单，再次点击删除按钮进行删除");
                    }
                    isDeleting = !isDeleting;
                } else {
                    ToastUtils.makePicTextShortToast(mContext, "没有可以删除的订单哦");
                }
            }
        });
        orderAdapter.setOnCancelClickListener(new OrderAdapter.OnCancelClickListener() {
            @Override
            public void onCancelClick(final BookList.Book book, final int position) {
                new AlertDialog(mContext)
                        .setTitle("提示")
                        .setContent("确定取消订单吗")
                        .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                            @Override
                            public void onBtnClick(View view, String input) {
                                cancelOrder(book, position);
                            }
                        })
                        .setCanceledOnTouchOutside(true)
                        .show();
            }
        });
        mPtrl.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                isPullDown = true;
                page = 0;
                size = 0;
                getOrderList(page, size);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                isPullUp = true;
                if (orders.size() < 10) {
                    page = 0;
                } else {
                    page++;
                }
                getOrderList(page, size);
            }
        });
    }

    private void getOrderList(int page, int size) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_BOOK_LIST)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.PAGE, String.valueOf(page))
                .addParams(Network.Param.SIZE, String.valueOf(size))
                .build()
                .execute(new DCallback<BookList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onResponse(BookList response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showOrderList(response.getList());
                            } else {
                                refreshOrLoadFail();
                            }
                        } else {
                            refreshOrLoadFail();
                        }
                    }
                });
    }

    private void showOrderList(List<BookList.Book> books) {
        if (isPullDown) {
            this.orders.clear();
            this.orders.addAll(books);
            orderAdapter.clearAllStatus();
            orderAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else if (isPullUp) {
            if (books.size() < 1) {
                ToastUtils.makePicTextShortToast(mContext, "没有更多了哦");
                refreshOrLoadFail();
                return;
            }
            if (this.orders.size() < 10) {
                this.orders.clear();
            }
            for (int i = 0; i < books.size(); i++) {
                this.orders.add(books.get(i));
                orderAdapter.notifyItemChanged(this.orders.size() + i);
            }
            refreshAndLoadMoreSuccess();
        } else {
            orders.clear();
            orders.addAll(books);
            orderAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 检查是否有可以删除的订单
     *
     * @return
     */
    private boolean checkIsDelete() {
        for (BookList.Book book : orders) {
            if (CANCELED.equals(book.getStatus()) || COMPLETE_STATEMENT_OF_ACCOUNT.equals(book.getStatus())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取选中的订单id，多个以“,”隔开
     * 如果没有选中反null
     *
     * @return
     */
    private String getSelectedOrderId() {
        if (deleteIndex == null) {
            deleteIndex = new ArrayList<>();
        } else {
            deleteIndex.clear();
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i : orderAdapter.status.keySet()) {
            if (orderAdapter.status.get(i)) {
                deleteIndex.add(i);
                if (count != 0) {
                    sb.append(",");
                }
                sb.append(orders.get(i).getOrder_id());
            }
            count++;
        }

        if (deleteIndex.size() < 1) {
            return null;
        }
        return sb.toString();
    }

    /**
     * 删除订单
     *
     * @param OrderIds
     */
    private void deleteOrder(String OrderIds) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_OUT_ORDER)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.IDS, OrderIds)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            if (response.isIs_delete()) {
                                for (int i : deleteIndex) {
                                    orders.remove(i);
                                    orderAdapter.notifyItemRemoved(i);
                                }
                                ToastUtils.makePicTextShortToast(mContext, "删除成功");
                                orderAdapter.setDeleting(false);
                            }
                        }
                    }
                });

    }

    private void cancelOrder(final BookList.Book order, final int position) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_CANCLE_ORDER)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ID, order.getOrder_id())
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
                                order.setStatus("1");
                                orderAdapter.notifyItemChanged(position);
                                ToastUtils.makePicTextShortToast(mContext, "订单取消成功");
                            }
                        }
                    }
                });
    }

}
