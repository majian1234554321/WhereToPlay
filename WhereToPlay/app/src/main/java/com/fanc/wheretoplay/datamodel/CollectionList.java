package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class CollectionList extends BaseModel {

    /**
     * content : {"list":[{"store_id":"5","id":"89","name":"上海白金翰宫商务会所","path":"/Upload/image/20170925/20170925093607_30193.jpg","grade":"顶级","decorate":"欧式","district":"黄浦区","distance":"11201","min_price":"1000"},{"store_id":"5","id":"88","name":"上海白金翰宫商务会所","path":"/Upload/image/20170925/20170925093607_30193.jpg","grade":"顶级","decorate":"欧式","district":"黄浦区","distance":"11201","min_price":"1000"}]}
     */
    private List<Collection> list;

    public List<Collection> getList() {
        return list;
    }

    public void setList(List<Collection> list) {
        this.list = list;
    }

    public static class Collection {
        /**
         * store_id : 5
         * id : 89
         * name : 上海白金翰宫商务会所
         * path : /Upload/image/20170925/20170925093607_30193.jpg
         * grade : 顶级
         * decorate : 欧式
         * district : 黄浦区
         * distance : 11201
         * min_price : 1000
         */

        private String store_id;
        private String id;
        private String name;
        private String path;
        private String grade;
        private String decorate;
        private String district;
        private String distance;
        private String min_price;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
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
