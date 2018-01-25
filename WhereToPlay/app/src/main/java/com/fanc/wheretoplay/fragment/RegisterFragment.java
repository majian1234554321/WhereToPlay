package com.fanc.wheretoplay.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.AlterCityActivity;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentRegisterBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.datamodel.DelectCollection;
import com.fanc.wheretoplay.datamodel.NewUser;
import com.fanc.wheretoplay.datamodel.User;
import com.fanc.wheretoplay.datamodel.VerifyCode;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;

import static com.fanc.wheretoplay.base.App.mContext;

/** Created by Administrator on 2017/6/12. */
@SuppressLint("ValidFragment")
public class RegisterFragment extends BaseFragment {

  private final long MINUTE = 1000 * 60; // 分钟
  private final long SECOND = 1000; // 秒

  FragmentRegisterBinding registerBinding;

  TopMenu mTmRegister;
  EditText mEtRegisterMobile;
  EditText mEtRegisterPwd;
  EditText mEtRegisterVerification;
  private EditText mEtReisterNickname;
  Button mBtnRegisterVerification;
  EditText mEtRegisterRedeemCode;

  Button mBtnRegister;
  CityResource.City mCity;
  // 验证码定时器
  CountDownTimer mTimer;
  private MultipartBody.Part requestFileA;
  private MultipartBody.Part requestFileB;
  private MultipartBody.Part requestFileC;
  private MultipartBody.Part requestFileD;
  private MultipartBody.Part requestFileF;
  private TextView mTvAgreementRight;
  public String value;

  public RegisterFragment(String value) {
    super();
    this.value = value;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    registerBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
    initView();
    init();
    return registerBinding.getRoot();
  }

  /** init views */
  private void initView() {
    mTmRegister = registerBinding.tmRegister;
    mEtRegisterMobile = registerBinding.etRegisterMobile;
    mEtRegisterPwd = registerBinding.etRegisterPassword;
    mEtRegisterVerification = registerBinding.etRegisterVerification;
    mEtReisterNickname = registerBinding.etRegisterNickname;
    mBtnRegisterVerification = registerBinding.btnRegisterVerification;
    mEtRegisterRedeemCode = registerBinding.etRedeemCode;
    if (value != null) mEtRegisterRedeemCode.setText(value);
    mBtnRegister = registerBinding.btnRegister;
    mTvAgreementRight = registerBinding.tvAgreementRight;
  }

  /** init data */
  private void init() {
    mTmRegister.setLeftIcon(R.drawable.left);
    mTmRegister.setTitle(R.string.register);
    mTmRegister.setTitleColor(getResources().getColor(R.color.white));
    mTmRegister.setLeftIconOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            ((SignInActivity) mContext).signIn("");
          }
        });
    registerBinding.setDoClick(this);
    //        if (LocationUtils.location != null) {
    //            List<CityResource.Province> provinces = getProvinceList();
    //            for (CityResource.Province province:provinces){
    //                for (CityResource.City city:province.getChild()){
    //                    if (LocationUtils.location.getCity() != null &&
    // LocationUtils.location.getCity().contains(city.getName())) {
    //                        this.mCity = city;
    //                    }
    //                }
    //            }
    //        }
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_register_verification:
        getVerification();
        break;
      case R.id.btn_register:
        register();
        break;
      case R.id.tv_agreement_right:
        agreement();
      default:
        break;
    }
  }

  // 同意协议
  private void agreement() {
    Intent intent = new Intent(mContext, DetailActivity.class);
    intent.putExtra(Constants.PAGE, Constants.AGREEMENT);
    startActivity(intent);
  }

  /** 获取验证码 */
  private void getVerification() {
    String mobile = mEtRegisterMobile.getText().toString().trim();
    if (((SignInActivity) mContext).mobileFormat(mobile)) {
      // 计时器
      countDown();
      mBtnRegisterVerification.setEnabled(false);

      showProgress();

      MultipartBody.Part requestFileA =
          MultipartBody.Part.createFormData(Network.Param.MOBILE, mobile);

      Retrofit_RequestUtils.getRequest()
          .getMyVerification(requestFileA)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
              new Observer<VerifyCode>() {
                @Override
                public void onComplete() {}

                @Override
                public void onError(Throwable e) {
                  connectError();
                  mTimer.cancel();
                  mBtnRegisterVerification.setEnabled(true);
                  mBtnRegisterVerification.setText("重新获取");
                }

                @Override
                public void onSubscribe(Disposable disposable) {}

                @Override
                public void onNext(VerifyCode response) {
                  closeProgress();
                }
              });
    }
  }

  /** 验证码倒计时 */
  private void countDown() {
    mTimer =
        new CountDownTimer(MINUTE, SECOND) {
          @Override
          public void onTick(long millisUntilFinished) {
            mBtnRegisterVerification.setText(
                getResources()
                    .getString(R.string.register_verifyTime, millisUntilFinished / SECOND));
          }

          @Override
          public void onFinish() {
            mBtnRegisterVerification.setText("重新获取");
            mBtnRegisterVerification.setEnabled(true);
          }
        };
    mTimer.start();
  }

  private void register() {

    // 手机号
    String mobile = mEtRegisterMobile.getText().toString().trim();
    if (!((SignInActivity) mContext).mobileFormat(mobile)) {
      return;
    }

    // 密码
    String password = mEtRegisterPwd.getText().toString();
    if (!((SignInActivity) mContext).passwordFormat(password)) {
      return;
    }

    // 验证码
    String inputVerifyCode = mEtRegisterVerification.getText().toString().trim();
    if (!((SignInActivity) mContext).verifyCodeFormat(inputVerifyCode)) {
      return;
    }

    // 推广码
    String redeemCode = mEtRegisterRedeemCode.getText().toString().trim();
    if (redeemCode.isEmpty()) {
      redeemCode = String.valueOf(-1);
    }

    // 昵称
    String nickName = mEtReisterNickname.getText().toString().trim();
    if (!((SignInActivity) mContext).nicknameFormat(nickName)) {
      return;
    }
    register(mobile, password, inputVerifyCode, redeemCode, nickName);
  }

  private void register(
      String mobile, String password, String verifyCode, String shearedCode, String nickName) {
    requestFileA = MultipartBody.Part.createFormData(Network.Param.MOBILE, mobile);
    requestFileB = MultipartBody.Part.createFormData(Network.Param.PASSWORD, password);
    requestFileC = MultipartBody.Part.createFormData(Network.Param.CODE, verifyCode);
    requestFileD = MultipartBody.Part.createFormData(Network.Param.SHARE_CODE, shearedCode);
    requestFileF = MultipartBody.Part.createFormData(Network.Param.NICKNAME, nickName);

    mBtnRegister.setEnabled(false);
    showProgress();
    //        OkHttpUtils.post()
    //                .url(Network.User.LOGIN_REGISTER)
    //                .addParams(Network.Param.MOBILE, mobile)
    //                .addParams(Network.Param.PASSWORD, password)
    //                .addParams(Network.Param.CODE, verifyCode)
    //                .addParams(Network.Param.SHARE_CODE, shearedCode)
    //                .addParams(Network.Param.NICKNAME, nickName)
    //                .build()
    //                .execute(new DCallback<User>() {
    //                    @Override
    //                    public void onError(Call call, Exception e) {
    //                        connectError();
    //                        mBtnRegister.setEnabled(true);
    //                    }
    //
    //                    @Override
    //                    public void onResponse(User response) {
    //                        mBtnRegister.setEnabled(true);
    //
    //                        if (isSuccess(response)) {
    //                            ToastUtils.showShortToast(mContext, "注册成功");
    //                            mSpUtils.putBoolean(Constants.IS_SIGN_IN, true);
    //                            mSpUtils.putUser(response);
    //                            ((SignInActivity) mContext).startActivityToHome();
    //                        }
    //                    }
    //                });

    Retrofit_RequestUtils.getRequest()
        .register(requestFileA, requestFileB, requestFileC, requestFileD, requestFileF)
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
                mBtnRegister.setEnabled(true);
              }

              @Override
              public void onSubscribe(Disposable disposable) {}

              @Override
              public void onNext(NewUser response) {
                mBtnRegister.setEnabled(true);
                if (isSuccess(response)) {
                  ToastUtils.showShortToast(mContext, "注册成功");
                  mSpUtils.putBoolean(Constants.IS_SIGN_IN, true);
                  mSpUtils.putUser(response.getUser());
                  ((SignInActivity) mContext).startActivityToHome();
                }
              }
            });
  }

  //    /**
  //     * 省列表
  //     *
  //     * @return
  //     */
  //    private List<CityResource.Province> getProvinceList() {
  //        List<CityResource.Province> provinces = null;
  //        try {
  //            InputStream inputStream = mContext.getAssets().open("city.txt");
  //            int length = inputStream.available();
  //            byte[] cityBytes = new byte[length];
  //            inputStream.read(cityBytes);
  //            String json = new String(cityBytes);
  ////            Logger.json(json);
  //            Gson gson = new Gson();
  //            CityResource cityResource = gson.fromJson(json, CityResource.class);
  //            provinces = cityResource.provinces;
  //        } catch (IOException e) {
  //            e.printStackTrace();
  //        }
  //        return provinces;
  //    }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    // 关闭定时器
    if (mTimer != null) {
      mTimer.cancel();
      mTimer = null;
    }
  }
}
