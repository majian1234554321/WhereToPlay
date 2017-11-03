package com.fanc.wheretoplay.activity;

/**
 * Created by admin on 2017/11/1.
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fanc.wheretoplay.R;

import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.Constants_sina;
import com.fanc.wheretoplay.util.ImageUtils;
import com.fanc.wheretoplay.util.LogUtils;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;


import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;




public class ShareActivity extends BaseActivity implements  IWXAPIEventHandler, WbShareCallback {
    @BindView(R.id.btn_share_wechat)
    TextView btnShareWechat;
    @BindView(R.id.btn_share_pyq)
    TextView btnSharePyq;
    @BindView(R.id.btn_share_weibo)
    TextView btnShareWeibo;
    @BindView(R.id.btn_share_qq)
    TextView btnShareQq;
    @BindView(R.id.btn_share_qzone)
    TextView btnShareQzone;
    @BindView(R.id.pop_layout2)
    LinearLayout popLayout2;
    @BindView(R.id.pop_layout)
    LinearLayout popLayout;
    private Tencent mTencent;

    private IWXAPI api;
    private Bitmap bmp;
    private String url = "www.baidu.com";
    private int type =2;

    private String desc = "乐互详情";
    private String contentTitle= "乐互" ;
    private String shareUrl = "www.baidu.com";
    private String imgUrl = "www.baidushare.com";
    private Bitmap thumb;
    private WbShareHandler shareHandler;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_share);
        ButterKnife.bind(this);
        // 注册应用到微信
        String APP_ID = "wx424026eca78d03e6";
        // 注册应用到QQ
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        mTencent = Tencent.createInstance(Constants.QQAPPID, this);
        // 注册应用到新浪微博

        WbSdk.install(this,new AuthInfo(this, Constants_sina.APP_KEY, Constants_sina.REDIRECT_URL, Constants_sina.SCOPE));
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();


        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int height = popLayout.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        ShareActivity.this.setResult(100, getIntent());
                        ShareActivity.this.finish();
                    }
                }
                int height2 = popLayout2.getBottom();
                int y2 = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y2 > height2) {
                        ShareActivity.this.setResult(100, getIntent());
                        ShareActivity.this.finish();
                    }
                }
                return true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareActivity.this.setResult(100, getIntent());

    }



    @OnClick({R.id.btn_share_wechat, R.id.btn_share_pyq, R.id.btn_share_weibo, R.id.btn_share_qq, R.id.btn_share_qzone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_share_wechat:
                if (!isApkAvilible(this, "com.tencent.mm")) {
                    Toast.makeText(this, "没有安装微信", Toast.LENGTH_SHORT).show();

                    return;
                }
                // 存在微信客户端进行好友分享
                shareWeChat(false);

                break;
            case R.id.btn_share_pyq:
                if (!isApkAvilible(this, "com.tencent.mm")) {
                    Toast.makeText(this, "没有安装微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 存在微信客户端进行朋友圈分享
                shareWeChat(true);

                break;
            case R.id.btn_share_weibo:
                if (!isApkAvilible(this, "com.sina.weibo")) {
                    // 目前第三方分享微博需要客户端的支持
                    Toast.makeText(this, "没有安装新浪微博客户端", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 存在新浪微博客户端进行分享
                shareWeiBo();
                break;
            case R.id.btn_share_qq:
                if (!isApkAvilible(this, "com.tencent.mobileqq")) {
                    Toast.makeText(this, "没有安装QQ客户端", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 存在QQ客户端进行QQ好友分享
                shareQQ();
                break;
            case R.id.btn_share_qzone:
                if (!isApkAvilible(this, "com.tencent.mobileqq")) {
                    Toast.makeText(this, "没有安装QQ客户端", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 存在QQ客户端进行QQ空间分享
                shareQzone();
                break;
        }
    }


    /**
     * 分享图片到微信
     *
     * @param isPYQ true 表示朋友圈；false 表示好友
     */
    private void shareWeChat(final boolean isPYQ) {
        switch (type) {
            case 1:

                // 初始化WXImageObject 和 WXMediaMessage对象
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                // 设置缩略图
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 200, true);
//                bmp.recycle();
                msg.thumbData = ImageUtils.bmpToByteArray(thumbBmp, true);

                // 构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                // transaction 字段用于唯一标识一个请求
                req.transaction = buildTransaction("img");
                req.message = msg;
                req.scene = isPYQ ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                //调用接口发送数据到微信
                api.sendReq(req);
                break;
            case 2:
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = shareUrl;
                WXMediaMessage msg1 = new WXMediaMessage(webpage);
                msg1.title = contentTitle;
                msg1.description = desc;
                 Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                if (thumb != null) {
                    Bitmap thumb2 = Bitmap.createScaledBitmap(thumb, 120, 120, true);//压缩Bitmap
                    msg1.thumbData = ImageUtils.bmpToByteArray(thumb2, true);

                } else {
                    Bitmap thumb2 = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_wxcircle);
                    msg1.thumbData = ImageUtils.bmpToByteArray(thumb2, true);
                }
                SendMessageToWX.Req req1 = new SendMessageToWX.Req();
                req1.transaction = buildTransaction("webpage");
                req1.message = msg1;
                req1.scene = isPYQ ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req1);
                finish();
                break;
        }

    }

    /**
     * 创建文本消息对象。
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        textObject.title = "xxxx";
        textObject.actionUrl = "http://www.baidu.com";
        return textObject;
    }

    /**
     * 获取分享的文本模板。
     */
    private String getSharedText() {

        String text = "@大屁老师，这是一个很漂亮的小狗，朕甚是喜欢-_-!! #大屁老师#http://weibo.com/p/1005052052202067/home?from=page_100505&mod=TAB&is_all=1#place";

        return text;
    }



    /**
     * 分享纯图片到微博
     */
    private void shareWeiBo() {
        switch (type) {
            case 1:

                break;
            case 2:
                WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
                weiboMessage.textObject = getTextObj();
                shareHandler.shareMessage(weiboMessage, false);





                break;
        }
    }

    /**
     * 分享纯图片到QQ空间
     */
    private void shareQzone() {
        switch (type) {
            case 1:
                Bundle params = new Bundle();
                params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "乐互");
                params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "乐互");
                params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.weibo.com/champdas");
                ArrayList<String> imgUrls = new ArrayList<>();
                imgUrls.add(url);
                params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrls);
                mTencent.shareToQzone(this, params, new BaseUiListener());
                break;
            case 2:
                final Bundle paramsQzone = new Bundle();
                paramsQzone.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                paramsQzone.putString(QzoneShare.SHARE_TO_QQ_TITLE, contentTitle);
                paramsQzone.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, desc);
                paramsQzone.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, shareUrl);
                ArrayList imageUrls = new ArrayList();
                imageUrls.add(imgUrl);
                paramsQzone.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
                paramsQzone.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
                mTencent.shareToQzone(this, paramsQzone, new BaseUiListener());
                break;
        }

    }

    /**
     * 分享纯图片到QQ好友
     */
    private void shareQQ() {
        switch (type) {
            case 1:
                Bundle params = new Bundle();
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, url);
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "乐互");
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
                params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);// 分享是隐藏分享到QQ空间的按钮
                mTencent.shareToQQ(this, params, new BaseUiListener());
                break;
            case 2:
                final Bundle params2 = new Bundle();
                params2.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                params2.putString(QQShare.SHARE_TO_QQ_TITLE, contentTitle);
                params2.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);
                params2.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareUrl);
                params2.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
                params2.putString(QQShare.SHARE_TO_QQ_APP_NAME, "乐互");
                params2.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 123);
                mTencent.shareToQQ(this, params2, new BaseUiListener());
                break;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        // 当前应用唤起微博并进行分享后，需要在此调用该函数
        // 来接收微博客户端返回的数据；执行成功，返回true,并调用
        shareHandler.doResultIntent(intent,this);
        ShareActivity.this.setResult(100, getIntent());
    }




    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {

            case BaseResp.ErrCode.ERR_OK:
                // "亲，分享成功了";
                ShareActivity.this.setResult(100, getIntent());
                this.finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                // "取消分享";
                ShareActivity.this.setResult(100, getIntent());
                this.finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                // "认证失败";
                ShareActivity.this.setResult(100, getIntent());
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, "分享成功", Toast.LENGTH_LONG).show();
        this.finish();
    }

    @Override
    public void onWbShareFail() {
        Toast.makeText(this,
                "分享失败",
                Toast.LENGTH_LONG).show();
        this.finish();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, "取消分享", Toast.LENGTH_LONG).show();
        this.finish();
    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
//            ToastUtils.showToastOnUiThread(ShareActivity.this,"分享成功！");
            ShareActivity.this.setResult(100, getIntent());
            ShareActivity.this.finish();
        }

        protected void doComplete(JSONObject values) {
        }

        @Override
        public void onError(UiError e) {
            ShareActivity.this.setResult(100, getIntent());
//            ToastUtils.showToastOnUiThread(ShareActivity.this,"分享失败！");
        }

        @Override
        public void onCancel() {
            ShareActivity.this.setResult(100, getIntent());
            LogUtils.i("cancel");
//            ToastUtils.showToastOnUiThread(ShareActivity.this,"取消分享！");
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mTencent)
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
    }

/*    @Override
    public void onResponse(BaseResponse baseResponse) {
        // 接收微博客户端的请求数据
        switch (baseResponse.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
//                ToastUtils.showToast("分享成功");
                ShareActivity.this.setResult(100, getIntent());
                ShareActivity.this.finish();
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
//                ToastUtils.showToast("取消分享");
                ShareActivity.this.setResult(100, getIntent());
                ShareActivity.this.finish();
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
//                ToastUtils.showToast("分享失败");
                ShareActivity.this.setResult(100, getIntent());
                ShareActivity.this.finish();
                break;
        }

    }*/

    private String buildTransaction(final String type) {

        return (type == null) ? String.valueOf(System.currentTimeMillis())

                : type + System.currentTimeMillis();

    }

    /**
     * 判断包名所对应的客户端是否存在
     *
     * @param context
     * @param apkName
     * @return
     */
    public static boolean isApkAvilible(Context context, String apkName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(apkName)) {
                    return true;
                }
            }
        }

        return false;
    }


}
