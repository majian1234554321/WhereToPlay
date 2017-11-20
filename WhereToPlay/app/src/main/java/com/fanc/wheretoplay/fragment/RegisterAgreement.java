package com.fanc.wheretoplay.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.view.TopMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by peace on 2017/11/17.
 */

public class RegisterAgreement extends BaseFragment {

    @BindView(R.id.tm_agreement)
    TopMenu tmAgreement;
    @BindView(R.id.wv_register_agr)
    WebView mWv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_register_agr, container, false);
        ButterKnife.bind(this, view);
        initViews();
        init();
        setListener();
        return view;
    }

    private void initViews() {
        tmAgreement.setLeftIcon(R.drawable.left);
        tmAgreement.setTitle(R.string.agreement);
        tmAgreement.setTitleColor(getResources().getColor(R.color.white));
    }

    private void init() {
        showAgreement();
    }

    private void showAgreement() {
        mWv.getSettings().setJavaScriptEnabled(true);
        mWv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWv.loadUrl(Constants.REGISTER_AGREEMENT_URL);
        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    private void setListener() {
        tmAgreement.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
            }
        });
    }

}
