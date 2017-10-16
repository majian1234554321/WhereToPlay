package com.fanc.wheretoplay.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ViewTopmenuBinding;


/**
 * @author tylz
 * @time 2016/3/18 0018 15:02
 * @des 自定义菜单栏
 * @updateAuthor tylz
 * @updateDate 2016/3/18 0018
 * @updateDes
 */
public class TopMenu extends RelativeLayout {
    ImageView mIvLeftIcon;
    TextView mTvLeftText;
    TextView mTvTitle;
    TextView mTvRightText;
    ImageView mIvRightIcon;
    RelativeLayout mRlTopMenuBg;

    public TopMenu(Context context) {
        super(context);
        initView();
    }

    public TopMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImageView getIvRightIcon() {
        return mIvRightIcon;
    }

    public void setBackgroundColor(int color) {
        mRlTopMenuBg.setBackgroundColor(color);
    }

    public void setBackgroundResource(int resId) {
        mRlTopMenuBg.setBackgroundResource(resId);
    }

    public void setBackgroundAlpha(float alpha) {
        mRlTopMenuBg.setAlpha(alpha);
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewTopmenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_topmenu, this, true);
        mIvLeftIcon = binding.menuIvLefticon;
        mTvLeftText = binding.menuTvLefttext;
        mTvTitle = binding.menuTvTitle;
        mTvRightText = binding.menuTvRighttext;
        mIvRightIcon = binding.menuIvRighticon;
        mRlTopMenuBg = binding.rlTopMenuBg;
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setTitle(int resId) {
        mTvTitle.setText(resId);
    }

    public void setTitleColor(int color) {
        mTvTitle.setTextColor(color);
    }

    public void setLeftText(String text) {
        mIvLeftIcon.setVisibility(View.GONE);
        mTvLeftText.setText(text);
        mTvLeftText.setVisibility(View.VISIBLE);
    }

    public void setRightText(String text) {
        mIvRightIcon.setVisibility(View.GONE);
        mTvRightText.setText(text);
        mTvRightText.setVisibility(View.VISIBLE);
    }

    public void setRightTextColor(int color) {
        mTvRightText.setTextColor(color);
    }

    public void setRightText(int resId) {
        mIvRightIcon.setVisibility(View.GONE);
        mTvRightText.setText(resId);
        mTvRightText.setVisibility(View.VISIBLE);
    }

    public void setLeftIcon(int resId) {
        mTvLeftText.setVisibility(View.GONE);
        mIvLeftIcon.setImageResource(resId);
        mIvLeftIcon.setVisibility(View.VISIBLE);
    }

    public void setRightIcon(int resId) {
        mTvRightText.setVisibility(View.GONE);
        mIvRightIcon.setImageResource(resId);
        mIvRightIcon.setVisibility(View.VISIBLE);
    }

    public void setLeftIconOnClickListener(OnClickListener listener) {
        mIvLeftIcon.setOnClickListener(listener);
    }

    public void setRightIconOnClickListener(OnClickListener listener) {
        mIvRightIcon.setOnClickListener(listener);
    }

    public void setLeftTextOnClickListener(OnClickListener listener) {
        mTvLeftText.setOnClickListener(listener);
    }

    public void setRightTextOnClickListener(OnClickListener listener) {
        mTvRightText.setOnClickListener(listener);
    }

    /**
     * 隐藏左边所有的控件
     */
    public void hideLeftView() {
        mTvLeftText.setVisibility(View.GONE);
        mIvLeftIcon.setVisibility(View.GONE);
    }

    /**
     * 隐藏右边所有的控件
     */
    public void hideRightView() {
        mTvRightText.setVisibility(View.GONE);
        mIvRightIcon.setVisibility(View.GONE);
    }
}
