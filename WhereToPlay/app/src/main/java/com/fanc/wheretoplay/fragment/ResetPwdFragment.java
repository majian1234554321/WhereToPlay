package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentResetPwdBinding;
import com.fanc.wheretoplay.datamodel.User;
import com.fanc.wheretoplay.datamodel.VerifyCode;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;
import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ResetPwdFragment extends BaseFragment {

    private final long MINUTE = 1000 * 60;// 分钟
    private final long SECOND = 1000;// 秒

    FragmentResetPwdBinding resetPwdBinding;

    TopMenu mTmResetPwd;
    EditText mEtResetPwdMobile;
    EditText mEtResetPwdPwd,mEtResetPwdPwd2;
    EditText mEtResetPwdVerification;
    Button mBtnResetPwdVerify;
    Button mBtnResetPwd;

    CountDownTimer mTimer;

    String verifyCode;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        resetPwdBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reset_pwd, container, false);
        initView();
        init();
        return resetPwdBinding.getRoot();
    }

    private void initView() {
        mTmResetPwd = resetPwdBinding.tmResetPwd;
        mEtResetPwdMobile = resetPwdBinding.etResetPwdMobile;
        mEtResetPwdPwd = resetPwdBinding.etResetPwdPassword;
        mEtResetPwdVerification = resetPwdBinding.etResetPwdVerification;
        mBtnResetPwdVerify = resetPwdBinding.btnResetPwdVerification;
        mBtnResetPwd = resetPwdBinding.btnResetPwd;
        mEtResetPwdPwd2 = resetPwdBinding.etResetPwdPassword2;

    }

    private void init() {
        mTmResetPwd.setLeftIcon(R.drawable.left);
        mTmResetPwd.setTitle(R.string.reset_password);
        mTmResetPwd.setTitleColor(getResources().getColor(R.color.white));
        mTmResetPwd.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignInActivity) mContext).signIn();
            }
        });

        resetPwdBinding.setClick(this);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset_pwd_verification:
                getVerifyCode();
                break;
            case R.id.btn_reset_pwd:
                resetPassword();
                break;
            default:
                break;
        }
    }

    private void getVerifyCode() {
        String mobile = mEtResetPwdMobile.getText().toString().trim();
        if (((SignInActivity) mContext).mobileFormat(mobile)) {
            mBtnResetPwdVerify.setEnabled(false);
            countDown();

            showProgress();

            MultipartBody.Part requestFileA = MultipartBody.Part.createFormData(Network.Param.MOBILE, mobile);

            Subscription subscription = Retrofit_RequestUtils.getRequest().getMyVerification(requestFileA)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<VerifyCode>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            connectError();
                            mTimer.cancel();
                            mBtnResetPwdVerify.setEnabled(true);
                            mBtnResetPwdVerify.setText("重新获取");
                        }

                        @Override
                        public void onNext(VerifyCode response) {
                            closeProgress();
                            if (isSuccess(response)) {
                                verifyCode = response.getVerifyCode();
                            }
                        }
                    });
        }
    }

    /**
     * 倒计时
     */
    private void countDown() {
        mTimer = new CountDownTimer(MINUTE, SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBtnResetPwdVerify.setText((millisUntilFinished / SECOND) + "s后重新获取");
            }

            @Override
            public void onFinish() {
                mBtnResetPwdVerify.setText("重新获取");
                mBtnResetPwdVerify.setEnabled(true);
            }
        };
        mTimer.start();
    }

    private void resetPassword() {
        String mobile = mEtResetPwdMobile.getText().toString().trim();
        if (!((SignInActivity) mContext).mobileFormat(mobile)) {
            return;
        }

        String password = mEtResetPwdPwd.getText().toString();
        if (!((SignInActivity) mContext).passwordFormat(password)) {
            return;
        }
        String inputVerifyCode = mEtResetPwdVerification.getText().toString().trim();
        if (!((SignInActivity) mContext).verifyCodeFormat(inputVerifyCode)) {
            return;
        }

        String password2 = mEtResetPwdPwd2.getText().toString();

        if (!password.equals(password2)){
            Toast.makeText(mContext, "两次密码不相符合", Toast.LENGTH_SHORT).show();
            return;
        }

        mBtnResetPwd.setEnabled(false);
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.LOGIN_FIND_PASSWORD)
                .addParams(Network.Param.MOBILE, mobile)
                .addParams(Network.Param.PASSWORD, password)
                .addParams(Network.Param.CODE, inputVerifyCode)
                .build()
                .execute(new DCallback<User>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        mBtnResetPwd.setEnabled(true);
                    }

                    @Override
                    public void onResponse(User response) {
                        mBtnResetPwd.setEnabled(true);
                        if (isSuccess(response)) {
                            ToastUtils.showShortToast(mContext,"密码修改成功");
                            mSpUtils.putUser(response);
                            mSpUtils.putBoolean(Constants.IS_SIGN_IN,true);
                            ((SignInActivity) mContext).startActivityToHome();
                        }
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
