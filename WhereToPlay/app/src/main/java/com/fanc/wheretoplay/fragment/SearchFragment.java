package com.fanc.wheretoplay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.DetailActivity;
import com.fanc.wheretoplay.adapter.SearchAdapter;
import com.fanc.wheretoplay.adapter.SearchResultAdapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentSearchBinding;
import com.fanc.wheretoplay.datamodel.Recommendation;
import com.fanc.wheretoplay.datamodel.Search;
import com.fanc.wheretoplay.db.RecordDatabaseHelper;
import com.fanc.wheretoplay.divider.RecycleViewDivider;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.FlowLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/14.
 */

public class SearchFragment extends BaseFragment {
    FragmentSearchBinding searchBinding;

    ImageView mIvSearch;
    EditText mEtSearch;
    TextView mTvSearch;
    LinearLayout mLlSearchBefore;
    TextView mTvSearchHistory;
    //    RecyclerView mRvSearchReferral;
    FlowLayout mFlSearchReferral;
    RecyclerView mRvSearchHistory;
    ListView mLvSearchResult;
    SearchResultAdapter resultAdapter;
    //    List<String> referrals;
//    SearchAdapter referralAdapter;
    List<String> histories;
    SearchAdapter historyAdapter;
    List<Search.Store> stores;

    String keyword;
    //搜索历史，数据库工具
    RecordDatabaseHelper helper;
    private double lat, lng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, null, false);
        initViews();
        init();
        setListener();
        return searchBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mEtSearch.clearFocus();
    }

    private void initViews() {
        mIvSearch = searchBinding.ivSearchBack;
        mEtSearch = searchBinding.etSearch;
        mTvSearch = searchBinding.tvSearch;
        mLlSearchBefore = searchBinding.llSearchBefore;
        mTvSearchHistory = searchBinding.tvSearchHistory;
//        mRvSearchReferral = searchBinding.rvSearchReferral;
        mFlSearchReferral = searchBinding.flSearchReferral;
        mRvSearchHistory = searchBinding.rvSearchHistory;
        mLvSearchResult = searchBinding.lvSearchResult;
    }

    private void init() {
        searchBinding.setClick(this);

//        referrals = new ArrayList<>();
//        GridLayoutManager referralGridLayoutManager = new GridLayoutManager(mContext, 3);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, LinearLayout.HORIZONTAL);
//        mRvSearchReferral.setLayoutManager(layoutManager);
//        referralAdapter = new SearchAdapter(mContext, referrals);
//        mRvSearchReferral.setAdapter(referralAdapter);
//        RecycleViewDivider referralDivider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL, UIUtils.dp2Px(10), UIUtils.getColor(R.color.bg));
//        mRvSearchReferral.addItemDecoration(referralDivider);

        histories = new ArrayList<>();
        LinearLayoutManager historyLayoutManager = new LinearLayoutManager(mContext);
        historyLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRvSearchHistory.setLayoutManager(historyLayoutManager);
        historyAdapter = new SearchAdapter(mContext, histories);
        mRvSearchHistory.setAdapter(historyAdapter);
        RecycleViewDivider historyDivider = new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL, UIUtils.dp2Px(1), getResources().getColor(R.color.pay_reserve_list_divider_white));
        mRvSearchHistory.addItemDecoration(historyDivider);

        mTvSearchHistory.setText(R.string.search_history);
        if (LocationUtils.location != null) {
            lat = LocationUtils.location.getLatitude();
            lng = LocationUtils.location.getLongitude();
        } else {
            lat = -1;
            lng = -1;
        }
        getReferralSearch(lat, lng);

        stores = new ArrayList<>();
        resultAdapter = new SearchResultAdapter(mContext, stores);
        mLvSearchResult.setAdapter(resultAdapter);

        helper = new RecordDatabaseHelper(mContext);
        showSearchRecord();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                mContext.finish();
                break;
            case R.id.tv_search:
                search(0, 0);
                break;
            default:
                break;
        }
    }

    private void setListener() {
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        mLvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.PAGE, Constants.MERCHANT_DETAIL);
                intent.putExtra(Constants.STORE_ID, stores.get(position).id);

                intent.putExtra("type",stores.get(position).style);

                mContext.startActivity(intent);
            }
        });
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mLlSearchBefore.setVisibility(View.VISIBLE);
//                    mEtSearch.setHint(R.string.hint_search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        historyAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String search, int position) {
                mEtSearch.setText(search);
                mEtSearch.setHint(search);
                mEtSearch.setSelection(search.length());
                search(0, 0);
            }
        });
    }

    /**
     * 显示历史消息
     */
    private void showSearchRecord() {
        List<String> search = helper.query();
        Log.i("aaa", "showSearchRecord: " + search);
        histories.clear();
        histories.addAll(search);
        historyAdapter.notifyDataSetChanged();
    }

    private void getReferralSearch(double lat, double lng) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Param.TOKEN, mUser.getToken());
        if (Constants.USELESS_NUMBER_PARAM != lat) {
            params.put(Network.Param.LAT, String.valueOf(lat));
        }
        if (Constants.USELESS_NUMBER_PARAM != lng) {
            params.put(Network.Param.LNG, String.valueOf(lng));
        }
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.COMMON_RECOMMENDATION)
                .params(params)
                .build()
                .execute(new DCallback<Recommendation>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Recommendation response) {
                        if (isSuccess(response)) {
                            if (response.data != null) {
                                showReferralSearch(response.data);
                            }
                        }
                    }
                });
    }

    private void showReferralSearch(List<Recommendation.DataBean> dataBeans) {
        if (dataBeans.size() < 1) {
            return;
        }
        String keyword = dataBeans.get((int) (Math.random() * dataBeans.size())).name;
        mEtSearch.setHint(keyword);
        for (final Recommendation.DataBean dataBean : dataBeans) {
//            referrals.add(dataBean.name);
            TextView tv = new TextView(mContext);
//            tv.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//            tv.setHeight(UIUtils.dp2Px(30));
            ViewGroup.MarginLayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, UIUtils.dp2Px(30));
            layoutParams.setMargins(0, 0, UIUtils.dp2Px(15), 0);
            tv.setLayoutParams(layoutParams);
            tv.setBackgroundResource(R.drawable.shape_item_search);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setTextColor(UIUtils.getColor(R.color.pay_reserve_list_text));
            tv.setPadding(UIUtils.dp2Px(10), 0, UIUtils.dp2Px(10), 0);
            tv.setText(dataBean.name);
            mFlSearchReferral.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtSearch.setHint(dataBean.name);
                    mEtSearch.setText(dataBean.name);
                    mEtSearch.setSelection(dataBean.name.length());
                    search(0, 0);
                }
            });
        }
//        referralAdapter.notifyDataSetChanged();
    }

    private void saveSearchKeyword(String word) {
        helper.insert(word);
    }

    private void search(int page, int size) {
        keyword = mEtSearch.getText().toString().trim();
        if (keyword.isEmpty()) {
            keyword = mEtSearch.getHint().toString().trim();
        }
        if (keyword.isEmpty()) {
            ToastUtils.makePicTextShortToast(mContext, "请输入搜索关键字");
            return;
        }

        showProgress();
        OkHttpUtils.post()
                .url(Network.User.COMMON_SEARCH)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.PAGE, String.valueOf(page))
                .addParams(Network.Param.SIZE, String.valueOf(size))
                .addParams(Network.Param.KEYWORD, keyword)
                .build()
                .execute(new DCallback<Search>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Search response) {
                        if (isSuccess(response)) {
                            if (response.list != null) {
                                showSearchResult(response.list);
                            }
                            mLlSearchBefore.setVisibility(View.GONE);
                            saveSearchKeyword(keyword);
                            // 如果重复了，就移除掉重复的
                            for (String word : histories) {
                                if (TextUtils.equals(word, keyword)) {
                                    histories.remove(word);
                                    break;
                                }
                            }
                            histories.add(0, keyword);
                            historyAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void showSearchResult(List<Search.Store> stores) {
        this.stores.clear();
        this.stores.addAll(stores);
        resultAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        helper = null;

    }
}
