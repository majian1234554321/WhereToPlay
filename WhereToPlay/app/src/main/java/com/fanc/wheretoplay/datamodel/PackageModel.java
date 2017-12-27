package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * @author admin
 * @date 2017/12/24
 */

public class PackageModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"pic_list":["http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-origin","http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-origin","http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-origin"],"name":"测试套餐1","introduce":"测试简介内容测试简介内容测试简介内容","product_list":[{"name":"测试商品1","price":"100","num":"1瓶"},{"name":"测试商品2","price":"200","num":"2份"}],"detail":"①第一条测试第一条测试第一条测试/n②第二条测试第二条测试第二条测试第二条测试第二条测试/n③第三条测试第三条测试第三条测试第三条测试/n④第四条测试第四条测试第四条测试第四条测试第四条测试/n⑤第五条测试第五条测试第五条测试第五条测试第五条测试","buy_notice":{"effect_date":"2017-12-12至2017-12-31","booking_info":"请您提前一天预约","rule_remind":"不再与其他优惠同享","tip":"提示内容测试提示内容测试提示内/n容测试提示内容测试提示内容测试"},"origin_price":"300","discount_price":"220"}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel<PackageModel.ContentBean> {
        /**
         * pic_list : ["http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-origin","http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-origin","http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-origin"]
         * name : 测试套餐1
         * introduce : 测试简介内容测试简介内容测试简介内容
         * product_list : [{"name":"测试商品1","price":"100","num":"1瓶"},{"name":"测试商品2","price":"200","num":"2份"}]
         * detail : ①第一条测试第一条测试第一条测试/n②第二条测试第二条测试第二条测试第二条测试第二条测试/n③第三条测试第三条测试第三条测试第三条测试/n④第四条测试第四条测试第四条测试第四条测试第四条测试/n⑤第五条测试第五条测试第五条测试第五条测试第五条测试
         * buy_notice : {"effect_date":"2017-12-12至2017-12-31","booking_info":"请您提前一天预约","rule_remind":"不再与其他优惠同享","tip":"提示内容测试提示内容测试提示内/n容测试提示内容测试提示内容测试"}
         * origin_price : 300
         * discount_price : 220
         */

        public String name;
        public String introduce;
        public String detail;
        public BuyNoticeBean buy_notice;
        public String origin_price;
        public String discount_price;
        public List<String> pic_list;
        public List<ProductListBean> product_list;

        public static class BuyNoticeBean {
            /**
             * effect_date : 2017-12-12至2017-12-31
             * booking_info : 请您提前一天预约
             * rule_remind : 不再与其他优惠同享
             * tip : 提示内容测试提示内容测试提示内/n容测试提示内容测试提示内容测试
             */

            public String effect_date;
            public String booking_info;
            public String rule_remind;
            public String effect_date_desc;

            public String tip;
        }

        public static class ProductListBean {
            /**
             * name : 测试商品1
             * price : 100
             * num : 1瓶
             */

            public String name;
            public String price;
            public String num;

            public ProductListBean(String name, String price, String num) {
                this.name = name;
                this.price = price;
                this.num = num;
            }
        }
    }
}
