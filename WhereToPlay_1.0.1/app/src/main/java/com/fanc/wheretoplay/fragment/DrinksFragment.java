package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.DrinksAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentDrinksBinding;
import com.fanc.wheretoplay.datamodel.Drinks;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
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
    RecyclerView mRvDrinks;
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
    List<Drinks.Wine> wines;
    DrinksAdapter mDrinksAdapter;

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
        mRvDrinks = binding.rvDrinks;
        mTvDrinksTitle = binding.tvDrinksTitle;
        mTvDrinksDiscount = binding.tvDrinksDiscountReal;
        mTvDrinksAddress = binding.tvDrinksAddress;
    }

    private void init() {
        mTmDrinks.setLeftIcon(R.drawable.left);
        mTmDrinks.setTitle(R.string.drinks);
        // 店铺信息
        mTvDrinksTitle.setText(mStoreName);
        mTvDrinksAddress.setText(mStoreAddress);
        SpannableString text = new SpannableString(mStoreDiscount);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvDrinksDiscount.setText(text, TextView.BufferType.SPANNABLE);
        // 酒水列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvDrinks.setLayoutManager(layoutManager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL);
        mRvDrinks.addItemDecoration(divider);
        wines = new ArrayList<>();
        mDrinksAdapter = new DrinksAdapter(mContext, wines);
        mRvDrinks.setAdapter(mDrinksAdapter);

        getDrinksList();
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

    /**
     * 获取酒水列表
     */
    private void getDrinksList() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_WINE)
                .addParams(Network.Param.STORE_ID, mStoreId)
                .build()
                .execute(new DCallback<Drinks>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Drinks response) {
                        if (isSuccess(response)) {
                            if (response.list != null) {
                                showDrinksList(response.list);
                            }
                        }
                    }
                });
    }

    /**
     * 展示酒水列表
     */
    private void showDrinksList(List<Drinks.Wine> wines) {
        this.wines.addAll(wines);
        mDrinksAdapter.notifyDataSetChanged();
    }
}
