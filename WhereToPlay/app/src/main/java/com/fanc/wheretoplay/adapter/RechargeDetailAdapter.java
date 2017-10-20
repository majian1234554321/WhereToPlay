package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemRechargeDetailBinding;
import com.fanc.wheretoplay.datamodel.Recharge;
import com.fanc.wheretoplay.util.DateFormatUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class RechargeDetailAdapter extends RecyclerView.Adapter<RechargeDetailAdapter.ViewHolder> {

    Context mContext;
    List mData;

    public RechargeDetailAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_recharge_detail, null, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recharge.RechargeDetail rechargeDetail = (Recharge.RechargeDetail) mData.get(position);
        holder.binding.setDetail(rechargeDetail);
        if (rechargeDetail.getCreated_time() != null && !rechargeDetail.getCreated_time().isEmpty()) {
            holder.mTvRechargeTime.setText(DateFormatUtil.getDateTimeStr(new Date(Long.parseLong(rechargeDetail.getCreated_time()) * 1000)));
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRechargeDetailBinding binding;

        TextView mTvRechargeTitle;
        TextView mTvRechargeTime;
        TextView mTvRechargeSum;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemRechargeDetailBinding) binding;
            initViews();
        }

        private void initViews() {
            mTvRechargeTitle = binding.tvItemRechargeTitle;
            mTvRechargeTime = binding.tvItemRechargeTime;
            mTvRechargeSum = binding.tvItemRechargeSum;
        }

    }
}
