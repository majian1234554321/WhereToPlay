package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemImageBinding;
import com.fanc.wheretoplay.network.Network;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class MerchantDetailImageAdapter extends RecyclerView.Adapter<MerchantDetailImageAdapter.ViewHolder> {

    List mData;
    Context mContext;

    OnItemClickListener listener;

    public MerchantDetailImageAdapter(Context context, List mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.w("llm", "onBindViewHolder: " + (Network.IMAGE + (String) mData.get(position)));
        Glide.with(mContext).load(Network.IMAGE + (String) mData.get(position)).placeholder(R.drawable.default_rect).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
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
        //        ItemImageBinding binding;
        ImageView imageView;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            imageView = ((ItemImageBinding) itemView).ivItem;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
