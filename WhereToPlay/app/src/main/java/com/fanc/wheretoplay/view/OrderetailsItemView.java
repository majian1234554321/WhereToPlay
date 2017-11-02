package com.fanc.wheretoplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;

/**
 * Created by admin on 2017/10/31.
 */

public class OrderetailsItemView extends RelativeLayout {

    private TextView tv_left;
    private TextView tv_right;

    public OrderetailsItemView(Context context) {
        super(context);
        initView();
    }

    public OrderetailsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.view_orderetailsitemview, this);
        tv_left = (TextView) view.findViewById(R.id.tv_left);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
    }

    public void setTv_left(String value) {
        tv_left.setText(value);
    }

    public void setTv_right(String value) {
        tv_right.setText(value);
    }

    public void setTv_rightTextColor(int i){
        tv_right.setTextColor(i);
    }

}
