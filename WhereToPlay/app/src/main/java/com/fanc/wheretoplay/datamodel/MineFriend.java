package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by peace on 2017/11/10.
 */

public class MineFriend extends BaseModel {


    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * nickname : name1
         * pic : http://ktv.51tzl.cn/Public/head_icon.png
         * regtime : 1506079634
         */

        private String nickname;
        private String pic;
        public String regtime;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }


    }
}
