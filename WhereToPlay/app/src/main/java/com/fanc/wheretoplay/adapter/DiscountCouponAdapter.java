package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemDiscountCouponBinding;
import com.fanc.wheretoplay.datamodel.DiscountCouponList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class DiscountCouponAdapter extends RecyclerView.Adapter<DiscountCouponAdapter.ViewHolder> {

    Context mContext;
    List mData;

    boolean isChoose;
    HashMap<Integer, Boolean> status;
    int pre;

    public DiscountCouponAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_discount_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DiscountCouponList.DiscountCoupon discountCoupon = (DiscountCouponList.DiscountCoupon) mData.get(position);
        holder.binding.setDiscount(discountCoupon);
//        holder.mTvDiscountSum.setText(String.valueOf((int) Double.parseDouble(discountCoupon.getPrice())));
//        holder.mTvDiscountDeadline.setText(DateFormatUtil.getDateFormatString(discountCoupon.getStart_time()) +
//                "-" + DateFormatUtil.getDateFormatString(discountCoupon.getEnd_time()));
        if (!isChoose) {
            holder.mRbDiscountStatus.setVisibility(View.GONE);
        } else {
            holder.mRbDiscountStatus.setVisibility(View.VISIBLE);
            holder.mRbDiscountStatus.setChecked(getItemStatus(position));
            holder.mRbDiscountStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pre != position) {// 和上一次点的相同
                        cleanItemStatus(pre);
                        setItemStatus(position);
                    } else {
                        if (getItemStatus(position)) {
                            cleanItemStatus(position);
                        } else {
                            setItemStatus(position);
                        }
                    }
                    pre = position;
                    notifyDataSetChanged();
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pre != position) {// 和上一次点的相同
                        cleanItemStatus(pre);
                        setItemStatus(position);
                    } else {
                        if (getItemStatus(position)) {
                            cleanItemStatus(position);
                        } else {
                            setItemStatus(position);
                        }
                    }
                    pre = position;
                    notifyDataSetChanged();
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

    public void setChoose(boolean choose) {
        isChoose = choose;
        if (status == null) {
            status = new HashMap<>();
        }
    }

    private void setItemStatus(int position) {
        status.put(position, true);
    }

    private void cleanItemStatus(int position) {
        status.remove(position);
    }

    private boolean getItemStatus(int position) {
        return status.get(position) != null && status.get(position);
    }

    public DiscountCouponList.DiscountCoupon getSelectedItem() {
        for (int i : status.keySet()) {
            if (status.get(i)) {
                return (DiscountCouponList.DiscountCoupon) mData.get(i);
            }
        }
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemDiscountCouponBinding binding;

        TextView mTvDiscountSum;
        TextView mTvDiscountDeadline;
        RadioButton mRbDiscountStatus;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDiscountCouponBinding) binding;
            initViews();
        }

        private void initViews() {
            mTvDiscountSum = binding.tvItemDiscountCouponSum;
            mTvDiscountDeadline = binding.tvItemDiscountCouponDeadline;
            mRbDiscountStatus = binding.rbItemDiscountStatus;
        }

    }

}
