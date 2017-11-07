package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ViewTopmenuBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.v_statubar, 1);
        sViewsWithIds.put(R.id.menu_iv_lefticon, 2);
        sViewsWithIds.put(R.id.menu_tv_lefttext, 3);
        sViewsWithIds.put(R.id.menu_tv_title, 4);
        sViewsWithIds.put(R.id.menu_tv_righttext, 5);
        sViewsWithIds.put(R.id.menu_iv_righticon, 6);
    }
    // views
    @NonNull
    public final android.widget.ImageView menuIvLefticon;
    @NonNull
    public final android.widget.ImageView menuIvRighticon;
    @NonNull
    public final android.widget.TextView menuTvLefttext;
    @NonNull
    public final android.widget.TextView menuTvRighttext;
    @NonNull
    public final android.widget.TextView menuTvTitle;
    @NonNull
    public final android.widget.RelativeLayout rlTopMenuBg;
    @NonNull
    public final android.view.View vStatubar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ViewTopmenuBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.menuIvLefticon = (android.widget.ImageView) bindings[2];
        this.menuIvRighticon = (android.widget.ImageView) bindings[6];
        this.menuTvLefttext = (android.widget.TextView) bindings[3];
        this.menuTvRighttext = (android.widget.TextView) bindings[5];
        this.menuTvTitle = (android.widget.TextView) bindings[4];
        this.rlTopMenuBg = (android.widget.RelativeLayout) bindings[0];
        this.rlTopMenuBg.setTag(null);
        this.vStatubar = (android.view.View) bindings[1];
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
    public static ViewTopmenuBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewTopmenuBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ViewTopmenuBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.view_topmenu, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ViewTopmenuBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewTopmenuBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.view_topmenu, null, false), bindingComponent);
    }
    @NonNull
    public static ViewTopmenuBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewTopmenuBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/view_topmenu_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ViewTopmenuBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}