package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemHousenewsBinding;
import com.fanc.wheretoplay.databinding.ItemHousetypeBinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseNewsAdapter extends RecyclerView.Adapter<HouseNewsAdapter.ViewHolder> {

    private final ArrayList<String> price;
    private final ArrayList<String> typeName;
    Context mContext;

    public HouseNewsAdapter(Context mContext, ArrayList<String> typeName, ArrayList<String> price) {
        this.mContext = mContext;
        this.typeName = typeName;
        this.price = price;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHousenewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_housenews, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 9;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private  ItemHousenewsBinding binding;
        private ImageView mIvHouseUseDetail;


        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemHousenewsBinding) binding;
            initViews();
        }

        private void initViews() {
           mIvHouseUseDetail = binding.ivHousenewsUseDetail;
        }
    }
}
