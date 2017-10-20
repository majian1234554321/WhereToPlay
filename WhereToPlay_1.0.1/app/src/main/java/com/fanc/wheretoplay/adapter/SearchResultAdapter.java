package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemSearchResultBinding;
import com.fanc.wheretoplay.datamodel.Search;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class SearchResultAdapter extends BaseAdapter {
    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public SearchResultAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            ItemSearchResultBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.item_search_result, parent, false);
            holder = new ViewHolder(binding);
            convertView = binding.getRoot();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.binding.setName(((Search.Store) getItem(position)).name);
        return convertView;
    }

    static class ViewHolder {
        ItemSearchResultBinding binding;
        TextView mTvSearchResult;

        public ViewHolder(ItemSearchResultBinding binding) {
            this.binding = binding;
        }
    }


}
