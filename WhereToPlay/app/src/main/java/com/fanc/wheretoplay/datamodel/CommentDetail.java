package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 */

public class CommentDetail extends BaseModel {

    public List<Comment> list;

    public static class Comment extends BaseObservable {
        /**
         * id: string, 评论的id
         * nickname: string, 用户昵称
         * avatar：string，用户头像路径
         * created_time: string, 创建的时间
         * sys_othert: 系统评论
         * cus_othert: 用户自定义评论
         */

        public String id;
        public String nickname;
        public String avatar;
        public String created_time;
        public String sys;
        public String cus;

        @BindingAdapter(value = {"url"}, requireAll = false)
        public static void setUserImage(CircleImageView imageView, String url) {
            Glide.with(imageView.getContext()).load(Network.IMAGE + url).error(R.drawable.default_square).into(imageView);
        }
    }
}
