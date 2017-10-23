package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentBriefBinding;
import com.fanc.wheretoplay.view.TopMenu;

/**
 * Created by Administrator on 2017/9/14.
 */

public class BriefFragment extends BaseFragment {
    FragmentBriefBinding binding;
    TopMenu mTmAction;
    WebView mWv;

    String mStoreId;
    private String briefUrl;

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
        mTmAction = binding.tmBrief;
        mWv = binding.wvBrief;
    }

    private void init() {
        mTmAction.setLeftIcon(R.drawable.left);
        mTmAction.setTitle(R.string.merchant_brief);

        mWv.loadUrl(briefUrl);
        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("aaa", "onPageFinished: url = " + url);
            }
        });
    }

    private void initListener() {
        mTmAction.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public Fragment setBriefUrl(String briefUrl) {
        this.briefUrl = briefUrl;
        return this;
    }
}
