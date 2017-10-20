package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.MapDialogAdapter;
import com.fanc.wheretoplay.databinding.DialogMapBinding;

/**
 * Created by Administrator on 2017/9/2.
 */

public class MapDialog {
    private Context mContext;
    private Dialog mDialog;

    private RecyclerView mRvDialogMap;

    public MapDialog(Context context) {
        this.mContext = context;
        mDialog = new Dialog(mContext, R.style.DialogStyle);
        initViewContent();
    }

    private void initViewContent() {
        DialogMapBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_map, null, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mContext.getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(binding.getRoot(), layoutParams);
        mDialog.getWindow().setGravity(Gravity.BOTTOM);

        mRvDialogMap = binding.rvDialogMap;
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvDialogMap.setLayoutManager(layoutManager);
    }

    public MapDialog show() {
        mDialog.show();
        return this;
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public MapDialog setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        return this;
    }

    public MapDialog setAdapter(RecyclerView.Adapter adapter) {
        mRvDialogMap.setAdapter(adapter);
        return this;
    }

    public MapDialog setAdapter(MapDialogAdapter adapter, MapDialogAdapter.OnItemClickListener listener) {
        mRvDialogMap.setAdapter(adapter);
        adapter.setOnItemClickListener(listener);
        return this;
    }
}
