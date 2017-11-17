package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemPopFilterBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.datamodel.Filter;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class FilterPopDialogAdapter extends BaseAdapter {

    int bg;// item 背景

    OnItemClickListener listener;

    boolean isFilter;
    int pre;
    HashMap<Integer, TextView> textViewHashMap;

    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public FilterPopDialogAdapter(Context context, List data) {
        super(context, data);
        getData().add(0, "全部");// 第一个数据，全部
        textViewHashMap = new HashMap<>();
    }

    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public FilterPopDialogAdapter(Context context, List data, boolean isAll) {
        super(context, data);
        if (!isAll) {
            getData().add(0, "不限");// 第一个数据，全部
        }
        textViewHashMap = new HashMap<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            ItemPopFilterBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.item_pop_filter, parent, false);
            convertView = binding.getRoot();
            convertView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            holder = new ViewHolder();
            holder.mLlFilterBg = binding.llItemPopFilter;
            if (bg != 0) {
                holder.mLlFilterBg.setBackgroundResource(bg);
            }
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
                        listener.onItemClick((String) getItem(position), null, position);
                    }
                    cleanStatus();
                }
            });

        } else {
            Object object = getData().get(position);
            if (object instanceof CityResource.Area) {
                final CityResource.Area area = (CityResource.Area) object;
                holder.mTvFilter.setText(area.getName());

                final ViewHolder finalHolder1 = holder;
                holder.mTvFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onItemClick(area.getName(), area.getId(), position);
                        }
                        if (textViewHashMap != null) {
                            textViewHashMap.put(position, finalHolder1.mTvFilter);
                        }
                        setClickTextColor(position);
                    }
                });
            }
            if (object instanceof Filter.FilterBean) {
                final Filter.FilterBean filterBean = (Filter.FilterBean) object;
                holder.mTvFilter.setText(filterBean.getName());

                final ViewHolder finalHolder2 = holder;
                holder.mTvFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onItemClick(filterBean.getName(),filterBean.getId(), position);
                        }
                        if (textViewHashMap != null) {
                            textViewHashMap.put(position, finalHolder2.mTvFilter);
                        }
                        setClickTextColor(position);
                    }
                });
            }
            if (object instanceof String) {
                final String category = (String) object;
                holder.mTvFilter.setText(category);

                final ViewHolder finalHolder = holder;

                holder.mTvFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (textViewHashMap != null) {
                            textViewHashMap.put(position, finalHolder.mTvFilter);
                        }
//                        if (isFilter) {
                        setClickTextColor(position);
//                        }
                        if (listener != null) {
                            listener.onItemClick(category, null, position);
                        }

                    }
                });
            }
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout mLlFilterBg;
        TextView mTvFilter;
    }

    public void setItemBg(int bg) {
        this.bg = bg;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String name, String dataID, int position);
    }

    /**
     * 是否是筛选
     */
    public void setFilter(boolean filter) {
        isFilter = filter;
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
            if (isFilter) {
                preTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
        Log.d("Aaaaa", "onClick:pre = " + pre + "\t preTextView = " + preTextView);

        TextView clickTextView = textViewHashMap.get(position);
        if (clickTextView != null) {
            clickTextView.setTextColor(UIUtils.getColor(R.color.text_red));
            if (isFilter) {
                clickTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_checked, 0);
            }
        }
        Log.w("Aaaaa", "onClick:position= " + position + "\t  clickTextView = " + clickTextView);
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

    /**
     * 是否点击过
     *
     * @return
     */
    public boolean getIsClicked() {
        if (textViewHashMap != null) {
            if (textViewHashMap.size() != 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
