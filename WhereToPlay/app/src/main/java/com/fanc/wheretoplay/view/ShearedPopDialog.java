package com.fanc.wheretoplay.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.PopDialogShearedBinding;
import com.fanc.wheretoplay.util.UIUtils;

/**
 * Created by Administrator on 2017/7/10.
 */

public class ShearedPopDialog extends PopupWindow {

    Context mContext;
    TextView mTvCollect;
    TextView mTvSheared;

    boolean isCollected;

    public ShearedPopDialog(Context context) {
        this(context, null, R.style.PopupWindowTrans);
    }

    public ShearedPopDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        PopDialogShearedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.pop_dialog_sheared, null, false);
        mTvCollect = binding.tvPopDialogCollect;
        mTvSheared = binding.tvPopDialogSheared;
        initPopupWindow(binding.getRoot());
    }

    private void initPopupWindow(View root) {
        setContentView(root);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);// 设置弹出窗体可点击
        setOutsideTouchable(true);
    }

    public void show(View view) {
        showAsDropDown(view, -UIUtils.dp2Px(70), -UIUtils.dp2Px(6));
    }

    public ShearedPopDialog setOnCollectClickListener(final View.OnClickListener listener) {
        mTvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCollected) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
                dismiss();
            }
        });
        return this;
    }

    public ShearedPopDialog setOnShearedClickListener(final View.OnClickListener listener) {
        mTvSheared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dismiss();
            }
        });
        return this;
    }

    public ShearedPopDialog setCollected(boolean collected) {
        isCollected = collected;
        if (collected) {
            mTvCollect.setText("已收藏");
        }
        return this;
    }
}
