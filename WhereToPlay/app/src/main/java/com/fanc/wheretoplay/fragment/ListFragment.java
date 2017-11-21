package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.ReserveAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentCollectionBinding;
import com.fanc.wheretoplay.datamodel.StoreList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ListFragment extends BaseFragment {
    FragmentCollectionBinding binding;
    /**
     * Views
     */
    TopMenu mTm;
    RecyclerView mRv;
    /**
     * 列表类型
     * 1，附近
     * 2, 最热
     * 3，新店
     * 4，最惠
     */
    int listPage;

    List<StoreList.Store> mStores;
    ReserveAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, container, false);
        initViews();
        init();
        initListeners();
        return binding.getRoot();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mTm = binding.tmCollection;
        mRv = binding.rvCollection;
    }

    /**
     * 业务逻辑
     */
    private void init() {
        mTm.setLeftIcon(R.drawable.left);

        switch (listPage) {
            case 1:
                mTm.setTitle(R.string.near);
                break;
            case 2:
                mTm.setTitle(R.string.hottest);
                break;
            case 3:
                mTm.setTitle(R.string.new_store);
                break;
            case 4:
                mTm.setTitle(R.string.most_discount);
                break;
        }
        mTm.setTitleColor(getResources().getColor(R.color.white));
        // 列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL);
        mRv.addItemDecoration(divider);
        mRv.setLayoutManager(linearLayoutManager);

        mStores = new ArrayList();
        adapter = new ReserveAdapter(mContext, mStores);
        mRv.setAdapter(adapter);

        getStoreList();
    }

    /**
     * 监听器
     */
    private void initListeners() {
        mTm.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public ListFragment setListPage(int listPage) {
        this.listPage = listPage;
        return this;
    }

    /**
     * 获取店铺列表
     */
    private void getStoreList() {
        Map<String, String> param = new HashMap<>();
        if (isLogin()) {
            param.put(Network.Param.TOKEN, mUser.getToken());
        }
        param.put(Network.Param.TYPE, String.valueOf(listPage));
        if (LocationUtils.location != null) {
            param.put(Network.Param.LAT, String.valueOf(LocationUtils.location.getLatitude()));
            param.put(Network.Param.LNG, String.valueOf(LocationUtils.location.getLongitude()));
        }

        showProgress();
        OkHttpUtils.post()
                .url(Network.User.COMMON_STORE_SORT)
                .params(param)
                .build()
                .execute(new DCallback<StoreList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(StoreList response) {
                        if (isSuccess(response)) {
                            if (response.getStore() != null) {
                                showStoreList(response.getStore());
                            }
                        }
                    }
                });
    }

    /**
     * 显示店铺列表
     *
     * @param list
     */
    private void showStoreList(List<StoreList.Store> list) {
        mStores.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
