package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemSideslipBinding;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SideslipAdapter extends RecyclerView.Adapter<SideslipAdapter.ViewHolder> {
    Context mContext;
    List mData;

    OnItemClickListener listener;

    public SideslipAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_sideslip, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTvTitle.setText((String) mData.get(position));
        switch (position) {
            case 0:
                holder.mIvIcon.setImageResource(R.drawable.business_ktv);
                break;
            case 1:
                holder.mIvIcon.setImageResource(R.drawable.ktv);
                break;
            case 2:
                holder.mIvIcon.setImageResource(R.drawable.illation);
                break;
            case 3:
                holder.mIvIcon.setImageResource(R.drawable.bar);
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick((String) mData.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvIcon;
        TextView mTvTitle;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mIvIcon = ((ItemSideslipBinding) binding).ivItemSideslip;
            mTvTitle = ((ItemSideslipBinding) binding).tvItemSideslip;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String title, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
