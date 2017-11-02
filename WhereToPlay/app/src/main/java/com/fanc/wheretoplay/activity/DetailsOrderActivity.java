package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.view.OrderetailsItemView;
import com.fanc.wheretoplay.view.TitleBarView;
import com.fanc.wheretoplay.view.TopMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsOrderActivity extends BaseActivity {


    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_pay)
    TextView tvPay;
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
    @BindView(R.id.tbv)
    TitleBarView tbv;

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

        oi1.setTv_right("信用预订成功");
        oi2.setTv_right("188****8888");
        oi3.setTv_right("18888888888");
        oi4.setTv_right("豪华小房");
        oi5.setTv_right("A808");
        oi6.setTv_right("2017-10-20 18:00");
        oi7.setTv_right("0");
        oi8.setTv_right("1");
        oi9.setTv_right("信用预订");
        oi10.setTv_right("暂无备注");

    }

    @OnClick({R.id.tv_pay, R.id.tv_release, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay:
                break;
            case R.id.tv_release:
                startActivity(new Intent(this,PublicationEvaluationActivity.class));
                break;
            case R.id.tv_cancel:
                break;
        }
    }
}
