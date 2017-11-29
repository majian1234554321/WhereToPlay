package com.fanc.wheretoplay.datamodel;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by admin on 2017/11/3.
 */

public class OrderDetailModel {


    /**
     * code : 0
     * message : 请求成功
     * content : {"discount":"7","store_name":"上海新缤纷年代","address":"上海市静安区南京西路580号-b座-908室","order_action":"9","order_name":"ddddd","mobile":"17601386386","name":"小包","arrival_time":"1509573600","car_num":"1","people_num":"1","action":"5","total":"0.00","remark":"","order_sn":"171109053050670607","created_time":"1509426060","reserve_time":null,"finish_time":"1506322519","phone":"4008006666","number":null,"statusdesc":"代付款","buttonlist":[{"buttonid":"1","title":"立即支付"},{"buttonid":"0","title":"取消订单"}]}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * discount : 7
         * store_name : 上海新缤纷年代
         * address : 上海市静安区南京西路580号-b座-908室
         * order_action : 9
         * order_name : ddddd
         * mobile : 17601386386
         * name : 小包
         * arrival_time : 1509573600
         * car_num : 1
         * people_num : 1
         * action : 5
         * total : 0.00
         * remark :
         * order_sn : 171109053050670607
         * created_time : 1509426060
         * reserve_time : null
         * finish_time : 1506322519
         * phone : 4008006666
         * number : null
         * statusdesc : 代付款
         * buttonlist : [{"buttonid":"1","title":"立即支付"},{"buttonid":"0","title":"取消订单"}]
         */

        public String discount;
        public String store_name;
        public String address;
        public String order_action;
        public String order_name;
        public String mobile;
        public String name;
        public String arrival_time;
        public String car_num;
        public String people_num;
        @Nullable
        public String action;
        public String total;
        public String remark;
        public String order_sn;
        public String created_time;
        public String reserve_time;
        public String finish_time;
        public String phone;
        public String number;
        public String statusdesc;
        public List<ButtonlistBean> buttonlist;
        public String book_price;
        public String prepay;

        public static class ButtonlistBean {
            /**
             * buttonid : 1
             * title : 立即支付
             */

            public String buttonid;
            public String title;
        }
    }
}
