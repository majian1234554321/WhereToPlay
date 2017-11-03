package com.fanc.wheretoplay.datamodel;

/**
 * Created by DELL on 2017/3/13.
 */

public class LoginResponse_Model {


    /**
     * code : 0
     * message : 请求成功
     * content : {"id":"25","pid":"0","mobile":"17601386386","created_time":"1509328865","status":"1","nonce":"yWfsuOgl","score":"0","avatar":"/Public/head_icon.png","share_code":"2559f687","signature":"","nickname":"","registered":"2623","gender":"0","birthday":"0","token":"eyJpZCI6IjI1Iiwibm9uY2UiOiJ5V2ZzdU9nbCIsInNoYXJlX2NvZGUiOiIyNTU5ZjY4NyJ9"}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * id : 25
         * pid : 0
         * mobile : 17601386386
         * created_time : 1509328865
         * status : 1
         * nonce : yWfsuOgl
         * score : 0
         * avatar : /Public/head_icon.png
         * share_code : 2559f687
         * signature :
         * nickname :
         * registered : 2623
         * gender : 0
         * birthday : 0
         * token : eyJpZCI6IjI1Iiwibm9uY2UiOiJ5V2ZzdU9nbCIsInNoYXJlX2NvZGUiOiIyNTU5ZjY4NyJ9
         */

        public String id;
        public String pid;
        public String mobile;
        public String created_time;
        public String status;
        public String nonce;
        public String score;
        public String avatar;
        public String share_code;
        public String signature;
        public String nickname;
        public String registered;
        public String gender;
        public String birthday;
        public String token;
    }
}
