package com.fanc.wheretoplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.callback.IOnFocusListener;
import com.fanc.wheretoplay.databinding.ActivityMainBinding;
import com.fanc.wheretoplay.fragment.DiscoverFragment;
import com.fanc.wheretoplay.fragment.MineFragment;
import com.fanc.wheretoplay.fragment.PayFragment;
import com.fanc.wheretoplay.fragment.ReserveFragment;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.MyViewPager;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseFragmentActivity {
    ActivityMainBinding mainBinding;
    /**
     * 主页
     */
    MyViewPager mMvpMain;
    /**
     * 未选中时小图
     */
    ImageView mIvReserve;
    ImageView mIvPay;
    ImageView mIvDiscover;
    private ImageView mIvMine;
    /**
     * 底部导航
     */
    LinearLayout mLlTabReserve;
    LinearLayout mLlTabPay;
    LinearLayout mLlTabDiscover;
    private LinearLayout mLlMine;
    private TextView tvMainTabDiscover;
    private TextView tvMainTabMine;
    private TextView tvMainTabPay;
    private TextView tvMainTabReserve;

    /**
     * 选中的导航栏
     */
    int selectedId;
    /**
     * 主页fragment
     */
    List<Fragment> fragments;
    /**
     * 屏幕宽度
     */
    int screenWidth;
    /**
     * 选中状态的LayoutParams
     */
    /**
     * 预定
     */
    ReserveFragment reserveFragment;
    /**
     * 上一次点击的时间
     */
    long preClickTime;
    /**
     * 退出登录广播
     */
    Receiver receiver;
    /**
     * fragment 触摸监听
     */
    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Intent intent = this.getIntent();
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            Unicorn.openServiceActivity(this, getResources().getString(R.string.app_name), new ConsultSource(null, null, null));
        }
        init();
        initFragments();
        initPage();
        setListeners();
        registerBroadcastReceiver();
    }

    /**
     * init views
     */
    private void init() {
        mMvpMain = mainBinding.mvpMain;
        //底部选择栏
        mIvReserve = mainBinding.ivMainTabReserve;
        mIvPay = mainBinding.ivMainTabPay;
        mIvDiscover = mainBinding.ivMainTabDiscover;
        mIvMine = mainBinding.ivMainTabMine;
        mLlTabReserve = mainBinding.llMaintabReserve;
        mLlTabPay = mainBinding.llMainTabPay;
        mLlTabDiscover = mainBinding.llMainTabDiscover;
        mLlMine = mainBinding.llMainTabMine;
        tvMainTabDiscover = mainBinding.tvMainTabDiscover;
        tvMainTabMine = mainBinding.tvMainTabMine;
        tvMainTabPay = mainBinding.tvMainTabPay;
        tvMainTabReserve = mainBinding.tvMainTabReserve;
//        Log.w("llm", "MainActivity（133）：RegistrationID =  " + JPushInterface.getRegistrationID(this));
    }

    /**
     * new fragment page
     */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(reserveFragment = new ReserveFragment());   //添加预定Fragment
        fragments.add(new PayFragment());   //添加支付Fragment
        fragments.add(new DiscoverFragment());   //添加发现Fragment
        fragments.add(new MineFragment());   //添加我的Fragment
    }

    /**
     * show page
     */
    private void initPage() {
        mMvpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        // 获取屏幕宽度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        setSelectedPage(0);
    }



    private void setSelectedPage(int position) {
        setSelectedId(position);
        setSelectedButton(position);
    }

    /**
     * 设置选中的页面
     *
     * @param position
     */
    private void setSelectedButton(int position) {
        mIvReserve.setImageResource(R.drawable.reserve_book);
        mIvPay.setImageResource(R.drawable.reserve_pay);
        mIvDiscover.setImageResource(R.drawable.discover);
        mIvMine.setImageResource(R.drawable.mine);
        tvMainTabDiscover.setTextColor(getResources().getColor(R.color.reserve_unpress));
        tvMainTabMine.setTextColor(getResources().getColor(R.color.reserve_unpress));
        tvMainTabPay.setTextColor(getResources().getColor(R.color.reserve_unpress));
        tvMainTabReserve.setTextColor(getResources().getColor(R.color.reserve_unpress));

        // 选中的大图标显示，小图标隐藏
        switch (position) {
            case 0:
                mIvReserve.setImageResource(R.drawable.reserve_book_in);
                tvMainTabReserve.setTextColor(getResources().getColor(R.color.text_red));
                break;
            case 1:
                mIvPay.setImageResource(R.drawable.reserve_pay_in);
                tvMainTabPay.setTextColor(getResources().getColor(R.color.text_red));
                break;
            case 2:
                mIvDiscover.setImageResource(R.drawable.discover_in);
                tvMainTabDiscover.setTextColor(getResources().getColor(R.color.text_red));
                break;
            case 3:
                mIvMine.setImageResource(R.drawable.mine_in);
                tvMainTabMine.setTextColor(getResources().getColor(R.color.text_red));
                break;
            default:
                break;
        }
    }

    /**
     * set selected listener and tab clicked listener
     */
    private void setListeners() {
        mMvpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0) {
                    switch (position) {
                        case 0:
//                            mIvPayBig.setAlpha(0F);
//                            mIvDiscoverBig.setAlpha(0F);
                            break;
                        case 1:
//                            mIvReserveBig.setAlpha(0F);
//                            mIvDiscoverBig.setAlpha(0F);
                            break;
                        case 2:
//                            mIvReserveBig.setAlpha(0F);
//                            mIvPayBig.setAlpha(0F);
                            break;
                    }
                }
                //去掉底部选择栏的滑动块
//                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mFlMainTab.getLayoutParams();
//                layoutParams.leftMargin = (int) (screenWidth / 3 * (position + positionOffset));
//                mFlMainTab.setLayoutParams(layoutParams);
//                if (positionOffset > 0) {
//                    if (position == 0) {
//                        // 预定大图标和支付小图标，向右滑动逐渐隐藏，向左滑动逐渐显示
////                        mIvReserveBig.setAlpha(1 - positionOffset);
////                        mIvPay.setAlpha(1 - positionOffset);
//                        // 预定小图标和支付大图标，向右滑动逐渐显示，向左滑动逐渐隐藏
////                        mIvPayBig.setAlpha(positionOffset);
//                        mIvReserve.setAlpha(positionOffset);
//                    }
//                    if (position == 1) {
//                        // 支付大图标和发现小图标，向右滑动逐渐隐藏，向左滑动逐渐显示
//                        mIvDiscover.setAlpha(1 - positionOffset);
////                        mIvPayBig.setAlpha(1 - positionOffset);
//                        // 支付小图标和发现大图标，向右滑动逐渐显示，向左滑动逐渐隐藏
////                        mIvDiscoverBig.setAlpha(positionOffset);
//                        mIvPay.setAlpha(positionOffset);
//                    }
//                }

            }

            @Override
            public void onPageSelected(int position) {
                setSelectedPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void doClick(View view) {
        switch (view.getId()) {
            //切换viewpager，if判断是防止二次加载
            case R.id.ll_maintab_reserve:
                if (selectedId != R.id.ll_maintab_reserve) {
                    mMvpMain.setCurrentItem(0);
                    setSelectedPage(0);
                }
                break;
            case R.id.ll_main_tab_pay:
                if (selectedId != R.id.ll_main_tab_pay) {
                    mMvpMain.setCurrentItem(1);
                    setSelectedPage(1);
                }
                break;
            case R.id.ll_main_tab_discover:
                if (selectedId != R.id.ll_main_tab_discover) {
                    mMvpMain.setCurrentItem(2);
                    setSelectedPage(2);
                }
                break;
            case R.id.ll_main_tab_mine:
                if (selectedId != R.id.ll_main_tab_mine) {
                    mMvpMain.setCurrentItem(3);
                    setSelectedPage(3);
                }
//            case R.id.cl_sideslip:
            default:
                break;
        }
    }

    /**
     * 设置选中页面的导航栏按钮id
     *
     * @param position
     */
    private void setSelectedId(int position) {
        switch (position) {
            case 0:
                selectedId = R.id.ll_maintab_reserve;
                break;
            case 1:
                selectedId = R.id.ll_main_tab_pay;
                break;
            case 2:
                selectedId = R.id.ll_main_tab_discover;
                break;
            case 3:
                selectedId = R.id.ll_main_tab_mine;
            default:
                break;
        }
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
            } else {//双击退出程序
                App.closeApplication();
                super.onBackPressed();
            }
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_SIGN_OUT);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
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
        public boolean onTouch(MotionEvent ev);
    }
//
//    private void test() {
//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
//                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/ceshi.apk");
//            }
//        });
//
//        binding.tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ((SampleApplication) MainActivity.this.getApplication()).closeApplication();
//                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
//                android.os.Process.killProcess(android.os.Process.myPid());
//            }
//        });
//        binding.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showInfo(MainActivity.this);
//            }
//        });
//        binding.button.setText(Float.floatToIntBits(5f) + "");
//    }
//
//    public boolean showInfo(Context context) {
//        // add more Build Info
//        final StringBuilder sb = new StringBuilder();
//        Tinker tinker = Tinker.with(getApplicationContext());
//        if (tinker.isTinkerLoaded()) {
//            sb.append(String.format("[patch is loaded] \n"));
//            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
//            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));
//
//            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
//            sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
//            sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
//            sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));
//
//        } else {
//            sb.append(String.format("[patch is not loaded] \n"));
//            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
//            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));
//
//            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
//            sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
//        }
//        sb.append(String.format("[BaseBuildInfo Message] %s \n", BaseBuildInfo.TEST_MESSAGE));
//
//        final TextView v = new TextView(context);
//        v.setText(sb);
//        v.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
//        v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
//        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        v.setTextColor(0xFF000000);
//        v.setTypeface(Typeface.MONOSPACE);
//        final int padding = 16;
//        v.setPadding(padding, padding, padding, padding);
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setCancelable(true);
//        builder.setView(v);
//        final AlertDialog alert = builder.create();
//        alert.show();
//        return true;
//    }

}
