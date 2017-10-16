package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.databinding.ItemReserveListBinding;
import com.fanc.wheretoplay.datamodel.OrderList;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */

public class OrderNewAdapter extends RecyclerView.Adapter<OrderNewAdapter.ViewHolder> {

    public final int SCROLL_UP = 0;// 向上滑
    public final int SCROLL_DOWN = 1;// 向下滑

    Context mContext;
    List mData;
    // 取消订单监听
    OnCancelClickListener listener;
    private int lastPosition;// 上次的下标
    // 滑动方向
    private int scroll_orientation = SCROLL_DOWN;

    public OrderNewAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_reserve_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final OrderList.Order order = (OrderList.Order) mData.get(position);
        holder.binding.setOrder(order);
        // 折扣
        if (order.discount.length() > 0) {
            SpannableString text = new SpannableString(order.discount + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mTvDiscount.setText(text, TextView.BufferType.SPANNABLE);
            holder.mTvDiscount.setVisibility(View.VISIBLE);
        } else {
            holder.mTvDiscount.setVisibility(View.GONE);
        }
        // 到店时间
        String time = DateFormatUtil.getYYYYMMDDHHmm(order.arrival_time).substring(5);
        holder.mTvTime.setText("到店时间 " + time);
        // 预订方式
        if (TextUtils.equals("1", order.book_type)) {
            holder.mIvWay.setImageResource(R.drawable.prepay);
        } else {
            holder.mIvWay.setImageResource(R.drawable.credit);
        }
        holder.mBtnToComment.setVisibility(View.GONE);// 去评价
        holder.mBtnCancel.setVisibility(View.GONE);// 去掉订单
        holder.mBtnBuy.setVisibility(View.GONE);// 买单/消费
        switch (order.status) {
            case "1":// 已经取消状态，取消/消费按钮隐藏
                holder.mIvState.setImageResource(R.drawable.order_cancel);
                holder.mTvState.setTextColor(UIUtils.getColor(R.color.order_cancel));
                holder.mTvState.setText(R.string.canceled);
                break;
            case "2":// 预定成功状态，取消按钮显示，消费按钮为结账
                holder.mIvState.setImageResource(R.drawable.reserved);
                holder.mTvState.setTextColor(UIUtils.getColor(R.color.order_reserved));
                holder.mTvState.setText(R.string.pay_reserved);
                holder.mBtnCancel.setVisibility(View.VISIBLE);
                holder.mBtnBuy.setText(R.string.buy);
                holder.mBtnBuy.setVisibility(View.VISIBLE);
                break;
            case "4":// 已经结账状态，取消按钮隐藏，消费按钮为消费
                holder.mIvState.setImageResource(R.drawable.close_account);
                holder.mTvState.setTextColor(UIUtils.getColor(R.color.close_account));
                holder.mTvState.setText(R.string.payed_the_bill);
                holder.mBtnBuy.setVisibility(View.VISIBLE);
                holder.mBtnBuy.setText(R.string.consume);
                holder.mBtnToComment.setVisibility(View.VISIBLE);// 去评价
                break;
            case "0":
            case "5":// 已支付定金状态，取消按钮显示，消费按钮隐藏
            case "6":
                holder.mIvState.setImageResource(R.drawable.reserving);
                holder.mTvState.setTextColor(UIUtils.getColor(R.color.orange_text));
                holder.mTvState.setText(R.string.reserving);
                holder.mBtnCancel.setVisibility(View.VISIBLE);
//                if (TextUtils.equals("1", order.getBook_type())) {
//                    holder.mTvOrderStatus.setText(R.string.pay_reserve_earnest1);
//                } else if (TextUtils.equals("2", order.getBook_type())) {
//                    holder.mTvOrderStatus.setText(R.string.credit_reserve);
//                } else {
//                    holder.mTvOrderStatus.setText(R.string.recharge);
//                    holder.mBtnCancel.setVisibility(View.GONE);
//                }
                break;
            default:
                holder.mIvState.setVisibility(View.GONE);
//                holder.mTvState.setTextColor(UIUtils.getColor(R.color.orange_text));
                holder.mTvState.setText("");
                break;
        }

        // 次日八点后所有订单不可用
        if (TextUtils.equals("0", order.consume_again)) {// 不可用
            holder.mBtnBuy.setVisibility(View.GONE);
            holder.mBtnCancel.setVisibility(View.GONE);
        } else {// 可用
            holder.mBtnBuy.setVisibility(View.VISIBLE);
            holder.mBtnCancel.setVisibility(View.VISIBLE);
            String status = order.status;
            if (TextUtils.equals("1", status) || TextUtils.equals("4", status)) {
                holder.mBtnCancel.setVisibility(View.GONE);
            }
            if (TextUtils.equals("1", status) || TextUtils.equals("6", status) || TextUtils.equals("5", status)) {
                holder.mBtnBuy.setVisibility(View.GONE);
            }
        }

        holder.mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancelClick(order, position);
                }
            }
        });
        holder.mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PayBillActivity.class);
                intent.putExtra(Constants.ORDER_ID, order.order_id);
                intent.putExtra(Constants.STORE_ID, order.store_id);
                if (TextUtils.equals("4", order.status)) {// 去消费
                    intent.putExtra(Constants.PAGE, Constants.CONSUME);
                }
                if (TextUtils.equals("2", order.status)) {// 去结账
                    intent.putExtra(Constants.PAGE, Constants.PAYING_THE_BILL);
                }
                mContext.startActivity(intent);
            }
        });
        holder.mBtnToComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.ORDER_ID, order.order_id);
                intent.putExtra(Constants.STORE_ID, order.store_id);
                intent.putExtra(Constants.PAGE, Constants.COMMENT);
                mContext.startActivity(intent);
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
        ItemReserveListBinding binding;

        TextView mTvDiscount;
        ImageView mIvState;
        TextView mTvState;
        ImageView mIvWay;
        TextView mTvTime;
        Button mBtnBuy;
        Button mBtnCancel;
        Button mBtnToComment;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemReserveListBinding) binding;
            mTvDiscount = this.binding.tvReserveListItemDiscount;
            mIvWay = this.binding.ivReserveListItemWay;
            mIvState = this.binding.ivReserveListItemState;
            mTvState = this.binding.tvReserveListItemState;
            mTvTime = this.binding.tvReserveListItemTime;
            mBtnBuy = this.binding.btnReserveListItemBuy;
            mBtnCancel = this.binding.btnReserveListItemCancel;
            mBtnToComment = this.binding.btnReserveListItemToComment;
        }
    }

    public void setOnCancelClickListener(OnCancelClickListener listener) {
        this.listener = listener;
    }

    public interface OnCancelClickListener {
        void onCancelClick(OrderList.Order order, int position);
    }

    public void setScrollOrientation(int scrollOrientation) {
        this.scroll_orientation = scrollOrientation;
    }

}
