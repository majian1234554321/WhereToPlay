package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemCollectionBinding;
import com.fanc.wheretoplay.datamodel.CollectionList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    Context mContext;
    List mData;

    boolean isDeleting;
    public HashMap<Integer, Boolean> status;

    public CollectionAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        status = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_collection, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CollectionList.Collection collection = (CollectionList.Collection) mData.get(position);
        holder.binding.setCollection(collection);
//        Glide.with(mContext).load(Network.IMAGE + collection.getPath()).into(holder.mIvCollection);
        if (isDeleting) {
            holder.mCbCollectionStatus.setVisibility(View.VISIBLE);
        } else {
            holder.mCbCollectionStatus.setVisibility(View.GONE);
        }
        holder.mCbCollectionStatus.setChecked(getItemStatus(position));
        holder.mCbCollectionStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemStatus(position)) {// 已经选中，则取消
                    clearItemStatus(position);
                } else {// 未选中，则选中
                    setItemStatus(position);
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
        ItemCollectionBinding binding;

        ImageView mIvCollection;
        TextView mTvCollectionTitle;
        TextView mTvCollectionDecorate;
        TextView mTvCollectionArea;
        TextView mTvCollectionDistance;
        TextView mTvCollectionPrice;
        CheckBox mCbCollectionStatus;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemCollectionBinding) binding;
            initViews();
        }

        private void initViews() {
            mIvCollection = binding.ivItemCollection;
            mTvCollectionTitle = binding.tvItemCollectionTitle;
            mTvCollectionDecorate = binding.tvItemCollectionDecorate;
            mTvCollectionArea = binding.tvItemCollectionArea;
            mTvCollectionDistance = binding.tvItemCollectionDistance;
            mTvCollectionPrice = binding.tvItemCollectionPrice;
            mCbCollectionStatus = binding.cbCollectionItemStatus;
        }
    }

    /**
     * 设置是否是删除状态
     *
     * @param deleting
     */
    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
        clearAllStatus();
//        notifyDataSetChanged();
    }

    public void setItemStatus(int position) {
        status.put(position, true);
    }

    public void clearItemStatus(int position) {
        status.remove(position);
    }

    public boolean getItemStatus(int position) {
        if (status.get(position) != null && status.get(position)) {
            return true;
        }
        return false;
    }

    public void clearAllStatus() {
        if (status != null) {
            status.clear();
        }
        notifyDataSetChanged();
    }
}
