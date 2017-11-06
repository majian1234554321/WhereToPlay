package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentWaiterPhotoBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_waiter_photo, 1);
        sViewsWithIds.put(R.id.iv_waiter_photo, 2);
        sViewsWithIds.put(R.id.tv_waiter_photo_name, 3);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivWaiterPhoto;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmWaiterPhoto;
    @NonNull
    public final android.widget.TextView tvWaiterPhotoName;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentWaiterPhotoBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivWaiterPhoto = (android.widget.ImageView) bindings[2];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tmWaiterPhoto = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvWaiterPhotoName = (android.widget.TextView) bindings[3];
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
    public static FragmentWaiterPhotoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentWaiterPhotoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentWaiterPhotoBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_waiter_photo, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentWaiterPhotoBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentWaiterPhotoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_waiter_photo, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentWaiterPhotoBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentWaiterPhotoBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_waiter_photo_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentWaiterPhotoBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}