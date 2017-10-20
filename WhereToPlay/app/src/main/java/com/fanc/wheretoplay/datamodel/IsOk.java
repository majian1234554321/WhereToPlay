package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/4.
 */

public class IsOk extends BaseModel {
    private boolean is_ok;

    private boolean result;// 结果
    private String info;// 描述信息

    private boolean is_cancle;// 订单取消/删除是否成功
    private boolean is_delete;
    private boolean payed;// 余额

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public void setIs_ok(boolean is_ok) {
        this.is_ok = is_ok;
    }

    public boolean isIs_ok() {
        return is_ok;
    }

    public boolean isResult() {
        return result;
    }

    public String getInfo() {
        return info;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setIs_cancle(boolean is_cancle) {
        this.is_cancle = is_cancle;
    }

    public boolean isIs_cancle() {
        return is_cancle;
    }
}
