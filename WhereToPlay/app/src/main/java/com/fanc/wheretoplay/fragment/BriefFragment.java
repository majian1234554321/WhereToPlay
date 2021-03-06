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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentBriefBinding;
import com.fanc.wheretoplay.view.TopMenu;

/**
 * Created by Administrator on 2017/9/14.
 */

public class BriefFragment extends BaseFragment {
    FragmentBriefBinding binding;
    TopMenu mBrief;
    WebView mWv;

    String mStoreId;
    private String briefUrl;
    private String mStoreName;
    private String mStoreAddress;
    private String mStoreDiscount;
    private TextView mTvAddress;
    private TextView mTvDiscount;
    private TextView mTvDistance;
    private TextView mTvTitle;
    private int length;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_brief, container, false);
        initViews();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initViews() {
        mBrief = binding.tmBrief;
        mWv = binding.wvBrief;
        mTvAddress = binding.tvBriefAddress;
        mTvDiscount = binding.tvBriefDiscountReal;
        mTvDistance = binding.tvBriefDistance;
        mTvTitle = binding.tvBriefTitle;

    }

    private void init() {
        //标题栏
        mBrief.setBackgroundColor(getResources().getColor(R.color.text_red));
        mBrief.setLeftIcon(R.drawable.left);
        mBrief.setTitle("简介");
        mBrief.setTitleColor(getResources().getColor(R.color.white));
        //地址、打折栏
        mTvAddress.setText(mStoreAddress);
        mTvTitle.setText(mStoreName);
        //打折
        if (mStoreDiscount.length() == 0) {
            mTvDiscount.setVisibility(View.GONE);
        } else {
            SpannableString text = new SpannableString(mStoreDiscount);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvDiscount.setText(text, TextView.BufferType.SPANNABLE);
        }

        if (briefUrl == null) {
            return;
        }
        mWv.getSettings().setJavaScriptEnabled(true);
        mWv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWv.loadUrl(briefUrl);
        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }

    private void initListener() {
        mBrief.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public BriefFragment setBriefUrl(String briefUrl) {
        this.briefUrl = briefUrl;
        return this;
    }

    public BriefFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    public BriefFragment setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
        return this;
    }

    public BriefFragment setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
        return this;
    }

    public BriefFragment setStoreDiscount(String mStoreDiscount) {
        this.mStoreDiscount = mStoreDiscount;
        return this;
    }
}
