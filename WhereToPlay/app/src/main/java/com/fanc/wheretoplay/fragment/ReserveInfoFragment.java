package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.activity.DownPaymentActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
//import com.fanc.wheretoplay.activity.ServiceActivity;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentReserveInfoBinding;
import com.fanc.wheretoplay.datamodel.DataValue;
import com.fanc.wheretoplay.datamodel.HousenewsList;
import com.fanc.wheretoplay.datamodel.RoomList;
import com.fanc.wheretoplay.datamodel.StoreDescribe;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.RoomTypeDialog;
import com.fanc.wheretoplay.view.TopMenu;
import com.orhanobut.logger.Logger;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.Call;


/**
 * Created by Administrator on 2017/6/20.
 */

public class ReserveInfoFragment extends BaseFragment {

    FragmentReserveInfoBinding reserveInfoBinding;

    TopMenu mTmReserveInfo;

    TextView mTvReserveInfoTitle;
    TextView mTvReserveInfoRealDiscount;
    TextView mTvReserveInfoAddress;
    TextView mTvReserveInfoDistance;
    ImageView mIvReserveInfoService;
    TextView mTvReserveLittleConsume;
    EditText mEtReserveInfoName;
    EditText mEtReserveInfoMobile;
    TextView mTvReserveInfoRoom;
    RadioButton mRbReserveInfoRepay;
    RadioButton mRbReserveInfoCredit;
    TextView mTvReserveInfoTime;
    //    TextView mTvReserveInfoWaiterInfo;
    EditText mEtReserveInfoCarport;
    EditText mEtReserveInfoNumberOfPeople;
    EditText mEtReserveInfoRemark;
    Button mBtnReserveInfoPay;

    String storeId;
    List<RoomList.Room> rooms;
    RoomTypeDialog roomTypeDialog;
    // 预定类型
    String type = Constants.RESERVE_WAY_CREDIT;
    String waiterId;

    // 预订时间
    String reserveDate;
    // 最晚预定时间
    String reservedTime;
    // 广播接收者
    Receiver receiver;
    // 经纬度，获取距离用
    private double lat, lng;
    // 店铺名称
    String storeName;
    private TextView tv1;
    private TextView tv2;
    private ImageView iv3;
    public String number;
    private TextView tvYdtext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reserveInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reserve_info, container, false);
        initViews();
        init();
        setListeners();
        RxBus.getDefault().toFlowable(HousenewsList.StatusBean.class)
                .subscribe(new Consumer<HousenewsList.StatusBean>() {
                    @Override
                    public void accept(HousenewsList.StatusBean s) {
                        if (s != null) {
                            room_ids = s.room_id;
                            mTvReserveInfoRoom.setText(s.getName());
                            mTvReserveInfoRoom.setTextColor(Color.parseColor("#333333"));
                            tv2.setText(s.getNumber());
                            number = s.getNumber();
                            tv2.setTextColor(Color.parseColor("#333333"));

                            tvYdtext.setText(s.date);
                            tvYdtext.setTextColor(Color.parseColor("#333333"));
                        }

                    }
                });
        return reserveInfoBinding.getRoot();
    }

    private void initViews() {


        TextView tvYd = reserveInfoBinding.tvYd;
        tvYdtext = reserveInfoBinding.tvYdtext;
        ImageView ivYd = reserveInfoBinding.ivYd;


        mTmReserveInfo = reserveInfoBinding.tmReserveInfo;
        mTvReserveInfoTitle = reserveInfoBinding.tvReserveInfoTitle;
        mTvReserveInfoRealDiscount = reserveInfoBinding.tvReserveInfoDiscountReal;
        mTvReserveInfoAddress = reserveInfoBinding.tvReserveInfoAddress;
        mTvReserveInfoDistance = reserveInfoBinding.tvReserveInfoDistance;
        mIvReserveInfoService = reserveInfoBinding.ivReserveInfoService;
        mTvReserveLittleConsume = reserveInfoBinding.tvReserveLittleConsume;
        mEtReserveInfoName = reserveInfoBinding.etReserveInfoName;
        mEtReserveInfoMobile = reserveInfoBinding.etReserveInfoMobile;
        mTvReserveInfoRoom = reserveInfoBinding.tvReserveInfoRoom;
        mRbReserveInfoRepay = reserveInfoBinding.rbReserveInfoPrepay;
        mRbReserveInfoCredit = reserveInfoBinding.rbReserveInfoCredit;
        mTvReserveInfoTime = reserveInfoBinding.tvReserveInfoTime;
//        mTvReserveInfoWaiterInfo = reserveInfoBinding.tvReserveInfoWaiterInfo;
        mEtReserveInfoCarport = reserveInfoBinding.etReserveInfoCarport;
        mEtReserveInfoNumberOfPeople = reserveInfoBinding.etReserveInfoNumberOfPeople;
        mEtReserveInfoRemark = reserveInfoBinding.etReserveInfoRemark;
        mBtnReserveInfoPay = reserveInfoBinding.btnReserveInfoPay;
        tv1 = reserveInfoBinding.tv1;
        tv2 = reserveInfoBinding.tv2;
        iv3 = reserveInfoBinding.iv3;


        if (names != null) {
            mTvReserveInfoRoom.setText(names);
            mTvReserveInfoRoom.setTextColor(Color.parseColor("#333333"));
        }
        if (numbers != null) {
            tv2.setText(numbers);
            number = numbers;
            tv2.setTextColor(Color.parseColor("#333333"));
        }

        if (datas != null) {
            tvYdtext.setText(datas);
            tvYdtext.setTextColor(Color.parseColor("#333333"));
        }

    }

    private void init() {
        mTmReserveInfo.setLeftIcon(R.drawable.left);
        mTmReserveInfo.setTitle(R.string.reserve);
        mTmReserveInfo.setTitleColor(getResources().getColor(R.color.white));

        reserveInfoBinding.setClick(this);
        // 联系人信息
        mEtReserveInfoName.setText(mUser.getNickname());
        mEtReserveInfoMobile.setText(mUser.getMobile());

        // 房型
        if (storeId != null) {
            getStoreDescribe(storeId, DataValue.latitude, DataValue.longitude);
//            getRoomList(storeId);
        }

        registerBroadcastReceiver();
    }

    private void setListeners() {
        mTmReserveInfo.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
                mContext.overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
            }
        });
        mIvReserveInfoService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConsultSource source = new ConsultSource(null, null, null);
                Unicorn.openServiceActivity(mContext, mContext.getResources().getString(R.string.app_name), source);
            }
        });
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_reserve_info_room:
                showRoomType();
                break;
            case R.id.iv_reserve_info_right:
                showRoomType();
                break;
            case R.id.tv_reserve_info_time:
                showReserveDateTime();
                break;
            case R.id.iv_reserve_info_date:
                showReserveDateTime();
                break;
            case R.id.tv_ydtext:
                selectedRoomId();
                break;
            case R.id.iv_yd:
                selectedRoomId();
                break;
            case R.id.tv2:
                selectedRoomId();
                break;

            case R.id.iv3:
                selectedRoomId();

                break;
            case R.id.btn_reserve_info_pay:
                goToPay();
                break;
            default:
                break;
        }
    }

    private void selectedRoomId() {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.HOUSENEWS);
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.putExtra(Constants.STORE_NAME, mTvReserveInfoTitle.getText().toString());
        intent.putExtra(Constants.DISCOUNT_COUPON, mTvReserveInfoRealDiscount.getText().toString());
        intent.putExtra(Constants.ADDRESS, mTvReserveInfoAddress.getText().toString() + "  " + mTvReserveInfoDistance.getText().toString());
        intent.putExtra("open", false);
        intent.putExtra("arrival_time", tvYdtext.getText().toString() + " " + mTvReserveInfoTime.getText().toString().trim());


        startActivity(intent);
    }


    public String numbers, prices, names, statuss, room_ids, datas;

    public ReserveInfoFragment setStoreId(String storeId, String numbers, String prices, String names, String statuss, String room_ids, String datas) {
        this.storeId = storeId;
        this.numbers = numbers;
        this.names = names;
        this.statuss = statuss;
        this.room_ids = room_ids;
        this.datas = datas;
        this.prices = prices;

        return this;
    }

    private void getStoreDescribe(String storeId, String lat, String lng) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        if (storeId != null && !storeId.isEmpty()) {
            params.put(Network.Param.STORE_ID, storeId);
        }

        params.put(Network.Param.LAT, lat);


        params.put(Network.Param.LNG, lng);


        OkHttpUtils.post()
                .url(Network.User.USER_STORE_DESCRIBE)
                .params(params)
                .build()
                .execute(new DCallback<StoreDescribe>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(StoreDescribe response) {
                        if (isSuccess(response)) {
                            if (response.store != null) {
                                showStoreDescribe(response.store);
                            }
                        }
                    }
                });
    }

    private void showStoreDescribe(StoreDescribe.Store store) {
        Log.i("XXXXXXXXXXXXXX", store.cover);
        reserveInfoBinding.setStore(store);
        String d = "";
        // 距离
        if ("-1".equals(store.distance)) {

        } else {
            double distance = Double.parseDouble(store.distance);
            DecimalFormat df = new DecimalFormat("#.0");
            if (distance < 1000) {
                d = store.distance + "m";
            } else {// <100km
                d = df.format(distance / 1000) + "km";
            }
        }
        mTvReserveInfoAddress.setText(store.area + "    " + d);
        // 折扣
        if (!TextUtils.isEmpty(store.discount) && store.discount.length() > 0) {
            SpannableString text = new SpannableString(store.discount + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvReserveInfoRealDiscount.setText(text, TextView.BufferType.SPANNABLE);
            mTvReserveInfoRealDiscount.setVisibility(View.VISIBLE);
        }
        // 最低消费
        if (!TextUtils.isEmpty(store.capita) && store.capita.length() > 1) {
            SpannableString text = new SpannableString(store.capita + "起");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvReserveLittleConsume.setText(text, TextView.BufferType.SPANNABLE);
            mTvReserveLittleConsume.setVisibility(View.VISIBLE);
        }

        if (TextUtils.equals("0", store.park)) {
            mEtReserveInfoCarport.setHint("不提供停车位");
            mEtReserveInfoCarport.setEnabled(false);
        }
        reservedTime = store.reserved_time;
        storeName = store.name;
    }


    /**
     * 选择房间类型
     */
    private void showRoomType() {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.ROOM);
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.putExtra(Constants.STORE_NAME, mTvReserveInfoTitle.getText().toString());
        intent.putExtra(Constants.DISCOUNT_COUPON, mTvReserveInfoRealDiscount.getText().toString());
        intent.putExtra(Constants.ADDRESS, mTvReserveInfoAddress.getText().toString() + "  " + mTvReserveInfoDistance.getText().toString());
        intent.putExtra(Constants.IS_CHOOSE, true);
        startActivity(intent);

//
    }

    /**
     * 预订日期，到店时间
     */
    private void showReserveDateTime() {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        intent.putExtra(Constants.PAGE, Constants.DATE);
        intent.putExtra(Constants.TIMES, reservedTime);
        intent.putExtra("Date", tvYdtext.getText().toString().trim());
        intent.putExtra(Constants.TYPE, type);
        Log.w("llm", reservedTime);
        startActivity(intent);


//
    }


    /**
     * 注册广播
     */
    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_WAITER_PHOTO_CONFIRM);
        filter.addAction(Constants.ACTION_PAY_SUCCESS);
        filter.addAction(Constants.ACTION_WXPAY_SUCCESS);
        filter.addAction(Constants.ACTION_SELECT_ROOM);
        filter.addAction(Constants.ACTION_SELECT_DATE_TIME);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constants.ACTION_WAITER_PHOTO_CONFIRM.equals(action)) {

                waiterId = intent.getStringExtra(Constants.WAITER_ID);
            } else if (Constants.ACTION_PAY_SUCCESS.equals(action) || Constants.ACTION_WXPAY_SUCCESS.equals(action)) {
                mContext.finish();
            } else if (Constants.ACTION_SELECT_ROOM.equals(action)) {
                mTvReserveInfoRoom.setText(intent.getStringExtra(Constants.NAME) + " (" + intent.getStringExtra(Constants.MONEY) + ")");
                mTvReserveInfoRoom.setTextColor(getResources().getColor(R.color.text_black));
                room_ids = intent.getStringExtra(Constants.ROOM);
                number = "0";
                tv2.setText("");
            } else if (Constants.ACTION_SELECT_DATE_TIME.equals(action)) {//到店时间
                reserveDate = intent.getStringExtra(Constants.TIMES);
                mTvReserveInfoTime.setText(reserveDate);
                mTvReserveInfoTime.setTextColor(getResources().getColor(R.color.text_black));
            }
        }
    }

    /**
     * 构造下单参数
     * 判断传入参数的正确性
     *
     * @param storeId
     * @param nickname
     * @param mobile
     * @param roomId
     * @param type
     * @param arrivalTime
     * @param waiterId
     * @param carNumber
     * @param mans
     * @param remark
     */
    private HashMap<String, String> constructionParam(String storeId, String nickname, String mobile,
                                                      String roomId, String type, String arrivalTime,
                                                      String waiterId, String carNumber, String mans,
                                                      String remark) {
        Log.d("aaa", "constructionParam:\n storeId = " + storeId + "\n nickname = " + nickname
                + "\n mobile = " + mobile + "\n roomId = " + roomId + "\n type = " + type + "\n arrivalTime = " + arrivalTime + "\n waiterId = " +
                waiterId + "\n carNumber = " + carNumber + "\n mans = " + mans + "\n remark = " + remark);
        // 必填参数有效行验证
        if (storeId == null || storeId.isEmpty()) {
            return null;
        }
        if (nickname == null || nickname.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, UIUtils.getString(R.string.hint_name));
            return null;
        }
        if (mobile == null || mobile.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, UIUtils.getString(R.string.hint_mobile));
            return null;
        }
        if (roomId == null || roomId.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, "请选择房型");
            return null;
        }
        if (type == null || type.isEmpty()) {
            return null;
        }
        if (TextUtils.isEmpty(mTvReserveInfoTime.getText().toString().trim())) {
            ToastUtils.makePicTextShortToast(mContext, "请选择到店时间");
            return null;
        }
        HashMap<String, String> param = new HashMap<>();
        // 必填参数
        param.put(Network.Param.TOKEN, mUser.getToken());
        param.put(Network.Param.STORE_ID, storeId);
        param.put(Network.Param.NICKNAME, nickname);
        param.put(Network.Param.MOBILE, mobile);
        param.put(Network.Param.ROOM_ID, roomId);
        param.put(Network.Param.TYPE, type);
        param.put(Network.Param.TYPE, type);
        param.put("number", number);
        try {
            param.put(Network.Param.ARRIVAL_TIME, String.valueOf(DateFormatUtil.parseDateFromYYYY_MM_DD_HH_mmStr(arrivalTime).getTime() / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (mans == null || mans.isEmpty()) {
//            param.put(Network.Param.MANS, String.valueOf(1));
            ToastUtils.makePicTextShortToast(mContext, "请输入人数");
            return null;
        } else if (TextUtils.equals("0", mans)) {
            ToastUtils.makePicTextShortToast(mContext, "人数不能为0");
            return null;
        } else {
            param.put(Network.Param.MANS, mans);
        }
        // 可选参数
        if (waiterId != null && !waiterId.isEmpty()) {
            param.put(Network.Param.WAITER_ID, waiterId);
        }
        if (carNumber != null && !carNumber.isEmpty()) {
            if (Integer.parseInt(carNumber) > Integer.parseInt(mans)) {
                ToastUtils.makePicTextShortToast(mContext, "车位不能超过人数！");
                return null;
            }
            param.put(Network.Param.CAR_NUM, carNumber);
        }
        if (remark != null && !remark.isEmpty()) {
            param.put(Network.Param.REMARK, remark);
        }
        Logger.d(param);
        return param;
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_reserve_info_prepay:
                type = Constants.RESERVE_WAY_PREPAY;
                if (reserveDate != null) {
                    mTvReserveInfoTime.setText(reserveDate);
                }
                break;
            case R.id.rb_reserve_info_credit:
                type = Constants.RESERVE_WAY_CREDIT;
                mTvReserveInfoTime.setText(R.string.please_choose_arrive_time);
                break;
            default:
                break;
        }
    }

    /**
     * 去支付
     */
    private void goToPay() {
        String nickname = mEtReserveInfoName.getText().toString();
        String mobile = mEtReserveInfoMobile.getText().toString().trim();
        HashMap params = constructionParam(storeId, nickname, mobile, room_ids, type,
                tvYdtext.getText().toString().trim() + " " + mTvReserveInfoTime.getText().toString(), waiterId, mEtReserveInfoCarport.getText().toString(),
                mEtReserveInfoNumberOfPeople.getText().toString().trim(), mEtReserveInfoRemark.getText().toString());
        if (params == null) {
            return;
        }
        Intent intent = new Intent(mContext, DownPaymentActivity.class);
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.putExtra(Constants.PARAM, params);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
    }

}
