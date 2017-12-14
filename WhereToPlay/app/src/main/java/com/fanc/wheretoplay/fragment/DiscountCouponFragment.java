package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.DiscountCouponAdapter;
import com.fanc.wheretoplay.base.BaseFragment;

import com.fanc.wheretoplay.datamodel.DiscountCouponList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;

import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/17.
 */

public class DiscountCouponFragment extends BaseFragment {


    TopMenu mTmDiscountCoupon;
    RecyclerView mRvDiscountCoupon;

    List<DiscountCouponList.DiscountCoupon> couponList;
    DiscountCouponAdapter couponAdapter;

    boolean isChoose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(inflater.getContext(), R.layout.fragment_discount_coupon, null);
        mTmDiscountCoupon = view.findViewById(R.id.tm_discount_coupon);
        mRvDiscountCoupon = view.findViewById(R.id.rv_discount_coupon);

        init();
        setListeners();
        return view;
    }


    public DiscountCouponFragment setChoose(boolean choose) {
        isChoose = choose;
        return this;
    }

    private void init() {
        mTmDiscountCoupon.setLeftIcon(R.drawable.left);
        mTmDiscountCoupon.setTitle(R.string.discount_coupon);
        mTmDiscountCoupon.setTitleColor(getResources().getColor(R.color.white));
        mTmDiscountCoupon.setRightTextColor(Color.WHITE);
        // 是否是选择优惠券
        if (isChoose) {
            chooseDiscountCoupon();
        }
        // 列表
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvDiscountCoupon.setLayoutManager(manager);
        RecycleViewDivider viewDivider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL);
        mRvDiscountCoupon.addItemDecoration(viewDivider);
        couponList = new ArrayList<>();
        couponAdapter = new DiscountCouponAdapter(mContext, couponList);
        couponAdapter.setChoose(isChoose);
        mRvDiscountCoupon.setAdapter(couponAdapter);
        mRvDiscountCoupon.setHasFixedSize(true);

        getDiscountCouponList();
    }

    private void setListeners() {
        mTmDiscountCoupon.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });

    }

    /**
     * 选择优惠券
     */
    private void chooseDiscountCoupon() {
        mTmDiscountCoupon.setRightText(R.string.confirm);
        mTmDiscountCoupon.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountCouponList.DiscountCoupon discountCoupon = couponAdapter.getSelectedItem();
                Intent intent = new Intent(Constants.ACTION_CHOOSE_DISCOUNT_COUPON);
                if (discountCoupon == null) {
                    intent.putExtra(Constants.DISCOUNT_ID, "");
                    intent.putExtra(Constants.PRICE, String.valueOf(0));
                } else {
                    intent.putExtra(Constants.DISCOUNT_ID, discountCoupon.getId());
                    intent.putExtra(Constants.PRICE, discountCoupon.getPrice());
                }
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                mContext.finish();
            }
        });
    }

    /**
     * 获取优惠券列表
     */
    private void getDiscountCouponList() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_COUPON_LIST)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<DiscountCouponList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(DiscountCouponList response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showDiscountCouponList(response.getList());
                            }
                        }
                    }
                });
    }

    private void showDiscountCouponList(List<DiscountCouponList.DiscountCoupon> discountCoupons) {
        couponList.clear();
        couponList.addAll(discountCoupons);
        couponAdapter.notifyDataSetChanged();
    }

}
