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
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.DrinkAdapter;
import com.fanc.wheretoplay.adapter.DrinksAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentDrinksBinding;
import com.fanc.wheretoplay.datamodel.Drinks;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;
import java.util.List;


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
    List<Drinks.Wine> wines;
    DrinksAdapter mDrinksAdapter;
    private RecyclerView mRc;
    private ArrayList<String> typeName;
    private ArrayList<String> price;

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
        SpannableString text = new SpannableString(mStoreDiscount);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvDrinksDiscount.setText(text, TextView.BufferType.SPANNABLE);

        //列表
        typeName = new ArrayList<>();
        price = new ArrayList<>();
        typeName.add("黑方");
        typeName.add("皇家礼炮");
        typeName.add("张裕干红");
        typeName.add("卡萨玛利亚");
        typeName.add("小贵妇");
        typeName.add("百威");
        typeName.add("青岛纯生");
        typeName.add("嘉士伯");
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
        DrinkAdapter houseTypeAdapter = new DrinkAdapter(mContext, typeName, price);
        mRc.setAdapter(houseTypeAdapter);

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


}
