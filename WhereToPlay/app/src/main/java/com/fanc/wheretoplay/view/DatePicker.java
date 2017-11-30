package com.fanc.wheretoplay.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.callback.TimeSelectedCallback;
import com.fanc.wheretoplay.databinding.ViewDatePickerBinding;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by llm on 2017/3/14.
 */

public class DatePicker extends LinearLayout {

    Context mContext;

    WheelView mWvYear;
    WheelView mWvMonth;
    WheelView mWvDay;

    private final String YEAR = "年";
    private final String MONTH = "月";
    private final String DAY = "日";
    List<String> years, months, days;
    WheelView.LineConfig config;// 线条颜色

    private String selectedYear;
    private String selectedMonth;
    private String selectedDay;
    private int currentYear = -1;
    private int currentMonth = -1;
    private int currentDay = -1;
    int maxDays;// 每月中最大天数
    private TextView textView;// 显示选中时间的控件

    private TimeSelectedCallback mTimeSelectedCallback;
    // 今年
    private int thisYear;

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        ViewDatePickerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.view_date_picker, this, true);
        mWvYear = binding.wvYear;
        mWvMonth = binding.wvMonth;
        mWvDay = binding.wvDay;

//        View.inflate(getContext(), R.layout.view_date_picker, this);
        init();
    }

    private void init() {
        config = new WheelView.LineConfig();
        setYears();
        setMonths();
        setDays();
        setLineConfig();
        setListeners();
    }

    private void setYears() {
        if (years == null) {
            years = new ArrayList<>();
            int thisYear = DateFormatUtil.getCurrentYear();
            for (int i = thisYear - 99; i <= thisYear; i++) {
                years.add(i + YEAR);
            }
        }
        if (currentYear == -1 && selectedYear == null) {
            currentYear = DateFormatUtil.getCurrentYear();
            selectedYear = currentYear + YEAR;
            mWvYear.setItems(years, selectedYear);
        } else if (selectedYear != null) {
            mWvYear.setItems(years, selectedYear);
        } else if (currentYear != -1) {
            mWvYear.setItems(years, currentYear);
        }
    }

    public void setYears(int years) {
        if (years < 0) {
            return;
        }
        if (this.years == null) {
            this.years = new ArrayList<>();
        }
        thisYear = DateFormatUtil.getCurrentYear();
        for (int i = thisYear - years; i <= thisYear; i++) {
            this.years.add(i + YEAR);
        }
        if (currentYear == -1 && selectedYear == null) {
            currentYear = DateFormatUtil.getCurrentYear();
            selectedYear = currentYear + YEAR;
            mWvYear.setItems(this.years, selectedYear);
        } else if (selectedYear != null) {
            mWvYear.setItems(this.years, selectedYear);
        } else if (currentYear != -1) {
            mWvYear.setItems(this.years, currentYear);
        }
    }

    private void setMonths() {
        if (months == null) {
            months = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                if (i < 10) {
                    months.add("0" + i + MONTH);
                    continue;
                }
                months.add(i + MONTH);
            }
        }

        if (currentMonth == -1 && selectedMonth == null) {
            currentMonth = DateFormatUtil.getCurrentMonth();
            selectedMonth = currentMonth + MONTH;
            mWvMonth.setItems(months, currentMonth - 1);
        } else if (selectedMonth != null) {
            mWvMonth.setItems(months, selectedMonth);
        } else {
            mWvMonth.setItems(months, currentMonth);
        }
    }

    private void setDays() {
        maxDays = DateFormatUtil.getDaysByYearMonth(currentYear, currentMonth);
        if (days == null) {
            days = new ArrayList<>();
            for (int i = 1; i <= maxDays; i++) {
                if (i < 10) {
                    days.add("0" + i + DAY);
                    continue;
                }
                days.add(i + DAY);
            }
        }
        if (currentDay == -1 && selectedDay == null) {
            currentDay = DateFormatUtil.getCurrentDay();
            selectedDay = currentDay + DAY;
            mWvDay.setItems(days, currentDay - 1);
        } else if (selectedDay != null) {
            mWvDay.setItems(days, selectedDay);
        } else {
            mWvDay.setItems(days, currentDay);
        }
    }

    private void setListeners() {
        mWvYear.setOnWheelListener(new WheelView.OnWheelListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {
                selectedYear = item;
                currentYear = Integer.parseInt(item.substring(0, item.length() - 1));
//                textView.setText(getSelectedDate());
                mTimeSelectedCallback.onSelected(getSelectedDate());
            }
        });
        mWvMonth.setOnWheelListener(new WheelView.OnWheelListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {
                selectedMonth = item;
                currentMonth = index + 1;
                int current = DateFormatUtil.getCurrentMonth();
                int day = DateFormatUtil.getCurrentDay();
                if (currentMonth >= current) {
                    if (currentMonth > current) {
                        ToastUtils.showShortToast(mContext, "不能选择未来的日期");
                    }
                    mWvMonth.setSelectedIndex(current - 1);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            selectedMonth = mWvMonth.getSelectedItem();
                            currentMonth = mWvMonth.getSelectedIndex() + 1;
                        }
                    }, 500);
                    if (currentDay > day) {
                        if (currentMonth == current) {
                            ToastUtils.showShortToast(mContext, "不能选择未来的日期");
                        }
                        mWvDay.setSelectedIndex(day - 1);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                selectedDay = mWvDay.getSelectedItem();
                                currentDay = mWvDay.getSelectedIndex() + 1;
                            }
                        }, 500);
                    }
                    return;
//                } else if (currentMonth == current) {
//                    if (currentDay > day) {
//                        ToastUtils.showShortToast(mContext, "生日不能选择未来的时间");
//                        mWvDay.setSelectedIndex(day - 1);
//                        postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                selectedDay = mWvDay.getSelectedItem();
//                                currentDay = mWvDay.getSelectedIndex() + 1;
//                            }
//                        }, 500);
//                        return;
//                    }
                }
//                if (!isFirst) {
                maxDays = DateFormatUtil.getDaysByYearMonth(currentYear, currentMonth);
                days.clear();
                for (int i = 1; i <= maxDays; i++) {
                    if (i < 10) {
                        days.add("0" + i + DAY);
                        continue;
                    }
                    days.add(i + DAY);
                }
                if (maxDays < currentDay) {
                    mWvDay.setItems(days, maxDays - 1);
                } else {
                    mWvDay.setItems(days, currentDay - 1);
                }
//                } else {
//                    isFirst = false;
//                }
//                textView.setText(getSelectedDate());
                mTimeSelectedCallback.onSelected(getSelectedDate());
            }
        });
        mWvDay.setOnWheelListener(new WheelView.OnWheelListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {
                selectedDay = item;
                currentDay = index + 1;
                int current = DateFormatUtil.getCurrentDay();
                if (currentMonth >= DateFormatUtil.getCurrentMonth()) {
                    if (currentDay > current) {
                        ToastUtils.showShortToast(mContext, "不能选择未来的日期");
                        mWvDay.setSelectedIndex(current - 1);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                selectedDay = mWvDay.getSelectedItem();
                                currentDay = mWvDay.getSelectedIndex() + 1;
                            }
                        }, 500);
                        return;
                    }
                }
//                textView.setText(getSelectedDate());
                mTimeSelectedCallback.onSelected(getSelectedDate());
            }
        });
    }

    /**
     * 设置偏移的个数
     *
     * @param offset
     */
    public void setOffset(int offset) {
        mWvYear.setOffset(offset);
        mWvMonth.setOffset(offset);
        mWvDay.setOffset(offset);
    }

    public void setTextColor(int color) {
        mWvYear.setTextColor(color);
        mWvMonth.setTextColor(color);
        mWvDay.setTextColor(color);
    }

    public void setTextColor(int selectColor, int normalColor) {
        mWvYear.setTextColor(selectColor, normalColor);
        mWvMonth.setTextColor(selectColor, normalColor);
        mWvDay.setTextColor(selectColor, normalColor);
    }

    /**
     * 分割线颜色
     *
     * @param color
     */
    public void setLineColor(int color) {
        config.setColor(color);
        setLineConfig();
    }

    /**
     * 线条透明度
     * 1--255
     *
     * @param alpha
     */
    public void setLineAlpha(int alpha) {
        config.setAlpha(alpha);
        setLineConfig();
    }

    /**
     * 线条长度
     * 0最长，1最短
     *
     * @param ratio
     */
    public void setLineRatio(float ratio) {
        config.setRatio(ratio);
        setLineConfig();
    }

    /**
     * 设置线条粗细
     *
     * @param thick
     */
    public void setLineThick(int thick) {
        config.setThick(thick);
        setLineConfig();
    }

    private void setLineConfig() {
        mWvYear.setLineConfig(config);
        mWvMonth.setLineConfig(config);
        mWvDay.setLineConfig(config);
    }


    /**
     * 设置年份范围
     *
     * @param list
     */
    public void setYearItems(List<String> list) {
        if (list != null) {
            years = list;
        }
    }

    /**
     * 设置月份范围
     *
     * @param list
     */
    public void setMonthItems(List<String> list) {
        if (list != null) {
            months = list;
        }
    }

    /**
     * 设置日范围
     *
     * @param list
     */
    public void setDayItems(List<String> list) {
        if (list != null) {
            days = list;
        }
    }

    // 设置显示日期的控件
    public void setTextView(TextView tv) {
        if (tv == null) {
            tv = new TextView(getContext());
        }
        textView = tv;
    }

    public void setTimeSelectedCallback(TimeSelectedCallback mTimeSelectedCallback) {
        this.mTimeSelectedCallback = mTimeSelectedCallback;
    }

    public void setYearSelected(int index) {
        currentYear = index;
    }

    public void setYearSelected(String item) {
        selectedYear = item;
    }

    public void setMonthSelected(int index) {
        currentMonth = index;
    }

    public void setMonthSelected(String item) {
        selectedMonth = item;
    }

    public void setDaySelected(int index) {
        currentDay = index;
    }

    public void setDaySelected(String item) {
        selectedDay = item;
    }

    public int getSelectedYear() {
        return Integer.parseInt(mWvYear.getSelectedItem().substring(0, 4));
    }

    public int getSelectedMonth() {
        return mWvMonth.getCurrentPosition() + 1;
    }

    public int getSelectedDay() {
        return mWvDay.getCurrentPosition() + 1;
    }

    public int getSelectedYearIndex() {
        return mWvYear.getSelectedIndex();
    }

    public int getSelectedMonthIndex() {
        return mWvMonth.getSelectedIndex();
    }

    public int getSelectedDayIndex() {
        return mWvDay.getSelectedIndex();
    }

    public String getSelectedYearItem() {
        return mWvYear.getSelectedItem();
    }

    public String getSelectedMonthItem() {
        return mWvMonth.getSelectedItem();
    }

    public String getSelectedDayItem() {
        return mWvDay.getSelectedItem();
    }

    public String getSelectedDate() {
        String month = null, day = null;
        if (currentMonth < 10) {
            month = "0" + currentMonth;
        } else {
            month = String.valueOf(currentMonth);
        }
        if (currentDay < 10) {
            day = "0" + currentDay;
        } else {
            day = String.valueOf(currentDay);
        }
        return currentYear + "-" + month + "-" + day;
    }

}
