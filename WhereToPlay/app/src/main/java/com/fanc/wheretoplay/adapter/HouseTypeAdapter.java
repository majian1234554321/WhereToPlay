package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemHousetypeBinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HouseTypeAdapter extends RecyclerView.Adapter<HouseTypeAdapter.ViewHolder> {

    private final ArrayList<String> price;
    private final ArrayList<String> typeName;
    Context mContext;

    public HouseTypeAdapter(Context mContext, ArrayList<String> typeName, ArrayList<String> price) {
        this.mContext = mContext;
        this.typeName = typeName;
        this.price = price;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHousetypeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_housetype, parent, false);
        ViewHolder holder = new ViewHolder(binding);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_housetype, parent, false);
//        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
//                holder.mIv.setImageResource(R.drawable.item_baoxiang);
//                break;
            case 1:
//                holder.mIv.setImageResource(R.drawable.item_baoxiang);
//                break;
            case 2:
                holder.mIv.setImageResource(R.drawable.item_baoxiang);
                break;
            case 3:
                holder.mIv.setImageResource(R.drawable.item_kazuo);
                break;
            case 4:
                holder.mIv.setImageResource(R.drawable.item_santai);
                break;
            default:
                break;
        }
            holder.mTvName.setText(typeName.get(position));
            holder.mTvPrice.setText(price.get(position));
    }

    @Override
    public int getItemCount() {
        if (typeName != null) {
            return typeName.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private  View view;
        private  ItemHousetypeBinding binding;
        private ImageView mIv;
        private TextView mTvName;
        private TextView mTvPrice;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemHousetypeBinding) binding;
            initViews();
        }

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        private void initViews() {
//            mIv = (ImageView)view.findViewById(R.id.iv_housetype);
//            mTvPrice = (TextView)view.findViewById(R.id.tv_housetype_price);
//            mTvName = (TextView)view.findViewById(R.id.tv_housetype_name);
            mIv = binding.ivHousetype;
            mTvName = binding.tvHousetypeName;
            mTvPrice = binding.tvHousetypePrice;
        }
    }
}
