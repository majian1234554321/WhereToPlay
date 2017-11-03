package com.fanc.wheretoplay.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.ServiceAdapter;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.databinding.ActivityServiceBinding;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LogUtils;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */
public class ServiceActivity extends BaseActivity {
    ActivityServiceBinding binding;

    TopMenu mTmService;
    RecyclerView mRvService;
    ImageButton mIbServiceAudio;
    EditText mEtServiceInput;
    ImageButton mIbServiceExpression;
    ImageButton mIbServiceImage;
    // 聊天列表
    List mData;
    ServiceAdapter serviceAdapter;
    // 店名
    String mStoreName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service);
        initViews();
        init();
        initListener();
    }

    private void initViews() {
        mTmService = binding.tmService;
        mRvService = binding.rvService;
        mIbServiceAudio = binding.ibServiceAudio;
        mEtServiceInput = binding.etServiceInput;
        mIbServiceExpression = binding.ibServiceExpression;
        mIbServiceImage = binding.ibServiceImage;
    }

    private void init() {
        mTmService.setLeftIcon(R.drawable.left);
        mTmService.setTitle(getIntent().getStringExtra(Constants.STORE_NAME) + "客服");
        mTmService.setTitleColor(getResources().getColor(R.color.white));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvService.setLayoutManager(linearLayoutManager);
        mData = new ArrayList();
        serviceAdapter = new ServiceAdapter(mContext, mData);
        mRvService.setAdapter(serviceAdapter);

    }

    private void initListener() {
        mTmService.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
