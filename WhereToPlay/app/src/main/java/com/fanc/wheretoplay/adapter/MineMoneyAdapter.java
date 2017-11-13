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

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.databinding.MineCommendMoneyBinding;
import com.fanc.wheretoplay.datamodel.MineMoney;
import com.fanc.wheretoplay.util.DateFormatUtil;

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
//        MineMoney.ContentBean mMoneyList = (MineMoney.ContentBean) mList.get(position);
//        //价格
//        SpannableString text = new SpannableString("+" + mMoneyList.getAmount() + " 元");
//        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length()-1, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        holder.mTvMineMoney.setText(text);
//        //头像
//        Glide.with(mContext).load(mMoneyList.getPic()).placeholder(R.drawable.default_rect).into(holder.mIvMineMoney);
//        //时间
//        holder.mTvMineMoeyTime.setText(DateFormatUtil.getYYYYMMDDHHmm(mMoneyList.getRegTime()));
//        //nickname
//        holder.mTvMineMoneyTitle.setText(mMoneyList.getNickname());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private  MineCommendMoneyBinding mBinding;
        private ImageView mIvMineMoney;
        private TextView mTvMineMoeyTime;
        private TextView mTvMineMoneyTitle;
        private TextView mTvMineMoney;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = (MineCommendMoneyBinding)binding;
            initViews();
        }

        private void initViews() {
            mIvMineMoney = mBinding.ivMineMoney;
            mTvMineMoeyTime = mBinding.tvMineMoneyTime;
            mTvMineMoneyTitle = mBinding.tvMineMoneyTitle;
            mTvMineMoney = mBinding.tvMineMoney;
        }
    }
}
