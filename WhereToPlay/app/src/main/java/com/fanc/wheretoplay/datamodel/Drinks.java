package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Drinks extends BaseModel {

    public List<Wine> list;

    public static class Wine {
        /**
         * id : 1
         * name : 酒水
         * price : 120.50
         */

        public String id;
        public String name;
        public String price;
    }
}
