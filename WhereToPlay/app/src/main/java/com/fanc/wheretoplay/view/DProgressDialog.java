package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ViewProgressDialogBinding;


/**
 * @author tylz
 * @time 2016/3/31 0031 14:04
 * @des 自定义个进度条
 * @updateAuthor
 * @updateDate 2016/3/31 0031
 * @updateDes
 */
public class DProgressDialog
        extends Dialog {
    TextView mTvLoadingMsg;
    ProgressBar mProgressBar;

    public DProgressDialog(Context context) {
        super(context, R.style.progress_dialog);
        initView();
    }


    public DProgressDialog(Context context, int theme) {
        super(context, theme);
        initView();
    }

    private void initView() {
        ViewProgressDialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.view_progress_dialog, null, false);
        setContentView(binding.getRoot());
        mTvLoadingMsg = binding.TvLoadingMsg;
        mProgressBar = binding.ProgressBar;
        setCancelable(true);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mTvLoadingMsg.setText(R.string.hard_loading);
    }

    public void setTip(int resId) {
        mTvLoadingMsg.setText(resId);
    }

    public void setTip(String tip) {
        mTvLoadingMsg.setText(tip);
    }

    public void setIcon(Drawable resId) {
        mProgressBar.setIndeterminateDrawable(resId);
    }

    public void setText(String text) {
        mTvLoadingMsg.setText(text);
    }
}
