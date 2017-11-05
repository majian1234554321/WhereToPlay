package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.CheckCommentsActivity;
import com.fanc.wheretoplay.activity.LargeImageActivity;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.databinding.ItemCheckCommentsBinding;
import com.fanc.wheretoplay.databinding.ItemCheckCommentsCardviewBinding;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private List<String> mPictureList;
    Context mContext;
    private DisplayMetrics dm;
    private RelativeLayout.LayoutParams lp;
    private ItemCheckCommentsCardviewBinding binding;
    private  ArrayList<String> imgs = new ArrayList<>();

    public CardViewAdapter(Context mContext, List<String> picture) {
        this.mContext = mContext;
        this.mPictureList = picture;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_check_comments_cardview, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        //获取屏幕宽度,再减去一行中的空白部分最后除于一行的条目数就等于一个条目的宽高
        WindowManager windowManager = ((CheckCommentsActivity) mContext).getWindowManager();
        dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        lp = new RelativeLayout.LayoutParams((dm.widthPixels - UIUtils.dp2Px(45)) / 4, (dm.widthPixels - UIUtils.dp2Px(45)) / 4);
        binding.cl.setLayoutParams(lp);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        binding.cl.setLayoutParams(lp);
//        holder.mIvCardView.setLayoutParams(lp);
        Log.e("wade", "onBindViewHolder: "+ holder.mIvCardView.getWidth() +"\t" + holder.mIvCardView.getHeight());
        Glide.with(mContext).load(Network.IMAGE + mPictureList.get(position)).placeholder(R.drawable.default_rect).into(holder.mIvCardView);
        imgs.add(mPictureList.get(position));
        //点击事件
        holder.mIvCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LargeImageActivity.class);
                intent.putExtra(Constants.URL, imgs);
                intent.putExtra(Constants.POSITION, position);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPictureList == null) {
            return 0;
        }
        return mPictureList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemCheckCommentsCardviewBinding mBinding;
        private ImageView mIvCardView;

        public ViewHolder(ItemCheckCommentsCardviewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            initViews();
        }

        private void initViews() {
            mIvCardView = mBinding.ivCardview;
        }
    }
}
