package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemDrinkBinding;
import com.fanc.wheretoplay.datamodel.Drinks;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    List<Drinks.ListBean> mHouseNews;
    Context mContext;

    public DrinkAdapter(Context mContext,List<Drinks.ListBean> housenews) {
        this.mContext = mContext;
        this.mHouseNews = housenews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDrinkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_drink, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        holder.mTvDrinkPrice.setText(stringBuilder.append(mContext.getResources().getString(R.string.drinks_price_1)).append(mHouseNews.get(position).getPrice()).append( mContext.getResources().getString(R.string.drinks_price_2)));
        holder.mTvDrinkName.setText(mHouseNews.get(position).getName());
        holder.mTvDrinkAction.setText(mHouseNews.get(position).getActivity());
    }

    @Override
    public int getItemCount() {
        if (mHouseNews != null) {
            return mHouseNews.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private  ItemDrinkBinding binding;
        private TextView mTvDrinkName;
        private TextView mTvDrinkPrice;
        private TextView mTvDrinkAction;


        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDrinkBinding) binding;
            initViews();
        }

        private void initViews() {
            mTvDrinkName = binding.tvDrinksName;
            mTvDrinkPrice = binding.tvDrinksPrice;
            mTvDrinkAction = binding.tvDrinksAction;
        }
    }
}
