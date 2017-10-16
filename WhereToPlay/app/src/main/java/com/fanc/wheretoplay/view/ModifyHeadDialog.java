package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.DialogModifyHeadBinding;
import com.fanc.wheretoplay.util.UIUtils;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ModifyHeadDialog {

    Context mContext;
    Dialog mDialog;

    TextView mTvTakePhoto;
    TextView mTvGallery;


    public ModifyHeadDialog(Context context) {
        this.mContext = context;
        mDialog = new Dialog(mContext, R.style.DialogStyle);
        init();
    }

    private void init() {
        DialogModifyHeadBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_modify_head, null, false);
        mTvGallery = binding.tvDialogGallery;
        mTvTakePhoto = binding.tvDialogTakePhoto;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (UIUtils.getScreenWidth() * 0.8),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(binding.getRoot(), layoutParams);

    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public ModifyHeadDialog show() {
        mDialog.show();
        return this;
    }

    public ModifyHeadDialog setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        return this;
    }

    public ModifyHeadDialog setTakePhotoOnClickListener(final View.OnClickListener listener) {
        mTvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    public ModifyHeadDialog setGalleryOnClickListener(final View.OnClickListener listener) {
        mTvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

}
