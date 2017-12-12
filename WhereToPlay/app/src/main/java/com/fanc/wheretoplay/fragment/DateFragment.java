package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.DateAdapter;
import com.fanc.wheretoplay.adapter.DateTimeAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentDateBinding;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class DateFragment extends BaseFragment {
    FragmentDateBinding binding;
    /**
     * UI控件
     */
    TopMenu mTmDate;
    TextView mTvDataMonth;
    TextView mTvDataYear;
    RecyclerView mRvDate;
    GridView mGvTime;
    Button mBtnDateConfirm;
    /**
     * 日期列表
     */
    List<Date> mDate;
    DateAdapter mDateAdapter;
    /**
     * 时间列表
     */
    List<String> mTime;
    DateTimeAdapter mDateTimeAdaper;
    /**
     * 预定类型
     */
    String reserveWay;
    /**
     * 商家设置的最晚预定时间
     */
    String lastTime;
    /**
     * 选择的时间
     */
    Date selectedDate;
    String selectedTime;
    /**
     * 平台限制最晚预定时间
     */
    long last;
    /**
     * 时间可用的第一个position
     */
    int timePosition;
    // handler
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            timePosition = mDateTimeAdaper.getFirstEnableItemPosition();
            int selectedPosition = mDateTimeAdaper.getSelectedPosition();
            if (!mDateTimeAdaper.checkSelectedItemIsEnable(selectedPosition)) {
                mDateTimeAdaper.setItemStatus(timePosition);
                if (mDateTimeAdaper.listener != null) {
                    mDateTimeAdaper.listener.onItemClick(timePosition);
                }
//            } else {
//                if (mDateTimeAdaper.listener != null) {
//                    mDateTimeAdaper.listener.onItemClick(selectedPosition);
//                }
            }
        }
    };

    public final int DAY = 1000 * 60 * 60 * 24;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date, container, false);
        initViews();
        init();
        initListener();
        return binding.getRoot();
    }

    private void initViews() {
        mTmDate = binding.tmDate;
        mTvDataMonth = binding.tvDateMonth;
        mTvDataYear = binding.tvDateYear;
        mRvDate = binding.rvDate;
        mGvTime = binding.gvTime;
        mBtnDateConfirm = binding.btnDateConfirm;
    }

    private void init() {
        mTmDate.setLeftIcon(R.drawable.left);
        mTmDate.setTitle(R.string.choose_date);
        mTmDate.setTitleColor(getResources().getColor(R.color.white));
        // 最晚时间
        last = DateFormatUtil.parseStringTolong("23:30", DateFormatUtil.HHmm);

        // 年月
        mTvDataMonth.setText(DateFormatUtil.getCurrentMonth() + UIUtils.getString(R.string.month));
        mTvDataYear.setText(String.valueOf(DateFormatUtil.getCurrentYear()));
        // 日期列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvDate.setLayoutManager(linearLayoutManager);
        mDate = new ArrayList();
        for (int i = 0; i < 7; i++) {
            Date date = new Date(System.currentTimeMillis() + i * DAY);
            mDate.add(date);
        }
        mDateAdapter = new DateAdapter(mContext, mDate);
        mRvDate.setAdapter(mDateAdapter);
        // 时间列表
        mTime = Arrays.asList(UIUtils.getStrings(R.array.reserve_time));


        //判断是不是今天



        if (DateFormatUtil.isCurrentDay(lastDate)){
            mDateTimeAdaper = new DateTimeAdapter(mContext, mTime,true);
        }else {
            mDateTimeAdaper = new DateTimeAdapter(mContext, mTime,false);
        }

        if (TextUtils.equals(Constants.RESERVE_WAY_CREDIT, reserveWay)) {
            mDateTimeAdaper.setLastTime(lastTime);
        }
        mGvTime.setAdapter(mDateTimeAdaper);
        // 默认选中第一项
        selectedDate = new Date(mDate.get(0).getTime());
        // 时间默认选中可用的第一项
        selectedTime = mTime.get(0);// 防止异常
        mHandler.sendEmptyMessageDelayed(255, 50);
    }

    private void initListener() {
        mTmDate.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mDateAdapter.setOnItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedDate = new Date(mDate.get(position).getTime());
                mDateTimeAdaper.setDay(DateFormatUtil.getCustomDay(selectedDate));
                if (DateFormatUtil.parseStringTolong(selectedTime, DateFormatUtil.HHmm) <= last) {
                    long date = selectedDate.getTime() + DAY;
                    selectedDate.setTime(date);
                }
                mTvDataMonth.setText(DateFormatUtil.getCustomMonth(selectedDate) + UIUtils.getString(R.string.month));
                mTvDataYear.setText(String.valueOf(DateFormatUtil.getCustomYear(selectedDate)));
                // 时间选择时，选中第一个
                mHandler.removeMessages(255);
                mHandler.sendEmptyMessageDelayed(255, 50);
            }
        });
        mDateTimeAdaper.setOnItemClickListener(new DateTimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedTime = mTime.get(position);


               /* if (DateFormatUtil.parseStringTolong(selectedTime, DateFormatUtil.HHmm) <= last) {
                    if (selectedDate.getTime() - mDate.get(mDateAdapter.getSelectedPosition()).getTime() < DAY) {
                        long date = selectedDate.getTime() + DAY;
                        selectedDate.setTime(date);
                    }
                }*/
            }
        });
        mBtnDateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Constants.ACTION_SELECT_DATE_TIME);
                String date =  selectedTime;
                intent.putExtra(Constants.TIMES, date);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                mContext.finish();
            }
        });
    }

    public DateFragment setReserveWay(String reserveWay) {
        this.reserveWay = reserveWay;
        return this;
    }

    public DateFragment setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String lastDate;
    public DateFragment setLastDate(String lastDate) {
        this.lastDate = lastDate;
        return this;
    }
}
