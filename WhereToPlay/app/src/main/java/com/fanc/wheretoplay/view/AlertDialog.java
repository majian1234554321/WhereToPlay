package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.DialogAlertBinding;

/**
 * Created by Administrator on 2017/6/23.
 */

public class AlertDialog {

    Context mContext;
    Dialog mDialog;

    TextView mTvTitle;
    TextView mTvContent;
    Button mBtnLeft;
    Button mBtnRight;
    RelativeLayout mRlPasswordInputBox;
    public NumPswView mNpvInputBox;

    public AlertDialog(Context context) {
        this.mContext = context;
        mDialog = new Dialog(mContext, R.style.DialogStyle);
        init();
    }

    private void init() {
        DialogAlertBinding alertBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_alert, null, false);
        mTvTitle = alertBinding.tvAlertTitle;
        mTvContent = alertBinding.tvAlertContent;
        mBtnLeft = alertBinding.btnAlertLeft;
        mBtnRight = alertBinding.btnAlertRight;
        mRlPasswordInputBox = alertBinding.rlAlertInputBox;
        mNpvInputBox = alertBinding.npvAlertInputBox;
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                (int) (metrics.widthPixels * 0.8), LinearLayout.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(alertBinding.getRoot(), lp);
    }

    public void dismiss() {
        mDialog.dismiss();
//        mDialog = null;
    }

    public AlertDialog show() {
        mDialog.show();
        return this;
    }

    /**
     * 标题
     *
     * @param resId
     * @return
     */
    public AlertDialog setTitle(int resId) {
        mTvTitle.setText(resId);
        return this;
    }

    public AlertDialog setTitle(String title) {
        if (title == null || title.isEmpty()) {
            mTvTitle.setVisibility(View.GONE);
            return this;
        }
        mTvTitle.setText(title);
        return this;
    }

    public AlertDialog setTitleColor(int color) {
        mTvTitle.setTextColor(color);
        return this;
    }

    public AlertDialog setTitleGravity(int gravity) {
        mTvTitle.setGravity(gravity);
        return this;
    }

    /**
     * 内容
     *
     * @param resId
     * @return
     */
    public AlertDialog setContent(int resId) {
        mTvContent.setText(resId);
        return this;
    }

    public AlertDialog setContent(String content) {
        mTvContent.setText(content);
        return this;
    }

    public AlertDialog setContentColor(int color) {
        mTvContent.setTextColor(color);
        return this;
    }

    public AlertDialog setContentGravity(int gravity) {
        mTvContent.setGravity(gravity);
        return this;
    }

    /**
     * 密码输入框
     *
     * @return
     */
    public AlertDialog setPasswordInputBox() {
        mTvContent.setVisibility(View.GONE);
        mRlPasswordInputBox.setVisibility(View.VISIBLE);
        setTitle(R.string.input_pay_password);
        setTitleColor(mContext.getResources().getColor(R.color.text_black));
        return this;
    }

    /**
     * 按钮
     *
     * @param listener
     * @return
     */
    public AlertDialog setBtnOnClickListener(final OnBtnClickListener listener) {
        mBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBtnClick(v, mNpvInputBox.getText().toString());
                dismiss();
            }
        });
        mBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return this;
    }

    public AlertDialog setLeftOnClickListener(final View.OnClickListener listener) {
        mBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    public AlertDialog setRightOnClickListener(final View.OnClickListener listener) {
        mBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    public AlertDialog setLeftOnClickListener(String text, final View.OnClickListener listener) {
        if (text != null && !text.isEmpty()) {
            mBtnLeft.setText(text);
        }
        mBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    public AlertDialog setRightOnClickListener(String text, final View.OnClickListener listener) {
        if (text != null && !text.isEmpty()) {
            mBtnRight.setText(text);
        }
        mBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    public AlertDialog setLeftBtnColor(int color) {
        mBtnLeft.setTextColor(color);
        return this;
    }
    public AlertDialog setRightBtnColor(int color) {
        mBtnRight.setTextColor(color);
        return this;
    }

    public AlertDialog setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        return this;
    }

    // 密码输入界面，点击按钮回调
    public interface OnBtnClickListener {
        void onBtnClick(View view, String input);
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    public AlertDialog setOnKeyListener(DialogInterface.OnKeyListener listener) {
        mDialog.setOnKeyListener(listener);
        return this;
    }

}
