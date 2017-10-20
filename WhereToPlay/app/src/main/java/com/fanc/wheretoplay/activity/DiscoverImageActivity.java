package com.fanc.wheretoplay.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivityDiscoverImageBinding;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;

import uk.co.senab.photoview.PhotoView;

public class DiscoverImageActivity extends BaseActivity {

    PhotoView mPvDiscover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDiscoverImageBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_discover_image);
        mPvDiscover = binding.pvDiscover;
        init();
    }

    private void init() {
        String url = getIntent().getStringExtra(Constants.URL);
        Glide.with(mContext).load(Network.IMAGE + url).placeholder(R.drawable.default_rect).into(mPvDiscover);
    }



}
