package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * @author admin
 * @date 2017/12/18
 */

public class CouponListModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"count":2,"list":[{"id":"106","user_id":"82","price":"100","start_time":"1513221297","end_time":"1524331297","created_time":"0","status":"1"},{"id":"107","user_id":"82","price":"100","start_time":"1513221297","end_time":"1523221297","created_time":"0","status":"1"}]}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel<CouponListModel.ContentBean> {
        /**
         * count : 2
         * list : [{"id":"106","user_id":"82","price":"100","start_time":"1513221297","end_time":"1524331297","created_time":"0","status":"1"},{"id":"107","user_id":"82","price":"100","start_time":"1513221297","end_time":"1523221297","created_time":"0","status":"1"}]
         */

        public int count;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 106
             * user_id : 82
             * price : 100
             * start_time : 1513221297
             * end_time : 1524331297
             * created_time : 0
             * status : 1
             */

            public String id;
            public String user_id;
            public String price;
            public String start_time;
            public String end_time;
            public String created_time;
            public String status;
        }
    }
}
