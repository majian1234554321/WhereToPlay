package com.fanc.wheretoplay.util;

import android.app.DownloadManager;

/**
 * Created by Administrator on 2017/6/14.
 */

public interface Constants {
    /**
     * 分享
     */
    String WX_APP_ID = "wx424026eca78d03e6";
    String WX_APP_SECRET = "f41673f89c694467745d16b81a13f212";
    String QQ_APP_ID = "1106322444";
    String QQ_APP_KEY = "XiKU9RCVa0SPvNTA";
    /**
     * constants
     */
    String IS_FIRST = "isFirst";// 是否第一次使用APP
    String IS_SIGN_IN = "is_sign_in";// 是否登录
    String IS_SET_PAY_PASSWORD = "pay_password";// 是否设置过支付密码
    // 无效参数
    double USELESS_NUMBER_PARAM = -1;
    // 预订方式
    String RESERVE_WAY_PREPAY = "1";// 预付预订
    String RESERVE_WAY_CREDIT = "2";// 信誉预订
    /**
     * 支付方式
     */
    int PAY_WAY_ALI = 1;// 支付宝
    int PAY_WAY_WEIXIN = 2;// 微信
    int PAY_WAY_BALANCE = 3;// 余额

    /**
     * page
     */
    String PAGE = "page";
    String MERCHANT_DETAIL = "merchant_detail";
    String SEARCH = "search";
    String MINE = "mine";
    String TO_EVALUATE = "to_evaluate";// 去评价
    String COMMENT = "comment";
    String MINE_INFO = "mine_info";
    String MESSAGE = "message";
    String WALLET = "wallet";
    String DISCOUNT_COUPON = "discount_coupon";
    String INTEGRAL = "integral";// 积分
    String ORDER = "order";// 订单
    String COLLECTION = "collection";
    String REFERRAL = "referral";// 推荐
    String DEAL_DETAIL = "deal_detail";
    String CONSUME_DETAIL = "consume_detail";
    String RECHARGE_DETAIL = "recharge_detail";
    String SET_PAY_PWD = "set_pay_pwd";
    String ADVICE_FEEDBACK = "advice_feedback";
    String SYSTEM_NOTIFY = "system_notify";
    String ABOUT_US = "about_us";
    String RESERVE_INFO = "reserve_info";
    String WAITER_INFO = "waiter_info";
    String WAITER_PHOTO = "waiter_photo";
    String MESSAGE_DETAIL = "message_detail";
    String COMMENT_DETAIL = "comment_detail";
    String COMMENT_DETAIL_ENVIRONMENT = "comment_detail_environment";
    String COMMENT_DETAIL_ATMOSPHERE = "comment_detail_atmosphere";
    String COMMENT_DETAIL_SERVICE = "comment_detail_service";
    String COMMENT_DETAIL_OTHER = "comment_detail_other";
    String PAYING_THE_BILL = "paying_the_bill";// 结账
    String CONSUME = "consume";// 消费
    String LIST_PAGE = "list";// 列表页面
    String ROOM = "room";// 房型
    String DRINKS = "drinks";// 酒水
    String MONEY = "money";// 金额
    String ACTION = "action";// 活动
    String BRIEF = "简介";   //简介
    String ORDER_TO_COMPLETE = "order_to_complete";// 下单完成界面


    /**
     * broadcast
     */
    String ACTION_WAITER_PHOTO_CHECKED = "intent.WAITER_PHOTO_CHECKED";// 服务员照片已查看
    String ACTION_WAITER_PHOTO_SELECTED = "intent.WAITER_PHOTO_SELECTED";// 服务员照片已查选择
    String ACTION_WAITER_PHOTO_CONFIRM = "intent.WAITER_PHOTO_CONFIRM";// 服务员照片已查选择
    String ACTION_CITY_SELECTED = "intent.CITY_SELECTED";// 城市已经选择
    String ACTION_AVATAR_MODIFY_SUCCESS = "intent.AVATAR_MODIFY_SUCCESS";//修改成功
    String ACTION_MINE_INFO_MODIFY_SUCCESS = "intent.MINE_INFO_MODIFY_SUCCESS";//修改成功
    String ACTION_MESSAGE_READED = "intent.MESSAGE_READED";// 消息已读
    String ACTION_FILTER_CONSUME = "intent.FILTER_CONSUME";// 消费明细筛选
    String ACTION_FILTER_RECHARGE = "intent.FILTER_RECHARGE";// 充值明细筛选
    String ACTION_CHOOSE_DISCOUNT_COUPON = "intent.CHOOSE_DISCOUNT_COUPON";// 选中了优惠券
    String ACTION_PAY_SUCCESS = "intent.PAY_SUCCESS";// 支付成功
    String ACTION_WXPAY_SUCCESS = "intent.WXPAY_SUCCESS";// 微信支付成功
    String ACTION_SIGN_OUT = "intent.SIGN_OUT";// 退出登录
    String ACTION_RESIGN_IN = "intent.RESIGN_IN";// 重新登录
    String ACTION_SELECT_ROOM = "intent.SELECT_ROOM";// 选择房型
    String ACTION_SELECT_DATE_TIME = "intent.SELECT_DATE_TIME";// 选择到店时间
    String APK_DOWNLOAD_COMPLETE = DownloadManager.ACTION_DOWNLOAD_COMPLETE;// apk下载完成

    /**
     * key-value
     * key
     */
    String POSITION = "position";// waiter info
    String CITY = "city";// selected city
    String STORE_ID = "store_id";// store id
    String URL = "url";// url
    String ID = "id";// id
    String STORE_NAME = "store_name";// 商店名称
    String ADDRESS = "address";
    String TIMES = "times";// 查看筛选时间戳
    String WAITER = "waiter";// 查看的服务员
    String WAITER_NAME = "waiter_name";
    String WAITER_IMAGE = "waiter_image";
    String WAITER_ID = "waiter_id";
    String PARAM = "param";// 参数
    String DATE = "date";
    String ORDER_ID = "order_id";
    String IS_CHOOSE = "is_choose";// 是否选择（优惠券）
    String DISCOUNT_ID = "discount_id";// 优惠券id
    String PRICE = "price";
    String IS_COMMENT = "is_comment";// 是否是去评价
    String NAME = "name";
    String MOBILE = "mobile";
    String CREDIT_RESERVE = "credit_reserve";// 信誉预订
    String TYPE = "type";
    String DATA = "data";   //数据

    /**
     * 请求权限码
     */
    int REQUEST_PERMISSION_CODE = 100;

    /**
     * 版本比较
     */
    enum Version {
        unknown,
        higher,
        equal,
        lower
    }

    /**
     * 文件类型
     */
    String MIME_TYPE = "application/vnd.android.package-archive";// apk文件类型

}
