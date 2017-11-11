package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.HouseNewsAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentHousenewsBinding;
import com.fanc.wheretoplay.datamodel.HousenewsList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private RecyclerView mRc;
    //集合
    private List<HousenewsList.StatusBean> housenews;

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
        mRc = binding.rcMerchantHousenews;
    }

    private void init() {
        mBrief.setBackgroundColor(getResources().getColor(R.color.text_red));
        mBrief.setLeftIcon(R.drawable.left);
        mBrief.setTitle(R.string.merchant_housenews);
        mBrief.setTitleColor(getResources().getColor(R.color.white));
        binding.tvRoomAddress.setText(mStoreAddress);
        binding.tvRoomTitle.setText(mStoreName);
        //打折
        if (mStoreDiscount.length() == 0) {
            binding.tvRoomDiscountReal.setVisibility(View.GONE);
        } else {
            SpannableString text = new SpannableString(mStoreDiscount);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.tvRoomDiscountReal.setText(text, TextView.BufferType.SPANNABLE);
        }

        getMerchantDetail(mStoreId);   //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRc.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRc.addItemDecoration(divider1);

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

    private void getMerchantDetail(String id) {
        showProgress();
        Log.e("token", mUser.getToken() + "\n" + Network.User.PUBLIC_HOUSENEWS);
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_HOUSENEWS)
                .addParams(Network.Param.STORE_ID, id)
                .build()
                .execute(new DCallback<HousenewsList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(HousenewsList response) {
                        if (isSuccess(response)) {
                            if (response.getStatus() != null ) {
                                housenews = response.getStatus();
                              showHouseNewsList(housenews);
                            }
                        }
                    }
                });
    }

    private void showHouseNewsList(List<HousenewsList.StatusBean> housenews) {
        HouseNewsAdapter houseNewsAdapter = new HouseNewsAdapter(mContext, housenews,mStoreId);
        mRc.setAdapter(houseNewsAdapter);
    }

}
