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

public class FilterPopChildAdapter extends BaseAdapter {

    OnItemClickListener listener;
    // 上次点击的下标
    int pre;
    HashMap<Integer, TextView> textViewHashMap;
    // 是优惠折扣
    boolean isDiscount;

    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public FilterPopChildAdapter(Context context, List data) {
        super(context, data);
        textViewHashMap = new HashMap<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            ItemPopFilterBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.item_pop_filter, parent, false);
            convertView = binding.getRoot();
            convertView.setBackgroundColor(Color.parseColor("#BBBBBB"));
            holder = new ViewHolder();
            holder.mTvFilter = binding.tvItemPopFilter;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Filter.FilterBean filterBean = (Filter.FilterBean) getData().get(position);
        if (isDiscount) {
            holder.mTvFilter.setText(filterBean.getName() + "折");
        } else {
            holder.mTvFilter.setText(filterBean.getName());

        }
        final ViewHolder finalHolder = holder;
        holder.mTvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (isDiscount) {
                        listener.onItemClick(filterBean.getName() + "折", filterBean.getId(), position);
                    } else {
                        listener.onItemClick(filterBean.getName(), filterBean.getId(), position);
                    }
                }
                if (textViewHashMap != null) {
                    textViewHashMap.put(position, finalHolder.mTvFilter);
                }
                setClickTextColor(position);
            }
        });
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
        }

        TextView clickTextView = textViewHashMap.get(position);
        if (clickTextView != null) {
            clickTextView.setTextColor(UIUtils.getColor(R.color.text_and_buttor_orange));
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
//                preTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            textViewHashMap.clear();
        }
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }
}
