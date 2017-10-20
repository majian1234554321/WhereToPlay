package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.CheckCommentActivity;
import com.fanc.wheretoplay.adapter.WriteCommentAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentCommentBinding;
import com.fanc.wheretoplay.datamodel.CommentPage;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/15.
 */

public class CommentFragment extends BaseFragment {

    FragmentCommentBinding commentBinding;

    TopMenu mTmComment;
    TextView mTvCommentTitle;
    TextView mTvCommentReserved;
    TextView mTvCommentRealTime;
    TextView mTvCommentRoom;
    TextView mTvCommentReserveRealCode;
    TextView mTvCommentPrice;
    TextView mTvCommentEarnest;
    ImageView mIvCommentEnvironment;
    ImageView mIvCommentAtmosphere;
    ImageView mIvCommentService;
    ImageView mIvCommentOther;
    RecyclerView mRvCommentEnvironment;
    RecyclerView mRvCommentAtmosphere;
    RecyclerView mRvCommentService;
    RecyclerView mRvCommentOther;
    LinearLayout mLlCommentEnvironmentEdit;
    EditText mEtCommentEnvironment;
    LinearLayout mLlCommentAtmosphereEdit;
    EditText mEtCommentAtmosphere;
    LinearLayout mLlCommentServiceEdit;
    EditText mEtCommentService;
    LinearLayout mLlCommentOtherEdit;
    EditText mEtCommentOther;

    // 商铺id
    String storeId;
    String orderId;

    // 环境
    List<CommentPage.Tag> environments;
    WriteCommentAdapter environmentAdapter;
    // 气氛
    List<CommentPage.Tag> atmospheres;
    WriteCommentAdapter atmosphereAdapter;
    // 服务
    List<CommentPage.Tag> services;
    WriteCommentAdapter serviceAdapter;
    // 其他
    List<CommentPage.Tag> others;
    WriteCommentAdapter otherAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        commentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment, null, false);
        initViews();
        init();
        setListener();
        return commentBinding.getRoot();
    }

    private void initViews() {
        mTmComment = commentBinding.tmComment;
        mTvCommentReserved = commentBinding.tvCommentReserved;
        mRvCommentEnvironment = commentBinding.rvCommentEnvironment;
        mRvCommentAtmosphere = commentBinding.rvCommentAtmosphere;
        mRvCommentService = commentBinding.rvCommentService;
        mRvCommentOther = commentBinding.rvCommentOther;
        mIvCommentEnvironment = commentBinding.ivCommentEnvironment;
        mIvCommentAtmosphere = commentBinding.ivCommentAtmosphere;
        mIvCommentService = commentBinding.ivCommentService;
        mIvCommentOther = commentBinding.ivCommentOther;
        mLlCommentEnvironmentEdit = commentBinding.llCommentEnvironmentEdit;
        mLlCommentAtmosphereEdit = commentBinding.llCommentAtmosphereEdit;
        mLlCommentServiceEdit = commentBinding.llCommentServiceEdit;
        mLlCommentOtherEdit = commentBinding.llCommentOtherEdit;
        mEtCommentEnvironment = commentBinding.etCommentEnvironmentEdit;
        mEtCommentAtmosphere = commentBinding.etCommentAtmosphereEdit;
        mEtCommentService = commentBinding.etCommentServiceEdit;
        mEtCommentOther = commentBinding.etCommentOtherEdit;
    }

    private void init() {
        mTmComment.setLeftIcon(R.drawable.left);
        mTmComment.setTitle(R.string.write_comment);
        mTmComment.setRightText(R.string.submit);

        commentBinding.setClick(this);

        initList();

        if (storeId != null && orderId != null) {
            getCommentPageData(storeId, orderId);
        }
    }

    /**
     * 列表：环境、气氛、服务和其他
     */
    private void initList() {
        environments = new ArrayList<>();
        environmentAdapter = new WriteCommentAdapter(mContext, environments);
        GridLayoutManager environmentLayoutManager = new GridLayoutManager(mContext, 4);
        mRvCommentEnvironment.setLayoutManager(environmentLayoutManager);
        mRvCommentEnvironment.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(5), mContext.getResources().getColor(R.color.bg)));
        mRvCommentEnvironment.setAdapter(environmentAdapter);

        atmospheres = new ArrayList<>();
        atmosphereAdapter = new WriteCommentAdapter(mContext, atmospheres);
        GridLayoutManager atmosphereLayoutManager = new GridLayoutManager(mContext, 4);
        mRvCommentAtmosphere.setLayoutManager(atmosphereLayoutManager);
        mRvCommentAtmosphere.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(5), mContext.getResources().getColor(R.color.bg)));
        mRvCommentAtmosphere.setAdapter(atmosphereAdapter);

        services = new ArrayList<>();
        serviceAdapter = new WriteCommentAdapter(mContext, services);
        GridLayoutManager serviceLayoutManager = new GridLayoutManager(mContext, 4);
        mRvCommentService.setLayoutManager(serviceLayoutManager);
        mRvCommentService.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(5), mContext.getResources().getColor(R.color.bg)));
        mRvCommentService.setAdapter(serviceAdapter);

        others = new ArrayList<>();
        otherAdapter = new WriteCommentAdapter(mContext, others);
        GridLayoutManager otherLayoutManager = new GridLayoutManager(mContext, 4);
        mRvCommentOther.setLayoutManager(otherLayoutManager);
        mRvCommentOther.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                UIUtils.dp2Px(5), mContext.getResources().getColor(R.color.bg)));
        mRvCommentOther.setAdapter(otherAdapter);
    }

    private void setListener() {
        mTmComment.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTmComment.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComment();
            }
        });
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_comment_environment:
                mLlCommentEnvironmentEdit.setVisibility(View.VISIBLE);
                mIvCommentEnvironment.setVisibility(View.GONE);
                break;
            case R.id.iv_comment_atmosphere:
                mLlCommentAtmosphereEdit.setVisibility(View.VISIBLE);
                mIvCommentAtmosphere.setVisibility(View.GONE);
                break;
            case R.id.iv_comment_service:
                mLlCommentServiceEdit.setVisibility(View.VISIBLE);
                mIvCommentService.setVisibility(View.GONE);
                break;
            case R.id.iv_comment_other:
                mLlCommentOtherEdit.setVisibility(View.VISIBLE);
                mIvCommentOther.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 商铺id
     *
     * @param storeId
     */
    public CommentFragment setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public CommentFragment setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    private void getCommentPageData(String storeId, String orderId) {
        OkHttpUtils.post()
                .url(Network.User.USER_COMMENT_PAGE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.STORE_ID, storeId)
                .addParams(Network.Param.ORDERFORM_ID, orderId)
                .build()
                .execute(new DCallback<CommentPage>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(CommentPage response) {
                        if (isSuccess(response)) {
                            if (response.getOrder() != null) {
                                showOrder(response.getOrder());
                            }
                            if (response.getTags() != null) {
                                showTags(response.getTags());
                            }
                        }
                    }
                });
    }

    /**
     * 订单信息
     *
     * @param order
     */
    private void showOrder(CommentPage.Order order) {
        commentBinding.setOrder(order);
        // 状态
        switch (order.getStatus()) {
            case "0":
                mTvCommentReserved.setText(R.string.stay_pay);
                break;
            case "1":
                mTvCommentReserved.setText(R.string.canceled);
                break;
            case "2":
                mTvCommentReserved.setText(R.string.pay_success);
                break;
            case "3":
                mTvCommentReserved.setText(R.string.consuming);
                break;
            case "4":
                mTvCommentReserved.setText(R.string.complete_statement_of_account);
                break;
            default:
                mTvCommentReserved.setText("");
                break;
        }
    }

    /**
     * 标签
     *
     * @param tags
     */
    private void showTags(List<CommentPage.Tag> tags) {
        for (CommentPage.Tag tag : tags) {
            switch (tag.getType()) {
                case "1":// 环境
                    environments.add(tag);
                    break;
                case "2":// 气氛
                    atmospheres.add(tag);
                    break;
                case "3":// 服务
                    services.add(tag);
                    break;
                case "4":// 其他
                    others.add(tag);
                    break;
                default:
                    break;
            }
        }
        environmentAdapter.notifyDataSetChanged();
        atmosphereAdapter.notifyDataSetChanged();
        serviceAdapter.notifyDataSetChanged();
        otherAdapter.notifyDataSetChanged();
    }

    /**
     * 提交评价
     */
    private void submitComment() {
        Map<String, String> param = new HashMap<>();
        param.put(Network.Param.TOKEN, mUser.getToken());
        param.put(Network.Param.STORE_ID, storeId);
        param.put(Network.Param.ORDER_ID,orderId);
        String sysEnvironment = environmentAdapter.getSelectedItemId();
        // 环境
        if (sysEnvironment != null) {
            param.put(Network.Param.SYS_ENVIRONMENT, sysEnvironment);
        }
        String cusEnvironment = mEtCommentEnvironment.getText().toString().trim();
        if (!cusEnvironment.isEmpty()) {
            param.put(Network.Param.CUS_ENVIRONMENT, cusEnvironment);
        }
        // 气氛
        String sysAtmosphere = atmosphereAdapter.getSelectedItemId();
        if (sysAtmosphere != null) {
            param.put(Network.Param.SYS_ATMOSPHERE, sysAtmosphere);
        }
        String cusAtmosphere = mEtCommentAtmosphere.getText().toString().trim();
        if (!cusAtmosphere.isEmpty()) {
            param.put(Network.Param.CUS_ATMOSPHERE, cusAtmosphere);
        }
        // 服务
        String sysService = serviceAdapter.getSelectedItemId();
        if (sysService != null) {
            param.put(Network.Param.SYS_SERVICE, sysService);
        }
        String cusService = mEtCommentService.getText().toString().trim();
        if (!cusService.isEmpty()) {
            param.put(Network.Param.CUS_SERVICE, cusService);
        }
        // 其他
        String sysOther = otherAdapter.getSelectedItemId();
        if (sysOther != null) {
            param.put(Network.Param.SYS_OTHER, sysOther);
        }
        String cusOther = mEtCommentOther.getText().toString().trim();
        if (!cusOther.isEmpty()) {
            param.put(Network.Param.CUS_OTHER, cusOther);
        }

        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_SUBMIT_COMMENT)
                .params(param)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            if (response.isIs_ok()) {
                                ToastUtils.makePicTextShortToast(mContext, "评价提交成功");
                                Intent intent = new Intent(mContext, CheckCommentActivity.class);
                                intent.putExtra(Constants.STORE_ID, storeId);
                                mContext.startActivity(intent);
                                mContext.finish();
                            }
                        }
                    }
                });

    }
}
