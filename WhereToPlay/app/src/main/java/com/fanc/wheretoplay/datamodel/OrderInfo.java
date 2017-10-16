package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/10.
 */

public class OrderInfo extends BaseModel {

    /**
     * order_info: 订单信息
     */

    public Order order_info;

    public static class Order {
        /**
         * id：string，订单id
         * store_name：string，商店的名称
         * arrival_time：string，到店时间
         * prepay：string，定金金额，元
         * room_type: string, 房间的类型
         */

        public String id;
        public String store_name;
        public String arrival_time;
        public String prepay;
        public String room_type;
    }
}
