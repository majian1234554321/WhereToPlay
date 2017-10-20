package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 */

public class CommentPage extends BaseModel {

    /**
     * order: 订单相关
     * tags: array，默认评论标签列表
     */

    private Order order;
    private List<Tag> tags;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public static class Order {
        /**
         * orderform_id: string, 订单ID
         * store_name: string, 商家名字
         * arrival_time：string，到店时间，时间戳
         * book_sn, string，预定号
         * prepay：string, 已支付定金，单位元
         * total：string，总价，单位元
         * created_time：string，创建时间，时间戳
         * status：string, 订单状态,0待付款,1已取消,2预订成功,3正在消费,4已结单
         * room_id: string，房间类型id
         * decorate_id:string，装修风格id
         * roomtype:string，房间类型
         * decoratetype:string，装修风格
         */

        private String orderform_id;
        private String store_name;
        private String arrival_time;
        private String book_sn;
        private String prepay;
        private String total;
        private String status;
        private String roomtype;
        private String decoratetype;

        public String getOrderform_id() {
            return orderform_id;
        }

        public void setOrderform_id(String orderform_id) {
            this.orderform_id = orderform_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(String arrival_time) {
            this.arrival_time = arrival_time;
        }

        public String getBook_sn() {
            return book_sn;
        }

        public void setBook_sn(String book_sn) {
            this.book_sn = book_sn;
        }

        public String getPrepay() {
            return prepay;
        }

        public void setPrepay(String prepay) {
            this.prepay = prepay;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRoomtype() {
            return roomtype;
        }

        public void setRoomtype(String roomtype) {
            this.roomtype = roomtype;
        }

        public String getDecoratetype() {
            return decoratetype;
        }

        public void setDecoratetype(String decoratetype) {
            this.decoratetype = decoratetype;
        }
    }

    public static class Tag {
        /**
         * tag_id: string, 标签ID
         * type：string，标签类型，1-环境|2-气氛|3-服务|4-其他
         * name：string，标签名称
         */

        private String tag_id;
        private String type;
        private String name;

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
