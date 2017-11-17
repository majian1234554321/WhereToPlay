package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ScoreList extends BaseModel {


    /**
     * content : {"total":{"score":"1732"},"list":[{"balance":"1.00","finish_time":null,"id":"155","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"153","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"152","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"151","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"150","name":"","score_detail":"0.00"}]}
     */

    public ContentBean content;

    public static class ContentBean {
        /**
         * total : {"score":"1732"}
         * list : [{"balance":"1.00","finish_time":null,"id":"155","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"153","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"152","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"151","name":"","score_detail":"0.00"},{"balance":"1.00","finish_time":null,"id":"150","name":"","score_detail":"0.00"}]
         */

        public TotalBean total;
        public List<ListBean> list;

        public static class TotalBean {
            /**
             * score : 1732
             */

            public String score;
        }

        public static class ListBean {
            /**
             * balance : 1.00
             * finish_time : null
             * id : 155
             * name :
             * score_detail : 0.00
             */

            public String balance;
            public String finish_time;
            public String id;
            public String name;
            public String score_detail;
        }
    }
}
