package com.fanc.wheretoplay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.ListOrderActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.view.TitleBarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/11/1.
 */

public class OrderFragments extends BaseFragment {

    @BindView(R.id.tbv)
    TitleBarView tbv;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(inflater.getContext(), R.layout.activity_list_order, null);
        ButterKnife.bind(this, view);

        tbv.setTv_title("订单");

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new OrderList1Fragment());
        fragments.add(new OrderList2Fragment());
        fragments.add(new OrderList3Fragment());
        fragments.add(new OrderList4Fragment());

        ListOrderPagerAdapter adapter = new ListOrderPagerAdapter(getChildFragmentManager(), inflater.getContext(), fragments);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        return view;
    }


    public class ListOrderPagerAdapter extends FragmentPagerAdapter {


        public Context context;

        private String[] mTitles = new String[]{"全部", "待支付", "待评价", "已完成"};

        public ArrayList<BaseFragment> fragments;


        public ListOrderPagerAdapter(FragmentManager fragmentManager, Context context, ArrayList<BaseFragment> fragments) {
            super(fragmentManager);
            this.context = context;
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
