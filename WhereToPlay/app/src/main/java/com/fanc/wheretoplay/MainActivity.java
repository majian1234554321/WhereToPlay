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
import android.util.DisplayMetrics;
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
import com.fanc.wheretoplay.util.BaiDuMapUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.MyViewPager;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.sdu.didi.openapi.DIOpenSDK;
import com.sdu.didi.openapi.DiDiWebActivity;

import java.util.ArrayList;
import java.util.List;

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


}
