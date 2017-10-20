package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/9/15.
 */

public class OrderReserved extends BaseModel {

    /**
     * order_info : {"store_name":"中国好声音","address":"广东省深圳市宝安区开屏路33","discount":"12","distance":"1265","nickname":"盖茨","mobile":"13826505722","room_type":"豪华大房","arrival_time":"1505482200","car_num":"0","mans":"1","waiter_name":"","remark":""}
     */

    public OrderInfo order_info;

    public static class OrderInfo {
        /**
         * store_name : 中国好声音
         * address : 广东省深圳市宝安区开屏路33
         * discount : 12
         * distance : 1265
         * nickname : 盖茨
         * mobile : 13826505722
         * room_type : 豪华大房
         * arrival_time : 1505482200
         * car_num : 0
         * mans : 1
         * waiter_name :
         * remark :
         */

        public String store_name;
        public String address;
        public String discount;
        public String distance;
        public String nickname;
        public String mobile;
        public String room_type;
        public String arrival_time;
        public String car_num;
        public String mans;
        public String waiter_name;
        public String remark;
    }
}
