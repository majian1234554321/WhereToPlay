package com.fanc.wheretoplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.activity.main.Main2Fragment;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.callback.IOnFocusListener;
import com.fanc.wheretoplay.datamodel.Version;
import com.fanc.wheretoplay.fragment.DiscoverFragment;
import com.fanc.wheretoplay.fragment.MineFragment;
import com.fanc.wheretoplay.fragment.PayFragment;
import com.fanc.wheretoplay.fragment.ReserveFragment;
import com.fanc.wheretoplay.fragment.ReserveFragment2;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DownloadUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.MyViewPager;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseFragmentActivity
        implements RadioGroup.OnCheckedChangeListener {

    List<Fragment> fragments;
    int screenWidth;
    ReserveFragment reserveFragment;
    long preClickTime;
    Receiver receiver;

    @BindView(R.id.mvp_main)
    MyViewPager mMvpMain;

    @BindView(R.id.rb1)
    RadioButton rb1;

    @BindView(R.id.rb2)
    RadioButton rb2;

    @BindView(R.id.rb3)
    RadioButton rb3;

    @BindView(R.id.rb4)
    RadioButton rb4;

    @BindView(R.id.ll_main_tab)
    RadioGroup llMainTab;

    @BindView(R.id.fragment)
    FrameLayout fragment;

    /** fragment 触摸监听 */
    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>();

    public String start = "1";
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);





        Intent intent = this.getIntent();

        spUtils = new SPUtils(this);
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            Unicorn.openServiceActivity(
                    this,
                    getResources().getString(R.string.app_name),
                    new ConsultSource(null, null, null));
        }

        initFragments();
        initPage();
        setListeners();
        registerBroadcastReceiver();

        if ("AAA".equals(spUtils.getString("AAA", ""))) {
            fragment.setVisibility(View.GONE);
        } else {
            start= "5";
            fragment.setBackgroundResource(R.drawable.main1);
        }

        fragment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (start) {
                            case "1":
                                fragment.setBackgroundResource(R.drawable.main1);
                                start = "5";
                                break;
                            case "2":
                                fragment.setBackgroundResource(R.drawable.main2);
                                start = "3";
                                break;
                            case "3":
                                fragment.setBackgroundResource(R.drawable.main3);
                                start = "4";
                                break;
                            case "4":
                                fragment.setBackgroundResource(R.drawable.main4);
                                start = "5";
                                break;
                            case "5":
                                fragment.setBackgroundResource(R.drawable.main5);
                                start = "GONE";
                                spUtils.putString("AAA", "AAA");
                                break;

                            case "GONE":
                                fragment.setVisibility(View.GONE);
                                break;

                            case "42":
                                fragment.setBackgroundResource(R.drawable.main42);
                                spUtils.putString("DDD", "DDD");
                                start = "GONE";

                                break;
                        }
                    }
                });
    }




    /** new fragment page */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ReserveFragment2());
        //  fragments.add(reserveFragment = new ReserveFragment());
        fragments.add(new Main2Fragment());

        //  fragments.add(new PayFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
    }

    /** show page */
    private void initPage() {
        mMvpMain.setAdapter(
                new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public int getCount() {
                        return fragments.size();
                    }

                    @Override
                    public Fragment getItem(int position) {
                        return fragments.get(position);
                    }
                });
        mMvpMain.setCurrentItem(0);
        mMvpMain.setOffscreenPageLimit(3);
        setDrawableTop(R.drawable.reserve_book_in, rb1, R.color.text_red);

        // 获取屏幕宽度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

    /** set selected listener and tab clicked listener */
    private void setListeners() {
        mMvpMain.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(
                            int position, float positionOffset, int positionOffsetPixels) {}

                    @Override
                    public void onPageSelected(int position) {
                        if ((position == 1 || position == 3)
                                && !mSpUtils.getBoolean(Constants.IS_SIGN_IN, false)) {
                            startActivity(new Intent(MainActivity.this, SignInActivity.class));
                            mMvpMain.setCurrentItem(position - 1);
                        } else {
                            mMvpMain.setCurrentItem(position);
                            switch (position) {
                                case 0:
                                    rb1.setChecked(true);
                                    break;
                                case 1:
                                    rb2.setChecked(true);
                                    break;
                                case 2:
                                    rb3.setChecked(true);
                                    break;
                                case 3:
                                    rb4.setChecked(true);
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });
        llMainTab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (reserveFragment instanceof IOnFocusListener) {
                reserveFragment.onWindowFocusChanged(hasFocus);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - preClickTime > 2000) {
            ToastUtils.showShortToast(this, UIUtils.getString(R.string.exit_app));
            preClickTime = System.currentTimeMillis();
        } else { // 双击退出程序
            App.closeApplication();
            super.onBackPressed();
        }
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_SIGN_OUT);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        setDrawableTop(R.drawable.reserve_book, rb1, R.color.reserve_unpress);
        setDrawableTop(R.drawable.reserve_pay, rb2, R.color.reserve_unpress);
        setDrawableTop(R.drawable.discover, rb3, R.color.reserve_unpress);
        setDrawableTop(R.drawable.mine, rb4, R.color.reserve_unpress);

        switch (i) {
            case R.id.rb1:
                rb1.setChecked(true);
                mMvpMain.setCurrentItem(0);
                setDrawableTop(R.drawable.reserve_book_in, rb1, R.color.text_red);
                break;
            case R.id.rb2:
                if (!mSpUtils.getBoolean(Constants.IS_SIGN_IN, false)) {
                    rb1.setChecked(true);
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                } else {
                    rb2.setChecked(true);
                    setDrawableTop(R.drawable.reserve_pay_in, rb2, R.color.text_red);
                    mMvpMain.setCurrentItem(1);
                }

                break;
            case R.id.rb3:
                if ("CCC".equals(spUtils.getString("CCC", ""))) {
                    fragment.setVisibility(View.GONE);
                } else {
                    fragment.setBackgroundResource(R.drawable.main31);
                    fragment.setVisibility(View.VISIBLE);
                    spUtils.putString("CCC", "CCC");
                    start = "GONE";
                }
                rb3.setChecked(true);
                setDrawableTop(R.drawable.discover_in, rb3, R.color.text_red);
                mMvpMain.setCurrentItem(2);

                break;
            case R.id.rb4:
                if (!mSpUtils.getBoolean(Constants.IS_SIGN_IN, false)) {
                    rb1.setChecked(true);
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                } else {
                    rb4.setChecked(true);

                    if ("DDD".equals(spUtils.getString("DDD", ""))) {
                        fragment.setVisibility(View.GONE);
                    } else {
                        fragment.setBackgroundResource(R.drawable.main42);
                        fragment.setVisibility(View.VISIBLE);
                        spUtils.putString("DDD", "DDD");
                        start = "GONE";
                    }

                    setDrawableTop(R.drawable.mine_in, rb4, R.color.text_red);
                    mMvpMain.setCurrentItem(3);
                }

                break;
        }
    }

    public void setDrawableTop(int resource, RadioButton radioButton, int color) {
        Drawable topDrawable = getResources().getDrawable(resource);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        radioButton.setCompoundDrawables(null, topDrawable, null, null);
        radioButton.setTextColor(getResources().getColor(color));
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
            receiver = null;
        }
        if (reserveFragment != null) {
            reserveFragment = null;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            if (listener != null) {
                listener.onTouch(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }

    public interface MyOnTouchListener {
        boolean onTouch(MotionEvent ev);
    }
}
