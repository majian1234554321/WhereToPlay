package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemCommentIconBinding;
import com.fanc.wheretoplay.datamodel.StoreDetail;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class CommentIconAdapter extends RecyclerView.Adapter<CommentIconAdapter.ViewHolder> {

    Context mContext;
    List mData;

    OnItemClickListener listener;

    public CommentIconAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_comment_icon, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(Network.IMAGE + ((StoreDetail.CommentBean) mData.get(position)).getAvatar())
                .error(R.drawable.default_square)
                .into(holder.mCivCommentIcon);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCommentIconBinding binding;

        CircleImageView mCivCommentIcon;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemCommentIconBinding) binding;
            mCivCommentIcon = this.binding.civItemCommentIcon;
        }
    }

}
