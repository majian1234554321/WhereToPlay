package com.fanc.wheretoplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by peace on 2017/10/20.
 */

public class MerchantTablayoutAdapter extends FragmentPagerAdapter {
    private  ArrayList<Fragment> fragmentArrayList;
    private  ArrayList<String> titleArraylist;

    public MerchantTablayoutAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList, ArrayList<String> titleArraylist) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        this.titleArraylist = titleArraylist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArraylist.get(position);
    }
}