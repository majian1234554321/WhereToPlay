package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemConsumeDetailBinding;
import com.fanc.wheretoplay.datamodel.Consume;
import com.fanc.wheretoplay.util.DateFormatUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ConsumeDetailAdapter extends RecyclerView.Adapter<ConsumeDetailAdapter.ViewHolder> {

    Context mContext;
    List mData;

    public ConsumeDetailAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_consume_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Consume.ConsumeDetail consumeDetail = (Consume.ConsumeDetail) mData.get(position);
        holder.detailBinding.setDetail(consumeDetail);
        if (consumeDetail.getCreated_time() != null && !consumeDetail.getCreated_time().isEmpty() && !TextUtils.equals("0", consumeDetail.getCreated_time())) {
            holder.mTvItemConsumeSubscriptionTime.setText(
                    DateFormatUtil.getDateTimeStr(new Date(Long.parseLong(consumeDetail.getCreated_time()) * 1000)));
        }
        if (consumeDetail.getReserve_time() != null && !consumeDetail.getReserve_time().isEmpty() && !TextUtils.equals("0", consumeDetail.getReserve_time())) {
            holder.mTvItemConsumeOrderTime.setText(
                    DateFormatUtil.getDateTimeStr(new Date(Long.parseLong(consumeDetail.getReserve_time()) * 1000)));
        }
        /*if (Double.parseDouble(consumeDetail.getPrepay()) == 0D) {
            holder.mLlTvItemConsumeSubscription.setVisibility(View.GONE);
        }
        if (Double.parseDouble(consumeDetail.getAccount()) == 0D) {
            holder.mLlTvItemConsumeOrder.setVisibility(View.GONE);
        }*/
       /* if (consumeDetail.getDiscount()!=null){
            if (Double.parseDouble(consumeDetail.getDiscount()) == 0D) {
                holder.mLlTvItemConsumeDiscount.setVisibility(View.GONE);
            }
        }*/

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemConsumeDetailBinding detailBinding;

        TextView mTvItemConsumeSubscriptionTime;
        TextView mTvItemConsumeOrderTime;
        LinearLayout mLlTvItemConsumeSubscription;
        LinearLayout mLlTvItemConsumeOrder;
        LinearLayout mLlTvItemConsumeDiscount;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            detailBinding = (ItemConsumeDetailBinding) binding;
            mTvItemConsumeSubscriptionTime = detailBinding.tvItemConsumeSubscriptionTime;
            mTvItemConsumeOrderTime = detailBinding.tvItemConsumeOrderTime;
            mLlTvItemConsumeSubscription = detailBinding.llItemConsumeSubscription;
            mLlTvItemConsumeOrder = detailBinding.llItemConsumeOrder;
            mLlTvItemConsumeDiscount = detailBinding.llItemConsumeDiscount;
        }
    }

}
