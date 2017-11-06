package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.datamodel.BookListModel;

import com.fanc.wheretoplay.image.GlideCatchUtil;
import com.fanc.wheretoplay.image.GlideImageLoader;
import com.fanc.wheretoplay.util.DateFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fanc.wheretoplay.network.Network.IMAGE;

/**
 * Created by admin on 2017/11/1.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    public Context context;
    public Fragment fragment;
    public BookListModel.ContentBean dataBean;


    public OrdersAdapter(Context context, Fragment fragment, BookListModel.ContentBean dataBean) {
        this.context = context;
        this.fragment = fragment;
        this.dataBean = dataBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                intent.putExtra("store_id", dataBean.list.get(position).store_id);
                intent.putExtra("storeName", dataBean.list.get(position).name);
                if (dataBean.list != null && dataBean.list.get(position).status != null) {
                    intent.putExtra("status", dataBean.list.get(position).status);
                }
                intent.setClass(context, DetailsOrderActivity.class);
                fragment.startActivityForResult(intent, 1001);
            }
        });


        holder.tv_storeName.setText(dataBean.list.get(position).name);
        holder.tvPayItemRoom.setText(dataBean.list.get(position).room_type);
        holder.tvPayItemDecorate.setText(dataBean.list.get(position).decorate_type);
        holder.tvPayItemReserveRealCode.setText(dataBean.list.get(position).book_sn);
        holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
        GlideImageLoader.display(context, holder.ivPayItem, IMAGE + dataBean.list.get(position).cover);

        holder.tvPayItemRealTime.setText(DateFormatUtil.stampToDate(dataBean.list.get(position).arrival_time) + " 前");


        //status：string，订单状态：1已取消,2预订成功,4已结单，5或6已支付订金
        if (dataBean.list != null && dataBean.list.get(position).status != null) {
            switch (dataBean.list.get(position).status) {
                case "1":
                    holder.tv_payState.setText("已取消");
                    break;
                case "2":
                    holder.tv_payState.setText("预订成功");
                    break;
                case "3":
                    holder.tv_payState.setText("已取消");
                    break;
                case "4":
                    holder.tv_payState.setText("已结单");
                    break;
                case "5":
                    holder.tv_payState.setText("已支付订金");
                    break;
                case "6":
                    holder.tv_payState.setText("已支付订金");
                    break;
                default:
                    holder.tv_payState.setText("....");
                    break;
            }
        }

//book_type：string，预订类型 1-订金预订 2-信誉预订 3-充值

        if (dataBean.list != null && dataBean.list.get(position).book_type != null) {
            switch (dataBean.list.get(position).book_type) {
                case "1":
                    holder.tvPayItemTitle.setText("预订方式：订金预订");
                    break;
                case "2":
                    holder.tvPayItemTitle.setText("预订方式：信誉预订");
                    break;
                case "3":
                    holder.tvPayItemTitle.setText("预订方式：充值");
                    break;
                default:
                    holder.tvPayItemTitle.setText("预订方式：...");
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataBean.list.size();
    }

    public void append(List<BookListModel.ContentBean.ListBean> list) {
        this.dataBean.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_pay_item_status)
        CheckBox cbPayItemStatus;
        @BindView(R.id.iv_pay_item)
        ImageView ivPayItem;
        @BindView(R.id.tv_pay_item_title)
        TextView tvPayItemTitle;
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


        @BindView(R.id.btn_pay_cancel_reserve)
        TextView btnPayCancelReserve;
        @BindView(R.id.btn_pay_consume)
        Button btnPayConsume;

        @BindView(R.id.tv_storeName)
        TextView tv_storeName;
        @BindView(R.id.tv_payState)
        TextView tv_payState;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
