package com.fanc.wheretoplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;

/**
 * @author admin
 * @date 2018/3/16
 */

public class ExtendTextView extends RelativeLayout {

    private TextView tv, tv0;
    private TextView tv_value;

    public ExtendTextView(Context context) {
        super(context);
        initView(context);
    }

    public ExtendTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExtendTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.extendtextview, this);
        tv = view.findViewById(R.id.tv);
        tv0 = view.findViewById(R.id.tv0);
        tv_value = view.findViewById(R.id.et_value);

    }

    public void setTv(String value, boolean flag) {
        tv.setText(value);
        if (flag) {
            tv0.setVisibility(VISIBLE);
        } else {
            tv0.setVisibility(INVISIBLE);
        }

    }

    public String getData(){
        return tv_value.getText().toString().trim();
    }

    public void setData(String value){
        tv_value.setText(value);
    }
}
