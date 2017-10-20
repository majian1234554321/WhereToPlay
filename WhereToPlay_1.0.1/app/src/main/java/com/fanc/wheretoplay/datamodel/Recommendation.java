package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class Recommendation extends BaseModel {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * name : 酒吧
         */

        public String name;
    }
}
