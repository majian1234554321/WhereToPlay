package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemRoomTypeDialogBinding;
import com.fanc.wheretoplay.datamodel.RoomList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class RoomTypeInDialogAdapter extends RecyclerView.Adapter<RoomTypeInDialogAdapter.ViewHolder> {

    Context mContext;
    List mData;

    OnItemClickListener listener;

    HashMap<Integer, Boolean> status;
    int pre;

    public RoomTypeInDialogAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        status = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_room_type_dialog, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RoomList.Room room = (RoomList.Room) mData.get(position);
        holder.binding.setRoom(room);
        holder.mRItemStatus.setChecked(getItemStatus(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanItemStatus(pre);
                setItemStatus(position);
                if (listener != null) {
                    listener.onItemClick(room.getName(), room.getId());
                }
            }
        });
        holder.mRItemStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanItemStatus(pre);
                setItemStatus(position);
                if (listener != null) {
                    listener.onItemClick(room.getName(), room.getId());
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

    private void setItemStatus(int position) {
        status.put(position, true);
        notifyItemChanged(position);
        pre = position;
    }

    private boolean getItemStatus(int position) {
        if (status.get(position) != null && status.get(position)) {
            return true;
        }
        return false;
    }

    private void cleanItemStatus(int position) {
        status.remove(position);
        notifyItemChanged(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRoomTypeDialogBinding binding;

        RadioButton mRItemStatus;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemRoomTypeDialogBinding) binding;
            mRItemStatus = this.binding.rbItemDialogRoom;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String roomName, String roomId);
    }

}
