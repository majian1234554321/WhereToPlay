package com.fanc.wheretoplay.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.MessageAdapter;
import com.fanc.wheretoplay.adapter.MineFriendAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMineFriendBinding;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;

import java.util.ArrayList;

/**
 * Created by peace on 2017/11/8.
 */

public class MineFriendFragment extends BaseFragment {

    private FragmentMineFriendBinding mbinding;
    private RecyclerView mRv;
    private TopMenu mTmMineFriend;
    private MineFriendAdapter mineFriendAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_friend, null, false);
        initViews();
        init();
        setListener();
        return mbinding.getRoot();
    }

    private void initViews() {
        mRv = mbinding.rvMineFriend;
        mTmMineFriend = mbinding.tmMineFriend;
    }

    private void init() {
        mTmMineFriend.setLeftIcon(R.drawable.left);
        mTmMineFriend.setTitle(R.string.friend);
        mTmMineFriend.setTitleColor(getResources().getColor(R.color.white));

        //列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineFriendAdapter = new MineFriendAdapter(mContext, null);
        mRv.setLayoutManager(layoutManager);
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.pay_reserve_list_divider_white));
        mRv.addItemDecoration(divider);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(mineFriendAdapter);
    }

    private void setListener() {
        mTmMineFriend.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
            }
        });
    }
}
