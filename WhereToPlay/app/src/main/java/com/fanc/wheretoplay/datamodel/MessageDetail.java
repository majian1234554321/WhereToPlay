package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/4.
 */

public class MessageDetail extends BaseModel {

    /**
     * detail : {"title":"heheheh","content":"hahahaha","picture":"/Upload/user/avatar/images/20170613/20170613174949_63453.jpg"}
     */

    private MessageDetailBean detail;

    public MessageDetailBean getDetail() {
        return detail;
    }

    public void setDetail(MessageDetailBean detail) {
        this.detail = detail;
    }

    public static class MessageDetailBean {
        /**
         * title : heheheh
         * content : hahahaha
         * picture : /Upload/user/avatar/images/20170613/20170613174949_63453.jpg
         */

        private String title;
        private String content;
        private String picture;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
