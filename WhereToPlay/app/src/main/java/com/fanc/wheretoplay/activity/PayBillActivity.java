package com.fanc.wheretoplay.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivityPayBillBinding;
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.OrderDone;
import com.fanc.wheretoplay.datamodel.PayOrder;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.pay.AliPayResult;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.Call;
import okhttp3.MultipartBody;


/**
 * Created by Administrator on 2017/6/16.
 */

public class PayBillActivity extends BaseActivity {

    ActivityPayBillBinding payBillBinding;

    TopMenu mTmPayBill;
    TextView mTvPayBillAddress;// 地址
    TextView mTvDiscount;// 店铺折扣
    TextView mTvDownPaymentSum;// 预付订金金额
    TextView mTvDiscountCoupon;// 优惠券面值
    EditText mEtConsumeSum;// 消费金额
    TextView mTvPaySumReal;// 实际支付金额
    //    TextView mTvDiscountSum;// 优惠金额=优惠券面值
    RadioGroup mRgPayBill;
    RadioButton mRbPayBillWeixin;
    RadioButton mRbPayBillAli;
    RadioButton mRbPayBillBalance;
    // 不参与优惠金额
    CheckBox mCbNotParticipation;
    LinearLayout mLlNotParticipation;
    EditText mEtNotParticipation;

    // 支付方式
    int payWay;
    // 店铺id
    String storeId;
    // 订单id，支付时的id
    String orderId;
    // 折扣
    double discount;
    /**
     * 订金
     */
    double subscription;
    // 是否使用订金
    boolean isUserSubscription;
    // 剩余订金
    double remainDeposit;
    // 优惠券
    String discountId;
    String discountPrice;
    // 实际消费付款金额（不含不参与优惠的金额）
    double lastPaySum;

    // 服务费率
    double cashRate;

    Receiver receiver;

    IWXAPI wxApi;
    private DecimalFormat df;
    private String statusTitle;
    private TextView tvPayBillStore;
    private RadioButton rb_upp;
    private String storeName;
    private String address;
    private String discountValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payBillBinding = DataBindingUtil.setContentView(this, R.layout.activity_pay_bill);
        initViews();
        setListeners();
        init();
    }

    private void initViews() {


        rb_upp = findViewById(R.id.rb_upp);

        tvPayBillStore = payBillBinding.tvPayBillStore;
        mTmPayBill = payBillBinding.tmPayBill;
        mTvPayBillAddress = payBillBinding.tvPayBillAddress;
        mTvDiscount = payBillBinding.tvPayBillDiscountReal;//折扣
        mTvDownPaymentSum = payBillBinding.tvPayBillDownPaymentSum;
        mTvDiscountCoupon = payBillBinding.tvPayBillDiscountCoupon;
        mEtConsumeSum = payBillBinding.etPayBillConsumeSum;
        mTvPaySumReal = payBillBinding.tvPayBillPaySumReal;
        mCbNotParticipation = payBillBinding.cbNotParticipation;
        mLlNotParticipation = payBillBinding.llNotParticipation;
        mEtNotParticipation = payBillBinding.etPayBillNotParticipationDiscountSum;
//        mTvDiscountSum = payBillBinding.tvPayBillDiscountSum;
        mRgPayBill = payBillBinding.rgPayBill;
        mRbPayBillWeixin = payBillBinding.rbPayBillWeixin;
        mRbPayBillAli = payBillBinding.rbPayBillAli;
        mRbPayBillBalance = payBillBinding.rbPayBillBalance;
        df = new DecimalFormat("0.00");


        String pay_Action = getIntent().getStringExtra("pay_Action");
        String dispayMoney = getIntent().getStringExtra("money");
        if ("预订方式：预付预订".equals(pay_Action) && dispayMoney != null) {
            mTvDownPaymentSum.setText(dispayMoney);
            mTvPaySumReal.setText(dispayMoney);
        } else if ("预订方式：结单支付".equals(pay_Action) && dispayMoney != null) {
            mEtConsumeSum.setText(dispayMoney);
            mEtConsumeSum.setFocusable(false);
            mEtConsumeSum.setFocusableInTouchMode(false);
            mTvPaySumReal.setText(dispayMoney);
        }


    }

    private void init() {
        mTmPayBill.setLeftIcon(R.drawable.left);
        mTmPayBill.setTitleColor(Color.WHITE);
        payWay = Constants.PAY_WAY_WEIXIN;
        Intent intent = getIntent();

        storeId = intent.getStringExtra(Constants.STORE_ID);
        storeName = intent.getStringExtra("storeName");
        discountValue = intent.getStringExtra("discount");
        address = intent.getStringExtra("address");
        if (discountValue != null && discountValue.length() > 0) {
            discount = Double.parseDouble(discountValue);
        } else {
            discount = 10;
        }


        if (discount > 0) {
            SpannableString text = new SpannableString(discount + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvDiscount.setText(text, TextView.BufferType.SPANNABLE);
            mTvDiscount.setVisibility(View.VISIBLE);
        } else {
            mTvDiscount.setVisibility(View.GONE);
        }


        mTvPayBillAddress.setText(address);
        tvPayBillStore.setText(storeName);

        statusTitle = intent.getStringExtra(Constants.PAGE);

        switch (statusTitle) {
            case Constants.CONSUME:
                mTmPayBill.setTitle(R.string.consume);

                break;
            case "商家详情支付":
                mTmPayBill.setTitle("支付");
                break;
            default:
                mTmPayBill.setTitle(R.string.buy);

                break;
        }


        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID);
        wxApi.registerApp(Constants.WX_APP_ID);

        payBillBinding.setClick(this);
        registerBroadcastReceiver();

    }

    private void setListeners() {
        mTmPayBill.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mRgPayBill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                ToastUtils.showShortToast(mContext, String.valueOf(checkedId));
//            }
//        });
        mEtConsumeSum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
//                    mTvPaySumReal.setText("0");
//                    // 优惠金额
//                    if (discountPrice == null) {
//                        mTvDiscountSum.setText(String.valueOf(0));
//                    } else {
//                        mTvDiscountSum.setText(discountPrice);
//                    }
//                    return;
                } else {
                    double sum = Double.parseDouble(s.toString());
                    if (discountPrice == null) {
                        discountPrice = String.valueOf(0);
                    }
                    lastPaySum = sum * discount / 10 - Double.parseDouble(discountPrice);
                    isUserSubscription = false;
                    if (lastPaySum > 0) {
                        lastPaySum = lastPaySum - subscription;
                        isUserSubscription = true;
                    }
                    if (lastPaySum > 0) {
                        String value = df.format(lastPaySum);
                        mTvPaySumReal.setText(value);

                    } else {
                        mTvPaySumReal.setText("0");
                    }
                }
                // 最终支付 = 消费支付金额 + 不参与优惠（+服务费）
                if (!TextUtils.isEmpty(mEtNotParticipation.getText().toString()) && mCbNotParticipation.isChecked()) {
                    if (lastPaySum < 0) {

                        String value = df.format(Double.parseDouble(mEtNotParticipation.getText().toString()) * (1 + cashRate / 100));
                        mTvPaySumReal.setText(value);

                    } else {

                        String value = df.format(lastPaySum + Double.parseDouble(mEtNotParticipation.getText().toString()) * (1 + cashRate / 100));
                        mTvPaySumReal.setText(value);
                    }
                }
//                Log.d("llm", "onTextChanged: ");
//                // 计算优惠金额
//                mTvDiscountSum.setText(String.valueOf((new BigDecimal(sum * (10 - discount) / 10).add(new BigDecimal(discountPrice))).doubleValue()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEtNotParticipation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    return;
                }
                // 最终支付 = 消费支付金额 + 不参与优惠（+服务费）
                if (lastPaySum < 0) {
                    String value = df.format(Double.parseDouble(s.toString()) * (1 + cashRate / 100));
                    mTvPaySumReal.setText(value);
                } else {
                    String value = df.format(lastPaySum + Double.parseDouble(s.toString()) * (1 + cashRate / 100));
                    mTvPaySumReal.setText(value);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mCbNotParticipation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLlNotParticipation.setVisibility(View.VISIBLE);
                    // 最终支付 = 消费支付金额 + 不参与优惠（+服务费）
                    if (!TextUtils.isEmpty(mEtNotParticipation.getText().toString())) {
                        if (lastPaySum < 0) {

                            String value = df.format(Double.parseDouble(mEtNotParticipation.getText().toString()) * (1 + cashRate / 100));
                            mTvPaySumReal.setText(value);
                        } else {
                            String value = df.format(lastPaySum + Double.parseDouble(mEtNotParticipation.getText().toString()) * (1 + cashRate / 100));

                            mTvPaySumReal.setText(value);
                        }
                    }
                } else {
                    mLlNotParticipation.setVisibility(View.GONE);
                    if (lastPaySum > 0) {
                        String value = df.format(lastPaySum);
                        mTvPaySumReal.setText(value);
                    } else {
                        mTvPaySumReal.setText("0");
                    }
                }
            }
        });
    }

    /**
     * 点击事件
     */
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pay_bill_discount_coupon:
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.PAGE, Constants.DISCOUNT_COUPON);
                intent.putExtra(Constants.IS_CHOOSE, true);
                startActivity(intent);
                break;
            case R.id.ll_pay_bill_weixin:
                mRbPayBillWeixin.setChecked(true);
                break;
            case R.id.ll_pay_bill_ali:
                mRbPayBillAli.setChecked(true);
                break;
            case R.id.ll_pay_bill_balance:
                mRbPayBillBalance.setChecked(true);
                break;
            case R.id.btn_pay_bill:
                payBill();
                break;
            case R.id.ll_upp:
                rb_upp.setChecked(true);
                break;
            default:
                break;
        }
    }

    /**
     * databinding RadioGroup Listener
     */
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_pay_bill_ali:
                payWay = Constants.PAY_WAY_ALI;
                break;
            case R.id.rb_pay_bill_weixin:
                payWay = Constants.PAY_WAY_WEIXIN;
                break;
            case R.id.rb_pay_bill_balance:
                payWay = Constants.PAY_WAY_BALANCE;
                break;

            case R.id.rb_upp:
                payWay = 4;
                break;
            default:
                break;
        }
    }


    private void payBill() {
        String consume = mEtConsumeSum.getText().toString();
        if (TextUtils.isEmpty(consume)) {
            ToastUtils.makePicTextShortToast(mContext, "请输入消费金额");
            return;
        }
        if (Double.parseDouble(consume) == 0D) {
            ToastUtils.makePicTextShortToast(mContext, "消费金额不能小于 0");
            return;
        }

        if ("商家详情支付".equals(statusTitle)) {
            storeDetailPay();
        } else {

            double paySum = Double.parseDouble(mTvPaySumReal.getText().toString());
            if (paySum <= 0) {
//            ToastUtils.makePicTextShortToast(mContext, "实际支付金额必须大于0");
                payBillDeductTheDeposit();
            } else {
                switch (payWay) {
                    case 0:
                        ToastUtils.makePicTextShortToast(mContext, "请选择支付方式");
                        break;
                    case 1://支付宝支付
                    case 2:// 微信支付
                        payOrder();
                        break;
                    case 3:// 余额支付
                        alertBalancePay();
                        break;
                    case 4:
                        payOrder();
                        break;
                    default:
                        break;
                }
            }
        }


    }

    //商家详情支付
    private void storeDetailPay() {


        String[] key = {"store_id", "token", "total", "fee", "display_prepay", "coup_id", "coup_amount", "money"};

        String[] value = {
                storeId
                , new SPUtils(mContext).getUser().getToken()
                , mEtConsumeSum.getText().toString().trim()
                , mEtNotParticipation.getText().toString().trim()
                , mTvDownPaymentSum.getText().toString().trim()
                , discountId
                , discountPrice
                , mTvPaySumReal.getText().toString().trim()

        };

        List<MultipartBody.Part> list = new ArrayList();
        for (int i = 0; i < key.length; i++) {
            if (value[i] != null) {
                list.add(MultipartBody.Part.createFormData(key[i], value[i]));
            }

        }


        Retrofit_RequestUtils.getRequest()
                .immediatelyPay(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccessOrderIdModel>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(AccessOrderIdModel accessOrderIdModel) {
                        double paySum = Double.parseDouble(mTvPaySumReal.getText().toString());

                        if ("0".equals(accessOrderIdModel.code)) {
                            //Toast.makeText(PayBillActivity.this, accessOrderIdModel.content.order_id, Toast.LENGTH_SHORT).show();
                            orderId = accessOrderIdModel.content.order_id;
                            if (paySum <= 0) {
                                payBillDeductTheDeposit();
                            } else {
                                switch (payWay) {
                                    case 0:
                                        ToastUtils.makePicTextShortToast(mContext, "请选择支付方式");
                                        break;
                                    case 1://支付宝支付
                                    case 2:// 微信支付
                                        payOrder();
                                        break;
                                    case 3:// 余额支付
                                        alertBalancePay();
                                        break;
                                    case 4:

                                        payOrder();
                                        break;

                                    default:
                                        break;
                                }
                            }


                        } else {
                            Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 从订金中扣除支付消费金额
     * 支付金额小于等于0
     */
    private void payBillDeductTheDeposit() {
        if (payWay != Constants.PAY_WAY_BALANCE) {
            ToastUtils.makePicTextShortToast(mContext, "支付金额为0，请用余额支付");
            return;
        }
        if (discountPrice == null) {
            discountPrice = String.valueOf(0);
        }
        // 剩余订金等于支付订金的相反数
        if (subscription > 0) {// 有订金
            if (isUserSubscription) {// 使用了优惠券
                remainDeposit = -lastPaySum;
            } else {// 未使用优惠券
                remainDeposit = subscription;
            }
        } else {// 没有订金
            remainDeposit = 0;
        }

        if (!mSpUtils.getBoolean(Constants.IS_SET_PAY_PASSWORD, false)) {
            new com.fanc.wheretoplay.view.AlertDialog(this)
                    .setTitle("提示")
                    .setContent("您还没有设置支付密码，现在去设置？")
                    .setBtnOnClickListener(new com.fanc.wheretoplay.view.AlertDialog.OnBtnClickListener() {
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
            new com.fanc.wheretoplay.view.AlertDialog(this)
                    .setPasswordInputBox()
                    .setBtnOnClickListener(new com.fanc.wheretoplay.view.AlertDialog.OnBtnClickListener() {
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
                            payZero(input);
                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .show();
        }

    }

    /**
     * 支付0元
     */
    private void payZero(String input) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        params.put(Network.Param.ORDER_ID, orderId);
        params.put(Network.Param.MONEY, String.valueOf(0));
        params.put(Network.Param.REMAIN, String.valueOf(remainDeposit));
        params.put(Network.Param.TYPE, String.valueOf(payWay));
        params.put(Network.Param.FEE, String.valueOf(0));
//        params.put(Network.Param.DISCOUNT, mTvDiscountSum.getText().toString());
        params.put(Network.Param.DISPLAY_BALANCE, mEtConsumeSum.getText().toString());
        params.put(Network.Param.CODE, input);
        if (discountId != null && !discountId.isEmpty()) {
            params.put(Network.Param.COUPON_ID, discountId);
        }

        OkHttpUtils.post()
                .url(Network.User.USER_ORDER_PAYOFF)
                .params(params)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (response.isResult()) {
                            paySuccess();
                        }
                    }
                });
    }


    // 支付成功去评价
    private void paySuccess() {
        // 去评价
        Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.TO_EVALUATE);
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.putExtra(Constants.ORDER_ID, orderId);
        intent.putExtra(Constants.PRICE, mTvPaySumReal.getText().toString());
        intent.putExtra(Constants.IS_COMMENT, true);
        intent.putExtra("discount", discountValue);

        intent.putExtra("store_name", storeName);
        intent.putExtra("address", address);


        startActivity(intent);
        // 通知消费页面
        Intent intent1 = new Intent(Constants.ACTION_PAY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent1);

        finish();
    }

    private void payOrder() {


        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        params.put(Network.Param.ORDER_ID, orderId);
        params.put(Network.Param.MONEY, mTvPaySumReal.getText().toString());
        params.put(Network.Param.TYPE, String.valueOf(payWay));
        params.put(Network.Param.REMAIN, String.valueOf(0));
        params.put(Network.Param.DISPLAY_BALANCE, mEtConsumeSum.getText().toString());
        if (mCbNotParticipation.isChecked()) {
            params.put(Network.Param.FEE, mEtNotParticipation.getText().toString());
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
                        Log.i("response", response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject json = object.getJSONObject("content");
                            if (payWay == 1) {// 支付宝
                                orderId = json.getString("orderform_id");
                                discountId = json.getString("coupon_id");
                                // 唤起支付宝
//                                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);// 沙箱环境

                                aliPay(json.getString("orderString"));
                            }
                            if (payWay == 2) {// 微信
                                wxPay(json);
                            }
                            if (payWay == 4) {
                                UPPayAssistEx.startPay(PayBillActivity.this, null, null, json.getString("orderString"), mMode);
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
        Observable.just(orderString).map(new Function<String, AliPayResult>() {
            @Override
            public AliPayResult apply(String payTask) throws Exception {
                PayTask alipay = new PayTask(PayBillActivity.this);
                Map<String, String> result = alipay.payV2(orderString, true);
                return new AliPayResult(result);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AliPayResult>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(AliPayResult payResult) {
                        /**
                         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                         */
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            ToastUtils.showShortToast(mContext, "支付成功");
                            // checkAliPayResult(resultInfo, orderId, discountId);
                            paySuccess();
                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            ToastUtils.showShortToast(mContext, "支付失败");
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


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
                            //  ToastUtils.makePicTextShortToast(mContext, response.getInfo());
                            if (response.isResult()) {
                                paySuccess();
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
        wxApi.sendReq(req);
    }

    /**
     * 余额支付时，检查是否设置过支付密码
     */
    private void alertBalancePay() {
        if (!mSpUtils.getBoolean(Constants.IS_SET_PAY_PASSWORD, false)) {
            new com.fanc.wheretoplay.view.AlertDialog(this)
                    .setTitle("提示")
                    .setContent("您还没有设置支付密码，现在去设置？")
                    .setBtnOnClickListener(new com.fanc.wheretoplay.view.AlertDialog.OnBtnClickListener() {
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
            new com.fanc.wheretoplay.view.AlertDialog(this)
                    .setPasswordInputBox()
                    .setBtnOnClickListener(new com.fanc.wheretoplay.view.AlertDialog.OnBtnClickListener() {
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
                            payOrderByBalance(input);
                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .show();
        }
    }

    private void payOrderByBalance(String password) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        params.put(Network.Param.ORDER_ID, orderId);
        params.put(Network.Param.MONEY, mTvPaySumReal.getText().toString());
        params.put(Network.Param.REMAIN, String.valueOf(0));
        params.put(Network.Param.TYPE, String.valueOf(payWay));
        params.put(Network.Param.CODE, password);
        params.put(Network.Param.DISPLAY_BALANCE, mEtConsumeSum.getText().toString());
        if (mCbNotParticipation.isChecked()) {
            params.put(Network.Param.FEE, mEtNotParticipation.getText().toString());
        } else {
            params.put(Network.Param.FEE, String.valueOf(0));
        }
//        params.put(Network.Param.DISCOUNT, mTvDiscountSum.getText().toString());
        if (discountId != null && !discountId.isEmpty()) {
            params.put(Network.Param.COUPON_ID, discountId);
        }

        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_ORDER_PAYOFF)
                .params(params)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            if (response.isResult()) {
                                // checkAliPayResult("", orderId, "");
                                paySuccess();
                            }
                        }
                    }
                });
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_CHOOSE_DISCOUNT_COUPON);
        filter.addAction(Constants.ACTION_WXPAY_SUCCESS);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constants.ACTION_CHOOSE_DISCOUNT_COUPON.equals(action)) {
                discountId = intent.getStringExtra(Constants.DISCOUNT_ID);
                discountPrice = intent.getStringExtra(Constants.PRICE);
                if ("0".equals(discountPrice)) {
                    mTvDiscountCoupon.setText("请使用优惠券");
                } else {
                    mTvDiscountCoupon.setText("-" + discountPrice + UIUtils.getString(R.string.currency));
                }


                String s = mEtConsumeSum.getText().toString();
                if (s.isEmpty()) {
                    s = String.valueOf(0);
                }
                double sum = Double.parseDouble(s);
//            lastPaySum = sum * discount / 10 - Double.parseDouble(discountPrice);
                lastPaySum = sum * discount / 10 - Double.parseDouble(discountPrice);
                isUserSubscription = false;
                // 除去优惠券后支付金额 > 0 就从订金中扣
                if (lastPaySum > 0) {
                    lastPaySum = lastPaySum - subscription;
                    isUserSubscription = true;
                }
                if (lastPaySum > 0) {


                    String value = df.format(lastPaySum);
                    mTvPaySumReal.setText(value);


                } else {
                    mTvPaySumReal.setText("0");
                }
                // 最终支付 = 消费支付金额 + 不参与优惠（+服务费）
                if (!TextUtils.isEmpty(mEtNotParticipation.getText().toString()) && mCbNotParticipation.isChecked()) {
                    if (lastPaySum < 0) {


                        String value = df.format(Double.parseDouble(mEtNotParticipation.getText().toString()) * (1 + cashRate / 100));
                        mTvPaySumReal.setText(value);

                    } else {

                        String value = df.format(lastPaySum + Double.parseDouble(mEtNotParticipation.getText().toString()) * (1 + cashRate / 100));
                        mTvPaySumReal.setText(value);


                    }
                }
//                // 计算优惠金额
//                mTvDiscountSum.setText(String.valueOf(Math.abs((new BigDecimal(sum * (10 - discount) / 10).add(new BigDecimal(discountPrice))).doubleValue())));
            } else if (Constants.ACTION_WXPAY_SUCCESS.equals(action)) {
                paySuccess();
            }
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

    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
     *****************************************************************/
    private final String mMode = "00";
    private static final String TN_URL_01 = "http://101.231.204.84:8091/sim/getacptn";


    //  UPPayAssistEx.startPay(this, null, null, tn, mode);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
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
                        msg = "支付成功！";
                    } else {
                        // 验签失败
                        msg = "支付失败！";
                    }
                } catch (JSONException e) {
                }
            }
            // 结果result_data为成功时，去商户后台查询一下再展示成功
            msg = "支付成功！";
        } else if ("fail".equalsIgnoreCase(str)) {
            msg = "支付失败！";
        } else if ("cancel".equalsIgnoreCase(str)) {
            msg = "用户取消了支付";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        // builder.setCustomTitle();
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
