package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDateBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_date, 1);
        sViewsWithIds.put(R.id.tv_date_month, 2);
        sViewsWithIds.put(R.id.tv_date_year, 3);
        sViewsWithIds.put(R.id.tv_date_explain, 4);
        sViewsWithIds.put(R.id.rv_date, 5);
        sViewsWithIds.put(R.id.gv_time, 6);
        sViewsWithIds.put(R.id.btn_date_confirm, 7);
    }
    // views
    @NonNull
    public final android.widget.Button btnDateConfirm;
    @NonNull
    public final android.widget.GridView gvTime;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvDate;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmDate;
    @NonNull
    public final android.widget.TextView tvDateExplain;
    @NonNull
    public final android.widget.TextView tvDateMonth;
    @NonNull
    public final android.widget.TextView tvDateYear;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDateBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnDateConfirm = (android.widget.Button) bindings[7];
        this.gvTime = (android.widget.GridView) bindings[6];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvDate = (android.support.v7.widget.RecyclerView) bindings[5];
        this.tmDate = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvDateExplain = (android.widget.TextView) bindings[4];
        this.tvDateMonth = (android.widget.TextView) bindings[2];
        this.tvDateYear = (android.widget.TextView) bindings[3];
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
    public static FragmentDateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentDateBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_date, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentDateBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_date, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentDateBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDateBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_date_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentDateBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}