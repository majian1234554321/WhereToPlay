package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;

/**
 * Created by peace on 2017/10/25.
 */

public class MineDriveFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataBindingUtil.inflate(inflater, R.layout.fragment_mine, null, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
