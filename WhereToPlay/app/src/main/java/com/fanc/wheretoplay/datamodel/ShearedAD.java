package com.fanc.wheretoplay.datamodel;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.network.Network;

/**
 * Created by Administrator on 2017/7/10.
 */

public class ShearedAD extends BaseModel {

    /**
     * share_ad: 推荐分享广告路径
     * share_code：推荐分享邀请码页面路径
     */

    public String share_ad;
    public String share_code;
    public String count;

    public String getCount() {
        if (!TextUtils.isEmpty(count)) {
            return count;
        } else return "0";

    }
}
