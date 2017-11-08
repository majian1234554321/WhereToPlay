package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityServiceBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_service, 1);
        sViewsWithIds.put(R.id.rv_service, 2);
        sViewsWithIds.put(R.id.ll_service_menu, 3);
        sViewsWithIds.put(R.id.ib_service_audio, 4);
        sViewsWithIds.put(R.id.et_service_input, 5);
        sViewsWithIds.put(R.id.ib_service_expression, 6);
        sViewsWithIds.put(R.id.ib_service_image, 7);
    }
    // views
    @NonNull
    public final android.widget.EditText etServiceInput;
    @NonNull
    public final android.widget.ImageButton ibServiceAudio;
    @NonNull
    public final android.widget.ImageButton ibServiceExpression;
    @NonNull
    public final android.widget.ImageButton ibServiceImage;
    @NonNull
    public final android.widget.LinearLayout llServiceMenu;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvService;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmService;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityServiceBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.etServiceInput = (android.widget.EditText) bindings[5];
        this.ibServiceAudio = (android.widget.ImageButton) bindings[4];
        this.ibServiceExpression = (android.widget.ImageButton) bindings[6];
        this.ibServiceImage = (android.widget.ImageButton) bindings[7];
        this.llServiceMenu = (android.widget.LinearLayout) bindings[3];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvService = (android.support.v7.widget.RecyclerView) bindings[2];
        this.tmService = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
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
    public static ActivityServiceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityServiceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityServiceBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_service, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityServiceBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityServiceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_service, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityServiceBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityServiceBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_service_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityServiceBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}