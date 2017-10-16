package com.fanc.wheretoplay.callback;

import android.util.Log;

import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2017/7/19.
 */

public class UMShareListener implements com.umeng.socialize.UMShareListener {


    /**
     * @param platform 平台类型
     * @descrption 分享开始的回调
     */
    @Override
    public void onStart(SHARE_MEDIA platform) {

    }

    /**
     * @param platform 平台类型
     * @descrption 分享成功的回调
     */
    @Override
    public void onResult(SHARE_MEDIA platform) {
        String name = platform.name();
        switch (name) {
            case "QQ":
                name = "QQ";
                break;
            case "QZONE":
                name = "QQ空间";
                break;
            case "WEIXIN":
                name = "微信";
                break;
            case "WEIXIN_CIRCLE":
                name = "微信朋友圈";
                break;
            default:
                name = "";
                break;
        }
        ToastUtils.makePicTextShortToast(UIUtils.getContext(), name + "分享成功了");
    }

    /**
     * @param platform 平台类型
     * @param t        错误原因
     * @descrption 分享失败的回调
     */
    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
        String name = platform.name();
        switch (name) {
            case "QQ":
                name = "QQ";
                break;
            case "QZONE":
                name = "QQ空间";
                break;
            case "WEIXIN":
                name = "微信";
                break;
            case "WEIXIN_CIRCLE":
                name = "微信朋友圈";
                break;
            default:
                name = "";
                break;
        }
        ToastUtils.makePicTextShortToast(UIUtils.getContext(), name + "分享失败");
        Log.e("aaa", "onError: " + t.getMessage());
    }

    /**
     * @param platform 平台类型
     * @descrption 分享取消的回调
     */
    @Override
    public void onCancel(SHARE_MEDIA platform) {
        String name = platform.name();
        switch (name) {
            case "QQ":
                name = "QQ";
                break;
            case "QZONE":
                name = "QQ空间";
                break;
            case "WEIXIN":
                name = "微信";
                break;
            case "WEIXIN_CIRCLE":
                name = "微信朋友圈";
                break;
            default:
                name = "";
                break;
        }
        ToastUtils.makePicTextShortToast(UIUtils.getContext(), name + "分享取消了");
    }
}
