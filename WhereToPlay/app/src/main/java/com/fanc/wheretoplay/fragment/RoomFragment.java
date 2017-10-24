package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.HouseTypeAdapter;
import com.fanc.wheretoplay.adapter.RoomTypeAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentRoomBinding;
import com.fanc.wheretoplay.datamodel.RoomCategory;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/14.
 */

public class RoomFragment extends BaseFragment {
    FragmentRoomBinding binding;
    /**
     * UI控件
     */
    TopMenu mTmRoom;
    TextView mTvRoomTitle;
    TextView mTvRoomDiscount;
    TextView mTvRoomAddress;
    //    TextView mTvRoomDistance;
    RecyclerView mRvRoom;
    /**
     * 店铺信息
     */
    String mStoreId;
    String mStoreName;
    String mStoreAddress;
    String mStoreDiscount;
    /**
     * 房型列表
     */
    List<RoomCategory.Room> rooms;
    RoomTypeAdapter roomTypeAdapter;
    //  是否选择房型
    boolean isSelect;
    //列表图
    private RecyclerView mRC;
    //集合
    private ArrayList<String> typeName;
    private ArrayList<String> price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room, container, false);
        initView();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initView() {
        mTmRoom = binding.tmRoom;
        mTvRoomAddress = binding.tvRoomAddress;
        mTvRoomDiscount = binding.tvRoomDiscountReal;
//        mTvRoomDistance = binding.tvRoomDistance;
        mTvRoomTitle = binding.tvRoomTitle;
        mRC = binding.rcMerchantHousetype;

    }

    private void init() {
        mTmRoom.setLeftIcon(R.drawable.left);
        mTmRoom.setTitle(R.string.room_category);
        mTmRoom.setTitleColor(getResources().getColor(R.color.white));
        // 店铺信息
        mTvRoomTitle.setText(mStoreName);
        mTvRoomAddress.setText(mStoreAddress);
        SpannableString text = new SpannableString(mStoreDiscount);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvRoomDiscount.setText(text, TextView.BufferType.SPANNABLE);

        //列表
        typeName = new ArrayList<>();
        price = new ArrayList<>();
        typeName.add("小包(2-5人)");
        typeName.add("中包(6-10人)");
        typeName.add("大包(11-15人)");
        typeName.add("卡座");
        typeName.add("散台");
        price.add("1300");
        price.add("3800");
        price.add("5800");
        price.add("1000");
        price.add("500");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRC.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRC.addItemDecoration(divider1);
        HouseTypeAdapter houseTypeAdapter = new HouseTypeAdapter(mContext, typeName, price);
        mRC.setAdapter(houseTypeAdapter);

        //旧板块
//        // 房型列表
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRvRoom.setLayoutManager(linearLayoutManager);
//        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL);
//        mRvRoom.addItemDecoration(divider);
//        rooms = new ArrayList<>();
//        roomTypeAdapter = new RoomTypeAdapter(mContext, rooms);
//        if (isSelect) {
//            roomTypeAdapter.setSelect(true);
//        }
//        mRvRoom.setAdapter(roomTypeAdapter);
//        // 获取列表
//        getRoomList();

    }

    private void initListener() {
        mTmRoom.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    /**
     * 获取房型列表
     */
//    private void getRoomList() {
//        showProgress();
//        OkHttpUtils.post()
//                .url(Network.User.PUBLIC_ROOM_TYPE)
//                .addParams(Network.Param.STORE_ID, mStoreId)
//                .build()
//                .execute(new DCallback<RoomCategory>() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        connectError();
//                    }
//
//                    @Override
//                    public void onResponse(RoomCategory response) {
//                        if (isSuccess(response)) {
//                            if (response.room != null) {
//                                showRoomList(response.room);
//                            }
//                        }
//                    }
//                });
//    }

    private void showRoomList(List<RoomCategory.Room> rooms) {
        this.rooms.addAll(rooms);
        roomTypeAdapter.notifyDataSetChanged();
    }

    public RoomFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    public RoomFragment setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
        return this;
    }

    public RoomFragment setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
        return this;
    }

    public RoomFragment setStoreDiscount(String mStoreDiscount) {
        this.mStoreDiscount = mStoreDiscount;
        return this;
    }

    public RoomFragment setSelect(boolean select) {
        isSelect = select;
        return this;
    }
}
