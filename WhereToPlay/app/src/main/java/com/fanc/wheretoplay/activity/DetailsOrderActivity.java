package com.fanc.wheretoplay.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.datamodel.OrderDetailModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxHelper;
import com.fanc.wheretoplay.rx.RxSubscribe;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.OrderetailsItemView;
import com.fanc.wheretoplay.view.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

public class DetailsOrderActivity extends BaseActivity {



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
    private String order_idValue, store_idValue, storeNameValue, statusValue;

    @BindView(R.id.rl)
    RelativeLayout rl;
    private String phone;

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
        oi10.setTv_left("备注");

        oi1.setTv_rightTextColor(Color.parseColor("#C4483C"));
        oi5.setTv_rightTextColor(Color.parseColor("#C4483C"));


        order_idValue = getIntent().getStringExtra("order_id");
        store_idValue = getIntent().getStringExtra("store_id");
        storeNameValue = getIntent().getStringExtra("storeName");
        statusValue = getIntent().getStringExtra("status");

        if (statusValue != null) {
            switch (statusValue) {
                case "1":
                    //("已取消");
                    tvLeft.setText("返回首页");
                    tvRight.setVisibility(View.GONE);

                    break;
                case "2":
                    // holder.tv_payState.setText("预订成功");
                    break;
                case "3":
                    // holder.tv_payState.setText("已取消");
                    break;
                case "4":
                    // holder.tv_payState.setText("已结单");
                    break;
                case "5":
                    //  holder.tv_payState.setText("已支付订金");
                    break;
                case "6":
                    //  holder.tv_payState.setText("已支付订金");
                    break;
                default:
                    break;
            }
        }


        loadData();

    }

    private void loadData() {
        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("order_id", order_idValue);
        Retrofit_RequestUtils.getRequest()
                .orderDetail(requestFileA, requestFileC)
                .compose(RxHelper.<OrderDetailModel.ContentBean>handleResult())
                .subscribe(new RxSubscribe<OrderDetailModel.ContentBean>() {
                    @Override
                    protected void _onNext(OrderDetailModel.ContentBean contentBean) {
                        setData(contentBean);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    private void setData(OrderDetailModel.ContentBean contentBean) {
        tvStoreName.setText(contentBean.store_name);
        tvAddress.setText(contentBean.address);

        switch (contentBean.order_action) {
            case "1":
                oi1.setTv_right("信用预定预定中");
                break;
            case "2":
                oi1.setTv_right("信用预定已预订");
                break;
            case "3":
                oi1.setTv_right("待评价");
                break;
            case "4":
                oi1.setTv_right("已评价");
                break;
            case "5":
                oi1.setTv_right("已取消");
                break;
            default:
                break;
        }


        oi2.setTv_right(contentBean.order_name);
        oi3.setTv_right(contentBean.mobile);
        oi4.setTv_right(contentBean.name);
        oi5.setTv_right(contentBean.number);

        phone = contentBean.mobile;
        oi6.setTv_right(DateFormatUtil.stampToDate(contentBean.arrival_time));
        oi7.setTv_right(contentBean.car_num);
        oi8.setTv_right(contentBean.people_num);

        switch (contentBean.action) {
            case "0":
                oi9.setTv_right("信用预定");
                break;
            case "1":
                oi9.setTv_right("预付预定");
                break;
            default:
                break;
        }

        oi10.setTv_right(contentBean.remark);


        SpannableStringBuilder style1 = new SpannableStringBuilder("订单编号: " + contentBean.order_sn);
        style1.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 5, style1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv1.setText(style1);

        SpannableStringBuilder style2 = new SpannableStringBuilder("创建时间: " + DateFormatUtil.stampToDate(contentBean.created_time));
        style2.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 5, style2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv2.setText(style2);

        SpannableStringBuilder style3 = new SpannableStringBuilder("预订时间: " + DateFormatUtil.stampToDate(contentBean.reserve_time));
        style3.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 5, style3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv3.setText(style3);

        SpannableStringBuilder style4 = new SpannableStringBuilder("成交时间: " + DateFormatUtil.stampToDate(contentBean.finish_time));
        style4.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 5, style4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv4.setText(style4);


    }

    @OnClick({R.id.tv_left, R.id.tv_right, R.id.rl, R.id.tv_call, R.id.tv_msn})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_left:
                intent.putExtra("order_id", order_idValue);
                intent.putExtra("store_id", store_idValue);
                intent.setClass(this, PublicationEvaluationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_right:

                intent.setClass(this, PayBillActivity.class);
                intent.putExtra(Constants.ORDER_ID, order_idValue);
                intent.putExtra(Constants.STORE_ID, store_idValue);
                if (TextUtils.equals("4", statusValue)) {// 去消费
                    intent.putExtra(Constants.PAGE, Constants.CONSUME);
                }
                if (TextUtils.equals("2", statusValue)) {// 去结账
                    intent.putExtra(Constants.PAGE, Constants.PAYING_THE_BILL);
                }
                mContext.startActivity(intent);
                
                break;

            case R.id.tv_cancel:
                MultipartBody.Part requestFileA =
                        MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());

                MultipartBody.Part requestFileC =
                        MultipartBody.Part.createFormData("order_id", order_idValue);
                Retrofit_RequestUtils.getRequest().cancle_order(requestFileA, requestFileC)
                        .compose(RxHelper.<CancleOrderModel.ContentBean>handleResult())
                        .subscribe(new RxSubscribe<CancleOrderModel.ContentBean>() {
                            @Override
                            protected void _onNext(CancleOrderModel.ContentBean contentBean) {
                                if (contentBean.is_cancle) {
                                    finish();
                                } else {
                                    ToastUtils.showShortToast(DetailsOrderActivity.this, "取消订单失败");
                                }
                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });

                break;
            case R.id.rl:
                intent.putExtra(Constants.PAGE, Constants.MERCHANT_DETAIL);
                intent.putExtra(Constants.STORE_ID, store_idValue);
                intent.setClass(this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_call:
              /*  Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent2);*/
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //请求拨打电话权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(phone);
                    }

                } else {
                    callPhone(phone);
                }

                break;
            case R.id.tv_msn:
                break;

        }
    }

    private void callPhone(String phoneNum) {
        //直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callPhone(phone);
        }
    }

    private final int REQUEST_CODE = 0x1001;
}
