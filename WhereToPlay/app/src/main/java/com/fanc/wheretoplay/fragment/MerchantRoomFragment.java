package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.HouseTypeAdapter;
import com.fanc.wheretoplay.adapter.RoomTypeAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentRoomBinding;
import com.fanc.wheretoplay.datamodel.HouseTypeList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;
import java.util.List;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MerchantRoomFragment extends BaseFragment {
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
    //  是否选择房型
    boolean isSelect;
    //列表图
    private RecyclerView mRC;
    private List<HouseTypeList.RoomBean> roomBean;


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
        //打折
        if (mStoreDiscount.length() == 0) {
            mTvRoomDiscount.setVisibility(View.GONE);
        } else {
            SpannableString text = new SpannableString(mStoreDiscount);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvRoomDiscount.setText(text, TextView.BufferType.SPANNABLE);
        }

        //列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRC.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRC.addItemDecoration(divider1);

        // 获取列表
        getRoomList();

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
    private void getRoomList() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_HOUSE_TYPE)
                .addParams(Network.Param.STORE_ID, mStoreId)
                .build()
                .execute(new DCallback<HouseTypeList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        mTvRoomAddress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(HouseTypeList response) {
                        if (isSuccess(response)) {
                            if (response.getRoom() != null) {
                                roomBean = response.getRoom();
                                showRoomList(roomBean);
                            }
                        }
                    }
                });
    }

    private void showRoomList(List<HouseTypeList.RoomBean> rooms) {
        HouseTypeAdapter houseTypeAdapter = new HouseTypeAdapter(mContext, rooms);
        //如果进入在线预订界面进行房型选择，则为true
        if (isSelect) {
            houseTypeAdapter.setSelect(true);
        }
        mRC.setAdapter(houseTypeAdapter);
    }

    public MerchantRoomFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    public MerchantRoomFragment setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
        return this;
    }

    public MerchantRoomFragment setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
        return this;
    }

    public MerchantRoomFragment setStoreDiscount(String mStoreDiscount) {
        this.mStoreDiscount = mStoreDiscount;
        return this;
    }

    public MerchantRoomFragment setSelect(boolean select) {
        isSelect = select;
        return this;
    }
}
