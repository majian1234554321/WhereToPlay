package com.fanc.wheretoplay.datamodel;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/6/21.
 */

public class CityResource {

    public List<Province> provinces;


    @Override
    public String toString() {
        return "CityResource{" +
                "provinces=" + provinces +
                '}';
    }

    public static class Province {
        private String id;// id
        private String pid;// 上级id
        private String name;// 名称
        private String lat;// 经度
        private String lng;// 纬度
        private String pcapital;//省会0不是1是
        private String status;//状态1为热门城市，0为默认
        private List<City> child;// 城市列表

        @Override
        public String toString() {
            return "Province{" +
                    "id='" + id + '\'' +
                    ", pid='" + pid + '\'' +
                    ", name='" + name + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    ", pcapital='" + pcapital + '\'' +
                    ", status='" + status + '\'' +
                    ", child='" + child + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public String getPid() {
            return pid;
        }

        public String getName() {
            return name;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getPcapital() {
            return pcapital;
        }

        public String getStatus() {
            return status;
        }

        public List<City> getChild() {
            return child;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setPcapital(String pcapital) {
            this.pcapital = pcapital;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setChild(List<City> child) {
            this.child = child;
        }
    }

    /**
     * 市
     */
    public static class City implements Comparable,Serializable {

        private String id;// 编号
        private String name;// 城市名
        private String pid;// 上级id
        private String lat;//经度
        private String lng;//纬度
        private String pcapital;//省会0不是1是
        private String status;//状态1为热门城市，0为默认
        private String pinyin;//城市首字母拼音
        private ArrayList<Area> child;// 区列表


        public City() {
            this("", "", "");
        }

        public City(String id, String name, String pinyin) {
            this(id, name, "", "", "", "", "", pinyin);
        }

        public City(String id, String name, String pid, String lat, String lng,
                    String pcapital, String status, String pinyin) {
            this.id = id;
            this.name = name;
            this.pid = pid;
            this.lat = lat;
            this.lng = lng;
            this.pcapital = pcapital;
            this.status = status;
            this.pinyin = pinyin;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setPcapital(String pcapital) {
            this.pcapital = pcapital;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPid() {
            return pid;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getPcapital() {
            return pcapital;
        }

        public String getStatus() {
            return status;
        }

        public String getPinyin() {
            return pinyin;
        }

        public ArrayList<Area> getChild() {
            return child;
        }

        public void setChild(ArrayList<Area> child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "City{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", pid='" + pid + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    ", pcapital='" + pcapital + '\'' +
                    ", status='" + status + '\'' +
                    ", pinyin='" + pinyin + '\'' +
                    '}';
        }

        @Override
        public int compareTo(@NonNull Object o) {
            City city = (City) o;
//            int flag = this.getPinyin().compareTo(city.getPinyin());
            int flag = Collator.getInstance(Locale.CHINESE).compare(this.getPinyin(), city.getPinyin());
            if (flag == 0) {
                return this.getId().compareTo(city.getId());
            } else {
                return flag;
            }
        }
    }

    /**
     * 区
     */
    public static class Area implements Serializable{
        private String id;//区域id
        private String pid;//上级id
        private String name;// 名称
        private String lat;// 经纬度
        private String lng;//
        private String pcapital;//省会0不是1是
        private String status;//状态1为热门城市，0为默认

        @Override
        public String toString() {
            return "Area{" +
                    "id='" + id + '\'' +
                    ", pid='" + pid + '\'' +
                    ", name='" + name + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    ", pcapital='" + pcapital + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public String getPid() {
            return pid;
        }

        public String getName() {
            return name;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getPcapital() {
            return pcapital;
        }

        public String getStatus() {
            return status;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setPcapital(String pcapital) {
            this.pcapital = pcapital;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
