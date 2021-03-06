package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailsOrderActivity;
import com.fanc.wheretoplay.activity.DisplayActivity;
import com.fanc.wheretoplay.activity.DownPaymentActivity;
import com.fanc.wheretoplay.activity.EvaluationSuccessActivity;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.PayPayActivity;
import com.fanc.wheretoplay.activity.PublicationEvaluationActivity;
import com.fanc.wheretoplay.datamodel.BookListModel;

import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.image.GlideImageLoader;
import com.fanc.wheretoplay.presenter.DetailsOrderPresenter;
import com.fanc.wheretoplay.rx.BaseResponseModel;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

import static com.fanc.wheretoplay.network.Network.IMAGE;

/**
 * @author admin
 * @date 2017/11/1
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
                intent.putExtra("image", dataBean.list.get(position).cover);
                intent.putExtra("statues", holder.tvPayItemTitle.getText().toString());
                if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
                    intent.putExtra("status", dataBean.list.get(position).order_action);
                }
                intent.putExtra("discount", dataBean.list.get(position).discount);
                if ("6".equals(dataBean.list.get(position).order_function) || "7".equals(dataBean.list.get(position).order_function)) {
                    intent.setClass(context, DisplayActivity.class);
                    intent.putExtra("order_function", dataBean.list.get(position).order_function);
                    intent.putExtra("DISPLAYTYPE", "PackageDetailsFragment");

                } else {
                    intent.setClass(context, DetailsOrderActivity.class);
                }

                fragment.startActivity(intent);


            }
        });


        if ("6".equals(dataBean.list.get(position).order_function) || "7".equals(dataBean.list.get(position).order_function)) {
            holder.tvPayItemTime.setText("套餐名称：");
            if ("6".equals(dataBean.list.get(position).order_function)) {
                holder.tvPayItemRoomCategory.setText("有效期至：");
            } else {
                holder.tvPayItemRoomCategory.setText("预订时间：");
            }

            holder.tvPayItemReserveCode.setText("数量：");
            holder.tvPayItemRealTime.setText(dataBean.list.get(position).package_name);
            holder.tvPayItemDecorateCategory.setVisibility(View.GONE);
            holder.tvPayItemDecorate.setVisibility(View.GONE);

            String times = !TextUtils.isEmpty(DateFormatUtil.stampToDate(dataBean.list.get(position).package_end_time)) ? DateFormatUtil.stampToDate(dataBean.list.get(position).package_end_time) : "";
            holder.tvPayItemRoom.setText(times);
            holder.tvPayItemReserveRealCode.setText(dataBean.list.get(position).package_number);
        } else {
            holder.tvPayItemTime.setText("到店时间：");
            holder.tvPayItemRoomCategory.setText("房型：");
            holder.tvPayItemReserveCode.setText("订单号：");
            String time = !TextUtils.isEmpty(DateFormatUtil.stampToDate(dataBean.list.get(position).arrival_time)) ? DateFormatUtil.stampToDate(dataBean.list.get(position).arrival_time) + "前" : "";
            holder.tvPayItemRealTime.setText(time);
            holder.tvPayItemRoom.setText(dataBean.list.get(position).room_type);
            holder.tvPayItemDecorateCategory.setVisibility(View.VISIBLE);
            holder.tvPayItemDecorate.setVisibility(View.VISIBLE);
            holder.tvPayItemReserveRealCode.setText(dataBean.list.get(position).book_sn);
            holder.tvPayItemDecorate.setText(dataBean.list.get(position).decorate_type);
        }


        holder.tv_storeName.setText(dataBean.list.get(position).name);


        GlideImageLoader.display(context, holder.ivPayItem, IMAGE + dataBean.list.get(position).cover);
        holder.tv_payState.setText(dataBean.list.get(position).statusdesc);


        if (dataBean != null && dataBean.list != null && dataBean.list.get(position) != null && dataBean.list.get(position).buttonlist != null) {
            for (int i = 0; i < dataBean.list.get(position).buttonlist.size(); i++) {

//            if (i + 1 == dataBean.list.get(position).buttonlist.size()) {
//                holder.lists.get(i).setBackgroundResource(R.drawable.shape_btn_black_c4483c);
//                holder.lists.get(i).setTextColor(Color.WHITE);
//            } else {
//                holder.lists.get(i).setBackgroundResource(R.drawable.shape_btn_black_stoke);
//                holder.lists.get(i).setTextColor(Color.parseColor("#333333"));
//            }

                if (dataBean.list.get(position).buttonlist.get(i).title != null) {
                    holder.lists.get(i).setText(dataBean.list.get(position).buttonlist.get(i).title);
                    holder.lists.get(i).setVisibility(View.VISIBLE);
                } else {
                    holder.lists.get(i).setText("");
                    holder.lists.get(i).setVisibility(View.GONE);

                }


                final int finalI = i;
                holder.lists.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                   /* Toast.makeText(context, "title" + dataBean.list.get(position).buttonlist.get(finalI).title + "----->" +
                                    dataBean.list.get(position).buttonlist.get(finalI).buttonid
                            , Toast.LENGTH_SHORT).show();*/

                        Intent intent = new Intent();
                        switch (dataBean.list.get(position).buttonlist.get(finalI).buttonid) {
                            case "0":
                                  cancleOrder(position);
                                break;

                            case "8":

                                intent.putExtra("DISPLAYTYPE", "CancelOrderFragment");
                                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                                intent.putExtra("storeName", dataBean.list.get(position).name);
                                intent.putExtra("image", dataBean.list.get(position).cover);
                                intent.putExtra("book_sn", dataBean.list.get(position).book_sn);
                                intent.putExtra("room_type", dataBean.list.get(position).room_type);
                                intent.putExtra("total", dataBean.list.get(position).total);
                                intent.putExtra("statues", holder.tvPayItemTitle.getText().toString());


                                intent.setClass(context, DisplayActivity.class);
                                fragment.startActivity(intent);

                                break;
                            case "1"://
                                pay(position, holder);
                                break;
                            case "4"://查看
                                intent.putExtra("discount", dataBean.list.get(position).discount);
                                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                                intent.putExtra("store_id", dataBean.list.get(position).store_id);
                                intent.putExtra("storeName", dataBean.list.get(position).name);
                                intent.putExtra("total", dataBean.list.get(position).total);
                                intent.putExtra("address", dataBean.list.get(position).address);
                                intent.putExtra("image", dataBean.list.get(position).cover);
                                intent.putExtra("statues", holder.tvPayItemTitle.getText().toString());
                                if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
                                    intent.putExtra("status", dataBean.list.get(position).order_action);
                                }
                                if ("6".equals(dataBean.list.get(position).order_function) || "7".equals(dataBean.list.get(position).order_function)) {
                                    intent.putExtra("DISPLAYTYPE", "PackageDetailsFragment");

                                    intent.putExtra("order_function", dataBean.list.get(position).order_function);


                                    intent.setClass(context, DisplayActivity.class);
                                } else {
                                    intent.setClass(context, DetailsOrderActivity.class);
                                }

                                fragment.startActivityForResult(intent, 1001);
                                break;
                            case "3"://立即评论

                                intent.putExtra("store_name", dataBean.list.get(position).name);
                                intent.putExtra("address", dataBean.list.get(position).address);
                                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                                intent.putExtra("store_id", dataBean.list.get(position).store_id);
                                intent.putExtra("discount", dataBean.list.get(position).discount);
                                intent.putExtra("address", dataBean.list.get(position).address);

                                intent.putExtra("total", dataBean.list.get(position).total);
                                if (dataBean.list != null && dataBean.list.get(position).order_action != null) {
                                    intent.putExtra("status", dataBean.list.get(position).order_action);
                                }
                                intent.setClass(context, PublicationEvaluationActivity.class);
                                fragment.startActivityForResult(intent, 1001);
                                break;

                            case "2":
                                intent.putExtra("flag", "预订支付");
                                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                                intent.putExtra("store_name", dataBean.list.get(position).name);

                                intent.putExtra("pay_type", "1");
                                intent.putExtra("pay_Action", "转预付");
                                intent.putExtra("address", dataBean.list.get(position).address);
                                intent.putExtra("arrival_time", dataBean.list.get(position).arrival_time);
                                intent.putExtra("prepay", dataBean.list.get(position).book_price);


                                intent.setClass(context, DownPaymentActivity.class);
                                fragment.startActivity(intent);
                                break;

                            case "7":

                                intent.setClass(context, PayBillActivity.class);


                                if ("预订类型：预付预订".equals(holder.tvPayItemTitle.getText().toString().trim())) {


                                    intent.putExtra("money211", dataBean.list.get(position).origin_prepay);
                                    intent.putExtra("money", dataBean.list.get(position).prepay);
                                } else {
                                    intent.putExtra("displayMoney", dataBean.list.get(position).total);
                                }


                                intent.putExtra(Constants.STORE_ID, dataBean.list.get(position).store_id);

                                intent.putExtra("storeName", dataBean.list.get(position).name);

                                intent.putExtra("discount", dataBean.list.get(position).discount);
                                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                                intent.putExtra("address", dataBean.list.get(position).address);
                                intent.putExtra(Constants.PAGE, "支付再支付");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);

                                break;

                        }


                    }
                });


            }

        }


//book_type：string，预订类型 1-订金预订 2-信誉预订 3-充值

        if (dataBean.list != null && dataBean.list.get(position).order_function != null) {
            switch (dataBean.list.get(position).order_function) {
                case "1":
                    holder.tvPayItemTitle.setText("预订类型：预付预订");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).prepay);
                    break;
                case "2":
                    holder.tvPayItemTitle.setText("预订类型：信用预订");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
                    break;
                case "3":
                    holder.tvPayItemTitle.setText("预订类型：充值");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
                case "5":
                    holder.tvPayItemTitle.setText("预订类型：结单支付");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
                    break;

                case "6":
                    holder.tvPayItemTitle.setText("预订类型：套餐");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
                    break;
                case "7":
                    holder.tvPayItemTitle.setText("预订类型：优惠预订");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
                    break;
                default:
                    holder.tvPayItemTitle.setText("预订类型：...");
                    holder.tvPayItemPrice.setText("总价：" + dataBean.list.get(position).total);
                    break;
            }
        }


    }

    private void pay(int position, ViewHolder holder) {
        Intent intent = new Intent();


        switch (holder.tvPayItemTitle.getText().toString().trim()) {
            case "预订类型：预付预订":
                intent.setClass(context, PayBillActivity.class);
                intent.putExtra("pay_Action", "预订类型：预付预订");
                intent.putExtra("money", dataBean.list.get(position).prepay);
                intent.putExtra("money211", dataBean.list.get(position).origin_prepay);
                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                break;
            case "预订类型：结单支付":
                intent.setClass(context, PayBillActivity.class);
                intent.putExtra("pay_Action", "预订类型：结单支付");
                intent.putExtra("money", dataBean.list.get(position).total);
                intent.putExtra("order_id", dataBean.list.get(position).order_id);
                break;
            case "预订类型：套餐":
                intent.setClass(context, PayPayActivity.class);
                intent.putExtra("type", "套餐详情");
                intent.putExtra("storeIdValue", dataBean.list.get(position).store_id);

                intent.putExtra("value0", dataBean.list.get(position).name);
                intent.putExtra("value1", dataBean.list.get(position).name);
                intent.putExtra("value2", dataBean.list.get(position).package_introduce);
                intent.putExtra("value3", DateFormatUtil.stampToDate(dataBean.list.get(position).finish_time));
                intent.putExtra("value4", dataBean.list.get(position).total);
                intent.putExtra("value5", dataBean.list.get(position).origin_price);
                intent.putExtra("order_id", dataBean.list.get(position).order_id);


                intent.putExtra("discountValue", dataBean.list.get(position).discount);
                break;
            default://优惠预订
                intent.setClass(context, PayPayActivity.class);
                intent.putExtra("type", "优惠预订");
                intent.putExtra("storeIdValue", dataBean.list.get(position).store_id);

                intent.putExtra("value0", dataBean.list.get(position).name);
                intent.putExtra("value1", dataBean.list.get(position).name);
                intent.putExtra("value2", dataBean.list.get(position).package_introduce);
                intent.putExtra("value3", DateFormatUtil.stampToDate(dataBean.list.get(position).finish_time));
                intent.putExtra("value4", dataBean.list.get(position).total);
                intent.putExtra("value5", dataBean.list.get(position).origin_price);
                intent.putExtra("order_id", dataBean.list.get(position).order_id);


                intent.putExtra("discountValue", dataBean.list.get(position).discount);
                break;
        }


        intent.putExtra("address", dataBean.list.get(position).address);
        intent.putExtra(Constants.STORE_ID, dataBean.list.get(position).store_id);
        intent.putExtra("storeName", dataBean.list.get(position).name);
        intent.putExtra("displayMoney", dataBean.list.get(position).total);
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
                                            Intent intent = new Intent();
                                            intent.putExtra("Key", "Value");
                                            dataBean.list.remove(position);
                                            notifyDataSetChanged();
                                            //  RxBus.getDefault().post(intent);

                                        } else {
                                            ToastUtils.showShortToast(context, "取消订单失败");
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable throwable) {
                                        ToastUtils.showShortToast(context, throwable.toString());
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
            int[] ids = {R.id.tv_005, R.id.tv_004, R.id.tv_003, R.id.tv_002, R.id.tv_001};
            for (int i = 0; i < ids.length; i++) {
                textView = itemView.findViewById(ids[i]);
                lists.add(textView);
            }
        }
    }
}
