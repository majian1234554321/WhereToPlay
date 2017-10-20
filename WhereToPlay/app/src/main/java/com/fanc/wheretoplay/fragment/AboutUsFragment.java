package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentAboutUsBinding;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.ItemView;
import com.fanc.wheretoplay.view.TopMenu;

/**
 * Created by Administrator on 2017/6/19.
 */

public class AboutUsFragment extends BaseFragment {
    FragmentAboutUsBinding aboutUsBinding;

    TopMenu mTmAboutUs;
    ItemView mIvAboutUsVersion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        aboutUsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false);
        initViews();
        init();
        setListener();
        return aboutUsBinding.getRoot();
    }

    private void initViews() {
        mTmAboutUs = aboutUsBinding.tmAboutUs;
        mIvAboutUsVersion = aboutUsBinding.ivAboutUsVersion;
    }

    private void init() {
        mTmAboutUs.setLeftIcon(R.drawable.left);
        mTmAboutUs.setTitle(R.string.about_us);

        displayCurrentVersion();

    }

    private void setListener() {
        mTmAboutUs.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    /**
     * 显示当前版本
     */
    private void displayCurrentVersion() {
        mIvAboutUsVersion.setIcon(R.drawable.version);
        mIvAboutUsVersion.setText(R.string.current_version);
        mIvAboutUsVersion.setTextColor(mContext.getResources().getColor(R.color.ddialog_text_color));
        mIvAboutUsVersion.setRightTextColor(mContext.getResources().getColor(R.color.ddialog_text_color));
        mIvAboutUsVersion.setBackgroundColor(mContext.getResources().getColor(R.color.about_us_version));
        mIvAboutUsVersion.isShowLine(false);

        mIvAboutUsVersion.setRightText("v" + UIUtils.getAppVersionName());

    }

}
