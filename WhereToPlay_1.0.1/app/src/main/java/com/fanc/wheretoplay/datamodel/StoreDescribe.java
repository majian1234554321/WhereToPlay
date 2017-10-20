package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/10.
 */

public class StoreDescribe extends BaseModel {

    /**
     * store : {"id":"55","name":"中国好声音","discount":"9","address":"广东省深圳市宝安区开屏路33","distance":"1265","park":"1","reserved_time":"22:50","capita":"100","area":"宝安区","cover":"/Upload/image/2017-09-08/1504854104_1777803730.jpg"}
     */

    public Store store;

    public static class Store {
        /**
         * id : 55
         * name : 中国好声音
         * discount : 9
         * address : 广东省深圳市宝安区开屏路33
         * distance : 1265
         * park : 1
         * reserved_time : 22:50
         * capita : 100
         * area : 宝安区
         * cover : /Upload/image/2017-09-08/1504854104_1777803730.jpg
         */

        public String id;
        public String name;
        public String discount;
        public String address;
        public String distance;
        public String park;
        public String reserved_time;
        public String capita;
        public String area;
        public String cover;
    }
}
