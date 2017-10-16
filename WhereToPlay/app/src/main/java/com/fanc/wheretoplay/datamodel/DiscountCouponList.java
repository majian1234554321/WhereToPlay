package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DiscountCouponList extends BaseModel {

    private List<DiscountCoupon> list;

    public List<DiscountCoupon> getList() {
        return list;
    }

    public void setList(List<DiscountCoupon> list) {
        this.list = list;
    }

    public static class DiscountCoupon {
        /**
         * id：string，优惠券的id，
         * user_id: string, 用户的id，
         * price: string, 优惠券的金额，单位元，
         * start_time: string，优惠券有效期开始时间，时间戳
         * end_time:string, 优惠券有效器结束时间，时间戳
         * created_time：创建的时间
         * status：string，优惠卷状态 0-禁用|1-正常(未使用)|2-已使用
         */

        private String id;
        private String user_id;
        private String price;
        private String start_time;
        private String end_time;
        private String created_time;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
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
    }
}
