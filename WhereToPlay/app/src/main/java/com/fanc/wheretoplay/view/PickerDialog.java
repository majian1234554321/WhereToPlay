package com.fanc.wheretoplay.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.callback.TimeSelectedCallback;
import com.fanc.wheretoplay.databinding.DialogPickerBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class PickerDialog {
    Context mContext;
    Dialog mDialog;

    TextView mTvPickerTitle;
    DatePicker mDpBirthday;
    LinearLayout mLlPickerCity;
    WheelView mWvProvince;
    WheelView mWvCity;
    Button mBtnConfirm;
    Button mBtnCancel;

    boolean isSelectBirthday;

    // 选中的日期，省市
    String date;
    String province;
    String city;
    int provinceIndex;
    int cityIndex;
    List<String> cities;

    List<CityResource.Province> provinces;

    public PickerDialog(Context mContext) {
        this.mContext = mContext;
        mDialog = new Dialog(mContext, R.style.DialogStyle);
        init();
    }

    private void init() {
        DialogPickerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_picker, null, false);
        mTvPickerTitle = binding.tvDialogPickerTitle;
        mDpBirthday = binding.dpDialogPickerDate;
        mLlPickerCity = binding.llDialogPickerCity;
        mWvCity = binding.wvDialogCity;
        mWvProvince = binding.wvDialogProvince;
        mBtnCancel = binding.btnDialogPickerCancel;
        mBtnConfirm = binding.btnDialogPickerConfirm;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (UIUtils.getScreenWidth() * 0.8),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(binding.getRoot(), layoutParams);

        setBirthdayContent();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public PickerDialog show() {
        mDialog.show();
        return this;
    }

    public PickerDialog setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        return this;
    }

    /**
     * 设置生日选择内容
     *
     * @return
     */
    public PickerDialog setBirthdayContent() {
        isSelectBirthday = true;
        mDpBirthday.setVisibility(View.VISIBLE);
        mTvPickerTitle.setText(R.string.please_choose);
        mLlPickerCity.setVisibility(View.GONE);
        // 年月日选择器
        mDpBirthday.setOffset(1);
        mDpBirthday.setLineRatio(0);
        mDpBirthday.setLineAlpha(100);
        mDpBirthday.setLineColor(mContext.getResources().getColor(R.color.gray));
        mDpBirthday.setTextColor(mContext.getResources().getColor(R.color.text_red));

        setOnDateSelected();
        return this;
    }

    /**
     * 选择省市
     *
     * @return
     */
    public PickerDialog setCityContent(List<CityResource.Province> provinces) {
        this.provinces = provinces;
        isSelectBirthday = false;
        mDpBirthday.setVisibility(View.GONE);
        mTvPickerTitle.setText(R.string.please_choose_province_and_city);
        mLlPickerCity.setVisibility(View.VISIBLE);
        WheelView.LineConfig config = new WheelView.LineConfig();
        config.setRatio(0);
        config.setAlpha(100);
        config.setColor(mContext.getResources().getColor(R.color.gray));
        mWvProvince.setOffset(1);
        mWvProvince.setLineConfig(config);
        mWvProvince.setTextColor(mContext.getResources().getColor(R.color.text_red));

        mWvCity.setOffset(1);
        mWvCity.setLineConfig(config);
        mWvCity.setTextColor(mContext.getResources().getColor(R.color.text_red));

        // 省市选择器
        setCityPickerData();
        setOnCitySelected();
        return this;
    }

    private void setCityPickerData() {
        List<String> provinces = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        for (int i = 0; i < this.provinces.size(); i++) {
            CityResource.Province province = this.provinces.get(i);
            provinces.add(province.getName());
            Log.d("aaa", "setCityPickerData: j = " + province.getName());
            if (i == 0) {
                for (int j = 0; j < province.getChild().size(); j++) {
                    CityResource.City city = province.getChild().get(j);
                    cities.add(city.getName());
                }
            }

        }
        mWvProvince.setItems(provinces);
        mWvCity.setItems(cities);
    }

    // 日期选择监听
    private PickerDialog setOnDateSelected() {
        mDpBirthday.setTimeSelectedCallback(new TimeSelectedCallback() {
            @Override
            public void onSelected(String selectedTime) {
                date = selectedTime;
            }
        });
        return this;
    }

    // 城市监听
    private PickerDialog setOnCitySelected() {
        mWvProvince.setOnWheelListener(new WheelView.OnWheelListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {
                province = item;
                provinceIndex = index;
                List<String> cities = new ArrayList<>();
                for (int i = 0; i < provinces.get(index).getChild().size(); i++) {
                    cities.add(provinces.get(index).getChild().get(i).getName());
                }
                mWvCity.setItems(cities);
            }
        });
        mWvCity.setOnWheelListener(new WheelView.OnWheelListener() {
            @Override
            public void onSelected(boolean isUserScroll, int index, String item) {
                city = item;
                cityIndex = index;
            }
        });
        return this;
    }

    /**
     * 确定按钮
     *
     * @param listener
     * @return
     */
    public PickerDialog setConfirmClickListener(final OnBtnClickListener listener) {
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelectBirthday) {
                    if (date == null) {
                        date = mDpBirthday.getSelectedDate();
                    }
                    if (listener != null) {
                        listener.onBtnClick(v, date, 0, null, 0);
                    }
                    Log.d("aaa", "onClick: date = " + date);
                } else {
                    if (province == null) {
                        province = mWvProvince.getSelectedItem();
                    }
                    if (city == null) {
                        city = mWvCity.getSelectedItem();
                    }
                    Log.d("aaa", "onClick: province = " + province + "\tcity = " + city);
                    listener.onBtnClick(v, province, provinceIndex, city, cityIndex);
                }
                dismiss();
            }
        });
        return this;
    }

    /**
     * 取消按钮
     *
     * @return
     */
    public PickerDialog setCancelClickListener() {
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isSelectBirthday) {
//                    if (date == null) {
//                        date = mDpBirthday.getSelectedDate();
//                    }
//                    if (listener != null) {
//                        listener.onBtnClick(v, date);
//                    }
//                } else {
//                    if (province == null) {
//                        province = mWvProvince.getSelectedItem();
//                    }
//                    if (city == null) {
//                        city = mWvCity.getSelectedItem();
//                    }
//                    listener.onBtnClick(v, province + city);
//                }
                dismiss();
            }
        });
        return this;
    }

    public PickerDialog setTitle(String title) {
        mTvPickerTitle.setText(title);
        return this;
    }

    public PickerDialog setTitle(int titleId) {
        mTvPickerTitle.setText(titleId);
        return this;
    }

    public interface OnBtnClickListener {
        void onBtnClick(View view, String selectedString, int provinceIndex, String city, int cityIndex);
    }

}
