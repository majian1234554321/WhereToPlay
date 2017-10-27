package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMessageDetailBinding;
import com.fanc.wheretoplay.datamodel.MessageDetail;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/22.
 */

public class MessageDetailFragment extends BaseFragment {

    FragmentMessageDetailBinding messageDetailBinding;

    TopMenu mTmMessageDetail;
    TextView mTvMessageDetailTitle;
    ImageView mIvMessageDetail;
    TextView mTvMessageDetailContent;

    String messageId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        messageDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_detail, container, false);
        initViews();
        init();
        setListener();
        return messageDetailBinding.getRoot();
    }

    private void initViews() {
        mTmMessageDetail = messageDetailBinding.tmMessageDetail;
        mTvMessageDetailTitle = messageDetailBinding.tvMessageDetailTitle;
        mIvMessageDetail = messageDetailBinding.ivMessageDetail;
        mTvMessageDetailContent = messageDetailBinding.tvMessageDetailContent;
    }

    private void init() {
        mTmMessageDetail.setTitle(R.string.message_detail);
        mTmMessageDetail.setLeftIcon(R.drawable.left);
        mTmMessageDetail.setTitleColor(getResources().getColor(R.color.white));

    }

    private void setListener() {
        mTmMessageDetail.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    public MessageDetailFragment setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (messageId != null) {
            getMessageDetail();
        }
    }

    private void getMessageDetail() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_MESSAGE_DETAIL)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.ID, messageId)
                .build()
                .execute(new DCallback<MessageDetail>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(MessageDetail response) {
                        if (isSuccess(response)) {
                            if (response.getDetail() != null) {
                                showMessageDetail(response.getDetail());
                                Intent intent = new Intent(Constants.ACTION_MESSAGE_READED);
                                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            }
                        }
                    }
                });
    }

    /**
     * 显示详情
     *
     * @param bean
     */
    private void showMessageDetail(MessageDetail.MessageDetailBean bean) {
        messageDetailBinding.setDetail(bean);
        Glide.with(mContext).load(Network.IMAGE + bean.getPicture()).into(mIvMessageDetail);
    }

}
