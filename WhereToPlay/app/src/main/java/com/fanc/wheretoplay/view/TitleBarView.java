package com.fanc.wheretoplay.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;

/**
 * Created by admin on 2017/11/1.
 */

public class TitleBarView  extends RelativeLayout {

    private TextView tv_title,tv_right;
    private ImageView menu_iv_lefticon;


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

    private void initView(final Context context) {
      View view  =   View.inflate(context, R.layout.titlebarview,this);
        tv_title = view.findViewById(R.id.tv_title);

        tv_right = view.findViewById(R.id.tv_right);

        menu_iv_lefticon = view.findViewById(R.id.menu_iv_lefticon);

        menu_iv_lefticon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });



    }


    public void setOnRightClickListener(final OnRightClickListener onRightClickListener ){

        tv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightClickListener!=null)
                    onRightClickListener.onRightClick();
            }
        });
    }


    public void setleftIconVisibility(){
        menu_iv_lefticon.setVisibility(GONE);
    }

    public void setTv_title(String value){
        tv_title.setText(value);
    }
    public void setTv_right(String value){
        tv_right.setText(value);
    }


    public  interface  OnRightClickListener {

        void onRightClick();
    }
}
