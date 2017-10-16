package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Wallet extends BaseModel {
    private String balance;// 余额

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
