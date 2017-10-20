package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class StoreList extends BaseModel {

    private List<String> sliders;//banner图路径列表
    private List<Store> store;//商铺列表

    public void setSliders(List<String> sliders) {
        this.sliders = sliders;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

    public List<String> getSliders() {
        return sliders;
    }

    public List<Store> getStore() {
        return store;
    }

    public static class Store {
        private String id;//商铺ID
        private String name;//店铺名字
        private String discount;// 商铺活动折扣
        private String capita;//人均消费元
        private String distance;//商铺距离，单位米，如果没有内容返回”-1”
        private String cover;// 图片路径
        private String area;// 区域

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDiscount() {
            return discount;
        }

        public String getCapita() {
            return capita;
        }

        public String getDistance() {
            return distance;
        }

        public String getCover() {
            return cover;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public void setCapita(String capita) {
            this.capita = capita;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }

}
