package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemDateTimeBinding;
import com.fanc.wheretoplay.util.DateFormatUtil;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/9/15.
 */

public class DateTimeAdapter extends BaseAdapter {
    // 选中状态
    HashMap<Integer, Boolean> status;
    // 保存是否可用状态
    public TreeMap<Integer, Boolean> statusAble;
    // 被选中的position
    int selectedPosition;
    // 点击监听
    public OnItemClickListener listener;
    // 最晚预定时间
    String lastTime1;
    // 时间边界，预留时间
    long earliest, last, lastTime;
    // 当前时间,HH:mm
    long current;
    // 今天的日期
    int currentDay;
    // 选中的日期
    int day;
    boolean flag;

    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public DateTimeAdapter(Context context, List data, boolean flag) {
        super(context, data);
        status = new HashMap<>();
//        status.put(0, true);
        statusAble = new TreeMap<>();
        String currentTime = DateFormatUtil.getYYYYMMDDHHmm(String.valueOf(System.currentTimeMillis() / 1000));
        current = DateFormatUtil.parseStringTolong(currentTime.substring(11), DateFormatUtil.HHmm);
        day = currentDay = DateFormatUtil.getCurrentDay();
        this.flag = flag;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            ItemDateTimeBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.item_date_time, parent, false);
            holder = new ViewHolder(binding);
            convertView = binding.getRoot();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String time = (String) getItem(position);
        holder.binding.setTime(time);
        // 超过最晚预定时间则item不可用


        long current = DateFormatUtil.parseStringTolong(time, DateFormatUtil.HHmm);

        if (flag) {


            if (lastTime ==0){
                if (!DateFormatUtil.time1totime2(time)) {
                    holder.mCbTime.setEnabled(true);
                }else {
                    holder.mCbTime.setEnabled(false);
                }
            }else  {
                if (current <= lastTime&&!DateFormatUtil.time1totime2(time)){
                    holder.mCbTime.setEnabled(true);
                }else {
                    holder.mCbTime.setEnabled(false);
                }
            }


        } else {
            holder.mCbTime.setEnabled(true);
        }


        statusAble.put(position, holder.mCbTime.isEnabled());

        holder.mCbTime.setChecked(getItemStatus(position));
        holder.mCbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemStatus(position);
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        return convertView;
    }

    /**
     * 设置是否选中的状态
     *
     * @param position
     */
    public void setItemStatus(int position) {
        cleanItemStatus(selectedPosition);
        status.put(position, true);
        selectedPosition = position;

        notifyDataSetChanged();
    }

    /**
     * 清楚选中状态
     *
     * @param position
     */
    private void cleanItemStatus(int position) {
        status.remove(position);
    }

    /**
     * 获取position是选中状态
     *
     * @param position
     * @return
     */
    private boolean getItemStatus(int position) {
        if (status.get(position) != null && status.get(position)) {
            return true;
        }
        return false;
    }

    /**
     * 获取选中的position
     *
     * @return
     */
    public int getSelectedPosition() {
        int position = 0;
        for (int i : status.keySet()) {
            if (getItemStatus(i)) {
                return i;
            }
        }
        return position;
    }

    /**
     * 获取第一个可用item的position
     *
     * @return
     */
    public int getFirstEnableItemPosition() {
        int position = 0;
        for (int i : statusAble.keySet()) {
            if (statusAble.get(i) != null && statusAble.get(i)) {
                return i;
            }
        }
        return position;
    }

    /**
     * 检查选中的item是否是在可用的item的范围内
     * 用于判断当前选中是否在切换日期后的可用范围内
     *
     * @param selectedPosition
     * @return
     */
    public boolean checkSelectedItemIsEnable(int selectedPosition) {
        for (int i : statusAble.keySet()) {
            if (statusAble.get(i) != null && statusAble.get(i)) {
                if (i == selectedPosition) {
                    return true;
                }
            }
        }
        return false;
    }

    static class ViewHolder {
        ItemDateTimeBinding binding;
        CheckBox mCbTime;

        public ViewHolder(ItemDateTimeBinding binding) {
            this.binding = binding;
            mCbTime = binding.cbTime;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setLastTime(String lastTime) {
        this.lastTime1 = lastTime;
        disposeLastTime();
    }

    /**
     * 处理最晚预定时间
     */
    private void disposeLastTime() {
        if (lastTime1 != null) {
            lastTime = DateFormatUtil.parseStringTolong(lastTime1, DateFormatUtil.HHmm);
            earliest = DateFormatUtil.parseStringTolong("18:00", DateFormatUtil.HHmm);
            last = DateFormatUtil.parseStringTolong("03:30", DateFormatUtil.HHmm);
            Log.w("llm", "lastTime = " + lastTime + "\n18:00 = " + earliest + "\n03:00 = " + last);
        }
    }

    public void setDay(int day) {
        this.day = day;
        notifyDataSetChanged();
    }
}
