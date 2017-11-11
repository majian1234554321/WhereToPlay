package com.fanc.wheretoplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.util.UIUtils;


/**
 * @author cxw
 * @time 2016/9/9 0009 11:17
 * @des
 * @updateAuthor ${author}
 * @updateDate 2016/9/9 0009
 * @updateDes
 */
public class ItemView extends RelativeLayout {
    ImageView mIvIcon;
    TextView mTvText;
    ImageView mIvArrow;
    View mViewLine;
    TextView mTvRightText;
    TextView mTvContent;
    private Switch mIvSwitch;
    private CheckStatus mCheckListener;

    public ItemView(Context context) {
        super(context);
        initView();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        setListener();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.view_item, this);
        mIvIcon = (ImageView) view.findViewById(R.id.item_view_icon);
        mTvText = (TextView) view.findViewById(R.id.item_view_text);
        mIvArrow = (ImageView) view.findViewById(R.id.item_view_arrow);
        mViewLine = view.findViewById(R.id.item_view_line);
        mTvRightText = (TextView) view.findViewById(R.id.item_view_right_text);
        mTvContent = (TextView) view.findViewById(R.id.item_view_content);
        mIvSwitch = (Switch) view.findViewById(R.id.item_view_switch);
    }

    public void setText(String text) {
        mTvText.setText(text);
    }

    public void setText(int resId) {
        mTvText.setText(UIUtils.getString(resId));
    }

    /**
     * 测试环境改变
     */
    public void setSwitchToShow(boolean isShow) {
        if (isShow) {
            mIvSwitch.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 测试环境改变
     */
    public void changeEnvironment(boolean isOn) {
        if (isOn) {
            mIvSwitch.setChecked(true);
        } else {
            mIvSwitch.setChecked(false);
        }
    }


    /**
     * 设置文本，并且隐藏图标
     *
     * @param resId 文本资源id
     */
    public void setTextAndHideIcon(int resId) {
        mIvIcon.setVisibility(View.GONE);
        mTvText.setText(UIUtils.getString(resId));
        LayoutParams params = (LayoutParams) mTvText.getLayoutParams();
        params.leftMargin = (int) UIUtils.getResources()
                .getDimension(R.dimen.view_line_margin);
    }

    /**
     * 设置文本，并且隐藏图标
     *
     * @param text 文本
     */
    public void setTextAndHideIcon(String text) {
        mIvIcon.setVisibility(View.GONE);
        mTvText.setText(text);
        LayoutParams params = (LayoutParams) mTvText.getLayoutParams();
        params.leftMargin = (int) UIUtils.getResources()
                .getDimension(R.dimen.view_line_margin);

        LayoutParams params1 = (LayoutParams) mViewLine.getLayoutParams();
        params1.leftMargin = (int) UIUtils.getResources()
                .getDimension(R.dimen.view_line_margin);

    }

    public void setTextColor(int color) {
        mTvText.setTextColor(color);
    }

    /**
     * 设置左侧图标
     *
     * @param resId 图片资源id
     */
    public void setIcon(int resId) {
        mIvIcon.setImageResource(resId);
    }

    /**
     * 是否展示左侧图标
     *
     * @param isShow ture 展示 false 不展示
     */
    public void isShowIcon(boolean isShow) {
        mIvIcon.setVisibility(isShow
                ? View.VISIBLE
                : View.GONE);
    }

    /**
     * 设置右侧文本，并且隐藏箭头图标
     *
     * @param resId 文本资源id
     */
    public void setRightText(int resId) {
//        mIvArrow.setVisibility(View.GONE);
        mTvRightText.setText(UIUtils.getString(resId));

    }

    /**
     * 设置右侧文本，并且隐藏箭头图标
     *
     * @param text 文本
     */
    public void setRightText(String text) {
//        mIvArrow.setVisibility(View.GONE);
        mTvRightText.setText(text);

    }

    /**
     * 设置右侧文本的颜色
     *
     * @param color
     */
    public void setRightTextColor(int color) {
        mTvRightText.setTextColor(color);
    }

    /**
     * 获取右侧文本
     *
     * @return 获取到的文本
     */
    public String getRightText() {
        return mTvRightText.getText().toString();
    }

    /**
     * 设置右侧点击事件
     *
     * @param listener 点击监听器
     */
    public void setRightTextListener(OnClickListener listener) {
        mTvRightText.setOnClickListener(listener);
    }

    /**
     * 设置右侧图片资源
     *
     * @param resId 图片资源id
     */
    public void setRightIcon(int resId) {
        mIvArrow.setImageResource(resId);
    }

    /**
     * 是否显示下划线
     *
     * @param isShow true 显示， false 不不显示
     */
    public void isShowLine(boolean isShow) {
        mViewLine.setVisibility(isShow
                ? View.VISIBLE
                : View.GONE);
    }

    /**
     * 设置下划线颜色
     *
     * @param color
     */
    public void setLineColor(int color) {
        mViewLine.setBackgroundColor(color);
    }

    /**
     * 设置内容
     *
     * @param text
     */
    public void setContentText(String text) {
        mTvContent.setText(text);
    }

    public String getContentText() {
        return mTvContent.getText().toString();
    }

    /**
     * 设置内容
     *
     * @param resId
     */
    public void setContentText(int resId) {
        mTvContent.setText(UIUtils.getString(resId));
    }

    /**
     * 测试环境改变
     */
    private void setListener() {
        mIvSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckListener != null) {
                    mCheckListener.statusChange(b);
                }
            }
        });
    }

    public void setCheckListener(CheckStatus checkListener) {
        this.mCheckListener = checkListener;
    }

    public interface CheckStatus {
        public void statusChange(boolean b);
    }
}
