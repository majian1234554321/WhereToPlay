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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.activity.ServiceActivity;
import com.fanc.wheretoplay.databinding.ItemReserveBinding;
import com.fanc.wheretoplay.datamodel.StoreList;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseTypeAdapter extends RecyclerView.Adapter<HouseTypeAdapter.ViewHolder> {

    List mData;
    Context mContext;

    int lastPosition;

    public HouseTypeAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemReserveBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_reserve, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            initViews();
        }

        private void initViews() {

        }
    }
}
