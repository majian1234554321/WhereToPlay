package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemHousetypeBinding;
import com.fanc.wheretoplay.datamodel.HouseTypeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseTypeAdapter extends RecyclerView.Adapter<HouseTypeAdapter.ViewHolder> {

    List<HouseTypeList.RoomBean> mRoom;
    Context mContext;

    public HouseTypeAdapter(Activity mContext, List<HouseTypeList.RoomBean> room) {
        this.mContext = mContext;
        this.mRoom = room;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHousetypeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_housetype, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
//                holder.mIv.setImageResource(R.drawable.item_baoxiang);
//                break;
            case 1:
//                holder.mIv.setImageResource(R.drawable.item_baoxiang);
//                break;
            case 2:
                holder.mIv.setImageResource(R.drawable.item_baoxiang);
                break;
            case 3:
                holder.mIv.setImageResource(R.drawable.item_kazuo);
                break;
            case 4:
                holder.mIv.setImageResource(R.drawable.item_santai);
                break;
            default:
                break;
        }
            holder.mTvName.setText(mRoom.get(position).getName());
            holder.mTvPrice.setText(mRoom.get(position).getMin_price());
    }

    @Override
    public int getItemCount() {
        if (mRoom != null) {
            return mRoom.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private  ItemHousetypeBinding binding;
        private ImageView mIv;
        private TextView mTvName;
        private TextView mTvPrice;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemHousetypeBinding) binding;
            initViews();
        }

        private void initViews() {
            mIv = binding.ivHousetype;
            mTvName = binding.tvHousetypeName;
            mTvPrice = binding.tvHousetypePrice;
        }
    }
}
