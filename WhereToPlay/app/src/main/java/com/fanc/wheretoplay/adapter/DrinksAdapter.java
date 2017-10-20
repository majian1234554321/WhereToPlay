package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemDrinksBinding;
import com.fanc.wheretoplay.datamodel.Drinks;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    Context mContext;
    List mData;

    public DrinksAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_drinks, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drinks.Wine wine = (Drinks.Wine) mData.get(position);
        holder.binding.setWine(wine);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDrinksBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDrinksBinding) binding;
        }
    }
}
