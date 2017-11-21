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
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.IntegralAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentIntegralBinding;
import com.fanc.wheretoplay.datamodel.ScoreList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;


/**
 * Created by Administrator on 2017/6/17.
 */

public class IntegralFragment extends BaseFragment {

    FragmentIntegralBinding integralBinding;

    TopMenu mTmIntegral;
    TextView mTvIntegralNo;
    RecyclerView mRvIntegral;

    List<ScoreList.ContentBean.ListBean> scoreList;
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
        mTmIntegral.setTitleColor(getResources().getColor(R.color.white));
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

        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());
        Retrofit_RequestUtils.getRequest().scoreList(requestFileA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScoreList>() {


                    @Override
                    public void onError(Throwable e) {
                        closeProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(ScoreList scoreList) {
                        closeProgress();
                        if (scoreList.code==0){
                            mTvIntegralNo.setText(scoreList.content.total.score.trim());
                            showScoreList(scoreList.content.list);
                        }
                    }
                });


    }



    /**
     * 显示积分列表
     *
     * @param scores
     */
    private void showScoreList(List<ScoreList.ContentBean.ListBean> scores) {
        scoreList.clear();
        scoreList.addAll(scores);
        integralAdapter.notifyDataSetChanged();
    }

}
