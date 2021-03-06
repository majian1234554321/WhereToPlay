package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
//import com.fanc.wheretoplay.activity.EvaluationSuccessActivity;
import com.fanc.wheretoplay.activity.DisplayActivity;
import com.fanc.wheretoplay.activity.ListOrderActivity;
import com.fanc.wheretoplay.activity.SettingsActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMineBinding;
import com.fanc.wheretoplay.datamodel.DataValue;
import com.fanc.wheretoplay.datamodel.Score;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.CircleImageView;
import com.fanc.wheretoplay.view.ItemView;
import com.fanc.wheretoplay.view.TopMenu;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.sdu.didi.openapi.DIOpenSDK;
import com.sdu.didi.openapi.DiDiWebActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.Call;

import static com.fanc.wheretoplay.util.LocationUtils.mLocationClient;

/**
 * Created by Administrator on 2017/6/14.
 */

public class MineFragment extends BaseFragment {

    FragmentMineBinding mineBinding;

    TopMenu mTmMine;
    CircleImageView mCivMine;
    TextView mTvMimeNickname;
    TextView mTvMimeMobile;
    TextView mTvMimeIntegral;
    ItemView mIvMineMessage;
    ItemView mIvMineWallet;
    ItemView mIvMineDiscountCoupon;
    ItemView mIvMineIntegral;
    ItemView mIvMineOrder;
    ItemView mIvMineCollection;
    ItemView mIvMineReferral,iv1234;
    private ItemView mIvMineFriend;
    private ItemView mIvMineMoney;
    private ItemView mTvMineDrive;

    Receiver receiver;
    private ItemView mIvMineService;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, null, false);
        initViews();
        init();
        setListeners();
        return mineBinding.getRoot();
    }

    private void initViews() {
        mTmMine = mineBinding.tmMine;
        mCivMine = mineBinding.civMine;
        mTvMimeNickname = mineBinding.tvMineNickname;
        mTvMimeMobile = mineBinding.tvMineMobile;
        mTvMimeIntegral = mineBinding.tvMineIntegral;
        mIvMineMessage = mineBinding.ivMineMessage;
        mIvMineWallet = mineBinding.ivMineWallet;
        mIvMineDiscountCoupon = mineBinding.ivMineDiscountCoupon;
        mIvMineIntegral = mineBinding.ivMineIntegral;
        mIvMineOrder = mineBinding.ivMineOrder;
        mIvMineCollection = mineBinding.ivMineCollection;
        mIvMineReferral = mineBinding.ivMineReferral;
        mTvMineDrive = mineBinding.ivMineDrive;
        mIvMineFriend = mineBinding.ivMineCommendFriend;
        mIvMineMoney = mineBinding.ivMineCommendMoney;
        mIvMineService = mineBinding.ivMineService;
        iv1234 =   mineBinding.iv1234;
    }

    private void init() {
//        mTmMine.setLeftIcon(R.drawable.left);
        mTmMine.setTitle(R.string.mine);
        mTmMine.setTitleColor(getResources().getColor(R.color.white));
        mTmMine.setRightIcon(R.drawable.settings);

        mineBinding.setClick(this);
        showSimpleMineInfo();
        // 获取我的资料
        getScore();
        iv1234.setIcon(R.drawable.iv_1234);
        iv1234.setText("娱乐行业从业申请");
        iv1234.setRightIcon(R.drawable.right);

        //条目栏的文字、图片设置
        mIvMineFriend.setIcon(R.drawable.mine_recommend_friend);
        mIvMineFriend.setText(R.string.friend);
        mIvMineFriend.setLeftTextBlod(true);
        mIvMineFriend.setRightIcon(R.drawable.right);
        mIvMineMoney.setIcon(R.drawable.mine_recommend_money);
        mIvMineMoney.setText(R.string.money);
        mIvMineMoney.setLeftTextBlod(true);
        mIvMineMoney.setRightIcon(R.drawable.right);
        mIvMineMessage.setIcon(R.drawable.mine_message);
        mIvMineMessage.setText(R.string.message);
        mIvMineMessage.setLeftTextBlod(true);
        mIvMineMessage.setRightIcon(R.drawable.right);
        mIvMineWallet.setIcon(R.drawable.mine_wallet);
        mIvMineWallet.setText(R.string.wallet);
        mIvMineWallet.setLeftTextBlod(true);
        mIvMineWallet.setRightIcon(R.drawable.right);
        mIvMineDiscountCoupon.setIcon(R.drawable.mine_discount_coupon);
        mIvMineDiscountCoupon.setText(R.string.discount_coupon);
        mIvMineDiscountCoupon.setLeftTextBlod(true);
        mIvMineDiscountCoupon.setRightIcon(R.drawable.right);
        mIvMineIntegral.setIcon(R.drawable.mine_integral);
        mIvMineIntegral.setText(R.string.integral);
        mIvMineIntegral.setLeftTextBlod(true);
        mIvMineIntegral.setRightIcon(R.drawable.right);
        mIvMineOrder.setIcon(R.drawable.mine_order);
        mIvMineOrder.setText(R.string.order);
        mIvMineOrder.setLeftTextBlod(true);
        mIvMineOrder.setRightIcon(R.drawable.right);
        mIvMineCollection.setIcon(R.drawable.mine_collection);
        mIvMineCollection.setText(R.string.collection);
        mIvMineCollection.setLeftTextBlod(true);
        mIvMineCollection.setRightIcon(R.drawable.right);
        mIvMineReferral.setIcon(R.drawable.mine_referral);
        mIvMineReferral.setText(R.string.referral);
        mIvMineReferral.setLeftTextBlod(true);
        mIvMineReferral.setRightIcon(R.drawable.right);
        mTvMineDrive.setIcon(R.drawable.mine_driver);
        mTvMineDrive.setText(R.string.drive);
        mTvMineDrive.setLeftTextBlod(true);
        mTvMineDrive.setRightIcon(R.drawable.right);
        mIvMineService.setText(R.string.mine_service);
        mIvMineService.setLeftTextBlod(true);
        mIvMineService.setIcon(R.drawable.mine_service);
        mIvMineService.setRightIcon(R.drawable.right);


        RxBus.getDefault().toFlowable(Intent.class)
                .subscribe(new Consumer<Intent>() {
                    @Override
                    public void accept(Intent intent) throws Exception {
                        if (intent.getStringExtra(Constants.URL) != null) {
                            Glide.with(mContext).load(intent.getStringExtra(Constants.URL)).into(mCivMine);
                        }

                        if (intent.getStringExtra(Constants.NAME) != null) {
                            mTvMimeNickname.setText(intent.getStringExtra(Constants.NAME));
                        }

                        if (intent.getStringExtra(Constants.MOBILE) != null) {
                            mTvMimeMobile.setText(intent.getStringExtra(Constants.MOBILE));
                        }




                    }
                });
        // registerBroadcastReceiver();
    }

    private void setListeners() {

        mTmMine.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showSimpleMineInfo() {
        Glide.with(mContext).load(Network.IMAGE + mUser.getAvatar()).into(mCivMine);
        if (TextUtils.isEmpty(mUser.getNickname())) {
            mTvMimeNickname.setText(R.string.nickname);
        } else {
            mTvMimeNickname.setText(mUser.getNickname());
        }
        mTvMimeMobile.setText(mUser.getMobile());
    }

    /**
     * 获取积分
     */
    private void getScore() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_HOME)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<Score>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Score response) {
                        if (isSuccess(response)) {
                            if (response.getScore() != null) {
                                mTvMimeIntegral.setText(response.getScore() + "分");
                            }
                        }
                    }
                });
    }


    /**
     * 注册广播
     */
    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_AVATAR_MODIFY_SUCCESS);
        filter.addAction(Constants.ACTION_MINE_INFO_MODIFY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constants.ACTION_AVATAR_MODIFY_SUCCESS.equals(action)) {
                Glide.with(mContext).load(intent.getStringExtra(Constants.URL)).into(mCivMine);
            } else if (Constants.ACTION_MINE_INFO_MODIFY_SUCCESS.equals(action)) {
                mTvMimeNickname.setText(intent.getStringExtra(Constants.NAME));
                mTvMimeMobile.setText(intent.getStringExtra(Constants.MOBILE));
            }
        }
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_info:
                modifyMineInfo();
                break;
            case R.id.iv_mine_message:
                goToNewPage(Constants.MESSAGE);
                break;
            case R.id.iv_mine_wallet:   //钱包
                goToNewPage(Constants.WALLET);
                break;
            case R.id.iv_mine_discount_coupon:
                Intent intent = new Intent(mContext, DisplayActivity.class);
                intent.putExtra("DISPLAYTYPE", "CouponFragment");
                startActivity(intent);
                break;
            case R.id.iv_mine_integral:
                goToNewPage(Constants.INTEGRAL);
                break;
            case R.id.iv_mine_order:
                //goToNewPage(Constants.ORDER);
                startActivity(new Intent(getContext(), ListOrderActivity.class));
                //startActivity(new Intent(getContext(),EvaluationSuccessActivity.class));
                break;
            case R.id.iv_mine_collection:
                goToNewPage(Constants.COLLECTION);
                break;
            case R.id.iv_mine_referral:
                goToNewPage(Constants.REFERRAL);
                break;
            case R.id.iv_mine_commend_friend:
                goToNewPage(Constants.MINEFRIEND);
                break;
            case R.id.iv_mine_commend_money:
                goToNewPage(Constants.MINEMONEY);
                break;
            case R.id.iv_mine_drive:
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("lat", DataValue.latitude);
                map.put("lng", DataValue.longitude);
                map.put("maptype", "wgs84");
                DIOpenSDK.showDDPage(mContext, map);
                break;
            case R.id.iv_mine_service:
                ConsultSource source = new ConsultSource(null, null, null);
                Unicorn.openServiceActivity(mContext, getResources().getString(R.string.app_name), source);

                break;
            case R.id.iv_1234:
                Intent intent2 = new Intent(mContext, DisplayActivity.class);
                intent2.putExtra("DISPLAYTYPE", "BGirlFragment");
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    /**
     * 修改我的资料
     */
    private void modifyMineInfo() {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra(Constants.PAGE, Constants.MINE_INFO);
        startActivity(intent);
    }

    private void goToNewPage(String newPage) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra(Constants.PAGE, newPage);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
