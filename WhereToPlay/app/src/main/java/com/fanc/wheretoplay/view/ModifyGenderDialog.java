package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.DialogModifyGenderBinding;
import com.fanc.wheretoplay.util.UIUtils;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ModifyGenderDialog {

    Context mContext;
    Dialog mDialog;

    RadioGroup mRgGender;
    RadioButton mRbGenderMale;
    RadioButton mRbGenderFemale;
    OnGenderSelectedListener listener;

    public ModifyGenderDialog(Context context) {
        this.mContext = context;
        mDialog = new Dialog(mContext, R.style.DialogStyle);
        init();
    }

    private void init() {
        DialogModifyGenderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_modify_gender, null, false);

        mRgGender = binding.rgDialogGender;
        mRbGenderMale = binding.rbDialogMale;
        mRbGenderFemale = binding.rbDialogFemale;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (UIUtils.getScreenWidth() * 0.8),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(binding.getRoot(), layoutParams);
        // 选择监听
        binding.setSelect(this);

    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (listener != null) {
            switch (checkedId) {
                case R.id.rb_dialog_male:
                    listener.onGenderSelected(UIUtils.getString(R.string.gender_male));
                    break;
                case R.id.rb_dialog_female:
                    listener.onGenderSelected(UIUtils.getString(R.string.gender_female));
                    break;
                default:
                    break;
            }
        }
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                dismiss();
            }
        }.sendEmptyMessageDelayed(0, 200);
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public ModifyGenderDialog show() {
        mDialog.show();
        return this;
    }

    public ModifyGenderDialog setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        return this;
    }

    public ModifyGenderDialog setOnGenderSelectedListener(OnGenderSelectedListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnGenderSelectedListener {
        void onGenderSelected(String gender);
    }

    public ModifyGenderDialog setSelectedGender(String gender) {
        if (gender != null && !gender.isEmpty()) {
            if (TextUtils.equals("1", gender)) {// 男
                mRbGenderMale.setChecked(true);
            } else {
                mRbGenderFemale.setChecked(true);
            }
        }
        return this;
    }

}
