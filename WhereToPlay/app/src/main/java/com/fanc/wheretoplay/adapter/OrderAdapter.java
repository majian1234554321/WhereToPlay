package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.databinding.ItemPayBinding;
import com.fanc.wheretoplay.datamodel.BookList;
import com.fanc.wheretoplay.fragment.OrderFragment;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List mData;
    Context mContext;

    boolean isDeleting;
    public HashMap<Integer, Boolean> status;
    OnCancelClickListener listener;
    OrderFragment  orderFragment;

    public OrderAdapter(Context mContext, List mData, OrderFragment  orderFragment) {
        this.mContext = mContext;
        this.mData = mData;
        this.orderFragment = orderFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_pay, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BookList.Book order = (BookList.Book) mData.get(position);
        holder.binding.setOrder(order);
//        Glide.with(mContext).load(Network.IMAGE + order.getCover()).into(holder.mIvOrderItem);
        holder.mCbItemStatus.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, DetailsOrderActivity.class);
                orderFragment.startActivityForResult(intent, 1000);
            }
        });



        String sum = "";
        if (Double.parseDouble(order.getPrepay()) == 0D) {// 订金为0
            sum = UIUtils.getString(R.string.pay_reserve_price) + order.getTotal();
        } else {// 订金不为0
            sum = UIUtils.getString(R.string.pay_reserve_price) + order.getTotal() // 总价
                    + " " + UIUtils.getString(R.string.earnest) + ":" + order.getPrepay() // 订金
                    + " (" + UIUtils.getString(R.string.residue) + order.getLeave_money() + ")";
        }
        holder.mTvPayPrice.setText(sum);

        switch (order.getStatus()) {
            case "1":// 已经取消状态（
                // 可删除），取消/消费按钮隐藏
                holder.mTvOrderStatus.setText(R.string.canceled);
                holder.mBtnCancel.setVisibility(View.GONE);
                holder.mBtnConsume.setVisibility(View.GONE);
                if (isDeleting) {
                    holder.mCbItemStatus.setVisibility(View.VISIBLE);
                    holder.mCbItemStatus.setChecked(getItemStatus(position));
                }
                break;
            case "2":// 预定成功状态，取消按钮显示，消费按钮为结账
                holder.mTvOrderStatus.setText(R.string.pay_reserved);
                holder.mBtnCancel.setVisibility(View.VISIBLE);
                holder.mBtnConsume.setText(R.string.paying_the_bill);
                holder.mBtnConsume.setVisibility(View.VISIBLE);
                break;
            case "4":// 已经结单状态（可删除），取消按钮隐藏，消费按钮为消费
                holder.mTvOrderStatus.setText(R.string.complete_statement_of_account);
                holder.mBtnCancel.setVisibility(View.GONE);
                holder.mBtnConsume.setVisibility(View.VISIBLE);
                holder.mBtnConsume.setText(R.string.consume);
                if (isDeleting) {
                    holder.mCbItemStatus.setVisibility(View.VISIBLE);
                    holder.mCbItemStatus.setChecked(getItemStatus(position));
                }
                break;
            case "5":// 已支付定金状态，取消按钮显示，消费按钮隐藏
            case "6":
                holder.mBtnCancel.setVisibility(View.VISIBLE);
                holder.mBtnConsume.setVisibility(View.GONE);
                holder.mBtnToComment.setVisibility(View.GONE);
                if (TextUtils.equals("1", order.getBook_type())) {// 订金预定
                    holder.mTvOrderStatus.setText(R.string.pay_reserve_earnest1);
                } else if (TextUtils.equals("2", order.getBook_type())) {// 信誉预定
                    holder.mTvOrderStatus.setText(R.string.credit_reserve);
                } else {
                    holder.mTvOrderStatus.setText(R.string.recharge);
                    holder.mBtnCancel.setVisibility(View.GONE);
                    holder.mBtnConsume.setVisibility(View.GONE);
                }
                break;
            default:
                holder.mTvOrderStatus.setText("");
                break;
        }
        if (TextUtils.equals("1", order.getIs_comment())) {
            holder.mBtnCheckComment.setVisibility(View.VISIBLE);
        } else {
            holder.mBtnCheckComment.setVisibility(View.GONE);
        }
        // 次日八点后所有订单不可用
        if (TextUtils.equals("0", order.getConsume_again())) {// 不可用
            holder.mBtnConsume.setVisibility(View.GONE);
            holder.mBtnCancel.setVisibility(View.GONE);
            if (TextUtils.equals("1", order.getIs_comment())) {// 评论过，去查看
                holder.mBtnCheckComment.setVisibility(View.VISIBLE);
                holder.mBtnToComment.setVisibility(View.GONE);
            } else {// 为评论过，去评论
                holder.mBtnCheckComment.setVisibility(View.GONE);
                holder.mBtnToComment.setVisibility(View.VISIBLE);
            }
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
            // 不能去查看
            holder.mBtnCheckComment.setVisibility(View.GONE);
            if (TextUtils.equals("4", status)) {
                holder.mBtnToComment.setVisibility(View.VISIBLE);
            } else {
                holder.mBtnToComment.setVisibility(View.GONE);
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
                if (TextUtils.equals("4", order.getStatus())) {// 去消费
                    intent.putExtra(Constants.STORE_ID, order.getStore_id());
                    intent.putExtra(Constants.PAGE, Constants.CONSUME);
                }
                if (TextUtils.equals("2", order.getStatus())) {// 去结账
                    intent.putExtra(Constants.PAGE, Constants.PAYING_THE_BILL);
                }
                mContext.startActivity(intent);
            }
        });
        holder.mCbItemStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemStatus(position)) {// 已经选中，则取消
                    clearItemStatus(position);
                } else {// 未选中，则选中
                    setItemStatus(position);
                }
            }
        });
//        holder.mBtnCheckComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, CheckCommentActivity.class);
//                intent.putExtra(Constants.STORE_ID, order.getStore_id());
//                mContext.startActivity(intent);
//            }
//        });
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

    /**
     * 设置是否是删除状态
     *
     * @param deleting
     */
    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
        if (status == null && deleting) {
            status = new HashMap<>();
        }
        clearAllStatus();
        notifyDataSetChanged();
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

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPayBinding binding;

        TextView mTvOrderStatus;
        CheckBox mCbItemStatus;
        ImageView mIvOrderItem;
        Button mBtnConsume;
        Button mBtnCancel;
        TextView mTvPayPrice;
        Button mBtnToComment;
        Button mBtnCheckComment;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemPayBinding) binding;
            mTvOrderStatus = this.binding.tvPayItemReserved;
            mCbItemStatus = this.binding.cbPayItemStatus;
            mIvOrderItem = this.binding.ivPayItem;
            mBtnCancel = this.binding.btnPayCancelReserve;
            mBtnConsume = this.binding.btnPayConsume;
            mTvPayPrice = this.binding.tvPayItemPrice;
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

}
