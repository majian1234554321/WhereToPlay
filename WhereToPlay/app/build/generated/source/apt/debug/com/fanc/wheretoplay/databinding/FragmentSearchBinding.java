package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSearchBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.et_search, 3);
        sViewsWithIds.put(R.id.ll_search_before, 4);
        sViewsWithIds.put(R.id.fl_search_referral, 5);
        sViewsWithIds.put(R.id.tv_search_history, 6);
        sViewsWithIds.put(R.id.rv_search_history, 7);
        sViewsWithIds.put(R.id.lv_search_result, 8);
    }
    // views
    @NonNull
    public final android.widget.EditText etSearch;
    @NonNull
    public final com.fanc.wheretoplay.view.FlowLayout flSearchReferral;
    @NonNull
    public final android.widget.ImageView ivSearchBack;
    @NonNull
    public final android.widget.LinearLayout llSearchBefore;
    @NonNull
    public final android.widget.ListView lvSearchResult;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvSearchHistory;
    @NonNull
    public final android.widget.TextView tvSearch;
    @NonNull
    public final android.widget.TextView tvSearchHistory;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.SearchFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentSearchBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.etSearch = (android.widget.EditText) bindings[3];
        this.flSearchReferral = (com.fanc.wheretoplay.view.FlowLayout) bindings[5];
        this.ivSearchBack = (android.widget.ImageView) bindings[1];
        this.ivSearchBack.setTag(null);
        this.llSearchBefore = (android.widget.LinearLayout) bindings[4];
        this.lvSearchResult = (android.widget.ListView) bindings[8];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvSearchHistory = (android.support.v7.widget.RecyclerView) bindings[7];
        this.tvSearch = (android.widget.TextView) bindings[2];
        this.tvSearch.setTag(null);
        this.tvSearchHistory = (android.widget.TextView) bindings[6];
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
        if (BR.click == variableId) {
            setClick((com.fanc.wheretoplay.fragment.SearchFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.SearchFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.SearchFragment getClick() {
        return mClick;
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
        android.view.View.OnClickListener clickOnClickAndroidViewViewOnClickListener = null;
        com.fanc.wheretoplay.fragment.SearchFragment click = mClick;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::onClick
                    clickOnClickAndroidViewViewOnClickListener = (((mClickOnClickAndroidViewViewOnClickListener == null) ? (mClickOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnClickAndroidViewViewOnClickListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.ivSearchBack.setOnClickListener(clickOnClickAndroidViewViewOnClickListener);
            this.tvSearch.setOnClickListener(clickOnClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.SearchFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.SearchFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentSearchBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSearchBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentSearchBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_search, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentSearchBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSearchBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_search, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentSearchBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSearchBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_search_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentSearchBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}