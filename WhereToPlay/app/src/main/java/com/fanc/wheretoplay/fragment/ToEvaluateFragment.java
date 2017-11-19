package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.PublicationEvaluationActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentToEvaluateBinding;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ToEvaluateFragment extends BaseFragment {

    FragmentToEvaluateBinding evaluateBinding;

    TopMenu mTmToEvaluate;
    TextView mTvToEvaluatePaySuccess;
    TextView mTvToEvaluatePaySum;
    TextView mTvToEvaluate;
    Button mBtnToEvaluate;

    String storeId;
    String orderId;
    boolean isComment;
    String price;
    boolean isCreditReserve;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        evaluateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_to_evaluate, null, false);
        initViews();
        init();
        setListeners();
        return evaluateBinding.getRoot();
    }

    private void initViews() {
        mTmToEvaluate = evaluateBinding.tmToEvaluate;
        mTvToEvaluatePaySuccess = evaluateBinding.tvToEvaluatePaySuccess;
        mTvToEvaluatePaySum = evaluateBinding.tvToEvaluatePaySum;
        mTvToEvaluate = evaluateBinding.tvToEvaluate;
        mBtnToEvaluate = evaluateBinding.btnToEvaluate;
    }

    private void init() {
        mTmToEvaluate.setLeftIcon(R.drawable.left);
        if (isComment) {
            mTmToEvaluate.setTitle("支付完成");
            mTmToEvaluate.setTitleColor(Color.parseColor("#FFFFFF"));
            mBtnToEvaluate.setText(R.string.to_evaluate);
        } else {
            mTmToEvaluate.setTitle(R.string.confirm);
            mBtnToEvaluate.setVisibility(View.GONE);
        }
        if (isCreditReserve) {
            mTvToEvaluatePaySum.setText("已提交预订申请，等待商家确认。");
            mTvToEvaluatePaySuccess.setVisibility(View.GONE);
            mTvToEvaluate.setVisibility(View.GONE);
        } else {
            mTvToEvaluatePaySuccess.setVisibility(View.VISIBLE);
            if (isComment) {
                mTvToEvaluatePaySum.setText(UIUtils.getString(R.string.currency_char) + price);
            } else {
                mTvToEvaluatePaySum.setText(UIUtils.getString(R.string.currency_char) + price);
                mTvToEvaluate.setText("请耐心等待商家确认。");
            }
        }
    }

    private void setListeners() {
        mTmToEvaluate.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mBtnToEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PublicationEvaluationActivity.class);
                intent.putExtra("order_id", orderId);
                intent.putExtra("store_id", storeId);
                startActivity(intent);
                mContext.finish();
            }
        });
    }

    public ToEvaluateFragment setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public ToEvaluateFragment setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public ToEvaluateFragment setComment(boolean comment) {
        isComment = comment;
        return this;
    }

    public ToEvaluateFragment setPrice(String price) {
        this.price = price;
        return this;
    }

    public ToEvaluateFragment isCreditReserve(boolean isCreditReserve) {
        this.isCreditReserve = isCreditReserve;
        return this;
    }

}
