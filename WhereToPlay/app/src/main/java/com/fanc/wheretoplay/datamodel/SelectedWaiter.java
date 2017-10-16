package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.network.Network;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 */

public class SelectedWaiter extends BaseModel {

    /**
     * list : {"user_id":"3","waiter_id":"4","name":"呱呱","image":"/Upload/image/20170617/20170617153911_37982.jpg","type":1}
     */

    private Waiter list;

    public Waiter getList() {
        return list;
    }

    public void setList(Waiter list) {
        this.list = list;
    }

    public static class Waiter extends BaseObservable implements Serializable {
        /**
         * user_id: string, 用户ID
         * waiter_id: string, 服务员id
         * name：string, 服务员名字
         * image, string， 服务员图片路径
         * type: string，是否查看了服务员，0-未查看 1-已查看
         */

        private String user_id;
        private String waiter_id;
        private String name;
        private String image;
        private int type;

        @BindingAdapter(value = {"url"}, requireAll = false)
        public static void setWaiterImage(ImageView imageView, String url) {
            Log.d("aaa", "setWaiterImage: " + Network.IMAGE + url);
            Glide.with(imageView.getContext()).load(Network.IMAGE + url).into(imageView);
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getWaiter_id() {
            return waiter_id;
        }

        public void setWaiter_id(String waiter_id) {
            this.waiter_id = waiter_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
