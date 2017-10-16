package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ScoreList extends BaseModel {

    /**
     * total: string，总积分数
     * list: array，积分明细列表
     */

    private String total;
    private List<Score> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Score> getList() {
        return list;
    }

    public void setList(List<Score> list) {
        this.list = list;
    }

    public static class Score {
        /**
         * id：string，积分明细的id
         * name: string, 店铺的名字
         * score_detail: string, 每一次获得积分数
         */

        private String id;
        private String name;
        private String score_detail;

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

        public String getScore_detail() {
            return score_detail;
        }

        public void setScore_detail(String score_detail) {
            this.score_detail = score_detail;
        }
    }
}
