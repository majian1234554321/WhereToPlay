package com.fanc.wheretoplay.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;

import com.fanc.wheretoplay.datamodel.AccessOrderIdModel;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.OrderInfoModel;
import com.fanc.wheretoplay.datamodel.OrderPayoffModel;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.pay.AliPayResult;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.TopMenu;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2017/6/15.
 */
public class DownPaymentActivity extends BaseActivity {

    /**
     * 店铺id
     */
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

    private String flag;

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

    float discountValue;

    private void init() {
        storeId = getIntent().getStringExtra(Constants.STORE_ID);

        payWay = Constants.PAY_WAY_WEIXIN;

        flag = getIntent().getStringExtra("flag");

        tmDownPayment.setLeftIcon(R.drawable.left);
        tmDownPayment.setTitle(R.string.down_payment);
        tmDownPayment.setTitleColor(getResources().getColor(R.color.white));

        if (!"预订支付".equals(flag)) {
            params = (HashMap<String, String>) getIntent().getSerializableExtra(Constants.PARAM);
            String discount = getIntent().getStringExtra("discount");
            if (discount != null) {
                discountValue = Float.parseFloat(discount) / 10;
            } else {
                discountValue = 1f;
            }
            reserveType = params.get(Network.Param.TYPE);
            tmDownPayment.setTitle(flag);
            if (Constants.RESERVE_WAY_CREDIT.equals(reserveType)) { // 信誉预订

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

            tvDownPayment1.setText(prepayValue);
            tvDownPaymentTitle.setText(store_nameValue);
            tvDownPaymentTime.setText(DateFormatUtil.stampToDate(arrival_timeValue));
            // tvDownPaymentRoom.setText(order.room_type);
            tvDownPaymentSum.setText(prepayValue);
            price = prepayValue;
            orderId = order_idValue;
        }

        registerBroadcastReceiver();
    }

    private void setListeners() {
        tmDownPayment.setLeftIconOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        rgDownPayment.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
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
                            case R.id.rb_upp:
                                payWay = 4;
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
        /* Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.ORDER_TO_COMPLETE);
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.putExtra(Constants.ORDER_ID, orderId);

        //tmDownPayment.setTitle(storeId+orderId);

        startActivity(intent);*/
        Intent intent = new Intent(this, ListOrderActivity.class);
        intent.putExtra("Key", "Value");
        RxBus.getDefault().post(intent);
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

        Retrofit_RequestUtils.getRequest()
                .onlineBook(fileA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<OrderInfoModel>() {

                            @Override
                            public void onSubscribe(Disposable disposable) {
                            }

                            @Override
                            public void onNext(OrderInfoModel orderInfoModel) {
                                closeProgress();
                                if ("0".equals(orderInfoModel.code)) {
                                    showOrderInfo(orderInfoModel.content.order_info);
                                } else {
                                    Toast.makeText(
                                            DownPaymentActivity.this,
                                            orderInfoModel.message,
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    finish();
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                Toast.makeText(DownPaymentActivity.this, "预订失败", Toast.LENGTH_SHORT)
                                        .show();
                                closeProgress();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });

        // compositeSubscription.add(subscription);

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
                    payOrder("");
                    break;
                case 3:
                    alertBalancePay();
                    break;

                case 4:
                    payOrder("");
                    break;
                default:
                    break;
            }
        }
        // 信誉预定
        if (Constants.RESERVE_WAY_CREDIT.equals(reserveType)) {
            List<MultipartBody.Part> list2 = new ArrayList<>();
            list2.add(MultipartBody.Part.createFormData("token", mUser.getToken()));
            list2.add(MultipartBody.Part.createFormData("order_id", orderId));
            Retrofit_RequestUtils.getRequest()
                    .is_display(list2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Observer<AccessOrderIdModel>() {
                                @Override
                                public void onSubscribe(Disposable disposable) {
                                }

                                @Override
                                public void onNext(AccessOrderIdModel accessOrderIdModel) {
                                    if ("0".equals(accessOrderIdModel.code)) {
                                        Toast.makeText(mContext, "信誉预订成功", Toast.LENGTH_SHORT)
                                                .show();
                                        startActivity(
                                                new Intent(
                                                        DownPaymentActivity.this,
                                                        ListOrderActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(mContext, "信誉预订失败", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }

                                @Override
                                public void onError(Throwable throwable) {
                                    Toast.makeText(mContext, "信誉预订失败", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
        }
    }

    private void payOrder(String password) {
        showProgress();

        List<MultipartBody.Part> lists = new ArrayList<>();
        lists.add(MultipartBody.Part.createFormData("token", mUser.getToken()));
        lists.add(MultipartBody.Part.createFormData("orderform_id", orderId));
        lists.add(MultipartBody.Part.createFormData("type", String.valueOf(payWay)));
        lists.add(MultipartBody.Part.createFormData("prepay", price));
        lists.add(MultipartBody.Part.createFormData("token", mUser.getToken()));

        lists.add(MultipartBody.Part.createFormData("code", password));

        Retrofit_RequestUtils.getRequest()
                .payOrder(lists)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<OrderPayoffModel>() {
                            @Override
                            public void onSubscribe(Disposable disposable) {
                            }

                            @Override
                            public void onNext(OrderPayoffModel orderPayoffModel) {
                                closeProgress();
                                if ("0".equals(orderPayoffModel.code)) {

                                    switch (payWay) {
                                        case 0:
                                            ToastUtils.makePicTextShortToast(mContext, "请选择支付方式");
                                            break;
                                        case 1: // 支付宝支付
                                            aliPay(orderPayoffModel.content.orderString);
                                            break;
                                        case 2: // 微信支付
                                            wxPay(orderPayoffModel.content);
                                            break;
                                        case 3:
                                            payCompleted();
                                            break;
                                        case 4:
                                            UPPayAssistEx.startPay(
                                                    DownPaymentActivity.this,
                                                    null,
                                                    null,
                                                    orderPayoffModel.content.orderString,
                                                    mMode);
                                            break;

                                        default:
                                            break;
                                    }

                                } else {
                                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                closeProgress();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }

    private final String mMode = "00";

    /**
     * 支付宝支付
     *
     * @param orderString
     */
    private void aliPay(final String orderString) {
        Runnable payRunnable =
                new Runnable() {

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
    private Handler mHandler =
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
                    /** 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。 */
                    String resultInfo = payResult.getResult(); // 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showShortToast(mContext, "支付成功");
                        payCompleted();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast(mContext, "支付失败");
                    }
                }
            };

    // 余额支付时检查有没有设置过密码
    private void alertBalancePay() {
        if (!mSpUtils.getBoolean(Constants.IS_SET_PAY_PASSWORD, false)) {
            new AlertDialog(this)
                    .setTitle("提示")
                    .setContent("您还没有设置支付密码，现在去设置？")
                    .setBtnOnClickListener(
                            new AlertDialog.OnBtnClickListener() {
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
                    .setBtnOnClickListener(
                            new AlertDialog.OnBtnClickListener() {
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
                                    payOrder(input);
                                }
                            })
                    .setCanceledOnTouchOutside(true)
                    .show();
        }
    }

    /**
     * 微信支付
     *
     * @param
     * @param content
     * @throws JSONException
     */
    private void wxPay(OrderPayoffModel.ContentBean content) {
        PayReq req = new PayReq();
        //        req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = content.appid;
        req.partnerId = content.partnerid;
        req.prepayId = content.prepayid;
        req.nonceStr = content.noncestr;
        req.timeStamp = content.timestamp;
        req.packageValue = content.packageX;
        req.sign = content.sign;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * *********************************************** 步骤3：处理银联手机支付控件返回的支付结果
         * **********************************************
         */
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if ("success".equalsIgnoreCase(str)) {

            // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                String result = data.getExtras().getString("result_data");
                try {
                    JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    // 此处的verify建议送去商户后台做验签
                    // 如要放在手机端验，则代码必须支持更新证书
                    boolean ret = verify(dataOrg, sign, mMode);
                    if (ret) {
                        // 验签成功，显示支付结果
                        msg = "支付成功";
                    } else {
                        // 验签失败
                        msg = "支付失败";
                    }
                } catch (JSONException e) {
                }
            }
            // 结果result_data为成功时，去商户后台查询一下再展示成功
            msg = "支付成功";
        } else if ("fail".equalsIgnoreCase(str)) {
            msg = "支付失败";
        } else if ("cancel".equalsIgnoreCase(str)) {
            msg = "用户取消了支付";
        }

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        // builder.setCustomTitle();
        final String finalMsg = msg;
        builder.setNegativeButton(
                "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("支付成功".equals(finalMsg)) {
                            payCompleted();
                        }
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private boolean verify(String msg, String sign64, String mode) {
        // 此处的verify，商户需送去商户后台做验签
        return true;
    }
}
