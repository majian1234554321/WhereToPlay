package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fanc.wheretoplay.BR;
import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class MessageList extends BaseModel {


    private List<Message> list;

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public static class Message extends BaseObservable {
        /**
         * id : 2
         * user_id : 8
         * type : 1
         * title : 预约
         * content : 您已经成功预约世界之光KTV
         * created_time : 1499066581
         * readed : 1
         */

        private String id;
        private String user_id;
        private String type;
        private String title;
        private String content;
        private String created_time;
        private String readed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return user_id;
        }

        public void setUserId(String user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatedTime() {
            return created_time;
        }

        public void setCreatedTime(String created_time) {
            this.created_time = created_time;
        }

        @Bindable
        public String getReaded() {
            return readed;
        }

        public void setReaded(String readed) {
            this.readed = readed;
            notifyPropertyChanged(BR.readed);
        }
    }
}
