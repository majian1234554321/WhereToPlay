package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentAdviceFeedbackBinding;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.Search;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/19.
 */

public class AdviceFeedbackFragment extends BaseFragment {

    FragmentAdviceFeedbackBinding adviceBinding;

    TopMenu mTmAdviceFeedback;
    EditText mEtAdviceFeedbackTitle;
    EditText mEtAdviceFeedbackContent;
    Button mBtnAdviceFeedbackSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adviceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_advice_feedback, container, false);
        initViews();
        init();
        setListener();
        return adviceBinding.getRoot();
    }

    private void initViews() {
        mTmAdviceFeedback = adviceBinding.tmAdviceFeedback;
        mEtAdviceFeedbackTitle = adviceBinding.etAdviceFeedbackTitle;
        mEtAdviceFeedbackContent = adviceBinding.etAdviceFeedbackContent;
        mBtnAdviceFeedbackSubmit = adviceBinding.btnAdviceFeedbackSubmit;
    }

    private void init() {
        mTmAdviceFeedback.setLeftIcon(R.drawable.left);
        mTmAdviceFeedback.setTitle(R.string.advice_feedback);
        mTmAdviceFeedback.setTitleColor(getResources().getColor(R.color.white));

        adviceBinding.setClick(this);
    }

    private void setListener() {
        mTmAdviceFeedback.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public void onViewClick(View view) {
        String title = mEtAdviceFeedbackTitle.getText().toString().trim();
        if (title.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, "请输入意见主题");
            return;
        }
        String content = mEtAdviceFeedbackContent.getText().toString().trim();
        if (content.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, "请输入意见内容");
            return;
        }
        confirmAdviceFeedback(title, content);
    }

    /**
     * 提交意见反馈
     *
     * @param title
     * @param content
     */
    private void confirmAdviceFeedback(String title, String content) {
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
        params.put(Network.Param.TITLE, title);
        params.put(Network.Param.CONTENT, content);
        if (Constants.USELESS_NUMBER_PARAM != lat) {
            params.put(Network.Param.LAT, String.valueOf(lat));
        }
        if (Constants.USELESS_NUMBER_PARAM != lng) {
            params.put(Network.Param.LNG, String.valueOf(lng));
        }
        mBtnAdviceFeedbackSubmit.setEnabled(false);
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_FEEDBACK)
                .params(params)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        mBtnAdviceFeedbackSubmit.setEnabled(true);
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        mBtnAdviceFeedbackSubmit.setEnabled(true);
                        if (isSuccess(response)) {
                            if (response.isIs_ok()) {
                                ToastUtils.makePicTextShortToast(mContext, "提交成功");
                                mContext.finish();
                            }
                        }
                    }
                });
    }

}
