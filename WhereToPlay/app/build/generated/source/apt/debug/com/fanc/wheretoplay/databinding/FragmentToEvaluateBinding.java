package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentToEvaluateBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_to_evaluate, 1);
        sViewsWithIds.put(R.id.tv_to_evaluate_pay_success, 2);
        sViewsWithIds.put(R.id.tv_to_evaluate_pay_sum, 3);
        sViewsWithIds.put(R.id.tv_to_evaluate, 4);
        sViewsWithIds.put(R.id.btn_to_evaluate, 5);
    }
    // views
    @NonNull
    public final android.widget.Button btnToEvaluate;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmToEvaluate;
    @NonNull
    public final android.widget.TextView tvToEvaluate;
    @NonNull
    public final android.widget.TextView tvToEvaluatePaySuccess;
    @NonNull
    public final android.widget.TextView tvToEvaluatePaySum;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentToEvaluateBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btnToEvaluate = (android.widget.Button) bindings[5];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tmToEvaluate = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvToEvaluate = (android.widget.TextView) bindings[4];
        this.tvToEvaluatePaySuccess = (android.widget.TextView) bindings[2];
        this.tvToEvaluatePaySum = (android.widget.TextView) bindings[3];
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
    public static FragmentToEvaluateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentToEvaluateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentToEvaluateBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_to_evaluate, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentToEvaluateBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentToEvaluateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_to_evaluate, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentToEvaluateBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentToEvaluateBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_to_evaluate_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentToEvaluateBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}