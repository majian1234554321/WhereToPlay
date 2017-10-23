package com.fanc.wheretoplay.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.adapter.HouseTypeAdapter;
import com.fanc.wheretoplay.adapter.ReserveAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMerchantBriefBinding;
import com.fanc.wheretoplay.databinding.FragmentMerchantHousetypeBinding;
import com.fanc.wheretoplay.datamodel.StoreDetail;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by peace on 2017/10/20.
 */

public class MerchantHouseTypeFragment extends BaseFragment {

    private FragmentMerchantHousetypeBinding mHousetypeBinding;
    private WebView mWv;
    private String stringExtra;
    private RecyclerView mRc;
    private ArrayList<String> typeName;
    private ArrayList<String> price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHousetypeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_merchant_housetype, container, false);
        initView();
        init();
        return mHousetypeBinding.getRoot();
    }

    private void initView() {
        mRc = mHousetypeBinding.rcMerchantHousetype;
    }

    private void init(){
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRc.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRc.addItemDecoration(divider1);
        HouseTypeAdapter houseTypeAdapter = new HouseTypeAdapter(mContext, typeName, price);
        mRc.setAdapter(houseTypeAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
