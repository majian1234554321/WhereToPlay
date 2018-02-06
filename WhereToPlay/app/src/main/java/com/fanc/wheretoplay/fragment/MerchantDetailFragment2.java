package com.fanc.wheretoplay.fragment;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.CheckCommentsActivity;
import com.fanc.wheretoplay.activity.LargeImageActivity;

import com.fanc.wheretoplay.activity.PayBillActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.activity.ShareActivity;

import com.fanc.wheretoplay.activity.SignInActivity;
import com.fanc.wheretoplay.adapter.MerchAdapter;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseFragment;

import com.fanc.wheretoplay.datamodel.AccessOrderIdModel;
import com.fanc.wheretoplay.datamodel.DataValue;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.MerchantDetailModel;

import com.fanc.wheretoplay.datamodel.SubmitCommentModel;

import com.fanc.wheretoplay.network.Network;

import com.fanc.wheretoplay.rx.DisposableSubscriber2;
import com.fanc.wheretoplay.rx.FlowableTransformer2;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.NaviUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;

import com.fanc.wheretoplay.view.MyRecycleView;
import com.fanc.wheretoplay.view.ShearedPopDialog;
import com.fanc.wheretoplay.view.TopMenu;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;

/** Created by Administrator on 2017/6/14. */
public class MerchantDetailFragment2 extends BaseFragment implements View.OnClickListener {

    TopMenu mTmDetail;

    MerchAdapter recommendAdapter;

    // 店铺id
    String mStoreId;
    // 展示图片
    List<ImageView> mImageViews;

    // 分享链接
    String shearedUrl;
    /** 是否收藏 */
    boolean isCollected;
    // 导航数据
    String address, lat, lng;

    private String briefUrl;
    private String discountValue;
    private MyRecycleView recycle;
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6;

    private int[] ids = {
        R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv_pay, R.id.tv_yd, R.id.tv_tel
    };
    private TextView view1, tv_storeName, tv_discount, tv_address, tv_pingjiashuliang;
    private RatingBar rb_merchant;
    private LinearLayout ll_merchant_detail_image;
    private List<MerchantDetailModel.ContentBean.StoreBean.ListBean> stores;
    private TextView tv_pay;
    public String start = "1";
    private FrameLayout fragment;
    private SPUtils spUtils;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = View.inflate(inflater.getContext(), R.layout.fragment_merchant_detail2, null);

        recycle = view.findViewById(R.id.recycle);
        mTmDetail = view.findViewById(R.id.mTmDetail);

        iv1 = view.findViewById(R.id.iv1);
        iv2 = view.findViewById(R.id.iv2);
        iv3 = view.findViewById(R.id.iv3);
        iv4 = view.findViewById(R.id.iv4);
        iv5 = view.findViewById(R.id.iv5);
        iv6 = view.findViewById(R.id.iv6);
        spUtils = new SPUtils(mContext);
        fragment = view.findViewById(R.id.framelayout);

        if ("STORE".equals(spUtils.getString("STORE", ""))) {
            fragment.setVisibility(View.GONE);
        } else {
            fragment.setBackgroundResource(R.drawable.store1);
            start = "2";
        }

        fragment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (start) {
                            case "1":
                                break;
                            case "2":
                                fragment.setBackgroundResource(R.drawable.store1);
                                start = "3";
                                break;
                            case "3":
                                fragment.setBackgroundResource(R.drawable.store3);
                                start = "GONE";
                                break;
                            case "GONE":
                                spUtils.putString("STORE","STORE");
                                fragment.setVisibility(View.GONE);
                                break;
                        }
                    }
                });

        rb_merchant = view.findViewById(R.id.rb_merchant);
        tv_storeName = view.findViewById(R.id.tv_storeName);
        tv_discount = view.findViewById(R.id.tv_discount);
        tv_address = view.findViewById(R.id.tv_address);
        tv_pingjiashuliang = view.findViewById(R.id.tv_pingjiashuliang);

        LinearLayout ll_pinglun = view.findViewById(R.id.ll_pinglun);
        LinearLayout ll_address = view.findViewById(R.id.ll_address);
        ll_merchant_detail_image = view.findViewById(R.id.ll_merchant_detail_image);

        ll_address.setOnClickListener(this);
        ll_pinglun.setOnClickListener(this);

        for (int i = 0; i < ids.length; i++) {
            view1 = view.findViewById(ids[i]);
            view1.setOnClickListener(this);
        }

        tv_pay = view.findViewById(R.id.tv_pay);

        initImageLayout();
        init();
        setListeners();

        return view;
    }

    private void init() {

        mTmDetail.setLeftIcon(R.drawable.left);
        mTmDetail.setRightIcon(R.drawable.merchant_detail_sheard);
        mTmDetail.setTitleColor(getResources().getColor(R.color.white));
        mTmDetail.setTitle(R.string.merchant_detail);
        int color = App.getContext().getResources().getColor(R.color.text_red);
        mTmDetail.setBackgroundColor(color);
        //        getShearedUrl(this.mStoreId);
        getMerchantDetail();
        getCollected(this.mStoreId);

        // 推荐店铺
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(linearLayoutManager);

        stores = new ArrayList<>();
        recommendAdapter = new MerchAdapter(mContext, stores);
        recycle.setAdapter(recommendAdapter);
    }

    private void setListeners() {
        // 商家后台传来的未读消息数量
        UnreadCountChangeListener listener =
                new UnreadCountChangeListener() {
                    @Override
                    public void onUnreadCountChange(int count) {
                        Log.e("wade", count + "");
                    }
                };
        Unicorn.addUnreadCountChangeListener(listener, true);

        mTmDetail.setLeftIconOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.finish();
                        //
                        // mContext.overridePendingTransition(R.anim.anim_in_top_right,
                        // R.anim.anim_close_top);
                    }
                });

        // 收藏
        mTmDetail.setRightIconOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ShearedPopDialog(mContext)
                                .setCollected(isCollected)
                                .setOnCollectClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                collectStore(mStoreId);
                                            }
                                        })
                                .setOnShearedClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                sheared();
                                            }
                                        })
                                .show(mTmDetail.getIvRightIcon());
                    }
                });
    }

    private void initImageLayout() {
        mImageViews = new ArrayList<>();
        mImageViews.add(iv1);
        mImageViews.add(iv2);
        mImageViews.add(iv3);
        mImageViews.add(iv4);
        mImageViews.add(iv5);
        mImageViews.add(iv6);

        int smallImageWidth = (UIUtils.getScreenWidth() - UIUtils.dp2Px(10 + 4 + 4 + 10)) / 4;
        for (int i = 0; i < mImageViews.size(); i++) {
            //            if (i != 0) {
            mImageViews.get(i).setScaleType(ImageView.ScaleType.FIT_XY);
            //            }
        }
        // 图片区域总高度

        LinearLayout.LayoutParams lp =
                (LinearLayout.LayoutParams) ll_merchant_detail_image.getLayoutParams();
        lp.height = smallImageWidth * 3 + UIUtils.dp2Px(5 + 5);
        ll_merchant_detail_image.setLayoutParams(lp);
        // 左边大图片
        ImageView imageView0 = mImageViews.get(0);
        LinearLayout.LayoutParams layoutParams0 =
                (LinearLayout.LayoutParams) imageView0.getLayoutParams();
        layoutParams0.width = UIUtils.getScreenWidth();
        imageView0.setLayoutParams(layoutParams0);

        // 右边上面图片
        ImageView imageView = mImageViews.get(1);
        LinearLayout.LayoutParams layoutParams =
                (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height = smallImageWidth;
        imageView.setLayoutParams(layoutParams);
    }

    public String type;

    public MerchantDetailFragment2 setValue(String mStoreId, String type) {
        this.mStoreId = mStoreId;
        this.type = type;

        return this;
    }

    private void getCollected(String storeId) {
        OkHttpUtils.post()
                .url(Network.User.USER_CONFIRM_COLLECT)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addParams(Network.Param.STORE_ID, storeId)
                .build()
                .execute(
                        new DCallback<IsOk>() {
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

    private void getMerchantDetail() {
        if (LocationUtils.location != null) {
            getMerchantDetail(this.mStoreId, DataValue.latitude, DataValue.longitude);
        } else {
            showTextProgress("定位中...");
            LocationUtils.getLocation(
                    mContext,
                    new LocationUtils.Callback() {
                        @Override
                        public void onReceiveLocation(BDLocation bdLocation) {
                            closeProgress();
                            getMerchantDetail(mStoreId, DataValue.latitude, DataValue.longitude);
                        }
                    });
        }
    }

    /**
     * 获取商铺详情
     *
     * @param id
     */
    private String phonevalue;

    private void getMerchantDetail(String id, String lat, String lng) {
        showProgress();
        MultipartBody.Part requestFileA = MultipartBody.Part.createFormData("id", id);
        MultipartBody.Part requestFileb = MultipartBody.Part.createFormData(Network.Param.LAT, lat);
        MultipartBody.Part requestFilec = MultipartBody.Part.createFormData(Network.Param.LNG, lng);
        List<MultipartBody.Part> list = new ArrayList<>();
        list.add(requestFileA);
        list.add(requestFileb);
        list.add(requestFilec);

        Retrofit_RequestUtils.getRequest()
                .storeDetail(list)
                .compose(new FlowableTransformer2<MerchantDetailModel.ContentBean>())
                .subscribe(
                        new DisposableSubscriber2<MerchantDetailModel.ContentBean>() {
                            @Override
                            protected void successful(MerchantDetailModel.ContentBean content) {
                                shearedUrl = Network.BASE + "/" + content.store.url;
                                showStoreDetail(content.store);
                                phonevalue = content.store.phone;

                                if (!"2".equals(type) && type != null) {
                                    if (content.store != null) {
                                        getChildFragmentManager()
                                                .beginTransaction()
                                                .replace(
                                                        R.id.fragment,
                                                        new ExtendFragment(
                                                                content.store.packageX,
                                                                type,
                                                                content.store.store_id,
                                                                discountValue,
                                                                content.store.name,
                                                                address,
                                                                phonevalue))
                                                .commit();
                                    }
                                }
                            }

                            @Override
                            public void failed(String t) {
                                Toast.makeText(mContext, t, Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    /**
     * 显示商铺详情
     *
     * @param store
     */
    private void showStoreDetail(final MerchantDetailModel.ContentBean.StoreBean store) {

        if ("0".equals(store.is_immediate_pay)) {
            tv_pay.setVisibility(View.GONE);
        } else {
            tv_pay.setVisibility(View.VISIBLE);
        }

        discountValue = store.discount;
        tv_storeName.setText(store.name);
        if (store.discount.length() > 0) {
            SpannableString text = new SpannableString(store.discount + "折");
            text.setSpan(
                    new TextAppearanceSpan(mContext, R.style.reserve_dicount),
                    0,
                    text.length() - 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(
                    new TextAppearanceSpan(mContext, R.style.reserve_dicount_small),
                    text.length() - 1,
                    text.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_discount.setText(text, TextView.BufferType.SPANNABLE);
            tv_discount.setVisibility(View.VISIBLE);
        } else {
            tv_discount.setVisibility(View.GONE);
        }

        if (!store.level.isEmpty()) {
            rb_merchant.setRating(Float.parseFloat(store.level));
        }

        tv_pingjiashuliang.setText(
                ("null".equals(store.number) || TextUtils.isEmpty(store.number)
                        ? "0"
                        : String.valueOf(store.number)));

        // 图片
        if (store.picture != null) {
            showPicture(store.picture);
        }

        String d = "";
        if (store.distance != null
                && !TextUtils.isEmpty(store.distance)
                && !TextUtils.equals("-1", store.distance)) {

            double distance = Double.parseDouble(store.distance);
            if (distance < 500) {
                d = "<500m";
            } else
            //                if (distance < 100000)
            {
                if (distance < 1000) {
                    d = store.distance + "m";
                } else {
                    // 0.几的时候，格式化会把小数点前的0去掉，原因未知
                    DecimalFormat df = new DecimalFormat("#.0");
                    d = df.format(distance / 1000) + "km";
                }
            }
        }
        tv_address.setText(store.address + "    " + d);

        briefUrl = Network.BASE + "/" + store.remark;

        if (store.list != null) {
            showRecommend(store.list);
        }
        // 导航
        address = store.address;
        lat = store.lat;
        lng = store.lng;
    }

    /**
     * 推荐店铺
     *
     * @param list
     */
    private void showRecommend(List<MerchantDetailModel.ContentBean.StoreBean.ListBean> list) {
        stores.addAll(list);
        recommendAdapter.notifyDataSetChanged();
    }

    /**
     * 图片
     *
     * @param pictures
     */
    private void showPicture(List<MerchantDetailModel.ContentBean.StoreBean.PictureBean> pictures) {
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
            imgs.add(pictures.get(i).picture_path);
            if (Util.isOnMainThread()) {
                Glide.with(App.getContext())
                        .load(Network.IMAGE + pictures.get(i).picture_path)
                        .placeholder(defaultImage)
                        .into(mImageViews.get(i));
            }
            final int finalI = i;
            mImageViews
                    .get(i)
                    .setOnClickListener(
                            new View.OnClickListener() {
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
                MultipartBody.Part.createFormData(
                        "token", new SPUtils(mContext).getUser().getToken());

        MultipartBody.Part requestFileC = MultipartBody.Part.createFormData("store_id", storeId);

        Retrofit_RequestUtils.getRequest()
                .collect(requestFileA, requestFileC)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<SubmitCommentModel>() {

                            @Override
                            public void onError(Throwable e) {
                                connectError();
                            }

                            @Override
                            public void onComplete() {}

                            @Override
                            public void onSubscribe(Disposable disposable) {}

                            @Override
                            public void onNext(SubmitCommentModel submitCommentModel) {
                                if ("0".equals(submitCommentModel.code)) {
                                    isCollected = true;
                                    ToastUtils.makePicTextShortToast(mContext, "收藏成功");
                                }
                            }
                        });
    }

    /** 分享 */
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
        //                if (ContextCompat.checkSelfPermission(mContext, permission) !=
        // PackageManager.PERMISSION_GRANTED) {
        //                    ActivityCompat.requestPermissions(mContext, new String[]{permission},
        // Constants.REQUEST_PERMISSION_CODE);
        //                }
        //            }

        Intent intent = new Intent(mContext, ShareActivity.class);
        intent.putExtra("title", "乐互网");
        intent.putExtra("secondtitle", "商家详情");

        intent.putExtra("shearedUrl", shearedUrl);

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        switch (view.getId()) {
            case R.id.tv1:
                intent.putExtra(Constants.PAGE, Constants.BRIEF);
                intent.putExtra(Constants.DATA, briefUrl);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, tv_storeName.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, tv_discount.getText().toString());
                intent.putExtra(Constants.ADDRESS, tv_address.getText().toString());
                startActivity(intent);
                break;
            case R.id.tv2:
                intent.putExtra(Constants.PAGE, Constants.ROOM);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, tv_storeName.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, tv_discount.getText().toString());
                intent.putExtra(Constants.ADDRESS, tv_address.getText().toString());
                startActivity(intent);
                break;
            case R.id.tv3:
                intent.putExtra(Constants.PAGE, Constants.DRINKS);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, tv_storeName.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, tv_discount.getText().toString());
                intent.putExtra(Constants.ADDRESS, tv_address.getText().toString());
                startActivity(intent);
                break;
            case R.id.tv4:
                intent.putExtra(Constants.PAGE, Constants.HOUSENEWS);
                intent.putExtra(Constants.STORE_ID, mStoreId);
                intent.putExtra(Constants.STORE_NAME, tv_storeName.getText().toString());
                intent.putExtra(Constants.DISCOUNT_COUPON, tv_discount.getText().toString());
                intent.putExtra(Constants.ADDRESS, tv_address.getText().toString());
                intent.putExtra("open", true);
                startActivity(intent);
                break;
            case R.id.tv_yd:
                if (isLogin()) {
                    intent.putExtra(Constants.STORE_ID, mStoreId);
                    intent.putExtra(Constants.PAGE, Constants.RESERVE_INFO);
                    startActivity(intent);
                    mContext.overridePendingTransition(
                            R.anim.anim_enter_bottom, R.anim.anim_out_top_right);
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(mContext, SignInActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent2);
                }
                break;

            case R.id.tv_pay:
                if (isLogin()) {
                    Intent intent1 = new Intent(mContext, PayBillActivity.class);
                    intent1.putExtra(Constants.STORE_ID, mStoreId);
                    intent1.putExtra("storeName", tv_storeName.getText().toString());
                    intent1.putExtra("address", tv_address.getText().toString());
                    intent1.putExtra("discount", discountValue);
                    intent1.putExtra(Constants.PAGE, "商家详情支付");
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent1);
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tv_tel:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                Uri uri =
                        Uri.parse(
                                "tel:"
                                        + (TextUtils.isEmpty(phonevalue)
                                                ? "4000051179"
                                                : phonevalue));
                intent2.setData(uri);
                startActivity(intent2);
                break;

            case R.id.ll_pinglun:
                Intent intent3 = new Intent(mContext, CheckCommentsActivity.class);
                intent3.putExtra(Constants.STORE_ID, mStoreId);
                startActivity(intent3);
                mContext.overridePendingTransition(R.anim.anim_in_top_right, R.anim.anim_close_top);
                break;

            case R.id.ll_address:
                if (LocationUtils.location != null) {
                    NaviUtils.checkMapExist(
                            mContext,
                            LocationUtils.location.getAddrStr(),
                            String.valueOf(LocationUtils.location.getLatitude()),
                            String.valueOf(LocationUtils.location.getLongitude()),
                            address,
                            lat,
                            lng);
                } else {
                    showTextProgress("定位中...");
                    LocationUtils.getLocation(
                            mContext,
                            new LocationUtils.Callback() {
                                @Override
                                public void onReceiveLocation(BDLocation bdLocation) {
                                    closeProgress();
                                    NaviUtils.checkMapExist(
                                            mContext,
                                            bdLocation.getAddrStr(),
                                            String.valueOf(bdLocation.getLatitude()),
                                            String.valueOf(bdLocation.getLongitude()),
                                            address,
                                            lat,
                                            lng);
                                }
                            });
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Util.isOnMainThread()) {
            Glide.with(this).pauseRequests();
        }
    }
}
