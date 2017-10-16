package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemMessageBinding;
import com.fanc.wheretoplay.datamodel.MessageList;
import com.fanc.wheretoplay.view.ItemSlideHelper;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> implements ItemSlideHelper.Callback {

    private final String READED = "1";// 已读

    Context mContext;
    List mData;

    RecyclerView recyclerView;

    OnItemClickListener onItemClickListener;

    public MessageAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MessageList.Message message = (MessageList.Message) mData.get(position);
        holder.messageBinding.setMessage(message);
//        holder.mSmlDelete.setIos(false).setLeftSwipe(true);
        if (READED.equals(message.getReaded())) {
            holder.mVUnread.setVisibility(View.GONE);
        } else {
            holder.mVUnread.setVisibility(View.VISIBLE);
        }
        holder.mLLMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClickDelete(position);
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.addOnItemTouchListener(new ItemSlideHelper(recyclerView.getContext(), this));
    }

    @Override
    public int getHorzontalRange(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) holder.itemView;
            return linearLayout.getChildAt(1).getLayoutParams().width;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return recyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return recyclerView.findChildViewUnder(x, y);
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemMessageBinding messageBinding;

        LinearLayout mLLMessage;
        View mVUnread;
        TextView mTvMessageTitle;
        TextView mTvMessageContent;
        Button mBtnDelete;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            messageBinding = (ItemMessageBinding) binding;
            initViews();
        }

        private void initViews() {
//            mSmlDelete = messageBinding.smlDelete;
            mLLMessage = messageBinding.llItemMessage;
            mVUnread = messageBinding.vUnread;
            mTvMessageTitle = messageBinding.tvItemMessageTitle;
            mTvMessageContent = messageBinding.tvItemMessageContent;
            mBtnDelete = messageBinding.btnItemMessageDelete;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onClickDelete(int position);
    }

}
