package com.fanc.wheretoplay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseAdapter;
import com.fanc.wheretoplay.databinding.ItemCityAllBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.PinyinUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.WrapHeightGridView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class CityAdapter extends BaseAdapter<CityResource.City> {

    List<CityResource.City> hotCities;

    /**
     * item view type count
     */
    private static final int VIEW_TYPE_COUNT = 3;
    /**
     * item view type
     */
    private static final int VIEW_TYPE_CURRENT = 0;// 当前城市
    private static final int VIEW_TYPE_HOT = 1;// 热门城市
    private static final int VIEW_TYPE_ALL = 2;// 全部城市
    /**
     * 字母的应该在的位置的下标
     */
    private HashMap<String, Integer> letterIndexes;
    /**
     * 字母数组
     */
    private String[] sections;

    private OnCityClickListener onCityClickListener;

    int screenWidth;

    String currentCity;
    int locateState = LocationUtils.LOCATING;

    /**
     * 构造方法
     *
     * @param context
     * @param data
     */
    public CityAdapter(Context context, List<CityResource.City> data, List<CityResource.City> hotCities) {
        super(context, data);
        this.hotCities = hotCities;


        getData().add(0, new CityResource.City("0", "定位", "0"));
        getData().add(1, new CityResource.City("1", "热门", "1"));

        screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;

        iniCitySpellLetter();

        if (LocationUtils.location != null) {
            currentCity = LocationUtils.location.getCity();
            if (TextUtils.isEmpty(currentCity)){
                getLocation();
            }else {
            locateState = LocationUtils.SUCCESS;
            }
        } else {
            getLocation();
        }
    }

    /**
     * 初始化城市拼音的首字母
     */
    private void iniCitySpellLetter() {
        int size = getData().size();
        letterIndexes = new HashMap<>();
        sections = new String[size];
        for (int index = 0; index < size; index++) {
            //当前城市拼音首字母
            String currentLetter = PinyinUtils.getFirstLetter(getData().get(index).getPinyin());
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? PinyinUtils.getFirstLetter(getData().get(index - 1).getPinyin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)) {
                letterIndexes.put(currentLetter, index);
                sections[index] = currentLetter;
            }
        }
    }

    /**
     * 获取字母索引的位置
     *
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter) {
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_CURRENT:// 定位
                convertView = getLayoutInflater().inflate(R.layout.item_city_local, null);
                final TextView mTvCurrentCity = convertView.findViewById(R.id.tv_item_city_local);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTvCurrentCity.getLayoutParams();
                lp.width = (screenWidth - UIUtils.dp2Px(86)) / 3;
                mTvCurrentCity.setLayoutParams(lp);
                switch (locateState) {   //结果
                    case LocationUtils.LOCATING:
                        mTvCurrentCity.setText("定位中");
                        break;
                    case LocationUtils.SUCCESS:
                        mTvCurrentCity.setText(currentCity.substring(0, currentCity.length() - 1));
                        break;
                    case LocationUtils.FAIL:
                        mTvCurrentCity.setText("定位失败");
                        break;
                }
                //定位text点击事件
                mTvCurrentCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (locateState == LocationUtils.FAIL) {   //如果显示失败就再定位，刷新之后会显示结果如何
                            getLocation();
                            mTvCurrentCity.setText("定位中");
                        } else if (locateState == LocationUtils.SUCCESS) {
                            if (onCityClickListener != null) {// 筛选点击的城市
                                for (int i = 2; i < getData().size(); i++) {
                                    CityResource.City city = getData().get(i);
                                    if (currentCity.contains(city.getName())) {
                                        onCityClickListener.onCityClick(city);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                });
                break;
            case VIEW_TYPE_HOT://热门
                convertView = getLayoutInflater().inflate(R.layout.item_city_hot, null);
                WrapHeightGridView hotCityGrid = convertView.findViewById(R.id.whgv_hot_city);
                HotCityAdapter hotCityAdapter = new HotCityAdapter(getContext(), hotCities);
                hotCityGrid.setAdapter(hotCityAdapter);
                hotCityGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (onCityClickListener != null) {
                            onCityClickListener.onCityClick((CityResource.City) parent.getItemAtPosition(position));
                        }
                    }
                });
                break;
            case VIEW_TYPE_ALL:// 所有
                if (convertView == null) {
                    ItemCityAllBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                            R.layout.item_city_all, null, false);
                    holder = new ViewHolder(binding);
                    convertView = binding.getRoot();
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                final CityResource.City city = getItem(position);
                holder.binding.setCity(city);
                String currentLetter = PinyinUtils.getFirstLetter(city.getPinyin());
                String previousLetter = position >= 1 ? PinyinUtils.getFirstLetter(getData().get(position - 1).getPinyin()) : "";
                if (!TextUtils.equals(currentLetter, previousLetter)) {
                    holder.binding.tvItemCityAllLetter.setVisibility(View.VISIBLE);
                    holder.binding.tvItemCityAllLetter.setText(currentLetter);
                } else {
                    holder.binding.tvItemCityAllLetter.setVisibility(View.GONE);
                }
                holder.binding.tvItemCityAllName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onCityClickListener != null) {
                            onCityClickListener.onCityClick(city);
                        }
                    }
                });
                break;
            default:
                break;
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position < VIEW_TYPE_COUNT - 1 ? position : VIEW_TYPE_COUNT - 1;
    }


    class ViewHolder {
        ItemCityAllBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            this.binding = (ItemCityAllBinding) binding;
        }
    }

    public void setOnCityClickListener(OnCityClickListener onCityClickListener) {
        this.onCityClickListener = onCityClickListener;
    }

    public interface OnCityClickListener {
        void onCityClick(CityResource.City city);
    }

    // 定位
    private void getLocation() {
        LocationUtils.getLocation(getContext(), new LocationUtils.Callback() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                currentCity = bdLocation.getCity();
                locateState = bdLocation.getLocType();
                Log.e("hpzl",""+locateState);
                // GPS定位61，离线定位成功66，网络定位成功161
                if (locateState == 61) {
                    if (bdLocation.getLatitude() < 0) {// 定位失败
                        locateState = LocationUtils.FAIL;
                    } else {// 定位成功
                        locateState = LocationUtils.SUCCESS;
                    }
                } else if (locateState == 66 || locateState == 161) {
                    locateState = LocationUtils.SUCCESS;
                } else {
                    locateState = LocationUtils.FAIL;
                }
                notifyDataSetChanged();
            }
        });
    }

}
