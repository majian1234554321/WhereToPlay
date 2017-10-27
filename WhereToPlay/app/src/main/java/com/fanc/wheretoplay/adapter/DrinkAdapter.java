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
import com.fanc.wheretoplay.databinding.ItemDrinkBinding;
import com.fanc.wheretoplay.databinding.ItemHousetypeBinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/13.
 */

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    private final ArrayList<String> price;
    private final ArrayList<String> typeName;
    Context mContext;

    public DrinkAdapter(Context mContext, ArrayList<String> typeName, ArrayList<String> price) {
        this.mContext = mContext;
        this.typeName = typeName;
        this.price = price;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDrinkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_drink, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvDrinkName.setText(typeName.get(position));
    }

    @Override
    public int getItemCount() {
        if (typeName != null) {
            return typeName.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private  ItemDrinkBinding binding;
        private TextView mTvDrinkName;


        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDrinkBinding) binding;
            initViews();
        }

        private void initViews() {
            mTvDrinkName = binding.tvDrinksName;
        }
    }
}
