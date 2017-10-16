package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemDialogMapBinding;
import com.fanc.wheretoplay.util.NaviUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public class MapDialogAdapter extends RecyclerView.Adapter {

    Context mContext;
    List mData;

    OnItemClickListener listener;

    public MapDialogAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_dialog_map, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String name = null;
        final String mapName = (String) mData.get(position);
        switch (mapName) {
            case NaviUtils.PACKAGE_NAME_GAODE_MAP:
                name = "高德地图";
                break;
            case NaviUtils.PACKAGE_NAME_TENCENT_MAP:
                name = "腾讯地图";
                break;
            case NaviUtils.PACKAGE_NAME_BAIDU_MAP:
            default:
                name = "百度地图";
                break;
        }

        ((ViewHolder) holder).binding.setMapName(name);
        ((ViewHolder) holder).binding.tvMapName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(mapName);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDialogMapBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDialogMapBinding) binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String mapName);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
