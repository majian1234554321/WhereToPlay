package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.network.Network;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class CollectionList extends BaseModel {

    private List<Collection> list;

    public List<Collection> getList() {
        return list;
    }

    public void setList(List<Collection> list) {
        this.list = list;
    }

    public static class Collection extends BaseObservable {
        /**
         * id：string，收藏id，
         * name: string, 商店名称，
         * path: string, 商店封面路径，
         * grade: string，商店档次，
         * decorate: string, 商店装修风格
         * district：string，商店所在区名
         * distance：string，用户与商店的距离
         * min_price: string, 最低消费单位元
         */

        private String id;
        private String name;
        private String path;
        private String grade;
        private String decorate;
        private String district;
        private String distance;
        private String min_price;

        @BindingAdapter(value = {"img"}, requireAll = false)
        public static void setCollectionImage(ImageView imageView, String url) {
            Glide.with(imageView.getContext()).load(Network.IMAGE + url).into(imageView);
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getDecorate() {
            return decorate;
        }

        public void setDecorate(String decorate) {
            this.decorate = decorate;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }
    }
}
