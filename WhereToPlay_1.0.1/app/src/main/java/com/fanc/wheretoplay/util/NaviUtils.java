package com.fanc.wheretoplay.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.fanc.wheretoplay.adapter.MapDialogAdapter;
import com.fanc.wheretoplay.view.MapDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class NaviUtils {
    public static final String PACKAGE_NAME_BAIDU_MAP = "com.baidu.BaiduMap";
    public static final String PACKAGE_NAME_GAODE_MAP = "com.autonavi.minimap";
    public static final String PACKAGE_NAME_TENCENT_MAP = "com.tencent.map";
    public static final String PACKAGE_NAME_GOOGLE_MAP = "com.google.android.apps.maps";


    private static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static List<String> checkApkExist(Context context) {
        List<String> existApks = new ArrayList<>();
        if (checkApkExist(context, PACKAGE_NAME_BAIDU_MAP)) {
            existApks.add(PACKAGE_NAME_BAIDU_MAP);
        }
        if (checkApkExist(context, PACKAGE_NAME_GAODE_MAP)) {
            existApks.add(PACKAGE_NAME_GAODE_MAP);
        }
        if (checkApkExist(context, PACKAGE_NAME_TENCENT_MAP)) {
            existApks.add(PACKAGE_NAME_TENCENT_MAP);
        }
//        if (checkApkExist(context, PACKAGE_NAME_GOOGLE_MAP)) {
//            existApks.add(PACKAGE_NAME_GOOGLE_MAP);
//        }
        return existApks;
    }

    /**
     * 检测地图是否安装，如果安装就导航
     *
     * @param context
     * @param startName
     * @param startLat
     * @param startLng
     * @param endName
     * @param endLat
     * @param endLng
     */
    public static void checkMapExist(final Context context, final String startName, final String startLat, final String startLng, final String endName, final String endLat, final String endLng) {
        final List<String> maps = checkApkExist(context);
        if (maps.size() == 0) {
            ToastUtils.showShortToast(context, "请先安装 百度地图/高德地图/腾讯地图 客户端");
            return;
        } else if (maps.size() == 1) {
            singleMapsNavi(maps.get(0), context, startName, startLat, startLng, endName, endLat, endLng);
        } else {
            MapDialogAdapter adapter = new MapDialogAdapter(context, maps);
            final MapDialog mapDialog = new MapDialog(context);
            mapDialog.setAdapter(adapter, new MapDialogAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String mapName) {
                    if (TextUtils.equals(PACKAGE_NAME_BAIDU_MAP, mapName)) {
                        singleMapsNavi(mapName, context, startName, startLat, startLng, endName, endLat, endLng);
                    } else {
                        double[] start = GPSUtil.bd09_To_Gcj02(Double.parseDouble(startLat), Double.parseDouble(startLng));
                        double[] end = GPSUtil.bd09_To_Gcj02(Double.parseDouble(endLat), Double.parseDouble(endLng));
                        singleMapsNavi(mapName, context, startName,String.valueOf(start[0]), String.valueOf(start[1]),
                                endName, String.valueOf(end[0]), String.valueOf(end[1]));
                    }
                    mapDialog.dismiss();
                }
            })
                    .setCanceledOnTouchOutside(true)
                    .show();
        }
    }

    /**
     * 地图导航
     *
     * @param map
     * @param context
     * @param startName
     * @param startLat
     * @param startLng
     * @param endName
     * @param endLat
     * @param endLng
     */
    private static void singleMapsNavi(String map, Context context, String startName, String startLat, String startLng, String endName, String endLat, String endLng) {
        if (map == PACKAGE_NAME_BAIDU_MAP) {
            baiduNavi(context, startName, startLat, startLng, endName, endLat, endLng);
        } else if (map == PACKAGE_NAME_GAODE_MAP) {
            gaodeNavi(context, startName, startLat, startLng, endName, endLat, endLng);
        } else if (map == PACKAGE_NAME_TENCENT_MAP) {
            tencentNavi(context, startName, startLat, startLng, endName, endLat, endLng);
        }
    }

    /**
     * 百度地图导航
     *
     * @param context
     * @param startName
     * @param startLat
     * @param startLng
     * @param endName
     * @param endLat
     * @param endLng
     */
    public static void baiduNavi(Context context, String startName, String startLat, String startLng, String endName, String endLat, String endLng) {
        StringBuilder uri = new StringBuilder("baidumap://map/direction?");
        /**
         *  起点和终点必须有一个
         *  名字和经纬度必须有一个
         */
        // 只有起点
        if ((!TextUtils.isEmpty(startName) || !TextUtils.isEmpty(startLat))
                && (TextUtils.isEmpty(endName) && TextUtils.isEmpty(endLat))) {
            if (TextUtils.isEmpty(startName)) {// 无名字，必须有经纬度
                if (!TextUtils.isEmpty(startLat)) {
                    uri.append("origin=latlng:" + startLat + "," + startLng);
                }
            } else {// 有名字，经纬度可有无
                uri.append("origin=name:" + startName);
                if (!TextUtils.isEmpty(startLat)) {
                    uri.append("|latlng:" + startLat + "," + startLng);
                }
            }
            Log.e("llm", "百度导航，只有起点");
        } else if ((!TextUtils.isEmpty(endName) || !TextUtils.isEmpty(endLat))// 只有终点
                && (TextUtils.isEmpty(startName) && TextUtils.isEmpty(startLat))) {
            if (TextUtils.isEmpty(endName)) {// 无名字，必须有经纬度
                if (!TextUtils.isEmpty(endLat)) {
                    uri.append("destination=latlng:" + endLat + "," + endLng);
                }
            } else {
                uri.append("destination=name:" + endName);
                if (!TextUtils.isEmpty(endLat)) {
                    uri.append("|latlng:" + endLat + "," + endLng);
                }
            }
            Log.w("llm", "百度导航，只有终点");
        } else if ((!TextUtils.isEmpty(startName) || !TextUtils.isEmpty(startLat))
                && (!TextUtils.isEmpty(endName) || !TextUtils.isEmpty(endLat))) {// 起点终点都有
            if (TextUtils.isEmpty(startName)) {// 无名字，必须有经纬度
                if (!TextUtils.isEmpty(startLat)) {
                    uri.append("origin=latlng:" + startLat + "," + startLng);
                }
            } else {// 有名字，经纬度可有无
                uri.append("origin=name:" + startName);
                if (!TextUtils.isEmpty(startLat)) {
                    uri.append("|latlng:" + startLat + "," + startLng);
                }
            }
            if (TextUtils.isEmpty(endName)) {// 无名字，必须有经纬度
                if (!TextUtils.isEmpty(endLat)) {
                    uri.append("&destination=latlng:" + endLat + "," + endLng);
                }
            } else {
                uri.append("&destination=name:" + endName);
                if (!TextUtils.isEmpty(endLat)) {
                    uri.append("|latlng:" + endLat + "," + endLng);
                }
            }
            Log.d("llm", "百度导航，起点终点都有");
        } else {// 起点终点都没
            ToastUtils.showShortToast(context, "请选择导航的起点或者终点");
            return;
        }

        // 路径规划模式
        uri.append("&mode=driving");
        Log.i("llm", "baiduNavi: uri  = " + uri.toString());
        // 跳转到地图
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(uri.toString()));
//        intent.setData(Uri.parse("baidumap://map/direction?region=beijing&origin=39.98871,116.43234&destination=name:西直门&mode=driving"));
        context.startActivity(intent);
    }

    /**
     * 腾讯地图导航
     *
     * @param context
     * @param fromName
     * @param fromLat
     * @param fromLng
     * @param toName
     * @param toLat
     * @param toLng
     */
    public static void tencentNavi(Context context, String fromName, String fromLat, String fromLng, String toName, String toLat, String toLng) {
        /**
         *  导航起点或者终点都有方可进行路线规划
         *  起点的名称和坐标至少有一个即可
         *  终点的名字必须有，坐标可有可无
         */
        if ((TextUtils.isEmpty(fromName) && TextUtils.isEmpty(fromLat)) || TextUtils.isEmpty(toName)) {
            Log.e("llm", "tencentNavi: 没有导航起点或终点");
            return;
        }
        StringBuilder uri = new StringBuilder("qqmap://map/routeplan?type=drive");
        if (TextUtils.isEmpty(fromName)) {// 起点名字为空，坐标就不能为空（坐标不判断）
            uri.append("&fromcoord=" + fromLat + "," + fromLng);// 起点坐标
            uri.append("&to=" + toName);// 终点名字
            if (!TextUtils.isEmpty(toLat)) {// 终点坐标
                uri.append("&tocoord=" + toLat + "," + toLng);
            }
        } else {// 起点名字不为空，坐标可有可无
            uri.append("&from=" + fromName);//起点名称
            if (!TextUtils.isEmpty(fromLat)) {
                uri.append("&fromcoord=" + fromLat + "," + fromLng);// 起点坐标
            }
            uri.append("&to=" + toName);// 终点名字
            if (!TextUtils.isEmpty(toLat)) {// 终点坐标
                uri.append("&tocoord=" + toLat + "," + toLng);
            }
        }
        Log.d("llm", "tencentNavi: uri = " + uri.toString());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(uri.toString()));
//    intent.setData(Uri.parse("qqmap://map/routeplan?type=drive&from=天坛南门&fromcoord=39.873145,116.413306&to=国家大剧院&tocoord=39.907380,116.388501"));
        context.startActivity(intent);
    }

    /**
     * 高德地图导航
     *
     * @param context
     * @param sName
     * @param sLat
     * @param sLng
     * @param dName
     * @param dLat
     * @param dLng
     */
    public static void gaodeNavi(Context context, String sName, String sLat, String sLng, String dName, String dLat, String dLng) {
        /**
         * 路径规划必须填入终点坐标（名字可有可无），起点可填可不填（不填，默认为当前位置）
         */
        if (TextUtils.isEmpty(dLat)) {
            Log.e("llm", "gaodeNavi: 没有终点");
            return;
        }
        StringBuilder uri = new StringBuilder("amapuri://route/plan/?");
        if (!TextUtils.isEmpty(sName) || !TextUtils.isEmpty(sLat)) {
            // 起点
            if (!TextUtils.isEmpty(sName)) {// 起点名字
                uri.append("sname=" + sName);
                if (!TextUtils.isEmpty(sLat)) {// 起点有坐标
                    uri.append("&slat=" + sLat + "&slon=" + sLng);
                }
            } else {
                if (!TextUtils.isEmpty(sLat)) {// 起点有坐标
                    uri.append("slat=" + sLat + "&slon=" + sLng);
                }
            }
            //终点坐标
            uri.append("&dlat=" + dLat + "&dlon=" + dLng);
        } else {
            //终点坐标
            uri.append("dlat=" + dLat + "&dlon=" + dLng);
        }
        // 终点名字
//        if (!TextUtils.isEmpty(dName)) {
//            uri.append("&dname=" + dName);
//        }
//        if (!TextUtils.isEmpty(sLat)) {
//            uri.append("&slat=" + sLat + "&slon=" + sLng);
//        }
//        if (!TextUtils.isEmpty(sName)) {
//            uri.append("&sname=" + sName);
//        }
//        uri.append("&did=BGVIS2&dlat=" + dLat + "&dlon=" + dLng);
//        if (!TextUtils.isEmpty(dName)) {
//            uri.append("&dname=" + dName);
//        }
        uri.append("&dev=0&t=0");
        Log.e("llm", "gaodeNavi: uri    = " + uri.toString());
        Log.d("llm", "gaodeNavi: intent = amapuri://route/plan/?sid=BGVIS1&slat=39.92848272&slon=116.39560823&sname=A&did=BGVIS2&dlat=39.98848272&dlon=116.47560823&dname=B&dev=0&t=0");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(uri.toString()));
//        intent.setData(Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=39.92848272&slon=116.39560823&sname=A&did=BGVIS2&dlat=39.98848272&dlon=116.47560823&dname=B&dev=0&t=0"));
        context.startActivity(intent);
    }
}
