package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHousenewsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_housenews, 1);
        sViewsWithIds.put(R.id.relativeLayout, 2);
        sViewsWithIds.put(R.id.tv_room_title, 3);
        sViewsWithIds.put(R.id.tv_room_discount_real, 4);
        sViewsWithIds.put(R.id.tv_room_address, 5);
        sViewsWithIds.put(R.id.tv_room_distance, 6);
        sViewsWithIds.put(R.id.tv_housenews_use, 7);
        sViewsWithIds.put(R.id.tv_housenews_book, 8);
        sViewsWithIds.put(R.id.tv_housenews_free, 9);
        sViewsWithIds.put(R.id.tv_housenews_type, 10);
        sViewsWithIds.put(R.id.tv_housenews_housename, 11);
        sViewsWithIds.put(R.id.tv_housenews_lowest, 12);
        sViewsWithIds.put(R.id.tv_housenews_housenews, 13);
        sViewsWithIds.put(R.id.rc_merchant_housenews, 14);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rcMerchantHousenews;
    @NonNull
    public final android.widget.RelativeLayout relativeLayout;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmHousenews;
    @NonNull
    public final android.widget.TextView tvHousenewsBook;
    @NonNull
    public final android.widget.TextView tvHousenewsFree;
    @NonNull
    public final android.widget.TextView tvHousenewsHousename;
    @NonNull
    public final android.widget.TextView tvHousenewsHousenews;
    @NonNull
    public final android.widget.TextView tvHousenewsLowest;
    @NonNull
    public final android.widget.TextView tvHousenewsType;
    @NonNull
    public final android.widget.TextView tvHousenewsUse;
    @NonNull
    public final android.widget.TextView tvRoomAddress;
    @NonNull
    public final android.widget.TextView tvRoomDiscountReal;
    @NonNull
    public final android.widget.TextView tvRoomDistance;
    @NonNull
    public final android.widget.TextView tvRoomTitle;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHousenewsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rcMerchantHousenews = (android.support.v7.widget.RecyclerView) bindings[14];
        this.relativeLayout = (android.widget.RelativeLayout) bindings[2];
        this.tmHousenews = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvHousenewsBook = (android.widget.TextView) bindings[8];
        this.tvHousenewsFree = (android.widget.TextView) bindings[9];
        this.tvHousenewsHousename = (android.widget.TextView) bindings[11];
        this.tvHousenewsHousenews = (android.widget.TextView) bindings[13];
        this.tvHousenewsLowest = (android.widget.TextView) bindings[12];
        this.tvHousenewsType = (android.widget.TextView) bindings[10];
        this.tvHousenewsUse = (android.widget.TextView) bindings[7];
        this.tvRoomAddress = (android.widget.TextView) bindings[5];
        this.tvRoomDiscountReal = (android.widget.TextView) bindings[4];
        this.tvRoomDistance = (android.widget.TextView) bindings[6];
        this.tvRoomTitle = (android.widget.TextView) bindings[3];
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
    public static FragmentHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentHousenewsBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_housenews, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_housenews, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentHousenewsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentHousenewsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_housenews_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentHousenewsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}