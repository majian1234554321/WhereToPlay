package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * Created by admin on 2017/11/2.
 */

public class BookListModel {


    /**
     * code : 0
     * message : 请求成功
     * content : {"list":[{"order_id":"492","store_id":"6","name":"上海天地汇会所","arrival_time":"1510308000","book_sn":"171110043239806689","order_action":"5","order_function":"2","room_type":"小包","decorate_type":"新古典","total":"0.00","statusdesc":"待处理","buttonlist":[{"buttonid":"0","title":"取消订单"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"444","store_id":"6","name":"上海天地汇会所","arrival_time":"1510308000","book_sn":"171110103706754060","order_action":"1","order_function":"1","room_type":"小包","decorate_type":"新古典","total":"1.00","statusdesc":"已取消","buttonlist":[{"buttonid":"0","title":"取消订单"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"443","store_id":"6","name":"上海天地汇会所","arrival_time":"1510308000","book_sn":"171110103706166216","order_action":"0","order_function":"1","room_type":"小包","decorate_type":"新古典","total":"0.00","statusdesc":"代付款","buttonlist":[{"buttonid":"1","title":"立即支付"},{"buttonid":"0","title":"取消订单"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"294","store_id":"6","name":"上海天地汇会所","arrival_time":"1509966000","book_sn":"171106044800350091","order_action":"4","order_function":"5","room_type":"小包","decorate_type":"新古典","total":"0.00","statusdesc":"已结单","buttonlist":[{"buttonid":"3","title":"立即评论"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"210","store_id":"4","name":"上海新缤纷年代","arrival_time":"1509573600","book_sn":"171031010100156947","order_action":"4","order_function":"5","room_type":"小包","decorate_type":"现代","total":"0.00","statusdesc":"已结单","buttonlist":[{"buttonid":"3","title":"立即评论"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"186","store_id":"0","name":"","arrival_time":"0","book_sn":"","order_action":"0","order_function":"3","room_type":null,"decorate_type":null,"total":"20.00","statusdesc":"代付款","buttonlist":[{"buttonid":"1","title":"立即支付"},{"buttonid":"0","title":"取消订单"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"185","store_id":"0","name":"","arrival_time":"0","book_sn":"","order_action":"0","order_function":"3","room_type":null,"decorate_type":null,"total":"20.00","statusdesc":"代付款","buttonlist":[{"buttonid":"1","title":"立即支付"},{"buttonid":"0","title":"取消订单"}],"cover":"/Public/pcuser/images/default_rect.png"},{"order_id":"184","store_id":"0","name":"","arrival_time":"0","book_sn":"","order_action":"0","order_function":"3","room_type":null,"decorate_type":null,"total":"20.00","statusdesc":"代付款","buttonlist":[{"buttonid":"1","title":"立即支付"},{"buttonid":"0","title":"取消订单"}],"cover":"/Public/pcuser/images/default_rect.png"}]}
     */

    public String code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel<ContentBean> {
        public List<ListBean> list;

        public static class ListBean {
            /**
             * order_id : 492
             * store_id : 6
             * name : 上海天地汇会所
             * arrival_time : 1510308000
             * book_sn : 171110043239806689
             * order_action : 5
             * order_function : 2
             * room_type : 小包
             * decorate_type : 新古典
             * total : 0.00
             * statusdesc : 待处理
             * buttonlist : [{"buttonid":"0","title":"取消订单"}]
             * cover : /Public/pcuser/images/default_rect.png
             */

            public String address;
            public String order_id;
            public String store_id;
            public String name;
            public String arrival_time;
            public String book_sn;
            public String order_action;
            public String order_function;
            public String room_type;
            public  String room_number;
            public String order_mobile;
            public String order_name;
            public String decorate_type;
            public String total;
            public String statusdesc;
            public String cover;
            public String discount;
            public List<ButtonlistBean> buttonlist;
            public String book_price;
            public String prepay;
            public String package_name;
            public String package_number;
            public String package_end_time;
            public String package_introduce;
            public String origin_price;
            public String finish_time;


            public static class ButtonlistBean {
                /**
                 * buttonid : 0
                 * title : 取消订单
                 */

                public String buttonid;
                public String title;
            }
        }
    }
}
