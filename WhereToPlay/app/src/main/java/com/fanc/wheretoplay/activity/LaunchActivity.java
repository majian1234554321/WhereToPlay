package com.fanc.wheretoplay.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivityLaunchBinding;
import com.fanc.wheretoplay.datamodel.DataValue;
import com.fanc.wheretoplay.datamodel.Version;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.service.InstallReceiver;
import com.fanc.wheretoplay.util.BaiDuMapUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DownloadUtils;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.ItemView;
import com.meituan.android.walle.WalleChannelReader;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.sdu.didi.openapi.DIOpenSDK;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class LaunchActivity extends BaseActivity {

    ActivityLaunchBinding launchBinding;

    ViewPager mVpLaunch;
    LinearLayout mLlLaunchIndicator;
    Button mBtnLaunch;
    ImageView mIvLaunchAd;

    int[] imgs;
    List<ImageView> imageViews;
    int prePosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
//        initPermissions();


        //七鱼需要
        parseIntent();




        initViews();



        init();

        changeConvironment();

      /*  OkHttpUtils.post()
                .url(Network.User.PUBLIC_VERSION)
                .addParams(Network.Param.OS, Network.PHONE_ANDROID)
                .addParams(Network.Param.VERSION, UIUtils.getAppVersionName())
                .addParams(Network.Param.AppMetaData, "_company")
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
*/

    }



    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }


    private void alertUpdate(final Version version) {
        if (getVerName(this).equals(version.getVersion())) {
            init();

            changeConvironment();
        } else {
            new AlertDialog(this)
                    .setTitle("提示")
                    .setTitleGravity(Gravity.CENTER_VERTICAL)
                    .setTitleColor(UIUtils.getColor(R.color.text_black))
                    .setContent("检测到最新版本")
                    .setContentColor(UIUtils.getColor(R.color.text_black))
                    .setLeftOnClickListener("更新", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (version.getApk_download_url()!=null){
                                DownloadUtils.APK_DOWNLOAD_ID = DownloadUtils.downloadApk(version.getVersion(),
                                        version.getApk_download_url(), "乐互网.apk");
                            }

                            init();

                            changeConvironment();

                        }
                    })
                    .setRightOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            init();

                            changeConvironment();
                        }
                    })
                    .setCanceledOnTouchOutside(false)
                    .show();
        }
    }




    /**
     * 七鱼需要
     */
    private void parseIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            Unicorn.openServiceActivity(this, "去哪儿客服", new ConsultSource(null, null, null));
            // 最好将intent清掉，以免从堆栈恢复时又打开客服窗口
            setIntent(new Intent());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initPermissions();

    }

    /**
     * 测试环境改变
     */
    private void changeConvironment() {
        String switchStatus = new SPUtils(mContext).getString("switchStatus", "");
        switch (switchStatus) {
            case "on":
                Network.changEnvironment("http://testapi.51tzl.cn");
                Network.User.changeUserUrl(Network.USER);
                break;
            case "off":
                Network.changEnvironment("http://ktv.51tzl.cn");
                Network.User.changeUserUrl(Network.USER);
                break;
            default:
                break;
        }
    }

    private void initPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{
                    Manifest.permission.CAMERA,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{permission}, Constants.REQUEST_PERMISSION_CODE);
                }
            }
        }
    }

    private void initViews() {
        mVpLaunch = launchBinding.vpLaunch;
        mLlLaunchIndicator = launchBinding.llLaunchIndicator;
        mBtnLaunch = launchBinding.btnLaunchIntoApp;
        mIvLaunchAd = launchBinding.ivLaunchAd;
    }

    private void init() {
        if (mSpUtils.getBoolean(Constants.IS_FIRST, true)) {// 第一次使用APP

            // 初始化引导页
            initImageAndIndicator();
            initViewPager();
        } else {
           /* if (!isLogin()) {
                // 未登录状态，去登录
                toSignIn();
            } else {
                // 已登录，去主页
                startActivityToHome();
            }*/
            startActivityToHome();

        }
    }

    /**
     * 初始化引导页指示器
     */
    private void initImageAndIndicator() {
        imgs = new int[]{
                R.drawable.launch_1,
                R.drawable.launch_2,
                R.drawable.launch_3
        };
        imageViews = new ArrayList<>();
        ImageView imageView = null;
        View view = null;
        for (int i = 0; i < imgs.length; i++) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(imgs[i]).into(imageView);
            imageViews.add(imageView);

            view = new View(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dp2Px(8), UIUtils.dp2Px(8));
            if (i != 0) {
                layoutParams.setMargins(UIUtils.dp2Px(10), 0, 0, 0);
            }
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.drawable.selector_launch_indicator);
            mLlLaunchIndicator.addView(view);
        }
        mLlLaunchIndicator.getChildAt(0).setSelected(true);
    }

    /**
     * 初始化引导页
     */
    private void initViewPager() {
        mVpLaunch.setOffscreenPageLimit(2);
        mVpLaunch.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
//                super.destroyItem(container, position, object);
            }
        });

        mVpLaunch.setCurrentItem(0);
        prePosition = 0;

        mVpLaunch.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLlLaunchIndicator.getChildAt(prePosition).setSelected(false);
                mLlLaunchIndicator.getChildAt(position).setSelected(true);
                prePosition = position;
                if (position == imageViews.size() - 1) {
                    mBtnLaunch.setVisibility(View.VISIBLE);
                } else {
                    mBtnLaunch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBtnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpUtils.putBoolean(Constants.IS_FIRST, false);
                toSignIn();
            }
        });
    }

    /**
     * 去登录
     */
    private void toSignIn() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


    /**
     * 跳转主页
     */
    private void startActivityToHome() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_close_top);
        finish();
        overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_close_top);
    }

}
