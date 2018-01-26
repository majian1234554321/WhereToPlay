package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

/** Created by Administrator on 2017/6/12. */
public class SignInFragment extends BaseFragment {
  FragmentSigninBinding signinBinding;

  EditText mEtMobil;
  EditText mEtPassword;

  Button mBtnSignIn;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    signinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false);
    initView();
    init();
    return signinBinding.getRoot();
  }

  /** init views */
  private void initView() {
    mEtMobil = signinBinding.etSigninMobile;
    mEtPassword = signinBinding.etSigninPassword;
    mBtnSignIn = signinBinding.btnSignin;
    signinBinding.ivclose.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startActivity(new Intent(mContext, MainActivity.class));

             mContext.finish();
          }
        });
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
        ((SignInActivity) mContext).register("");
        break;
      case R.id.btn_signin:
        signIn();
        break;
      default:
        break;
    }
  }

  /** 登录 */
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

    String registrationID = JPushInterface.getRegistrationID(mContext); // 极光推送id
    MultipartBody.Part requestFileA =
        MultipartBody.Part.createFormData(Network.Param.MOBILE, mobile);
    MultipartBody.Part requestFileB =
        MultipartBody.Part.createFormData(Network.Param.PASSWORD, pwd);
    MultipartBody.Part requestFileC =
        MultipartBody.Part.createFormData(Network.Param.REGISTRATIONID, registrationID);

    Retrofit_RequestUtils.getRequest()
        .logIn(requestFileA, requestFileB, requestFileC)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new Observer<NewUser>() {
              @Override
              public void onComplete() {}

              @Override
              public void onError(Throwable e) {
                closeProgress();
                Toast.makeText(mContext, "网络出错", Toast.LENGTH_SHORT).show();
                mBtnSignIn.setEnabled(true);
              }

              @Override
              public void onSubscribe(Disposable disposable) {}

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
