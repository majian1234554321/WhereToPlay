package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemSystemNotifyBinding;
import com.fanc.wheretoplay.datamodel.NotificationList;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class SystemNotifyAdapter extends RecyclerView.Adapter<SystemNotifyAdapter.ViewHolder> {

    Context mContext;
    List mData;

    public SystemNotifyAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_system_notify, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.notifyBinding.setNotification((NotificationList.Notification) mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemSystemNotifyBinding notifyBinding;

        TextView mTvContent;
        TextView mTvTime;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            notifyBinding = (ItemSystemNotifyBinding) binding;
            initViews();
        }

        private void initViews() {
            mTvContent = notifyBinding.tvItemSystemNotifyContent;
            mTvTime = notifyBinding.tvItemSystemNotifyTime;
        }
    }

}
