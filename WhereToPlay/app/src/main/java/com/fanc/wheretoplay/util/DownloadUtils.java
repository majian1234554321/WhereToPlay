package com.fanc.wheretoplay.util;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;
import static com.fanc.wheretoplay.base.App.mContext;

/**
 * Created by Administrator on 2017/4/25.
 */

public class DownloadUtils {

    public static long APK_DOWNLOAD_ID = -1;

    // 下载apk
    public static long downloadApk(String version, String url, String apkName) {
        String path = "";
        if (FileUtils.isSDCardAvailable()) {
            if (!Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).exists()) {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();// 创建保存目录
            }
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        } else {
            path = FileUtils.getDownloadDir();
        }
        Log.w("aaa", "apk保存路径" + path);
        // 删除以前下载的安装包
        File file = new File(path, apkName);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir(path, apkName);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);// WiFi下下载
        request.setAllowedOverRoaming(true);// 允许漫游是下载
        request.setAllowedOverMetered(true);// 允许计量时网络下载
        request.allowScanningByMediaScanner();// 允许媒体扫描到这个文件
        // 设置通知标题、描述、显示状态
        request.setTitle(apkName);
        request.setDescription("V" + version + "版");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setMimeType(Constants.MIME_TYPE);
        request.setVisibleInDownloadsUi(true);
        ToastUtils.showShortToast(mContext, "后台下载开始");
        return downloadManager.enqueue(request);// 执行下载并获取下载id
    }
}
