package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemIntegralDetailBinding;
import com.fanc.wheretoplay.datamodel.ScoreList;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.ViewHolder> {

    Context mContext;
    List mData;

    public IntegralAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_integral_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setScore((ScoreList.Score) mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemIntegralDetailBinding binding;

        TextView mTvIntegralTitle;
        //        TextView mTvIntegralGetWay;
        TextView mTvIntegralNo;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemIntegralDetailBinding) binding;
//            initViews();
        }

        private void initViews() {
            mTvIntegralTitle = binding.tvItemIntegralTitle;
//            mTvIntegralGetWay = binding.tvItemIntegralGetWay;
            mTvIntegralNo = binding.tvItemIntegralNo;
        }
    }
}
