package com.fanc.wheretoplay.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;

import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.OrderInfoModel;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.pay.AliPayResult;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/15.
 */

public class DownPaymentActivity extends BaseActivity {


    //店铺id
    String storeId;
    // 订单参数
    HashMap<String, String> params;
    // 预定类型
    String reserveType;
    // 支付方式
    int payWay;
    // 支付价格
    String price;
    // 订单id
    String orderId;

    IWXAPI wxApi;
    Receiver receiver;
    @BindView(R.id.tm_down_payment)
    TopMenu tmDownPayment;
    @BindView(R.id.tv_down_payment_title)
    TextView tvDownPaymentTitle;
    @BindView(R.id.tv_down_payment_time_1)
    TextView tvDownPaymentTime1;
    @BindView(R.id.tv_down_payment_time)
    TextView tvDownPaymentTime;
    @BindView(R.id.tv_down_payment_room_category)
    TextView tvDownPaymentRoomCategory;
    @BindView(R.id.tv_down_payment_room)
    TextView tvDownPaymentRoom;
    @BindView(R.id.tv_down_payment_1)
    TextView tvDownPayment1;
    @BindView(R.id.tv_down_payment_sum)
    TextView tvDownPaymentSum;
    @BindView(R.id.tv_down_payment_pay_way)
    TextView tvDownPaymentPayWay;
    @BindView(R.id.ll_down_payment_weixin)
    LinearLayout llDownPaymentWeixin;
    @BindView(R.id.ll_down_payment_ali)
    LinearLayout llDownPaymentAli;
    @BindView(R.id.ll_down_payment_balance)
    LinearLayout llDownPaymentBalance;
    @BindView(R.id.ll_upp)
    LinearLayout llUpp;
    @BindView(R.id.rb_down_payment_weixin)
    RadioButton rbDownPaymentWeixin;
    @BindView(R.id.rb_down_payment_ali)
    RadioButton rbDownPaymentAli;
    @BindView(R.id.rb_down_payment_balance)
    RadioButton rbDownPaymentBalance;
    @BindView(R.id.rb_upp)
    RadioButton rbUpp;
    @BindView(R.id.rg_down_payment)
    RadioGroup rgDownPayment;
    @BindView(R.id.rl_down_payment_pay_way)
    RelativeLayout rlDownPaymentPayWay;
    @BindView(R.id.btn_down_payment_pay)
    Button btnDownPaymentPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_down_payment);
        ButterKnife.bind(this);

        init();
        setListeners();
        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID);
        wxApi.registerApp(Constants.WX_APP_ID);
    }


    private void init() {
        storeId = getIntent().getStringExtra(Constants.STORE_ID);


        payWay = Constants.PAY_WAY_WEIXIN;

        String flag = getIntent().getStringExtra("flag");

        tmDownPayment.setLeftIcon(R.drawable.left);
        tmDownPayment.setTitle(R.string.down_payment);
        tmDownPayment.setTitleColor(getResources().getColor(R.color.white));

        if (!"预订支付".equals(flag)) {
            params = (HashMap<String, String>) getIntent().getSerializableExtra(Constants.PARAM);
            reserveType = params.get(Network.Param.TYPE);
            if (Constants.RESERVE_WAY_CREDIT.equals(reserveType)) {// 信誉预订
                tmDownPayment.setTitle(R.string.credit_reserve);
                tvDownPaymentPayWay.setVisibility(View.GONE);
                rlDownPaymentPayWay.setVisibility(View.GONE);
                btnDownPaymentPay.setText(R.string.confirm);
            }

            getOrderInfo(params);
        } else {

            String order_idValue = getIntent().getStringExtra("order_id");

             reserveType = getIntent().getStringExtra("pay_type");

            String store_nameValue = getIntent().getStringExtra("store_name");
            String arrival_timeValue = getIntent().getStringExtra("arrival_time");
            String prepayValue = getIntent().getStringExtra("prepay");

            tvDownPaymentTitle.setText(store_nameValue);
            tvDownPaymentTime.setText(DateFormatUtil.stampToDate(arrival_timeValue));
            //tvDownPaymentRoom.setText(order.room_type);
            tvDownPaymentSum.setText(prepayValue);
            price = prepayValue;
            orderId = order_idValue;
        }


        registerBroadcastReceiver();
    }

    private void setListeners() {
        tmDownPayment.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rgDownPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_down_payment_weixin:
                        payWay = Constants.PAY_WAY_WEIXIN;
                        break;
                    case R.id.rb_down_payment_ali:
                        payWay = Constants.PAY_WAY_ALI;
                        break;
                    case R.id.rb_down_payment_balance:
                        payWay = Constants.PAY_WAY_BALANCE;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_down_payment_weixin:
                rbDownPaymentWeixin.setChecked(true);
                break;
            case R.id.ll_down_payment_ali:
                rbDownPaymentAli.setChecked(true);
                break;
            case R.id.ll_down_payment_balance:
                rbDownPaymentBalance.setChecked(true);
                break;
            case R.id.btn_down_payment_pay:
                pay();
                break;
            default:
                break;
        }
    }

    /**
     * 支付完成去待评价页面
     */
    private void payCompleted() {
        // 广播通知
        Intent paySuccess = new Intent(Constants.ACTION_PAY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(paySuccess);
        // 跳转去评价
        Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.ORDER_TO_COMPLETE);
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.putExtra(Constants.ORDER_ID, orderId);
//        intent.putExtra(Constants.PRICE, price);
//        if (Constants.RESERVE_WAY_CREDIT.equals(reserveType)) {
//            intent.putExtra(Constants.CREDIT_RESERVE, true);// 信誉预订
//        }
        startActivity(intent);

        finish();
    }

    /**
     * 订单信息
     *
     * @param params
     */
    private void getOrderInfo(HashMap<String, String> params) {
        showProgress();

        List<MultipartBody.Part> fileA = new ArrayList<>();


        for (Map.Entry<String, String> entry : params.entrySet()) {
            MultipartBody.Part requestFileA =
                    MultipartBody.Part.createFormData(entry.getKey(), entry.getValue());
            fileA.add(requestFileA);
        }


        Subscription subscription = Retrofit_RequestUtils.getRequest().onlineBook(fileA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(DownPaymentActivity.this, "预订失败", Toast.LENGTH_SHORT).show();
                        closeProgress();
                        connectError();
                    }

                    @Override
                    public void onNext(OrderInfoModel orderInfo) {
                        closeProgress();
                        if (orderInfo.code.equals("0")) {
                            showOrderInfo(orderInfo.content.order_info);
                        }
                    }
                });

        compositeSubscription.add(subscription);


    }

    /**
     * 显示订单
     *
     * @param order
     */
    private void showOrderInfo(OrderInfoModel.ContentBean.OrderInfoBean order) {
        tvDownPaymentTitle.setText(order.store_name);
        tvDownPaymentTime.setText(DateFormatUtil.stampToDate(order.arrival_time));
        tvDownPaymentRoom.setText(order.room_type);
        tvDownPaymentSum.setText(order.prepay);
        price = order.prepay;
        orderId = order.id;
    }


    /**
     * 订金预定支付，信誉预定跳过支付
     */
    private void pay() {
        if (orderId == null || price == null) {
            ToastUtils.makePicTextShortToast(mContext, "下单失败，请重新下单");
            return;
        }
        // 订金预定
        if (Constants.RESERVE_WAY_PREPAY.equals(reserveType)) {
            switch (payWay) {
                case 0:
                    ToastUtils.makePicTextShortToast(mContext, "请选择支付方式");
                    break;
                case 1:
                case 2:
                    payOrder();
                    break;
                case 3:
                    alertBalancePay();
                    break;
                default:
                    break;
            }
        }
        // 信誉预定
        if (Constants.RESERVE_WAY_CREDIT.equals(reserveType)) {
            Toast.makeText(mContext, "信誉预订成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void payOrder() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_PAYORDER)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ORDERFORM_ID, orderId)
                .addParams(Network.Param.TYPE, String.valueOf(payWay))
                .addParams(Network.Param.PREPAY, price)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(String response) {
                        closeProgress();
                        Logger.json(response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject json = object.getJSONObject("content");
                            if (payWay == 1) {// 支付宝
                                orderId = json.getString("orderform_id");
                                // 唤起支付宝
//                                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);// 沙箱环境

                                aliPay(json.getString("orderString"));
                            }
                            if (payWay == 2) {// 微信
                                wxPay(json);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 确认信誉预订
     */
    private void payCreditOrder() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_IS_DISPLAY)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ORDER_ID, orderId)
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
                                payCompleted();
                            }
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
                PayTask alipay = new PayTask(DownPaymentActivity.this);
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
               // checkAliPayResult(resultInfo, orderId);

                payCompleted();
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
                .url(Network.User.USER_PREPAY_ORDER_VALIDATE)
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
                            ToastUtils.makePicTextShortToast(mContext, response.getInfo());
                            if (response.isResult()) {
                                payCompleted();
                            }
                        }
                    }
                });
    }

    //余额支付时检查有没有设置过密码
    private void alertBalancePay() {
        if (!mSpUtils.getBoolean(Constants.IS_SET_PAY_PASSWORD, false)) {
            new AlertDialog(this)
                    .setTitle("提示")
                    .setContent("您还没有设置支付密码，现在去设置？")
                    .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                        @Override
                        public void onBtnClick(View view, String input) {
                            Intent intent = new Intent(mContext, DetailActivity.class);
                            intent.putExtra(Constants.PAGE, Constants.SET_PAY_PWD);
                            startActivity(intent);
                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .show();
        } else {
            new AlertDialog(this)
                    .setPasswordInputBox()
                    .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                        @Override
                        public void onBtnClick(View view, String input) {
                            if (input.isEmpty()) {
                                ToastUtils.makePicTextShortToast(mContext, "请输入支付密码");
                                return;
                            }
                            if (input.length() < 6) {
                                ToastUtils.makePicTextShortToast(mContext, "支付密码位数不正确");
                                return;
                            }
                            payByBalance(input);
                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .show();
        }
    }

    /**
     * 余额支付
     *
     * @param password
     */
    private void payByBalance(String password) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_PAYORDER)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ORDERFORM_ID, orderId)
                .addParams(Network.Param.CODE, password)
                .addParams(Network.Param.TYPE, String.valueOf(payWay))
                .addParams(Network.Param.PREPAY, price)
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
                            if (response.isResult()) {// 支付成功
                                payCompleted();
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
            payCompleted();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
