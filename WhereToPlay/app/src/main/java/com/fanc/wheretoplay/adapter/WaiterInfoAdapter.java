package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemWaiterBinding;
import com.fanc.wheretoplay.datamodel.WaiterList;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class WaiterInfoAdapter extends RecyclerView.Adapter<WaiterInfoAdapter.ViewHolder> {

    Context mContext;
    List mData;
    /**
     * 屏幕宽度
     */
    int screenWidth;
    /**
     * 点击监听
     */
    OnItemClickListener listener;

    /**
     * 选中状态
     */
    HashMap<Integer, Boolean> status;
    int prePosition;// 上一次选中的位置
    /**
     * 是否是选择服务员
     */
    boolean isSelect;

    public WaiterInfoAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        status = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_waiter, parent, false));
        holder.setItemWidthAndHeight((screenWidth - UIUtils.dp2Px(30)) / 3);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        WaiterList.WaiterInfo waiterInfo = (WaiterList.WaiterInfo) mData.get(position);
        holder.binding.setWaiter(waiterInfo);
        holder.mCbStatus.setChecked(getItemStatus(position));
        holder.mCbStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearItemStatus(prePosition);
                setItemStatus(position, true);

                if (listener != null) {
                    listener.onItemSelected(position);
                }
            }
        });
        holder.mFlWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }

            }
        });
        if (isSelect) {
            if (waiterInfo.getType() == 0) {
                holder.mCbStatus.setVisibility(View.GONE);
            } else {
                holder.mCbStatus.setVisibility(View.VISIBLE);
            }
        } else {
            holder.mCbStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    /**
     * 获取选中状态
     *
     * @param position
     * @return
     */
    public boolean getItemStatus(int position) {
        if (status.get(position) != null && status.get(position)) {
            return true;
        }
        return false;
    }

    /**
     * 清楚所有状态
     */
    public void clearAllStatus() {
//        for (Integer i : status.keySet()) {
//            status.remove(i);
//        }

        status.clear();
    }

    /**
     * 清楚状态
     *
     * @param position
     */
    public void clearItemStatus(int position) {
        status.remove(position);
        notifyItemChanged(position);
    }

    /**
     * 设置状态
     *
     * @param position
     * @param condition
     */
    public void setItemStatus(int position, boolean condition) {
        status.put(position, condition);
        prePosition = position;
        notifyItemChanged(position);
    }

    /**
     * 获取选中的数量
     *
     * @return
     */
    public int getSelectedItemCount() {
        int count = 0;
        if (status.size() == 0) {
            return count;
        }
        for (int i = 0; i < status.size(); i++) {
            if (getItemStatus(i)) {
                count++;
            }
        }
        return count;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    /**
     * 设置点击监听器
     *
     * @param listener
     */
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemSelected(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemWaiterBinding binding;

        FrameLayout mFlWaiter;
        ImageView mIvDistinct;
        ImageView mIvFuzzy;
        ImageView mIvLock;
        CheckBox mCbStatus;
        TextView mTvName;

        int itemWidth;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemWaiterBinding) binding;
            initViews();
        }

        private void initViews() {
            mFlWaiter = binding.flItemWaiter;
            mIvDistinct = binding.ivItemWaiterDistinct;
            mIvFuzzy = binding.ivItemWaiterFuzzy;
            mIvLock = binding.ivItemWaiterLock;
            mCbStatus = binding.cbItemWaiterStatus;
            mTvName = binding.tvItemWaiterName;
        }

        /**
         * 调整item的宽高
         *
         * @param itemWidthAndHeight
         */
        public void setItemWidthAndHeight(int itemWidthAndHeight) {
            this.itemWidth = itemWidthAndHeight;
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mFlWaiter.getLayoutParams();
            lp.width = itemWidth;
            lp.height = itemWidth;
            mFlWaiter.setLayoutParams(lp);
        }


    }

}
