package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Filter extends BaseModel {

    private ArrayList<FilterBean> style;// 店家风格
    private List<FilterBean> room;// 房型
    private List<FilterBean> decorate;// 装修风格
    private List<FilterBean> grade;// 装修档次
    private List<FilterBean> activity;// 活动专场
    private List<FilterBean> discount;// 优惠折扣

    public void setStyle(ArrayList<FilterBean> style) {
        this.style = style;
    }

    public void setRoom(List<FilterBean> room) {
        this.room = room;
    }

    public void setDecorate(List<FilterBean> decorate) {
        this.decorate = decorate;
    }

    public void setGrade(List<FilterBean> grade) {
        this.grade = grade;
    }

    public void setActivity(List<FilterBean> activity) {
        this.activity = activity;
    }

    public void setDiscount(List<FilterBean> discount) {
        this.discount = discount;
    }

    public List<FilterBean> getStyle() {
        return style;
    }

    public List<FilterBean> getRoom() {
        return room;
    }

    public List<FilterBean> getDecorate() {
        return decorate;
    }

    public List<FilterBean> getGrade() {
        return grade;
    }

    public List<FilterBean> getActivity() {
        return activity;
    }

    public List<FilterBean> getDiscount() {
        return discount;
    }

    public static class FilterBean {

        /**
         * id : 1
         * name : KTV
         */

        private String id;
        private String name;

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
    }
}
