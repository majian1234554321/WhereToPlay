package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.MineCommendFriendBinding;
import com.fanc.wheretoplay.databinding.MineCommendMoneyBinding;

import java.util.List;

/**
 * Created by peace on 2017/11/8.
 */

public class MineMoneyAdapter extends RecyclerView.Adapter<MineMoneyAdapter.ViewHolder> {

    private  List mList;
    private  Context mContext;

    public MineMoneyAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MineCommendMoneyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.mine_commend_money, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //价格
        SpannableString text = new SpannableString(holder.mTvMineMoney.getText() + " 元");
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length()-1, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.mTvMineMoney.setText(text);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private  MineCommendMoneyBinding mBinding;
        private ImageView mIvMineFriend;
        private TextView mTvMineFriendTime;
        private TextView mTvMineFriendTitle;
        private TextView mTvMineMoney;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = (MineCommendMoneyBinding)binding;
            initViews();
        }

        private void initViews() {
            mIvMineFriend = mBinding.ivMineMoney;
            mTvMineFriendTime = mBinding.tvMineMoneyTime;
            mTvMineFriendTitle = mBinding.tvMineMoneyTitle;
            mTvMineMoney = mBinding.tvMineMoney;
        }
    }
}
