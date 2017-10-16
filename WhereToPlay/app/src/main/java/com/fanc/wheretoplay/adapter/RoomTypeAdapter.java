package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.databinding.ItemRoomTypeBinding;
import com.fanc.wheretoplay.datamodel.RoomCategory;
import com.fanc.wheretoplay.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class RoomTypeAdapter extends RecyclerView.Adapter<RoomTypeAdapter.ViewHolder> {

    Context mContext;
    List mData;

    boolean isSelect;

    public RoomTypeAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_room_type, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RoomCategory.Room room = (RoomCategory.Room) mData.get(position);
        holder.binding.setRoom(room);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelect) {
                    sendBroadcastBySelectedRoom(room.name, room.id,room.min_price);
                    ((ReuseActivity) mContext).finish();
                }
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
        ItemRoomTypeBinding binding;

//        TextView mTvItemRoomName;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemRoomTypeBinding) binding;
//            mTvItemRoomName = this.binding.tvItemRoomName;
        }
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    /**
     * 选中room后发送广播
     *
     * @param roomName
     * @param roomId
     */
    private void sendBroadcastBySelectedRoom(String roomName, String roomId,String money) {
        Intent intent = new Intent(Constants.ACTION_SELECT_ROOM);
        intent.putExtra(Constants.ROOM, roomId);
        intent.putExtra(Constants.NAME, roomName);
        intent.putExtra(Constants.MONEY,money);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }
}
