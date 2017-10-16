package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Advertising extends BaseModel {

    private List<Shop> list;

    public List<Shop> getList() {
        return list;
    }

    public void setList(List<Shop> list) {
        this.list = list;
    }

    public static class Shop {
        /**
         * id : 1
         * store_id : 2
         * picture : /Upload/image/20170526/20170526143707_39113.jpg
         */

        private String id;
        private String store_id;
        private String picture;
        private String title;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
