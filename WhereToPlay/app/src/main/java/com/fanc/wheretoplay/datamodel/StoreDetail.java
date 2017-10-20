package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class StoreDetail extends BaseModel {


    /**
     * store : {"store_id":"1","name":"糖果KTV","branch":"宝安店","level":"3","phone":"0715-88888888",
     * "address":"广东省深圳市南山区科技中一路24",
     * "picture":[{"picture_path":"/Upload/image/20170525/20170525091634_92106.jpg","type":"2"},
     * {"picture_path":"/Upload/image/20170525/20170525091626_80213.jpg","type":"3"},
     * {"picture_path":"/Upload/image/20170525/20170525091645_89442.jpg","type":"4"},
     * {"picture_path":"/Upload/image/20170525/20170525091626_80213.jpg","type":"4"}],
     * "number":3,"comment":[{"comment_id":"3","avatar":"/Upload/image/20170525/20170525091626_80213.jpg"},
     * {"comment_id":"2","avatar":"/Upload/image/20170525/20170525091626_80213.jpg"},
     * {"comment_id":"1","avatar":"/Upload/image/20170525/20170525091626_80213.jpg"}],
     * "room":[{"id":"1","room_type":"1","book_price":"300.00","min_price":"100.00","path":"/Upload/image/20170607/20170607162804_75324.jpg"}]}
     */

    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public static class Store {
        /**
         * store_id: string, 商铺ID
         * name: string, 店铺名字
         * level：string, 商铺等级
         * phone, 商铺座机电话
         * address：商铺地址
         * remark:商铺详情
         * picture: array, 商铺详情图片列表
         * number: string，评论总数量,
         * comment：array, 评论列表(最新的三条评论头像)
         * room: array，商铺的房型
         * url: string,分享商铺的路径
         */

        private String store_id;
        private String name;
        private String branch;
        private String level;
        private String phone;
        private String address;
        private int number;
        private String discount;
        private String distance;
        private String lng;
        private String lat;
        private String remark;
        private ArrayList<Picture> picture;
        private List<CommentBean> comment;
        private List<Room> room;
        private String url;
        private List<StoreList.Store> list;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getNumber() {
            return number;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public ArrayList<Picture> getPicture() {
            return picture;
        }

        public void setPicture(ArrayList<Picture> picture) {
            this.picture = picture;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public List<Room> getRoom() {
            return room;
        }

        public void setRoom(List<Room> room) {
            this.room = room;
        }

        public List<StoreList.Store> getList() {
            return list;
        }

        public void setList(List<StoreList.Store> list) {
            this.list = list;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }

    public static class Picture implements Serializable {
        /**
         * picture_path：图片地址
         * type：图片类型：图片的类型2-详情图1|3-详情图2|4-详情图3
         */

        private String picture_path;
        private String type;

        public String getPicture_path() {
            return picture_path;
        }

        public void setPicture_path(String picture_path) {
            this.picture_path = picture_path;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class CommentBean {
        /**
         * comment_id:评论的id
         * avatar: 用户的头像
         */

        private String comment_id;
        private String avatar;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class Room {
        /**
         * id: 房间的id
         * room_type: string, 房间类型
         * min_price: string，最低消费价格
         */

        private String id;
        private String room_type;
        private String book_price;
        private String min_price;
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getBook_price() {
            return book_price;
        }

        public void setBook_price(String book_price) {
            this.book_price = book_price;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
