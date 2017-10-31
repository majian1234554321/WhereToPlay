package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.CheckCommentsAdapter;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.databinding.ActivityCheckCommentsBinding;
import com.fanc.wheretoplay.datamodel.CheckComments;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.fragment.MerchantDetailFragment;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;

/**
 * Created by peace on 2017/10/30.
 */

public class CheckCommentsActivity extends BaseFragmentActivity {

    private ActivityCheckCommentsBinding mCheckCommentsBinding;
    private TopMenu mTopMenu;
    private RecyclerView mRc;
    private String mStoreId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCheckCommentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_comments);
        initView();
        init();
        setListener();
    }

    private void initView() {
        mTopMenu = mCheckCommentsBinding.tmCheckComments;
        mRc = mCheckCommentsBinding.rcComments;
    }

    private void init() {
        intent = getIntent();
        mTopMenu.setTitleColor(getResources().getColor(R.color.white));
        mTopMenu.setTitle(R.string.topmenu_comment);
        mTopMenu.setLeftIcon(R.drawable.left);
        //评论列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRc.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRc.addItemDecoration(divider1);
        CheckCommentsAdapter checkCommentsAdapter = new CheckCommentsAdapter(this);
        mRc.setAdapter(checkCommentsAdapter);
//        requestComments();
    }

    private void setListener() {
        mTopMenu.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
            }
        });
    }

    public void comments(View view) {   //不能把权限设置为私有
        switch (view.getId()) {
            case R.id.bt_comments_one:
//                requestComments();
                Log.e("wade", mSPUtils.getUser().getToken());
                ToastUtils.makePicTextShortToast(this, "1");
                break;
            case R.id.bt_comments_two:
                ToastUtils.makePicTextShortToast(this, "2");
                break;
            case R.id.bt_comments_three:
                ToastUtils.makePicTextShortToast(this, "3");
                break;
            case R.id.bt_comments_four:
                ToastUtils.makePicTextShortToast(this, "4");
                break;
            default:
                break;
        }
    }

    private void requestComments() {
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_COMMENTS)
                .addParams(Network.Param.TYPE, 1)
                .addParams(Network.Param.PAGEINDEX, 1)
                .addParams(Network.Param.PAGEINDEX, 1)
                .addParams(Network.Param.TOKEN,  mSPUtils.getUser().getToken())
                .build()
                .execute(new DCallback<CheckComments>() {
                    @Override
                    public void onError(Call call, Exception e) {
//                        connectError();
                    }

                    @Override
                    public void onResponse(CheckComments response) {
//                        if (isSuccess(response)) {
//                            if (response.isResult()) {
//                                isCollected = true;
//                            }
//                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
    }

}
