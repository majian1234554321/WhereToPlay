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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.RechargeDetailAdapter;
import com.fanc.wheretoplay.base.BaseFragment;

import com.fanc.wheretoplay.datamodel.Recharge;
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

public class RechargeDetailFragment extends BaseFragment {



    RecyclerView mRvDealDetailRecharge;

    List<Recharge.RechargeDetail> rechargeDetails;
    RechargeDetailAdapter rechargeDetailAdapter;

    Receiver receiver;
    //    private String storeName;
    private String time;
    private RelativeLayout rrrrrr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // detailListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_deal_detail_list, null, false);


      View  view  =   View.inflate(inflater.getContext(),R.layout.fragment_deal_detail_list, null);
        mRvDealDetailRecharge =    view.findViewById(R.id.rv_deal_detail) ;
        rrrrrr = view.findViewById(R.id.rrrrrr);



        init();
        return view;
    }


    private void init() {
        // 列表
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvDealDetailRecharge.setLayoutManager(manager);
        rechargeDetails = new ArrayList<>();
        rechargeDetailAdapter = new RechargeDetailAdapter(mContext, rechargeDetails);
        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL);
        mRvDealDetailRecharge.addItemDecoration(divider);
        mRvDealDetailRecharge.setAdapter(rechargeDetailAdapter);

        getRechargeDetailList(null);

        registerBroadcastReceiver();
    }

    /**
     * 获取充值明细
     */
    private void getRechargeDetailList(String time) {
        Map<String, String> param = new HashMap<>();
        param.put(Network.Param.TOKEN, mUser.getToken());
        if (!(time == null || time.isEmpty())) {
            long times = DateFormatUtil.getDate4StrDate(time, "yyyy-MM-dd").getTime() / 1000;
            param.put(Network.Param.TIMES, String.valueOf(times));
        }

        OkHttpUtils.post()
                .url(Network.User.USER_RECHARGE_DETAIL)
                .params(param)
                .build()
                .execute(new DCallback<Recharge>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Recharge response) {
                        if (response.code == 0) {
                            if (response.getList() != null) {
                                showRechargeDetailList(response.getList());
                            }else {
                                rrrrrr.setVisibility(View.VISIBLE);
                            }
                        }else {
                            rrrrrr.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    private void showRechargeDetailList(List<Recharge.RechargeDetail> details) {
        rechargeDetails.clear();
        rechargeDetails.addAll(details);
        rechargeDetailAdapter.notifyDataSetChanged();
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_FILTER_RECHARGE);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            storeName = intent.getStringExtra(Constants.STORE_NAME);
            time = intent.getStringExtra(Constants.DATE);
            getRechargeDetailList(time);
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
