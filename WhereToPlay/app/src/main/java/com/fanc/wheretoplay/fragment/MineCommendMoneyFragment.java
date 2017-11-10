package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.MineMoneyAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMineFriendBinding;
import com.fanc.wheretoplay.datamodel.MineMoney;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.MyScrollView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by peace on 2017/11/8.
 */

public class MineCommendMoneyFragment extends BaseFragment {

    private FragmentMineFriendBinding mbinding;
    private RecyclerView mRv;
    private TopMenu mTmMineCommendMoney;

    private MineMoneyAdapter mineMoneyAdapter;
    private List<MineMoney.ContentBean> mCommenMoney;

    //刷新
    private int page = 0;
    private int size = 6;
    private List mStores;
    private MyScrollView vCommendFriend;
    private MyScrollView mSvCommendMoney;

    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_friend, null, false);
        initViews();
        init();
        setListener();
        return mbinding.getRoot();
    }

    private void initViews() {
        mRv = mbinding.rvMineFriend;
        mTmMineCommendMoney = mbinding.tmMineFriend;
        mPtrl = mbinding.ptrlCommendFriend;
        mSvCommendMoney = mbinding.svCommendFriend;
    }

    private void init() {
        mStores = new ArrayList();
        mTmMineCommendMoney.setLeftIcon(R.drawable.left);
        mTmMineCommendMoney.setTitle(R.string.money);
        mTmMineCommendMoney.setTitleColor(getResources().getColor(R.color.white));
        // 是否可以上下拉
        mSvCommendMoney.setCanPullDown(true);
        mSvCommendMoney.setCanPullUp(true);
        //进入此页面第一次请求数据
        requestCommendMoney(page, size);
        //列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineMoneyAdapter = new MineMoneyAdapter(mContext, mStores);
        mRv.setLayoutManager(layoutManager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.pay_reserve_list_divider_white));
        mRv.addItemDecoration(divider);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(mineMoneyAdapter);
    }

    private void setListener() {
        mTmMineCommendMoney.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
            }
        });
        mPtrl.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                isPullDown = true;
                page = 0;
                size = 6;
                requestCommendMoney(page, size);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                isPullUp = true;
                size = 6;
                if (mStores.size() < 6) {
                    page = 0;
                } else {
                    page++;
                }
                requestCommendMoney(page, size);
            }
        });
    }


    private void requestCommendMoney(int page, int size) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_RCOMMEND_MONEY)
                .addParams(Network.Param.PAGE, String.valueOf(page))
                .addParams(Network.Param.SIZE, String.valueOf(size))
                .addParams(Network.Param.TOKEN, "eyJpZCI6IjE0Iiwibm9uY2UiOiJrWFpGbkR3bCIsInNoYXJlX2NvZGUiOiIxNDU5ZGYwMiJ9")
                .build()
                .execute(new DCallback<MineMoney>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onResponse(MineMoney response) {
                        if (isSuccess(response)) {
                            mCommenMoney = response.getContent();
                            showCommendMoneyList(mCommenMoney);
                        }
                    }
                });
    }

    private void showCommendMoneyList(List<MineMoney.ContentBean> mCommenMoneyList) {
        if (isPullDown) {// 下拉刷新
            if (mCommenMoneyList.size() == 0) {
                ToastUtils.makePicTextShortToast(mContext, "没有推荐奖励");
                refreshAndLoadMoreSuccess();
                return;
            }
            mStores.clear();
            mStores.addAll(mCommenMoneyList);
            mineMoneyAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else if (isPullUp) {// 上拉加载
            if (mCommenMoneyList.size() == 0) {
                ToastUtils.makePicTextShortToast(mContext, "没有更多了哦");
                refreshOrLoadFail();
                return;
            }
            if (mStores.size() < 6) {
                mStores.clear();
            }
            mStores.addAll(mCommenMoneyList);
            mineMoneyAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
            return;
        } else {   //正常
            mStores.clear();
            mStores.addAll(mCommenMoneyList);
            mineMoneyAdapter.notifyDataSetChanged();
            if (mCommenMoneyList.size() == 0) {
                ToastUtils.showShortToast(mContext, "暂时还没有推荐奖励");
            }
        }
    }

}
