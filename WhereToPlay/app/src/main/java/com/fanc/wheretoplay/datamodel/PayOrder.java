package com.fanc.wheretoplay.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017/11/10.
 */

public class PayOrder {

    /**
     * code : 0
     * message : 请求成功
     * content : {"appid":"wx424026eca78d03e6","partnerid":"1486907482","prepayid":null,"package":"Sign=WXPay","noncestr":"5HO1aiDlmTqhqzIk1gyFPk3T8emsz6w7","timestamp":1510280834,"sign":"71A5CFF51362D3B5AD8F9F734D2E096E"}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * appid : wx424026eca78d03e6
         * partnerid : 1486907482
         * prepayid : null
         * package : Sign=WXPay
         * noncestr : 5HO1aiDlmTqhqzIk1gyFPk3T8emsz6w7
         * timestamp : 1510280834
         * sign : 71A5CFF51362D3B5AD8F9F734D2E096E
         */

        public String appid;
        public String partnerid;
        public Object prepayid;
        @SerializedName("package")
        public String packageX;
        public String noncestr;
        public int timestamp;
        public String sign;
        public String  orderString;
        public String  orderform_id;
    }
}
