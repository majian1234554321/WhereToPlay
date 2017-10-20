package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class RoomList extends BaseModel {

    private List<Room> list;

    public List<Room> getList() {
        return list;
    }

    public void setList(List<Room> list) {
        this.list = list;
    }

    public static class Room {
        /**
         * id : 2
         * room_type : 3
         * name : 中卡
         * book_price : 200.00
         * min_price : 80.00
         * path : /Upload/image/20170607/20170607162834_97118.jpg
         */

        private String id;
        private String room_type;
        private String name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
