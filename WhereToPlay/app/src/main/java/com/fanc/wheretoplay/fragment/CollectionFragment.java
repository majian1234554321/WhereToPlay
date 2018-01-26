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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.CollectionAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentCollectionBinding;
import com.fanc.wheretoplay.datamodel.CollectionList;
import com.fanc.wheretoplay.datamodel.DelectCollection;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.MineMoney;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.AlertDialog;
import com.fanc.wheretoplay.view.TopMenu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2017/6/17.
 */

public class CollectionFragment extends BaseFragment {

    FragmentCollectionBinding binding;

    TopMenu mTmCollection;
    RecyclerView mRvCollection;
    /**
     * 收藏列表
     */
    List<CollectionList.Collection> collections;
    CollectionAdapter collectionAdapter;
    // 删除的收藏id
    String collectionIds;
    private double lat, lng;
    boolean isDeleting;
    List<Integer> deleteIndex;
    private RelativeLayout rrrrr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, null, false);
        initViews();
        init();
        setListener();
        return binding.getRoot();
    }

    private void initViews() {
        mTmCollection = binding.tmCollection;
        mRvCollection = binding.rvCollection;
        rrrrr = binding.rrrrrr;
    }

    private void init() {
        mTmCollection.setLeftIcon(R.drawable.left);
        mTmCollection.setTitle(R.string.collection);
        mTmCollection.setRightText(R.string.delete);
        mTmCollection.setTitleColor(getResources().getColor(R.color.white));
        mTmCollection.setRightTextColor(getResources().getColor(R.color.white));

        // 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvCollection.setLayoutManager(layoutManager);
        collections = new ArrayList<>();
        collectionAdapter = new CollectionAdapter(mContext, collections);
        RecycleViewDivider viewDivider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.pay_reserve_list_divider_white));
        mRvCollection.addItemDecoration(viewDivider);
        mRvCollection.setAdapter(collectionAdapter);
        if (LocationUtils.location != null) {
            lat = LocationUtils.location.getLatitude();
            lng = LocationUtils.location.getLongitude();
        } else {
            lat = -1;
            lng = -1;
        }
        getCollectionList(lat, lng, -1, -1);

    }

    private void setListener() {
        mTmCollection.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mTmCollection.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (collections.size() > 0) {
                    if (isDeleting) {// 是删除状态，执行删除操作
                        final String collectionIds = getSelectedCollectionId();
                        if (collectionIds != null) {
                            new AlertDialog(mContext)
                                    .setTitle("提示")
                                    .setContent("确定要删除收藏吗")
                                    .setBtnOnClickListener(new AlertDialog.OnBtnClickListener() {
                                        @Override
                                        public void onBtnClick(View view, String input) {
                                            emptyCollection(collectionIds);
                                            isDeleting = !isDeleting;
                                        }
                                    })
                                    .setCanceledOnTouchOutside(false)
                                    .show();
                        } else {
                            collectionAdapter.setDeleting(false);
                            isDeleting = !isDeleting;
                        }
                    } else {// 非删除状态，进入删除状态
                        collectionAdapter.setDeleting(true);
                        ToastUtils.makePicTextShortToast(mContext, "请选择要删除的收藏，再次点击删除按钮进行删除");
                        isDeleting = !isDeleting;
                    }
//                    isDeleting = !isDeleting;
                } else {
                    ToastUtils.makePicTextShortToast(mContext, "你还没有收藏哦");
                }
            }
        });
    }

    /**
     * 获取选中的收藏id，多个以“,”隔开
     * 如果没有选中反null
     *
     * @return
     */
    private String getSelectedCollectionId() {
        if (deleteIndex == null) {
            deleteIndex = new LinkedList();
        } else {
            deleteIndex.clear();
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i : collectionAdapter.status.keySet()) {
            if (collectionAdapter.status.get(i)) {
                deleteIndex.add(i);
                if (count != 0) {
                    sb.append(",");
                }
                sb.append(collections.get(i).getId());
            }
            count++;
        }

        if (deleteIndex.size() < 1) {
            return null;
        }

        Collections.sort(deleteIndex);
        return sb.toString();
    }

    /**
     * 获取收藏列表
     *
     * @param lat
     * @param lng
     * @param page
     * @param size
     */
    private void getCollectionList(double lat, double lng, int page, int size) {
        Map<String, String> param = new HashMap<>();
        param.put(Network.Param.TOKEN, mUser.getToken());

        if (Constants.USELESS_NUMBER_PARAM == lat) {
            param.put(Network.Param.LAT, "");
        } else {
            param.put(Network.Param.LAT, String.valueOf(lat));
        }
        if (Constants.USELESS_NUMBER_PARAM == lng) {
            param.put(Network.Param.LNG, "");
        } else {
            param.put(Network.Param.LNG, String.valueOf(lng));
        }
        if (Constants.USELESS_NUMBER_PARAM != page) {
            param.put(Network.Param.PAGE, String.valueOf(page));
        }
        if (Constants.USELESS_NUMBER_PARAM != size) {
            param.put(Network.Param.SIZE, String.valueOf(size));
        }

        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_COLLECTION_LIST)
                .params(param)
                .build()
                .execute(new DCallback<CollectionList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(CollectionList response) {
                        if (isSuccess(response)) {
                            if (response.getList() != null) {
                                showCollectionList(response.getList());
                            }else {
                                rrrrr.setVisibility(View.VISIBLE);
                            }
                        }else {
                            rrrrr.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 显示收藏列表
     *
     * @param collections
     */
    private void showCollectionList(List<CollectionList.Collection> collections) {
        if (collections.size() > 0) {
            this.collections.clear();
            this.collections.addAll(collections);
            collectionAdapter.notifyDataSetChanged();
        }else {
            rrrrr.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 删除收藏的id
     *
     * @return
     */
    private String getCollectionIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < collections.size(); i++) {
            CollectionList.Collection collection = collections.get(i);
            if (i != 0) {
                sb.append(",");
            }
            sb.append(collection.getId());
        }
        return sb.toString();
    }

    /**
     * 删除收藏
     */
    private void emptyCollection(String collectionIds) {
        showProgress();

        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData(Network.Param.TOKEN, mUser.getToken());
        MultipartBody.Part requestFileB =
                MultipartBody.Part.createFormData(Network.Param.COLLECT_ID, collectionIds);

        Retrofit_RequestUtils.INSTANCE.getRequest().delectCollection(requestFileA, requestFileB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DelectCollection>() {

                    @Override
                    public void onError(Throwable e) {
                        closeProgress();
                        Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(DelectCollection response) {
                        closeProgress();
                        if (response != null) {
                            if (response.getContent().isIs_ok()) {
                                for (int i = deleteIndex.size() - 1; i >= 0; i --) {
                                    int nba = deleteIndex.get(i);
                                    collections.remove(nba );
                                    collectionAdapter.notifyItemRemoved(nba );
                                }
                                ToastUtils.makePicTextShortToast(mContext, "删除成功");
                                collectionAdapter.setDeleting(false);
                            }
                        }
                    }

                });
    }

}
