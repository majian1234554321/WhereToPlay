package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.FragmentWaiterPhotoBinding;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;

/**
 * Created by Administrator on 2017/7/7.
 */

public class WaiterPhotoActivity extends BaseActivity {
    FragmentWaiterPhotoBinding photoBinding;

    TopMenu mTmWaiterPhoto;
    ImageView mIvWaiterPhoto;
    TextView mTvWaiterName;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoBinding = DataBindingUtil.setContentView(this, R.layout.fragment_waiter_photo);
        initViews();
        init();
        setListeners();
    }

    private void initViews() {
        mTmWaiterPhoto = photoBinding.tmWaiterPhoto;
        mIvWaiterPhoto = photoBinding.ivWaiterPhoto;
        mTvWaiterName = photoBinding.tvWaiterPhotoName;
    }

    private void init() {
        bundle = getIntent().getExtras();
        mTmWaiterPhoto.setLeftIcon(R.drawable.left);
        mTmWaiterPhoto.setTitle(R.string.detail);
        mTmWaiterPhoto.setRightText(R.string.next);
        mTvWaiterName.setText(bundle.getString(Constants.WAITER_NAME));
        Glide.with(mContext).load(Network.IMAGE + bundle.getString(Constants.WAITER_IMAGE)).into(mIvWaiterPhoto);
    }

    private void setListeners() {
        mTmWaiterPhoto.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(Constants.ACTION_WAITER_PHOTO_CHECKED);
            }
        });
        mTmWaiterPhoto.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(Constants.ACTION_WAITER_PHOTO_SELECTED);

            }
        });
    }

    private void sendBroadcast(String action) {
        Intent intent = new Intent(action);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        sendBroadcast(Constants.ACTION_WAITER_PHOTO_CHECKED);
        super.onBackPressed();
    }
}
