package com.fanc.wheretoplay.datamodel;

/**
 * Created by admin on 2017/11/9.
 */

public class AccessOrderIdModel {


    /**
     * code : 0
     * message : 请求成功
     * content : {"order_id":"465"}
     */

    public String code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * order_id : 465
         */

        public String order_id;
        public String prepay;
        public String result;
        public String origin_prepay;
        public String status;
        public String year_review_status;
        public String application_amount;
        public String patch_card_amount;
        public String year_review_amount;
        public String patch_card_status;
    }
}
