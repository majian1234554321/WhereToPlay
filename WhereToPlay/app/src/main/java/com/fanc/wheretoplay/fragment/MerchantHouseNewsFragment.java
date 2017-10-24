package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentBriefBinding;
import com.fanc.wheretoplay.databinding.FragmentHousenewsBinding;
import com.fanc.wheretoplay.view.TopMenu;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MerchantHouseNewsFragment extends BaseFragment {
    FragmentHousenewsBinding binding;
    TopMenu mBrief;
    WebView mWv;

    String mStoreId;
    private String briefUrl;
    private String mStoreName;
    private String mStoreAddress;
    private String mStoreDiscount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_housenews, container, false);
        initViews();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initViews() {
        mBrief = binding.tmHousenews;
    }

    private void init() {
        mBrief.setBackgroundColor(getResources().getColor(R.color.text_red));
        mBrief.setLeftIcon(R.drawable.left);
        mBrief.setTitle(R.string.merchant_housenews);
        mBrief.setTitleColor(getResources().getColor(R.color.white));
        binding.tvRoomAddress.setText(mStoreAddress);
        binding.tvRoomTitle.setText(mStoreName);
        SpannableString text = new SpannableString(mStoreDiscount);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvRoomDiscountReal.setText(text, TextView.BufferType.SPANNABLE);
    }

    private void initListener() {
        mBrief.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }
    public MerchantHouseNewsFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    public MerchantHouseNewsFragment setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
        return this;
    }

    public MerchantHouseNewsFragment setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
        return this;
    }

    public MerchantHouseNewsFragment setStoreDiscount(String mStoreDiscount) {
        this.mStoreDiscount = mStoreDiscount;
        return this;
    }
}
