package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by peace on 2017/11/17.
 */

public class NewUser extends BaseModel {

    /**
     * "code": 50002,
     "message": "手机号码已注册",
     * content : {"id":"29","pid":"20","mobile":"13526505114","created_time":"1497321970","status":"1","share_code":"29593f51","token":"eyJpZCI6IjI5Iiwibm9uY2UiOiJFdlhLcUZvTCIsInNoYXJlX2NvZGUiOiIyOTU5M2Y1MSJ9"}
     */

    private User content;

    public User getUser() {
        return content;
    }

    public void setUser(User content) {
        this.content = content;
    }

    public static class User {
        /**
         * id : 用户ID
         * pid : 用户ID的父级ID
         * mobile : 手机号码
         * created_time : 时间戳，用户创建时间
         * status : 用户状态，枚举值，0-禁用|1-正常，这里只会是1，为0时接口会产生50001错误
         * share_code : 用户的邀请码，已用户ID开始的字符串
         * token : 令牌，请求其它接口时需要
         */

        private String id;
        private String pid;
        private String mobile;
        private String created_time;
        private String status;
        private String share_code;
        private String token;
        private String avatar;//头像
        private String nickname;// 昵称
        private String gender;// 性别
        private String birthday;// 生日
        private String registered;// 所在地,
        private String signature;// 签名

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShare_code() {
            return share_code;
        }

        public void setShare_code(String share_code) {
            this.share_code = share_code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public String getGender() {
            return gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getRegistered() {
            return registered;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setRegistered(String registered) {
            this.registered = registered;
        }
    }
}
