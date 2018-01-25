package com.fanc.wheretoplay.base;

import android.content.Context;
import android.view.LayoutInflater;


import com.fanc.wheretoplay.util.SPUtils;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    SPUtils mSpUtils;

    /**
     * Context
     */
    private Context context;
    /**
     * 数据集合
     */
    private List<T> data;

    private LayoutInflater inflater;

    /**
     * 构造方法
     * @param context
     * @param data
     */
    public BaseAdapter(Context context, List<T> data) {
        super();
        mSpUtils = new SPUtils(context);
        setContext(context);
        setData(data);
        setLayouInflater();
    }

    private final void setContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("传入的Context不能为空null");
        }
        this.context = context;
    }


    protected final Context getContext() {
        return context;
    }


    private void setLayouInflater() {
        inflater = LayoutInflater.from(context);
    }


    protected final LayoutInflater getLayoutInflater() {
        return inflater;
    }

    protected final void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<T>();
        }
        this.data = data;
    }

    protected final List<T> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 是否登录
    public boolean isLogin() {
        return mSpUtils.getBoolean(com.fanc.wheretoplay.util.Constants.IS_SIGN_IN, false);
    }

}
