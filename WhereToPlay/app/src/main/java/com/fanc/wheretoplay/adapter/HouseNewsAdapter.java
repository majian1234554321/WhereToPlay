package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemHousenewsBinding;
import com.fanc.wheretoplay.databinding.ItemHousetypeBinding;
import com.fanc.wheretoplay.datamodel.HousenewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseNewsAdapter extends RecyclerView.Adapter<HouseNewsAdapter.ViewHolder> {

    private final List<HousenewsBean.ContentBean.StatusBean> housenews;
    Context mContext;

    public HouseNewsAdapter(Activity mContext, List<HousenewsBean.ContentBean.StatusBean> housenews) {
        this.mContext = mContext;
        this.housenews = housenews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHousenewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_housenews, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
