package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.fragment.RegisterFragment;
import com.fanc.wheretoplay.fragment.ResetPwdFragment;
import com.fanc.wheretoplay.fragment.SignInFragment;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;

import static com.fanc.wheretoplay.base.App.mContext;


/**
 * @author Administrator
 * @date 2017/6/12
 */

public class SignInActivity extends BaseFragmentActivity {

    public static final String TAG_SIGNIN = "signin";
    public static final String TAG_REGISTER = "register";
    public static final String TAG_RESETPWD = "resetpwd";
    private String isShowing = TAG_SIGNIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_signin);
        App.addActivity(this);
        init();
        LocationUtils.getLocation(this);
    }

    private void init() {
        signIn();
    }

    /**
     * page of sign in
     */
    public void signIn() {
        isShowing = TAG_SIGNIN;
        initFragment(isShowing);
    }

    /**
     * page of register
     */
    public void register() {
        isShowing = TAG_REGISTER;
        initFragment(isShowing);

    }

    /**
     * page of reset password
     */
    public void resetPwd() {
        isShowing = TAG_RESETPWD;
        initFragment(isShowing);
    }

    /**
     * 初始化页面fragment
     *
     * @param tag 页面标签
     */
    private void initFragment(String tag) {
        //transaction事务 不能通用，一个transaction只能commit一次
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = manager.findFragmentByTag(tag);
        if (f == null) {
            switch (tag) {
                case TAG_SIGNIN:
                    f = new SignInFragment();
                    break;
                case TAG_REGISTER:
                    f = new RegisterFragment();
                    break;
                case TAG_RESETPWD:
                    f = new ResetPwdFragment();
                    break;
                default:
                    break;
            }
        }
        transaction.replace(R.id.fl_signin_container, f, tag);
//        transaction.addToBackStack(tag);
        transaction.commit();
    }

    /**
     * 验证手机号的正确性
     *
     * @param mobile
     * @return
     */
    public boolean mobileFormat(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShortToast(mContext, "请输入手机号");
            return false;
        }
        if (mobile.length() < 11) {
            ToastUtils.showShortToast(mContext, "手机号长度不正确");
            return false;
        }
        String telRegex = "[1][3578]\\d{9}";
        if (!mobile.matches(telRegex)) {
            ToastUtils.showShortToast(mContext, "请输入正确的手机号");
            return false;
        }
        return true;
    }

    /**
     * 密码有效性验证
     *
     * @param password
     * @return
     */
    public boolean passwordFormat(String password) {
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShortToast(mContext, "请输入密码");
            return false;
        }
        if (password.length() < 6) {
            ToastUtils.showShortToast(mContext, "密码长度不能少于6位");
            return false;
        }
        return true;
    }

    /**
     * 验证码有效性验证
     *
     * @param input 用户输入的验证
     * @return
     */
    public boolean verifyCodeFormat(String input) {
        if (TextUtils.isEmpty(input)) {
            ToastUtils.showShortToast(mContext, "请输入验证码");
            return false;
        }
        return true;
    }

    /**
     * 验证昵称
     *
     * @param nickname
     * @return
     */
    public boolean nicknameFormat(String nickname) {
        if (TextUtils.isEmpty(nickname)) {
            ToastUtils.showShortToast(mContext, "请输入昵称");
            return false;
        }
        return true;
    }

    /**
     * 跳转主页
     */
    public void startActivityToHome() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_close_top);
        finish();
        overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_close_top);
    }


    @Override
    public void onBackPressed() {
        switch (isShowing) {
            case TAG_SIGNIN:
                super.onBackPressed();
                break;
            case TAG_REGISTER:
//                    signIn();
//                    break;-
            case TAG_RESETPWD:
                signIn();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        App.removeActivity(this);
        LocationUtils.stopLocation();
        super.onDestroy();
    }
}
