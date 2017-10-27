package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.adapter.MessageAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentCollectionBinding;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.MessageList;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MessageFragment extends BaseFragment {
    FragmentCollectionBinding messageBinding;

    TopMenu mTmMessage;
    RecyclerView mRvMessage;


    List<MessageList.Message> messages;
    MessageAdapter adapter;

    Receiver receiver;
    int pos = -1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        messageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, null, false);
        initViews();
        init();
        setListener();
        return messageBinding.getRoot();
    }

    private void initViews() {
        mTmMessage = messageBinding.tmCollection;
        mRvMessage = messageBinding.rvCollection;
    }

    private void setListener() {
        mTmMessage.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.PAGE, Constants.MESSAGE_DETAIL);
                intent.putExtra(Constants.ID, messages.get(position).getId());
                startActivity(intent);
                pos = position;
            }

            @Override
            public void onClickDelete(int position) {
                pos = position;
                deleteMessage(messages.get(position).getId());
            }
        });
    }

    private void init() {
        mTmMessage.setLeftIcon(R.drawable.left);
        mTmMessage.setTitle(R.string.message);
        mTmMessage.setTitleColor(getResources().getColor(R.color.white));

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMessage.setLayoutManager(layoutManager);
        messages = new ArrayList<>();
        adapter = new MessageAdapter(mContext, messages);
        RecycleViewDivider divider = new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.bg_gray));
        mRvMessage.addItemDecoration(divider);
        mRvMessage.setItemAnimator(new DefaultItemAnimator());
        mRvMessage.setHasFixedSize(true);
        mRvMessage.setAdapter(adapter);

        getMessageList();
        registerBroadcastReceiver();
    }

    private void getMessageList() {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_MESSAGE_LIST)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .build()
                .execute(new DCallback<MessageList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(MessageList response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showMessageList(response.getList());
                            }
                        }
                    }
                });
    }

    private void showMessageList(List<MessageList.Message> messages) {
        this.messages.clear();
        this.messages.addAll(messages);
        adapter.notifyDataSetChanged();
    }

    private void deleteMessage(String messageId) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_DELETE_MESSAGE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.MESSAGE_ID, messageId)
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
                                ToastUtils.showShortToast(mContext, "删除成功");
                                messages.remove(pos);
                                adapter.notifyItemRemoved(pos);
                                adapter.notifyItemRangeChanged(pos, adapter.getItemCount());
                            }
                        }
                    }
                });
    }

    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_MESSAGE_READED);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (pos != -1) {
                messages.get(pos).setReaded("1");
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
