package com.fanc.wheretoplay.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.FilterPopDialogAdapter;
import com.fanc.wheretoplay.databinding.PopFilterBinding;
import com.fanc.wheretoplay.util.UIUtils;

/**
 * Created by Administrator on 2017/7/11.
 */

public class FilterPopupDialog extends PopupWindow {

    Context mContext;
    ListView mlvPopFilterStoreType;
    ListView mLvPopFilter;
    ListView mLvPopFilterChild;

    BaseAdapter childAdapter;

    public FilterPopupDialog(Context context) {
        this(context, null, R.style.PopupWindowTrans);
    }

    public FilterPopupDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        PopFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.pop_filter, null, false);
        mlvPopFilterStoreType = binding.lvPopFilterStoreType;
        mLvPopFilter = binding.lvPopFilter;
        mLvPopFilterChild = binding.lvPopFilterChild;

        init(binding.getRoot());
    }

    private void init(View root) {
        setContentView(root);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(UIUtils.getScreenWidth() / 3);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);// 设置弹出窗体可点击
        setTouchable(true);
        setOutsideTouchable(true);

        setOnTouchListener();
    }

    /**
     * 窗口高度
     *
     * @param dp
     * @return
     */
    public FilterPopupDialog setPopupWindowHeight(int dp) {
        if (dp >= 0) {
            setHeight(UIUtils.dp2Px(dp));
        } else {
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        return this;
    }

    /**
     * 窗口内部点击事件
     */
    private void setOnTouchListener() {
        this.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 第三级菜单
                if (mLvPopFilterChild.getVisibility() == View.INVISIBLE) {
                    if (event.getX() > getWidth() * 2 / 3) {
                        dismiss();
                        return true;
                    }
                } else {
                    if (mLvPopFilterChild.getVisibility() == View.VISIBLE) {
                        if (event.getX() > getWidth() * 2 / 3 && event.getY() > mLvPopFilterChild.getBottom()) {
                            dismiss();
                            return true;
                        }
                    }
                }
                if (mlvPopFilterStoreType.getVisibility() == View.VISIBLE) {
                    // 第一级菜单
                    if (event.getX() < getWidth() / 3 && event.getY() > mlvPopFilterStoreType.getBottom()) {
                        dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 显示在某个控件下方
     *
     * @param anchor
     * @return
     */
    public FilterPopupDialog showAsViewDown(View anchor) {
        if (childAdapter != null) {
            setWidth(UIUtils.getScreenWidth() / 3 * 2);
            if (((FilterPopDialogAdapter) mLvPopFilter.getAdapter()).getIsClicked()) {
                mLvPopFilterChild.setVisibility(View.VISIBLE);
            } else {
                mLvPopFilterChild.setVisibility(View.INVISIBLE);
            }
        }
        super.showAsDropDown(anchor);
        return this;
    }

    /**
     * 显示在某个控件下方,包含店家类型
     *
     * @param anchor
     * @return
     */
    public FilterPopupDialog showAsViewDownWithStoreType(View anchor) {
        setWidth(UIUtils.getScreenWidth());
        mlvPopFilterStoreType.setVisibility(View.VISIBLE);
        if (((FilterPopDialogAdapter) mLvPopFilter.getAdapter()).getIsClicked()) {
            mLvPopFilterChild.setVisibility(View.VISIBLE);
        } else {
            mLvPopFilterChild.setVisibility(View.INVISIBLE);
        }
        super.showAsDropDown(anchor);
        return this;
    }

    /**
     * 筛选adapter（区域，筛选，距离最近等）
     */
    public FilterPopupDialog setAdapter(BaseAdapter adapter) {
        mLvPopFilter.setAdapter(adapter);
        return this;
    }

    /**
     * 筛选子集adapter+
     *
     * @param childAdapter
     * @return
     */
    public FilterPopupDialog setChildAdapter(BaseAdapter childAdapter) {
        this.childAdapter = childAdapter;
        mLvPopFilterChild.setAdapter(this.childAdapter);
        return this;
    }

    /**
     * 筛选店家类型adapter
     */
    public FilterPopupDialog setStoreTypeAdapter(BaseAdapter adapter) {
        mlvPopFilterStoreType.setAdapter(adapter);
        return this;
    }

    /**
     * 显示筛选子级
     *
     * @return
     */
    public FilterPopupDialog showChildList() {
        if (mLvPopFilterChild.getVisibility() != View.VISIBLE) {
//            setWidth(UIUtils.getScreenWidth() / 3 * 2);
            mLvPopFilterChild.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 隐藏筛选子级
     */
    public FilterPopupDialog dismissChildList() {
        if (mLvPopFilterChild.getVisibility() != View.GONE) {
            setWidth(UIUtils.getScreenWidth() / 3);
            mLvPopFilterChild.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 主列表（中间）点击监听
     *
     * @param listener
     * @return
     */
    public FilterPopupDialog setOnItemClickListener(final OnItemClickListener listener) {
        mLvPopFilter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onItemClick(parent, view, position, id);
                }
            }
        });
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }
}
