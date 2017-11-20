package com.fanc.wheretoplay.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.datamodel.User;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.DProgressDialog;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;

import java.util.Arrays;

import rx.subscriptions.CompositeSubscription;

import static com.fanc.wheretoplay.base.App.mContext;


/**
 * @author tylz
 * @time 2016/3/18 0018 15:02
 * @des 所有Activity的基类，保存一些公共方法和属性
 * @updateAuthor tylz
 * @updateDate 2016/3/18 0018
 * @updateDes
 */
public class BaseActivity
        extends Activity {
    public SPUtils mSpUtils;
    private DProgressDialog mProgressDialog;
    public Context mContext;
    public User mUser;

    private com.fanc.wheretoplay.view.AlertDialog mAlertDialog;
    private Receiver receiver;
    public CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        translucent();
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);
        mSpUtils = new SPUtils(this);
        mUser = mSpUtils.getUser();
        App.addActivity(this);
        mContext = getApplicationContext();
        if (LocationUtils.location == null) {
            LocationUtils.getLocation(this);
        }

        compositeSubscription = new CompositeSubscription();

        registerBroadcastReceiver();

    }


    // 状态栏透明
    private void translucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags;
        }

    }

    // 获取状态栏高度
    public int getStatuBarHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.removeActivity(this);
        LocationUtils.stopLocation();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
        if (mAlertDialog != null) {
            mAlertDialog = null;
        }

        if (compositeSubscription!=null) {
            compositeSubscription.clear();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);

    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


    /**
     * 对返回的数据信息进行解析，判断网络是否成功
     *
     * @param model 数据
     * @return true 代表成功 ，false 代表失败
     */
    public boolean isSuccess(BaseModel model) {
        closeProgress();
        if (model.code != 0 || !TextUtils.isEmpty(model.message)) {
//            if (model.code == 40001 || model.equals("接口授权失败")) {
//                ToastUtils.makePicTextShortToast(this, Constants.ICON_TIP, UIUtils.getString(R.string.login_Lose));
//                mSpUtils.removeUser();
//                mSpUtils.putBoolean(Constants.IS_LOGIN, false);
//                Intent intent = new Intent();
//                intent.setClass(this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            } else if (model.code == 40002||model.code==43002) {
//                ToastUtils.showShortToast(this, "用户名或密码错误！");
//            } else {
            ToastUtils.showShortToast(this, model.message);
            if (model.code == 50003) {
                Intent intent = new Intent(Constants.ACTION_RESIGN_IN);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
//            }
            return false;
        } else {
            return true;
        }
    }

    // 是否登录
    public boolean isLogin() {
        return mSpUtils.getBoolean(com.fanc.wheretoplay.util.Constants.IS_SIGN_IN, false);
    }

    /**
     * 网络连接失败
     */
    public void connectError() {
        closeProgress();
        ToastUtils.makePicTextShortToast(this, this.getString(R.string.connect_net_error));
    }

    /**
     * 开启进度条
     */
    public void showProgress() {
        mProgressDialog = new DProgressDialog(this);
        mProgressDialog.show();
    }

    /**
     * 开启进度条
     */
    public void showTextProgress(String text) {
        mProgressDialog = new DProgressDialog(this);
        mProgressDialog.setText(text);
        mProgressDialog.show();
    }

    /**
     * 关闭进度条
     */
    public void closeProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_PERMISSION_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("llm", "onRequestPermissionsResult: permissions = " + Arrays.toString(permissions));
                Log.d("llm", "onRequestPermissionsResult: grantResults = " + Arrays.toString(grantResults));
                if (grantResults.length > 0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                        boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                        if (!b) {
                            // 用户还是想用我的 APP 的
                            // 提示用户去应用设置界面手动开启权限
                            new AlertDialog.Builder(this)
                                    .setTitle("权限不可用")
                                    .setMessage("请在-应用设置-权限-中，允许乐互网使用权限")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();
//                        showDialogTipUserGoToAppSettting();
//                    } else {
//                        finish();
                        }
                    } else {
                        ToastUtils.makePicTextShortToast(mContext, "权限获取成功");
                    }
                }
            }
        }
    }

    /**
     * 提示是否重新登录
     */
    private void alertReSignIn() {
        mAlertDialog = new com.fanc.wheretoplay.view.AlertDialog(this)
                .setTitle("提示")
                .setContent("您的账号在其他的地方登录了\n是否重新登录？")
                .setLeftOnClickListener("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 关闭当前进程
                        App.closeApplication();
                    }
                })
                .setRightOnClickListener("重新登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, SignInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        // 关闭当前进程
                        App.closeApplication();
                    }
                })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        // 关闭当前进程
                        App.closeApplication();
                        return true;
                    }
                })
                .setCanceledOnTouchOutside(false)
                .show();
        mSpUtils.removeUser();
        mSpUtils.removeKey(Constants.IS_SIGN_IN);
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_RESIGN_IN);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("llm", "收到广播");
            if (mAlertDialog == null) {
                Log.i("llm", "收到广播");
                alertReSignIn();
            }
        }
    }

}
