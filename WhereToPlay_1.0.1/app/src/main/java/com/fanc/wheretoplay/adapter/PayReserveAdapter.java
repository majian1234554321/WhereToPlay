package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.databinding.ItemPayBinding;
import com.fanc.wheretoplay.datamodel.BookList;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class PayReserveAdapter extends RecyclerView.Adapter<PayReserveAdapter.ViewHolder> {

    public final int SCROLL_UP = 0;// 向上滑
    public final int SCROLL_DOWN = 1;// 向下滑

    List mData;
    Context mContext;
    // 取消订单监听
    OnCancelClickListener listener;
    private int lastPosition;// 上次的下标
    // 滑动方向
    private int scroll_orientation = SCROLL_DOWN;

    public PayReserveAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_pay, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    private void startAnimation(final View view, int position) {
        if (position != lastPosition) {
            AnimationSet animationSet = null;
            if (scroll_orientation == SCROLL_DOWN) {
                animationSet = (AnimationSet) AnimationUtils.loadAnimation(mContext, R.anim.anim_item_right_in);
            } else {
                animationSet = (AnimationSet) AnimationUtils.loadAnimation(mContext, R.anim.anim_item_center_in);
            }
            animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
//            animationSet.setInterpolator(new BounceInterpolator());
            final AnimationSet finalAnimationSet = animationSet;
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    view.startAnimation(finalAnimationSet);
                }
            }.sendEmptyMessageDelayed(0, 20);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        startAnimation(holder.itemView, position);
        final BookList.Book order = (BookList.Book) mData.get(position);
        holder.binding.setOrder(order);
        String sum = "";
        if (Double.parseDouble(order.getPrepay()) == 0D) {// 订金为0
            sum = UIUtils.getString(R.string.pay_reserve_price) + order.getTotal();
        } else {// 订金不为0
            sum = UIUtils.getString(R.string.pay_reserve_price) + order.getTotal() // 总价
                    + " " + UIUtils.getString(R.string.earnest) + ":" + order.getPrepay() // 订金
                    + " (" + UIUtils.getString(R.string.residue) + order.getLeave_money() + ")";
        }
        holder.mTvPayPrice.setText(sum);
//        Glide.with(mContext).load(Network.IMAGE + order.getCover()).into(holder.mIvPayItem);
        holder.mBtnCheckComment.setVisibility(View.GONE);
        holder.mBtnToComment.setVisibility(View.GONE);
        switch (order.getStatus()) {
            case "1":// 已经取消状态，取消/消费按钮隐藏
                holder.mTvOrderStatus.setText(R.string.canceled);
                holder.mBtnCancel.setVisibility(View.GONE);
                holder.mBtnConsume.setVisibility(View.GONE);
                break;
            case "2":// 预定成功状态，取消按钮显示，消费按钮为结账
                holder.mTvOrderStatus.setText(R.string.pay_reserved);
                holder.mBtnCancel.setVisibility(View.VISIBLE);
                holder.mBtnConsume.setText(R.string.paying_the_bill);
                holder.mBtnConsume.setVisibility(View.VISIBLE);
                break;
            case "4":// 已经结单状态，取消按钮隐藏，消费按钮为消费
                holder.mTvOrderStatus.setText(R.string.complete_statement_of_account);
                holder.mBtnCancel.setVisibility(View.GONE);
                holder.mBtnConsume.setVisibility(View.VISIBLE);
                holder.mBtnConsume.setText(R.string.consume);
                holder.mBtnToComment.setVisibility(View.VISIBLE);// 去评价
                break;
            case "5":// 已支付定金状态，取消按钮显示，消费按钮隐藏
            case "6":
                holder.mBtnCancel.setVisibility(View.VISIBLE);
                holder.mBtnConsume.setVisibility(View.GONE);
                if (TextUtils.equals("1", order.getBook_type())) {
                    holder.mTvOrderStatus.setText(R.string.pay_reserve_earnest1);
                } else if (TextUtils.equals("2", order.getBook_type())) {
                    holder.mTvOrderStatus.setText(R.string.credit_reserve);
                } else {
                    holder.mTvOrderStatus.setText(R.string.recharge);
                    holder.mBtnCancel.setVisibility(View.GONE);
                }
                break;
            default:
                holder.mTvOrderStatus.setText("");
                break;
        }
        // 次日八点后所有订单不可用
        if (TextUtils.equals("0", order.getConsume_again())) {// 不可用
            holder.mBtnConsume.setVisibility(View.GONE);
            holder.mBtnCancel.setVisibility(View.GONE);
        } else {// 可用
            holder.mBtnConsume.setVisibility(View.VISIBLE);
            holder.mBtnCancel.setVisibility(View.VISIBLE);
            String status = order.getStatus();
            if (TextUtils.equals("1", status) || TextUtils.equals("4", status)) {
                holder.mBtnCancel.setVisibility(View.GONE);
            }
            if (TextUtils.equals("1", status) || TextUtils.equals("6", status) || TextUtils.equals("5", status)) {
                holder.mBtnConsume.setVisibility(View.GONE);
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
        holder.mBtnConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PayBillActivity.class);
                intent.putExtra(Constants.ORDER_ID, order.getOrder_id());
                intent.putExtra(Constants.STORE_ID, order.getStore_id());
                if (TextUtils.equals("4", order.getStatus())) {// 去消费
                    intent.putExtra(Constants.PAGE, Constants.CONSUME);
                }
                if (TextUtils.equals("2", order.getStatus())) {// 去结账
                    intent.putExtra(Constants.PAGE, Constants.PAYING_THE_BILL);
                }
                mContext.startActivity(intent);
            }
        });
        holder.mBtnToComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.ORDER_ID, order.getOrder_id());
                intent.putExtra(Constants.STORE_ID, order.getStore_id());
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

        private ItemPayBinding binding;

        TextView mTvOrderStatus;
        ImageView mIvPayItem;
        Button mBtnConsume;
        Button mBtnCancel;
        //        TextView mTvPayRealPrice;
        TextView mTvPayPrice;
        //        TextView mTvPayItemEarnest;
//        TextView mTvPayItemRealEarnest;
        Button mBtnToComment;
        Button mBtnCheckComment;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemPayBinding) binding;
            mTvOrderStatus = this.binding.tvPayItemReserved;
            mIvPayItem = this.binding.ivPayItem;
            mBtnCancel = this.binding.btnPayCancelReserve;
            mBtnConsume = this.binding.btnPayConsume;
            mTvPayPrice = this.binding.tvPayItemPrice;
//            mTvPayItemEarnest = this.binding.tvPayItemEarnest;
//            mTvPayItemRealEarnest = this.binding.tvPayItemRealEarnest;
            mBtnToComment = this.binding.btnToComment;
            mBtnCheckComment = this.binding.btnCheckComment;
        }
    }

    public void setOnCancelClickListener(OnCancelClickListener listener) {
        this.listener = listener;
    }

    public interface OnCancelClickListener {
        void onCancelClick(BookList.Book book, int position);
    }

    public void setScrollOrientation(int scrollOrientation) {
        this.scroll_orientation = scrollOrientation;
    }
}
