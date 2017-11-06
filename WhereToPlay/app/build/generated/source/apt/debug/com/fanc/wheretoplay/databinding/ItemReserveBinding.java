package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemReserveBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_reserve_item, 4);
        sViewsWithIds.put(R.id.tv_reserve_item_discount_sum, 5);
        sViewsWithIds.put(R.id.tv_reserve_item_distance, 6);
        sViewsWithIds.put(R.id.tv_reserve_item_rise, 7);
        sViewsWithIds.put(R.id.iv_reserve_service, 8);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivReserveItem;
    @NonNull
    public final android.widget.ImageView ivReserveService;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvReserveItemAddress;
    @NonNull
    public final android.widget.TextView tvReserveItemDiscountSum;
    @NonNull
    public final android.widget.TextView tvReserveItemDistance;
    @NonNull
    public final android.widget.TextView tvReserveItemPrice;
    @NonNull
    public final android.widget.TextView tvReserveItemRise;
    @NonNull
    public final android.widget.TextView tvReserveItemTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.StoreList.Store mStore;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemReserveBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.ivReserveItem = (android.widget.ImageView) bindings[4];
        this.ivReserveService = (android.widget.ImageView) bindings[8];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvReserveItemAddress = (android.widget.TextView) bindings[3];
        this.tvReserveItemAddress.setTag(null);
        this.tvReserveItemDiscountSum = (android.widget.TextView) bindings[5];
        this.tvReserveItemDistance = (android.widget.TextView) bindings[6];
        this.tvReserveItemPrice = (android.widget.TextView) bindings[2];
        this.tvReserveItemPrice.setTag(null);
        this.tvReserveItemRise = (android.widget.TextView) bindings[7];
        this.tvReserveItemTitle = (android.widget.TextView) bindings[1];
        this.tvReserveItemTitle.setTag(null);
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
        if (BR.store == variableId) {
            setStore((com.fanc.wheretoplay.datamodel.StoreList.Store) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setStore(@Nullable com.fanc.wheretoplay.datamodel.StoreList.Store Store) {
        this.mStore = Store;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.store);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.StoreList.Store getStore() {
        return mStore;
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
        java.lang.String storeCapita = null;
        java.lang.String storeArea = null;
        java.lang.String storeName = null;
        com.fanc.wheretoplay.datamodel.StoreList.Store store = mStore;

        if ((dirtyFlags & 0x3L) != 0) {



                if (store != null) {
                    // read store.capita
                    storeCapita = store.getCapita();
                    // read store.area
                    storeArea = store.getArea();
                    // read store.name
                    storeName = store.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveItemAddress, storeArea);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveItemPrice, storeCapita);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveItemTitle, storeName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemReserveBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemReserveBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemReserveBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_reserve, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemReserveBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemReserveBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_reserve, null, false), bindingComponent);
    }
    @NonNull
    public static ItemReserveBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemReserveBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_reserve_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemReserveBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): store
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}