package com.fanc.wheretoplay.service;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DownloadUtils;
import com.fanc.wheretoplay.util.ToastUtils;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by Apple on 2018/2/27.
 */

public  class InstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constants.APK_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            ToastUtils.showShortToast(context, "下载完成，乐互网.apk保存在 Download 目录下");
            if (DownloadUtils.APK_DOWNLOAD_ID == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(DownloadUtils.APK_DOWNLOAD_ID);
                Cursor cursor = ((DownloadManager) context.getSystemService(DOWNLOAD_SERVICE)).query(query);
                if (cursor.moveToFirst() && cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    //  String filePath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
//


                    int fileUriIdx = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                    String fileUri = cursor.getString(fileUriIdx);
                    String filePath = null;


                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        if (fileUri != null) {
                            filePath = Uri.parse(fileUri).getPath();
                        }
                    } else {
                        //Android 7.0以上的方式：请求获取写入权限，这一步报错
                        //过时的方式：DownloadManager.COLUMN_LOCAL_FILENAME
                        int fileNameIdx = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                        filePath = cursor.getString(fileNameIdx);
                    }




                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    installIntent.setDataAndType(Uri.parse("file://" + filePath), Constants.MIME_TYPE);
                    installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(installIntent);
                }
                cursor.close();
            }
        }
    }
}