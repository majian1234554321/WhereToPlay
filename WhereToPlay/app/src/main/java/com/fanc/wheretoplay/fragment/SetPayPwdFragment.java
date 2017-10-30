package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentSetPayPwdBinding;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.NumPswView;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/19.
 */

public class SetPayPwdFragment extends BaseFragment {

    FragmentSetPayPwdBinding payPwdBinding;

    TopMenu mTmSetPayPwd;
    TextView mTvSetPayPwdHint;
    NumPswView mNpvSetPayPwd;
    Button mBtnSetPayPwd;

    String password1, password2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        payPwdBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_pay_pwd, container, false);
        initViews();
        init();
        setListener();
        return payPwdBinding.getRoot();
    }

    private void initViews() {
        mTmSetPayPwd = payPwdBinding.tmSetPayPwd;
        mTvSetPayPwdHint = payPwdBinding.tvSetPayPwdHint;
        mNpvSetPayPwd = payPwdBinding.npvSetPayPwd;
        mBtnSetPayPwd = payPwdBinding.btnSetPayPwdConfirm;
    }

    private void init() {
        mTmSetPayPwd.setLeftIcon(R.drawable.left);
        mTmSetPayPwd.setTitle(R.string.set_pay_pwd);
        mTmSetPayPwd.setTitleColor(getResources().getColor(R.color.white));

        payPwdBinding.setClick(this);

    }

    private void setListener() {
        mTmSetPayPwd.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public void onViewClick(View view) {
        if (password1 == null) {//第一次输入密码
            password1 = mNpvSetPayPwd.getText().toString();
            if (TextUtils.isEmpty(password1)) {
                ToastUtils.makePicTextShortToast(mContext, "请输入密码!");
                password1 = null;
                return;
            }
            if (password1.length() < 6) {
                ToastUtils.makePicTextShortToast(mContext, "密码长度不正确!");
                password1 = null;
                return;
            }
            mNpvSetPayPwd.setText("");
            mBtnSetPayPwd.setText(R.string.confirm);
            mTvSetPayPwdHint.setText(R.string.hint_set_pay_pwd_2);
        } else {// 第二次输入密码
            password2 = mNpvSetPayPwd.getText().toString();
            if (TextUtils.isEmpty(password2)) {
                ToastUtils.makePicTextShortToast(mContext, "请输入密码");
                return;
            }
            if (!password1.equals(password2)) {// 两次密码不一致
                ToastUtils.makePicTextShortToast(mContext, "与第一次输入的密码不一致！请重新输入");
                mNpvSetPayPwd.setText("");
            } else {// 两次密码一致
                setPayPwd();
            }
        }
    }

    private void setPayPwd() {
        mBtnSetPayPwd.setEnabled(false);
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_PAY_PASSWORD)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.PASSWORD, password1)
                .addParams(Network.Param.REPASSWORD, password2)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        mBtnSetPayPwd.setEnabled(true);
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        mBtnSetPayPwd.setEnabled(true);
                        if (isSuccess(response)) {
                            if (response.isIs_ok()) {
                                ToastUtils.showShortToast(mContext, "密码设置成功！");
                                mSpUtils.putBoolean(Constants.IS_SET_PAY_PASSWORD, true);
                                mContext.finish();
                            }
                        }
                    }
                });
    }

}
