package com.fanc.wheretoplay.base;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.Arrays;

import static com.fanc.wheretoplay.base.App.mContext;


/**
 * @author tylz
 * @time 2016/3/23 0023 15:37
 * @des FragmentActivity的基类
 * @updateAuthor
 * @updateDate 2016/3/23 0023
 * @updateDes
 */
public class BaseFragmentActivity extends FragmentActivity {
    public SPUtils mSPUtils;
    public com.fanc.wheretoplay.view.AlertDialog mAlertDialog;
    private Receiver receiver;
    //刷新
    public PullToRefreshLayout mPtrl;
    public boolean isPullDown, isPullUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        translucent();// 状态栏透明
        mSPUtils = new SPUtils(this);
        App.addActivity(this);
        // 友盟统计
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
//        Log.e("aaa", "onCreate:状态栏高度 =  " + UIUtils.px2Dp(getStatuBarHeight()));
        if (LocationUtils.location == null) {
            LocationUtils.getLocation(this);
        }
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
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_PERMISSION_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("llm", "onRequestPermissionsResult: permissions = " + Arrays.toString(permissions));
                Log.d("llm", "onRequestPermissionsResult: grantResults = " + Arrays.toString(grantResults));
                if (grantResults.length>0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                        boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                        if (!b) {
                            // 用户还是想用我的 APP 的
                            // 提示用户去应用设置界面手动开启权限
                            new AlertDialog.Builder(this)
                                    .setTitle("权限不可用")
                                    .setMessage("请在-应用设置-权限-中，允许去哪玩使用权限")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", UIUtils.getPackageName(), null);
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
                        ToastUtils.makePicTextShortToast(this, "权限获取成功");
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
                        // 关闭当前进程-
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
        mSPUtils.removeUser();
        mSPUtils.removeKey(Constants.IS_SIGN_IN);
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
                Log.d("llm", "收到广播");
                alertReSignIn();
            }
        }
    }

    /**
     * 刷新加载失败
     */
    public void refreshOrLoadFail() {
        if (mPtrl != null) {
            if (isPullDown) {
                isPullDown = false;
                mPtrl.refreshFinish(PullToRefreshLayout.FAIL);
            }
            if (isPullUp) {
                isPullUp = false;
                mPtrl.loadmoreFinish(PullToRefreshLayout.FAIL);
            }
        }
    }

    /**
     * 刷新/加载成功
     */
    public void refreshAndLoadMoreSuccess() {
        if (mPtrl != null) {
            if (isPullDown) {
                mPtrl.refreshFinish(PullToRefreshLayout.SUCCEED);
                isPullDown = false;
            }
            if (isPullUp) {
                mPtrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                isPullUp = false;
            }
        }
    }

}
