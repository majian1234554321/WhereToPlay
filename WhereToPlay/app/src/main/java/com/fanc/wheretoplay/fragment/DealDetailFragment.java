package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import com.fanc.wheretoplay.databinding.FragmentDealDetailBinding;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.DealDetailFilterDialog;
import com.fanc.wheretoplay.view.MyViewPager;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class DealDetailFragment extends BaseFragment {

    private final int CONSUME_DETAIL = 1;// 消费明细
    private final int RECHARGE_DETAIL = 2;// 充值明细

    FragmentDealDetailBinding dealDetailBinding;
    TopMenu mTmDealDetail;
    MyViewPager mMvpDealDetail;
    RadioButton mRbDealDetailConsume;
    RadioButton mRbDealDetailRechage;

    List<Fragment> fragments;

    int currentDetail = CONSUME_DETAIL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dealDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_deal_detail, null, false);
        initViews();
        init();
        setListeners();
        return dealDetailBinding.getRoot();
    }

    private void initViews() {
        mTmDealDetail = dealDetailBinding.tmDealDetail;
        mMvpDealDetail = dealDetailBinding.mvpDealDetail;
        mRbDealDetailConsume = dealDetailBinding.rbDealDetailConsume;
        mRbDealDetailRechage = dealDetailBinding.rbDealDetailRecharge;
    }

    private void init() {
        mTmDealDetail.setLeftIcon(R.drawable.left);
        mTmDealDetail.setTitle(R.string.deal_detail);
        mTmDealDetail.setRightText(R.string.filter);

        dealDetailBinding.setClcik(this);
        initPage();
        mRbDealDetailConsume.setChecked(true);
    }

    private void initPage() {
        fragments = new ArrayList<>();
        fragments.add(new ConsumeDetailFragment());
        fragments.add(new RechargeDetailFragment());

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
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_deal_detail_consume:
                mMvpDealDetail.setCurrentItem(0);
                currentDetail = CONSUME_DETAIL;
                break;
            case R.id.rb_deal_detail_recharge:
                mMvpDealDetail.setCurrentItem(1);
                currentDetail = RECHARGE_DETAIL;
                break;
            default:
                break;
        }
    }

}
