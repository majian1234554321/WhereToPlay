package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.adapter.DiscoverAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentDiscoverBinding;
import com.fanc.wheretoplay.datamodel.Advertising;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/12.
 */

public class DiscoverFragment extends BaseFragment {
    FragmentDiscoverBinding discoverBinding;

    TopMenu mTmDiscover;
    RecyclerView mRvDiscover;
    /**
     * 商店列表
     */
    DiscoverAdapter mDiscoverAdapter;
    List<Advertising.Shop> shops;
    boolean isGrid = false;
    private double lat, lng;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        discoverBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        initViews();
        init();
        return discoverBinding.getRoot();
    }

    private void initViews() {
        mTmDiscover = discoverBinding.tmDiscover;
        mRvDiscover = discoverBinding.rvDiscover;
    }

    @Override
    public void onStart() {
        super.onStart();
        getAdvertisingList(null, lat, lng);

    }

    private void init() {
        mTmDiscover.setTitle(R.string.discover);
        mTmDiscover.setTitleColor(getResources().getColor(R.color.white));
        discoverBinding.setClick(this);

        final LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvDiscover.setLayoutManager(lm);
        if (LocationUtils.location != null) {
            lat = LocationUtils.location.getLatitude();
            lng = LocationUtils.location.getLongitude();
        } else {
            lat = -1;
            lng = -1;
        }

        // 商店列表
        shops = new ArrayList<>();
        mDiscoverAdapter = new DiscoverAdapter(mContext, shops);
        mRvDiscover.setAdapter(mDiscoverAdapter);
        // 分割线
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(10), UIUtils.getColor(R.color.bg_gray));
        mRvDiscover.addItemDecoration(divider);

//        mTmDiscover.setRightText("切换");
//        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
//        mTmDiscover.setRightTextOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isGrid) {
//                    mRvDiscover.setLayoutManager(lm);
//                } else {
//                    mRvDiscover.setLayoutManager(gridLayoutManager);
//                }
//                mDiscoverAdapter.notifyDataSetChanged();
//                isGrid = !isGrid;
//            }
//        });
    }

    private void getAdvertisingList(String token, double lat, double lng) {
        Map<String, String> param = new HashMap<>();
        if (token != null) {
            param.put(Network.Param.TOKEN, token);
        }
        if (lat != Constants.USELESS_NUMBER_PARAM) {
            param.put(Network.Param.LAT, String.valueOf(lat));
        }
        if (lng != Constants.USELESS_NUMBER_PARAM) {
            param.put(Network.Param.LNG, String.valueOf(lng));
        }
        OkHttpUtils.post()
                .url(Network.User.COMMON_ADVERTISING)
                .params(param)
                .build()
                .execute(new DCallback<Advertising>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Advertising response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showShopList(response.getList());
                            }
                        }
                    }
                });
        showProgress();
    }

    /**
     * 显示列表
     *
     * @param shops
     */
    private void showShopList(List<Advertising.Shop> shops) {
        this.shops.clear();
        this.shops.addAll(shops);
        mDiscoverAdapter.notifyDataSetChanged();
    }

    public void onViewClick(View view) {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        switch (view.getId()) {
            case R.id.ll_discover_near:
                intent.putExtra(Constants.PARAM, 1);
                break;
            case R.id.ll_discover_hottest:
                intent.putExtra(Constants.PARAM, 2);
                break;
            case R.id.ll_discover_new_store:
                intent.putExtra(Constants.PARAM, 3);
                break;
            case R.id.ll_discover_most_discount:
                intent.putExtra(Constants.PARAM, 4);
                break;
            default:
                return;
        }
        intent.putExtra(Constants.PAGE, Constants.LIST_PAGE);
        startActivity(intent);
    }

}
