package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemHousetypeBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_housetype, 1);
        sViewsWithIds.put(R.id.tv_housetype_name, 2);
        sViewsWithIds.put(R.id.tv_housetype_price, 3);
        sViewsWithIds.put(R.id.tv_housetype_prive_qi, 4);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivHousetype;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvHousetypeName;
    @NonNull
    public final android.widget.TextView tvHousetypePrice;
    @NonNull
    public final android.widget.TextView tvHousetypePriveQi;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.StoreList.Store mStore;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemHousetypeBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivHousetype = (android.widget.ImageView) bindings[1];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvHousetypeName = (android.widget.TextView) bindings[2];
        this.tvHousetypePrice = (android.widget.TextView) bindings[3];
        this.tvHousetypePriveQi = (android.widget.TextView) bindings[4];
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemHousetypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemHousetypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemHousetypeBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_housetype, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemHousetypeBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemHousetypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_housetype, null, false), bindingComponent);
    }
    @NonNull
    public static ItemHousetypeBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemHousetypeBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_housetype_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemHousetypeBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): store
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}