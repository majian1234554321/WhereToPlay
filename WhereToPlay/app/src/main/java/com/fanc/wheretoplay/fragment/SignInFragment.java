package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentSigninBinding;
import com.fanc.wheretoplay.datamodel.NewUser;
import com.fanc.wheretoplay.datamodel.User;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2017/6/12.
 */

public class SignInFragment extends BaseFragment {
    FragmentSigninBinding signinBinding;

    EditText mEtMobil;
    EditText mEtPassword;

    //    TextView mTvForgetPwd;
//    TextView mTvMobilRegister;
    Button mBtnSignIn;
    private MultipartBody.Part requestFileA;
    private MultipartBody.Part requestFileB;
    private MultipartBody.Part requestFileC;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false);
        initView();
        init();
        return signinBinding.getRoot();
    }

    /**
     * init views
     */
    private void initView() {
        mEtMobil = signinBinding.etSigninMobile;
        mEtPassword = signinBinding.etSigninPassword;
//        mTvForgetPwd = signinBinding.tvSigninForgetPwd;
//        mTvMobilRegister = signinBinding.tvSigninRegister;
        mBtnSignIn = signinBinding.btnSignin;
    }

    private void init() {
        signinBinding.setDoClick(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signin_forget_pwd:
                ((SignInActivity) mContext).resetPwd();
                break;
            case R.id.tv_signin_register:
                ((SignInActivity) mContext).register();
                break;
            case R.id.btn_signin:
                signIn();
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void signIn() {
        String mobile = mEtMobil.getText().toString().trim();
        if (!((SignInActivity) mContext).mobileFormat(mobile)) {
            return;
        }
        String pwd = mEtPassword.getText().toString();
        if (!((SignInActivity) mContext).passwordFormat(pwd)) {
            return;
        }

        mBtnSignIn.setEnabled(false);
        showProgress();
//        OkHttpUtils.post()
//                .url(Network.User.LOGIN_LOGIN)
//                .addParams(Network.Param.MOBILE, mobile)
//                .addParams(Network.Param.PASSWORD, pwd)
//                .build()
//                .execute(new DCallback<User>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        connectError();
//                        mBtnSignIn.setEnabled(true);
//                    }
//
//                    @Override
//                    public void onResponse(User response) {
//                        mBtnSignIn.setEnabled(true);
//                        if (isSuccess(response)) {
//                            ToastUtils.showShortToast(mContext, "登录成功");
//                            mSpUtils.putUser(response);
//                            mSpUtils.putBoolean(Constants.IS_SIGN_IN, true);
//                            ((SignInActivity) mContext).startActivityToHome();
//                        }
//                    }
//                });
        String registrationID = JPushInterface.getRegistrationID(mContext);//极光推送id
        requestFileA = MultipartBody.Part.createFormData(Network.Param.MOBILE,  mobile);
        requestFileB = MultipartBody.Part.createFormData(Network.Param.PASSWORD,  pwd);
        requestFileC = MultipartBody.Part.createFormData(Network.Param.REGISTRATIONID,  registrationID);


         Retrofit_RequestUtils.getRequest().logIn(requestFileA, requestFileB, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewUser>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        closeProgress();
                        Toast.makeText(mContext, "网络出错", Toast.LENGTH_SHORT).show();
                        mBtnSignIn.setEnabled(true);
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(NewUser response) {
                        mBtnSignIn.setEnabled(true);
                        if (isSuccess(response)) {
                            ToastUtils.showShortToast(mContext, "登录成功");
                            mSpUtils.putUser(response.getUser());
                            mSpUtils.putBoolean(Constants.IS_SIGN_IN, true);
                            ((SignInActivity) mContext).startActivityToHome();
                        }
                    }
                });
    }
}
