package com.fanc.wheretoplay.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


/**
 * @author tylz
 * @time 2016/5/18 0018 15:10
 * @des 获取经纬度
 * @updateAuthor
 * @updateDate 2016/5/18 0018
 * @updateDes 获取经纬度
 */
public class LocationUtils {
    public static final int LOCATING = 0;//定位中
    public static final int SUCCESS = 1;//定位成功
    public static final int FAIL = 2;//定位失败
    public static BDLocation location = null;

    public static LocationClient mLocationClient = null;
    private static MyLocationListener myListener;

    public static void getLocation(Context mContext, Callback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, permissions, Constants.REQUEST_PERMISSION_CODE);
                }
            }
        }
        mLocationClient = new LocationClient(mContext);     //声明LocationClient类
        myListener = new MyLocationListener(callback);
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        int span = 5*60*1000;
        int span = 0;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(false);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }

    public static void getLocation(final Context mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, permissions, Constants.REQUEST_PERMISSION_CODE);
                }
            }
        }
        mLocationClient = new LocationClient(mContext);     //声明LocationClient类
        myListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        int span = 5*60*1000;
        int span = 0;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(false);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }


    public static class MyLocationListener implements BDLocationListener {
        Callback callback;

        public MyLocationListener() {
        }

        public MyLocationListener(Callback callback) {
            this.callback = callback;
        }

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            // GPS 定位 61，离线定位 66，网络定位成功 161
            if (callback != null) {
                callback.onReceiveLocation(bdLocation);
                Log.e("hpzl", "bdLocation:" + bdLocation.toString());
            }
            location = bdLocation;
            Log.e("hpzl", "onReceiveLocation: City = " + bdLocation.getCity() + "\t" + bdLocation.getLocType());
            Log.e("hpzl", "onReceiveLocation: Address = " + bdLocation.getAddrStr());
            Log.e("hpzl", "onReceiveLocation: \n lat = " + bdLocation.getLatitude() + "\n lnt = " + bdLocation.getLongitude());
        }


    }

    // 定位成功后的回调接口
    public interface Callback {
        void onReceiveLocation(BDLocation bdLocation);
    }

    /**
     * 停止定位
     */
    public static void stopLocation() {
        if (mLocationClient != null) {
            if (mLocationClient.isStarted()) {
                mLocationClient.unRegisterLocationListener(myListener);
                mLocationClient.stop();
                myListener.callback = null;
                myListener = null;
            }
            mLocationClient = null;
        }
    }

    public static void pauseLoaction() {
        mLocationClient.stop();
    }

    public static void startLoaction() {
        mLocationClient.start();
    }

}
