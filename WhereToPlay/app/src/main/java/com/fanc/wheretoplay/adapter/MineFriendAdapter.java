package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.MineCommendFriendBinding;
import com.fanc.wheretoplay.datamodel.MineFriend;
import com.fanc.wheretoplay.util.DateFormatUtil;

import java.util.List;

/**
 * Created by peace on 2017/11/8.
 */

public class MineFriendAdapter extends RecyclerView.Adapter<MineFriendAdapter.ViewHolder> {

    private  List mList;
    private  Context mContext;

    public MineFriendAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MineCommendFriendBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.mine_commend_friend, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MineFriend.ContentBean mMineFriendList = (MineFriend.ContentBean) mList.get(position);
        //头像
        Glide.with(mContext).load(mMineFriendList.getPic()).placeholder(R.drawable.default_rect).into(holder.mIvMineFriend);
        //时间
        holder.mTvMineFriendTime.setText(String.format(mContext.getResources().getString(R.string.money_time),DateFormatUtil.getYYYYMMDDHHmm(mMineFriendList.getRegTime())));
        //nickname
        holder.mTvMineFriendTitle.setText(mMineFriendList.getNickname());
    }

    @Override
    public int getItemCount() {
        if (mList !=null) {
            return mList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private  MineCommendFriendBinding mBinding;
        private ImageView mIvMineFriend;
        private TextView mTvMineFriendTitle;
        private TextView mTvMineFriendTime;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = (MineCommendFriendBinding)binding;
            initViews();
        }

        private void initViews() {
            mIvMineFriend = mBinding.ivMineFriend;
            mTvMineFriendTitle = mBinding.tvMineFriendTitle;
            mTvMineFriendTime = mBinding.tvMineFriendTime;
        }
    }
}
