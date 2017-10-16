package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.CommentDetailAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentCommentDetailBinding;
import com.fanc.wheretoplay.datamodel.CommentDetail;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/27.
 */

public class CommentDetailFragment extends BaseFragment {

    FragmentCommentDetailBinding detailBinding;

    RecyclerView mRvCommentDetail;

    String pageTag;
    String storeId;
    String commentUrl;
    List<CommentDetail.Comment> comments;
    CommentDetailAdapter detailAdapter;

    int page, size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment_detail, container, false);
        initView();
        init();
        return detailBinding.getRoot();
    }

    private void initView() {
        mRvCommentDetail = detailBinding.rvCommentDetail;
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvCommentDetail.setLayoutManager(layoutManager);
        mRvCommentDetail.setItemAnimator(new DefaultItemAnimator());
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.pay_reserve_list_divider));
        mRvCommentDetail.addItemDecoration(divider);
        mRvCommentDetail.setItemAnimator(new DefaultItemAnimator());
        comments = new ArrayList<>();
        detailAdapter = new CommentDetailAdapter(mContext, comments);
        mRvCommentDetail.setAdapter(detailAdapter);
        Log.d("aaa", "init: adapter.getItemCount() = " + detailAdapter.getItemCount());

        initCommentUrl();
        if (Constants.COMMENT_DETAIL_ENVIRONMENT.equals(pageTag)) {
            showProgress();
        }
        getCommentDetailList(page, size);
    }

    public CommentDetailFragment setPageTag(String pageTag) {
        this.pageTag = pageTag;
        return this;
    }

    public CommentDetailFragment setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    private void initCommentUrl() {
        switch (pageTag) {
            case Constants.COMMENT_DETAIL_ENVIRONMENT:
                commentUrl = Network.User.PUBLIC_ENVIRONMENT;
                break;
            case Constants.COMMENT_DETAIL_ATMOSPHERE:
                commentUrl = Network.User.PUBLIC_ATMOSPHERE;
                break;
            case Constants.COMMENT_DETAIL_SERVICE:
                commentUrl = Network.User.PUBLIC_SERVICE;
                break;
            case Constants.COMMENT_DETAIL_OTHER:
                commentUrl = Network.User.PUBLIC_OTHER;
                break;
        }
    }

    private void getCommentDetailList(int page, int size) {
        OkHttpUtils.post()
                .url(commentUrl)
                .addParams(Network.Param.STORE_ID, storeId)
                .addParams(Network.Param.PAGE, String.valueOf(page))
                .addParams(Network.Param.SIZE, String.valueOf(size))
                .build()
                .execute(new DCallback<CommentDetail>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(CommentDetail response) {
                        if (isSuccess(response)) {
                            if (response.list != null) {
                                showCommentDetailList(response.list);
                            }
                        }
                    }
                });
    }

    private void showCommentDetailList(List<CommentDetail.Comment> commentList) {
        comments.addAll(commentList);
        detailAdapter.notifyDataSetChanged();
    }

}
