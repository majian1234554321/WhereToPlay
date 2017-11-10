package com.fanc.wheretoplay.datamodel;

import java.util.List;

/**
 * Created by admin on 2017/11/2.
 */

public class BookListModel   {

    /**
     * code : 0
     * message : 请求成功
     * content : {"list":[{"order_id":"210","store_id":"4","name":"上海新缤纷年代","arrival_time":"1509573600","book_sn":"171031010100156947","book_type":"2","room_type":"小包","decorate_type":"现代","prepay":"0.00","total":"0.00","created_time":"1509426060","status":"5","consume_again":"1","leave_money":"0.00","is_comment":"0","is_display":"1","cover":"/Public/pcuser/images/default_rect.png"}]}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        public List<ListBean> list;

        public static class ListBean {
            /**
             * order_id : 210
             * store_id : 4
             * name : 上海新缤纷年代
             * arrival_time : 1509573600
             * book_sn : 171031010100156947
             * book_type : 2
             * room_type : 小包
             * decorate_type : 现代
             * prepay : 0.00
             * total : 0.00
             * created_time : 1509426060
             * order_action : 5
             * consume_again : 1
             * leave_money : 0.00
             * is_comment : 0
             * is_display : 1
             * cover : http://ktv.51tzl.cn/Public/pcuser/images/default_rect.png
             */

            public String order_id;
            public String store_id;
            public String name;
            public String arrival_time;
            public String book_sn;
            public String book_type;
            public String room_type;
            public String decorate_type;
            public String prepay;
            public String total;
            public String created_time;
            public String order_action;
            public String consume_again;
            public String leave_money;
            public String is_comment;
            public String is_display;
            public String cover;
        }
    }
}
