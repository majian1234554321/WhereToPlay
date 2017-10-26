package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.HouseNewsAdapter;
import com.fanc.wheretoplay.adapter.HouseTypeAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentBriefBinding;
import com.fanc.wheretoplay.databinding.FragmentHousenewsBinding;
import com.fanc.wheretoplay.datamodel.HousenewsBean;
import com.fanc.wheretoplay.datamodel.StoreDetail;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.LogUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import org.json.JSONObject;

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
    private ArrayList<String> typeName;
    private ArrayList<String> price;
    private HousenewsBean housenews;

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
        SpannableString text = new SpannableString(mStoreDiscount);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvRoomDiscountReal.setText(text, TextView.BufferType.SPANNABLE);

        //列表
        typeName = new ArrayList<>();
        price = new ArrayList<>();
        typeName.add("小包(2-5人)");
        typeName.add("中包(6-10人)");
        typeName.add("大包(11-15人)");
        typeName.add("卡座");
        typeName.add("散台");
        price.add("1300");
        price.add("3800");
        price.add("5800");
        price.add("1000");
        price.add("500");
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
//        showProgress();
//        OkHttpUtils.post()
//                .url(Network.User.PUBLIC_HOUSENEWS)
//                .addParams(Network.Param.STORE_ID, id)
//                .build()
//                .execute(new DCallback<HousenewsBean>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        connectError();
//                    }
//
//                    @Override
//                    public void onResponse(HousenewsBean response) {
//                        if (isSuccess(response)) {
//                            Log.e("Kobe","??????????????????????????" +(response.getContent()!=null));
//                            if (response.getContent() != null ) {
//                                housenews = response.getContent().getStatus();
//                                Log.e("Kobe","==========================" +  response.getContent().getStatus().size());
//                            }
//                        }
//                    }
//                });
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add(Network.Param.STORE_ID, id)
                .build();
        Request request = new Request.Builder()
                .url(Network.User.PUBLIC_HOUSENEWS)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    housenews = gson.fromJson(response.body().string(), HousenewsBean.class);
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (housenews != null) {
                                HouseNewsAdapter houseTypeAdapter = new HouseNewsAdapter(mContext, housenews.getContent().getStatus());
                                mRc.setAdapter(houseTypeAdapter);
                            }
                        }
                    });
                }
            }
        });

    }

}
