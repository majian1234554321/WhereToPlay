package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.activity.DownPaymentActivity;
import com.fanc.wheretoplay.activity.EvaluationSuccessActivity;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.PublicationEvaluationActivity;
import com.fanc.wheretoplay.datamodel.BookListModel;

import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.image.GlideImageLoader;
import com.fanc.wheretoplay.presenter.DetailsOrderPresenter;
import com.fanc.wheretoplay.rx.BaseResponseModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
                intent.putExtra("discount", dataBean.list.get(position).discount);
                intent.setClass(context, DetailsOrderActivity.class);
               // intent.setClass(context, PublicationEvaluationActivity.class);
                fragment.startActivityForResult(intent, 1001);
            }
        });


        holder.tv_storeName.setText(dataBean.list.get(position).name);
        holder.tvPayItemRoom.setText(dataBean.list.get(position).room_type);
        holder.tvPayItemDecorate.setText(dataBean.list.get(position).decorate_type);
        holder.tvPayItemReserveRealCode.setText(dataBean.list.get(position).book_sn);
        holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
        GlideImageLoader.display(context, holder.ivPayItem, IMAGE + dataBean.list.get(position).cover);
        holder.tv_payState.setText(dataBean.list.get(position).statusdesc);

        holder.tvPayItemRealTime.setText(DateFormatUtil.stampToDate(dataBean.list.get(position).arrival_time) + " 前");


        for (int i = 0; i < holder.lists.size(); i++) {
            final int finalI = i;
            holder.lists.get(i).setVisibility(View.GONE);
            holder.lists.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    switch (holder.lists.get(finalI).getText().toString().trim()) {

                        case "查看":
                            intent.putExtra("discount", dataBean.list.get(position).discount);
                            intent.putExtra("order_id", dataBean.list.get(position).order_id);
                            intent.putExtra("store_id", dataBean.list.get(position).store_id);
                            intent.putExtra("storeName", dataBean.list.get(position).name);
                            intent.putExtra("total", dataBean.list.get(position).total);
                            if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
                                intent.putExtra("status", dataBean.list.get(position).order_action);
                            }
                            intent.setClass(context, DetailsOrderActivity.class);
                            fragment.startActivityForResult(intent, 1001);
                            break;
                        case "取消订单":
                            cancleOrder(position);
                            break;
                        case "立即支付":
                            pay(position);
                            break;
                        case "立即评论":
                            intent.putExtra("address", dataBean.list.get(position).address);
                            intent.putExtra("order_id", dataBean.list.get(position).order_id);
                            intent.putExtra("store_id", dataBean.list.get(position).store_id);
                            intent.putExtra("storeName", dataBean.list.get(position).name);
                            intent.putExtra("total", dataBean.list.get(position).total);
                            if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
                                intent.putExtra("status", dataBean.list.get(position).order_action);
                            }
                            intent.setClass(context, PublicationEvaluationActivity.class);
                            fragment.startActivityForResult(intent, 1001);
                            break;
                        case "转预付":

                            intent.putExtra("flag", "预订支付");
                            intent.putExtra("order_id", dataBean.list.get(position).order_id);
                            intent.putExtra("store_name", dataBean.list.get(position).name);

                            intent.putExtra("pay_type", "1");

                            intent.putExtra("arrival_time", dataBean.list.get(position).arrival_time);
                            intent.putExtra("prepay", dataBean.list.get(position).total);


                            intent.setClass(context, DownPaymentActivity.class);
                            fragment.startActivity(intent);

                            break;
                    }
                }
            });
        }


        for (int i = 0; i < dataBean.list.get(position).buttonlist.size(); i++) {

            if (i + 1 == dataBean.list.get(position).buttonlist.size()) {
                holder.lists.get(i).setBackgroundResource(R.drawable.shape_btn_black_c4483c);
                holder.lists.get(i).setTextColor(Color.WHITE);
            } else {
                holder.lists.get(i).setBackgroundResource(R.drawable.shape_btn_black_stoke);
                holder.lists.get(i).setTextColor(Color.parseColor("#333333"));
            }

            if (dataBean.list.get(position).buttonlist.get(i).title != null) {
                holder.lists.get(i).setText(dataBean.list.get(position).buttonlist.get(i).title);
                holder.lists.get(i).setVisibility(View.VISIBLE);
            } else {
                holder.lists.get(i).setText("");
                holder.lists.get(i).setVisibility(View.GONE);
            }
        }


//book_type：string，预订类型 1-订金预订 2-信誉预订 3-充值

        if (dataBean.list != null && dataBean.list.get(position).order_function != null) {
            switch (dataBean.list.get(position).order_function) {
                case "1":
                    holder.tvPayItemTitle.setText("预订方式：预付预订");
                    break;
                case "2":
                    holder.tvPayItemTitle.setText("预订方式：信用预订");
                    break;
                case "3":
                    holder.tvPayItemTitle.setText("预订方式：充值");
                case "5":
                    holder.tvPayItemTitle.setText("预订方式：结单支付");
                    break;
                default:
                    holder.tvPayItemTitle.setText("预订方式：...");
                    break;
            }
        }


    }

    private void pay(int position) {
        Intent intent = new Intent();
//        intent.setClass(context, PayBillActivity.class);
//        intent.putExtra(Constants.ORDER_ID, dataBean.list.get(position).order_id);
//        intent.putExtra(Constants.STORE_ID, dataBean.list.get(position).store_id);
//        if (TextUtils.equals("4", dataBean.list.get(position).order_action)) {// 去消费
//            intent.putExtra(Constants.PAGE, Constants.CONSUME);
//        }
//        if (TextUtils.equals("2", dataBean.list.get(position).order_action)) {// 去结账
//            intent.putExtra(Constants.PAGE, Constants.PAYING_THE_BILL);
//        }
//        context.startActivity(intent);


        intent.putExtra("address", dataBean.list.get(position).address);
        intent.setClass(context, PayBillActivity.class);
        intent.putExtra(Constants.STORE_ID, dataBean.list.get(position).order_id);
        intent.putExtra("storeName", dataBean.list.get(position).name);
        // intent.putExtra("address", dataBean.list.get(position).add);
        intent.putExtra("discount", dataBean.list.get(position).discount);
        intent.putExtra(Constants.PAGE, "商家详情支付");

        context.startActivity(intent);

    }

    private void cancleOrder(final int position) {

        new AlertDialog(context)
                .setTitle("提示")
                .setContent("确定取消订单吗")
                .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                    @Override
                    public void onBtnClick(View view, String input) {
                        //cancelOrder(order, position);

                        //DetailsOrderPresenter detailsOrderPresenter =  new DetailsOrderPresenter();
                        // detailsOrderPresenter.cancelOrder();
                        MultipartBody.Part requestFileA =
                                MultipartBody.Part.createFormData("token", new SPUtils(context).getUser().getToken());

                        MultipartBody.Part requestFileC =
                                MultipartBody.Part.createFormData("id", dataBean.list.get(position).order_id);

                        Retrofit_RequestUtils.getRequest().cancle_order(requestFileA, requestFileC)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<BaseResponseModel<CancleOrderModel.ContentBean>>() {

                                    @Override
                                    public void onSubscribe(Disposable disposable) {

                                    }

                                    @Override
                                    public void onNext(BaseResponseModel<CancleOrderModel.ContentBean> contentBeanBaseResponseModel) {
                                        if (contentBeanBaseResponseModel.success() && contentBeanBaseResponseModel.content.is_cancle) {
                                            ToastUtils.showShortToast(context, "取消订单成功");
                                            dataBean.list.remove(position);
                                            notifyDataSetChanged();
                                        } else {
                                            ToastUtils.showShortToast(context, "取消订单失败");
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable throwable) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });


                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();
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


        @BindView(R.id.tv_storeName)
        TextView tv_storeName;
        @BindView(R.id.tv_payState)
        TextView tv_payState;

        public List<TextView> lists = new ArrayList<>();
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            lists.clear();
            int[] ids = {R.id.tv_001, R.id.tv_002, R.id.tv_003};
            for (int i = 0; i < ids.length; i++) {
                textView = (TextView) itemView.findViewById(ids[i]);
                lists.add(textView);
            }
        }
    }
}
