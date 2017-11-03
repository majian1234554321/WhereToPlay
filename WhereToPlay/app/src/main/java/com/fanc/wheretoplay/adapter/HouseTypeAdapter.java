package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.databinding.ItemHousetypeBinding;
import com.fanc.wheretoplay.datamodel.HouseTypeList;
import com.fanc.wheretoplay.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseTypeAdapter extends RecyclerView.Adapter<HouseTypeAdapter.ViewHolder> {

    List<HouseTypeList.RoomBean> mRoom;
    Context mContext;
    private boolean isSelect;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        switch (mRoom.get(position).getName()) {
            case "小包":
            case "中包":
            case "大包":
                holder.mIv.setImageResource(R.drawable.item_baoxiang);
                break;
            case "VIP包":
                holder.mIv.setImageResource(R.drawable.item_santai);
                break;
            case "卡座":
                holder.mIv.setImageResource(R.drawable.item_kazuo);
                break;
            default:
                break;
        }

            holder.mTvName.setText(String.valueOf(mRoom.get(position).getName()+ mContext.getResources().getString(R.string.housetype_symbol) + mRoom.get(position).getMans()+mContext.getResources().getString(R.string.housetype_symbol2)));
            holder.mTvPrice.setText(mRoom.get(position).getMin_price());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isSelect) {
                        sendBroadcastBySelectedRoom(mRoom.get(position).getName(), mRoom.get(position).getId(),mRoom.get(position).getMin_price());
                        ((ReuseActivity) mContext).finish();
                    }
                }
            });
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
    public void setSelect(boolean select) {
        isSelect = select;
    }

    private void sendBroadcastBySelectedRoom(String name, String id, String min_price) {
        Intent intent = new Intent(Constants.ACTION_SELECT_ROOM);
        intent.putExtra(Constants.ROOM, id);
        intent.putExtra(Constants.NAME, name);
        intent.putExtra(Constants.MONEY,min_price);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

}
