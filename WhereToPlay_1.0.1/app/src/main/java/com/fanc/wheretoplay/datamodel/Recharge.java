package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Recharge extends BaseModel {

    private List<RechargeDetail> list;

    public List<RechargeDetail> getList() {
        return list;
    }

    public void setList(List<RechargeDetail> list) {
        this.list = list;
    }

    public static class RechargeDetail {
        /**
         * id：string，消费明细的id，
         * balance: string, 充值的金额，单位元
         * balance_type: string, 充值的类型，1-支付宝2-微信
         * created_time:充值时间
         */

        private String id;
        private String balance;
        private String balance_type;
        private String created_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBalance_type() {
            return balance_type;
        }

        public void setBalance_type(String balance_type) {
            this.balance_type = balance_type;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }
    }
}
