package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentOrderToCompleteBinding;
import com.fanc.wheretoplay.datamodel.OrderReserved;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/15.
 */

public class OrderToCompleteFragment extends BaseFragment {
    FragmentOrderToCompleteBinding binding;

    TopMenu mTmOrderToComplete;
    RelativeLayout mRlOrderToCompleteStoreInfo;
    TextView mTvOrderToCompleteDiscount;
    TextView mTvOrderToCompleteDistance;
    LinearLayout mLlOrderToCompleteOrderInfo;

    String orderId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_to_complete, container, false);
        initView();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initView() {
        mTmOrderToComplete = binding.tmOrderToComplete;
        mRlOrderToCompleteStoreInfo = binding.rlStoreInfo;
        mTvOrderToCompleteDiscount = binding.tvOrderToCompleteDiscountReal;
        mTvOrderToCompleteDistance = binding.tvOrderToCompleteDistance;
        mLlOrderToCompleteOrderInfo = binding.llOrderInfo;
    }

    private void init() {
        mTmOrderToComplete.setLeftIcon(R.drawable.left);
        mTmOrderToComplete.setTitle(R.string.reserve_submit);
        getOrderInfo();
    }

    private void initListener() {
        mTmOrderToComplete.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public OrderToCompleteFragment setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * 获取订单详情
     */
    private void getOrderInfo() {
        final Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        params.put(Network.Param.ORDER_ID, orderId);
        if (LocationUtils.location != null) {
            params.put(Network.Param.LAT, String.valueOf(LocationUtils.location.getLatitude()));
            params.put(Network.Param.LNG, String.valueOf(LocationUtils.location.getLongitude()));
            requestOrderInfo(params);
        } else {
            showTextProgress("定位中...");
            LocationUtils.getLocation(mContext, new LocationUtils.Callback() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    closeProgress();
                    params.put(Network.Param.LAT, String.valueOf(bdLocation.getLatitude()));
                    params.put(Network.Param.LNG, String.valueOf(bdLocation.getLongitude()));
                    requestOrderInfo(params);
                }
            });
        }
    }

    /**
     * 请求 订单详情
     *
     * @param params
     */
    private void requestOrderInfo(Map<String, String> params) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_WAITING)
                .params(params)
                .build()
                .execute(new DCallback<OrderReserved>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(OrderReserved response) {
                        if (isSuccess(response)) {
                            if (response.order_info != null) {
                                showOrderInfo(response.order_info);
                                mRlOrderToCompleteStoreInfo.setVisibility(View.VISIBLE);
                                mLlOrderToCompleteOrderInfo.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    /**
     * 展示订单详情
     *
     * @param orderInfo
     */
    private void showOrderInfo(OrderReserved.OrderInfo orderInfo) {
        binding.setOrder(orderInfo);
        // 折扣
        if (orderInfo.discount.length() > 0) {
            SpannableString text = new SpannableString(orderInfo.discount + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvOrderToCompleteDiscount.setText(text, TextView.BufferType.SPANNABLE);
        }
        // 距离
        if (!TextUtils.isEmpty(orderInfo.distance)) {
            double distance = Double.parseDouble(orderInfo.distance);
            String d = "";
            if (distance < 500) {
                d = "<500m";
            } else {
                if (distance < 1000) {
                    d = orderInfo.distance + "m";
                } else {
                    // 0.几的时候，格式化会把小数点前的0去掉，原因未知
                    DecimalFormat df = new DecimalFormat("#.0");
                    d = df.format(distance / 1000) + "km";
                }
            }
            mTvOrderToCompleteDistance.setText(d);
        }
    }
}
