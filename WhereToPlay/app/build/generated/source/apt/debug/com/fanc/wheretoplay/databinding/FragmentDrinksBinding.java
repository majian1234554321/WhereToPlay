package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDrinksBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_drinks, 1);
        sViewsWithIds.put(R.id.relativeLayout, 2);
        sViewsWithIds.put(R.id.tv_drinks_title, 3);
        sViewsWithIds.put(R.id.tv_drinks_discount_real, 4);
        sViewsWithIds.put(R.id.tv_drinks_address, 5);
        sViewsWithIds.put(R.id.tv_drinks_distance, 6);
        sViewsWithIds.put(R.id.tv_drinks_name, 7);
        sViewsWithIds.put(R.id.tv_housenews_housename, 8);
        sViewsWithIds.put(R.id.tv_drinks_action, 9);
        sViewsWithIds.put(R.id.rc_merchant_drinks, 10);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rcMerchantDrinks;
    @NonNull
    public final android.widget.RelativeLayout relativeLayout;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmDrinks;
    @NonNull
    public final android.widget.TextView tvDrinksAction;
    @NonNull
    public final android.widget.TextView tvDrinksAddress;
    @NonNull
    public final android.widget.TextView tvDrinksDiscountReal;
    @NonNull
    public final android.widget.TextView tvDrinksDistance;
    @NonNull
    public final android.widget.TextView tvDrinksName;
    @NonNull
    public final android.widget.TextView tvDrinksTitle;
    @NonNull
    public final android.widget.TextView tvHousenewsHousename;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDrinksBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rcMerchantDrinks = (android.support.v7.widget.RecyclerView) bindings[10];
        this.relativeLayout = (android.widget.RelativeLayout) bindings[2];
        this.tmDrinks = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvDrinksAction = (android.widget.TextView) bindings[9];
        this.tvDrinksAddress = (android.widget.TextView) bindings[5];
        this.tvDrinksDiscountReal = (android.widget.TextView) bindings[4];
        this.tvDrinksDistance = (android.widget.TextView) bindings[6];
        this.tvDrinksName = (android.widget.TextView) bindings[7];
        this.tvDrinksTitle = (android.widget.TextView) bindings[3];
        this.tvHousenewsHousename = (android.widget.TextView) bindings[8];
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
    public static FragmentDrinksBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDrinksBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentDrinksBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_drinks, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentDrinksBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDrinksBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_drinks, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentDrinksBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDrinksBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_drinks_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentDrinksBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}