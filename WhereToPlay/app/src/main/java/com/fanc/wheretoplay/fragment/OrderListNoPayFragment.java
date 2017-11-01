package com.fanc.wheretoplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.base.BaseFragment;

/**
 * Created by admin on 2017/11/1.
 */

public class OrderListNoPayFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(inflater.getContext());
        tv.setText("OrderListNoPayFragment");
        return tv;
    }
}
