package com.fanc.wheretoplay.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.fragment.BriefFragment;
import com.fanc.wheretoplay.fragment.CommentFragment;
import com.fanc.wheretoplay.fragment.DateFragment;
import com.fanc.wheretoplay.fragment.DrinksFragment;
import com.fanc.wheretoplay.fragment.ListFragment;
import com.fanc.wheretoplay.fragment.MerchantHouseNewsFragment;
import com.fanc.wheretoplay.fragment.OrderToCompleteFragment;
import com.fanc.wheretoplay.fragment.ReserveInfoFragment;
import com.fanc.wheretoplay.fragment.MerchantRoomFragment;
import com.fanc.wheretoplay.fragment.SearchFragment;
import com.fanc.wheretoplay.fragment.ToEvaluateFragment;
import com.fanc.wheretoplay.util.Constants;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ReuseActivity extends BaseFragmentActivity {

    Intent intent;
    Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse);
        init();
    }

    private void init() {
        intent = getIntent();
        initPage(intent.getStringExtra(Constants.PAGE));
        registerBroadcastReceiver();
    }

    private void initPage(String page) {
        switch (page) {
            case Constants.SEARCH:
                initFragment(new SearchFragment());
                break;
//                去掉标题栏的“我的”图标
//            case Constants.MINE:
//                initFragment(new MineFragment());
//                break;
            case Constants.TO_EVALUATE:
                initFragment(new ToEvaluateFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID))
                        .setOrderId(intent.getStringExtra(Constants.ORDER_ID))
                        .setComment(intent.getBooleanExtra(Constants.IS_COMMENT, false))
                        .setPrice(intent.getStringExtra(Constants.PRICE))
                        .isCreditReserve(intent.getBooleanExtra(Constants.CREDIT_RESERVE, false)));
                break;
            case Constants.COMMENT:
                initFragment(new CommentFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID))
                        .setOrderId(intent.getStringExtra(Constants.ORDER_ID)));
                break;
            case Constants.RESERVE_INFO:
                initFragment(new ReserveInfoFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID)));
                break;
            case Constants.LIST_PAGE:
                initFragment(new ListFragment().setListPage(intent.getIntExtra(Constants.PARAM, 0)));
                break;


                //去掉活动界面跳转
//            case Constants.ACTION:   //活动
//                initFragment(new ActionFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID)));
//                break;
            case Constants.HOUSENEWS:   //房态
                initFragment(new MerchantHouseNewsFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID))
                .setStoreAddress(intent.getStringExtra(Constants.ADDRESS))
                .setStoreName(intent.getStringExtra(Constants.STORE_NAME))
                .setStoreDiscount(intent.getStringExtra(Constants.DISCOUNT_COUPON))
                );
                break;
            case Constants.DRINKS:   //酒水
                initFragment(new DrinksFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID))
                        .setStoreName(intent.getStringExtra(Constants.STORE_NAME))
                        .setStoreAddress(intent.getStringExtra(Constants.ADDRESS))
                        .setStoreDiscount(intent.getStringExtra(Constants.DISCOUNT_COUPON)));
                break;
            case Constants.ROOM:   //房型
                initFragment(new MerchantRoomFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID))
                        .setStoreName(intent.getStringExtra(Constants.STORE_NAME))
                        .setStoreAddress(intent.getStringExtra(Constants.ADDRESS))
                        .setStoreDiscount(intent.getStringExtra(Constants.DISCOUNT_COUPON))
                        .setSelect(intent.getBooleanExtra(Constants.IS_CHOOSE, false)));
                break;
            case Constants.BRIEF:   //简介
                initFragment(new BriefFragment().setBriefUrl(intent.getStringExtra(Constants.DATA))
                        .setStoreName(intent.getStringExtra(Constants.STORE_NAME))
                        .setStoreAddress(intent.getStringExtra(Constants.ADDRESS))
                        .setStoreDiscount(intent.getStringExtra(Constants.DISCOUNT_COUPON)));
                break;
            case Constants.DATE:
                initFragment(new DateFragment().setReserveWay(intent.getStringExtra(Constants.TYPE))
                        .setLastTime(intent.getStringExtra(Constants.TIMES)));
                break;
            case Constants.ORDER_TO_COMPLETE:
                initFragment(new OrderToCompleteFragment().setOrderId(intent.getStringExtra(Constants.ORDER_ID)));
                break;
            default:
                break;
        }
    }

    //初始化fragment
    private void initFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_reuse_container, fragment);
        transaction.commit();
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
            ReuseActivity.this.overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
            receiver = null;
        }
    }

}
