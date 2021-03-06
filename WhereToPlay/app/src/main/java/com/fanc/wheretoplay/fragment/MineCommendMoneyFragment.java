package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.MineMoneyAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMineFriendBinding;
import com.fanc.wheretoplay.datamodel.MineMoney;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.MyScrollView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.Response;

/**
 * Created by peace on 2017/11/8.
 */

public class MineCommendMoneyFragment extends BaseFragment {

    private FragmentMineFriendBinding mbinding;
    private RecyclerView mRv;
    private TopMenu mTmMineCommendMoney;

    private MineMoneyAdapter mineMoneyAdapter;
    private List mCommenMoney;

    //刷新
    private int page = 0;
    private int size = 6;
    private List mStores;
    private MyScrollView vCommendFriend;
    private MyScrollView mSvCommendMoney;
    private RelativeLayout rrrrrr;

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
        rrrrrr = mbinding.rrrrrr;
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
//        showProgress();
//        OkHttpUtils.post()
//                .url(Network.User.USER_RCOMMEND_MONEY)
//                .addParams(Network.Param.PAGE, String.valueOf(0))
//                .addParams(Network.Param.SIZE, String.valueOf(6))
//                .addParams(Network.Param.TOKEN, String.valueOf("eyJpZCI6IjE0Iiwibm9uY2UiOiJrWFpGbkR3bCIsInNoYXJlX2NvZGUiOiIxNDU5ZGYwMiJ9"))
//                .build()
//                .execute(new DCallback<Response>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        connectError();
//                        refreshOrLoadFail();
//                    }
//
//                    @Override
//                    public void onResponse(Response response) {
//                        try {
//                            Log.e("dd","====================="+response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
        showProgress();
        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("size", size + "");
        MultipartBody.Part requestFileB =
                MultipartBody.Part.createFormData("page", page + "");
        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("token", mUser.getToken());

        Retrofit_RequestUtils.getRequest().recomReward(requestFileA, requestFileB, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MineMoney>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        closeProgress();
                        Toast.makeText(mContext, "没有数据", Toast.LENGTH_SHORT).show();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(MineMoney mineMoney) {
                        closeProgress();
                        List<MineMoney.ContentBean> content = mineMoney.getContent();
                        showCommendMoneyList(content);
                    }
                });
    }

    private void showCommendMoneyList(List mCommenMoneyList) {
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
                refreshOrLoadFail();
                return;
            }
            if (mStores.size() < 6) {
                mStores.clear();
            }
            mStores.addAll(mCommenMoneyList);
            mineMoneyAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else {   //正常
            mStores.clear();
            mStores.addAll(mCommenMoneyList);
            mineMoneyAdapter.notifyDataSetChanged();
            if (mCommenMoneyList.size() == 0) {
                if (page == 0) {
                    rrrrrr.setVisibility(View.VISIBLE);
                   // ToastUtils.showShortToast(mContext, "暂时还没有推荐奖励");
                }

            }
        }
    }

}
