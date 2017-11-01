package com.fanc.wheretoplay.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LogUtils;
import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;




public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static IWXAPI api;
    private Gson gson;
    private String toJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.WXAPPID, false);
        api.handleIntent(getIntent(), this);
        gson = new Gson();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {
        LogUtils.i("BaseReq:" + baseReq.getType());
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                LogUtils.i("ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX");
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                LogUtils.i("ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX");
                break;
            default:
                break;
        }
        finish();
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc9dab29ef67892e1&secret=bc940d01449c680aefa08be62cd9f6a6&code=011926b6c83b71e751f6e8ad24f165eF&grant_type=authorization_code
    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() != 1) {

            switch (baseResp.errCode) {

                case BaseResp.ErrCode.ERR_OK:

                    sendBroadcast(new Intent("WXShare"));
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:

                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:

                    break;
                default:

                    break;
            }
            //
            WXEntryActivity.this.finish();

        } else {
            String code = ((SendAuth.Resp) baseResp).code;
        }}
}
