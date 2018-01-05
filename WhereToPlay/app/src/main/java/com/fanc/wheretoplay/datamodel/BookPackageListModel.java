package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * @author admin
 * @date 2018/1/4
 */

public class BookPackageListModel {

    /**
     * code : 0
     * message : 请求成功
     * content : [{"id":"1","room_name":"小包(3~4人)","package_list":[{"id":"80","name":"啤酒套餐测试","price":"0.02"}]}]
     */

    public int code;
    public String message;
    public List<ContentBean> content;

    public static class ContentBean  {
        /**
         * id : 1
         * room_name : 小包(3~4人)
         * package_list : [{"id":"80","name":"啤酒套餐测试","price":"0.02"}]
         */

        public String id;
        public String room_name;
        public List<PackageListBean> package_list;

        public static class PackageListBean {
            /**
             * id : 80
             * name : 啤酒套餐测试
             * price : 0.02
             */

            public String id;
            public String name;
            public String price;
        }
    }
}
