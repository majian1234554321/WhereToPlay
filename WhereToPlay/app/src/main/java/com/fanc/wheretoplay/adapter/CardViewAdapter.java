package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.CheckCommentsActivity;
import com.fanc.wheretoplay.activity.LargeImageActivity;
import com.fanc.wheretoplay.base.App;

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

public class CardViewAdapter extends BaseAdapter {

    private List<String> mPictureList;
    Context mContext;
    private DisplayMetrics dm;
    private RelativeLayout.LayoutParams lp;

    private ArrayList<String> imgs = new ArrayList<>();

    public CardViewAdapter(Context mContext, List<String> picture) {
        this.mContext = mContext;
        this.mPictureList = picture;
    }

  /*  @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_check_comments_cardview, parent, false);
        ViewHolder holder = new ViewHolder(binding);
        //获取屏幕宽度,再减去一行中的空白部分最后除于一行的条目数就等于一个条目的宽高
        WindowManager windowManager = ((CheckCommentsActivity) mContext).getWindowManager();
        dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return holder;
    }*/

/*
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       */
/* Glide.with(mContext).load(Network.IMAGE + mPictureList.get(position)).asBitmap().placeholder(R.drawable.default_rect).dontAnimate().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                //这个bitmap就是你图片url加载得到的结果
                //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                ViewGroup.LayoutParams layoutParams =  binding.cl.getLayoutParams();//获取你要填充图片的布局的layoutParam
                layoutParams.height = (dm.widthPixels - UIUtils.dp2Px(45)) / 4;
                //因为是2列,所以宽度是屏幕的一半,高度是根据bitmap的高/宽*屏幕宽的一半
                layoutParams.width =  (dm.widthPixels - UIUtils.dp2Px(45)) / 4;//这个是布局的宽度
                binding.cl.setLayoutParams(layoutParams);//容器的宽高设置好了
                bitmap = zoomImg(bitmap, layoutParams.width, layoutParams.height);
                // 然后在改变一下bitmap的宽高
                holder.mIvCardView.setImageBitmap(bitmap);
            }
        });*//*


        Glide.with(mContext).load( mPictureList.get(position)).into(holder.mIvCardView);

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
*/

  /*  //改变bitmap尺寸的方法
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }*/

  /*  @Override
    public int getItemCount() {
        if (mPictureList == null) {
            return 0;
        }
        return mPictureList.size();
    }*/

    @Override
    public int getCount() {
        if (mPictureList == null) {
            return 0;
        }
        return mPictureList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPictureList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View views = View.inflate(mContext, R.layout.item_check_comments_cardview, null);
        ImageView iv = views.findViewById(R.id.iv_cardview);
        Glide.with(mContext).load(mPictureList.get(position)).into(iv);

        imgs.add(mPictureList.get(position));
        //点击事件
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LargeImageActivity.class);
                intent.putExtra(Constants.URL, imgs);
                intent.putExtra(Constants.POSITION, position);
                mContext.startActivity(intent);

            }
        });
        return views;
    }

  /*  static class ViewHolder extends RecyclerView.ViewHolder {

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
    }*/
}
