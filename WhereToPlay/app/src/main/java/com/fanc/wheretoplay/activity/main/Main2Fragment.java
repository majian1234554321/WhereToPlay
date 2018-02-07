package com.fanc.wheretoplay.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.Main2Adapter;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.datamodel.BookListModel;

import com.fanc.wheretoplay.rx.DisposableSubscriber2;
import com.fanc.wheretoplay.rx.FlowableTransformer2;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.view.ItemDecorations;
import com.fanc.wheretoplay.view.TitleBarView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import okhttp3.MultipartBody;

/**
 * @author admin
 * @date 2018/1/16
 */
public class Main2Fragment extends BaseFragment implements XRecyclerView.LoadingListener {

  public static final String TYPE = "2";
  public int currentPage;
  private XRecyclerView recyclerview;
  private Main2Adapter myAdapter;
  private RelativeLayout rrrrrr;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = View.inflate(inflater.getContext(), R.layout.main2fragment, null);
    TitleBarView tbv = view.findViewById(R.id.tbv);
    tbv.setTv_title("支付");
    tbv.setleftIconVisibility();
    recyclerview = view.findViewById(R.id.recyclerview);
    currentPage = 0;

    rrrrrr = view.findViewById(R.id.rrrrrr);
    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerview.setLayoutManager(layoutManager);

    recyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
    recyclerview.getDefaultFootView().setLoadingHint("正在加载......");
    recyclerview.getDefaultFootView().setNoMoreHint("已经是最后一页");

    recyclerview.addItemDecoration(new ItemDecorations(12));

    recyclerview.setLoadingListener(this);

    loadData("onRefresh", currentPage);
    return view;
  }

  private void loadData(final String action, int currentPageValue) {
    MultipartBody.Part requestFileA =
        MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());
    MultipartBody.Part requestFileC =
        MultipartBody.Part.createFormData("page", currentPageValue + "");
    MultipartBody.Part requestFileD = MultipartBody.Part.createFormData("size", "10");
    MultipartBody.Part requestFileB = MultipartBody.Part.createFormData("type", "2");

    Retrofit_RequestUtils.getRequest()
        .bookList(requestFileA, requestFileC, requestFileB, requestFileD)
        .compose(new FlowableTransformer2<BookListModel.ContentBean>())
        .subscribe(
            new DisposableSubscriber2<BookListModel.ContentBean>() {
              @Override
              protected void successful(BookListModel.ContentBean content) {
                if (content.list != null) setData(content, action);
              }

              @Override
              public void failed(String t) {
             //   Toast.makeText(mContext, t, Toast.LENGTH_SHORT).show();
                if ("onRefresh".equals(action)) {
                  recyclerview.refreshComplete();
                } else {
                  recyclerview.loadMoreComplete();
                }
              }
            });
  }

  private void setData(BookListModel.ContentBean contentBean, String action) {

    if (contentBean.list != null) {
      if ("onRefresh".equals(action)) {
        recyclerview.refreshComplete();
        if ("onLoadMore".equals(action) && myAdapter != null) {
          myAdapter.notifyDataSetChanged();
        } else {
            if (contentBean.list.size()==0){
                rrrrrr.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);

            }else {
                rrrrrr.setVisibility(View.GONE);
              recyclerview.setVisibility(View.VISIBLE);
                myAdapter =
                        new Main2Adapter(
                                mContext, (ArrayList<BookListModel.ContentBean.ListBean>) contentBean.list);
                recyclerview.setAdapter(myAdapter);
            }

        }
      } else if ("onLoadMore".equals(action)) {
        recyclerview.loadMoreComplete();
        if (contentBean.list.size() > 0) {
          myAdapter.append((ArrayList<BookListModel.ContentBean.ListBean>) contentBean.list);
        } else {
          recyclerview.setNoMore(true);
        }
      }
    } else {
      recyclerview.setNoMore(true);
    }
  }

  @Override
  public void onRefresh() {
    currentPage = 0;
    loadData("onRefresh", currentPage);
  }

  @Override
  public void onLoadMore() {
    currentPage++;
    loadData("onLoadMore", currentPage);
  }
}
