package com.fanc.wheretoplay.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.CheckCommentsActivity;
import com.fanc.wheretoplay.activity.LargeImageActivity;
import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
//import com.fanc.wheretoplay.activity.ServiceActivity;
import com.fanc.wheretoplay.activity.ShareActivity;
import com.fanc.wheretoplay.adapter.CommentIconAdapter;
import com.fanc.wheretoplay.adapter.MerchantTablayoutAdapter;
import com.fanc.wheretoplay.adapter.ReserveAdapter;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMerchantDetailBinding;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.StoreDetail;
import com.fanc.wheretoplay.datamodel.StoreList;
import com.fanc.wheretoplay.datamodel.SubmitCommentModel;
import com.fanc.wheretoplay.datamodel.Url;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.NaviUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.DrawableCenterLeftTextView;
import com.fanc.wheretoplay.view.MyRecycleView;
import com.fanc.wheretoplay.view.ShearedPopDialog;
import com.fanc.wheretoplay.view.TopMenu;

import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/14.
 */

public class MerchantDetailFragment extends BaseFragment {

    FragmentMerchantDetailBinding detailBinding;

    TopMenu mTmDetail;

    // 商家信息
    TextView mTvMerchantDetailTitle;
    RatingBar mRbMerchantDetailGrade;
    TextView mTvMerchantDetailDiscountSum;
    //    TextView mTvMerchantDetailDiscount;
    TextView mTvMerchantDetailGrade;
    TextView mTvMerchantDetailCommentNo;
    LinearLayout mLlMerchantDetailComment;
    //    RecyclerView mRvMerchantDetailCommentHeadImage;
    // 图片
    LinearLayout mLlMerchantDetailImage;
    ImageView mIvMerchantDetail1;
    ImageView mIvMerchantDetail2;
    ImageView mIvMerchantDetail3;
    ImageView mIvMerchantDetail4;
    ImageView mIvMerchantDetail5;
    ImageView mIvMerchantDetail6;
    // 预订
    TextView mTvReservePromptly;
    // 电话地址
//    LinearLayout mLlMerchantDetailTel;
    DrawableCenterLeftTextView tvTelReserve, tvPay;
    // TextView mTvMerchantDetailTel;
    LinearLayout mLlMerchantDetailAddress;
    TextView mTvMerchantDetailAddress;
    // 房型、酒水、活动、简介
    LinearLayout mLlMerchantDetailRoom;
    LinearLayout mLlMerchantDetailDrinks;
    LinearLayout mLlMerchantDetailAction;
    //    LinearLayout mLlMerchantDetailWaiter;
    private LinearLayout mLlMerchantBrief;



    // 推荐店铺
    MyRecycleView mRvMerchantDetailRecommend;
    List<StoreList.Store> stores;
    ReserveAdapter recommendAdapter;

    // 店铺id
    String mStoreId;
    // 展示图片
    List<ImageView> mImageViews;
    // 评论头像列表
    List<StoreDetail.CommentBean> mCommentIcons;
    CommentIconAdapter iconAdapter;

    // 分享链接
    String shearedUrl;
    /**
     * 是否收藏
     */
    boolean isCollected;
    // 导航数据
    String address, lat, lng;
    // 店铺信息
    StoreDetail.Store mStore;
    //    private Banner mBannerDetail;
    private ArrayList<Object> bannerArrayList;
    private ArrayList<String> imgs;
    private FragmentManager fragmentManager;
    private MerchantTablayoutAdapter adpter;
    private String briefUrl;
    private String discountValue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_merchant_detail, null, false);
        initViews();
        initImageLayout();
        init();
        setListeners();
        return detailBinding.getRoot();
    }

    private void initViews() {
        mTmDetail = detailBinding.tmMerchantDetail;
//        mBannerDetail = detailBinding.bannerDetail;
        mTvMerchantDetailTitle = detailBinding.tvMerchantDetailTitle;
        mRbMerchantDetailGrade = detailBinding.rbMerchant;
        mTvMerchantDetailDiscountSum = detailBinding.tvMerchantDetailDiscountSum;
//        mTvMerchantDetailDiscount = detailBinding.tvMerchantDetailDiscount;
        mTvMerchantDetailGrade = detailBinding.tvMerchantDetailGrade;
        mTvMerchantDetailCommentNo = detailBinding.tvMerchantDetailComment;
        mLlMerchantDetailComment = detailBinding.llMerchantDetailComment;
//        mRvMerchantDetailCommentHeadImage = detailBinding.rvMerchantDetailHeadImage;
        mLlMerchantDetailImage = detailBinding.llMerchantDetailImage;
        mIvMerchantDetail1 = detailBinding.ivMerchantDetail1;
        mIvMerchantDetail2 = detailBinding.ivMerchantDetail2;
        mIvMerchantDetail3 = detailBinding.ivMerchantDetail3;
        mIvMerchantDetail4 = detailBinding.ivMerchantDetail4;
        mIvMerchantDetail5 = detailBinding.ivMerchantDetail5;
        mIvMerchantDetail6 = detailBinding.ivMerchantDetail6;
        mTvReservePromptly = detailBinding.tvReserveOnline;
//        mLlMerchantDetailTel = detailBinding.llMerchantReserveTel;
        tvTelReserve = detailBinding.tvTelReserve;
        //  mTvMerchantDetailTel = detailBinding.tvDetailTel;
        mTvMerchantDetailAddress = detailBinding.tvMerchantReserveAddress;
        mLlMerchantDetailAddress = detailBinding.llMerchantReserveAddress;
        mLlMerchantDetailRoom = detailBinding.llMerchantDetailRoom;
        mLlMerchantDetailDrinks = detailBinding.llMerchantDetailDrinks;
        mLlMerchantDetailAction = detailBinding.llMerchantDetailActive;
//        mLlMerchantDetailWaiter = detailBinding.llMerchantDetailWaiter;
        mLlMerchantBrief = detailBinding.llMerchantBrief;
        mRvMerchantDetailRecommend = detailBinding.rvMerchantDetailRecommend;
        tvPay = detailBinding.tvPay;
//        mRvMerchantDetailRoomPrice = detailBinding.rvMerchantDetailRoomPrice;
    }

    private void init() {
        //banner轮播图
//        imgs = new ArrayList<>();
//        bannerArrayList = new ArrayList<>();
//        mBannerDetail.setImageLoader(new GlideImageLoader());
//        mBannerDetail.setOnBannerListener(this);
//        mBannerDetail.setBannerStyle(BannerConfig.NUM_INDICATOR);

        mTmDetail.setLeftIcon(R.drawable.left);
        mTmDetail.setRightIcon(R.drawable.merchant_detail_sheard);
        mTmDetail.setTitleColor(getResources().getColor(R.color.white));
        mTmDetail.setTitle(R.string.merchant_detail);
        int color = App.getContext().getResources().getColor(R.color.text_red);
        mTmDetail.setBackgroundColor(color);
//        getShearedUrl(this.mStoreId);
        getMerchantDetail();
        getCollected(this.mStoreId);
        // 评论
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRvMerchantDetailCommentHeadImage.setLayoutManager(layoutManager);
//        RecycleViewDivider divider = new RecycleViewDivider(mContext, LinearLayout.VERTICAL, UIUtils.dp2Px(2), UIUtils.getColor(R.color.bg));
//        mRvMerchantDetailCommentHeadImage.addItemDecoration(divider);
//        mCommentIcons = new ArrayList<>();
//        iconAdapter = new CommentIconAdapter(mContext, mCommentIcons);
//        mRvMerchantDetailCommentHeadImage.setAdapter(iconAdapter);

        // 推荐店铺
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMerchantDetailRecommend.setLayoutManager(linearLayoutManager);
        //去掉自定义的recyclerview分割线
//        RecycleViewDivider divider1 = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, UIUtils.dp2Px(1), UIUtils.getColor(R.color.bg));
//        mRvMerchantDetailRecommend.addItemDecoration(divider1);
        stores = new ArrayList<>();
        recommendAdapter = new ReserveAdapter(mContext, stores);
        mRvMerchantDetailRecommend.setAdapter(recommendAdapter);
        RxBus.getDefault().toObservable(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });

    }


    private void setListeners() {
        //商家后台传来的未读消息数量
        UnreadCountChangeListener listener = new UnreadCountChangeListener() {
            @Override
            public void onUnreadCountChange(int count) {
                Log.e("wade", count + "");
            }
        };
        Unicorn.addUnreadCountChangeListener(listener, true);

        mTmDetail.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
//                mContext.overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
            }
        });
        //收藏
        mTmDetail.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShearedPopDialog(mContext)
                        .setCollected(isCollected)
                        .setOnCollectClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                collectStore(mStoreId);
                            }
                        })
                        .setOnShearedClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sheared();
                            }
                        })
                        .show(mTmDetail.getIvRightIcon());
            }
        });

        mTvReservePromptly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.PAGE, Constants.RESERVE_INFO);
                startActivity(intent);
                mContext.overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_out_top_right);
            }
        });

        //查看评论
        mLlMerchantDetailComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, CheckCommentActivity.class);
                Intent intent = new Intent(mContext, CheckCommentsActivity.class);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                startActivity(intent);
                mContext.overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
            }
        });
        tvTelReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + tvTelReserve.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        mLlMerchantDetailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocationUtils.location != null) {
                    NaviUtils.checkMapExist(mContext, LocationUtils.location.getAddrStr(), String.valueOf(LocationUtils.location.getLatitude()),
                            String.valueOf(LocationUtils.location.getLongitude()), address, lat, lng);
                } else {
                    showTextProgress("定位中...");
                    LocationUtils.getLocation(mContext, new LocationUtils.Callback() {
                        @Override
                        public void onReceiveLocation(BDLocation bdLocation) {
                            closeProgress();
                            NaviUtils.checkMapExist(mContext, bdLocation.getAddrStr(), String.valueOf(bdLocation.getLatitude()),
                                    String.valueOf(bdLocation.getLongitude()), address, lat, lng);
                        }
                    });
                }
            }
        });
        //房型
        mLlMerchantDetailRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.PAGE, Constants.ROOM);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, mTvMerchantDetailTitle.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, mTvMerchantDetailDiscountSum.getText().toString());
                intent.putExtra(Constants.ADDRESS, mTvMerchantDetailAddress.getText().toString());
                startActivity(intent);
            }
        });
        //酒水
        mLlMerchantDetailDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.PAGE, Constants.DRINKS);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, mTvMerchantDetailTitle.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, mTvMerchantDetailDiscountSum.getText().toString());
                intent.putExtra(Constants.ADDRESS, mTvMerchantDetailAddress.getText().toString());
                startActivity(intent);
            }
        });

        //支付
        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PayBillActivity.class);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra("storeName", mTvMerchantDetailTitle.getText().toString());
                intent.putExtra("address", mTvMerchantDetailAddress.getText().toString());
                intent.putExtra("discount", discountValue);
                intent.putExtra(Constants.PAGE, "商家详情支付");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });
        //房态界面跳转
        mLlMerchantDetailAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.PAGE, Constants.HOUSENEWS);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, mTvMerchantDetailTitle.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, mTvMerchantDetailDiscountSum.getText().toString());
                intent.putExtra(Constants.ADDRESS, mTvMerchantDetailAddress.getText().toString());
                intent.putExtra("open", true);
                startActivity(intent);
            }
        });
        //简介
        mLlMerchantBrief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReuseActivity.class);
                intent.putExtra(Constants.PAGE, Constants.BRIEF);
                intent.putExtra(Constants.DATA, briefUrl);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, mTvMerchantDetailTitle.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, mTvMerchantDetailDiscountSum.getText().toString());
                intent.putExtra(Constants.ADDRESS, mTvMerchantDetailAddress.getText().toString());
                startActivity(intent);
            }
        });
        //去掉服务页跳转
//        mLlMerchantDetailWaiter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, DetailActivity.class);
//                intent.putExtra(Constants.PAGE, Constants.WAITER_INFO);
//                intent.putExtra(Constants.STORE_ID, mStoreId);
//                startActivity(intent);
//            }
//        });
    }

    private void initImageLayout() {
        mImageViews = new ArrayList<>();
        mImageViews.add(mIvMerchantDetail1);
        mImageViews.add(mIvMerchantDetail2);
        mImageViews.add(mIvMerchantDetail3);
        mImageViews.add(mIvMerchantDetail4);
        mImageViews.add(mIvMerchantDetail5);
        mImageViews.add(mIvMerchantDetail6);

        int smallImageWidth = (UIUtils.getScreenWidth() - UIUtils.dp2Px(10 + 4 + 4 + 10)) / 4;
        for (int i = 0; i < mImageViews.size(); i++) {
//            if (i != 0) {
            mImageViews.get(i).setScaleType(ImageView.ScaleType.FIT_XY);
//            }
        }
        // 图片区域总高度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLlMerchantDetailImage.getLayoutParams();
        lp.height = smallImageWidth * 3 + UIUtils.dp2Px(5 + 5);
        mLlMerchantDetailImage.setLayoutParams(lp);
        // 左边大图片
        ImageView imageView0 = mImageViews.get(0);
        LinearLayout.LayoutParams layoutParams0 = (LinearLayout.LayoutParams) imageView0.getLayoutParams();
        layoutParams0.width = UIUtils.getScreenWidth();
        imageView0.setLayoutParams(layoutParams0);

        // 右边上面图片
        ImageView imageView = mImageViews.get(1);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height = smallImageWidth;
        imageView.setLayoutParams(layoutParams);
    }

    public MerchantDetailFragment setStoreId(String mStoreId) {
        this.mStoreId = mStoreId;
        return this;
    }

    private void getCollected(String storeId) {
        OkHttpUtils.post()
                .url(Network.User.USER_CONFIRM_COLLECT)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.STORE_ID, storeId)
                .build()
                .execute(new DCallback<IsOk>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(IsOk response) {
                        if (isSuccess(response)) {
                            if (response.isResult()) {
                                isCollected = true;
                            }
                        }
                    }
                });
    }

    private void getShearedUrl(String storeId) {
        OkHttpUtils.post()
                .url(Network.User.USER_SHARE_STORE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.STORE_ID, storeId)
                .build()
                .execute(new DCallback<Url>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Url response) {
                        if (isSuccess(response)) {
                            shearedUrl = Network.BASE + "/" + response.getUrl();
                        }
                    }
                });
    }

    private void getMerchantDetail() {
        if (LocationUtils.location != null) {
            getMerchantDetail(this.mStoreId, LocationUtils.location.getLatitude(), LocationUtils.location.getLongitude());
        } else {
            showTextProgress("定位中...");
            LocationUtils.getLocation(mContext, new LocationUtils.Callback() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    closeProgress();
                    getMerchantDetail(mStoreId, bdLocation.getLatitude(), bdLocation.getLongitude());
                }
            });
        }
    }

    /**
     * 获取商铺详情
     *
     * @param id
     */
    private void getMerchantDetail(String id, double lat, double lng) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.PUBLIC_STORE_DETAIL)
                .addParams(Network.Param.ID, id)
                .addParams(Network.Param.LAT, String.valueOf(lat))
                .addParams(Network.Param.LNG, String.valueOf(lng))
                .build()
                .execute(new DCallback<StoreDetail>() {
                    @Override
                    public void onError(Call call, Exception e) {

                        connectError();
                    }

                    @Override
                    public void onResponse(StoreDetail response) {

                        if (isSuccess(response)) {

                            if (response.getStore() != null) {
                                mStore = response.getStore();
                                showStoreDetail(mStore);
                                shearedUrl = Network.BASE + "/" + response.getStore().getUrl();
                            }
                        }
                    }
                });
    }

    /**
     * 显示商铺详情
     *
     * @param store
     */
    private void showStoreDetail(final StoreDetail.Store store) {
        discountValue = store.getDiscount();
        mTvMerchantDetailTitle.setText(store.getName());
        if (store.getDiscount().length() > 0) {
            SpannableString text = new SpannableString(store.getDiscount() + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvMerchantDetailDiscountSum.setText(text, TextView.BufferType.SPANNABLE);
            mTvMerchantDetailDiscountSum.setVisibility(View.VISIBLE);
        } else {
            mTvMerchantDetailDiscountSum.setVisibility(View.GONE);
        }

//        mTvMerchantDetailDiscountSum.setText(store.getDiscount());
//        if (store.getDiscount().isEmpty()) {
//            mTvMerchantDetailDiscount.setVisibility(View.GONE);
//        }
        if (!store.getLevel().isEmpty()) {
            mRbMerchantDetailGrade.setRating(Float.parseFloat(store.getLevel()));
        }
        mTvMerchantDetailGrade.setText(String.valueOf(store.getNumber()));
//        mTvMerchantDetailContent.setText(store.getRemark());
        // 评论
        if (store.getComment() != null) {
            showComment(store.getComment());
        }
        // 图片
        if (store.getPicture() != null) {
            showPicture(store.getPicture());
        }
        //  tvTelReserve.setText(store.getPhone());
        // 地址 距离
        String d = "";
        if (store.getDistance() != null && !TextUtils.isEmpty(store.getDistance()) && !TextUtils.equals("-1", store.getDistance())) {

            double distance = Double.parseDouble(store.getDistance());
            if (distance < 500) {
                d = "<500m";
            } else
//                if (distance < 100000)
            {
                if (distance < 1000) {
                    d = store.getDistance() + "m";
                } else {
                    // 0.几的时候，格式化会把小数点前的0去掉，原因未知
                    DecimalFormat df = new DecimalFormat("#.0");
                    d = df.format(distance / 1000) + "km";
                }
            }
        }
        mTvMerchantDetailAddress.setText(store.getAddress() + "    " + d);

        briefUrl = Network.BASE + "/" + store.getRemark();
        //把之前布局中的webview放到briefFragment中去
//        mWvDetail.loadUrl(Network.BASE + "/" + store.getRemark());
//        mWvDetail.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Log.d("Aaaaa", "onPageFinished: url = " + url);
//            }
//        });
//        new MerchantBriefFragment().setLoadWebviewListener(new MerchantBriefFragment.LoadWebview() {
//            @Override
//            public void loadWebview(WebView webView) {
//                webView.loadUrl(Network.BASE + "/" + store.getRemark());
//                webView.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
//                        Log.d("Jordan1", "onPageFinished: url = " + store.getRemark());
//                    }
//                });
//            }
//        });

        if (store.getList() != null) {
            showRecommend(store.getList());
        }
        // 导航
        address = store.getAddress();
        lat = store.getLat();
        lng = store.getLng();
    }

    /**
     * 推荐店铺
     *
     * @param list
     */
    private void showRecommend(List<StoreList.Store> list) {
        stores.addAll(list);
        recommendAdapter.notifyDataSetChanged();
    }

    /**
     * 评论
     */
    private void showComment(List<StoreDetail.CommentBean> commentIcons) {
//        mCommentIcons.addAll(commentIcons);
//        iconAdapter.notifyDataSetChanged();
    }

    /**
     * 图片
     *
     * @param pictures
     */
    private void showPicture(final ArrayList<StoreDetail.Picture> pictures) {
        /*旧版图片展示*/
        int defaultImage = 0;
        final ArrayList<String> imgs = new ArrayList<>();
        for (int i = 0; i < pictures.size(); i++) {
            if (i >= 6) {
                return;
            }
            if (i < 2) {
                defaultImage = R.drawable.default_rect;
            } else {
                defaultImage = R.drawable.default_square;
            }
            imgs.add(pictures.get(i).getPicture_path());
            Glide.with(mContext).load(Network.IMAGE + pictures.get(i).getPicture_path()).placeholder(defaultImage).into(mImageViews.get(i));
            final int finalI = i;
            mImageViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, LargeImageActivity.class);
                    intent.putExtra(Constants.URL, imgs);
                    intent.putExtra(Constants.POSITION, finalI);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 收藏商铺
     *
     * @param storeId
     */
    private void collectStore(String storeId) {


        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("token", new SPUtils(mContext).getUser().getToken());

        MultipartBody.Part requestFileC =
                MultipartBody.Part.createFormData("store_id", storeId);

     Subscription subscription =  Retrofit_RequestUtils.getRequest().collect(requestFileA, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubmitCommentModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        connectError();
                    }

                    @Override
                    public void onNext(SubmitCommentModel submitCommentModel) {
                        if (submitCommentModel.code.equals("0")) {
                            isCollected = true;
                            ToastUtils.makePicTextShortToast(mContext, "收藏成功");
                        }
                    }
                });

        compositeSubscription.add(subscription);


    }

    /**
     * 分享
     */
    private void sheared() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            String[] mPermissionList = new String[]{
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.CALL_PHONE,
//                    Manifest.permission.READ_PHONE_STATE
//            };
//            for (String permission : mPermissionList) {
//                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(mContext, new String[]{permission}, Constants.REQUEST_PERMISSION_CODE);
//                }
//            }


        Intent intent = new Intent(mContext, ShareActivity.class);
        intent.putExtra("title", "乐互网");
        intent.putExtra("secondtitle", "商家详情");

        intent.putExtra("shearedUrl", shearedUrl);

        startActivity(intent);



  /*      new ShareAction(mContext)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        // 分享内容
                        UMWeb web = new UMWeb(shearedUrl);
                        web.setTitle("偷着乐娱乐平台");//标题
                        web.setThumb(new UMImage(mContext, R.mipmap.ic_launcher));  //缩略图
                        web.setDescription("商家详情");//描述
                        new ShareAction(mContext)
                                .setPlatform(share_media)
                                .setCallback(new UMShareListener())
                                .withMedia(web)
                                .share();
                    }
                })
                .open();*/
    }

}
