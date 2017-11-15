package com.fanc.wheretoplay.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentWalletBinding;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.Wallet;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.pay.AliPayResult;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.orhanobut.logger.Logger;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */

public class WalletFragment extends BaseFragment {

    FragmentWalletBinding walletBinding;

    TopMenu mTmWallet;
    TextView mTvBalance;
    EditText mEtRechargeSum;
    RadioButton mRbWalletWeixin;
    RadioButton mRbWalletAli;
    // 充值类型，1支付宝，2微信
    int type = 2;
    // 充值金额
    double rechargeSum;

    String orderId;
    IWXAPI wxApi;

    Receiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        walletBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, null, false);
        initViews();
        init();
        setListeners();
        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID);
        wxApi.registerApp(Constants.WX_APP_ID);
        return walletBinding.getRoot();
    }

    private void initViews() {
        mTmWallet = walletBinding.tmWallet;
        mTvBalance = walletBinding.tvWalletBalance;
        mEtRechargeSum = walletBinding.etWalletSum;
        mRbWalletWeixin = walletBinding.rbWalletWeixin;
        mRbWalletAli = walletBinding.rbWalletAli;
    }

    private void setListeners() {
        mTmWallet.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTmWallet.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.PAGE, Constants.DEAL_DETAIL);
                startActivity(intent);
            }
        });
    }

    private void init() {
        mTmWallet.setLeftIcon(R.drawable.left);
        mTmWallet.setTitle(R.string.mine_wallet);
        mTmWallet.setTitleColor(getResources().getColor(R.color.white));
        mTmWallet.setRightText(R.string.deal_detail);
        mTmWallet.setRightTextColor(getResources().getColor(R.color.white));

        walletBinding.setClick(this);

        queryBalance();

        registerBroadcastReceiver();
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wallet_weixin:
                mRbWalletWeixin.setChecked(true);
                break;
            case R.id.ll_wallet_ali:
                mRbWalletAli.setChecked(true);
                break;
            case R.id.btn_wallet_recharge:
                recharge();
                // 修改显示金额
                break;
            default:
                break;
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_wallet_weixin:
                type = 2;
                break;
            case R.id.rb_wallet_ali:
                type = 1;
                break;
            default:
                break;
        }
    }

    /**
     * 查询余额
     */
    private void queryBalance() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_MY_WALLET)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<Wallet>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Wallet response) {
                        if (isSuccess(response)) {
                            if (response.getBalance() == null || response.getBalance().isEmpty()) {
                                mTvBalance.setText("0元");
                            } else {
                                mTvBalance.setText(response.getBalance() + UIUtils.getString(R.string.currency));
                            }
                        }
                    }
                });

    }

    /**
     * 充值,范围1-10w
     */
    private void recharge() {
        String s = mEtRechargeSum.getText().toString().trim();
        if (s.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, "请输入充值金额");
            return;
        }
        rechargeSum = Double.parseDouble(s);
        if (rechargeSum < 1 || rechargeSum > 100000) {
            ToastUtils.makePicTextShortToast(mContext, "充值范围为1-100000元");
            return;
        }

        OkHttpUtils.post()
                .url(Network.User.USER_USER_RECHARGE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.TYPE, String.valueOf(type))
                .addParams(Network.Param.MONEY, s)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.w("aaa", response);
                        Logger.d(response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject json = object.getJSONObject("content");
                            if (type == 1) {// 支付宝
                                orderId = json.getString("orderform_id");
                                // 唤起支付宝
//                                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);// 沙箱环境

                                aliPay(json.getString("orderString"));
                            }
                            if (type == 2) {// 微信
                                wxPay(json);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    /**
     * 支付宝支付
     *
     * @param orderString
     */
    private void aliPay(final String orderString) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mContext);
                Map<String, String> result = alipay.payV2(orderString, true);

                Message msg = new Message();
                msg.what = ALI_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private final int ALI_PAY = 1;
    @SuppressLint("HandlerLeak")
    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                checkAliPayResult(resultInfo, orderId);
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                ToastUtils.showShortToast(mContext, "支付失败");
            }
        }
    };

    /**
     * 支付结果验证
     *
     * @param resultInfo
     * @param orderId
     */
    private void checkAliPayResult(String resultInfo, String orderId) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_RECHARGE_ALIPAY_VALIDA)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ORDERFORM_ID, orderId)
                .addParams(Network.Param.CONTENT, resultInfo)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            if (response.isResult()) {
                                // 修改显示金额
                                String s = mTvBalance.getText().toString().trim();
                                s = s.substring(0, s.length() - 1);
                                double sum = Double.parseDouble(s);
                                sum += rechargeSum;
                                mTvBalance.setText(new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP) + UIUtils.getString(R.string.currency));
                                ToastUtils.makePicTextShortToast(mContext, response.getInfo());
                            }
                        }
                    }
                });
    }

    /**
     * 微信支付
     *
     * @param json
     * @throws JSONException
     */
    private void wxPay(JSONObject json) throws JSONException {
        PayReq req = new PayReq();
//        req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = json.getString("appid");
        req.partnerId = json.getString("partnerid");
        req.prepayId = json.getString("prepayid");
        req.nonceStr = json.getString("noncestr");
        req.timeStamp = json.getString("timestamp");
        req.packageValue = json.getString("package");
        req.sign = json.getString("sign");
//        req.extData = "app data"; // optional
//        Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        wxApi.sendReq(req);
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_WXPAY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 修改显示金额
            String s = mTvBalance.getText().toString().trim();
            s = s.substring(0, s.length() - 1);
            double sum = Double.parseDouble(s);
            sum += rechargeSum;
            mTvBalance.setText(sum + UIUtils.getString(R.string.currency));
        }
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
