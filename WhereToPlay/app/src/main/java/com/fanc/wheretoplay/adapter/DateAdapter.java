package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemDateBinding;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {
    Context mContext;
    List mData;
    // 状态标记
    HashMap<Integer, Boolean> status;
    // 上一次选中标记
    int pre;

    OnItemClickListener listener;

    public DateAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        status = new HashMap<>();
        status.put(0, true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_date, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.setDate((Date) mData.get(position));
        if (getItemStatus(position)) {
            holder.mLlDate.setBackgroundColor(UIUtils.getColor(R.color.text_red));
            holder.mTvItemDate.setSelected(true);
            holder.mTvItemWeek.setSelected(true);
        } else {
            holder.mLlDate.setBackgroundColor(UIUtils.getColor(R.color.tran));
            holder.mTvItemDate.setSelected(false);
            holder.mTvItemWeek.setSelected(false);
        }
        holder.mLlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanItemStatus(pre);
                notifyItemChanged(pre);
                setItemStatus(position);
                notifyItemChanged(position);
                pre = position;
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    private void setItemStatus(int position) {
        status.put(position, true);
    }

    private void cleanItemStatus(int position) {
        status.remove(position);
    }

    private boolean getItemStatus(int position) {
        return status.get(position) != null && status.get(position);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDateBinding binding;
        LinearLayout mLlDate;
        TextView mTvItemDate;
        TextView mTvItemWeek;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDateBinding) binding;
            mLlDate = this.binding.llDate;
            mTvItemDate = this.binding.tvDate;
            mTvItemWeek = this.binding.tvWeek;

            int itemWidth = UIUtils.getScreenWidth() / 7;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.itemView.setLayoutParams(layoutParams);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
