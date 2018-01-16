package com.fanc.wheretoplay.datamodel;

/**
 * @author admin
 * @date 2018/1/12
 */

public class OrderDoneModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"order":{"order_id":"3464","name":"上海凯撒国际会所","discount":"8.4","prepay":"0.00","cash_rate":"15.00","distance":"-1","address":"静安区石门二路301号"}}
     */

    public String  code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * order : {"order_id":"3464","name":"上海凯撒国际会所","discount":"8.4","prepay":"0.00","cash_rate":"15.00","distance":"-1","address":"静安区石门二路301号"}
         */

        public OrderBean order;

        public static class OrderBean {
            /**
             * order_id : 3464
             * name : 上海凯撒国际会所
             * discount : 8.4
             * prepay : 0.00
             * cash_rate : 15.00
             * distance : -1
             * address : 静安区石门二路301号
             */

            public String order_id;
            public String name;
            public String discount;
            public String prepay;
            public String cash_rate;
            public String distance;
            public String address;
        }
    }
}
