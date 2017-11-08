package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.mvp_main, 1);
        sViewsWithIds.put(R.id.ll_main_tab, 2);
        sViewsWithIds.put(R.id.ll_maintab_reserve, 3);
        sViewsWithIds.put(R.id.iv_main_tab_reserve, 4);
        sViewsWithIds.put(R.id.tv_main_tab_reserve, 5);
        sViewsWithIds.put(R.id.ll_main_tab_pay, 6);
        sViewsWithIds.put(R.id.iv_main_tab_pay, 7);
        sViewsWithIds.put(R.id.tv_main_tab_pay, 8);
        sViewsWithIds.put(R.id.ll_main_tab_discover, 9);
        sViewsWithIds.put(R.id.iv_main_tab_discover, 10);
        sViewsWithIds.put(R.id.tv_main_tab_discover, 11);
        sViewsWithIds.put(R.id.ll_main_tab_mine, 12);
        sViewsWithIds.put(R.id.iv_main_tab_mine, 13);
        sViewsWithIds.put(R.id.tv_main_tab_mine, 14);
        sViewsWithIds.put(R.id.tv, 15);
        sViewsWithIds.put(R.id.tv2, 16);
        sViewsWithIds.put(R.id.textView, 17);
        sViewsWithIds.put(R.id.imageView, 18);
        sViewsWithIds.put(R.id.button, 19);
    }
    // views
    @NonNull
    public final android.widget.Button button;
    @NonNull
    public final android.widget.ImageView imageView;
    @NonNull
    public final android.widget.ImageView ivMainTabDiscover;
    @NonNull
    public final android.widget.ImageView ivMainTabMine;
    @NonNull
    public final android.widget.ImageView ivMainTabPay;
    @NonNull
    public final android.widget.ImageView ivMainTabReserve;
    @NonNull
    public final android.widget.LinearLayout llMainTab;
    @NonNull
    public final android.widget.LinearLayout llMainTabDiscover;
    @NonNull
    public final android.widget.LinearLayout llMainTabMine;
    @NonNull
    public final android.widget.LinearLayout llMainTabPay;
    @NonNull
    public final android.widget.LinearLayout llMaintabReserve;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.MyViewPager mvpMain;
    @NonNull
    public final android.widget.TextView textView;
    @NonNull
    public final android.widget.TextView tv;
    @NonNull
    public final android.widget.TextView tv2;
    @NonNull
    public final android.widget.TextView tvMainTabDiscover;
    @NonNull
    public final android.widget.TextView tvMainTabMine;
    @NonNull
    public final android.widget.TextView tvMainTabPay;
    @NonNull
    public final android.widget.TextView tvMainTabReserve;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds);
        this.button = (android.widget.Button) bindings[19];
        this.imageView = (android.widget.ImageView) bindings[18];
        this.ivMainTabDiscover = (android.widget.ImageView) bindings[10];
        this.ivMainTabMine = (android.widget.ImageView) bindings[13];
        this.ivMainTabPay = (android.widget.ImageView) bindings[7];
        this.ivMainTabReserve = (android.widget.ImageView) bindings[4];
        this.llMainTab = (android.widget.LinearLayout) bindings[2];
        this.llMainTabDiscover = (android.widget.LinearLayout) bindings[9];
        this.llMainTabMine = (android.widget.LinearLayout) bindings[12];
        this.llMainTabPay = (android.widget.LinearLayout) bindings[6];
        this.llMaintabReserve = (android.widget.LinearLayout) bindings[3];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mvpMain = (com.fanc.wheretoplay.view.MyViewPager) bindings[1];
        this.textView = (android.widget.TextView) bindings[17];
        this.tv = (android.widget.TextView) bindings[15];
        this.tv2 = (android.widget.TextView) bindings[16];
        this.tvMainTabDiscover = (android.widget.TextView) bindings[11];
        this.tvMainTabMine = (android.widget.TextView) bindings[14];
        this.tvMainTabPay = (android.widget.TextView) bindings[8];
        this.tvMainTabReserve = (android.widget.TextView) bindings[5];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_main, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_main, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityMainBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityMainBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_main_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMainBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}