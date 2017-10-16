package com.fanc.wheretoplay.datamodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.network.Network;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Url extends BaseModel {

    private String url;
    public String marketing;

    @BindingAdapter(value = {"url"}, requireAll = false)
    public static void setImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(Network.IMAGE + url).into(imageView);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
