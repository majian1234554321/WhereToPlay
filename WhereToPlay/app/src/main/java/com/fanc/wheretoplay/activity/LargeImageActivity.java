package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivityLargeImageBinding;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.PhotoViewPager;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by llm on 2016/11/24.
 */

public class LargeImageActivity extends BaseActivity {

    ActivityLargeImageBinding imageBinding;

//    TopMenu mTmLargeImage;
    LinearLayout mLlDots;
    RelativeLayout mRlImg;
    PhotoViewPager mVpImage;

    private ArrayList<String> images;// 图片地址
    private int pos;
    private ArrayList<PhotoView> imgs;
    Intent intent;
    int statuBarHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageBinding = DataBindingUtil.setContentView(this, R.layout.activity_large_image);
        initViews();
        init();
    }

    private void initViews() {
//        mTmLargeImage = imageBinding.tmLargeImage;
        mLlDots = imageBinding.llDotsLargeImage;
        mRlImg = imageBinding.rlImg;
        mVpImage = imageBinding.vpLargeImage;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getRawY() - statuBarHeight;
        if (!(y < mVpImage.getBottom() && y > mVpImage.getTop())) {
            finish();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void init() {
//        mTmLargeImage.setLeftIcon(R.drawable.left);
//        mTmLargeImage.setTitle("商家图片");
//        mTmLargeImage.setLeftIconOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        statuBarHeight = getStatuBarHeight();
        intent = getIntent();
        images = (ArrayList<String>) intent.getSerializableExtra(Constants.URL);
        pos = intent.getIntExtra(Constants.POSITION, 0);
        initData();
        mVpImage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView imageView = imgs.get(position);
                container.addView(imageView);
                imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        LargeImageActivity.this.finish();
                    }
                });
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        mVpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLlDots.getChildAt(position).setSelected(true);
                mLlDots.getChildAt(pos).setSelected(false);
                pos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpImage.setCurrentItem(pos);
        mLlDots.getChildAt(pos).setSelected(true);
    }

    private void initData() {
        PhotoView imageView;
        View view;
        LinearLayout.LayoutParams paramsDots = new LinearLayout.LayoutParams(UIUtils.dp2Px(10), UIUtils.dp2Px(10));
        imgs = new ArrayList<>();
        int defaultImage = 0;
        for (int i = 0; i < images.size(); i++) {
            imageView = new PhotoView(mContext);
            if (i < 2) {
                defaultImage = R.drawable.default_rect;
            } else {
                defaultImage = R.drawable.default_square;
            }
            Glide.with(mContext).load(Network.IMAGE + images.get(i)).placeholder(defaultImage).into(imageView);
            imgs.add(imageView);
            view = new View(mContext);
            view.setBackgroundResource(R.drawable.selector_dots);
            if (i != 0) {
                paramsDots.setMargins(UIUtils.dp2Px(8), 0, 0, 0);
            }
            view.setLayoutParams(paramsDots);
            mLlDots.addView(view);
        }
    }
}
