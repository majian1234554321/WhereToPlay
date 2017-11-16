package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.CheckCommentsAdapter;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.databinding.ActivityCheckCommentsBinding;
import com.fanc.wheretoplay.datamodel.CheckComments;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.fragment.MerchantDetailFragment;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.MyScrollView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

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
    //顶部4个分数
    private TextView mTvScoreEnvironment;
    private TextView mTvScoreatmosphere;
    private TextView mTvScoreservice;
    private TextView mTvAverageScore;
    //顶部3个星星
    private RatingBar mRbEnvironment;
    private RatingBar mRbAtmosphere;
    private RatingBar mRbService;
    //中部四个文字按钮
    private TextView mBtAll;
    private TextView mBtPleasure;
    private TextView mBtDispleasure;
    private TextView mBtpicture;

    private int mID = R.id.bt_comments_one;
    private CheckComments mResponse;

    private int page = 1;
    private int size = 6;
    private int mType = 1;
    private List mStores;
    private CheckCommentsAdapter checkCommentsAdapter;
    private MyScrollView mSvReserve;

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
        mTvAverageScore = mCheckCommentsBinding.tvCommentsScore;
        mTvScoreEnvironment = mCheckCommentsBinding.tvCommentsScoreOne;
        mTvScoreatmosphere = mCheckCommentsBinding.tvCommentsScoreTwo;
        mTvScoreservice = mCheckCommentsBinding.tvCommentsScoreThree;
        mRbEnvironment = mCheckCommentsBinding.rbCommentsOne;
        mRbAtmosphere = mCheckCommentsBinding.rbCommentsTwo;
        mRbService = mCheckCommentsBinding.rbCommentsThree;
        mBtAll = mCheckCommentsBinding.btCommentsOne;
        mBtPleasure = mCheckCommentsBinding.btCommentsTwo;
        mBtDispleasure = mCheckCommentsBinding.btCommentsThree;
        mBtpicture = mCheckCommentsBinding.btCommentsFour;
        mPtrl = mCheckCommentsBinding.ptrlReserve;
        mSvReserve = mCheckCommentsBinding.svReserve;
    }

    private void init() {
        intent = getIntent();
        mTopMenu.setTitleColor(getResources().getColor(R.color.white));
        mTopMenu.setTitle(R.string.topmenu_comment);
        mTopMenu.setLeftIcon(R.drawable.left);
        clickPress(R.id.bt_comments_one);
        mStores = new ArrayList();
        //评论列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRc.setLayoutManager(linearLayoutManager);
        //自定义的recyclerview分割线
        RecycleViewDivider divider1 = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.btn_pressed));
        mRc.addItemDecoration(divider1);
        requestComments(mType, page, size);
        //底部评论
        checkCommentsAdapter = new CheckCommentsAdapter(this,  mStores);
        mRc.setAdapter(checkCommentsAdapter);
        // 是否可以上下拉
        mSvReserve.setCanPullDown(true);
        mSvReserve.setCanPullUp(true);
    }

    private void setListener() {
        mTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 空实现，防止滑动后，点击传递到后面
            }
        });
        mTopMenu.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
            }
        });

        mPtrl.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                isPullDown = true;
                page = 0;
                size = 6;
                requestComments(mType, page, size);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                isPullUp = true;
                size = 6;
                if (mStores.size() < 6) {
                    page = 0;
                } else {
                    page++;
                }
                requestComments(mType, page, size);
            }
        });
    }

    /**
     * 点击中部四个按钮
     * @param view
     */
    public void comments(View view) {   //不能把权限设置为私有
        switch (view.getId()) {
            case R.id.bt_comments_one:
                if (mID != R.id.bt_comments_one) {
                    clickPress(R.id.bt_comments_one);
                    requestComments(1, 1, 6);
                }
                break;
            case R.id.bt_comments_two:
                if (mID != R.id.bt_comments_two) {
                    clickPress(R.id.bt_comments_two);
                    requestComments(2, 1, 6);
                }
                break;
            case R.id.bt_comments_three:
                if (mID != R.id.bt_comments_three) {
                    clickPress(R.id.bt_comments_three);
                    requestComments(3, 1, 6);
                }
                break;
            case R.id.bt_comments_four:
                if (mID != R.id.bt_comments_four) {
                    clickPress(R.id.bt_comments_four);
                    requestComments(4, 1, 6);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 点击按钮改变颜色
     * @param id
     */
    private void clickPress(int id) {
        recordId(id);
        pressChange(id);
    }

    /**
     * 改变颜色
     * @param id
     */
    private void pressChange(int id) {
        mBtAll.setBackground(getResources().getDrawable(R.drawable.shape_pink));
        mBtAll.setTextColor(getResources().getColor(R.color.text_black_sec));
        mBtPleasure.setBackground(getResources().getDrawable(R.drawable.shape_pink));
        mBtPleasure.setTextColor(getResources().getColor(R.color.text_black_sec));
        mBtDispleasure.setBackground(getResources().getDrawable(R.drawable.shape_unselect_gray));
        mBtDispleasure.setTextColor(getResources().getColor(R.color.text_gray));
        mBtpicture.setBackground(getResources().getDrawable(R.drawable.shape_pink));
        mBtpicture.setTextColor(getResources().getColor(R.color.text_black_sec));
        switch (id) {
            case R.id.bt_comments_one:
                mBtAll.setBackground(getResources().getDrawable(R.drawable.shape_reserve_info_checked));
                mBtAll.setTextColor(getResources().getColor(R.color.text_white));
                break;
            case R.id.bt_comments_two:
                mBtPleasure.setBackground(getResources().getDrawable(R.drawable.shape_reserve_info_checked));
                mBtPleasure.setTextColor(getResources().getColor(R.color.text_white));
                break;
            case R.id.bt_comments_three:
                mBtDispleasure.setBackground(getResources().getDrawable(R.drawable.shape_select_gray));
                mBtDispleasure.setTextColor(getResources().getColor(R.color.text_white));
                break;
            case R.id.bt_comments_four:
                mBtpicture.setBackground(getResources().getDrawable(R.drawable.shape_reserve_info_checked));
                mBtpicture.setTextColor(getResources().getColor(R.color.text_white));
                break;
            default:
                break;
        }
    }

    /**
     * 记录点下的id值
     * @param id
     */
    private void recordId(int id) {
        switch (id) {
            case R.id.bt_comments_one:
                mID = R.id.bt_comments_one;
                mType = 1;
                break;
            case R.id.bt_comments_two:
                mID = R.id.bt_comments_two;
                mType = 2;
                break;
            case R.id.bt_comments_three:
                mID = R.id.bt_comments_three;
                mType = 3;
                break;
            case R.id.bt_comments_four:
                mID = R.id.bt_comments_four;
                mType = 4;
                break;
        }

    }

    /**
     * 请求评论数据
     * @param type
     * @param pageIndex
     * @param pageSize
     */
    private void requestComments(int type, int pageIndex, int pageSize) {
        Log.e("dd",intent.getStringExtra(Constants.STORE_ID));
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_COMMENTS)
                .addParams(Network.Param.STORE_ID, intent.getStringExtra(Constants.STORE_ID))
                .addParams(Network.Param.TYPE, String.valueOf(type))
                .addParams(Network.Param.PAGEINDEX, String.valueOf(pageIndex))
                .addParams(Network.Param.PAGESIZE, String.valueOf(pageSize))
                .addParams(Network.Param.TOKEN,  mSPUtils.getUser().getToken())
                .build()
                .execute(new DCallback<CheckComments>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        refreshOrLoadFail();
                        ToastUtils.showShortToast(CheckCommentsActivity.this, getResources().getString(R.string.connect_net_error));
                    }

                    @Override
                    public void onResponse(CheckComments response) {
                        //判断拿到的数据是否正常
                        if (response.getComment_list() == null) {
                            ToastUtils.showShortToast(CheckCommentsActivity.this, getResources().getString(R.string.no_data));
                            refreshOrLoadFail();
                            return;
                        }
                        if (response.code != 0 || !TextUtils.isEmpty(response.message)) {
                            ToastUtils.showShortToast(CheckCommentsActivity.this, response.message);
                            if (response.code == 50003) {
                                Intent intent = new Intent(Constants.ACTION_RESIGN_IN);
                                LocalBroadcastManager.getInstance(CheckCommentsActivity.this).sendBroadcast(intent);
                            }
                            refreshOrLoadFail();
                            return;
                        } else {
                            mResponse = response;
                            showCheckComments(response);

                        }
                    }
                });
    }

    /**
     * 展示获取的数据
     * @param response
     */
    private void showCheckComments(CheckComments response) {
            //顶部分数显示
            mTvAverageScore.setText(response.getAverage_comment());
            mTvScoreEnvironment.setText(response.getComment_environment());
            mTvScoreatmosphere.setText(response.getComment_atmosphere());
            mTvScoreservice.setText(response.getComment_server());
            //顶部3个星星
            mRbEnvironment.setRating(Float.parseFloat(response.getComment_environment()));
            mRbAtmosphere.setRating(Float.parseFloat(response.getComment_atmosphere()));
            mRbService.setRating(Float.parseFloat(response.getComment_server()));
            //中部四个文字按钮
            mBtAll.setText(getResources().getText(R.string.check_comment_all) + "(" + response.getAll_count() +")");
            mBtPleasure.setText(getResources().getText(R.string.check_comment_pleasure) + "(" + response.getPleasure_count() +")");
            mBtDispleasure.setText(getResources().getText(R.string.check_comment_displeasure) + "(" + response.getDispleasure_count() +")");
            mBtpicture.setText(getResources().getText(R.string.check_comment_picture) + "(" + response.getPicture_count() +")");
        //集合添加数据
        storeList(response.getComment_list());
//        //底部评论
//        checkCommentsAdapter = new CheckCommentsAdapter(this, response);
//        mRc.setAdapter(checkCommentsAdapter);
    }

    private void storeList(List<CheckComments.CommentListBean> commentList) {
        if (isPullDown) {// 下拉刷新
//            if (commentList.size() == 0) {
//                ToastUtils.makePicTextShortToast(CheckCommentsActivity.this, "没有评论");
//                refreshAndLoadMoreSuccess();
//                return;
//            }
            mStores.clear();
            mStores.addAll(commentList);
            checkCommentsAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else if (isPullUp) {// 上拉加载
            if (commentList.size() == 0) {
                refreshOrLoadFail();
                return;
            }
            if (mStores.size() < 6) {
                mStores.clear();
            }
            mStores.addAll(commentList);
            checkCommentsAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
            return;
        } else {   //正常
            mStores.clear();
            mStores.addAll(commentList);
            checkCommentsAdapter.notifyDataSetChanged();
            if (commentList.size() == 0) {
                ToastUtils.showShortToast(CheckCommentsActivity.this, "暂时还没有评论");
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
    }

}
