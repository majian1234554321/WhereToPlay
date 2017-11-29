package com.fanc.wheretoplay.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.LauncherActivity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.image.GlideGalleryImageLoader;
import com.fanc.wheretoplay.service.CatchExcep;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.FileUtils;
import com.fanc.wheretoplay.util.GlideImageLoader;
import com.fanc.wheretoplay.util.LocationUtils;

import com.qiyukf.unicorn.api.SavePowerConfig;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.UICustomization;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;

import com.sdu.didi.openapi.DIOpenSDK;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by eliang on 2017-09-07.
 */

public class App extends Application {

    public static Context mContext;
    public static long mMainThreadId;
    public static Handler mMainThreadHandler;
    private static List<Activity> activitys = new LinkedList<Activity>();
    private static List<Service> services = new LinkedList<Service>();
    private StatusBarNotificationConfig mStatusBarNotificationConfig;




    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 方法里面初始化了handler，如果放在子线程中，
        // 小米手机中出java.lang.RuntimeException，无法启动
        // 华为手机无异常

        init();

        DIOpenSDK.registerApp(this, "didi65434B4C6253787A30446D76544D474E", "c841ae9e1a8cf7dcc912fe3fe6624dfd");

        // 加快APP启动速度1
        // 1 把耗时操作的初始化过程放在子线程中
        // 2 把耗时操作的初始化过程IntentService中
        new Thread(new Runnable() {
            @Override

            public void run() {
                initOkHttp();
                JPushInterface.setDebugMode(true);
                JPushInterface.init(mContext);
//                initGallery();
                // 注册友盟

//                initTbsX5();
            }
        }).start();

        //七鱼
        mStatusBarNotificationConfig=new StatusBarNotificationConfig();
        mStatusBarNotificationConfig.notificationEntrance= LauncherActivity.class;

        Unicorn.init(this, Constants.QIYU_APPKEY, options(), new GlideImageLoader(mContext));

    }

    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.savePowerConfig = new SavePowerConfig();
        options.uiCustomization = new UICustomization();
        options.uiCustomization.topTipBarBackgroundColor = getResources().getColor(R.color.text_red);
        options.uiCustomization.titleBackgroundColor = getResources().getColor(R.color.text_red);
        options.uiCustomization.titleCenter = true;
        options.uiCustomization.topTipBarTextColor = getResources().getColor(R.color.white);
        return options;
    }

    /**
     * 设置点击Notification消息后进入的页面
     * @param activity
     */
    public void setServiceEntranceActivity(Class<? extends Activity> activity){
        mStatusBarNotificationConfig.notificationEntrance=activity;
    }


    private void initOkHttp() {
        OkHttpUtils.getInstance().debug("OkHttpUtils",true).setConnectTimeout(100000, TimeUnit.MILLISECONDS);
    }

    private void init() {
        // 1.上下文
        mContext = this;

        // 2.主线程的Id
        /**
         * Tid Thread Pid Process Uid User
         */
        mMainThreadId = android.os.Process.myTid();

        // 3.创建一个主线程的handler
        mMainThreadHandler = new Handler();
    }

    private void initTbsX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        //TbsDownloader.needDownload(getApplicationContext(), false);
/*
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                Log.e("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub

            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d("app", "onDownloadFinish is " + i);
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d("app", "onInstallFinish is " + i);
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d("app", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);*/
    }


    /**
     * 初始化Gallery
     */
    private void initGallery() {
        //设置主题
        ThemeConfig themeConfig = ThemeConfig.DEFAULT;
        //配置功能
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        functionConfigBuilder
                .setEnableCamera(true)// 使用相机
                .setEnableEdit(true)// 编辑
                .setEnableCrop(true)// 裁剪
                .setCropHeight(320) // 裁剪高度
                .setForceCrop(true)
                .setCropWidth(320)// 裁剪宽度
                .setCropReplaceSource(true)
                .setCropSquare(true) // 方形裁剪
                .setEnablePreview(true)
                .setRotateReplaceSource(true)
                .setEnableRotate(true);
        FunctionConfig functionConfig = functionConfigBuilder.build();
        ImageLoader imageLoader = new GlideGalleryImageLoader();
        File editCacheFile = new File(FileUtils.getIconDir() + "/edit");
        File cacheFile = new File(FileUtils.getIconDir());

        Log.d("aaa", "initGallery: editCacheFile =" + FileUtils.getIconDir() + "/edit" + "\t cacheFile = " + FileUtils.getIconDir());
        CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageLoader, themeConfig)
                .setEditPhotoCacheFolder(editCacheFile)
                .setTakePhotoFolder(cacheFile)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    public static Context getContext() {
        return mContext;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public static void addService(Service service) {
        services.add(service);
    }

    public static void removeService(Service service) {
        services.remove(service);
    }

    public static void closeApplication() {
        LocationUtils.stopLocation();
        closeActivitys();
        closeServices();
        killProcess();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void closeActivitys() {
        ListIterator<Activity> iterator = activitys.listIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    private static void closeServices() {
        ListIterator<Service> iterator = services.listIterator();
        while (iterator.hasNext()) {
            Service service = iterator.next();
            if (service != null) {
                getContext().stopService(new Intent(getContext(), service.getClass()));
            }
        }
    }

    private static void killProcess() {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        Collections.reverse(list);
        for (ActivityManager.RunningAppProcessInfo info : list
                ) {
            android.os.Process.killProcess(info.pid);
        }
    }
}
