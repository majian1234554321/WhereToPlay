package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ServiceCommentFragment extends BaseFragment {

    FragmentCommentDetailBinding detailBinding;

    RecyclerView mRvCommentDetail;

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
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL,UIUtils.dp2Px(1),UIUtils.getColor(R.color.pay_reserve_list_divider));
        mRvCommentDetail.addItemDecoration(divider);

        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        CommentDetailAdapter adapter = new CommentDetailAdapter(mContext, list);
        mRvCommentDetail.setAdapter(adapter);

        Log.d("aaa", "init: adapter.getItemCount() = " + adapter.getItemCount());

    }

}
