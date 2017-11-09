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
import com.fanc.wheretoplay.fragment.MineCommendMoneyFragment;
import com.fanc.wheretoplay.fragment.MineFriendFragment;
import com.fanc.wheretoplay.fragment.MineInfoFragment;
import com.fanc.wheretoplay.fragment.OrderFragment;
import com.fanc.wheretoplay.fragment.OrderFragments;
import com.fanc.wheretoplay.fragment.ReferralFragment;
import com.fanc.wheretoplay.fragment.SetPayPwdFragment;
import com.fanc.wheretoplay.fragment.SystemNotifyFragment;
import com.fanc.wheretoplay.fragment.WalletFragment;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LogUtils;
import com.fanc.wheretoplay.util.ToastUtils;


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

    public String getStringExtra() {
        return intent.getStringExtra(Constants.STORE_ID);
    }

    private void initPage(String page) {
        switch (page) {
            case Constants.MERCHANT_DETAIL:
                initFragment(new MerchantDetailFragment().setStoreId(intent.getStringExtra(Constants.STORE_ID)));
                break;
            case Constants.MINE_INFO:
                initFragment(new MineInfoFragment());
                break;
            case Constants.MESSAGE:
                initFragment(new MessageFragment());
                break;
            case Constants.WALLET:
                initFragment(new WalletFragment());
                break;
            case Constants.DEAL_DETAIL:   //交易明细
                initFragment(new DealDetailFragment());
                break;
            case Constants.DISCOUNT_COUPON:
                initFragment(new DiscountCouponFragment().setChoose(intent.getBooleanExtra(Constants.IS_CHOOSE, false)));
                break;
            case Constants.INTEGRAL:
                initFragment(new IntegralFragment());
                break;
            case Constants.ORDER:   //订单
                initFragment(new OrderFragments());

                //startActivity(new Intent(this,ListOrderActivity.class));

                break;
            case Constants.COLLECTION:   //收藏
                initFragment(new CollectionFragment());
                break;
            case Constants.REFERRAL:   //推荐
                initFragment(new ReferralFragment());
                break;
            case Constants.SET_PAY_PWD:   //设置密码
                initFragment(new SetPayPwdFragment());
                break;
            case Constants.ADVICE_FEEDBACK:   //意见反馈
                initFragment(new AdviceFeedbackFragment());
                break;
            case Constants.SYSTEM_NOTIFY:   //系统通知
                initFragment(new SystemNotifyFragment());
                break;
            case Constants.ABOUT_US:   //关于我们
                initFragment(new AboutUsFragment());
                break;
            case Constants.MINEFRIEND:   //推荐好友
                initFragment(new MineFriendFragment());
                break;
            case Constants.MINEMONEY:   //推荐奖励
                initFragment(new MineCommendMoneyFragment());
                break;
            case Constants.MESSAGE_DETAIL:
                initFragment(new MessageDetailFragment().setMessageId(intent.getStringExtra(Constants.ID)));
                break;
            case Constants.COMMENT_DETAIL:
                initFragment(new CommentDetailFragment());
                break;
            case Constants.DRIVE:
                ToastUtils.makePicTextShortToast(this,"修复中，敬请期待！");
//                initFragment(new CommentDetailFragment());
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

    }
}
