package com.fanc.wheretoplay.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivitySettingsBinding;
import com.fanc.wheretoplay.datamodel.Version;
import com.fanc.wheretoplay.image.GlideCatchUtil;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DownloadUtils;
import com.fanc.wheretoplay.util.FileUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.ItemView;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.File;

import okhttp3.Call;

import static com.fanc.wheretoplay.util.Constants.ACTION_SIGN_OUT;

/**
 *
 * @author Administrator
 * @date 2017/6/19
 */

public class SettingsActivity extends BaseActivity {

    ActivitySettingsBinding settingsBinding;

    TopMenu mTmSettings;
    ItemView mIvSettingSetPayPwd;
    ItemView mIvSettingAdviceFeedback;
    ItemView mIvSettingSystemNotify;
    ItemView mIvSettingCheckToUpdate;
    ItemView mIvSettingClearCache;
    ItemView mIvSettingAboutUs;
    Button mBtnSettingsSignOut;
    public  ItemView mIvChangeEnvironment;
    // 缓存清理工具
    GlideCatchUtil catchUtil;
    // 清除缓存进度
    ProgressDialog mDialog;
    // 下载完成后的广播
    Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        initViews();
        init();
        setListener();
    }

    private void initViews() {
        mTmSettings = settingsBinding.tmSettings;
        mIvSettingSetPayPwd = settingsBinding.ivSettingsSetPayPwd;
        mIvSettingAdviceFeedback = settingsBinding.ivSettingsAdviceFeedback;
        mIvSettingSystemNotify = settingsBinding.ivSettingsSystemNotify;
        mIvSettingCheckToUpdate = settingsBinding.ivSettingsCheckToUpdate;
        mIvSettingClearCache = settingsBinding.ivSettingsClearCache;
        mIvSettingAboutUs = settingsBinding.ivSettingsAboutUs;
        mBtnSettingsSignOut = settingsBinding.btnSettingsSignOut;
        mIvChangeEnvironment = settingsBinding.ivChangeEnvironment;
    }

    private void init() {
        mTmSettings.setLeftIcon(R.drawable.left);
        mTmSettings.setTitle(R.string.settings);
        mTmSettings.setTitleColor(getResources().getColor(R.color.white));

        mIvChangeEnvironment.isShowIcon(false);
        mIvChangeEnvironment.setText(R.string.changeEnv);
        mIvChangeEnvironment.setLeftTextBlod(true);
        mIvChangeEnvironment.setSwitchToShow(true);
        mIvSettingSetPayPwd.isShowIcon(false);
        mIvSettingSetPayPwd.setText(R.string.set_pay_pwd);
        mIvSettingSetPayPwd.setLeftTextBlod(true);
        mIvSettingSetPayPwd.setRightIcon(R.drawable.right);
        mIvSettingAdviceFeedback.isShowIcon(false);
        mIvSettingAdviceFeedback.setText(R.string.advice_feedback);
        mIvSettingAdviceFeedback.setLeftTextBlod(true);
        mIvSettingAdviceFeedback.setRightIcon(R.drawable.right);
        mIvSettingSystemNotify.isShowIcon(false);
        mIvSettingSystemNotify.setText(R.string.system_notify);
        mIvSettingSystemNotify.setLeftTextBlod(true);
        mIvSettingSystemNotify.setRightIcon(R.drawable.right);
        mIvSettingCheckToUpdate.isShowIcon(false);
        mIvSettingCheckToUpdate.setText(R.string.check_to_update);
        mIvSettingCheckToUpdate.setLeftTextBlod(true);
        mIvSettingCheckToUpdate.setRightIcon(R.drawable.right);
        mIvSettingClearCache.isShowIcon(false);
        mIvSettingClearCache.setText(R.string.clear_cache);
        mIvSettingClearCache.setLeftTextBlod(true);
        mIvSettingClearCache.setRightIcon(R.drawable.right);
        mIvSettingAboutUs.isShowIcon(false);
        mIvSettingAboutUs.setText(R.string.about_us);
        mIvSettingAboutUs.setLeftTextBlod(true);
        mIvSettingAboutUs.setRightIcon(R.drawable.right);

        mIvSettingCheckToUpdate.setRightText(UIUtils.getAppVersionName());

        showSwitchStatus();

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("正在清除缓存...");
        // 缓存工具类
        catchUtil = GlideCatchUtil.getInstance();
        // 显示缓存大小
        try {
            mIvSettingClearCache.setRightText(calculateCacheSize());
        } catch (Exception e) {
            e.printStackTrace();
        }

        registerReceiver();
    }

    /**
     * 测试环境改变
     */
    private void showSwitchStatus() {
        String switchStatus = new SPUtils(mContext).getString("switchStatus", "");
        switch (switchStatus) {
            case "on":
                mIvChangeEnvironment.changeEnvironment(true);
                break;
            case "off":
                mIvChangeEnvironment.changeEnvironment(false);
                break;
            default:
                break;
        }
    }
    /**
     * 测试环境改变
     */
    private void setListener() {
        mTmSettings.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvChangeEnvironment.setCheckListener(new ItemView.CheckStatus() {
            @Override
            public void statusChange(boolean isopen) {
                if (isopen) {
                    (new SPUtils(mContext)).putString("switchStatus","on");
                    Network.changEnvironment("http://testapi.51tzl.cn");
                    Network.User.changeUserUrl(Network.USER );
                   ToastUtils.showShortToast(SettingsActivity.this, "现在是测试环境，请重新登录");
                    signOut();
                } else {
                    (new SPUtils(mContext)).putString("switchStatus","off");
                    Network.changEnvironment("http://ktv.51tzl.cn");
                    Network.User.changeUserUrl(Network.USER);
                    ToastUtils.showShortToast(SettingsActivity.this, "现在是正式环境，请重新登录");
                    signOut();
                }
            }
        });
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_settings_set_pay_pwd:
                goToNewPage(Constants.SET_PAY_PWD);
                break;
            case R.id.iv_settings_advice_feedback:
                goToNewPage(Constants.ADVICE_FEEDBACK);
                break;
            case R.id.iv_settings_system_notify:
                goToNewPage(Constants.SYSTEM_NOTIFY);
                break;
            case R.id.iv_settings_check_to_update:
                getVersion();
                break;
            case R.id.iv_settings_clear_cache:
                alertDeleteCache();
                break;
            case R.id.iv_settings_about_us:
                goToNewPage(Constants.ABOUT_US);
                break;
            case R.id.btn_settings_sign_out:
                signOut();
                break;
                default:
                    break;
        }
    }

    private void goToNewPage(String newPage) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra(Constants.PAGE, newPage);
        startActivity(intent);
    }

    /**
     * 获取服务器版本
     */
    private void getVersion() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_VERSION)
                .addParams(Network.Param.OS, Network.PHONE_ANDROID)
                .addParams(Network.Param.VERSION, UIUtils.getAppVersionName())
                .build()
                .execute(new DCallback<Version>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Version response) {
                        if (isSuccess(response)) {
                            alertUpdate(response);
                        }
                    }
                });
    }

    private void alertUpdate(final Version version) {
        if (Constants.Version.higher.equals(version.getResult())) {
            ToastUtils.makePicTextShortToast(mContext, "已是最新版本");
        } else {
            new AlertDialog(this)
                    .setTitle(version.getUpdate_title())
                    .setTitleGravity(Gravity.CENTER)
                    .setTitleColor(UIUtils.getColor(R.color.text_black))
                    .setContent(version.getUpdate_log())
                    .setContentGravity(Gravity.LEFT)
                    .setLeftOnClickListener("更新", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DownloadUtils.APK_DOWNLOAD_ID = DownloadUtils.downloadApk(version.getVersion(),
                                    version.getApk_download_url(), "乐互网.apk");
                        }
                    })
                    .setRightOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .show();
        }
    }

    private void signOut() {
        mSpUtils.removeKey(Constants.IS_SIGN_IN);
        mSpUtils.removeKey(Constants.IS_SET_PAY_PASSWORD);
        mSpUtils.removeUser();

        Intent intent = new Intent(mContext, SignInActivity.class);
        startActivity(intent);

        Intent intent1 = new Intent(Constants.ACTION_SIGN_OUT);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent1);

        finish();
    }

    /**
     * 计算缓存大小
     *
     * @return
     * @throws Exception
     */
    private String calculateCacheSize() throws Exception {
        long cache = catchUtil.getFolderSize(mContext.getCacheDir());
        long cacheCode = 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            cacheCode = catchUtil.getFolderSize(mContext.getCodeCacheDir());
        } else {
            cacheCode = catchUtil.getFolderSize(new File(FileUtils.getAppRootDir() + "/" + "code_cache"));
        }
        long files = catchUtil.getFolderSize(mContext.getFilesDir());
        long database = catchUtil.getFolderSize(new File(FileUtils.getAppDatabaseDir()));
        long fuzzyImage = catchUtil.getFolderSize(new File(FileUtils.getExternalStoragePath()));
        return GlideCatchUtil.getFormatSize(cache + cacheCode + files + database + fuzzyImage);
    }

    /**
     * 警告 清除缓存
     */
    private void alertDeleteCache() {
        new AlertDialog(this)
                .setTitle("警告")
                .setTitleGravity(Gravity.CENTER_HORIZONTAL)
                .setContent("\t\t\t\t清除缓存会删除本地缓存和搜索历史记录等信息，确定要清除吗?")
                .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                    @Override
                    public void onBtnClick(View view, String input) {
                        String[] permissions = new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        };
                        if (ContextCompat.checkSelfPermission(SettingsActivity.this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(SettingsActivity.this, permissions, Constants.REQUEST_PERMISSION_CODE);
                        } else {
                            mDialog.show();
                            new Thread(mRunnable).start();
                        }
                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                deleteCache();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.sendEmptyMessage(200);
        }
    };

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    mDialog.cancel();
                    mDialog.dismiss();
//                    mClearCache.setRightText(CacheTools.getCacheSize());
                    ToastUtils.makePicTextShortToast(mContext, "清除缓存成功！");
                    try {
                        mIvSettingClearCache.setRightText("0B");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    /**
     * 删除缓存
     */
    private void deleteCache() {
        catchUtil.deleteFolderFile(mContext.getCacheDir().getAbsolutePath(), true);
        catchUtil.deleteFolderFile(FileUtils.getAppRootDir() + "/" + "code_cache", true);
        catchUtil.deleteFolderFile(mContext.getFilesDir().getAbsolutePath(), true);
        catchUtil.deleteFolderFile(FileUtils.getAppDatabaseDir(), true);
        catchUtil.deleteFolderFile(FileUtils.getExternalStoragePath(), false);
//        RecordDatabaseHelper helper = new RecordDatabaseHelper(mContext);
//        helper.deleteDatabaseTable();
    }


    private void registerReceiver() {
        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter(Constants.APK_DOWNLOAD_COMPLETE);
        registerReceiver(receiver, intentFilter);
    }

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constants.APK_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                ToastUtils.showShortToast(mContext, "下载完成，乐互网.apk保存在 Download 目录下");
                if (DownloadUtils.APK_DOWNLOAD_ID == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(DownloadUtils.APK_DOWNLOAD_ID);
                    Cursor cursor = ((DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE)).query(query);
                    if (cursor.moveToFirst() && cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        String filePath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
//                        Log.v("Aaaaa", "Downloading of the new app version complete");
                        //start the installation of the latest version
                        // 安装apk
                        Intent installIntent = new Intent(Intent.ACTION_VIEW);
                        installIntent.setDataAndType(Uri.parse("file://" + filePath), Constants.MIME_TYPE);
                        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(installIntent);
                    }
                    cursor.close();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        if (mHandler != null) {
            mHandler = null;
        }
    }
}
