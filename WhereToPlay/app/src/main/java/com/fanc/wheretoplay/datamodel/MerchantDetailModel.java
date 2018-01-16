package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author admin
 * @date 2017/12/24
 */

public class MerchantDetailModel {


    /**
     * code : 0
     * message : 请求成功
     * content : {"store":{"store_id":"18","name":"LIGHT","branch":"","remark":"Api/Html/store_remark/store_id/18","level":"3","phone":"13564714856","address":"上海徐汇区2258号","discount":"8.6","distance":"-1","lng":"121.388022","lat":"31.213444","picture":[{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"2","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"3","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"4","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"5","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/15119392705a1e5cc654006.jpeg-origin","type":"6","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/15119392705a1e5cc654006.jpeg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111840_33684.jpg-origin","type":"7","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111840_33684.jpg-thumb"}],"number":"4","comment":[{"comment_id":"52","avatar":"/Upload/user/avatar/images/20171129/20171129142509_16554.jpg"},{"comment_id":"51","avatar":"/Public/head_icon.png"},{"comment_id":"50","avatar":"/Public/head_icon.png"}],"url":"Api/Html/share_store/store_id/18","list":[{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","lng":"121.449306","lat":"31.242995","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127134019_34672.jpg-origin"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1","distance":"-1","lng":"121.264561","lat":"31.368141","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127133711_67262.jpg-origin"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","lng":"121.498626","lat":"31.236310","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127132725_42309.jpg-origin"}],"package":{"count":5,"pkg_list":[{"id":1,"name":"测试套餐1","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":2,"name":"测试套餐2","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":3,"name":"测试套餐3","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":4,"name":"测试套餐4","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":5,"name":"测试套餐5","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"}]}}}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel<MerchantDetailModel.ContentBean> {
        /**
         * store : {"store_id":"18","name":"LIGHT","branch":"","remark":"Api/Html/store_remark/store_id/18","level":"3","phone":"13564714856","address":"上海徐汇区2258号","discount":"8.6","distance":"-1","lng":"121.388022","lat":"31.213444","picture":[{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"2","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"3","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"4","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"5","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/15119392705a1e5cc654006.jpeg-origin","type":"6","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/15119392705a1e5cc654006.jpeg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111840_33684.jpg-origin","type":"7","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111840_33684.jpg-thumb"}],"number":"4","comment":[{"comment_id":"52","avatar":"/Upload/user/avatar/images/20171129/20171129142509_16554.jpg"},{"comment_id":"51","avatar":"/Public/head_icon.png"},{"comment_id":"50","avatar":"/Public/head_icon.png"}],"url":"Api/Html/share_store/store_id/18","list":[{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","lng":"121.449306","lat":"31.242995","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127134019_34672.jpg-origin"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1","distance":"-1","lng":"121.264561","lat":"31.368141","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127133711_67262.jpg-origin"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","lng":"121.498626","lat":"31.236310","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127132725_42309.jpg-origin"}],"package":{"count":5,"pkg_list":[{"id":1,"name":"测试套餐1","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":2,"name":"测试套餐2","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":3,"name":"测试套餐3","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":4,"name":"测试套餐4","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":5,"name":"测试套餐5","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"}]}}
         */

        public StoreBean store;

        public static class StoreBean {
            /**
             * store_id : 18
             * name : LIGHT
             * branch :
             * remark : Api/Html/store_remark/store_id/18
             * level : 3
             * phone : 13564714856
             * address : 上海徐汇区2258号
             * discount : 8.6
             * distance : -1
             * lng : 121.388022
             * lat : 31.213444
             * picture : [{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"2","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"3","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"4","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin","type":"5","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/15119392705a1e5cc654006.jpeg-origin","type":"6","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/15119392705a1e5cc654006.jpeg-thumb"},{"picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111840_33684.jpg-origin","type":"7","thumb_picture_path":"http://oz3cqe2qe.bkt.clouddn.com/20171128111840_33684.jpg-thumb"}]
             * number : 4
             * comment : [{"comment_id":"52","avatar":"/Upload/user/avatar/images/20171129/20171129142509_16554.jpg"},{"comment_id":"51","avatar":"/Public/head_icon.png"},{"comment_id":"50","avatar":"/Public/head_icon.png"}]
             * url : Api/Html/share_store/store_id/18
             * list : [{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","lng":"121.449306","lat":"31.242995","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127134019_34672.jpg-origin"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1","distance":"-1","lng":"121.264561","lat":"31.368141","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127133711_67262.jpg-origin"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","lng":"121.498626","lat":"31.236310","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127132725_42309.jpg-origin"}]
             * package : {"count":5,"pkg_list":[{"id":1,"name":"测试套餐1","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":2,"name":"测试套餐2","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":3,"name":"测试套餐3","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":4,"name":"测试套餐4","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":5,"name":"测试套餐5","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"}]}
             */

            public String is_immediate_pay;
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
            @SerializedName("package")
            public PackageBean packageX;
            public List<PictureBean> picture;
            public List<CommentBean> comment;
            public List<ListBean> list;

            public static class PackageBean {
                /**
                 * count : 5
                 * pkg_list : [{"id":1,"name":"测试套餐1","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":2,"name":"测试套餐2","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":3,"name":"测试套餐3","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":4,"name":"测试套餐4","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"},{"id":5,"name":"测试套餐5","pic":"http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb","origin_price":"3580","discount_price":"2580"}]
                 */

                public int count;
                public List<PkgListBean> pkg_list;

                public static class PkgListBean {
                    /**
                     * id : 1
                     * name : 测试套餐1
                     * pic : http://oz3cqe2qe.bkt.clouddn.com//Upload/user/avatar/images/20171218/20171218092711_21524.jpg-thumb
                     * origin_price : 3580
                     * discount_price : 2580
                     */

                    public String id;
                    public String name;
                    public String pic;
                    public String origin_price;
                    public String discount_price;
                }
            }

            public static class PictureBean {
                /**
                 * picture_path : http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin
                 * type : 2
                 * thumb_picture_path : http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-thumb
                 */

                public String picture_path;
                public String type;
                public String thumb_picture_path;
            }

            public static class CommentBean {
                /**
                 * comment_id : 52
                 * avatar : /Upload/user/avatar/images/20171129/20171129142509_16554.jpg
                 */

                public String comment_id;
                public String avatar;
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
                 * cover : http://oz3cqe2qe.bkt.clouddn.com/20171127134019_34672.jpg-origin
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
