package com.fanc.wheretoplay.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


import com.bumptech.glide.Glide;

import java.io.File;

import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by llm on 2016/11/4.
 */

public class GlideGalleryImageLoader implements ImageLoader {

    private Bitmap.Config mConfig;

    public GlideGalleryImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public GlideGalleryImageLoader(Bitmap.Config config) {
        this.mConfig = config;
    }

    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        Glide.with(activity)
                .load(new File(path))
                .placeholder(defaultDrawable)//当图片没有加载上的时候，显示的图片
                .error(defaultDrawable) //当图片加载错误的时候，显示图片
//                .resize(width, height)  //为图片重新定义大小
//                .centerInside()
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
