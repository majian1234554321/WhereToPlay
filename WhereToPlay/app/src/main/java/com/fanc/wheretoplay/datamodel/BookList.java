package com.fanc.wheretoplay.datamodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.base.BaseModel;
import com.fanc.wheretoplay.network.Network;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class BookList extends BaseModel {

    private List<Book> list;

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public static class Book extends BaseObservable {
        /**
         * order_id：string，订单id，
         * store_id：string，商店id，
         * name: string, 商店名称，
         * cover: string, 商店封面路径，
         * arrival_time: string，到店时间，时间戳
         * book_sn：string，预订号
         * book_type：string，预订类型 1-订金预订 2-信誉预订 3-充值
         * room_type：string，房间类型
         * decorate_type: string, 商店装修风格
         * prepay：string，预付定金，单位元
         * total：string，总价，单位元
         * created_time: string, 创建时间，时间戳
         * status：string，订单状态：0待付款,1已取消,2预订成功,3正在消费,4已结单
         * leave_money:string,剩余订金
         * consume_again:String.是否可以消费
         * private String is_comment;是否评价过
         */

        private String order_id;
        private String store_id;
        private String name;
        private String cover;
        private String arrival_time;
        private String book_sn;
        private String book_type;
        private String room_type;
        private String decorate_type;
        private String prepay;
        private String total;
        private String created_time;
        private String status;
        private String leave_money;
        private String consume_again;
        private String is_comment;

        @BindingAdapter(value = {"url"}, requireAll = false)
        public static void setImage(ImageView imageView, String url) {
            Glide.with(imageView.getContext()).load(Network.IMAGE + url).into(imageView);
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getBook_type() {
            return book_type;
        }

        public void setBook_type(String book_type) {
            this.book_type = book_type;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getDecorate_type() {
            return decorate_type;
        }

        public void setDecorate_type(String decorate_type) {
            this.decorate_type = decorate_type;
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

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLeave_money() {
            return leave_money;
        }

        public void setLeave_money(String leave_money) {

            this.leave_money = leave_money;
        }

        public String getConsume_again() {
            return consume_again;
        }

        public void setConsume_again(String consume_again) {

            this.consume_again = consume_again;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getIs_comment() {

            return is_comment;
        }
    }
}
