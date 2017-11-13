package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by peace on 2017/11/10.
 */

public class MineMoney extends BaseModel {

        /**
         * nickname : name1
         * pic : http://ktv.51tzl.cn/Public/head_icon.png
         * regTime : 1506079634
         * amount : 22
         */

        private String nickname;
        private String pic;
        private String regTime;
        private int amount;

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

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

}
