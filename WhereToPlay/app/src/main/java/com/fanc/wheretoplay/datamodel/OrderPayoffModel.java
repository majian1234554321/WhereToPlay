package com.fanc.wheretoplay.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * @author admin
 * @date 2017/12/22
 */

public class OrderPayoffModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"appid":"wx36ee02c8a52b9dc1","partnerid":"1444226202","prepayid":"wx201707180952240d55800c2a0348712851","package":"Sign=WXPay","noncestr":"5h6d8jE6j7XoLFndKcAJCsepUOMiLoDB","timestamp":1500339346,"sign":"005AB6C710561FAAD1950785CDDA9A0D"}
     */

    public String code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * appid : wx36ee02c8a52b9dc1
         * partnerid : 1444226202
         * prepayid : wx201707180952240d55800c2a0348712851
         * package : Sign=WXPay
         * noncestr : 5h6d8jE6j7XoLFndKcAJCsepUOMiLoDB
         * timestamp : 1500339346
         * sign : 005AB6C710561FAAD1950785CDDA9A0D
         */

        public String appid;
        public String partnerid;
        public String prepayid;
        @SerializedName("package")
        public String packageX;
        public String noncestr;
        public String timestamp;
        public String sign;
        public String payed;
        public String orderString;
        public String orderform_id;
    }
}
