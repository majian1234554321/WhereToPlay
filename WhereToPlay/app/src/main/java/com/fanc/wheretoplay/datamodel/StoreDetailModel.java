package com.fanc.wheretoplay.datamodel;

import java.util.List;

/**
 * Created by admin on 2017/11/21.
 */

public class StoreDetailModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"store":{"store_id":"15","name":"菲比酒吧（青浦店）","branch":"","remark":"Api/Html/store_remark/store_id/15","level":"4","phone":"021-59829898","address":"青浦区城中北路158号","discount":"8.6","distance":"-1","lng":"121.459896","lat":"31.225125","picture":[{"picture_path":"/Upload/image/20171019/20171019165136_55535.jpg","type":"2"},{"picture_path":"/Upload/image/20171019/20171019165457_71891.jpg","type":"3"},{"picture_path":"/Upload/image/20171019/20171019165506_90444.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165514_30297.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165523_96292.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165534_51724.jpg","type":"4"}],"number":"0","comment":[],"url":"Api/Html/share_store/store_id/15","list":[{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","lng":"121.449306","lat":"31.242995","cover":"/Upload/image/20171018/20171018174832_29258.jpg"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1500","distance":"-1","lng":"121.264561","lat":"31.368141","cover":"/Upload/image/20171018/20171018180810_41619.jpg"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","lng":"121.498626","lat":"31.236310","cover":"/Upload/image/20171019/20171019122210_86583.jpg"}]}}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean {
        /**
         * store : {"store_id":"15","name":"菲比酒吧（青浦店）","branch":"","remark":"Api/Html/store_remark/store_id/15","level":"4","phone":"021-59829898","address":"青浦区城中北路158号","discount":"8.6","distance":"-1","lng":"121.459896","lat":"31.225125","picture":[{"picture_path":"/Upload/image/20171019/20171019165136_55535.jpg","type":"2"},{"picture_path":"/Upload/image/20171019/20171019165457_71891.jpg","type":"3"},{"picture_path":"/Upload/image/20171019/20171019165506_90444.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165514_30297.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165523_96292.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165534_51724.jpg","type":"4"}],"number":"0","comment":[],"url":"Api/Html/share_store/store_id/15","list":[{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","lng":"121.449306","lat":"31.242995","cover":"/Upload/image/20171018/20171018174832_29258.jpg"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1500","distance":"-1","lng":"121.264561","lat":"31.368141","cover":"/Upload/image/20171018/20171018180810_41619.jpg"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","lng":"121.498626","lat":"31.236310","cover":"/Upload/image/20171019/20171019122210_86583.jpg"}]}
         */

        public StoreBean store;

        public static class StoreBean {
            /**
             * store_id : 15
             * name : 菲比酒吧（青浦店）
             * branch :
             * remark : Api/Html/store_remark/store_id/15
             * level : 4
             * phone : 021-59829898
             * address : 青浦区城中北路158号
             * discount : 8.6
             * distance : -1
             * lng : 121.459896
             * lat : 31.225125
             * picture : [{"picture_path":"/Upload/image/20171019/20171019165136_55535.jpg","type":"2"},{"picture_path":"/Upload/image/20171019/20171019165457_71891.jpg","type":"3"},{"picture_path":"/Upload/image/20171019/20171019165506_90444.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165514_30297.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165523_96292.jpg","type":"4"},{"picture_path":"/Upload/image/20171019/20171019165534_51724.jpg","type":"4"}]
             * number : 0
             * comment : []
             * url : Api/Html/share_store/store_id/15
             * list : [{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","lng":"121.449306","lat":"31.242995","cover":"/Upload/image/20171018/20171018174832_29258.jpg"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1500","distance":"-1","lng":"121.264561","lat":"31.368141","cover":"/Upload/image/20171018/20171018180810_41619.jpg"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","lng":"121.498626","lat":"31.236310","cover":"/Upload/image/20171019/20171019122210_86583.jpg"}]
             */

            public String store_id;
            public String name;
            public String branch;
            public String remark;
            public String level;
            public String phone;
            public String address;
            public String discount;
            public String distance;
            public String lng;
            public String lat;
            public String number;
            public String url;
            public List<PictureBean> picture;
            public List<?> comment;
            public List<ListBean> list;

            public static class PictureBean {
                /**
                 * picture_path : /Upload/image/20171019/20171019165136_55535.jpg
                 * type : 2
                 */

                public String picture_path;
                public String type;
            }

            public static class ListBean {
                /**
                 * id : 9
                 * name : Sky酒吧
                 * area : 静安区
                 * discount : 8.8
                 * capita : 1000
                 * distance : -1
                 * lng : 121.449306
                 * lat : 31.242995
                 * cover : /Upload/image/20171018/20171018174832_29258.jpg
                 */

                public String id;
                public String name;
                public String area;
                public String discount;
                public String capita;
                public String distance;
                public String lng;
                public String lat;
                public String cover;
            }
        }
    }
}
