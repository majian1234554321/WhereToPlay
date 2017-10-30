package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by peace on 2017/10/25.
 */

public class HousenewsList extends BaseModel {
    /**
     * content : {"status":[{"name":"小包","number":"101","min_price":"2280","status":"2"}]}
     */
    private List<StatusBean> status;

    public List<StatusBean> getStatus() {
        return status;
    }

    public void setStatus(List<StatusBean> status) {
        this.status = status;
    }

    public static class StatusBean extends BaseObservable {
        /**
         * name : 小包
         * number : 101
         * min_price : 2280
         * status : 2
         */

        private String name;
        private String number;
        private String min_price;
        private String status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
