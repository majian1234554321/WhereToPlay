package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.datamodel.BookListModel;

import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.image.GlideCatchUtil;
import com.fanc.wheretoplay.image.GlideImageLoader;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.BaseResponseModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxHelper;
import com.fanc.wheretoplay.rx.RxSubscribe;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                intent.putExtra("total", dataBean.list.get(position).total);
                if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
                    intent.putExtra("status", dataBean.list.get(position).order_action);
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
        if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
            switch (dataBean.list.get(position).order_action) {
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

//        if (dataBean.list != null && dataBean.list.get(position).book_type != null) {
//            switch (dataBean.list.get(position).book_type) {
//                case "1":
//                    holder.tvPayItemTitle.setText("预订方式：订金预订");
//                    break;
//                case "2":
//                    holder.tvPayItemTitle.setText("预订方式：信誉预订");
//                    break;
//                case "3":
//                    holder.tvPayItemTitle.setText("预订方式：充值");
//                    break;
//                default:
//                    holder.tvPayItemTitle.setText("预订方式：...");
//                    break;
//            }
//        }

        holder.btnPayConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, PayBillActivity.class);
                intent.putExtra(Constants.ORDER_ID, dataBean.list.get(position).order_id);
                intent.putExtra(Constants.STORE_ID, dataBean.list.get(position).store_id);
                if (TextUtils.equals("4", dataBean.list.get(position).order_action)) {// 去消费
                    intent.putExtra(Constants.PAGE, Constants.CONSUME);
                }
                if (TextUtils.equals("2", dataBean.list.get(position).order_action)) {// 去结账
                    intent.putExtra(Constants.PAGE, Constants.PAYING_THE_BILL);
                }
                context.startActivity(intent);
            }
        });


        holder.btnPayCancelReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(context)
                        .setTitle("提示")
                        .setContent("确定取消订单吗")
                        .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                            @Override
                            public void onBtnClick(View view, String input) {
                                //cancelOrder(order, position);

                                MultipartBody.Part requestFileA =
                                        MultipartBody.Part.createFormData("token", new SPUtils(context).getUser().getToken());

                                MultipartBody.Part requestFileC =
                                        MultipartBody.Part.createFormData("id", dataBean.list.get(position).order_id);

                                Retrofit_RequestUtils.getRequest().cancle_order(requestFileA, requestFileC)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Subscriber<BaseResponseModel<CancleOrderModel.ContentBean>>() {
                                            @Override
                                            public void onCompleted() {

                                            }

                                            @Override
                                            public void onError(Throwable throwable) {

                                            }

                                            @Override
                                            public void onNext(BaseResponseModel<CancleOrderModel.ContentBean> contentBean) {
                                                if (contentBean.success()&&contentBean.content.is_cancle) {
                                                    ToastUtils.showShortToast(context, "取消订单成功");
                                                    dataBean.list.remove(position);
                                                    notifyDataSetChanged();
                                                } else {
                                                    ToastUtils.showShortToast(context, "取消订单失败");
                                                }
                                            }
                                        });


                            }
                        })
                        .setCanceledOnTouchOutside(true)
                        .show();
            }
        });

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
