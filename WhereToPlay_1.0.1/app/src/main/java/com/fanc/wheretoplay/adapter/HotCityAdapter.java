package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemCityHotGridviewBinding;
import com.fanc.wheretoplay.datamodel.CityResource;

import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class HotCityAdapter extends BaseAdapter {
    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public HotCityAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            ItemCityHotGridviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                    R.layout.item_city_hot_gridview, parent, false);
            convertView = binding.getRoot();
            holder = new ViewHolder(binding);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CityResource.City city = (CityResource.City) getItem(position);
        holder.binding.setCity(city);
        return convertView;
    }

    static class ViewHolder {
        ItemCityHotGridviewBinding binding;

//        TextView mTvCityName;

        public ViewHolder(ViewDataBinding binding) {
            this.binding = (ItemCityHotGridviewBinding) binding;
//            mTvCityName = this.binding.tvHotCityName;
        }
    }
}
