package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.activity.WaiterPhotoActivity;
import com.fanc.wheretoplay.adapter.WaiterInfoAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentWaiterInfoBinding;
import com.fanc.wheretoplay.datamodel.SelectedWaiter;
import com.fanc.wheretoplay.datamodel.WaiterList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/20.
 */

public class WaiterInfoFragment extends BaseFragment {
    FragmentWaiterInfoBinding waiterInfoBinding;

    TopMenu mTmWaiterInfo;
    TextView mTvWaiterInfoExplain;
    RecyclerView mRvWaiterInfo;

    List<WaiterList.WaiterInfo> waiterInfos;
    WaiterInfoAdapter adapter;// WaiterInfoAdapter

    // 广播
    Receiver receiver;
    // 商铺id
    String storeId;
    // 点击的下标
    int pos;
    // 是否是选择服务员
    boolean isSelect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        waiterInfoBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_waiter_info, container, false);
        initViews();
        init();
        setListener();
        return waiterInfoBinding.getRoot();
    }

    private void initViews() {
        mTmWaiterInfo = waiterInfoBinding.tmWaiterInfo;
        mTvWaiterInfoExplain = waiterInfoBinding.tvWaiterInfoExplain;
        mRvWaiterInfo = waiterInfoBinding.rvWaiterInfo;
    }

    private void init() {
        mTmWaiterInfo.setLeftIcon(R.drawable.left);
        mTmWaiterInfo.setTitle(R.string.detail);
        mTmWaiterInfo.setTitleColor(getResources().getColor(R.color.white));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        mRvWaiterInfo.setLayoutManager(gridLayoutManager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayout.HORIZONTAL, UIUtils.dp2Px(10), mContext.getResources().getColor(R.color.bg));
        mRvWaiterInfo.addItemDecoration(divider);
        waiterInfos = new ArrayList<>();
        adapter = new WaiterInfoAdapter(mContext, waiterInfos);
        mRvWaiterInfo.setHasFixedSize(true);
        mRvWaiterInfo.setAdapter(adapter);
        // 注册广播
        registerBroadcastReceiver();
    }

    private void setListener() {
        mTmWaiterInfo.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTmWaiterInfo.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaiterList.WaiterInfo waiterInfo = waiterInfos.get(pos);
                Intent intent = new Intent(Constants.ACTION_WAITER_PHOTO_CONFIRM);
                intent.putExtra(Constants.WAITER_NAME, waiterInfo.getName());
                intent.putExtra(Constants.WAITER_ID, waiterInfo.getId());
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                mContext.finish();
            }
        });
        adapter.setListener(new WaiterInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                pos = position;
                final WaiterList.WaiterInfo waiterInfo = waiterInfos.get(position);
                if (waiterInfo.getType() == 0) {// 第一次查看服务员
                    if (!mSpUtils.getBoolean(Constants.IS_SET_PAY_PASSWORD, false)) {
                        new AlertDialog(mContext)
                                .setTitle("提示")
                                .setContent("您还没有设置支付密码，现在去设置？")
                                .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                                    @Override
                                    public void onBtnClick(View view, String input) {
                                        Intent intent = new Intent(mContext, DetailActivity.class);
                                        intent.putExtra(Constants.PAGE, Constants.SET_PAY_PWD);
                                        startActivity(intent);
                                    }
                                })
                                .setCanceledOnTouchOutside(true)
                                .show();
                    } else {
                        new AlertDialog(mContext)
                                .setPasswordInputBox()
                                .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                                    @Override
                                    public void onBtnClick(View view, String input) {
                                        if (input.isEmpty()) {
                                            ToastUtils.makePicTextShortToast(mContext, "请输入支付密码");
                                            return;
                                        }
                                        if (input.length() < 6) {
                                            ToastUtils.makePicTextShortToast(mContext, "支付密码位数不正确");
                                            return;
                                        }
                                        // 余额支付查看服务员
                                        selectWaiter(storeId, waiterInfo.getId(), waiterInfo.getPrice(), input);
                                    }
                                })
                                .setCanceledOnTouchOutside(true)
                                .show();
                    }
                } else {// 服务员已经看过
                    goToCheckWaiterLargeImage(waiterInfo.getName(), waiterInfo.getImage());
                }
            }

            @Override
            public void onItemSelected(int position) {
                pos = position;
                mTmWaiterInfo.setRightText(R.string.confirm);
            }
        });
    }

    public WaiterInfoFragment setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public WaiterInfoFragment setSelect(boolean select) {
        isSelect = select;
        return this;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (storeId != null) {
            getWaiterList(storeId);
        }
    }

    private void getWaiterList(String storeId) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_SERVER_LIST)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.STORE_ID, storeId)
                .build()
                .execute(new DCallback<WaiterList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(WaiterList response) {
                        if (isSuccess(response)) {
                            mTvWaiterInfoExplain.setText(response.getExplain());
                            if (response.getList() != null) {
                                showWaiterList(response.getList());
                            }
                        }
                    }
                });
    }

    /**
     * 显示服务员列表
     *
     * @param infos
     */
    private void showWaiterList(List<WaiterList.WaiterInfo> infos) {
        waiterInfos.addAll(infos);
        adapter.setSelect(isSelect);
        adapter.notifyDataSetChanged();
    }

    /**
     * 选择服务员
     *
     * @param storeId
     * @param waiterId
     * @param price
     * @param payPassword
     */
    private void selectWaiter(String storeId, String waiterId, String price, String payPassword) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_SELECT_WAITER)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.STORE_ID, storeId)
                .addParams(Network.Param.WAITER_ID, waiterId)
                .addParams(Network.Param.PRICE, price)
                .addParams(Network.Param.PAY_PW, payPassword)
                .build()
                .execute(new DCallback<SelectedWaiter>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(SelectedWaiter response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                goToCheckWaiterLargeImage(response.getList().getName(), response.getList().getImage());
                            }
                        }
                    }
                });

    }

    /**
     * 去查看服务员大图
     *
     * @param waiterName
     * @param waiterImage
     */
    private void goToCheckWaiterLargeImage(String waiterName, String waiterImage) {
        Intent intent = new Intent(mContext, WaiterPhotoActivity.class);
//        intent.putExtra(Constants.PAGE, Constants.WAITER_PHOTO);

        Bundle bundle = new Bundle();
        bundle.putString(Constants.WAITER_NAME, waiterName);
        bundle.putString(Constants.WAITER_IMAGE, waiterImage);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    /**
     * 注册本地广播
     */
    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_WAITER_PHOTO_CHECKED);
        filter.addAction(Constants.ACTION_WAITER_PHOTO_SELECTED);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {
        int pre = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 第一次查看需要改状态，其他时候不需要
            if (waiterInfos.get(pos).getType() != 1) {
                waiterInfos.get(pos).setType(1);
            }
            if (Constants.ACTION_WAITER_PHOTO_CHECKED.equals(action)) {

            } else if (Constants.ACTION_WAITER_PHOTO_SELECTED.equals(action)) {
                if (isSelect) {
                    adapter.clearAllStatus();
                    adapter.setItemStatus(pos, true);
                    mTmWaiterInfo.setRightText(R.string.confirm);
                }
            }
            adapter.notifyItemChanged(pre);
            adapter.notifyItemChanged(pos);
            pre = pos;
//            adapter.notifyDataSetChanged();
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
