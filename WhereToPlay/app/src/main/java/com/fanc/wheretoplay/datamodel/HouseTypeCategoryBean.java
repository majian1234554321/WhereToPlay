package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by peace on 2017/10/25.
 */

public class HouseTypeCategoryBean extends BaseModel {

    /**
     * content : {"room":[{"id":"3","name":"大包","mans":"6-8","min_price":"2980","allow_number":"2","audio":"DMX","other":"无"}]}
     */

    private ContentBean content;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private List<RoomBean> room;

        public List<RoomBean> getRoom() {
            return room;
        }

        public void setRoom(List<RoomBean> room) {
            this.room = room;
        }

        public static class RoomBean {
            /**
             * id : 3
             * name : 大包
             * mans : 6-8
             * min_price : 2980
             * allow_number : 2
             * audio : DMX
             * other : 无
             */

            private String id;
            private String name;
            private String mans;
            private String min_price;
            private String allow_number;
            private String audio;
            private String other;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMans() {
                return mans;
            }

            public void setMans(String mans) {
                this.mans = mans;
            }

            public String getMin_price() {
                return min_price;
            }

            public void setMin_price(String min_price) {
                this.min_price = min_price;
            }

            public String getAllow_number() {
                return allow_number;
            }

            public void setAllow_number(String allow_number) {
                this.allow_number = allow_number;
            }

            public String getAudio() {
                return audio;
            }

            public void setAudio(String audio) {
                this.audio = audio;
            }

            public String getOther() {
                return other;
            }

            public void setOther(String other) {
                this.other = other;
            }
        }
    }
}
