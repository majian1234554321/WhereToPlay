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
import android.widget.ImageButton;
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
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
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

public class ReserveFragment2 extends BaseFragment implements IOnFocusListener, LocationUtils.Callback, View.OnClickListener, XRecyclerView.LoadingListener {


    MainActivity.MyOnTouchListener onTouchListener;

    int selectedId;
    public String type = "1";
    private RotateAnimation animation_up;
    private TextView tv_reserve_city, mTvReserveTitle;
    private CityResource.City city;
    private Receiver receiver;
    private List areas;
    private List filters;
    private List conditions;
    private List filterZH;
    private List filterEN;
    private List<Filter.FilterBean> filterStoreType;
    private List filterBeans;
    private List mBannerIamges;
    private XRecyclerView recyclerview;
    private List mStores;
    private ReserveAdapter mReserveAdapter;


    int mLlSuspendTop;
    int mLlSuspendBottom;

    public String storeType = "2";// 筛选店铺类型,默认为 "2" 表示商务KTV

    boolean isFirst = true;
    int page, count = 9, size = count;
    private Banner mBanner;
    private FilterPopDialogAdapter filterAreaAdapter;
    private FilterPopupDialog filterArea;
    private FilterPopDialogAdapter filterAdapter;
    private FilterPopupDialog filter;
    private ImageView mIvCommericalKtv;
    private TextView mTvCommercialKtv;
    private ImageView mIvVolumeSales;
    private TextView mTvVolumeSales;
    private ImageView mIvReserveBar;
    private TextView mTvReserveBar;
    private LinearLayout mLlBar;
    private LinearLayout mLlVolumeSales;
    private LinearLayout mLlCommercialKtv;
    private LinearLayout mLlReserveArea;
    private LinearLayout mLlReserveFilterReal;
    private View headView;
    private TextView mTvReserveArea;
    private ImageView mIvReserveArea;
    private TextView mTvReserveFilter;
    private ImageView mIvReserveFilter;
    private LinearLayout mLlReserveAreaSuspend;
    private TextView mTvReserveAreaSuspend;
    private ImageView mIvReserveAreaSuspend;
    private TextView mTvReserveFilterSuspend;
    private ImageView mIvReserveFilterSuspend;
    private LinearLayout mLlReserveFilterRealSuspend;

    String areaId;// 区域id
    String filterType;// 筛选
    String value;// 筛选值，id
    String category;// 分类筛选
    private LinearLayout mFilterSuspend;
    private LinearLayout mLlReserveFilter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = View.inflate(inflater.getContext(), R.layout.fragment_reserve2, null);


        tv_reserve_city = view.findViewById(R.id.tv_reserve_city);
        tv_reserve_city.setOnClickListener(this);


        mLlReserveFilterRealSuspend = view.findViewById(R.id.ll_reserve_filter_real_suspend);

        mFilterSuspend = view.findViewById(R.id.ll_reserve_filter_suspend);

        mLlReserveAreaSuspend = view.findViewById(R.id.ll_reserve_area_suspend);
        mTvReserveAreaSuspend = view.findViewById(R.id.tv_reserve_area__suspend);
        mIvReserveAreaSuspend = view.findViewById(R.id.iv_reserve_area__suspend);

        ImageButton ib_reserve_search = view.findViewById(R.id.ib_reserve_search);
        ib_reserve_search.setOnClickListener(this);


        mTvReserveFilterSuspend = view.findViewById(R.id.tv_reserve_filter_suspend);
        mIvReserveFilterSuspend = view.findViewById(R.id.iv_reserve_filter__suspend);


        mTvReserveTitle = view.findViewById(R.id.tv_reserve_title);

        recyclerview = view.findViewById(R.id.recyclerview);

        headView = View.inflate(inflater.getContext(), R.layout.headview, null);

        mBanner = headView.findViewById(R.id.banner_reserve);

        mLlReserveFilter = headView.findViewById(R.id.ll_reserve_filter);


        mTvReserveArea = headView.findViewById(R.id.tv_reserve_area);
        mIvReserveArea = headView.findViewById(R.id.iv_reserve_area);

        mTvReserveFilter = headView.findViewById(R.id.tv_reserve_filter);
        mIvReserveFilter = headView.findViewById(R.id.iv_reserve_filter);

        mLlCommercialKtv = headView.findViewById(R.id.ll_reserve_commercial_ktv);
        mIvCommericalKtv = headView.findViewById(R.id.iv_reserve_commercial_ktv);
        mTvCommercialKtv = headView.findViewById(R.id.tv_reserve_commercial_ktv);


        mLlVolumeSales = headView.findViewById(R.id.ll_reserve_volume_sales);
        mIvVolumeSales = headView.findViewById(R.id.iv_reserve_volume_sales);
        mTvVolumeSales = headView.findViewById(R.id.tv_reserve_volume_sales);

        mLlBar = headView.findViewById(R.id.ll_reserve_bar);
        mIvReserveBar = headView.findViewById(R.id.iv_reserve_reserve_bar);
        mTvReserveBar = headView.findViewById(R.id.tv_reserve_reserve_bar);


        mLlReserveArea = headView.findViewById(R.id.ll_reserve_area);
        mLlReserveFilterReal = headView.findViewById(R.id.ll_reserve_filter_real);




        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);

        init();
        //第一次请求数据，city.getId()强制设置为上海的id
        getStoreList("onRefresh", city.getId(), page, size, null, storeType, null, null, null);


        recyclerview.getDefaultFootView().setLoadingHint("正在加载......");
        recyclerview.getDefaultFootView().setNoMoreHint("数据加载完毕");


        recyclerview.addHeaderView(headView);
        recyclerview.setLoadingListener(this);


        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getResult4SetPayPassword();
        // 上传极光id
        uploadRegistrationID();

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, ReuseActivity.class);
        switch (view.getId()) {
            case R.id.tv_reserve_city:
                intent.setClass(mContext, AlterCityActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ib_reserve_search:
                intent.putExtra(Constants.PAGE, Constants.SEARCH);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }

    }

    private void init() {
        //商务ktv圆形按钮被选中
        clickToChange(0);
        //设置字体
        AssetManager assets = App.getContext().getAssets();
        Typeface font = Typeface.createFromAsset(assets, "fonts/trends .ttf");
        mTvReserveTitle.setTypeface(font);

//        //得到当前城市，强制设置为“上海”
        city = getCity();
//        Log.e("city","LocationUtils.location为空：\t" + (LocationUtils.location == null));
        //第一次强制设置为上海
        tv_reserve_city.setText(city.getName());

        mBannerIamges = new ArrayList<>();
        mBanner.setImageLoader(new GlideImageLoader());
        // 商铺列表
        mStores = new ArrayList();
        mReserveAdapter = new ReserveAdapter(mContext, mStores, storeType);


        recyclerview.setAdapter(mReserveAdapter);
        // 主页网络请求
        showProgress();
        getFilterList(null, Double.parseDouble(DataValue.latitude), Double.parseDouble(DataValue.longitude));

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

        //监听GPS打开情况，打开后获取定位
//        LocationUtils.getLocation(mContext, this);
    }

    private void setListener() {

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
                            //  mSvReserve.setCanPullDown(false);
                        } else if (lastY > mBanner.getTop() && y < mBanner.getBottom() && dy >= dx / 2) {
                            // mSvReserve.setCanPullDown(true);
                        } else {
                            // mSvReserve.setCanPullDown(true);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        };
        ((MainActivity) mContext).registerMyOnTouchListener(onTouchListener);


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
                getStoreList("onRefresh", city.getId(), 0, 0, areaId, storeType, category, filterType, value);
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
                getStoreList("onRefresh", city.getId(), 0, 0, areaId, storeType, category, filterType, value);
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

        filterStoreType.addAll(filter.getStyle());

        filterBeans.addAll(filter.getDecorate());

    }

    /**
     * 商铺列表
     *
     * @param
     * @param page
     * @param size
     * @param area
     * @param storeTypeId
     * @param type
     * @param filter
     * @param value
     */
    private void getStoreList(final String action, String cityId, int page, int size, String area, final String storeTypeId, final String type, String filter, String value) {
        Map<String, String> param = new HashMap<>();
        param.put(Network.Param.CITY, cityId);
        param.put(Network.Param.LAT, DataValue.latitude);
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

                    }

                    @Override
                    public void onResponse(StoreList response) {
                        if (isSuccess(response)) {
                            if (isFirst) {   //轮播图的数据只请求一次
                                showBanner(response.getSliders());
                                isFirst = !isFirst;
                            }
                            showStoreList(response.getStore(), storeTypeId, action);
                        } else {

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
    private void showStoreList(List<StoreList.Store> stores, String type, String action) {
        mReserveAdapter.setType(type);
        if (action.equals("onRefresh")) {   // 下拉刷新
            mStores.clear();
            mStores.addAll(stores);
            mReserveAdapter.notifyDataSetChanged();
            recyclerview.refreshComplete();
        } else if (action.equals("onLoadMore")) {// 上拉加载

            if (stores.size() == 0) {
                if (recyclerview != null) {
                    recyclerview.setNoMore(true);
                    mReserveAdapter.notifyDataSetChanged();
                }
                return;
            }

            mStores.addAll(stores);
            mReserveAdapter.notifyDataSetChanged();
            recyclerview.loadMoreComplete();
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


    @Override
    public void onRefresh() {
        isPullDown = true;
        page = 0;
        size = count;
        getStoreList("onRefresh", city.getId(), page, size, areaId, storeType, category, filterType, value);
    }

    @Override
    public void onLoadMore() {
        isPullUp = true;
        size = count;
        page++;
        getStoreList("onLoadMore", city.getId(), page, size, areaId, storeType, category, filterType, value);
    }



    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            city = (CityResource.City) intent.getSerializableExtra(Constants.CITY);
            tv_reserve_city.setText(city.getName());

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
            page = 0;
            getStoreList("onRefresh", city.getId(), 0, 10, areaId, storeType, category, filterType, value);
        }
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
        getStoreList("onRefresh", city.getId(), 0, count, areaId, this.storeType, category, filterType, value);
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
