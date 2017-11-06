package com.fanc.wheretoplay.datamodel;

/**
 * Created by admin on 2017/11/3.
 */

public class CancleOrderModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"is_cancle":true}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * is_cancle : true
         */

        public boolean is_cancle;
    }
}
