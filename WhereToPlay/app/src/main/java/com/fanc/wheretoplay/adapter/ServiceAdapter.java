package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemServiceChatLeftBinding;
import com.fanc.wheretoplay.databinding.ItemServiceChatRightBinding;
import com.fanc.wheretoplay.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */

public class ServiceAdapter extends RecyclerView.Adapter {
    Context mContext;
    List mData;

    public ServiceAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        switch (viewType) {
            case 1:
            default:
                viewHolder = new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_service_chat_right, parent, false), viewType);
                break;
            case 2:
                viewHolder = new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_service_chat_left, parent, false), viewType);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiceChatLeftBinding leftBinding;
        ItemServiceChatRightBinding rightBinding;

        TextView mTvTime;
        CircleImageView mCivHead;
        TextView mTvContent;

        public ViewHolder(ViewDataBinding binding, int itemViewType) {
            super(binding.getRoot());
            switch (itemViewType) {
                case 1:
                    rightBinding = (ItemServiceChatRightBinding) binding;
                    mTvContent = rightBinding.tvServiceChatContent;
                    mTvTime = rightBinding.tvServiceChatTime;
                    mCivHead = rightBinding.civHead;
                    break;
                case 2:
                    leftBinding = (ItemServiceChatLeftBinding) binding;
                    mTvContent = leftBinding.tvServiceChatContent;
                    mTvTime = leftBinding.tvServiceChatTime;
                    mCivHead = leftBinding.civHead;
                    break;
            }
        }

    }
}
