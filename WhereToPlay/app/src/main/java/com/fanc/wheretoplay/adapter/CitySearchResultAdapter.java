package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemCitySearchResultBinding;
import com.fanc.wheretoplay.datamodel.CityResource;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class CitySearchResultAdapter extends BaseAdapter {
    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public CitySearchResultAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            ItemCitySearchResultBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.item_city_search_result, parent, false);
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
        ItemCitySearchResultBinding binding;

        public ViewHolder(ItemCitySearchResultBinding binding) {
            this.binding = binding;
        }
    }
}
