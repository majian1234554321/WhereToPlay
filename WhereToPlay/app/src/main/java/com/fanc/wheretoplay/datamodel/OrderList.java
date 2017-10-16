package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */

public class OrderList extends BaseModel {

    public List<Order> list;

    public static class Order {
        /**
         * order_id : 1076
         * store_id : 55
         * store_name : 中国好声音
         * discount : 12
         * nickname : 盖茨
         * mobile : 13826505722
         * arrival_time : 1505482200
         * book_type : 2
         * room_type : 豪华大房
         * room_number : 777
         * status : 2
         * consume_again : 1
         * is_comment : 0
         * is_display : 1
         */

        public String order_id;
        public String store_id;
        public String store_name;
        public String discount;
        public String nickname;
        public String mobile;
        public String arrival_time;
        public String book_type;
        public String room_type;
        public String room_number;
        public String status;
        public String consume_again;
        public String is_comment;
        public String is_display;
    }
}
