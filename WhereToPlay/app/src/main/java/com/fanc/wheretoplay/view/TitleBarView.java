package com.fanc.wheretoplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.fanc.wheretoplay.R;

/**
 * Created by admin on 2017/11/1.
 */

public class TitleBarView  extends RelativeLayout {
    public TitleBarView(Context context) {
        super(context);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
      View view  =   View.inflate(context, R.layout.titlebarview,this);
    }
}
