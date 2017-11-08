package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDiscoverBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_discover, 5);
        sViewsWithIds.put(R.id.iv_discover_near, 6);
        sViewsWithIds.put(R.id.tv_discover_near, 7);
        sViewsWithIds.put(R.id.iv_discover_hottest, 8);
        sViewsWithIds.put(R.id.tv_discover_hottest, 9);
        sViewsWithIds.put(R.id.iv_discover_new_store, 10);
        sViewsWithIds.put(R.id.tv_discover_new_store, 11);
        sViewsWithIds.put(R.id.iv_discover_most_discount, 12);
        sViewsWithIds.put(R.id.tv_discover_most_discount, 13);
        sViewsWithIds.put(R.id.rv_discover, 14);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivDiscoverHottest;
    @NonNull
    public final android.widget.ImageView ivDiscoverMostDiscount;
    @NonNull
    public final android.widget.ImageView ivDiscoverNear;
    @NonNull
    public final android.widget.ImageView ivDiscoverNewStore;
    @NonNull
    public final android.widget.LinearLayout llDiscoverHottest;
    @NonNull
    public final android.widget.LinearLayout llDiscoverMostDiscount;
    @NonNull
    public final android.widget.LinearLayout llDiscoverNear;
    @NonNull
    public final android.widget.LinearLayout llDiscoverNewStore;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvDiscover;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmDiscover;
    @NonNull
    public final android.widget.TextView tvDiscoverHottest;
    @NonNull
    public final android.widget.TextView tvDiscoverMostDiscount;
    @NonNull
    public final android.widget.TextView tvDiscoverNear;
    @NonNull
    public final android.widget.TextView tvDiscoverNewStore;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.DiscoverFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentDiscoverBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.ivDiscoverHottest = (android.widget.ImageView) bindings[8];
        this.ivDiscoverMostDiscount = (android.widget.ImageView) bindings[12];
        this.ivDiscoverNear = (android.widget.ImageView) bindings[6];
        this.ivDiscoverNewStore = (android.widget.ImageView) bindings[10];
        this.llDiscoverHottest = (android.widget.LinearLayout) bindings[2];
        this.llDiscoverHottest.setTag(null);
        this.llDiscoverMostDiscount = (android.widget.LinearLayout) bindings[4];
        this.llDiscoverMostDiscount.setTag(null);
        this.llDiscoverNear = (android.widget.LinearLayout) bindings[1];
        this.llDiscoverNear.setTag(null);
        this.llDiscoverNewStore = (android.widget.LinearLayout) bindings[3];
        this.llDiscoverNewStore.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvDiscover = (android.support.v7.widget.RecyclerView) bindings[14];
        this.tmDiscover = (com.fanc.wheretoplay.view.TopMenu) bindings[5];
        this.tvDiscoverHottest = (android.widget.TextView) bindings[9];
        this.tvDiscoverMostDiscount = (android.widget.TextView) bindings[13];
        this.tvDiscoverNear = (android.widget.TextView) bindings[7];
        this.tvDiscoverNewStore = (android.widget.TextView) bindings[11];
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
            setClick((com.fanc.wheretoplay.fragment.DiscoverFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.DiscoverFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.DiscoverFragment getClick() {
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
        com.fanc.wheretoplay.fragment.DiscoverFragment click = mClick;
        android.view.View.OnClickListener clickOnViewClickAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::onViewClick
                    clickOnViewClickAndroidViewViewOnClickListener = (((mClickOnViewClickAndroidViewViewOnClickListener == null) ? (mClickOnViewClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnViewClickAndroidViewViewOnClickListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.llDiscoverHottest.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.llDiscoverMostDiscount.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.llDiscoverNear.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.llDiscoverNewStore.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.DiscoverFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.DiscoverFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onViewClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentDiscoverBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDiscoverBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentDiscoverBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_discover, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentDiscoverBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDiscoverBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_discover, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentDiscoverBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDiscoverBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_discover_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentDiscoverBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}