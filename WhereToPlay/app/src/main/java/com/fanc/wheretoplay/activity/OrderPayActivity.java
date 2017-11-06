package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.pay.AliPayResult;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class OrderPayActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.tm_pay_bill)
    TopMenu tmPayBill;
    @BindView(R.id.tv_pay_bill_store)
    TextView tvPayBillStore;
    @BindView(R.id.tv_pay_bill_discount_real)
    TextView tvPayBillDiscountReal;
    @BindView(R.id.tv_pay_bill_address)
    TextView tvPayBillAddress;
    @BindView(R.id.tv_pay_bill_distance)
    TextView tvPayBillDistance;
    @BindView(R.id.et_pay_bill_consume_sum)
    EditText etPayBillConsumeSum;
    @BindView(R.id.cb_not_participation)
    CheckBox cbNotParticipation;
    @BindView(R.id.et_pay_bill_not_participation_discount_sum)
    EditText etPayBillNotParticipationDiscountSum;
    @BindView(R.id.tv_pay_bill_down_payment_sum)
    TextView tvPayBillDownPaymentSum;
    @BindView(R.id.tv_pay_bill_discount_coupon)
    TextView tvPayBillDiscountCoupon;
    @BindView(R.id.iv_pay_bill_discount_coupon)
    ImageView ivPayBillDiscountCoupon;
    @BindView(R.id.rb_pay_bill_weixin)
    RadioButton rbPayBillWeixin;
    @BindView(R.id.rb_pay_bill_ali)
    RadioButton rbPayBillAli;
    @BindView(R.id.rb_pay_bill_balance)
    RadioButton rbPayBillBalance;
    @BindView(R.id.rg_pay_bill)
    RadioGroup rgPayBill;
    @BindView(R.id.tv_pay_bill_pay_sum_real)
    TextView tvPayBillPaySumReal;
    @BindView(R.id.btn_pay_bill)
    Button btnPayBill;
    private IWXAPI wxApi;
    private String order_idValue;
    private String store_idValue;

    String discountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        ButterKnife.bind(this);

        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID);
        wxApi.registerApp(Constants.WX_APP_ID);
        rgPayBill.setOnCheckedChangeListener(this);

        order_idValue = getIntent().getStringExtra("order_id");
        store_idValue = getIntent().getStringExtra("store_id");



    }

    @OnClick(R.id.btn_pay_bill)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pay_bill:
                switch (payWay) {
                    case 1:
                        payOrder();
                        break;
                    case 2:
                        payOrder();
                        break;
                    case 3:
                        break;
                }
                break;

        }
    }


    private void payOrder() {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        params.put(Network.Param.ORDER_ID, order_idValue);
        params.put(Network.Param.MONEY, tvPayBillPaySumReal.getText().toString());
        params.put(Network.Param.TYPE, String.valueOf(payWay));
        params.put(Network.Param.REMAIN, String.valueOf(0));
        params.put(Network.Param.DISPLAY_BALANCE, etPayBillConsumeSum.getText().toString());
        if (cbNotParticipation.isChecked()) {
            params.put(Network.Param.FEE, etPayBillNotParticipationDiscountSum.getText().toString());
        } else {
            params.put(Network.Param.FEE, String.valueOf(0));
        }
//        params.put(Network.Param.DISCOUNT, mTvDiscountSum.getText().toString());
        if (discountId != null) {
            params.put(Network.Param.COUPON_ID, discountId);
        }
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_ORDER_PAYOFF)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(String response) {
                        Logger.json(response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject json = object.getJSONObject("content");
                            if (payWay==1) {// 支付宝
                                order_idValue = json.getString("orderform_id");
                                discountId = json.getString("coupon_id");
                                // 唤起支付宝
//                                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);// 沙箱环境

                                aliPay(json.getString("orderString"));
                            }
                            if (payWay ==2) {// 微信
                                wxPay(json);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        closeProgress();
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
                PayTask alipay = new PayTask(OrderPayActivity.this);
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
    private Handler mHandler = new Handler() {
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
                checkAliPayResult(resultInfo, order_idValue, discountId);
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
     * @param coupon_id
     */
    private void checkAliPayResult(String resultInfo, String orderId, String coupon_id) {
//        ToastUtils.makePicTextShortToast(mContext, "支付成功");
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_ORDER_PAYOFF_VALIDATE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ORDERFORM_ID, orderId)
                .addParams(Network.Param.CONTENT, resultInfo)
                .addParams(Network.Param.COUPON_ID, coupon_id)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            ToastUtils.makePicTextShortToast(mContext, response.getInfo());
                            if (response.isResult()) {
                                paySuccess();
                            }
                        }
                    }
                });
    }



    // 支付成功去评价
    private void paySuccess() {
        // 去评价
        Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.TO_EVALUATE);
        intent.putExtra(Constants.STORE_ID, store_idValue);
        intent.putExtra(Constants.ORDER_ID, order_idValue);
        intent.putExtra(Constants.PRICE, tvPayBillPaySumReal.getText().toString());
        intent.putExtra(Constants.IS_COMMENT, true);
        startActivity(intent);
        // 通知消费页面
        Intent intent1 = new Intent(Constants.ACTION_PAY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent1);
        finish();
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











    public int payWay = Constants.PAY_WAY_WEIXIN;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_pay_bill_weixin:
                payWay = Constants.PAY_WAY_WEIXIN;
                break;
            case R.id.rb_pay_bill_ali:
                payWay = Constants.PAY_WAY_WEIXIN;
                break;
            case R.id.rb_pay_bill_balance:
                payWay = Constants.PAY_WAY_BALANCE;
                break;

        }
    }
}
