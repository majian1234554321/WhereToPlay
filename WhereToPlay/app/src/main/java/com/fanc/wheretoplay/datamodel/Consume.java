package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */
public class Consume extends BaseModel {

    private List<ConsumeDetail> list;

    public List<ConsumeDetail> getList() {
        return list;
    }

    public void setList(List<ConsumeDetail> list) {
        this.list = list;
    }

    public static class ConsumeDetail {
        /**
         * name：string, 商店名称
         * branch: string, 商店的分店名
         * prepay：string, 预付定金，单位元
         * discount: string, 优惠的金额，单位元
         * created_time:string ,订金消费时间
         * reserve_time：string ,订单消费时间
         * account:string,订单消费，单位元
         * total : int ,总计
         */

        private String name;
        private String branch;
        private String prepay;
        private String discount;
        private String account;
        private String created_time;
        private String reserve_time;
        private double total;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getPrepay() {
            return prepay;
        }

        public void setPrepay(String prepay) {
            this.prepay = prepay;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getReserve_time() {
            return reserve_time;
        }

        public void setReserve_time(String reserve_time) {
            this.reserve_time = reserve_time;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}
