package com.fanc.wheretoplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.OrderAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.PullableRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/11/1.
 */

public class OrderListAllFragment extends BaseFragment {
    @BindView(R.id.rv_pay)
    PullableRecyclerView mRvOrder;
    Unbinder unbinder;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(inflater.getContext(), R.layout.orderlistallfragment, null);
        unbinder = ButterKnife.bind(this, view);

        TextView tv = new TextView(inflater.getContext());
        tv.setText("OrderListNoPayFragment");
        return tv;



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
