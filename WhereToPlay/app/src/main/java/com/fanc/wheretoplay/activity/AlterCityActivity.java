package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.CityAdapter;
import com.fanc.wheretoplay.adapter.CitySearchResultAdapter;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivityAlterCityBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.SideLetterBar;
import com.fanc.wheretoplay.view.TopMenu;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/21.
 */

public class AlterCityActivity extends BaseActivity {
    ActivityAlterCityBinding cityBinding;

    TopMenu mTmAlterCity;
    EditText mEtAlterCity;
    ListView mLvAlterCityAll;
    TextView mTvAlterCitySelectedLetter;
    SideLetterBar mSlbAlterCity;
    ListView mLvAlterCityResult;
    TextView mTvAlterCitySearchError;
    TextView mTvAlterCityLoading;

    // 所有城市
    List<CityResource.Province> provinces;
    List<CityResource.City> cities;
    List<CityResource.City> hotCities;
    CityAdapter mCityAdapter;

    // 搜索结果
    List<CityResource.City> searchResult;
    CitySearchResultAdapter resultAdapter;
    /**
     * 正则匹配
     */
    Pattern pattern;

    InnerHandler handler;

    String[] hotCityStrings = new String[]{"北京", "上海", "广州", "深圳", "杭州", "南京", "温州", "宁波", "重庆", "厦门"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityBinding = DataBindingUtil.setContentView(this, R.layout.activity_alter_city);
        initViews();
        init();
        setListeners();
    }



    private void initViews() {
        mTmAlterCity = cityBinding.tmAlterCity;
        mEtAlterCity = cityBinding.etAlterCity;
        mLvAlterCityAll = cityBinding.lvAlterCityAll;
        mTvAlterCitySelectedLetter = cityBinding.tvAlterCitySelectedLetter;
        mSlbAlterCity = cityBinding.slbAlterCity;
        mLvAlterCityResult = cityBinding.lvAlterCityResult;
        mTvAlterCitySearchError = cityBinding.tvAlterCitySearchError;
        mTvAlterCityLoading = cityBinding.tvAlterCityLoading;

    }

    private void init() {
        mTmAlterCity.setLeftIcon(R.drawable.left);
        mTmAlterCity.setTitle(R.string.alter_city);
        mTmAlterCity.setTitleColor(getResources().getColor(R.color.white));

        //选中字母的显示控件
        mSlbAlterCity.setOverlay(mTvAlterCitySelectedLetter);

        handler = new InnerHandler();
        new Thread() {
            @Override
            public void run() {
                initCityList();
                handler.sendEmptyMessage(1);
            }
        }.start();

        //正则匹配
        pattern = Pattern.compile("^[A-Za-z]+$");

        cityBinding.setEditText(this);
    }

    private void setListeners() {
        mTmAlterCity.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSlbAlterCity.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mLvAlterCityAll.setSelection(position);
            }
        });
        mLvAlterCityResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (searchResult.size() > 0) {
                    CityResource.City city = searchResult.get(position);
                    Intent intent = new Intent(Constants.ACTION_CITY_SELECTED);
                    intent.putExtra(Constants.CITY, city);
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    finish();
                }
            }
        });
    }

    // 初始化城市列表
    private void initCityList() {
        //省列表
        provinces = getProvinceList();
        cities = getCityList();
        Collections.sort(cities);
        hotCities = getHotCities();
//        Collections.sort(hotCities);

        Logger.d("城市数量：" + cities.size());

    }

    /**
     * 省列表
     *
     * @return
     */
    private List<CityResource.Province> getProvinceList() {
        List<CityResource.Province> provinces = null;
        try {
            InputStream inputStream = getAssets().open("city.txt");
            int length = inputStream.available();
            byte[] cityBytes = new byte[length];
            inputStream.read(cityBytes);
            String json = new String(cityBytes);
//            Logger.json(json);
            Gson gson = new Gson();
            CityResource cityResource = gson.fromJson(json, CityResource.class);
            provinces = cityResource.provinces;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    /**
     * 获取城市列表
     *
     * @return
     */
    private List<CityResource.City> getCityList() {
        List<CityResource.City> cities = new ArrayList<>();
        for (int i = 0; i < provinces.size(); i++) {
            cities.addAll(provinces.get(i).getChild());
        }
        return cities;
    }

    private final String HOT_CITY = "1";

    /**
     * 热门城市
     *
     * @return
     */
    private List<CityResource.City> getHotCities() {
        List<CityResource.City> cities = new ArrayList<>();
        out:
        for (int i = 0; i < hotCityStrings.length; i++) {
            in:
            for (CityResource.City city : this.cities) {
//            if (HOT_CITY.equals(city.getStatus())) {
//                cities.add(city);
//            }
                if (city.getName().contains(hotCityStrings[i])) {// 添加热门城市
                    cities.add(city);
                    if (cities.size() == hotCityStrings.length) {// 热门城市添加完了，跳出循环
                        break out;
                    }
                    // 成功添加城市后，进行下一次循环
                    continue out;
                }
            }
        }
        return cities;
    }

    /**
     * 搜索框文字变化后
     *
     * @param s
     */
    public void afterTextChanged(Editable s) {
        String keyword = s.toString();
        if (TextUtils.isEmpty(keyword)) {
            mLvAlterCityAll.setVisibility(View.VISIBLE);
            mSlbAlterCity.setVisibility(View.VISIBLE);
            mLvAlterCityResult.setVisibility(View.GONE);
            mTvAlterCitySearchError.setVisibility(View.GONE);
        } else {
            searchResult.clear();
            if (pattern.matcher(keyword.substring(0, 1)).matches()) {
                for (CityResource.City city : cities) {
                    if (city.getPinyin().contains(keyword.toUpperCase())) {
                        searchResult.add(city);
                    }
                }
            } else {
                for (CityResource.City city : cities) {
                    if (city.getName().contains(keyword)) {
                        searchResult.add(city);
                    }
                }
            }
            resultAdapter.notifyDataSetChanged();
            mLvAlterCityResult.setVisibility(View.VISIBLE);
            mLvAlterCityAll.setVisibility(View.GONE);
            mSlbAlterCity.setVisibility(View.GONE);
            if (searchResult.size() < 1) {
                mTvAlterCitySearchError.setVisibility(View.VISIBLE);
            }
//            ToastUtils.showShortToast(mContext, "更新通知");
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mEtAlterCity.clearFocus();
        }
    }

    class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    finish();
                    break;
                case 1:
                    mTvAlterCityLoading.setVisibility(View.GONE);

                    mCityAdapter = new CityAdapter(mContext, new ArrayList<>(cities), hotCities);
                    mLvAlterCityAll.setAdapter(mCityAdapter);
                    // 搜索结果
                    searchResult = new ArrayList<>();
                    resultAdapter = new CitySearchResultAdapter(mContext, searchResult);
                    mLvAlterCityResult.setAdapter(resultAdapter);

                    mCityAdapter.setOnCityClickListener(new CityAdapter.OnCityClickListener() {
                        @Override
                        public void onCityClick(CityResource.City city) {
//                            ToastUtils.showShortToast(mContext, city.getName());
                            Intent intent = new Intent(Constants.ACTION_CITY_SELECTED);
                            intent.putExtra(Constants.CITY, city);
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            handler.sendEmptyMessageAtTime(0, 500);
                        }
                    });
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler = null;
        }
    }
}
