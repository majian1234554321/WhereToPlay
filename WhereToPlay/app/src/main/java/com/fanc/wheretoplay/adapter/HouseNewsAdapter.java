package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.databinding.ItemHousenewsBinding;
import com.fanc.wheretoplay.datamodel.HousenewsList;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseNewsAdapter extends RecyclerView.Adapter<HouseNewsAdapter.ViewHolder> {

    private final List<HousenewsList.StatusBean> housenews;
    Context mContext;
    public String mStoreId;
    public boolean open;
    public String date;

    public HouseNewsAdapter(Activity mContext, List<HousenewsList.StatusBean> housenews, String mStoreId, boolean open, String date) {
        this.mContext = mContext;
        this.housenews = housenews;
        this.mStoreId = mStoreId;
        this.open = open;
        this.date = date;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHousenewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_housenews, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTvType.setText(housenews.get(position).getName());
        holder.mTvHouseName.setText(housenews.get(position).getNumber());
        holder.mTvLowest.setText(housenews.get(position).getMin_price());
        switch (Integer.parseInt(housenews.get(position).getStatus())) {
            case 1:
                holder.mIv.setImageResource(R.drawable.merchant_housenews_free_big);
                break;
            case 2:
                holder.mIv.setImageResource(R.drawable.merchant_housenews_book_big);
                break;
            case 3:
                holder.mIv.setImageResource(R.drawable.merchant_housenews_use_big);
                break;
            default:
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HousenewsList.StatusBean statusBean = new HousenewsList.StatusBean(housenews.get(position).getName(),
                        housenews.get(position).getNumber(),
                        housenews.get(position).getMin_price(),
                        housenews.get(position).getStatus(),
                        housenews.get(position).room_id,
                        date);
                if ("1".equals(housenews.get(position).getStatus())) {
                    if (open) {
                        Intent intent = new Intent(mContext, ReuseActivity.class);
                        intent.putExtra(Constants.STORE_ID, mStoreId);

                        intent.putExtra("number", housenews.get(position).getNumber());
                        intent.putExtra("name", housenews.get(position).getName());
                        intent.putExtra("price", housenews.get(position).getMin_price());
                        intent.putExtra("status", housenews.get(position).getStatus());
                        intent.putExtra("room_id", housenews.get(position).room_id);
                        intent.putExtra("date", date);
                        intent.putExtra(Constants.PAGE, Constants.RESERVE_INFO);

                        mContext.startActivity(intent);

                    } else {
                        RxBus.getDefault().post(statusBean);
                        ((Activity) mContext).finish();
                    }
                } else {
                    Toast.makeText(mContext, "该房间已被预订或者正在使用", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (housenews == null) {
            return 0;
        }
        return housenews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemHousenewsBinding binding;
        private ImageView mIvHouseUseDetail;
        private ImageView mIv;
        private TextView mTvHouseName;
        private TextView mTvLowest;
        private TextView mTvType;


        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemHousenewsBinding) binding;
            initViews();
        }

        private void initViews() {
            mIv = binding.ivHousenewsUseDetail;
            mTvHouseName = binding.tvHousenewsHousename;
            mTvLowest = binding.tvHousenewsLowest;
            mTvType = binding.tvHousenewsType;
        }
    }
}
