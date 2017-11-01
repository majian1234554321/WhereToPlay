package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/11/1.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    public Context context;


    public OrdersAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_pay_item_status)
        CheckBox cbPayItemStatus;
        @BindView(R.id.iv_pay_item)
        ImageView ivPayItem;
        @BindView(R.id.tv_pay_item_title)
        TextView tvPayItemTitle;
        @BindView(R.id.tv_pay_item_reserved)
        TextView tvPayItemReserved;
        @BindView(R.id.tv_pay_item_time)
        TextView tvPayItemTime;
        @BindView(R.id.tv_pay_item_real_time)
        TextView tvPayItemRealTime;
        @BindView(R.id.tv_pay_item_room_category)
        TextView tvPayItemRoomCategory;
        @BindView(R.id.tv_pay_item_room)
        TextView tvPayItemRoom;
        @BindView(R.id.tv_pay_item_decorate_category)
        TextView tvPayItemDecorateCategory;
        @BindView(R.id.tv_pay_item_decorate)
        TextView tvPayItemDecorate;
        @BindView(R.id.tv_pay_item_reserve_code)
        TextView tvPayItemReserveCode;
        @BindView(R.id.tv_pay_item_reserve_real_code)
        TextView tvPayItemReserveRealCode;
        @BindView(R.id.tv_pay_item_price)
        TextView tvPayItemPrice;
        @BindView(R.id.btn_check_comment)
        Button btnCheckComment;
        @BindView(R.id.btn_to_comment)
        Button btnToComment;
        @BindView(R.id.btn_pay_cancel_reserve)
        Button btnPayCancelReserve;
        @BindView(R.id.btn_pay_consume)
        Button btnPayConsume;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
