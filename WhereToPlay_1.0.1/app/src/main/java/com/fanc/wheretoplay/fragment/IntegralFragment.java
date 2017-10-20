package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.IntegralAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentIntegralBinding;
import com.fanc.wheretoplay.datamodel.ScoreList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/17.
 */

public class IntegralFragment extends BaseFragment {

    FragmentIntegralBinding integralBinding;

    TopMenu mTmIntegral;
    TextView mTvIntegralNo;
    RecyclerView mRvIntegral;

    List<ScoreList.Score> scoreList;
    IntegralAdapter integralAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        integralBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_integral, container, false);
        initViews();
        init();
        setListener();
        return integralBinding.getRoot();
    }

    private void initViews() {
        mTmIntegral = integralBinding.tmIntegral;
        mTvIntegralNo = integralBinding.tvIntegralNo;
        mRvIntegral = integralBinding.rvIntegral;
    }

    private void init() {
        mTmIntegral.setLeftIcon(R.drawable.left);
        mTmIntegral.setTitle(R.string.integral);
        // 列表
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvIntegral.setLayoutManager(manager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL);
        mRvIntegral.addItemDecoration(divider);
        scoreList = new ArrayList<>();
        integralAdapter = new IntegralAdapter(mContext, scoreList);
        mRvIntegral.setAdapter(integralAdapter);

        getScoreList();

    }

    private void setListener() {
        mTmIntegral.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    private void getScoreList() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_SCORE_LIST)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<ScoreList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(ScoreList response) {
                        if (isSuccess(response)) {
                            mTvIntegralNo.setText(response.getTotal());

                            if (response.getList() != null) {
                                showScoreList(response.getList());
                            }
                        }
                    }
                });
    }

    /**
     * 显示积分列表
     *
     * @param scores
     */
    private void showScoreList(List<ScoreList.Score> scores) {
        scoreList.clear();
        scoreList.addAll(scores);
        integralAdapter.notifyDataSetChanged();
    }

}
