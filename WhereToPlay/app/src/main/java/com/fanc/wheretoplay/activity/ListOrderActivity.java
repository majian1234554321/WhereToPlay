package com.fanc.wheretoplay.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.fragment.OrderBookFragment;
import com.fanc.wheretoplay.fragment.OrderListAllFragment;
import com.fanc.wheretoplay.fragment.OrderListNoPayFragment;
import com.fanc.wheretoplay.fragment.OrderListPayFragment;
import com.fanc.wheretoplay.view.TitleBarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOrderActivity extends BaseFragmentActivity {

    @BindView(R.id.tbv)
    TitleBarView tbv;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        ButterKnife.bind(this);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new OrderListAllFragment());
        fragments.add(new OrderBookFragment());
        fragments.add(new OrderListPayFragment());
        fragments.add(new OrderListNoPayFragment());

      ListOrderPagerAdapter adapter =   new ListOrderPagerAdapter(getSupportFragmentManager(),this,fragments);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);


    }



    public class ListOrderPagerAdapter extends FragmentPagerAdapter {



      public  ListOrderActivity listOrderActivity;

        private String[] mTitles = new String[]{"全部", "已预订", "已支付","已完成"};

        public ArrayList<BaseFragment> fragments;



        public ListOrderPagerAdapter(FragmentManager fragmentManager, ListOrderActivity listOrderActivity, ArrayList<BaseFragment> fragments) {
            super(fragmentManager);
            this.listOrderActivity = listOrderActivity;
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
