package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/17.
 */

public class OrderDone extends BaseModel {

    /**
     * order : {"id":"220","name":"小泽玛利亚KTV","discount":"7","prepay":"500"}
     */

    public Order order;

    public static class Order {
        /**
         * order_id: string,订单id
         * name : string,店铺名
         * discount: string,折扣
         * prepay : string,订金
         * coupon_id:String,优惠券id
         */

        public String order_id;
        public String name;
        public String discount;
        public String prepay;
        public String coupon_id;
        public String cash_rate;// 服务费率
        public String distance;// 距离
        public String address;// 地址
    }
}
