package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentReserveBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_top_menu, 4);
        sViewsWithIds.put(R.id.v_reserve_top_menu_bg, 5);
        sViewsWithIds.put(R.id.cl_top_menu, 6);
        sViewsWithIds.put(R.id.tv_reserve_title, 7);
        sViewsWithIds.put(R.id.sv_reserve, 8);
        sViewsWithIds.put(R.id.banner_reserve, 9);
        sViewsWithIds.put(R.id.ll_reserve_entertainment, 10);
        sViewsWithIds.put(R.id.ll_reserve_commercial_ktv, 11);
        sViewsWithIds.put(R.id.iv_reserve_commercial_ktv, 12);
        sViewsWithIds.put(R.id.tv_reserve_commercial_ktv, 13);
        sViewsWithIds.put(R.id.ll_reserve_volume_sales, 14);
        sViewsWithIds.put(R.id.iv_reserve_volume_sales, 15);
        sViewsWithIds.put(R.id.tv_reserve_volume_sales, 16);
        sViewsWithIds.put(R.id.ll_reserve_bar, 17);
        sViewsWithIds.put(R.id.iv_reserve_reserve_bar, 18);
        sViewsWithIds.put(R.id.tv_reserve_reserve_bar, 19);
        sViewsWithIds.put(R.id.ll_reserve_filter, 20);
        sViewsWithIds.put(R.id.ll_reserve_area, 21);
        sViewsWithIds.put(R.id.tv_reserve_area, 22);
        sViewsWithIds.put(R.id.iv_reserve_area, 23);
        sViewsWithIds.put(R.id.v_reserve_divider1, 24);
        sViewsWithIds.put(R.id.ll_reserve_filter_real, 25);
        sViewsWithIds.put(R.id.tv_reserve_filter, 26);
        sViewsWithIds.put(R.id.iv_reserve_filter, 27);
        sViewsWithIds.put(R.id.rv_reserve, 28);
        sViewsWithIds.put(R.id.ll_reserve_filter_suspend, 29);
        sViewsWithIds.put(R.id.ll_reserve_area_suspend, 30);
        sViewsWithIds.put(R.id.tv_reserve_area__suspend, 31);
        sViewsWithIds.put(R.id.iv_reserve_area__suspend, 32);
        sViewsWithIds.put(R.id.v_reserve_divider1__suspend, 33);
        sViewsWithIds.put(R.id.ll_reserve_filter_real_suspend, 34);
        sViewsWithIds.put(R.id.tv_reserve_filter_suspend, 35);
        sViewsWithIds.put(R.id.iv_reserve_filter__suspend, 36);
    }
    // views
    @NonNull
    public final com.youth.banner.Banner bannerReserve;
    @NonNull
    public final android.support.constraint.ConstraintLayout clTopMenu;
    @NonNull
    public final android.widget.ImageButton ibReserveSearch;
    @NonNull
    public final android.widget.ImageView ivReserveArea;
    @NonNull
    public final android.widget.ImageView ivReserveAreaSuspend;
    @NonNull
    public final android.widget.ImageView ivReserveCommercialKtv;
    @NonNull
    public final android.widget.ImageView ivReserveFilter;
    @NonNull
    public final android.widget.ImageView ivReserveFilterSuspend;
    @NonNull
    public final android.widget.ImageView ivReserveReserveBar;
    @NonNull
    public final android.widget.ImageView ivReserveVolumeSales;
    @NonNull
    public final android.widget.LinearLayout llReserveArea;
    @NonNull
    public final android.widget.LinearLayout llReserveAreaSuspend;
    @NonNull
    public final android.widget.LinearLayout llReserveBar;
    @NonNull
    public final android.widget.LinearLayout llReserveCommercialKtv;
    @NonNull
    public final android.widget.LinearLayout llReserveEntertainment;
    @NonNull
    public final android.widget.LinearLayout llReserveFilter;
    @NonNull
    public final android.widget.LinearLayout llReserveFilterReal;
    @NonNull
    public final android.widget.LinearLayout llReserveFilterRealSuspend;
    @NonNull
    public final android.widget.LinearLayout llReserveFilterSuspend;
    @NonNull
    public final android.widget.LinearLayout llReserveVolumeSales;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.PullToRefreshLayout ptrlReserve;
    @NonNull
    public final android.widget.RelativeLayout rlTopMenu;
    @NonNull
    public final com.fanc.wheretoplay.view.MyRecycleView rvReserve;
    @NonNull
    public final com.fanc.wheretoplay.view.MyScrollView svReserve;
    @NonNull
    public final android.widget.TextView tvReserveArea;
    @NonNull
    public final android.widget.TextView tvReserveAreaSuspend;
    @NonNull
    public final android.widget.TextView tvReserveCity;
    @NonNull
    public final android.widget.TextView tvReserveCommercialKtv;
    @NonNull
    public final android.widget.TextView tvReserveFilter;
    @NonNull
    public final android.widget.TextView tvReserveFilterSuspend;
    @NonNull
    public final android.widget.TextView tvReserveReserveBar;
    @NonNull
    public final android.widget.TextView tvReserveTitle;
    @NonNull
    public final android.widget.TextView tvReserveVolumeSales;
    @NonNull
    public final android.view.View vReserveDivider1;
    @NonNull
    public final android.view.View vReserveDivider1Suspend;
    @NonNull
    public final android.view.View vReserveTopMenuBg;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.ReserveFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentReserveBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds);
        this.bannerReserve = (com.youth.banner.Banner) bindings[9];
        this.clTopMenu = (android.support.constraint.ConstraintLayout) bindings[6];
        this.ibReserveSearch = (android.widget.ImageButton) bindings[2];
        this.ibReserveSearch.setTag(null);
        this.ivReserveArea = (android.widget.ImageView) bindings[23];
        this.ivReserveAreaSuspend = (android.widget.ImageView) bindings[32];
        this.ivReserveCommercialKtv = (android.widget.ImageView) bindings[12];
        this.ivReserveFilter = (android.widget.ImageView) bindings[27];
        this.ivReserveFilterSuspend = (android.widget.ImageView) bindings[36];
        this.ivReserveReserveBar = (android.widget.ImageView) bindings[18];
        this.ivReserveVolumeSales = (android.widget.ImageView) bindings[15];
        this.llReserveArea = (android.widget.LinearLayout) bindings[21];
        this.llReserveAreaSuspend = (android.widget.LinearLayout) bindings[30];
        this.llReserveBar = (android.widget.LinearLayout) bindings[17];
        this.llReserveCommercialKtv = (android.widget.LinearLayout) bindings[11];
        this.llReserveEntertainment = (android.widget.LinearLayout) bindings[10];
        this.llReserveFilter = (android.widget.LinearLayout) bindings[20];
        this.llReserveFilterReal = (android.widget.LinearLayout) bindings[25];
        this.llReserveFilterRealSuspend = (android.widget.LinearLayout) bindings[34];
        this.llReserveFilterSuspend = (android.widget.LinearLayout) bindings[29];
        this.llReserveVolumeSales = (android.widget.LinearLayout) bindings[14];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.ptrlReserve = (com.fanc.wheretoplay.view.PullToRefreshLayout) bindings[3];
        this.ptrlReserve.setTag(null);
        this.rlTopMenu = (android.widget.RelativeLayout) bindings[4];
        this.rvReserve = (com.fanc.wheretoplay.view.MyRecycleView) bindings[28];
        this.svReserve = (com.fanc.wheretoplay.view.MyScrollView) bindings[8];
        this.tvReserveArea = (android.widget.TextView) bindings[22];
        this.tvReserveAreaSuspend = (android.widget.TextView) bindings[31];
        this.tvReserveCity = (android.widget.TextView) bindings[1];
        this.tvReserveCity.setTag(null);
        this.tvReserveCommercialKtv = (android.widget.TextView) bindings[13];
        this.tvReserveFilter = (android.widget.TextView) bindings[26];
        this.tvReserveFilterSuspend = (android.widget.TextView) bindings[35];
        this.tvReserveReserveBar = (android.widget.TextView) bindings[19];
        this.tvReserveTitle = (android.widget.TextView) bindings[7];
        this.tvReserveVolumeSales = (android.widget.TextView) bindings[16];
        this.vReserveDivider1 = (android.view.View) bindings[24];
        this.vReserveDivider1Suspend = (android.view.View) bindings[33];
        this.vReserveTopMenuBg = (android.view.View) bindings[5];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.click == variableId) {
            setClick((com.fanc.wheretoplay.fragment.ReserveFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.ReserveFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.ReserveFragment getClick() {
        return mClick;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.view.View.OnClickListener clickOnClickAndroidViewViewOnClickListener = null;
        com.fanc.wheretoplay.fragment.ReserveFragment click = mClick;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::onClick
                    clickOnClickAndroidViewViewOnClickListener = (((mClickOnClickAndroidViewViewOnClickListener == null) ? (mClickOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnClickAndroidViewViewOnClickListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.ibReserveSearch.setOnClickListener(clickOnClickAndroidViewViewOnClickListener);
            this.tvReserveCity.setOnClickListener(clickOnClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.ReserveFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.ReserveFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentReserveBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReserveBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentReserveBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_reserve, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentReserveBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReserveBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_reserve, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentReserveBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReserveBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_reserve_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentReserveBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}