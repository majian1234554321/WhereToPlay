package com.fanc.wheretoplay.fragment;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMerchantBriefBinding;
import com.fanc.wheretoplay.datamodel.StoreDetail;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.LocationUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;

/**
 * Created by peace on 2017/10/20.
 */

public class MerchantBriefFragment extends BaseFragment {

    private FragmentMerchantBriefBinding mBriefBinding;
    private WebView mWv;
    private String stringExtra;
    private LoadWebview loadWebviewListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBriefBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_merchant_brief, container, false);
        initView();
        return mBriefBinding.getRoot();
    }

    private void initView() {
        mWv= mBriefBinding.wvMerchantBrief;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init(){
//        if (loadWebviewListener != null) {
//            loadWebviewListener.loadWebview(mWv);
//        }
        DetailActivity detailActivity = (DetailActivity) getActivity();
        if (stringExtra == null) {
            stringExtra = detailActivity.getStringExtra();
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_STORE_DETAIL)
                .addParams(Network.Param.ID, stringExtra)
                .addParams(Network.Param.LAT, String.valueOf(LocationUtils.location.getLatitude()))
                .addParams(Network.Param.LNG, String.valueOf( LocationUtils.location.getLongitude()))
                .build()
                .execute(new DCallback<StoreDetail>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(StoreDetail response) {
                        if (isSuccess(response)) {
                            if (response.getStore() != null) {
                                response.getStore();
                                Log.d("Jordan", "onPageFinished: url = " + response.getStore().getRemark());
                                mWv.loadUrl(Network.BASE + "/" + response.getStore().getRemark());
                                mWv.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);
                                        Log.d("Jordan", "onPageFinished: url = " + url + "\n加载完成了");
                                    }
                                });
                            }
                        }
                    }
                });

        }
    }

    public void setLoadWebviewListener(LoadWebview loadWebviewListener){
        this.loadWebviewListener = loadWebviewListener;
        return;
    }

    public interface LoadWebview{
         void loadWebview(WebView webView);
    }
}
