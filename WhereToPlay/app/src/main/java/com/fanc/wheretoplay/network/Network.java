package com.fanc.wheretoplay.network;

import android.util.Log;

import com.fanc.wheretoplay.datamodel.AccessOrderIdModel;
import com.fanc.wheretoplay.datamodel.BookListModel;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import com.fanc.wheretoplay.datamodel.CancleOrderModel;
import com.fanc.wheretoplay.datamodel.MineFriend;
import com.fanc.wheretoplay.datamodel.MineMoney;
import com.fanc.wheretoplay.datamodel.OrderDetailModel;
import com.fanc.wheretoplay.datamodel.PayOrder;
import com.fanc.wheretoplay.datamodel.SubmitCommentModel;
import com.fanc.wheretoplay.datamodel.VerifyCode;
import com.fanc.wheretoplay.rx.BaseResponseModel;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/29.
 */

public class Network{

    // 公共前缀123456678888888888
//    String BASE = "http://192.168.1.96/ktv/index.php";// 开发
//    String IMAGE = "http://192.168.1.96/ktv";// 图片

//    String BASE = "http://ktv.ctkey.com.cn";// 测试
//    String IMAGE = "http://ktv.ctkey.com.cn";

    public static String BASE = "http://testapi.51tzl.cn";   // 测试
    public static String IMAGE = "http://testapi.51tzl.cn";   // 测试
    // 模块
    public static String API = "/Api/";// 用户模块

    // 手机系统
    public static String PHONE_ANDROID = "android";

//    public static String IMAGE = "http://ktv.51tzl.cn";
//    public static String BASE = "http://ktv.51tzl.cn";
    public static String USER = BASE + API;

    public static void changEnvironment(String url) {
        BASE = url;
        IMAGE = url;
        USER = url + API;
    }

    public static class User {

        public static String PUBLIC_STORE_DETAIL = USER + "Public/storeDetail";// 店铺详情
        public static String PUBLIC_VERSION = USER + "Public/version";// 版本检测
        public static String PUBLIC_ENVIRONMENT = USER + "Public/environment";// 环境
        public static String PUBLIC_ATMOSPHERE = USER + "Public/atmosphere";// 气氛
        public static String PUBLIC_SERVICE = USER + "Public/service";// 服务
        public static String PUBLIC_OTHER = USER + "Public/other";// 其他
        public static String PUBLIC_ROOM_LIST = USER + "Public/roomList";// 房型列表
        public static String PUBLIC_MARKETING = USER + "Public/marketing";// 活动
        public static String PUBLIC_WINE = USER + "Public/wine";// 酒水
        public static String PUBLIC_ROOM_TYPE = USER + "Public/roomtype";// 房型列表
        public static String PUBLIC_HOUSENEWS = USER + "Public/roomstatus";// 房态（新）
        public static String PUBLIC_HOUSE_TYPE = USER + "Public/roomtype";// 房型列表（新）
        public static String PUBLIC_COMMENTS = USER + "Public/storecomment";// 全部评论

        public static String COMMON_STORE_LIST = USER + "Common/storeList";//主页列表
        public static String COMMON_FILTER = USER + "Common/filter";// 筛选列表
        public static String COMMON_ADVERTISING = USER + "Common/advertising";//发现店铺
        public static String COMMON_RECOMMENDATION = USER + "Common/recommendation";// 推荐搜索
        public static String COMMON_SEARCH = USER + "Common/search";// 搜索
        public static String COMMON_STORE_SORT = USER + "Common/storeSort";// 发现筛选

        public static String LOGIN_VERIFY = USER + "Login/verify";// 验证码
        public static String LOGIN_REGISTER = USER + "Login/register";// 注册
        public static String LOGIN_LOGIN = USER + "Login/login";// 登录
        public static String LOGIN_FIND_PASSWORD = USER + "Login/findPassword";//找回密码

        public static String USER_HOME = USER + "User/home";// 我的主页
        public static String USER_RCOMMEND_FRIEND = USER + "Reco/recomList";// 推荐好友
        public static String USER_RCOMMEND_MONEY = USER + "Reco/recomReward";// 推荐奖励
        public static String USER_MODIFY_AVATAR = USER + "User/modifyAvatar";// 修改头像
        public static String USER_MODIFY_PRO_FILE = USER + "User/modifyProfile";//修改资料
        public static String USER_MESSAGE_LIST = USER + "User/messageList";// 消息
        public static String USER_MESSAGE_DETAIL = USER + "User/messageDetail";// 消息详情
        public static String USER_DELETE_MESSAGE = USER + "User/deleteMessage";// 删除消息
        public static String USER_MY_WALLET = USER + "User/myWallet";// 钱包
        public static String USER_COST_DETAIL = USER + "User/costDetail";// 消费明细
        public static String USER_RECHARGE_DETAIL = USER + "User/rechargeDetail";// 充值明细
        public static String USER_COUPON_LIST = USER + "User/couponList";// 优惠券
        public static String USER_SCORE_LIST = USER + "User/scoreList";// 积分
        public static String USER_BOOK_LIST = USER + "User/bookList";// 订单
        public static String USER_COLLECTION_LIST = USER + "User/collectionList";// 收藏
        public static String USER_SHARE_AD = USER + "User/shareAD";// 推荐分享
        public static String USER_PAY_PASSWORD = USER + "User/payPassword";// 设置支付密码
        public static String USER_FEEDBACK = USER + "User/feedback";// 意见反馈
        public static String USER_NOTIFICATION = USER + "User/notification";// 系统通知
        public static String USER_SHARE_STORE = USER + "user/share_store";// 分享店铺
        public static String USER_SHARE_CODE = USER + "user/share_code";// 分享邀请
        public static String USER_COLLECT = USER + "User/Collect";// 收藏
        public static String USER_DELETE_COLLECTION = USER + "User/deleteCollection";// 删除收藏
        public static String USER_STORE_DESCRIBE = USER + "User/store_describe";// 商铺描述
        public static String USER_SERVER_LIST = USER + "User/serverList";// 服务员列表
        public static String USER_SELECT_WAITER = USER + "User/selectWaiter";// 选择服务员
        public static String USER_ONLINE_BOOK = USER + "User/onlineBook";// 在线预订
        public static String USER_COMMENT_PAGE = USER + "User/commentPage";// 写评价页面
        public static String USER_SUBMIT_COMMENT = USER + "User/submitComment";// 提交评论
        public static String USER_USER_RECHARGE = USER + "User/userRecharge";// 用户充值
        public static String USER_RECHARGE_ALIPAY_VALIDA = USER + "User/rechargeAlipayValida";// 支付宝充值结果验证
        public static String USER_CANCLE_ORDER = USER + "User/cancle_order";// 取消订单
        public static String USER_OUT_ORDER = USER + "User/out_order";// 删除订单
        public static String USER_ORDER_DONE = USER + "User/order_done";// 结单
        public static String USER_CONSUME_AGAIN = USER + "User/consume_again";// 消费
        public static String USER_ORDER_PAYOFF = USER + "User/order_payoff";// 订单支付
        public static String USER_ORDER_PAYOFF_VALIDATE = USER + "User/order_payoffvalidate";// 结账/消费 支付宝支付验证
        public static String USER_PAYORDER = USER + "User/payOrder";// 订金支付
        public static String USER_PREPAY_ORDER_VALIDATE = USER + "User/prepayorderValidate";// 预订 支付宝支付结果验证
        public static String USER_CONFIRM_SETCODE = USER + "User/confirm_setcode";// 检查用户是否设置过密码
        public static String USER_CONFIRM_COLLECT = USER + "User/confirm_collect";// 判断用户是否收藏过店铺
        public static String USER_SAVE_REGISTRATIONID = USER + "User/save_registrationid";// 极光RegistrationID保存接口
        public static String USER_IS_DISPLAY = USER + "User/is_display";// 信誉预订是否显示
        public static String USER_WAITING = USER + "User/waiting";// 下单成功
        public static String USER_NEW_ORDERLIST = USER + "User/new_orderList";// 订单新接口

        public  static void changeUserUrl(String url) {
             PUBLIC_STORE_DETAIL = url + "Public/storeDetail";// 店铺详情
             PUBLIC_VERSION = url + "Public/version";// 版本检测
             PUBLIC_ENVIRONMENT = url + "Public/environment";// 环境
             PUBLIC_ATMOSPHERE = url + "Public/atmosphere";// 气氛
             PUBLIC_SERVICE = url + "Public/service";// 服务
             PUBLIC_OTHER = url + "Public/other";// 其他
             PUBLIC_ROOM_LIST = url + "Public/roomList";// 房型列表
             PUBLIC_MARKETING = url + "Public/marketing";// 活动
             PUBLIC_WINE = url + "Public/wine";// 酒水
             PUBLIC_ROOM_TYPE = url + "Public/roomtype";// 房型列表
             PUBLIC_HOUSENEWS = url + "Public/roomstatus";// 房态（新）
             PUBLIC_HOUSE_TYPE = url + "Public/roomtype";// 房型列表（新）
             PUBLIC_COMMENTS = url + "Public/storecomment";// 全部评论

             COMMON_STORE_LIST = url + "Common/storeList";//主页列表
             COMMON_FILTER = url + "Common/filter";// 筛选列表
             COMMON_ADVERTISING = url + "Common/advertising";//发现店铺
             COMMON_RECOMMENDATION = url + "Common/recommendation";// 推荐搜索
             COMMON_SEARCH = url + "Common/search";// 搜索
             COMMON_STORE_SORT = url + "Common/storeSort";// 发现筛选

             LOGIN_VERIFY = url + "Login/verify";// 验证码
             LOGIN_REGISTER = url + "Login/register";// 注册
             LOGIN_LOGIN = url + "Login/login";// 登录
             LOGIN_FIND_PASSWORD = url + "Login/findPassword";//找回密码

             USER_HOME = url + "User/home";// 我的主页
             USER_RCOMMEND_FRIEND = url + "Reco/recomList";// 推荐好友
            USER_RCOMMEND_MONEY = url + "Reco/recomReward";// 推荐奖励
             USER_MODIFY_AVATAR = url + "User/modifyAvatar";// 修改头像
             USER_MODIFY_PRO_FILE = url + "User/modifyProfile";//修改资料
             USER_MESSAGE_LIST = url + "User/messageList";// 消息
             USER_MESSAGE_DETAIL = url + "User/messageDetail";// 消息详情
             USER_DELETE_MESSAGE = url + "User/deleteMessage";// 删除消息
             USER_MY_WALLET = url + "User/myWallet";// 钱包
             USER_COST_DETAIL = url + "User/costDetail";// 消费明细
             USER_RECHARGE_DETAIL = url + "User/rechargeDetail";// 充值明细
             USER_COUPON_LIST = url + "User/couponList";// 优惠券
             USER_SCORE_LIST = url + "User/scoreList";// 积分
             USER_BOOK_LIST = url + "User/bookList";// 订单
             USER_COLLECTION_LIST = url + "User/collectionList";// 收藏
             USER_SHARE_AD = url + "User/shareAD";// 推荐分享
             USER_PAY_PASSWORD = url + "User/payPassword";// 设置支付密码
             USER_FEEDBACK = url + "User/feedback";// 意见反馈
             USER_NOTIFICATION = url + "User/notification";// 系统通知
             USER_SHARE_STORE = url + "user/share_store";// 分享店铺
             USER_SHARE_CODE = url + "user/share_code";// 分享邀请
             USER_COLLECT = url + "User/Collect";// 收藏
             USER_DELETE_COLLECTION = url + "User/deleteCollection";// 删除收藏
             USER_STORE_DESCRIBE = url + "User/store_describe";// 商铺描述
             USER_SERVER_LIST = url + "User/serverList";// 服务员列表
             USER_SELECT_WAITER = url + "User/selectWaiter";// 选择服务员
             USER_ONLINE_BOOK = url + "User/onlineBook";// 在线预订
             USER_COMMENT_PAGE = url + "User/commentPage";// 写评价页面
             USER_SUBMIT_COMMENT = url + "User/submitComment";// 提交评论
             USER_USER_RECHARGE = url + "User/userRecharge";// 用户充值
             USER_RECHARGE_ALIPAY_VALIDA = url + "User/rechargeAlipayValida";// 支付宝充值结果验证
             USER_CANCLE_ORDER = url + "User/cancle_order";// 取消订单
             USER_OUT_ORDER = url + "User/out_order";// 删除订单
             USER_ORDER_DONE = url + "User/order_done";// 结单
             USER_CONSUME_AGAIN = url + "User/consume_again";// 消费
             USER_ORDER_PAYOFF = url + "User/order_payoff";// 订单支付
             USER_ORDER_PAYOFF_VALIDATE = url + "User/order_payoffvalidate";// 结账/消费 支付宝支付验证
             USER_PAYORDER = url + "User/payOrder";// 订金支付
             USER_PREPAY_ORDER_VALIDATE = url + "User/prepayorderValidate";// 预订 支付宝支付结果验证
             USER_CONFIRM_SETCODE = url + "User/confirm_setcode";// 检查用户是否设置过密码
             USER_CONFIRM_COLLECT = url + "User/confirm_collect";// 判断用户是否收藏过店铺
             USER_SAVE_REGISTRATIONID = url + "User/save_registrationid";// 极光RegistrationID保存接口
             USER_IS_DISPLAY = url + "User/is_display";// 信誉预订是否显示
             USER_WAITING = url + "User/waiting";// 下单成功
             USER_NEW_ORDERLIST = url + "User/new_orderList";// 订单新接口
        }
    }

    public static class  Param {
        public static String MOBILE = "mobile";// mobile
        public static String PASSWORD = "password";// string, 必须, 密码
        public static String CODE = "code";// int , 必须, 短信验证码
        public static String REGISTERED = "registered";// 必须，用户的注册地
        public static String LNG = "lng";// float, 可选, 手机当前经度，百度坐标
        public static String LAT = "lat";// float, 可选, 手机当前纬度，百度坐标
        public static String SHARE_CODE = "share_code";// string,可选，邀请码
        public static String TOKEN = "token";
        public static String PAGE = "page";// 页码
        public static String SIZE = "size";// 每页的数量
        public static String TYPE = "type";// 类型（首页查看）
        public static String FILTER = "filter";// 筛选
        public static String VALUE = "value";// 筛选二级菜单值
        public static String ID = "id";// id信息
        public static String IDS = "ids";// id信息
        public static String AVATAR = "avatar";// 头像
        public static String NICKNAME = "nickname";// 昵称
        public static String GENDER = "gender";//性别
        public static String BIRTHDAY = "birthday";// 生日
        public static String SIGNATURE = "signature";//签名
        public static String MESSAGE_ID = "message_id";// 消息id
        public static String STORE_NAME = "store_name";// 店名
        public static String STORE_ID = "store_id";// 店名
        public static String TIMES = "times";// 时间戳
        public static String REPASSWORD = "repassword";// 第二次输入密码
        public static String TITLE = "title";// 内容
        public static String CONTENT = "content";// 内容
        public static String OS = "os";// 手机系统
        public static String VERSION = "version";// 版本
        public static String COLLECT_ID = "collect_id";//收藏id
        public static String WAITER_ID = "waiter_id";//服务员id
        public static String PRICE = "price";// 价格
        public static String PAY_PW = "pay_pw";// 支付密码
        public static String ROOM_ID = "room_id";// 房型id
        public static String ARRIVAL_TIME = "arrival_time";// 预订时间
        public static String REMARK = "remark";// 备注
        public static String CAR_NUM = "car_num";// 车位数
        public static String MANS = "mans";// 人数
        public static String SYS_ENVIRONMENT = "sys_environment";//系统环境评论标签id
        public static String SYS_ATMOSPHERE = "sys_atmosphere";//系统环境评论标签id
        public static String SYS_SERVICE = "sys_service";//系统环境评论标签id
        public static String SYS_OTHER = "sys_other";//系统环境评论标签id
        public static String CUS_ENVIRONMENT = "cus_environment";//自定义环境评论内容
        public static String CUS_ATMOSPHERE = "cus_atmosphere";//自定义气氛评论内容
        public static String CUS_SERVICE = "cus_service";//自定义服务评论内容
        public static String CUS_OTHER = "cus_other";//自定义其他评论内容
        public static String MONEY = "money";// 充值金额
        public static String ORDERFORM_ID = "orderform_id";// 订单id
        public static String KEYWORD = "keyword";//关键字
        public static String AREA = "area";// 区域
        public static String STYLE = "style";// 店家类型
        public static String ORDER_ID = "order_id";// 订单id
        public static String COUPON_ID = "coupon_id";// 优惠券id
        public static String PREPAY = "prepay";// 预付
        public static String CITY = "city";// 城市
        public static String DISCOUNT = "discount";//优惠金额
        public static String REGISTRATIONID = "registrationid";// 极光注册id
        public static String REMAIN = "remain";// 剩余订金
        public static String DISPLAY_BALANCE = "display_balance";
        public static String FEE = "fee";//不参与优惠的金额
        public static String PAGEINDEX = "pageindex";   // 当前页码
        public static String PAGESIZE = "pagesize";   // 当前显示的评论用户个数
    }


    public  interface  LH_API {

        //订单列表
        @Multipart
        @POST("User/bookList")
        Observable<BaseResponseModel<BookListModel.ContentBean>> bookList(@Part MultipartBody.Part file, @Part MultipartBody.Part fileB, @Part MultipartBody.Part filec, @Part MultipartBody.Part filed);

        //订单详情
        @Multipart
        @POST("User/orderDetail")
        Observable<BaseResponseModel<OrderDetailModel.ContentBean>> orderDetail(@Part MultipartBody.Part file, @Part MultipartBody.Part fileB);

        //取消预订
        @Multipart
        @POST("User/cancle_order")
        Observable<BaseResponseModel<CancleOrderModel.ContentBean>> cancle_order(@Part MultipartBody.Part file, @Part MultipartBody.Part fileB);

        //提交评论
        @Multipart
        @POST("Public/submitcomment")
        Observable<SubmitCommentModel> SubmitCommentModel(@Part MultipartBody.Part fileA);

        //商家详情支付获取订单Id
        @Multipart
        @POST("User/immediatelyPay")
        Observable<AccessOrderIdModel> immediatelyPay(@Part MultipartBody.Part fileA,@Part MultipartBody.Part fileB,@Part MultipartBody.Part fileC);

        //商家详情支付获取支付信息
        @Multipart
        @POST("User/order_payoff")
        Observable<PayOrder> order_payoff(@Part MultipartBody.Part fileA, @Part MultipartBody.Part fileB, @Part MultipartBody.Part fileC, @Part MultipartBody.Part fileAA,
                                          @Part MultipartBody.Part fileBB, @Part MultipartBody.Part fileCC,@Part MultipartBody.Part fileCCC,@Part MultipartBody.Part fileCCCC);

        //推荐奖励
        @Multipart
        @POST("Reco/recomReward")
        Observable<MineMoney> recomReward(@Part MultipartBody.Part fileA, @Part MultipartBody.Part fileB, @Part MultipartBody.Part fileC);

        //推荐好友
        @Multipart
        @POST("Reco/recomList")
        Observable<MineFriend> recomFriend(@Part MultipartBody.Part fileA, @Part MultipartBody.Part fileB, @Part MultipartBody.Part fileC);

        //验证码
        @Multipart
        @POST("Login/verify")
        Observable<VerifyCode> getMyVerification(@Part MultipartBody.Part fileA);

        //收藏
        @Multipart
        @POST("User/Collect")
        Observable<SubmitCommentModel> collect(@Part MultipartBody.Part fileA, @Part MultipartBody.Part fileB);

    }

}
