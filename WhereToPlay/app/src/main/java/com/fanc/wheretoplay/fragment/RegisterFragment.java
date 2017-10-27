package com.fanc.wheretoplay.fragment;

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

import com.baidu.location.BDLocation;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.AlterCityActivity;
import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentRegisterBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.datamodel.User;
import com.fanc.wheretoplay.datamodel.VerifyCode;
import com.fanc.wheretoplay.network.Network;
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

import okhttp3.Call;

import static com.fanc.wheretoplay.base.App.mContext;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RegisterFragment extends BaseFragment {

    private final long MINUTE = 1000 * 60;// 分钟
    private final long SECOND = 1000;// 秒

    FragmentRegisterBinding registerBinding;

    TopMenu mTmRegister;
    TextView mTvRegisterCity;
    EditText mEtRegisterMobile;
    EditText mEtRegisterPwd;
    EditText mEtRegisterVerification;
    private EditText mEtReisterNickname;
    Button mBtnRegisterVerification;
    EditText mEtRegisterRedeemCode;

    Button mBtnRegister;
    Receiver receiver;
    CityResource.City mCity;
    // 验证码定时器
    CountDownTimer mTimer;
    String verifyCode;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        initView();
        init();
        return registerBinding.getRoot();
    }

    /**
     * init views
     */
    private void initView() {
        mTmRegister = registerBinding.tmRegister;
        mTvRegisterCity = registerBinding.tvRegisterCity;
        mEtRegisterMobile = registerBinding.etRegisterMobile;
        mEtRegisterPwd = registerBinding.etRegisterPassword;
        mEtRegisterVerification = registerBinding.etRegisterVerification;
        mEtReisterNickname = registerBinding.etRegisterNickname;
        mBtnRegisterVerification = registerBinding.btnRegisterVerification;
        mEtRegisterRedeemCode = registerBinding.etRedeemCode;
        mBtnRegister = registerBinding.btnRegister;
    }

    /**
     * init data
     */
    private void init() {
        mTmRegister.setLeftIcon(R.drawable.left);
        mTmRegister.setTitle(R.string.register);
        mTmRegister.setTitleColor(getResources().getColor(R.color.white));
        mTmRegister.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignInActivity) mContext).signIn();
            }
        });
        registerBinding.setDoClick(this);

        registerBroadcastReceiver();
        Log.v("RegisterFragment", "LocationUtils.location:" + LocationUtils.location);
        if (LocationUtils.location != null) {
            mTvRegisterCity.setText(LocationUtils.location.getCity());
            List<CityResource.Province> provinces = getProvinceList();
            for (CityResource.Province province:provinces){
                for (CityResource.City city:province.getChild()){
                    if (LocationUtils.location.getCity().contains(city.getName())){
                        this.mCity = city;
                    }
                }
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_city:
                goToSelectedCity();
                break;
            case R.id.btn_register_verification:
                getVerification();
                break;
            case R.id.btn_register:
                register();
                break;
            default:

                break;
        }
    }

    private void goToSelectedCity() {
        Intent intent = new Intent(mContext, AlterCityActivity.class);
        startActivity(intent);
    }

    /**
     * 获取验证码
     */
    private void getVerification() {
        String mobile = mEtRegisterMobile.getText().toString().trim();
        if (((SignInActivity) mContext).mobileFormat(mobile)) {
            // 计时器
            countDown();
            mBtnRegisterVerification.setEnabled(false);

            showProgress();
            OkHttpUtils.post()
                    .url(Network.User.LOGIN_VERIFY)
                    .addParams(Network.Param.MOBILE, mobile)
                    .build()
                    .execute(new DCallback<VerifyCode>() {
                        @Override
                        public void onError(Call call, Exception e) {
                            connectError();
                            mTimer.cancel();
                            mBtnRegisterVerification.setEnabled(true);
                            mBtnRegisterVerification.setText("重新获取");
                        }

                        @Override
                        public void onResponse(VerifyCode response) {
                            if (isSuccess(response)) {
                                verifyCode = response.getVerifyCode();
//                                mEtRegisterVerification.setText(verifyCode);
                            }
                        }
                    });

        }
    }

    /**
     * 验证码倒计时
     */
    private void countDown() {
        mTimer = new CountDownTimer(MINUTE, SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBtnRegisterVerification.setText((millisUntilFinished / SECOND) + "s后重新获取");
            }

            @Override
            public void onFinish() {
                mBtnRegisterVerification.setText("重新获取");
                mBtnRegisterVerification.setEnabled(true);
            }
        };
        mTimer.start();
    }

    /**
     * 城市有效性检查
     *
     * @param city
     * @return
     */
    private boolean cityFormat(String city) {
        if (UIUtils.getString(R.string.city).equals(city) || mCity == null) {
            ToastUtils.showShortToast(mContext, "请选择城市");
            return false;
        }
//        if (city.equals(mCity.getName())) {
//            ToastUtils.showShortToast(mContext, "请选择城市");
//            return false;
//        }
        return true;
    }

    private void register() {
        String city = mTvRegisterCity.getText().toString().trim();
        if (!cityFormat(city)) {
            return;
        }

        //手机号
        String mobile = mEtRegisterMobile.getText().toString().trim();
        if (!((SignInActivity) mContext).mobileFormat(mobile)) {
            return;
        }

        //密码
        String password = mEtRegisterPwd.getText().toString();
        if (!((SignInActivity) mContext).passwordFormat(password)) {
            return;
        }

        //验证码
        String inputVerifyCode = mEtRegisterVerification.getText().toString().trim();
        if (!((SignInActivity) mContext).verifyCodeFormat(inputVerifyCode, verifyCode)) {
            return;
        }

        //推广码
        String redeemCode = mEtRegisterRedeemCode.getText().toString().trim();
        if (redeemCode.isEmpty()) {
            redeemCode = String.valueOf(-1);
        }

        //昵称
        String nickName = mEtReisterNickname.getText().toString().trim();
        if (!((SignInActivity) mContext).nicknameFormat(nickName)) {
            return;
        }
        register(mobile, password, inputVerifyCode, mCity.getId(), mCity.getLat(), mCity.getLng(), redeemCode);
    }

    private void register(String mobile, String password, String verifyCode,
                          String cityId, String lat, String lng, String shearedCode) {
        mBtnRegister.setEnabled(false);
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.LOGIN_REGISTER)
                .addParams(Network.Param.MOBILE, mobile)
                .addParams(Network.Param.PASSWORD, password)
                .addParams(Network.Param.CODE, verifyCode)
                .addParams(Network.Param.REGISTERED, cityId)
                .addParams(Network.Param.LAT, lat)
                .addParams(Network.Param.LNG, lng)
                .addParams(Network.Param.SHARE_CODE, shearedCode)
                .build()
                .execute(new DCallback<User>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        mBtnRegister.setEnabled(true);
                    }

                    @Override
                    public void onResponse(User response) {
                        mBtnRegister.setEnabled(true);

                        if (isSuccess(response)) {
                            ToastUtils.showShortToast(mContext, "注册成功");
                            mSpUtils.putBoolean(Constants.IS_SIGN_IN, true);
                            mSpUtils.putUser(response);
                            ((SignInActivity) mContext).startActivityToHome();
                        }
                    }
                });
    }


    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_CITY_SELECTED);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mCity = (CityResource.City) intent.getSerializableExtra(Constants.CITY);
            mTvRegisterCity.setText(mCity.getName());
        }
    }

    /**
     * 省列表
     *
     * @return
     */
    private List<CityResource.Province> getProvinceList() {
        List<CityResource.Province> provinces = null;
        try {
            InputStream inputStream = mContext.getAssets().open("city.txt");
            int length = inputStream.available();
            byte[] cityBytes = new byte[length];
            inputStream.read(cityBytes);
            String json = new String(cityBytes);
//            Logger.json(json);
            Gson gson = new Gson();
            CityResource cityResource = gson.fromJson(json, CityResource.class);
            provinces = cityResource.provinces;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 取消广播
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
        // 关闭定时器
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
