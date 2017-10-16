package com.fanc.wheretoplay.datamodel;

import com.fanc.wheretoplay.base.BaseModel;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Version extends BaseModel {

    /**
     * version: string,服务器上的APP版本号
     * result: string,
     * 枚举值unknown、higher、equal、lower。当前APP的版本与服务器上的APP版本号比对结果，如果没有传入当前APP版本号则返回unknown
     * apk_download_url: Android客户端下载应用的地址
     * ipa_download_url: ios客户端下载应用的地址
     * update_title:日志更新的标题
     * update_log: 日志更新内容
     */

    private String version;
    private String result;
    private String apk_download_url;
    private String ipa_download_url;
    private String update_title;
    private String update_log;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApk_download_url() {
        return apk_download_url;
    }

    public void setApk_download_url(String apk_download_url) {
        this.apk_download_url = apk_download_url;
    }

    public String getIpa_download_url() {
        return ipa_download_url;
    }

    public void setIpa_download_url(String ipa_download_url) {
        this.ipa_download_url = ipa_download_url;
    }

    public String getUpdate_title() {
        return update_title;
    }

    public void setUpdate_title(String update_title) {
        this.update_title = update_title;
    }

    public String getUpdate_log() {
        return update_log;
    }

    public void setUpdate_log(String update_log) {
        this.update_log = update_log;
    }
}
