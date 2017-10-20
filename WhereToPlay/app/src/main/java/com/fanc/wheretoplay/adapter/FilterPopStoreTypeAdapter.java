package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemPopFilterBinding;
import com.fanc.wheretoplay.datamodel.Filter;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class FilterPopStoreTypeAdapter extends BaseAdapter {

    OnItemClickListener listener;
    // 上次点击的下标
    int pre;
    HashMap<Integer, TextView> textViewHashMap;

    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public FilterPopStoreTypeAdapter(Context context, List data) {
        super(context, data);
        getData().add("全部");
        textViewHashMap = new HashMap<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            ItemPopFilterBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.item_pop_filter, parent, false);
            convertView = binding.getRoot();
            convertView.setBackgroundColor(Color.WHITE);
            holder = new ViewHolder();
            holder.mTvFilter = binding.tvItemPopFilter;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            holder.mTvFilter.setText((String) getItem(position));
            holder.mTvFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        // 店家类型：style
                        listener.onItemClick((String) getItem(position), null, position);
                    }
                    cleanStatus();
                }
            });
        } else {
            final Filter.FilterBean filterBean = (Filter.FilterBean) getData().get(position);
            holder.mTvFilter.setText(filterBean.getName());
            final ViewHolder finalHolder = holder;
            holder.mTvFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        // 店家类型：style
                        listener.onItemClick(filterBean.getName(), filterBean.getId(), position);
                    }
                    if (textViewHashMap != null) {
                        textViewHashMap.put(position, finalHolder.mTvFilter);
                    }
                    setClickTextColor(position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        TextView mTvFilter;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String name, String typeId, int position);
    }

    /**
     * 点击之后的文字颜色变化
     *
     * @param position
     */
    private void setClickTextColor(int position) {
        TextView preTextView = textViewHashMap.get(pre);
        if (preTextView != null) {
            preTextView.setTextColor(UIUtils.getColor(R.color.text_black));
            preTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        TextView clickTextView = textViewHashMap.get(position);
        if (clickTextView != null) {
            clickTextView.setTextColor(UIUtils.getColor(R.color.text_and_buttor_orange));
            clickTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_checked, 0);
        }
        this.pre = position;
    }

    /**
     * 清除点击的状态
     */
    public void cleanStatus() {
        if (textViewHashMap != null) {
            TextView preTextView = textViewHashMap.get(pre);
            if (preTextView != null) {
                preTextView.setTextColor(UIUtils.getColor(R.color.text_black));
                preTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            textViewHashMap.clear();
        }
    }

}
