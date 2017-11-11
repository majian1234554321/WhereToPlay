package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.callback.TimeSelectedCallback;
import com.fanc.wheretoplay.databinding.DialogDealDetailFilterBinding;
import com.fanc.wheretoplay.util.UIUtils;

/**
 * Created by Administrator on 2017/7/11.
 */

public class DealDetailFilterDialog {
    private final int CONSUME_DETAIL = 1;// 消费明细
    private final int RECHARGE_DETAIL = 2;// 充值明细

    Context mContext;
    Dialog mDialog;

    LinearLayout mLlStroreName;
    EditText mEtStoreName;
    TextView mTvFilterDate;
    DatePicker mDpFilterDate;
    Button mBtnConfirm;
    Button mBtnCancel;
    ImageView mIvFilter;

    String date;
    String name;

    boolean isUnfold = true;

    public DealDetailFilterDialog(Context context) {
        mContext = context;
        initViews();
    }

    private void initViews() {
        DialogDealDetailFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_deal_detail_filter, null, false);
        mLlStroreName = binding.llDealDetailFilterStoreName;
        mEtStoreName = binding.etDialogDealDetailFilter;
        mTvFilterDate = binding.tvDialogDealDetailConsumeDate;
        mIvFilter = binding.ivDialogDealDetailFilter;
        mDpFilterDate = binding.dpDialogFilterDate;
        mBtnConfirm = binding.btnDialogFilterConfirm;
        mBtnCancel = binding.btnDialogFilterCancel;

        mDialog = new Dialog(mContext, R.style.DialogStyle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                UIUtils.getScreenWidth(), LinearLayout.LayoutParams.MATCH_PARENT);
        mDialog.setContentView(binding.getRoot(), layoutParams);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        init();
    }

    private void init() {
        mDpFilterDate.setYears(10);

        mDpFilterDate.setOffset(1);
        mDpFilterDate.setLineRatio(0);
        mDpFilterDate.setLineAlpha(100);
        mDpFilterDate.setLineColor(mContext.getResources().getColor(R.color.gray));
        mDpFilterDate.setTextColor(mContext.getResources().getColor(R.color.text_red));

        setOnDateSelectedListener();
        mTvFilterDate.setText(mDpFilterDate.getSelectedDate());
        setImageViewClick();
    }

    private void setImageViewClick() {
        mIvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageViewAnim();

                setDatePickerAnim();
            }
        });
    }

    private void setImageViewAnim() {
        RotateAnimation animation;
        if (isUnfold) {
            animation = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotate_down_deal_filter);
        } else {
            animation = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotate_up_deal_filter);
        }
        animation.setDuration(300);
        animation.setFillAfter(!animation.getFillAfter());
        mIvFilter.startAnimation(animation);
    }

    private void setDatePickerAnim() {
        ScaleAnimation animation;
        if (isUnfold) {
            AnimationSet set = new AnimationSet(true);
            animation = (ScaleAnimation) AnimationUtils.loadAnimation(mContext, R.anim.scal_shrink_deal_filter);
//            AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(mContext,R.anim.alpha_deal_filter_lucency);
//            alphaAnimation.setDuration(100);
        } else {
            animation = (ScaleAnimation) AnimationUtils.loadAnimation(mContext, R.anim.scal_magnify_deal_filter);

        }
        animation.setDuration(100);
        animation.setFillAfter(!animation.getFillAfter());
        mDpFilterDate.setAnimation(animation);

        new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (isUnfold) {
                    mDpFilterDate.setVisibility(View.GONE);
                } else {
                    mDpFilterDate.setVisibility(View.VISIBLE);
                }
                isUnfold = !isUnfold;
            }
        }.sendEmptyMessageDelayed(0, 100);
    }

    private void setOnDateSelectedListener() {
        mDpFilterDate.setTimeSelectedCallback(new TimeSelectedCallback() {
            @Override
            public void onSelected(String selectedTime) {
                date = selectedTime;
                mTvFilterDate.setText(date);
            }
        });
    }

    public DealDetailFilterDialog setContentDetailFilter(int currentDetail) {
        if (currentDetail == RECHARGE_DETAIL) {
            mLlStroreName.setVisibility(View.GONE);
        }
        return this;
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public DealDetailFilterDialog show() {
        mDialog.show();
        return this;
    }

    public DealDetailFilterDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    public DealDetailFilterDialog setOnBtnClickListener(final OnSelectedListener listener) {
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date == null) {
                    date = mDpFilterDate.getSelectedDate();
                }
                name = mEtStoreName.getText().toString();
                if (listener != null) {
                    listener.onSelected(name, date);
                }
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return this;
    }

    public interface OnSelectedListener {
        void onSelected(String storeName, String selectedDate);
    }

}
