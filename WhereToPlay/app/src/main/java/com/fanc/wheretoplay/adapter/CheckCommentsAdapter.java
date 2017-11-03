package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.ItemCheckCommentsBinding;
import com.fanc.wheretoplay.databinding.ItemHousenewsBinding;
import com.fanc.wheretoplay.datamodel.CheckComments;
import com.fanc.wheretoplay.datamodel.HousenewsList;
import com.fanc.wheretoplay.divider.GridDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CheckCommentsAdapter extends RecyclerView.Adapter<CheckCommentsAdapter.ViewHolder> {
    private  CheckComments mResponse;
    private List mList;
    Context mContext;

    public CheckCommentsAdapter(Activity mContext, List list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCheckCommentsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_check_comments, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CheckComments.CommentListBean commentListBean = (CheckComments.CommentListBean)mList.get(position);
        //头像
        Glide.with(mContext).load(Network.IMAGE + commentListBean.getAvatar()).placeholder(R.drawable.default_rect).into(holder.mIvAvatar);
        //评论
        holder.mTvContent.setText(commentListBean.getComment());
        //时间
        holder.mTvTime.setText(DateFormatUtil.getDateFormatString(commentListBean.getCreated_time()));
        //昵称
        holder.mTvNickName.setText(commentListBean.getNickname());
        //平均分星星
        holder.mRbComments.setRating(Float.parseFloat(commentListBean.getAverage_comment()));

        //评论中的图片
        if (commentListBean.getPicture() != null && commentListBean.getPicture().size() > 0) {
            holder.mRcCardView.setVisibility(View.VISIBLE);
            CardViewAdapter cardViewAdapter = new CardViewAdapter(mContext, commentListBean.getPicture());
            holder.mRcCardView.setLayoutManager(new GridLayoutManager(mContext, 4));
            //设置图片间距
            holder.mRcCardView.addItemDecoration(new GridDivider(mContext,4, UIUtils.dp2Px(5), mContext.getResources().getColor(R.color.white)));
            holder.mRcCardView.setAdapter(cardViewAdapter);
        } else {
            holder.mRcCardView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        private final ItemCheckCommentsBinding mBinding;
        private ImageView mIvAvatar;
        private RecyclerView mRcCardView;
        private TextView mTvContent;
        private TextView mTvNickName;
        private TextView mTvTime;
        private RatingBar mRbComments;

        public ViewHolder(ItemCheckCommentsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            initViews();
        }

        private void initViews() {
            mIvAvatar = mBinding.ivCheckCommentsAvatar;
            mRcCardView = mBinding.rcCardview;
            mTvContent = mBinding.tvCheckCommentsContent;
            mTvNickName  = mBinding.tvCheckCommentsNickname;
            mTvTime = mBinding.tvCheckCommentsTime;
            mRbComments = mBinding.rbComments;
        }
    }
}
