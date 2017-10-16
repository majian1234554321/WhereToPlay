package com.fanc.wheretoplay.adapter;

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

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.activity.DiscoverImageActivity;
import com.fanc.wheretoplay.databinding.ItemDiscoverBinding;
import com.fanc.wheretoplay.datamodel.Advertising;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {
    Context mContext;
    List mData;

    public DiscoverAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDiscoverBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_discover, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Advertising.Shop shop = (Advertising.Shop) mData.get(position);
        Glide.with(mContext).load(Network.IMAGE + shop.getPicture()).placeholder(R.drawable.default_rect).into(holder.mIvDiscover);
        holder.mTvTitle.setText(shop.getTitle());
        holder.mTvContent.setText(shop.getContent());
        holder.mIvDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DiscoverImageActivity.class);
                intent.putExtra(Constants.URL, shop.getPicture());
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.PAGE, Constants.MERCHANT_DETAIL);
                intent.putExtra(Constants.STORE_ID, shop.getStore_id());
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

        ItemDiscoverBinding binding;
        ImageView mIvDiscover;
        //        Button mBtnDiscoverDetail;
//        Button mBtnDiscoverReserve;
        TextView mTvTitle;
        TextView mTvContent;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDiscoverBinding) binding;
            initViews();
        }

        private void initViews() {
            mIvDiscover = binding.ivDiscoverItem;
//            mBtnDiscoverDetail = binding.btnDiscoverItemDetail;
//            mBtnDiscoverReserve = binding.btnDiscoverItemReserve;
            mTvContent = binding.tvDiscoverContent;
            mTvTitle = binding.tvDiscoverTitle;
        }
    }
}
