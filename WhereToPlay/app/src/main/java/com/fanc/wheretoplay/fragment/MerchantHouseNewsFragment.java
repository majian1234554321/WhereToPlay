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
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRc.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRc.addItemDecoration(divider1);
        HouseNewsAdapter houseTypeAdapter = new HouseNewsAdapter(mContext, typeName, price);
        mRc.setAdapter(houseTypeAdapter);
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
