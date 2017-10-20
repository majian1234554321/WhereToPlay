package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class RoomCategory extends BaseModel {

    public List<Room> room;

    public static class Room {
        /**
         * id : 176
         * name : 豪华大房
         * mans : 5-8人
         * min_price : 999.00
         * allow_number : 10
         * audio : BOSE
         * other : BOSE
         */

        public String id;
        public String name;
        public String mans;
        public String min_price;
        public String allow_number;
        public String audio;
        public String other;
    }
}
