package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemWriteCommentBinding;
import com.fanc.wheretoplay.datamodel.CommentPage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class WriteCommentAdapter extends RecyclerView.Adapter<WriteCommentAdapter.ViewHolder> {

    Context mContext;
    List mData;

    HashMap<Integer, Boolean> status;
    int pos;//选中的位置

    public WriteCommentAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        status = new HashMap<>();
        status.put(0,true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWriteCommentBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.item_write_comment, null, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.commentBinding.setComment(((CommentPage.Tag) getItem(position)).getName());
        holder.mTv.setSelected(getItemSelectStatus(position));
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleatSelected(pos);
                setSelected(position);
                pos = position;
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

    public Object getItem(int position) {
        if (mData != null && mData.size() > 0) {
            return mData.get(position);
        }
        return null;
    }

    public void cleatSelected(int position) {
        status.remove(position);
    }

    public void setSelected(int position) {
        status.put(position, true);
        notifyDataSetChanged();
    }

    public boolean getItemSelectStatus(int position) {
        if (status.get(position) != null && status.get(position)) {
            return true;
        }
        return false;
    }

    public CommentPage.Tag getSelectedItem() {
        CommentPage.Tag tag = null;
        for (int i = 0; i < getItemCount(); i++) {
            if (getItemSelectStatus(i)) {
                tag = (CommentPage.Tag) getItem(i);
                break;
            }
        }
        return tag;
    }

    public String getSelectedItemId() {
        String id = null;
        for (int i = 0; i < getItemCount(); i++) {
            if (getItemSelectStatus(i)) {
                id = ((CommentPage.Tag) getItem(i)).getTag_id();
                break;
            }
        }
        return id;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemWriteCommentBinding commentBinding;
        TextView mTv;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            commentBinding = (ItemWriteCommentBinding) binding;
            mTv = commentBinding.tvWriteComment;
        }
    }

}
