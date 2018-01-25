package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;

import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.DealDetailFilterDialog;

import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/16.
 */

public class DealDetailFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    private final int CONSUME_DETAIL = 1;// 消费明细
    private final int RECHARGE_DETAIL = 2;// 充值明细

    TopMenu mTmDealDetail;
    ViewPager mMvpDealDetail;
    RadioButton mRbDealDetailConsume;
    RadioButton mRbDealDetailRechage, referral;

    List<Fragment> fragments;

    int currentDetail = CONSUME_DETAIL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(inflater.getContext(), R.layout.fragment_deal_detail, null);


        initViews(view);
        init();
        setListeners();
        return view;
    }

    private void initViews(View view) {

        mTmDealDetail = view.findViewById(R.id.tm_deal_detail);
        mRbDealDetailConsume = view.findViewById(R.id.rb_deal_detail_consume);
        mRbDealDetailRechage = view.findViewById(R.id.rb_deal_detail_recharge);
        referral = view.findViewById(R.id.rb_deal_detail_referral);
        mMvpDealDetail = view.findViewById(R.id.mvp_deal_detail);

        RadioGroup rg = view.findViewById(R.id.rg_deal_detail);
        rg.setOnCheckedChangeListener(this);


    }

    private void init() {
        mTmDealDetail.setLeftIcon(R.drawable.left);
        mTmDealDetail.setTitle(R.string.deal_detail);
        mTmDealDetail.setRightText(R.string.filter);
        mTmDealDetail.setTitleColor(getResources().getColor(R.color.white));
        mTmDealDetail.setRightTextColor(getResources().getColor(R.color.white));


        initPage();
        mRbDealDetailConsume.setChecked(true);
    }

    private void initPage() {
        fragments = new ArrayList<>();
        fragments.add(new ConsumeDetailFragment());
        fragments.add(new RechargeDetailFragment());
        fragments.add(new MineCommendMoneyFragment());

        mMvpDealDetail.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        mMvpDealDetail.setCurrentItem(0);
    }

    private void setListeners() {
        mTmDealDetail.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTmDealDetail.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DealDetailFilterDialog(mContext)
                        .setContentDetailFilter(currentDetail)
                        .setCanceledOnTouchOutside(true)
                        .setOnBtnClickListener(new DealDetailFilterDialog.OnSelectedListener() {
                            @Override
                            public void onSelected(String storeName, String selectedDate) {
                                ToastUtils.makePicTextShortToast(mContext, storeName + "\t" + selectedDate);
                                Intent intent = new Intent();
                                if (currentDetail == CONSUME_DETAIL) {
                                    intent.setAction(Constants.ACTION_FILTER_CONSUME);
                                    intent.putExtra(Constants.STORE_NAME, storeName);
                                } else {
                                    intent.setAction(Constants.ACTION_FILTER_RECHARGE);
                                }
                                intent.putExtra(Constants.DATE, selectedDate);
                                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            }
                        })
                        .show();
            }
        });
        mMvpDealDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRbDealDetailConsume.setChecked(true);
                        break;
                    case 1:
                        mRbDealDetailRechage.setChecked(true);
                        break;
                    case 2:
                        referral.setChecked(true);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_deal_detail_consume:
                mMvpDealDetail.setCurrentItem(0);
                currentDetail = CONSUME_DETAIL;
                mTmDealDetail.mTvRightText.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_deal_detail_recharge:
                mMvpDealDetail.setCurrentItem(1);
                currentDetail = RECHARGE_DETAIL;
                mTmDealDetail.mTvRightText.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_deal_detail_referral:
                mMvpDealDetail.setCurrentItem(2);
                mTmDealDetail.mTvRightText.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
