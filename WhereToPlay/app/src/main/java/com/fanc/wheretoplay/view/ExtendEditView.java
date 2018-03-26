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

public class ExtendEditView extends RelativeLayout {

    private TextView tv, tv0;
    private EditText et_value;

    public ExtendEditView(Context context) {
        super(context);
        initView(context);
    }

    public ExtendEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExtendEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.extendeditview, this);
        tv = view.findViewById(R.id.tv);
        tv0 = view.findViewById(R.id.tv0);
        et_value = view.findViewById(R.id.et_value);

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
        return et_value.getText().toString().trim();
    }

    public void setData(String value){
        et_value.setText(value);
    }
}
