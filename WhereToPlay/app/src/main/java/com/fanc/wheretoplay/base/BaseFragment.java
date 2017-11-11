package com.fanc.wheretoplay.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.datamodel.User;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.DProgressDialog;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;


/**
 * @author tylz
 * @time 2016/3/18 0018 15:02
 * @des 所有Fragment的基类，保存一些公共方法和属性
 * @updateAuthor tylz
 * @updateDate 2016/3/18 0018
 * @updateDes
 */

public abstract class BaseFragment
        extends Fragment {
    public SPUtils mSpUtils;
    public Activity mContext;
    public LayoutInflater mLayoutInflater;
    private DProgressDialog mProgressDialog;
    public User mUser;
    // 上下拉
    public boolean isPullDown, isPullUp;
    public PullToRefreshLayout mPtrl;

    boolean isShowing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mSpUtils = new SPUtils(getActivity());
        mUser = mSpUtils.getUser();
        mLayoutInflater = LayoutInflater.from(mContext);
        //点击商家后台传来的消息可以进入聊天界面
    }

    @Override
    public void onDestroyView() {
//        closeProgress();
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }




    // 获取状态栏高度
    public int getStatuBarHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return mSpUtils.getBoolean(com.fanc.wheretoplay.util.Constants.IS_SIGN_IN, false);
    }

    /**
     * 对返回的数据信息进行解析，判断网络是否成功
     *
     * @param model 数据
     * @return true 代表成功 ，false 代表失败
     */
    public boolean isSuccess(BaseModel model) {
        closeProgress();
        if (model == null) {
            return false;
        }
        if (model.code != 0 || !TextUtils.isEmpty(model.message)) {
//            if (model.code == 40001 || model.equals("接口授权失败")) {
//                ToastUtils.makePicTextShortToast(mContext, Constants.ICON_TIP, UIUtils.getString(R.string.login_Lose));
//                mSpUtils.removeUser();
//                mSpUtils.putBoolean(Constants.IS_LOGIN, false);
//                Intent intent = new Intent();
//                intent.setClass(mContext, LoginActivity.class);
//                startActivity(intent);
//                onDestroy();
//            } else {
            ToastUtils.showShortToast(mContext, model.message);
            if (model.code == 50003) {
                Intent intent = new Intent(Constants.ACTION_RESIGN_IN);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
//            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 网络连接失败
     */
    public void connectError() {
        closeProgress();
        ToastUtils.showShortToast(mContext, mContext.getResources().getString(R.string.connect_net_error));
    }

    /**
     * 开启进度条
     */
    public void showProgress() {
        mProgressDialog = new DProgressDialog(mContext);
        mProgressDialog.show();
    }

    /**
     * 开启进度条
     */
    public void showTextProgress(String text) {
        mProgressDialog = new DProgressDialog(mContext);
        mProgressDialog.setText(text);
        mProgressDialog.show();
    }

    /**
     * 关闭进度条
     */
    public void closeProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 刷新加载失败
     */
    public void refreshOrLoadFail() {
        if (mPtrl != null) {
            if (isPullDown) {
                isPullDown = false;
                mPtrl.refreshFinish(PullToRefreshLayout.FAIL);
            }
            if (isPullUp) {
                isPullUp = false;
                mPtrl.loadmoreFinish(PullToRefreshLayout.FAIL);
            }
        }
    }

    /**
     * 刷新/加载成功
     */
    public void refreshAndLoadMoreSuccess() {
        if (mPtrl != null) {
            if (isPullDown) {
                mPtrl.refreshFinish(PullToRefreshLayout.SUCCEED);
                isPullDown = false;
            }
            if (isPullUp) {
                mPtrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                isPullUp = false;
            }
        }
    }

}
