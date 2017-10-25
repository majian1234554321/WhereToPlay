package com.fanc.wheretoplay.network;

/**
 * Created by Administrator on 2017/6/29.
 */

public interface Network {

    // 公共前缀
//    String BASE = "http://192.168.1.96/ktv/index.php";// 开发
//    String IMAGE = "http://192.168.1.96/ktv";// 图片

//    String BASE = "http://ktv.ctkey.com.cn";// 测试
//    String IMAGE = "http://ktv.ctkey.com.cn";
    String BASE = "http://ktv.51tzl.cn";// 测试
    String IMAGE = "http://ktv.51tzl.cn";

    // 模块
    String API = "/Api/";// 用户模块

    // 手机系统
    String PHONE_ANDROID = "android";

    interface User {
        String USER = BASE + API;

        String PUBLIC_STORE_DETAIL = USER + "Public/storeDetail";// 店铺详情
        String PUBLIC_VERSION = USER + "Public/version";// 版本检测
        String PUBLIC_ENVIRONMENT = USER + "Public/environment";// 环境
        String PUBLIC_ATMOSPHERE = USER + "Public/atmosphere";// 气氛
        String PUBLIC_SERVICE = USER + "Public/service";// 服务
        String PUBLIC_OTHER = USER + "Public/other";// 其他
        String PUBLIC_ROOM_LIST = USER + "Public/roomList";// 房型列表
        String PUBLIC_MARKETING = USER + "Public/marketing";// 活动
        String PUBLIC_WINE = USER + "Public/wine";// 酒水
        String PUBLIC_ROOM_TYPE = USER + "Public/roomtype";// 房型列表
        String PUBLIC_HOUSENEWS = USER + "Public/roomstatus";// 房态（新）
        String PUBLIC_HOUSE_TYPE = USER + "Public/roomtype";// 房型列表（新）

        String COMMON_STORE_LIST = USER + "Common/storeList";//主页列表
        String COMMON_FILTER = USER + "Common/filter";// 筛选列表
        String COMMON_ADVERTISING = USER + "Common/advertising";//发现店铺
        String COMMON_RECOMMENDATION = USER + "Common/recommendation";// 推荐搜索
        String COMMON_SEARCH = USER + "Common/search";// 搜索
        String COMMON_STORE_SORT = USER + "Common/storeSort";// 发现筛选

        String LOGIN_VERIFY = USER + "Login/verify";// 验证码
        String LOGIN_REGISTER = USER + "Login/register";// 注册
        String LOGIN_LOGIN = USER + "Login/login";// 登录
        String LOGIN_FIND_PASSWORD = USER + "Login/findPassword";//找回密码

        String USER_HOME = USER + "User/home";// 我的主页
        String USER_MODIFY_AVATAR = USER + "User/modifyAvatar";// 修改头像
        String USER_MODIFY_PRO_FILE = USER + "User/modifyProfile";//修改资料
        String USER_MESSAGE_LIST = USER + "User/messageList";// 消息
        String USER_MESSAGE_DETAIL = USER + "User/messageDetail";// 消息详情
        String USER_DELETE_MESSAGE = USER + "User/deleteMessage";// 删除消息
        String USER_MY_WALLET = USER + "User/myWallet";// 钱包
        String USER_COST_DETAIL = USER + "User/costDetail";// 消费明细
        String USER_RECHARGE_DETAIL = USER + "User/rechargeDetail";// 充值明细
        String USER_COUPON_LIST = USER + "User/couponList";// 优惠券
        String USER_SCORE_LIST = USER + "User/scoreList";// 积分
        String USER_BOOK_LIST = USER + "User/bookList";// 订单
        String USER_COLLECTION_LIST = USER + "User/collectionList";// 收藏
        String USER_SHARE_AD = USER + "User/shareAD";// 推荐分享
        String USER_PAY_PASSWORD = USER + "User/payPassword";// 设置支付密码
        String USER_FEEDBACK = USER + "User/feedback";// 意见反馈
        String USER_NOTIFICATION = USER + "User/notification";// 系统通知
        String USER_SHARE_STORE = USER + "user/share_store";// 分享店铺
        String USER_SHARE_CODE = USER + "user/share_code";// 分享邀请
        String USER_COLLECT = USER + "User/Collect";// 收藏
        String USER_DELETE_COLLECTION = USER + "User/deleteCollection";// 删除收藏
        String USER_STORE_DESCRIBE = USER + "User/store_describe";// 商铺描述
        String USER_SERVER_LIST = USER + "User/serverList";// 服务员列表
        String USER_SELECT_WAITER = USER + "User/selectWaiter";// 选择服务员
        String USER_ONLINE_BOOK = USER + "User/onlineBook";// 在线预订
        String USER_COMMENT_PAGE = USER + "User/commentPage";// 写评价页面
        String USER_SUBMIT_COMMENT = USER + "User/submitComment";// 提交评论
        String USER_USER_RECHARGE = USER + "User/userRecharge";// 用户充值
        String USER_RECHARGE_ALIPAY_VALIDA = USER + "User/rechargeAlipayValida";// 支付宝充值结果验证
        String USER_CANCLE_ORDER = USER + "User/cancle_order";// 取消订单
        String USER_OUT_ORDER = USER + "User/out_order";// 删除订单
        String USER_ORDER_DONE = USER + "User/order_done";// 结单
        String USER_CONSUME_AGAIN = USER + "User/consume_again";// 消费
        String USER_ORDER_PAYOFF = USER + "User/order_payoff";// 订单支付
        String USER_ORDER_PAYOFF_VALIDATE = USER + "User/order_payoffvalidate";// 结账/消费 支付宝支付验证
        String USER_PAYORDER = USER + "User/payOrder";// 订金支付
        String USER_PREPAY_ORDER_VALIDATE = USER + "User/prepayorderValidate";// 预订 支付宝支付结果验证
        String USER_CONFIRM_SETCODE = USER + "User/confirm_setcode";// 检查用户是否设置过密码
        String USER_CONFIRM_COLLECT = USER + "User/confirm_collect";// 判断用户是否收藏过店铺
        String USER_SAVE_REGISTRATIONID = USER + "User/save_registrationid";// 极光RegistrationID保存接口
        String USER_IS_DISPLAY = USER + "User/is_display";// 信誉预订是否显示
        String USER_WAITING = USER + "User/waiting";// 下单成功
        String USER_NEW_ORDERLIST = USER + "User/new_orderList";// 订单新接口
    }

    interface Param {
        String MOBILE = "mobile";// mobile
        String PASSWORD = "password";// string, 必须, 密码
        String CODE = "code";// int , 必须, 短信验证码
        String REGISTERED = "registered";// 必须，用户的注册地
        String LNG = "lng";// float, 可选, 手机当前经度，百度坐标
        String LAT = "lat";// float, 可选, 手机当前纬度，百度坐标
        String SHARE_CODE = "share_code";// string,可选，邀请码
        String TOKEN = "token";
        String PAGE = "page";// 页码
        String SIZE = "size";// 每页的数量
        String TYPE = "type";// 类型（首页查看）
        String FILTER = "filter";// 筛选
        String VALUE = "value";// 筛选二级菜单值
        String ID = "id";// id信息
        String IDS = "ids";// id信息
        String AVATAR = "avatar";// 头像
        String NICKNAME = "nickname";// 昵称
        String GENDER = "gender";//性别
        String BIRTHDAY = "birthday";// 生日
        String SIGNATURE = "signature";//签名
        String MESSAGE_ID = "message_id";// 消息id
        String STORE_NAME = "store_name";// 店名
        String STORE_ID = "store_id";// 店名
        String TIMES = "times";// 时间戳
        String REPASSWORD = "repassword";// 第二次输入密码
        String TITLE = "title";// 内容
        String CONTENT = "content";// 内容
        String OS = "os";// 手机系统
        String VERSION = "version";// 版本
        String COLLECT_ID = "collect_id";//收藏id
        String WAITER_ID = "waiter_id";//服务员id
        String PRICE = "price";// 价格
        String PAY_PW = "pay_pw";// 支付密码
        String ROOM_ID = "room_id";// 房型id
        String ARRIVAL_TIME = "arrival_time";// 预订时间
        String REMARK = "remark";// 备注
        String CAR_NUM = "car_num";// 车位数
        String MANS = "mans";// 人数
        String SYS_ENVIRONMENT = "sys_environment";//系统环境评论标签id
        String SYS_ATMOSPHERE = "sys_atmosphere";//系统环境评论标签id
        String SYS_SERVICE = "sys_service";//系统环境评论标签id
        String SYS_OTHER = "sys_other";//系统环境评论标签id
        String CUS_ENVIRONMENT = "cus_environment";//自定义环境评论内容
        String CUS_ATMOSPHERE = "cus_atmosphere";//自定义气氛评论内容
        String CUS_SERVICE = "cus_service";//自定义服务评论内容
        String CUS_OTHER = "cus_other";//自定义其他评论内容
        String MONEY = "money";// 充值金额
        String ORDERFORM_ID = "orderform_id";// 订单id
        String KEYWORD = "keyword";//关键字
        String AREA = "area";// 区域
        String STYLE = "style";// 店家类型
        String ORDER_ID = "order_id";// 订单id
        String COUPON_ID = "coupon_id";// 优惠券id
        String PREPAY = "prepay";// 预付
        String CITY = "city";// 城市
        String DISCOUNT = "discount";//优惠金额
        String REGISTRATIONID = "registrationid";// 极光注册id
        String REMAIN = "remain";// 剩余订金
        String DISPLAY_BALANCE = "display_balance";
        String FEE = "fee";//不参与优惠的金额
    }

}
