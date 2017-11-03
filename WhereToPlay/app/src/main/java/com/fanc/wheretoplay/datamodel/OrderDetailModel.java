package com.fanc.wheretoplay.datamodel;

/**
 * Created by admin on 2017/11/3.
 */

public class OrderDetailModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"store_name":"上海滩&amp;菁英会国际会所","address":"普陀区长寿路577号","order_action":"0","order_name":"","mobile":"18218419525","name":"大包","number":"A01","arrival_time":"1506364200","car_num":"0","people_num":"1","action":"0","remark":"","order_sn":"","created_time":"1506311245","reserve_time":"1506322528","finish_time":"1506322519"}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * store_name : 上海滩&amp;菁英会国际会所
         * address : 普陀区长寿路577号
         * order_action : 0
         * order_name :
         * mobile : 18218419525
         * name : 大包
         * number : A01
         * arrival_time : 1506364200
         * car_num : 0
         * people_num : 1
         * action : 0
         * remark :
         * order_sn :
         * created_time : 1506311245
         * reserve_time : 1506322528
         * finish_time :  1506322519
         */

        public String store_name;
        public String address;
        public String order_action;
        public String order_name;
        public String mobile;
        public String name;
        public String number;
        public String arrival_time;
        public String car_num;
        public String people_num;
        public String action;
        public String remark;
        public String order_sn;
        public String created_time;
        public String reserve_time;
        public String finish_time;
    }
}
