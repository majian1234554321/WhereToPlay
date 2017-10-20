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
import com.fanc.wheretoplay.databinding.ItemSearchBinding;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context mContext;
    List mData;

    OnItemClickListener listener;

    public SearchAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String search = (String) mData.get(position);
        holder.searchBinding.setSearch(search);
        holder.mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(search, position);
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

        ItemSearchBinding searchBinding;
        TextView mTvSearch;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            searchBinding = (ItemSearchBinding) binding;
            mTvSearch = searchBinding.tvItemSearch;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String search, int position);
    }

}
