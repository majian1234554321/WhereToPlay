package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.fragment.AboutUsFragment;
import com.fanc.wheretoplay.fragment.AdviceFeedbackFragment;
import com.fanc.wheretoplay.fragment.CollectionFragment;
import com.fanc.wheretoplay.fragment.CommentDetailFragment;
import com.fanc.wheretoplay.fragment.DealDetailFragment;
import com.fanc.wheretoplay.fragment.DiscountCouponFragment;
import com.fanc.wheretoplay.fragment.IntegralFragment;
import com.fanc.wheretoplay.fragment.MerchantDetailFragment;
import com.fanc.wheretoplay.fragment.MessageDetailFragment;
import com.fanc.wheretoplay.fragment.MessageFragment;
import com.fanc.wheretoplay.fragment.MineInfoFragment;
import com.fanc.wheretoplay.fragment.OrderFragment;
import com.fanc.wheretoplay.fragment.ReferralFragment;
import com.fanc.wheretoplay.fragment.SetPayPwdFragment;
import com.fanc.wheretoplay.fragment.SystemNotifyFragment;
import com.fanc.wheretoplay.fragment.WaiterInfoFragment;
import com.fanc.wheretoplay.fragment.WalletFragment;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LogUtils;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2017/6/16.
 */

public class DetailActivity extends BaseFragmentActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_detail);
        init();
    }

    private void init() {
        intent = getIntent();
        initPage(intent.getStringExtra(Constants.PAGE));
    }

    private void initPage(String page) {
        switch (page) {
            case Constants.MERCHANT_DETAIL:
                Log.e("Iverson","MERCHANT_DETAIL");
                initFragment(new MerchantDetailFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID)));
                break;
            case Constants.MINE_INFO:
                Log.e("Iverson","MINE_INFO");
                initFragment(new MineInfoFragment());
                break;
            case Constants.MESSAGE:
                initFragment(new MessageFragment());
                break;
            case Constants.WALLET:
                initFragment(new WalletFragment());
                break;
            case Constants.DEAL_DETAIL:
                initFragment(new DealDetailFragment());
                break;
            case Constants.DISCOUNT_COUPON:
                initFragment(new DiscountCouponFragment().setChoose(intent.getBooleanExtra(Constants.IS_CHOOSE, false)));
                break;
            case Constants.INTEGRAL:
                Log.e("Iverson","INTEGRAL");
                initFragment(new IntegralFragment());
                break;
            case Constants.ORDER:
                initFragment(new OrderFragment());
                break;
            case Constants.COLLECTION:
                initFragment(new CollectionFragment());
                break;
            case Constants.REFERRAL:
                initFragment(new ReferralFragment());
                break;
            case Constants.SET_PAY_PWD:
                initFragment(new SetPayPwdFragment());
                break;
            case Constants.ADVICE_FEEDBACK:
                initFragment(new AdviceFeedbackFragment());
                break;
            case Constants.SYSTEM_NOTIFY:
                initFragment(new SystemNotifyFragment());
                break;
            case Constants.ABOUT_US:
                Log.e("Iverson","ABOUT_US");
                initFragment(new AboutUsFragment());
                break;
            case Constants.WAITER_INFO:
                Log.e("Iverson","WAITER_INFO");

                initFragment(new WaiterInfoFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID))
                .setSelect(intent.getBooleanExtra(Constants.IS_CHOOSE,false)));
                break;
//            case Constants.WAITER_PHOTO:
//                initFragment(new WaiterPhotoFragment().setBundle(intent.getExtras()), Constants.WAITER_PHOTO);
//                break;
            case Constants.MESSAGE_DETAIL:
                initFragment(new MessageDetailFragment().setMessageId(intent.getStringExtra(Constants.ID)));
                break;
            case Constants.COMMENT_DETAIL:
                initFragment(new CommentDetailFragment());
                break;
            default:
                break;
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_detail_container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 友盟分享回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
