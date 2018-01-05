package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * @author admin
 * @date 2018/1/4
 */

public class BookPackageDetailModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"room_id":"1","room_name":"小包(3~4人)","name":"啤酒套餐测试","price":"0.02","introduce":"仅售元，价值元啤酒套餐测试套餐，不限时段通用!","time_length":"6","book_package_id":"21","detail":"测试","buy_notice":{"booking_info":"请提前一天预约","effect_date":"2017-12-28至2018-05-02","effect_date_desc":"2017-12-28至2018-05-02","rule_remind":"\r如需团购券发票，请您在消费时向商户咨询\r为了保障您的权益，建议使用乐互网线上支付。若使用其他支付方式导致纠纷，乐互网不承担任何责任，感谢您的理解和支持！","tip":"\r如需团购券发票，请您在消费时向商户咨询\r为了保障您的权益，建议使用乐互网线上支付。若使用其他支付方式导致纠纷，乐互网不承担任何责任，感谢您的理解和支持！"},"origin_price":"100.00","discount_price":"0.02","startime":"36000","endtime":"64800","effect_start_time":"1514390400","effect_end_time":"1525190400","pic_list":["http://testapi.51tzl.cn/Upload/package/2018/01/15148738535a4b23fd61d6a.jpeg","http://testapi.51tzl.cn/Upload/package/2018/01/15148735365a4b22c0077d2.jpeg","http://testapi.51tzl.cn/Upload/package/2018/01/15148738735a4b2411c6b1f.jpeg"],"product_list":[{"num":"1份","name":"测试1","price":258,"unit":"份"}],"time_list":["18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30","00:00","00:30","01:00","01:30","02:00"]}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel<BookPackageDetailModel.ContentBean> {
        /**
         * room_id : 1
         * room_name : 小包(3~4人)
         * name : 啤酒套餐测试
         * price : 0.02
         * introduce : 仅售元，价值元啤酒套餐测试套餐，不限时段通用!
         * time_length : 6
         * book_package_id : 21
         * detail : 测试
         * buy_notice : {"booking_info":"请提前一天预约","effect_date":"2017-12-28至2018-05-02","effect_date_desc":"2017-12-28至2018-05-02","rule_remind":"\r如需团购券发票，请您在消费时向商户咨询\r为了保障您的权益，建议使用乐互网线上支付。若使用其他支付方式导致纠纷，乐互网不承担任何责任，感谢您的理解和支持！","tip":"\r如需团购券发票，请您在消费时向商户咨询\r为了保障您的权益，建议使用乐互网线上支付。若使用其他支付方式导致纠纷，乐互网不承担任何责任，感谢您的理解和支持！"}
         * origin_price : 100.00
         * discount_price : 0.02
         * startime : 36000
         * endtime : 64800
         * effect_start_time : 1514390400
         * effect_end_time : 1525190400
         * pic_list : ["http://testapi.51tzl.cn/Upload/package/2018/01/15148738535a4b23fd61d6a.jpeg","http://testapi.51tzl.cn/Upload/package/2018/01/15148735365a4b22c0077d2.jpeg","http://testapi.51tzl.cn/Upload/package/2018/01/15148738735a4b2411c6b1f.jpeg"]
         * product_list : [{"num":"1份","name":"测试1","price":258,"unit":"份"}]
         * time_list : ["18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30","00:00","00:30","01:00","01:30","02:00"]
         */

        public String room_id;
        public String room_name;
        public String name;
        public String price;
        public String introduce;
        public String time_length;
        public String book_package_id;
        public String detail;
        public BuyNoticeBean buy_notice;
        public String origin_price;
        public String discount_price;
        public String startime;
        public String endtime;
        public String effect_start_time;
        public String effect_end_time;
        public List<String> pic_list;
        public List<ProductListBean> product_list;
        public List<String> time_list;

        public static class BuyNoticeBean {
            /**
             * booking_info : 请提前一天预约
             * effect_date : 2017-12-28至2018-05-02
             * effect_date_desc : 2017-12-28至2018-05-02
             * rule_remind : 如需团购券发票，请您在消费时向商户咨询为了保障您的权益，建议使用乐互网线上支付。若使用其他支付方式导致纠纷，乐互网不承担任何责任，感谢您的理解和支持！
             * tip : 如需团购券发票，请您在消费时向商户咨询为了保障您的权益，建议使用乐互网线上支付。若使用其他支付方式导致纠纷，乐互网不承担任何责任，感谢您的理解和支持！
             */

            public String booking_info;
            public String effect_date;
            public String effect_date_desc;
            public String rule_remind;
            public String tip;
        }

        public static class ProductListBean {
            /**
             * num : 1份
             * name : 测试1
             * price : 258
             * unit : 份
             */



            public String num;
            public String name;
            public String price;
            public String unit;

            public ProductListBean(String num, String name, String price, String unit) {
                this.num = num;
                this.name = name;
                this.price = price;
                this.unit = unit;
            }
        }
    }
}
