package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.RoomTypeInDialogAdapter;
import com.fanc.wheretoplay.databinding.DialogRoomTypeBinding;
import com.fanc.wheretoplay.datamodel.RoomList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class RoomTypeDialog {

    DialogRoomTypeBinding binding;

    Context mContext;
    Dialog mDialog;

    List<RoomList.Room> rooms;
    RecyclerView mRvRoomType;

    OnRoomSelectedListener listener;

    public RoomTypeDialog(Context context) {
        mContext = context;
        mDialog = new Dialog(mContext, R.style.DialogStyle);
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_room_type, null, false);
        mRvRoomType = binding.rvRoomType;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.8),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(binding.getRoot(), layoutParams);

        binding.setClick(this);
    }

    public RoomTypeDialog setContent(List<RoomList.Room> rooms) {
        this.rooms = rooms;
        if (this.rooms != null) {
            showContent(this.rooms);
        }
        return this;
    }

    private void showContent(List<RoomList.Room> rooms) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvRoomType.setLayoutManager(layoutManager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL);
        mRvRoomType.addItemDecoration(divider);
        RoomTypeInDialogAdapter adapter = new RoomTypeInDialogAdapter(mContext, rooms);
        mRvRoomType.setAdapter(adapter);
        mRvRoomType.setHasFixedSize(true);
        adapter.setOnItemClickListener(new RoomTypeInDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String roomName, String roomId) {
                if (listener != null) {
                    listener.onRoomSelected(roomName, roomId);
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            dismiss();
                        }
                    }.sendEmptyMessageDelayed(0, 200);
                }
            }
        });
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public RoomTypeDialog show() {
        mDialog.show();
        return this;
    }

    public RoomTypeDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }


    public RoomTypeDialog setOnRoomSelectedListener(OnRoomSelectedListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnRoomSelectedListener {
        void onRoomSelected(String roomName, String roomId);
    }
    public boolean isShowing(){
        return mDialog.isShowing();
    }
}
