package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.rx.BaseResponseModel;

import java.util.List;

/**
 * @author admin
 * @date 2017/12/24
 */

public class StoreListModel {

    /**
     * code : 0
     * message : 请求成功
     * content : {"sliders":["http://oz3cqe2qe.bkt.clouddn.com/20171128101446_82853.png-origin","http://oz3cqe2qe.bkt.clouddn.com/20171128101424_47925.png-origin","http://oz3cqe2qe.bkt.clouddn.com/20171128101403_56267.png-origin","http://oz3cqe2qe.bkt.clouddn.com/20171128101250_39005.png-origin","http://oz3cqe2qe.bkt.clouddn.com/20171128101015_57677.png-origin"],"store":[{"id":"18","name":"LIGHT","area":"徐汇区","discount":"8.6","capita":"866","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin"},{"id":"17","name":"BM101酒吧","area":"浦东新区","discount":"8.6","capita":"588","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127181147_32829.jpg-origin"},{"id":"16","name":"MD酒吧","area":"青浦区","discount":"8.6","capita":"388","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171128111452_66284.jpg-origin"},{"id":"15","name":"菲比酒吧（青浦店）","area":"青浦区","discount":"8.6","capita":"800","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171128111554_29761.jpg-origin"},{"id":"14","name":"MYST酒吧","area":"静安区","discount":"8.8","capita":"1500","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127150531_71053.jpg-origin"},{"id":"13","name":"S2酒吧","area":"黄浦区","discount":"8.8","capita":"1000","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127130744_98962.jpg-origin"},{"id":"12","name":"Diva酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171127132231_73890.jpg-origin"},{"id":"11","name":"Muse2","area":"卢湾区","discount":"8.8","capita":"1088","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171128111352_75442.jpg-origin"},{"id":"10","name":"Linx酒吧","area":"卢湾区","discount":"8.8","capita":"1","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171128110901_36299.jpg-origin"},{"id":"9","name":"Sky酒吧","area":"静安区","discount":"8.8","capita":"1000","distance":"-1","cover":"http://oz3cqe2qe.bkt.clouddn.com/20171128111054_42732.jpg-origin"}]}
     */

    public int code;
    public String message;
    public ContentBean content;

    public static class ContentBean extends BaseResponseModel<StoreListModel.ContentBean> {
        public List<String> sliders;
        public List<StoreBean> store;

        public static class StoreBean  {
            /**
             * id : 18
             * name : LIGHT
             * area : 徐汇区
             * discount : 8.6
             * capita : 866
             * distance : -1
             * cover : http://oz3cqe2qe.bkt.clouddn.com/20171128111938_82528.jpg-origin
             */

            public String id;
            public String name;
            public String area;
            public String discount;
            public String capita;
            public String distance;
            public String cover;
        }
    }
}
