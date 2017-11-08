package com.fanc.wheretoplay.wxapi;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
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

        if (resp.errCode==0) {
            Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Constants.ACTION_WXPAY_SUCCESS);
           LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }else {
            Toast.makeText(mContext, "支付失败"+resp.errCode, Toast.LENGTH_SHORT).show();
        }


//        String msg = "";
//        Intent intent = new Intent(this, MainActivity.class);
//        if (resp.errCode == 0) {
//            Intent intent = new Intent(Constants.ACTION_WXPAY_SUCCESS);
//            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
//            msg = "支付成功";
//            intent.putExtra("pay_result", 1);
//            intent.putExtra("pay_method", "微信支付");
//            startActivity(intent);
//
//        } else if (resp.errCode == -1) {
//            msg = "已取消支付";
//            intent.putExtra("pay_result", -1);
//            intent.putExtra("pay_method", "微信支付");
//            startActivity(intent);
//        } else if (resp.errCode == -2) {
//            msg = "支付失败";
//            intent.putExtra("pay_result", -1);
//            intent.putExtra("pay_method", "微信支付");
//            startActivity(intent);
//        }
//
//
//
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("提示");
//            builder.setMessage("支付结果" + String.valueOf(resp.errCode));
//            builder.show();
//        }
//
        finish();






    }
}