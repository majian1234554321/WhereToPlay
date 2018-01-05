package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.ConsumeDetailAdapter;
import com.fanc.wheretoplay.base.BaseFragment;

import com.fanc.wheretoplay.datamodel.Consume;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ConsumeDetailFragment extends BaseFragment {


    RecyclerView mRvDealDetailConsume;

    List<Consume.ConsumeDetail> details;
    ConsumeDetailAdapter adapter;

    // 筛选店名和时间
    String storeName;
    String time;

    Receiver receiver;
    private RelativeLayout rrrrrr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = View.inflate(inflater.getContext(), R.layout.fragment_deal_detail_list, null);
        mRvDealDetailConsume = view.findViewById(R.id.rv_deal_detail);
        rrrrrr = view.findViewById(R.id.rrrrrr);
        init();
        return view;
    }


    private void init() {
        // 列表
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvDealDetailConsume.setLayoutManager(manager);
        mRvDealDetailConsume.addItemDecoration(new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL));
        details = new ArrayList<>();
        adapter = new ConsumeDetailAdapter(mContext, details);
        mRvDealDetailConsume.setAdapter(adapter);

        getConsumeDetailList(null, null);

        registerBroadcastReceiver();
    }

    /**
     * 获取消费详情
     *
     * @param storeName
     * @param time
     */
    private void getConsumeDetailList(String storeName, String time) {
        Map<String, String> param = new HashMap<>();
        param.put(Network.Param.TOKEN, mUser.getToken());

        if (!(storeName == null || storeName.isEmpty())) {
            param.put(Network.Param.STORE_NAME, storeName);
        }
        if (!(time == null || time.isEmpty())) {
            long times = DateFormatUtil.getDate4StrDate(time, "yyyy-MM-dd").getTime() / 1000;
            param.put(Network.Param.TIMES, String.valueOf(times));
        }

        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_COST_DETAIL)
                .params(param)
                .build()
                .execute(new DCallback<Consume>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Consume response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showConsumeDetailList(response.getList());
                            } else {
                                rrrrrr.setVisibility(View.VISIBLE);
                            }
                        } else {
                            rrrrrr.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void showConsumeDetailList(List<Consume.ConsumeDetail> details) {
        this.details.clear();
        this.details.addAll(details);
        adapter.notifyDataSetChanged();
    }


    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_FILTER_CONSUME);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            storeName = intent.getStringExtra(Constants.STORE_NAME);
            time = intent.getStringExtra(Constants.DATE);

            getConsumeDetailList(storeName, time);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
