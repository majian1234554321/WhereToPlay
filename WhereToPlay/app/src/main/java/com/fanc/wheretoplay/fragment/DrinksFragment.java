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
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.DrinkAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentDrinksBinding;
import com.fanc.wheretoplay.datamodel.Drinks;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/9/14.
 */

public class DrinksFragment extends BaseFragment {
    FragmentDrinksBinding binding;

    TopMenu mTmDrinks;
    TextView mTvDrinksTitle;
    TextView mTvDrinksDiscount;
    TextView mTvDrinksAddress;

    /**
     * 店铺信息
     */
    String mStoreId;
    String mStoreName;
    String mStoreAddress;
    String mStoreDiscount;

    /**
     * 酒水列表
     */
    private RecyclerView mRc;
    private ArrayList<String> typeName;
    private ArrayList<String> price;
    private List<Drinks.ListBean> housenews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drinks, container, false);
        initView();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initView() {
        mTmDrinks = binding.tmDrinks;
        mTvDrinksTitle = binding.tvDrinksTitle;
        mTvDrinksDiscount = binding.tvDrinksDiscountReal;
        mTvDrinksAddress = binding.tvDrinksAddress;
        mRc = binding.rcMerchantDrinks;
    }

    private void init() {
        mTmDrinks.setLeftIcon(R.drawable.left);
        mTmDrinks.setTitle(R.string.drinks);
        mTmDrinks.setTitleColor(getResources().getColor(R.color.white));
        // 店铺信息
        mTvDrinksTitle.setText(mStoreName);
        mTvDrinksAddress.setText(mStoreAddress);
        //打折
        if (mStoreDiscount.length() == 0) {
            mTvDrinksDiscount.setVisibility(View.GONE);
        } else {
            SpannableString text = new SpannableString(mStoreDiscount);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvDrinksDiscount.setText(text, TextView.BufferType.SPANNABLE);
        }
        getMerchantDetail(mStoreId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRc.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRc.addItemDecoration(divider1);

    }

    private void initListener() {
        mTmDrinks.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public DrinksFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    public DrinksFragment setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
        return this;
    }

    public DrinksFragment setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
        return this;
    }

    public DrinksFragment setStoreDiscount(String mStoreDiscount) {
        this.mStoreDiscount = mStoreDiscount;
        return this;
    }

    private void getMerchantDetail(String id) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_WINE)
                .addParams(Network.Param.STORE_ID, id)
                .build()
                .execute(new DCallback<Drinks>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Drinks response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null ) {
                                housenews = response.getList();
                                showHouseNewsList(housenews);
                            }
                        }
                    }
                });
    }

    private void showHouseNewsList(List<Drinks.ListBean> housenews) {
        DrinkAdapter houseTypeAdapter = new DrinkAdapter(mContext,housenews);
        mRc.setAdapter(houseTypeAdapter);
    }

}
