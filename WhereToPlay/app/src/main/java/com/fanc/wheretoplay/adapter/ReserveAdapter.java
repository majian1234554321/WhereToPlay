package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.databinding.ItemReserveBinding;
import com.fanc.wheretoplay.datamodel.StoreList;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by Administrator on 2017/6/13.
 */

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ViewHolder> {

    List mData;
    Context mContext;
    public String type;

    int lastPosition;

    public ReserveAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public ReserveAdapter(Context mContext, List mData,String type) {
        this.mContext = mContext;
        this.mData = mData;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemReserveBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_reserve, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // startAnimation(holder.itemView, position);
//        holder.binding.setClick(this);
        final StoreList.Store store = (StoreList.Store) mData.get(position);
        holder.binding.setStore(store);
        // 图片
        Glide.with(mContext).load( store.getCover()).placeholder(R.drawable.default_rect).into(holder.mIvReserveItem);
        // 折扣
        Log.e("what","1\n" + store.getDiscount());
        if (store.getDiscount().length() > 0) {
            SpannableString text = new SpannableString(store.getDiscount() + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mTvReserveItemDiscountSum.setText(text, TextView.BufferType.SPANNABLE);
            holder.mTvReserveItemDiscountSum.setVisibility(View.VISIBLE);
        } else {
            holder.mTvReserveItemDiscountSum.setVisibility(View.GONE);
        }
        // 地址 距离
        if (store.getDistance() != null && !TextUtils.isEmpty(store.getDistance())) {

            double distance = Double.parseDouble(store.getDistance());
            String d = "";
            if (distance < 500) {
                d = "<500m";
            } else
//                if (distance < 100000)
            {
                if (distance < 1000) {
//                    d = String.valueOf(distance / 1000);
//                    d = d.substring(0, 3) + "km";
                    d = store.getDistance() + "m";
                } else {
                    // 0.几的时候，格式化会把小数点前的0去掉，原因未知
                    DecimalFormat df = new DecimalFormat("#.0");
                    d = df.format(distance / 1000) + "km";
                }
//            } else {
//                d = ">99km";
            }
            holder.mTvReserveItemDistance.setText(d);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.PAGE, Constants.MERCHANT_DETAIL);
                intent.putExtra(Constants.STORE_ID, store.getId());
                intent.putExtra("type",type);

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(mContext, R.anim.anim_enter_bottom, R.anim.anim_out_top_right);

                ActivityCompat.startActivity(mContext, intent, compat.toBundle());
            }
        });

    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    private void startAnimation(View view, int position) {
        if (position != lastPosition) {
            AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(mContext, R.anim.anim_item_bottom_in);
//            animationSet.setInterpolator(new BounceInterpolator());
            animationSet.setStartTime(20);
//            animationSet.setStartOffset(20);
            view.startAnimation(animationSet);
            lastPosition = position;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemReserveBinding binding;

        ImageView mIvReserveItem;
        TextView mTvReserveItemAddress;
        TextView mTvReserveItemDiscountSum;
        TextView mTvReserveItemPrice;
        TextView mTvReserveItemDistance;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemReserveBinding) binding;
            initViews();
        }

        private void initViews() {
            mIvReserveItem = binding.ivReserveItem;
            mTvReserveItemAddress = binding.tvReserveItemAddress;
            mTvReserveItemDiscountSum = binding.tvReserveItemDiscountSum;
            mTvReserveItemPrice = binding.tvReserveItemPrice;
            mTvReserveItemDistance = binding.tvReserveItemDistance;
        }
    }
}
