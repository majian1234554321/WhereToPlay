package com.fanc.wheretoplay.fragment;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentReferralsBinding;
import com.fanc.wheretoplay.datamodel.ShearedAD;
import com.fanc.wheretoplay.datamodel.Url;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.CircleImageView;
import com.fanc.wheretoplay.view.TopMenu;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/19.
 */

public class ReferralFragment extends BaseFragment {

    FragmentReferralsBinding referralsBinding;

    TopMenu mTmReferrals;
    ImageView mIvReferralsLogo;
    ImageView mIvReferralsAD;
    CircleImageView mCivMineHeader;
    TextView mTvMineNickname;
    EditText mTvInvitationCode;
    Button mBtnReferralsSheared;

    String shearedUrl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        referralsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_referrals, container, false);
        initViews();
        init();
        setListeners();
        return referralsBinding.getRoot();
    }

    private void initViews() {
        mTmReferrals = referralsBinding.tmReferral;
        mIvReferralsLogo = referralsBinding.ivReferralLogo;
        mIvReferralsAD = referralsBinding.ivReferralReferralAd;
        mCivMineHeader = referralsBinding.civReferralMineHeader;
        mTvMineNickname = referralsBinding.tvReferralMineNickname;
        mTvInvitationCode = referralsBinding.tvReferralMineCode;
        mBtnReferralsSheared = referralsBinding.btnReferralSheared;
    }

    private void init() {
        mTmReferrals.setLeftIcon(R.drawable.left);
        mTmReferrals.setTitle(R.string.referral);
        mTmReferrals.setTitleColor(getResources().getColor(R.color.white));
        referralsBinding.setClick(this);

        //  mTvMineNickname.setText(TextUtils.isEmpty(mUser.getNickname()) ? UIUtils.getString(R.string.nickname) : mUser.getNickname());
        mTvInvitationCode.setText(mUser.getShare_code());
        Glide.with(mContext).load(Network.IMAGE + mUser.getAvatar()).into(mCivMineHeader);

        getShearedAD();
//        getShearedUrl();
    }

    private void setListeners() {
        mTmReferrals.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTvInvitationCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(mUser.getShare_code());
                //ToastUtil.toastSth(getContext() , "订单号已复制到剪切板，快去粘贴吧~");
                return false;
            }
        });
    }

    public void onViewClick(View view) {
//        ToastUtils.showShortToast(mContext, "分享邀请码");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_PHONE_STATE,
            };
//            ActivityCompat.requestPermissions(mContext, mPermissionList, Constants.REQUEST_PERMISSION_CODE);
            for (String permission : mPermissionList) {
                Log.w("llm", "onViewClick: permission = " + permission);
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mContext, new String[]{permission}, Constants.REQUEST_PERMISSION_CODE);
                }
            }
        }

    }

    private void getShearedAD() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_SHARE_AD)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<ShearedAD>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(ShearedAD response) {
                        if (isSuccess(response)) {
                            Glide.with(mContext).load(Network.IMAGE + response.share_ad).into(mIvReferralsAD);
                            shearedUrl = Network.BASE + "/" + response.share_code;
                            mTvMineNickname.setText("已邀请" + response.count + "人");



                        }
                    }
                });
    }

    private void getShearedUrl() {
        OkHttpUtils.post()
                .url(Network.User.USER_SHARE_CODE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<Url>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Url response) {
                        if (isSuccess(response)) {
                            shearedUrl = Network.BASE + "/" + response.getUrl();
                        }
                    }
                });
    }


}
