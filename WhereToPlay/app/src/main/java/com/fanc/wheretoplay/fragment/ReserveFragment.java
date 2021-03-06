package com.fanc.wheretoplay.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.baidu.location.BDLocation;
import com.fanc.wheretoplay.MainActivity;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.activity.AlterCityActivity;
import com.fanc.wheretoplay.activity.ReuseActivity;
import com.fanc.wheretoplay.adapter.FilterPopChildAdapter;
import com.fanc.wheretoplay.adapter.FilterPopDialogAdapter;
import com.fanc.wheretoplay.adapter.FilterPopStoreTypeAdapter;
import com.fanc.wheretoplay.adapter.ReserveAdapter;
import com.fanc.wheretoplay.base.App;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.callback.IOnFocusListener;
import com.fanc.wheretoplay.databinding.FragmentReserveBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.datamodel.DataValue;
import com.fanc.wheretoplay.datamodel.Filter;
import com.fanc.wheretoplay.datamodel.IsOk;

import com.fanc.wheretoplay.datamodel.StoreList;
import com.fanc.wheretoplay.image.GlideImageLoader;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.LocationUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.FilterPopupDialog;
import com.fanc.wheretoplay.view.MyRecycleView;
import com.fanc.wheretoplay.view.MyScrollView;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;

/**
 * @author Administrator
 * @date 2017/6/12
 */

public class ReserveFragment extends BaseFragment implements IOnFocusListener, LocationUtils.Callback {
    FragmentReserveBinding reserveBinding;

    RelativeLayout mRlTopMenu;
    //    ImageView mIvReserveSideslip;
    MyScrollView mSvReserve;
    Banner mBanner;
    TextView mTvReserveCity;
    LinearLayout mLlReserveFilter;
    LinearLayout mLlReserveArea;
    TextView mTvReserveArea;
    ImageView mIvReserveArea;
    LinearLayout mLlReserveFilterReal;
    TextView mTvReserveFilter;
    ImageView mIvReserveFilter;
    //    LinearLayout mLlReserveCategory;
//    TextView mTvReserveCategory;
//    ImageView mIvReserveCategory;
    MyRecycleView mRvReserve;
    View mVTopMenuBg;
    //    LinearLayout mLlSuspend;
    LinearLayout mLlReserveAreaSuspend;
    TextView mTvReserveAreaSuspend;
    ImageView mIvReserveAreaSuspend;
    LinearLayout mLlReserveFilterRealSuspend;
    TextView mTvReserveFilterSuspend;
    ImageView mIvReserveFilterSuspend;


    // 悬停栏在scrollView中的位置
    int mLlSuspendTop;
    int mLlSuspendBottom;


    Receiver receiver;

    CityResource.City city;
    //筛选区域
    ArrayList areas;
    // 筛选
    List<List<Filter.FilterBean>> filters;
    public List<Filter.FilterBean> filterStoreType;
    List<Filter.FilterBean> filterBeans;
    List<String> filterZH;
    List<String> filterEN;
    // 筛选分类
    List<String> conditions;
    // 页码。数量
    int page, count = 9, size = count;
    boolean isFirst = true;
    // 轮播图
    List<String> mBannerIamges;
    // 商铺
    List mStores;
    ReserveAdapter mReserveAdapter;
    // 区域筛选
    FilterPopupDialog filterArea;
    FilterPopDialogAdapter filterAreaAdapter;
    // 筛选
    FilterPopupDialog filter;
    FilterPopStoreTypeAdapter filterStoreTypeAdapter;
    FilterPopDialogAdapter filterAdapter;
    FilterPopChildAdapter filterChildAdapter;
    // 分类筛选
//    FilterPopupDialog filterCategory;
//    FilterPopDialogAdapter filterCategoryAdapter;

    String areaId;// 区域id
    public String storeType = "2";// 筛选店铺类型,默认为 "2" 表示商务KTV
    String filterType;// 筛选
    String value;// 筛选值，id
    String category;// 分类筛选

    MainActivity.MyOnTouchListener onTouchListener;
    //    private LinearLayout mEntertainment;
    private LinearLayout mFilterSuspend;
    private TextView mTvReserveTitle;
    //娱乐分类
    private LinearLayout mLlCommercialKtv;
    private LinearLayout mLlBar;
    private LinearLayout mLlVolumeSales;
    private ImageView mIvCommericalKtv;
    private ImageView mIvVolumeSales;
    private ImageView mIvReserveBar;
    private TextView mTvCommercialKtv;
    private TextView mTvVolumeSales;
    private TextView mTvReserveBar;
    //定位信息
    private BDLocation bdLocation;
    /**
     * 选中的娱乐分栏图标
     */
    int selectedId;

    public String type = "1";

    private RotateAnimation animation_up;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reserveBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reserve, container, false);
        initViews();
        init();
        setListener();
        return reserveBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getResult4SetPayPassword();
        // 上传极光id
        uploadRegistrationID();

    }

    private void initViews() {
        mRlTopMenu = reserveBinding.rlTopMenu;
//        mIvReserveSideslip = reserveBinding.ivSideslipMenu;
        mVTopMenuBg = reserveBinding.vReserveTopMenuBg;
        mPtrl = reserveBinding.ptrlReserve;
        mSvReserve = reserveBinding.svReserve;
        mBanner = reserveBinding.bannerReserve;
        mTvReserveCity = reserveBinding.tvReserveCity;
//        mEntertainment = reserveBinding.llReserveEntertainment;
        mFilterSuspend = reserveBinding.llReserveFilterSuspend;
        mLlReserveFilter = reserveBinding.llReserveFilter;
        mTvReserveArea = reserveBinding.tvReserveArea;
        mIvReserveArea = reserveBinding.ivReserveArea;
        mTvReserveFilter = reserveBinding.tvReserveFilter;
        mIvReserveFilter = reserveBinding.ivReserveFilter;
        mTvReserveTitle = reserveBinding.tvReserveTitle;
//        mTvReserveCategory = reserveBinding.tvReserveCategory;
//        mIvReserveCategory = reserveBinding.ivReserveCategory;
        mRvReserve = reserveBinding.rvReserve;
        mTvReserveAreaSuspend = reserveBinding.tvReserveAreaSuspend;
        mIvReserveAreaSuspend = reserveBinding.ivReserveAreaSuspend;
        mTvReserveFilterSuspend = reserveBinding.tvReserveFilterSuspend;
        mIvReserveFilterSuspend = reserveBinding.ivReserveFilterSuspend;
//        mTvReserveCategorySuspend = reserveBinding.tvReserveCategorySuspend;
//        mIvReserveCategorySuspend = reserveBinding.ivReserveCategorySuspend;
        mLlReserveArea = reserveBinding.llReserveArea;
        mLlReserveAreaSuspend = reserveBinding.llReserveAreaSuspend;

        mLlReserveFilterReal = reserveBinding.llReserveFilterReal;
        mLlReserveFilterRealSuspend = reserveBinding.llReserveFilterRealSuspend;
//        mLlReserveCategory = reserveBinding.llReserveCategory;
//        mLlReserveCategorySuspend = reserveBinding.llReserveCategorySuspend;
        //娱乐分类
        mLlCommercialKtv = reserveBinding.llReserveCommercialKtv;
        mLlBar = reserveBinding.llReserveBar;
        mLlVolumeSales = reserveBinding.llReserveVolumeSales;
        mIvCommericalKtv = reserveBinding.ivReserveCommercialKtv;
        mIvVolumeSales = reserveBinding.ivReserveVolumeSales;
        mIvReserveBar = reserveBinding.ivReserveReserveBar;
        mTvCommercialKtv = reserveBinding.tvReserveCommercialKtv;
        mTvVolumeSales = reserveBinding.tvReserveVolumeSales;
        mTvReserveBar = reserveBinding.tvReserveReserveBar;

    }

    private void init() {
        //商务ktv圆形按钮被选中
        clickToChange(0);
        //设置字体
        AssetManager assets = App.getContext().getAssets();
        Typeface font = Typeface.createFromAsset(assets, "fonts/trends .ttf");
        mTvReserveTitle.setTypeface(font);
        reserveBinding.setClick(this);
//        //得到当前城市，强制设置为“上海”
        city = getCity();
//        Log.e("city","LocationUtils.location为空：\t" + (LocationUtils.location == null));
        //第一次强制设置为上海
        mTvReserveCity.setText(city.getName());

        mBannerIamges = new ArrayList<>();
        mBanner.setImageLoader(new GlideImageLoader());
        // 商铺列表
        mStores = new ArrayList();
        mReserveAdapter = new ReserveAdapter(mContext, mStores,storeType);
        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvReserve.setLayoutManager(lm);
        mRvReserve.setAdapter(mReserveAdapter);
        // 主页网络请求
        showProgress();
        getFilterList(null, Double.parseDouble(DataValue.latitude), Double.parseDouble(DataValue.longitude));
        //第一次请求数据，city.getId()强制设置为上海的id
        getStoreList(null, city.getId(), page, size, null, storeType, null, null, null);
        // 筛选
        areas = city.getChild();
        filters = new ArrayList<>();
        conditions = new ArrayList<>(Arrays.asList(UIUtils.getStrings(R.array.filter_condition)));
        filterZH = new ArrayList<>(Arrays.asList(UIUtils.getStrings(R.array.filter)));
        filterEN = Arrays.asList(UIUtils.getStrings(R.array.filter_en));
        filterStoreType = new ArrayList<>();
        filterBeans = new ArrayList<>();
        // 弹窗
        newPopupWindow();

        registerBroadcastReceiver();
        // 是否可以上下拉
        mSvReserve.setCanPullDown(true);
        mSvReserve.setCanPullUp(true);
        //监听GPS打开情况，打开后获取定位
//        LocationUtils.getLocation(mContext, this);
    }

    private void setListener() {
        mRlTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 空实现，防止滑动后，点击传递到后面
            }
        });

        mPtrl.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                isPullDown = true;
                page = 0;
                size = count;
                getStoreList(null, city.getId(), page, size, areaId, storeType, category, filterType, value);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                isPullUp = true;
                size = count;
//                if (mStores.size() < 10) {
//                    page = 0;
//                } else {
//                    page++;
//                }
                page++;
                getStoreList(null, city.getId(), page, size, areaId, storeType, category, filterType, value);
            }
        });
        onTouchListener = new MainActivity.MyOnTouchListener() {
            float lastX = 0, lastY = 0, x, y;

            @Override
            public boolean onTouch(MotionEvent ev) {
                int action = ev.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = ev.getX();
                        lastY = ev.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x = ev.getX();
                        y = ev.getY();
                        float dx = Math.abs(x - lastX);
                        float dy = Math.abs(y - lastY);
                        // 触摸在轮播图上，x轴移动的距离大于y轴的一半就不能下滑，横滑事不能下滑
                        // 反之，当y轴移动距离大于x轴的一半，才能下滑
                        if (lastY > mBanner.getTop() && y < mBanner.getBottom() && dx >= dy / 2) {
                            mSvReserve.setCanPullDown(false);
                        } else if (lastY > mBanner.getTop() && y < mBanner.getBottom() && dy >= dx / 2) {
                            mSvReserve.setCanPullDown(true);
                        } else {
                            mSvReserve.setCanPullDown(true);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        };
        ((MainActivity) mContext).registerMyOnTouchListener(onTouchListener);
        mSvReserve.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
//                标题栏随滑动距离改变透明度
//                if (t <= mContext.getResources().getDimension(R.dimen.menu_height)) {
//                    mVTopMenuBg.setAlpha(t / mContext.getResources().getDimension(R.dimen.menu_height));
//                } else {
//                    mVTopMenuBg.setAlpha(1F);
//                }
                //标题栏不透明
                mVTopMenuBg.setAlpha(1F);
                hoverBar();
            }
        });

        /*去掉首页选择栏按钮*/
        mLlReserveArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterArea.showAsDropDown(mLlReserveArea);
                setTextColor(mTvReserveArea, mTvReserveAreaSuspend, false);
                setImageViewRotateAnimation(mIvReserveArea, mIvReserveAreaSuspend, false);

            }
        });
        mLlReserveFilterReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.showAsDropDown(mLlReserveFilterReal);
                setTextColor(mTvReserveFilter, mTvReserveFilterSuspend, false);
                setImageViewRotateAnimation(mIvReserveFilter, mIvReserveFilterSuspend, false);
            }
        });

        mLlReserveAreaSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterArea.showAsDropDown(mLlReserveAreaSuspend);
                setTextColor(mTvReserveArea, mTvReserveAreaSuspend, false);
                setImageViewRotateAnimation(mIvReserveArea, mIvReserveAreaSuspend, false);
            }
        });
        mLlReserveFilterRealSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.showAsDropDown(mLlReserveFilterRealSuspend);
                setTextColor(mTvReserveFilter, mTvReserveFilterSuspend, false);
                setImageViewRotateAnimation(mIvReserveFilter, mIvReserveFilterSuspend, false);
            }
        });

        //娱乐分栏点击事件
        entertainment(mLlBar, 3, "酒吧");
        entertainment(mLlVolumeSales, 1, "量贩KTV");
        entertainment(mLlCommercialKtv, 0, "商务KTV");

        // 筛选栏弹出窗点击和隐藏监听
        setPopupWindowClickListener();
        setPopupWindowDismissListener();
    }

    private void entertainment(LinearLayout linearLayout, final int num, final String name) {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick(num);
                if (filterStoreType.size() > 0) {
                    setStoreType(filterStoreType.get(num).getId());
                } else {
                    ToastUtils.makePicTextShortToast(getActivity(), "没有数据");
                }
            }
        });
    }

    /**
     * 点击同一个图标不会改变第二次
     *
     * @param num
     */
    private void mClick(int num) {
        switch (num) {
            case 0:
                if (selectedId != 0) {
                    clickToChange(0);
                }
                break;
            case 1:
                if (selectedId != 1) {
                    clickToChange(1);
                }
                break;
            case 3:
                if (selectedId != 3) {
                    clickToChange(3);
                }
                break;
            default:
                break;
        }
    }

    public void clickToChange(int num) {
        clickToPress(num);
        setSelectedId(num);
    }

    private void clickToPress(int num) {

        //不按时
        mIvCommericalKtv.setImageResource(R.drawable.reserve_commercial_ktv);
        mTvCommercialKtv.setTextColor(getResources().getColor(R.color.text_black));
        mIvVolumeSales.setImageResource(R.drawable.reserve_volume_sales);
        mTvVolumeSales.setTextColor(getResources().getColor(R.color.text_black));
        mIvReserveBar.setImageResource(R.drawable.reserve_bar);
        mTvReserveBar.setTextColor(getResources().getColor(R.color.text_black));
        //按下时
        switch (num) {
            case 0:
                mIvCommericalKtv.setImageResource(R.drawable.reserve_commercial_ktv_in);
                mTvCommercialKtv.setTextColor(getResources().getColor(R.color.text_red));
                setSelectedId(num);
                break;
            case 1:
                mIvVolumeSales.setImageResource(R.drawable.reserve_volume_sales_in);
                mTvVolumeSales.setTextColor(getResources().getColor(R.color.text_red));
                break;
            case 3:
                mIvReserveBar.setImageResource(R.drawable.reserve_bar_in);
                mTvReserveBar.setTextColor(getResources().getColor(R.color.text_red));
                break;
            default:
                break;
        }
    }

    private void setSelectedId(int num) {
        switch (num) {
            case 0:
                selectedId = 0;
                break;
            case 1:
                selectedId = 1;
                break;
            case 3:
                selectedId = 3;
            default:
                break;
        }
    }

    private void newPopupWindow() {
        // 区域筛选
        filterAreaAdapter = new FilterPopDialogAdapter(mContext, areas);
        filterArea = new FilterPopupDialog(mContext)
                .setPopupWindowHeight(254)

                .setAdapter(filterAreaAdapter);
        // 筛选（两级菜单）
        filterAdapter = new FilterPopDialogAdapter(mContext, filterBeans);
        filter = new FilterPopupDialog(mContext)
                .setAdapter(filterAdapter);

    }

    private void setPopupWindowClickListener() {
        filterAreaAdapter.setListener(new FilterPopDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, String dataID, int position) {
                if (position == 0) {
                    mTvReserveArea.setText(R.string.area_filter);
                    mTvReserveAreaSuspend.setText(R.string.area_filter);
                    areaId = null;
                } else {
                    mTvReserveArea.setText(name);
                    mTvReserveAreaSuspend.setText(name);
                    areaId = dataID;
                }
                filterArea.dismiss();
                showProgress();
                getStoreList(null, city.getId(), 0, 0, areaId, storeType, category, filterType, value);
            }
        });

        // 店家   房型，装修风格，档次，活动和优惠等
        filterAdapter.setListener(new FilterPopDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, String dataID, int position) {
                if (position == 0) {
                    mTvReserveFilter.setText(R.string.filter);
                    mTvReserveFilterSuspend.setText(R.string.filter);
                    filter.dismiss();

                    filterType = null;
                    value = null;
                    filterAdapter.cleanStatus();
                } else {
                    filterType = "decorate";

                    value = dataID;
                    filter.dismiss();
                    mTvReserveFilter.setText(name);
                    mTvReserveFilterSuspend.setText(name);
                }
                getStoreList(null, city.getId(), 0, 0, areaId, storeType, category, filterType, value);
            }
        });

    }

    private void setPopupWindowDismissListener() {
        filterArea.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextColor(mTvReserveArea, mTvReserveAreaSuspend, true);
                setImageViewRotateAnimation(mIvReserveArea, mIvReserveAreaSuspend, true);
            }
        });
        filter.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextColor(mTvReserveFilter, mTvReserveFilterSuspend, true);
                setImageViewRotateAnimation(mIvReserveFilter, mIvReserveFilterSuspend, true);
            }
        });

    }

    /**
     * 设置点击筛选的文字颜色
     *
     * @param textView1
     * @param textView2
     * @param isClick
     */
    private void setTextColor(TextView textView1, TextView textView2, boolean isClick) {
        if (isClick) {
            setTextColorToWhite(textView1);
            setTextColorToWhite(textView2);
        } else {
            setTextColorToRed(textView1);
            setTextColorToRed(textView2);
        }
    }

    /**
     * 设置为橙色
     *
     * @param textView
     */
    private void setTextColorToRed(TextView textView) {
        textView.setTextColor(UIUtils.getColor(R.color.text_red));
    }

    /**
     * 设置为白色
     */
    private void setTextColorToWhite(TextView textView) {
        textView.setTextColor(UIUtils.getColor(R.color.text_black));
    }

    /**
     * 设置ImageView的旋转动画
     *
     * @param imageView1
     * @param imageView2
     * @param isRotated
     */
    private void setImageViewRotateAnimation(ImageView imageView1, ImageView imageView2, boolean isRotated) {
        if (isRotated) {
            setImageViewRotateAnimationRotated(imageView1);
            /*去掉了顶部“区域 风格”栏*/
            if (mFilterSuspend.getVisibility() == View.VISIBLE) {
                setImageViewRotateAnimationRotated(imageView2);
            }
        } else {
            setImageViewRotateAnimationUnrotate(imageView1);
            if (mFilterSuspend.getVisibility() == View.VISIBLE) {
                setImageViewRotateAnimationUnrotate(imageView2);
            }
        }
    }

    /**
     * 设置ImageView旋转动画，未旋转过，也就是手指点下去第一次旋转
     *
     * @param imageView
     */
    private void setImageViewRotateAnimationUnrotate(final ImageView imageView) {
        imageView.startAnimation(trilateral_0_180_Animation());

        animation_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.clearAnimation();
                imageView.setImageResource(R.drawable.pull_up);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 设置ImageView旋转动画，旋转过
     *
     * @param imageView
     */
    private void setImageViewRotateAnimationRotated(final ImageView imageView) {
        imageView.startAnimation(trilateral_180_360_Animation());
        imageView.setImageResource(R.drawable.pull_down_3);
    }

    /**
     * 三角形向上的动画
     */
    private RotateAnimation trilateral_0_180_Animation() {
        Log.e("animation", "走了向上的动画");
        if (animation_up == null) {
            animation_up = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotate_down_deal_filter);
        }
        animation_up.setDuration(300);
        animation_up.setFillAfter(!animation_up.getFillAfter());
        return animation_up;
    }

    /**
     * 三角形向下的动画
     */
    private RotateAnimation trilateral_180_360_Animation() {
        Log.e("animation", "走了向上的动画");
        RotateAnimation animation_down = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotate_up_deal_filter);
        animation_down.setDuration(300);
        animation_down.setFillAfter(!animation_down.getFillAfter());
        return animation_down;
    }

    /**
     * 把assets中city.txt的json形式的数据准成bean类型的省列表
     *
     * @return
     */
    private List<CityResource.Province> getProvinceList() {
        List<CityResource.Province> provinces = null;
        try {
            InputStream inputStream = mContext.getAssets().open("city.txt");
            int length = inputStream.available();
            byte[] cityBytes = new byte[length];
            inputStream.read(cityBytes);
            String json = new String(cityBytes);
//            Logger.json(json);
            Gson gson = new Gson();
            CityResource cityResource = gson.fromJson(json, CityResource.class);
            provinces = cityResource.provinces;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    /**
     * 定位返回的数据mBdLocation
     *
     * @param mBdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation mBdLocation) {
//        bdLocation = mBdLocation;
//        //得到当前城市
//        city = getCity();
//        Log.e("city","LocationUtils.location为空：\t" + (LocationUtils.location == null?"true":LocationUtils.location .getCity()));
//        mTvReserveCity.setText(city.getName());
//        getStoreList(null, city.getId(), page, size, null, storeType, null, null, null);
//        // 筛选
//        areas = city.getChild();
    }

    //得到CityResource.City类
    private CityResource.City getCity() {
        CityResource.City city = null;
        List<CityResource.Province> provinces = getProvinceList();   //得到bean类型的省列表
        BDLocation bdLocation = LocationUtils.location;
        for (CityResource.Province province : provinces) {
            //循环bean类型的省列表中province类中的城市
            for (int i = 0; i < province.getChild().size(); i++) {
                city = province.getChild().get(i);
                if (bdLocation != null) {// 定位成功
                    String bdCity = bdLocation.getCity();   //百度地位的城市

                    if (bdCity != null) {// 获取城市成功
                        //如果定位的当前城市和province中的城市一样，则返回
                        if (bdCity.contains(city.getName())) {
                            return city;
                        }
                    } else {   // 获取城市失败，从本地读取，也就是个人信息里选择的城市,判断是否一样，一样则返回
                        //第一次进入页面city.getId()强制设置为上海的id ="2623"
                        if ("2623".equals(city.getId())) {
                            return city;
                        }
                    }
                } else {   // 定位失败，从本地读取，也就是个人信息里选择的城市,判断是否一样，一样则返回
                    //第一次进入页面city.getId()强制设置为上海的id ="2623"
                    if ("2623".equals(city.getId())) {
                        return city;
                    }
                }
            }
        }
        return city;
    }

    /**
     * 悬停栏
     */
    private void hoverBar() {
        if (mLlSuspendTop == 0 || mLlSuspendBottom == 0) {
            //"区域 风格" 栏的测量
            if (mLlReserveFilter != null) {
                mLlSuspendTop = mLlReserveFilter.getTop();
                mLlSuspendBottom = mLlReserveFilter.getBottom();
            }
        }
        if (mSvReserve.getScrollY() > mLlSuspendTop) {
            if (mFilterSuspend.getVisibility() != View.VISIBLE) {
                mFilterSuspend.setVisibility(View.VISIBLE);
            }
        } else if (mSvReserve.getScrollY() < mLlSuspendBottom) {
            if (mFilterSuspend.getVisibility() != View.INVISIBLE) {
                mFilterSuspend.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void getFilterList(String token, double lat, double lng) {
        Map<String, String> param = new HashMap<>();
        if (token != null) {
            param.put(Network.Param.TOKEN, token);
        }
        if (lat != Constants.USELESS_NUMBER_PARAM) {
            param.put(Network.Param.LAT, String.valueOf(lat));
        }
        if (lng != Constants.USELESS_NUMBER_PARAM) {
            param.put(Network.Param.LNG, String.valueOf(lng));
        }

        OkHttpUtils.post()
                .url(Network.User.COMMON_FILTER)
                .params(param)
                .build()
                .execute(new DCallback<Filter>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                    }

                    @Override
                    public void onResponse(Filter response) {
                        if (isSuccess(response)) {
                            tidyFilters(response);
                        }
                    }
                });

    }

    /**
     * 筛选数据
     *
     * @param filter
     */
    private void tidyFilters(Filter filter) {
        // 筛选一级
        filterStoreType.addAll(filter.getStyle());
        // 筛选三级
//        filters.add(filter.getRoom());
//        filters.add(filter.getDecorate());
        filterBeans.addAll(filter.getDecorate());
//        filters.add(filter.getGrade());
//        filters.add(filter.getActivity());
        // 折扣排序
//        Collections.sort(filter.getDiscount(), new Comparator<Filter.FilterBean>() {
//            @Override
//            public int compare(Filter.FilterBean o1, Filter.FilterBean o2) {
//                // 小到大d1-d2，大到小d2-d1
//                double d1 = Double.parseDouble(o1.getName());
//                double d2 = Double.parseDouble(o2.getName());
//                return (int) (d1 - d2);
//            }
//        });
//        // 折扣
//        filters.add(filter.getDiscount());
    }

    /**
     * 商铺列表
     *
     * @param token
     * @param page
     * @param size
     * @param area
     * @param storeTypeId
     * @param type
     * @param filter
     * @param value
     */
    private void getStoreList(String token, String cityId, int page, int size, String area, final String storeTypeId, final String type, String filter, String value) {
        Map<String, String> param = new HashMap<>();
//        param.put(Network.Param.TOKEN, token);
        param.put(Network.Param.CITY, cityId);


        // param.put(Network.Param.LAT, String.valueOf(lat));
        param.put(Network.Param.LAT, DataValue.latitude);


        //  param.put(Network.Param.LNG, String.valueOf(lng));
        param.put(Network.Param.LNG, DataValue.longitude);


        param.put(Network.Param.PAGE, String.valueOf(page));
        param.put(Network.Param.SIZE, String.valueOf(size));
        if (area != null) {
            param.put(Network.Param.AREA, area);
        }
        if (storeTypeId != null) {
            param.put(Network.Param.STYLE, storeTypeId);
        }
        if (type != null) {
            param.put(Network.Param.TYPE, type);
        }
        if (filter != null && value != null) {
            param.put(Network.Param.FILTER, filter);
            param.put(Network.Param.VALUE, value);
        }


        OkHttpUtils.post()
                .url(Network.User.COMMON_STORE_LIST)
                .params(param)
                .build()
                .execute(new DCallback<StoreList>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onResponse(StoreList response) {
                        if (isSuccess(response)) {
                            if (isFirst) {   //轮播图的数据只请求一次
                                showBanner(response.getSliders());
                                isFirst = !isFirst;
                            }
                            showStoreList(response.getStore(),storeTypeId);
                        } else {
                            refreshOrLoadFail();
                        }
                    }
                });
    }

    /**
     * 轮播图
     */
    private void showBanner(List<String> images) {
        if (images == null) {
            return;
        }
        mBannerIamges.clear();
        for (String url : images) {
            mBannerIamges.add(Network.IMAGE + url);
        }
        mBanner.setImages(mBannerIamges);
        mBanner.start();
    }

    /**
     * 商铺列表
     *
     * @param stores
     */
    private void showStoreList(List<StoreList.Store> stores,String type) {
        mReserveAdapter.setType(type);
        if (isPullDown) {   // 下拉刷新
            mStores.clear();
            mStores.addAll(stores);
            mReserveAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
        } else if (isPullUp) {// 上拉加载
            //集合为0，则显示“没有更多数据”
            if (stores.size() == 0) {
                refreshOrLoadFail();
                return;
            }
//            if (mStores.size() < 6) {
//                mStores.clear();
//            }
            mStores.addAll(stores);
            mReserveAdapter.notifyDataSetChanged();
            refreshAndLoadMoreSuccess();
            return;
        } else {// 正常情况
            mStores.clear();
            mStores.addAll(stores);
            mReserveAdapter.notifyDataSetChanged();
            if (stores.size() == 0) {
                ToastUtils.showShortToast(mContext, "未找到店家");
            }
        }
    }

    /**
     * 注册广播
     */
    private void registerBroadcastReceiver() {
        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_CITY_SELECTED);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, intentFilter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            city = (CityResource.City) intent.getSerializableExtra(Constants.CITY);
            mTvReserveCity.setText(city.getName());

            areas.clear();
            areas.addAll(city.getChild());
            areas.add(0, "全部");
            filterAreaAdapter.cleanStatus();
            filterAreaAdapter.notifyDataSetChanged();
            mTvReserveArea.setText(R.string.area_filter);
            mTvReserveAreaSuspend.setText(R.string.area_filter);
            areaId = null;
            mTvReserveFilter.setText(R.string.filter);
            mTvReserveFilterSuspend.setText(R.string.filter);
            filterType = null;
            value = null;
//            mTvReserveCategory.setText(R.string.all);
//            mTvReserveCategorySuspend.setText(R.string.all);
            category = null;

            showProgress();
            getStoreList(null, city.getId(), 0, 10, areaId, storeType, category, filterType, value);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        switch (view.getId()) {
            case R.id.tv_reserve_city:
                intent.setClass(mContext, AlterCityActivity.class);
                break;
            case R.id.ib_reserve_search:
                intent.putExtra(Constants.PAGE, Constants.SEARCH);
                break;
            default:
                break;
        }
        mContext.startActivity(intent);
    }

    /**
     * 检查用户是否设置过支付密码
     */
    private void getResult4SetPayPassword() {
        OkHttpUtils.post()
                .url(Network.User.USER_CONFIRM_SETCODE)
                .addParams(Network.Param.TOKEN, mUser.getToken())
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
                                mSpUtils.putBoolean(Constants.IS_SET_PAY_PASSWORD, true);
                            }
                        }
                    }
                });
    }

    /**
     * 上传极光RegistrationID
     */
    private void uploadRegistrationID() {
        String registrationID = JPushInterface.getRegistrationID(mContext);
        if (registrationID != null && !registrationID.isEmpty()) {
            OkHttpUtils.post()
                    .url(Network.User.USER_SAVE_REGISTRATIONID)
                    .addParams(Network.Param.TOKEN, mUser.getToken())
                    .addParams(Network.Param.REGISTRATIONID, registrationID)
                    .build()
                    .execute(new DCallback<IsOk>() {
                        @Override
                        public void onError(Call call, Exception e) {
                            connectError();
                        }

                        @Override
                        public void onResponse(IsOk response) {
                            closeProgress();
                        }
                    });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            if (mLlSuspendTop == 0 || mLlSuspendBottom == 0) {
                //"区域 风格" 栏的测量
                if (mLlReserveFilter != null) {
                    mLlSuspendTop = mLlReserveFilter.getTop();
                    mLlSuspendBottom = mLlReserveFilter.getBottom();
                }
            }
        }
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
        page = 0;
        getStoreList(null, city.getId(), 0, count, areaId, this.storeType, category, filterType, value);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
            receiver = null;
        }
        ((MainActivity) mContext).unregisterMyOnTouchListener(onTouchListener);
        if (animation_up != null) {
            animation_up.cancel();
        }
    }
}
