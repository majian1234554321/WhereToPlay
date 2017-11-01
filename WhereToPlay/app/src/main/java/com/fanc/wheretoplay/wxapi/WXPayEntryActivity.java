package com.fanc.wheretoplay.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("aaa", "onPayFinish, errCode = " + resp.errCode);
        if (resp.errCode == 0) {
            Intent intent = new Intent(Constants.ACTION_WXPAY_SUCCESS);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
//        } else {
//            ToastUtils.makePicTextShortToast(mContext, resp.errStr);
        }
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//        }
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();
            }
        }.sendEmptyMessageDelayed(0, 500);
    }
}