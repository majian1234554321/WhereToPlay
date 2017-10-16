package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemCommentBinding;
import com.fanc.wheretoplay.datamodel.CommentDetail;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class CommentDetailAdapter extends RecyclerView.Adapter<CommentDetailAdapter.ViewHolder> {

    Context mContext;
    List mData;

    public CommentDetailAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentDetail.Comment comment = (CommentDetail.Comment) mData.get(position);
        holder.binding.setComment(comment);
        holder.mTvCommentDate.setText(DateFormatUtil.getYYYY_MM_DDString(comment.created_time));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemCommentBinding binding;

        CircleImageView mCivComment;
        TextView mTvCommentNickname;
        TextView mTvCommentDate;
        TextView mTvCommentLabel;
        TextView mTvCommentContent;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemCommentBinding) binding;
            initViews();
        }

        private void initViews() {
            mCivComment = binding.civItemComment;
            mTvCommentNickname = binding.tvItemCommentNickname;
            mTvCommentDate = binding.tvItemCommentDate;
            mTvCommentLabel = binding.tvItemCommentLabel;
            mTvCommentContent = binding.tvItemCommentContent;
        }
    }
}
