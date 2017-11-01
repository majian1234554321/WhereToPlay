package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by peace on 2017/10/31.
 */

public class CheckComments extends BaseModel {


    /**
     * content : {"comment_environment":"4.8","comment_atmosphere":"4.9","comment_server":"5.0","average_comment":"4.9","all_count":"1000","pleasure_count":"990","displeasure_count":"10","picture_count":"600","comment_list":[{"id":"1","nickname":"hoye","avatar":"/Upload/user/avatar/images/20171017/20171017132806_22312.jpg","average_comment":"9","comment":"服务态度棒","created_time":"1501558168","picture":["/Upload/image/20170922/20170922205941_74305.jpg","/Upload/image/20170922/20170922205941_74305.jpg","/Upload/image/20170922/20170922205950_33240.jpg","/Upload/image/20170922/20170922210006_79122.jpg"]}]}
     */
    /**
     * comment_environment : 4.8
     * comment_atmosphere : 4.9
     * comment_server : 5.0
     * average_comment : 4.9
     * all_count : 1000
     * pleasure_count : 990
     * displeasure_count : 10
     * picture_count : 600
     * comment_list : [{"id":"1","nickname":"hoye","avatar":"/Upload/user/avatar/images/20171017/20171017132806_22312.jpg","average_comment":"9","comment":"服务态度棒","created_time":"1501558168","picture":["/Upload/image/20170922/20170922205941_74305.jpg","/Upload/image/20170922/20170922205941_74305.jpg","/Upload/image/20170922/20170922205950_33240.jpg","/Upload/image/20170922/20170922210006_79122.jpg"]}]
     */

    private String comment_environment;
    private String comment_atmosphere;
    private String comment_server;
    private String average_comment;
    private String all_count;
    private String pleasure_count;
    private String displeasure_count;
    private String picture_count;
    private List<CommentListBean> comment_list;

    public String getComment_environment() {
        return comment_environment;
    }

    public void setComment_environment(String comment_environment) {
        this.comment_environment = comment_environment;
    }

    public String getComment_atmosphere() {
        return comment_atmosphere;
    }

    public void setComment_atmosphere(String comment_atmosphere) {
        this.comment_atmosphere = comment_atmosphere;
    }

    public String getComment_server() {
        return comment_server;
    }

    public void setComment_server(String comment_server) {
        this.comment_server = comment_server;
    }

    public String getAverage_comment() {
        return average_comment;
    }

    public void setAverage_comment(String average_comment) {
        this.average_comment = average_comment;
    }

    public String getAll_count() {
        return all_count;
    }

    public void setAll_count(String all_count) {
        this.all_count = all_count;
    }

    public String getPleasure_count() {
        return pleasure_count;
    }

    public void setPleasure_count(String pleasure_count) {
        this.pleasure_count = pleasure_count;
    }

    public String getDispleasure_count() {
        return displeasure_count;
    }

    public void setDispleasure_count(String displeasure_count) {
        this.displeasure_count = displeasure_count;
    }

    public String getPicture_count() {
        return picture_count;
    }

    public void setPicture_count(String picture_count) {
        this.picture_count = picture_count;
    }

    public List<CommentListBean> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<CommentListBean> comment_list) {
        this.comment_list = comment_list;
    }

    public static class CommentListBean extends BaseObservable {
        /**
         * id : 1
         * nickname : hoye
         * avatar : /Upload/user/avatar/images/20171017/20171017132806_22312.jpg
         * average_comment : 9
         * comment : 服务态度棒
         * created_time : 1501558168
         * picture : ["/Upload/image/20170922/20170922205941_74305.jpg","/Upload/image/20170922/20170922205941_74305.jpg","/Upload/image/20170922/20170922205950_33240.jpg","/Upload/image/20170922/20170922210006_79122.jpg"]
         */

        private String id;
        private String nickname;
        private String avatar;
        private String average_comment;
        private String comment;
        private String created_time;
        private List<String> picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAverage_comment() {
            return average_comment;
        }

        public void setAverage_comment(String average_comment) {
            this.average_comment = average_comment;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public List<String> getPicture() {
            return picture;
        }

        public void setPicture(List<String> picture) {
            this.picture = picture;
        }
    }
}
