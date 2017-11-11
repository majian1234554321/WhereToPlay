package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemHousenewsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_housenews_type, 1);
        sViewsWithIds.put(R.id.gl_1, 2);
        sViewsWithIds.put(R.id.tv_housenews_housename, 3);
        sViewsWithIds.put(R.id.gl_2, 4);
        sViewsWithIds.put(R.id.tv_housenews_lowest, 5);
        sViewsWithIds.put(R.id.gl_3, 6);
        sViewsWithIds.put(R.id.iv_housenews_use_detail, 7);
        sViewsWithIds.put(R.id.gl_4, 8);
        sViewsWithIds.put(R.id.iv_housenews_arrow, 9);
    }
    // views
    @NonNull
    public final android.support.constraint.Guideline gl1;
    @NonNull
    public final android.support.constraint.Guideline gl2;
    @NonNull
    public final android.support.constraint.Guideline gl3;
    @NonNull
    public final android.support.constraint.Guideline gl4;
    @NonNull
    public final android.widget.ImageView ivHousenewsArrow;
    @NonNull
    public final android.widget.ImageView ivHousenewsUseDetail;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvHousenewsHousename;
    @NonNull
    public final android.widget.TextView tvHousenewsLowest;
    @NonNull
    public final android.widget.TextView tvHousenewsType;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemHousenewsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.gl1 = (android.support.constraint.Guideline) bindings[2];
        this.gl2 = (android.support.constraint.Guideline) bindings[4];
        this.gl3 = (android.support.constraint.Guideline) bindings[6];
        this.gl4 = (android.support.constraint.Guideline) bindings[8];
        this.ivHousenewsArrow = (android.widget.ImageView) bindings[9];
        this.ivHousenewsUseDetail = (android.widget.ImageView) bindings[7];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvHousenewsHousename = (android.widget.TextView) bindings[3];
        this.tvHousenewsLowest = (android.widget.TextView) bindings[5];
        this.tvHousenewsType = (android.widget.TextView) bindings[1];
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
    public static ItemHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemHousenewsBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_housenews, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemHousenewsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_housenews, null, false), bindingComponent);
    }
    @NonNull
    public static ItemHousenewsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemHousenewsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_housenews_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemHousenewsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}