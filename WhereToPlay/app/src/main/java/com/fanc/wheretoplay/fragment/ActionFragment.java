package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentActionBinding;
import com.fanc.wheretoplay.datamodel.Url;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ActionFragment extends BaseFragment {
    FragmentActionBinding binding;
    TopMenu mTmAction;
    WebView mWvAction;

    String mStoreId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_action, container, false);
        initViews();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initViews() {
        mTmAction = binding.tmAciton;
        mWvAction = binding.wvAciton;
    }

    private void init() {
        mTmAction.setLeftIcon(R.drawable.left);
        mTmAction.setTitle(R.string.action);

        getAction();
    }

    private void initListener() {
        mTmAction.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public ActionFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    private void getAction() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_MARKETING)
                .addParams(Network.Param.STORE_ID, mStoreId)
                .build()
                .execute(new DCallback<Url>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Url response) {
                        if (isSuccess(response)) {
                            mWvAction.loadUrl(Network.BASE + "/" + response.marketing);
                        }
                    }
                });
    }
}
