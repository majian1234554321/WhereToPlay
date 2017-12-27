package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * Created by admin on 2017/11/3.
 */

public class OrderDetailModel extends BaseResponseModel {


    /**
     * code : 0
     * message : 请求成功
     * content : {"discount":"8.6","store_name":"LIGHT","address":"上海徐汇区2258号","order_action":"4","order_name":"","mobile":"17601386386","name":null,"arrival_time":"0","car_num":"0","people_num":"0","action":"6","order_function":"6","total":"1.00","remark":"","order_sn":"171222055930504820","created_time":"1513936770","reserve_time":null,"finish_time":"1513936780","phone":"13564714856","book_price":null,"prepay":"0.00","package_id":"28","package_end_time":"0","store_id":"18","number":null,"statusdesc":"已结单","buttonlist":[{"buttonid":"3","title":"立即评论"},{"buttonid":"5","title":"返回首页"}],"package_detail":{"id":"28","name":"测试的","cover":"/Upload/package/2017/12/15138355595a3b4c279d4b1.jpeg","origin_price":999,"discount_price":1,"introduce":"仅售13元，价值999元测试的套餐，不限时段通用!","detail":"加防腐剂肌肤","buy_notice":{"booking_info":"加防腐剂","effect_date":"加到几点到家","rule_remind":"加防腐剂家","tip":"1.  滴滴客服客服参加课程教材反馈\n2.（看看反馈反馈方法看看反馈反馈发3.  加方法看看反馈方法美女呢\n4.  看反馈反馈\n5.  反馈反馈反馈看\n6.  度假酒店扩大开放\n\n"},"create_time":"1513835559","effect_start_time":"0","effect_end_time":"0","status":"1","pic_list":["http://testapi.51tzl.cn/Upload/package/2017/12/15138355595a3b4c279ddb4.jpeg"],"product_list":[{"num":"41到家","name":"此次","price":74,"unit":"到家"}]},"store":{"phone":"13564714856","address":"上海徐汇区2258号","distance":"-1"}}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel {
        /**
         * discount : 8.6
         * store_name : LIGHT
         * address : 上海徐汇区2258号
         * order_action : 4
         * order_name :
         * mobile : 17601386386
         * name : null
         * arrival_time : 0
         * car_num : 0
         * people_num : 0
         * action : 6
         * order_function : 6
         * total : 1.00
         * remark :
         * order_sn : 171222055930504820
         * created_time : 1513936770
         * reserve_time : null
         * finish_time : 1513936780
         * phone : 13564714856
         * book_price : null
         * prepay : 0.00
         * package_id : 28
         * package_end_time : 0
         * store_id : 18
         * number : null
         * statusdesc : 已结单
         * buttonlist : [{"buttonid":"3","title":"立即评论"},{"buttonid":"5","title":"返回首页"}]
         * package_detail : {"id":"28","name":"测试的","cover":"/Upload/package/2017/12/15138355595a3b4c279d4b1.jpeg","origin_price":999,"discount_price":1,"introduce":"仅售13元，价值999元测试的套餐，不限时段通用!","detail":"加防腐剂肌肤","buy_notice":{"booking_info":"加防腐剂","effect_date":"加到几点到家","rule_remind":"加防腐剂家","tip":"1.  滴滴客服客服参加课程教材反馈\n2.（看看反馈反馈方法看看反馈反馈发3.  加方法看看反馈方法美女呢\n4.  看反馈反馈\n5.  反馈反馈反馈看\n6.  度假酒店扩大开放\n\n"},"create_time":"1513835559","effect_start_time":"0","effect_end_time":"0","status":"1","pic_list":["http://testapi.51tzl.cn/Upload/package/2017/12/15138355595a3b4c279ddb4.jpeg"],"product_list":[{"num":"41到家","name":"此次","price":74,"unit":"到家"}]}
         * store : {"phone":"13564714856","address":"上海徐汇区2258号","distance":"-1"}
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
        public String action;
        public String order_function;
        public String total;
        public String remark;
        public String order_sn;
        public String created_time;
        public String reserve_time;
        public String finish_time;
        public String phone;
        public String book_price;
        public String prepay;
        public String package_id;
        public String package_end_time;
        public String store_id;
        public String number;
        public String statusdesc;
        public PackageDetailBean package_detail;
        public StoreBean store;
        public List<ButtonlistBean> buttonlist;

        public static class PackageDetailBean {
            /**
             * id : 28
             * name : 测试的
             * cover : /Upload/package/2017/12/15138355595a3b4c279d4b1.jpeg
             * origin_price : 999
             * discount_price : 1
             * introduce : 仅售13元，价值999元测试的套餐，不限时段通用!
             * detail : 加防腐剂肌肤
             * buy_notice : {"booking_info":"加防腐剂","effect_date":"加到几点到家","rule_remind":"加防腐剂家","tip":"1.  滴滴客服客服参加课程教材反馈\n2.（看看反馈反馈方法看看反馈反馈发3.  加方法看看反馈方法美女呢\n4.  看反馈反馈\n5.  反馈反馈反馈看\n6.  度假酒店扩大开放\n\n"}
             * create_time : 1513835559
             * effect_start_time : 0
             * effect_end_time : 0
             * status : 1
             * pic_list : ["http://testapi.51tzl.cn/Upload/package/2017/12/15138355595a3b4c279ddb4.jpeg"]
             * product_list : [{"num":"41到家","name":"此次","price":74,"unit":"到家"}]
             */

            public String id;
            public String name;
            public String cover;
            public String origin_price;
            public String discount_price;
            public String introduce;
            public String detail;
            public BuyNoticeBean buy_notice;
            public String create_time;

            public String effect_start_time;
            public String effect_end_time;
            public String status;
            public List<String> pic_list;
            public List<ProductListBean> product_list;

            public static class BuyNoticeBean {
                /**
                 * booking_info : 加防腐剂
                 * effect_date : 加到几点到家
                 * rule_remind : 加防腐剂家
                 * tip : 1.  滴滴客服客服参加课程教材反馈
                 2.（看看反馈反馈方法看看反馈反馈发3.  加方法看看反馈方法美女呢
                 4.  看反馈反馈
                 5.  反馈反馈反馈看
                 6.  度假酒店扩大开放


                 */

                public String booking_info;
                public String effect_date;
                public String rule_remind;
                public String effect_date_desc;
                public String tip;
            }

            public static class ProductListBean {
                /**
                 * num : 41到家
                 * name : 此次
                 * price : 74
                 * unit : 到家
                 */

                public String num;
                public String name;
                public String price;
                public String unit;
            }
        }

        public static class StoreBean {
            /**
             * phone : 13564714856
             * address : 上海徐汇区2258号
             * distance : -1
             */

            public String phone;
            public String address;
            public String distance;
        }

        public static class ButtonlistBean {
            /**
             * buttonid : 3
             * title : 立即评论
             */

            public String buttonid;
            public String title;
        }
    }
}
