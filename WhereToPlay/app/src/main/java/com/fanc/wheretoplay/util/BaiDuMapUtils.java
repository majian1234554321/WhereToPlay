package com.fanc.wheretoplay.util;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.sdu.didi.openapi.DiDiWebActivity;

import java.util.HashMap;

import static com.fanc.wheretoplay.util.LocationUtils.mLocationClient;

/**
 * Created by admin on 2017/11/23.
 */

public class BaiDuMapUtils {

    private BaiDuInfoModel baiDuInfoModel;
    private LocationClient mLocationClient;
    private MyBDLocationListener mBDLocationListener;

    //获取当前的信息 (地址，经度，纬度)
    public BaiDuInfoModel getCurrentAllinfo(Context mContext) {
        mLocationClient = new LocationClient(mContext);
        mBDLocationListener = new MyBDLocationListener();
        // 注册监听
        mLocationClient.registerLocationListener(mBDLocationListener);


        // 声明定位参数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式 高精度
        option.setCoorType("bd09ll");// 设置返回定位结果是百度经纬度 默认gcj02
       // option.setScanSpan(5000);// 设置发起定位请求的时间间隔 单位ms
        option.setIsNeedAddress(true);// 设置定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 设置定位结果包含手机机头 的方向
        // 设置定位参数
        mLocationClient.setLocOption(option);
        // 启动定位
        mLocationClient.start();

        return baiDuInfoModel;
    }

    class MyBDLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            // 非空判断
            if (location != null) {
                // 根据BDLocation 对象获得经纬度以及详细地址信息
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String address = location.getAddrStr();
                Log.i("HHHHHH", "address:" + address + " latitude:" + latitude
                        + " longitude:" + longitude + "---" + Thread.currentThread().getName());
                baiDuInfoModel = new BaiDuInfoModel(latitude + "", longitude + "", address);

                if (mLocationClient.isStarted()) {
                    // 获得位置之后停止定位
                    mLocationClient.stop();
                }

            }
        }
    }


    //注销
    public void destoryBaiDuLocation() {
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(mBDLocationListener);
        }
    }

    //根据经纬度获取地址

    public void getLonLat2Address() {
        GeoCoder mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.geocode(new GeoCodeOption()
                .city("北京")
                .address("海淀区上地十街10号"));

    }

    //根据地址获取经纬度
    public void getAddress2LonLat(double lat, double lon) {
        GeoCoder mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(new LatLng(31.23006, 121.360874)));
    }



    private OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Log.i("HHHHHH", "没结果");
            }
            assert result != null;
            Log.i("HHHHHH", ""+result.getLocation().latitude);
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Log.i("HHHHHH", "没结果");
            }
            //获取反向地理编码结果
            assert result != null;
            Log.i("HHHHHH", ""+result.getLocation().latitude);
        }
    };


    public  class BaiDuInfoModel {
        public String latitude;
        public String longitude;
        public String address;

        public BaiDuInfoModel(String latitude, String longitude, String address) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
        }

        @Override
        public String toString() {
            return "BaiDuInfoModel{" +
                    "latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }


}
