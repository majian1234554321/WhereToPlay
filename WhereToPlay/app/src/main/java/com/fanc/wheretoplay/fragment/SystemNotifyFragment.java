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
import com.fanc.wheretoplay.adapter.SystemNotifyAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentCollectionBinding;
import com.fanc.wheretoplay.datamodel.NotificationList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
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
 * Created by Administrator on 2017/6/19.
 */

public class SystemNotifyFragment extends BaseFragment {

    FragmentCollectionBinding notifyBinding;

    TopMenu mTmSystemNotify;
    RecyclerView mRvSystemNotify;

    List<NotificationList.Notification> notifications;
    SystemNotifyAdapter notifyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        notifyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, container, false);
        initViews();
        init();
        setListener();
        return notifyBinding.getRoot();
    }

    private void initViews() {
        mTmSystemNotify = notifyBinding.tmCollection;
        mRvSystemNotify = notifyBinding.rvCollection;
    }

    private void init() {
        mTmSystemNotify.setLeftIcon(R.drawable.left);
        mTmSystemNotify.setTitle(R.string.system_notify);
        mTmSystemNotify.setTitleColor(getResources().getColor(R.color.white));
        // 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvSystemNotify.setLayoutManager(layoutManager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL);
        mRvSystemNotify.addItemDecoration(divider);
        notifications = new ArrayList<>();
        notifyAdapter = new SystemNotifyAdapter(mContext, notifications);
        mRvSystemNotify.setAdapter(notifyAdapter);
        // 获取通知列表
        getNotificationList();

    }

    private void setListener() {
        mTmSystemNotify.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    /**
     * 获取通知列表
     */
    private void getNotificationList() {
        double lat = 0, lng = 0;
        if (LocationUtils.location != null) {
            lat = LocationUtils.location.getLatitude();
            lng = LocationUtils.location.getLongitude();
        } else {
            lat = -1;
            lng = -1;
        }
        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        if (Constants.USELESS_NUMBER_PARAM != lat) {
            params.put(Network.Param.LAT, String.valueOf(lat));
        }
        if (Constants.USELESS_NUMBER_PARAM != lng) {
            params.put(Network.Param.LNG, String.valueOf(lng));
        }
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_NOTIFICATION)
                .params(params)
                .build()
                .execute(new DCallback<NotificationList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(NotificationList response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showNotificationList(response.getList());
                            }
                        }
                    }
                });
    }

    /**
     * 显示通知
     *
     * @param list
     */
    private void showNotificationList(List<NotificationList.Notification> list) {
        notifications.clear();
        notifications.addAll(list);
        notifyAdapter.notifyDataSetChanged();
    }

}
