package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Drinks extends BaseModel {


    /**
     * content : {"list":[{"id":"32","name":"皇家礼炮21年","price":"4800.00","activity":"买二送一"},{"id":"33","name":"芝华士15年","price":"1980.00","activity":"买二送一"},{"id":"34","name":"芝华士12年","price":"1580.00","activity":"买二送一"},{"id":"35","name":"芝华士18年","price":"2980.00","activity":"买二送一"},{"id":"36","name":"马爹利xo","price":"5800.00","activity":"买二送一"},{"id":"37","name":"马爹利蓝带","price":"3800.00","activity":"买二送一"},{"id":"38","name":"马爹利名仕","price":"1980.00","activity":"买二送一"},{"id":"39","name":"轩尼诗vsop","price":"2580.00","activity":"买二送一"},{"id":"40","name":"兰方","price":"4800.00","activity":"买二送一"},{"id":"41","name":"黑方","price":"1580.00","activity":"买二送一"},{"id":"42","name":"百龄坛15年","price":"1580.00","activity":"买二送一"},{"id":"43","name":"百龄坛17年","price":"2580.00","activity":"买二送一"},{"id":"44","name":"喜力啤酒（小瓶）","price":"80.00","activity":"买二送一"},{"id":"45","name":"百威啤酒（小瓶）","price":"60.00","activity":"买二送一"},{"id":"46","name":"青岛啤酒（小瓶）","price":"50.00","activity":"买二送一"}]}
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 32
         * name : 皇家礼炮21年
         * price : 4800.00
         * activity : 买二送一
         */

        private String id;
        private String name;
        private String price;
        private String activity;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }
    }

}

