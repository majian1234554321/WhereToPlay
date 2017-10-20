package com.fanc.wheretoplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by peace on 2017/10/20.
 */

public class MerchantBriefFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_topmenu, container, false);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_top_menu_bg);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.color.dactionsheet_red);
        integers.add(R.color.tab_color);
        integers.add(R.color.tab_color);
        integers.add(R.color.btn_presell);
        integers.add(R.color.order_reserved);
        integers.add(R.color.not_disount);
        relativeLayout.setBackgroundColor(integers.get(new Random().nextInt(5) + 1));
        return view;
    }
}
