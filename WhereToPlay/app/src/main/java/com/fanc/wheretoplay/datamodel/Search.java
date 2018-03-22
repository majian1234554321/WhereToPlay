package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Search extends BaseModel {

    public List<Store> list;

    public static class Store {
        /**
         * id: string, 商铺ID
         * name: string, 店铺名字
         */

        public String id;
        public String name;
        public String style;
    }
}
