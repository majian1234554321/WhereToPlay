package com.fanc.wheretoplay.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.datamodel.OrderDetailModel;
import com.fanc.wheretoplay.presenter.DetailsOrderPresenter;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.DetailsOrderView;
import com.fanc.wheretoplay.view.OrderetailsItemView;
import com.fanc.wheretoplay.view.TitleBarView;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DetailsOrderActivity extends BaseActivity implements DetailsOrderView {

    @BindView(R.id.tv_storeName)
    TextView tvStoreName;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.oi1)
    OrderetailsItemView oi1;

    @BindView(R.id.oi2)
    OrderetailsItemView oi2;

    @BindView(R.id.oi3)
    OrderetailsItemView oi3;

    @BindView(R.id.oi4)
    OrderetailsItemView oi4;

    @BindView(R.id.oi5)
    OrderetailsItemView oi5;

    @BindView(R.id.oi6)
    OrderetailsItemView oi6;

    @BindView(R.id.oi7)
    OrderetailsItemView oi7;

    @BindView(R.id.oi8)
    OrderetailsItemView oi8;

    @BindView(R.id.oi9)
    OrderetailsItemView oi9;

    @BindView(R.id.oi10)
    OrderetailsItemView oi10;

    @BindView(R.id.oi11)
    OrderetailsItemView oi11;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

    @BindView(R.id.tv3)
    TextView tv3;

    @BindView(R.id.tv4)
    TextView tv4;

    @BindView(R.id.tv_call)
    TextView tv_call;

    @BindView(R.id.tv_msn)
    TextView tv_msn;

    @BindView(R.id.tbv)
    TitleBarView tbv;

    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;

    @BindView(R.id.ll)
    RelativeLayout ll;

    @BindView(R.id.item_view_line)
    View itemViewLine;

    @BindView(R.id.item_view_line2)
    View itemViewLine2;

    @BindView(R.id.tv_left)
    TextView tvLeft;

    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.tv_mid)
    TextView tv_mid;

    @BindView(R.id.tv_mid2)
    TextView tv_mid2;

    @BindView(R.id.tv_copy)
    TextView tv_copy;

    @BindView(R.id.oi91)
    OrderetailsItemView oi91;

    @BindView(R.id.oi92)
    OrderetailsItemView oi92;

    @BindView(R.id.oi93)
    OrderetailsItemView oi93;

    @BindView(R.id.oi931)
    OrderetailsItemView oi931;

    @BindView(R.id.oi94)
    OrderetailsItemView oi94;

    @BindView(R.id.oi95)
    OrderetailsItemView oi95;

    @BindView(R.id.oi96)
    OrderetailsItemView oi96;

    @BindView(R.id.iv_qrcode)
    ImageView iv_qrcode;

    private String order_idValue,
            store_idValue,
            storeNameValue,
            statusValue,
            totalValue,
            discountValue,
            addressValue, image, statues;

    @BindView(R.id.rl)
    RelativeLayout rl;

    private String phone;

    List<TextView> lists = new ArrayList<>();
    private DetailsOrderPresenter detailsOrderPresenter;
    private String order_sn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaels_order);
        ButterKnife.bind(this);

        tbv.setTv_title("订单详情");
        oi1.setTv_left("订单状态");
        oi2.setTv_left("姓名");
        oi3.setTv_left("手机");
        oi4.setTv_left("房型");
        oi5.setTv_left("包厢号");
        oi6.setTv_left("到店时间");
        oi7.setTv_left("车位");
        oi8.setTv_left("人数");
        oi9.setTv_left("预订方式");

        oi91.setTv_left("订金");
        oi92.setTv_left("消费");
        oi93.setTv_left("不参与优惠金额");
        oi931.setTv_left("折扣");

        oi94.setTv_left("优惠券金额");
        oi95.setTv_left("实际支付");
        oi96.setTv_left("支付方式");

        oi10.setTv_left("总价");
        oi11.setTv_left("备注");

        lists.add(tvLeft);
        lists.add(tv_mid);
        lists.add(tv_mid2);
        lists.add(tvRight);

        oi1.setTv_rightTextColor(Color.parseColor("#C4483C"));
        oi5.setTv_rightTextColor(Color.parseColor("#C4483C"));

        order_idValue = getIntent().getStringExtra("order_id");
        store_idValue = getIntent().getStringExtra("store_id");
        storeNameValue = getIntent().getStringExtra("storeName");
        statusValue = getIntent().getStringExtra("status");
        totalValue = getIntent().getStringExtra("total");
        discountValue = getIntent().getStringExtra("discount");
        image = getIntent().getStringExtra("image");
        statues = getIntent().getStringExtra("statues");


        addressValue = getIntent().getStringExtra("address");

        Disposable rxSubscription =
                RxBus.getDefault()
                        .toFlowable(String.class)
                        .subscribe(
                                new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        if (s != null && "提交评价成功".equals(s)) {
                                            statusValue = "1";
                                        }
                                    }
                                });
        compositeSubscription.add(rxSubscription);

        detailsOrderPresenter = new DetailsOrderPresenter(this, order_idValue, this);
        detailsOrderPresenter.getDetailsOrderData();
    }

    @OnClick({R.id.rl, R.id.tv_msn, R.id.tv_call, R.id.tv_copy})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                ClipData clipData = ClipData.newPlainText("simple text copy", order_sn);
                if (cm != null) {
                    cm.setPrimaryClip(clipData);
                    Toast.makeText(mContext, "已复制订单号到剪切板", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rl:
                intent.putExtra(Constants.PAGE, Constants.MERCHANT_DETAIL);
                intent.putExtra(Constants.STORE_ID, store_idValue);
                intent.setClass(this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_call:
                if (Build.VERSION.SDK_INT >= 23) {

                    // 判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // 请求拨打电话权限
                        ActivityCompat.requestPermissions(
                                this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(phone);
                    }

                } else {
                    callPhone(phone);
                }

                break;
            case R.id.tv_msn:
                ConsultSource source = new ConsultSource(null, null, null);
                Unicorn.openServiceActivity(mContext, getResources().getString(R.string.app_name), source);
                break;

            default:
                break;
        }
    }

    private void callPhone(String phoneNum) {
        // 直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        // 此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE
                && PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            callPhone(phone);
        }
    }

    private final int REQUEST_CODE = 0x1001;

    @Override
    public void setDetailsOrderViewData(final OrderDetailModel.ContentBean contentBean) {
        tvStoreName.setText(contentBean.store_name);
        tvAddress.setText(contentBean.address);

        // String qrCodeText =
        String qrCodeText =
                com.fanc.wheretoplay.network.Network.QRCODE + store_idValue + "/order/" + order_idValue;
        Bitmap mBitmap =
                CodeUtils.createImage(
                        qrCodeText,
                        400,
                        400,
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        iv_qrcode.setImageBitmap(mBitmap);

        if (contentBean.buttonlist != null) {
            for (int i = 0; i < contentBean.buttonlist.size(); i++) {
                lists.get(i).setText(contentBean.buttonlist.get(i).title);
                lists.get(i).setVisibility(View.VISIBLE);

                final int finalI = i;
                lists
                        .get(i)
                        .setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                    /*   Toast.makeText(DetailsOrderActivity.this, "title" + contentBean.buttonlist.get(finalI).title + "----->" +
                            contentBean.buttonlist.get(finalI).buttonid
                    , Toast.LENGTH_SHORT).show();*/
                                        Intent intent = new Intent();
                                        switch (contentBean.buttonlist.get(finalI).buttonid) {
                                            case "1":
                                                intent.setClass(mContext, PayBillActivity.class);
                                                intent.putExtra(Constants.STORE_ID, store_idValue);
                                                intent.putExtra("storeName", storeNameValue);
                                                intent.putExtra("address", tvAddress.getText().toString());
                                                intent.putExtra("discount", discountValue);

                                                if ("预付预订".equals(oi9.getTv_right())) {
                                                    intent.putExtra("pay_Action", "预订类型：预付预订");
                                                    intent.putExtra("money211", contentBean.origin_prepay);
                                                    intent.putExtra("money", contentBean.prepay);
                                                } else {
                                                    intent.putExtra("pay_Action", "预订类型：结单支付");
                                                    intent.putExtra("money", contentBean.total);
                                                }

                                                intent.putExtra(Constants.PAGE, "商家详情支付");
                                                intent.setFlags(
                                                        Intent.FLAG_ACTIVITY_NEW_TASK
                                                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                                mContext.startActivity(intent);
                                                break;
                                            case "2": // 转预付
                                                intent.putExtra("flag", "预订支付");
                                                intent.putExtra("order_id", order_idValue);
                                                intent.putExtra("store_name", storeNameValue);
                                                intent.putExtra("pay_type", "1");
                                                intent.putExtra("arrival_time", contentBean.arrival_time);
                                                intent.putExtra("prepay", contentBean.book_price);

                                                intent.setClass(DetailsOrderActivity.this, DownPaymentActivity.class);
                                                mContext.startActivity(intent);
                                                break;
                                            case "3": // 立即评论
                                                intent.putExtra("order_id", order_idValue);
                                                intent.putExtra("store_id", store_idValue);
                                                intent.putExtra("store_name", storeNameValue);
                                                intent.putExtra("address", tvAddress.getText().toString());

                                                intent.setClass(
                                                        DetailsOrderActivity.this, PublicationEvaluationActivity.class);
                                                intent.setFlags(
                                                        Intent.FLAG_ACTIVITY_NEW_TASK
                                                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                                mContext.startActivity(intent);
                                                break;
                                            case "4":
                                                break;
                                            case "5":
                                                intent.setClass(DetailsOrderActivity.this, MainActivity.class);
                                                intent.setFlags(
                                                        Intent.FLAG_ACTIVITY_NEW_TASK
                                                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                                mContext.startActivity(intent);
                                                finish();
                                                break;
                                            case "6":
                                                break;
                                            case "7":
                                                intent.setClass(DetailsOrderActivity.this, PayBillActivity.class);


                                                if ("预付预订".equals(oi9.getTv_right())) {
                                                    // intent.putExtra("pay_Action", "预订类型：预付预订");

                                                    intent.putExtra("money", contentBean.prepay);
                                                    intent.putExtra("money211", contentBean.origin_prepay);

                                                }
                                                // intent.putExtra("money", dataBean.list.get(position).prepay);


                                                intent.putExtra(Constants.STORE_ID, store_idValue);

                                                intent.putExtra("storeName", storeNameValue);
                                                intent.putExtra("displayMoney", contentBean.total);
                                                intent.putExtra("discount", discountValue);
                                                intent.putExtra("order_id", order_idValue);
                                                intent.putExtra("address", tvAddress.getText().toString());
                                                intent.putExtra(Constants.PAGE, "支付再支付");
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                break;

                                            case "0":
                                                detailsOrderPresenter.cancelOrder();
                                                break;

                                            case "8":


                                                intent.putExtra("DISPLAYTYPE", "CancelOrderFragment");
                                                intent.putExtra("order_id", order_idValue);
                                                intent.putExtra("storeName", storeNameValue);

                                                intent.putExtra("image", image);


                                                intent.putExtra("book_sn", contentBean.order_sn);
                                                intent.putExtra("statues", statues);
                                                intent.putExtra("room_type", contentBean.name);
                                                intent.putExtra("total", contentBean.total);
                                                intent.setClass(DetailsOrderActivity.this, DisplayActivity.class);
                                                startActivity(intent);

                                                break;
                                        }
                                    }
                                });
            }
        }

    /* for (int i = 0; i < lists.size(); i++) {
        final int finalI = i;
        lists.get(i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                switch (lists.get(finalI).getText().toString().trim()) {
                    case "返回首页":
                        intent.setClass(DetailsOrderActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        mContext.startActivity(intent);
                        finish();
                        break;
                    case "立即支付":
                        intent.setClass(mContext, PayBillActivity.class);
                        intent.putExtra(Constants.STORE_ID, store_idValue);
                        intent.putExtra("storeName", storeNameValue);
                        intent.putExtra("address", tvAddress.getText().toString());
                        intent.putExtra("discount", discountValue);

                        if ("预付预订".equals(oi9.getTv_right())) {
                            intent.putExtra("pay_Action", "预订方式：预付预订");
                            intent.putExtra("money", contentBean.prepay);
                        } else {
                            intent.putExtra("pay_Action", "预订方式：结单支付");
                            intent.putExtra("money", contentBean.total);
                        }

                        intent.putExtra(Constants.PAGE, "商家详情支付");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        mContext.startActivity(intent);


                        break;
                    case "取消订单":
                        detailsOrderPresenter.cancelOrder();
                        break;
                    case "立即评论":
                        intent.putExtra("order_id", order_idValue);
                        intent.putExtra("store_id", store_idValue);

                        intent.putExtra("store_id", store_idValue);

                        intent.putExtra("store_name", storeNameValue);

                        intent.putExtra("address", tvAddress.getText().toString());


                        intent.setClass(DetailsOrderActivity.this, PublicationEvaluationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        mContext.startActivity(intent);

                        break;

                    case "转预付":

                        intent.putExtra("flag", "预订支付");
                        intent.putExtra("order_id", order_idValue);
                        intent.putExtra("store_name", storeNameValue);
                        intent.putExtra("pay_type", "1");
                        intent.putExtra("arrival_time", contentBean.arrival_time);
                        intent.putExtra("prepay", contentBean.book_price);


                        intent.setClass(DetailsOrderActivity.this, DownPaymentActivity.class);
                        mContext.startActivity(intent);

                        break;
                    default:
                        break;
                }

            }

        });
    }*/

        oi1.setTv_right(contentBean.statusdesc);
        oi2.setTv_right(contentBean.order_name);
        oi3.setTv_right(contentBean.mobile);
        oi4.setTv_right(contentBean.name);
        oi5.setTv_right(contentBean.number);

        phone = contentBean.phone;
        oi6.setTv_right(DateFormatUtil.stampToDate(contentBean.arrival_time));
        oi7.setTv_right(contentBean.car_num);
        oi8.setTv_right(contentBean.people_num);

        oi91.setTv_right(contentBean.prepay + "(原价:" + contentBean.origin_prepay + "元)");
        oi92.setTv_right(contentBean.display_balance);
        oi93.setTv_right(contentBean.fee);
        if (contentBean.discount_rate != null) oi931.setTv_right(contentBean.discount_rate + "折");
        oi94.setTv_right(contentBean.coupon_amount);
        oi95.setTv_right(contentBean.total);
        oi96.setTv_right(contentBean.pay_method);

        if (contentBean.action != null) {
            switch (contentBean.action) {
                case "0":
                    oi9.setTv_right("信用预订");
                    oi10.setTv_right(totalValue);
                    break;
                case "1":
                    oi9.setTv_right("预付预订");
                    oi10.setTv_right(contentBean.prepay);
                    break;

                case "2":
                    oi9.setTv_right("信用预订");
                    oi10.setTv_right(totalValue);
                    break;
                case "3":
                    oi9.setTv_right("充值");
                    oi10.setTv_right(totalValue);
                    break;
                case "5":
                    oi9.setTv_right("结单支付");
                    oi10.setTv_right(totalValue);
                    break;
                default:
                    break;
            }
        }

        oi11.setTv_right(contentBean.remark);

        order_sn = contentBean.order_sn;

        SpannableStringBuilder style1 = new SpannableStringBuilder("订单编号: " + contentBean.order_sn);
        style1.setSpan(
                new ForegroundColorSpan(Color.parseColor("#333333")),
                5,
                style1.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv1.setText(style1);

        if (contentBean.created_time != null) {
            SpannableStringBuilder style2 =
                    new SpannableStringBuilder(
                            "创建时间: " + DateFormatUtil.stampToDate(contentBean.created_time));
            style2.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#333333")),
                    5,
                    style2.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv2.setText(style2);
        }

        if (contentBean.reserve_time != null) {
            SpannableStringBuilder style3 =
                    new SpannableStringBuilder(
                            "预订时间: " + DateFormatUtil.stampToDate(contentBean.reserve_time));
            style3.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#333333")),
                    5,
                    style3.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv3.setText(style3);
        }

        if (contentBean.finish_time != null) {
            SpannableStringBuilder style4 =
                    new SpannableStringBuilder(
                            "成交时间: " + DateFormatUtil.stampToDate(contentBean.finish_time));
            style4.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#333333")),
                    5,
                    style4.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv4.setText(style4);
        }
    }

    @Override
    public void cancelOrderAction() {
        ToastUtils.showShortToast(mContext, "取消订单成功");
        finish();
    }
}
