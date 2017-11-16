package com.fanc.wheretoplay.datamodel;

/**
 * Created by admin on 2017/11/16.
 */

public class OrderInfoModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"order_info":{"id":"769","store_name":"上海白金翰宫商务会所","arrival_time":"1510855200","prepay":"0.00","room_type":"小包"}}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * order_info : {"id":"769","store_name":"上海白金翰宫商务会所","arrival_time":"1510855200","prepay":"0.00","room_type":"小包"}
         */

        public OrderInfoBean order_info;

        public static class OrderInfoBean {
            /**
             * id : 769
             * store_name : 上海白金翰宫商务会所
             * arrival_time : 1510855200
             * prepay : 0.00
             * room_type : 小包
             */

            public String id;
            public String store_name;
            public String arrival_time;
            public String prepay;
            public String room_type;


        }
    }
}
