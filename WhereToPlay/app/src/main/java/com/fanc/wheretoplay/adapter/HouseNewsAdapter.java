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
import com.fanc.wheretoplay.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseNewsAdapter extends RecyclerView.Adapter<HouseNewsAdapter.ViewHolder> {

    private final List<HousenewsList.StatusBean> housenews;
    Context mContext;
    public String mStoreId;

    public HouseNewsAdapter(Activity mContext, List<HousenewsList.StatusBean> housenews, String mStoreId) {
        this.mContext = mContext;
        this.housenews = housenews;
        this.mStoreId = mStoreId;
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
                if ("1".equals(housenews.get(position).getStatus())){
                    Toast.makeText(mContext, "可以被预订", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, ReuseActivity.class);
                    intent.putExtra(Constants.STORE_ID, mStoreId);
                    intent.putExtra(Constants.PAGE, Constants.RESERVE_INFO);
                    mContext. startActivity(intent);
                }else {
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
