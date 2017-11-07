package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAlterCityBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_alter_city, 2);
        sViewsWithIds.put(R.id.lv_alter_city_all, 3);
        sViewsWithIds.put(R.id.tv_alter_city_selected_letter, 4);
        sViewsWithIds.put(R.id.slb_alter_city, 5);
        sViewsWithIds.put(R.id.lv_alter_city_result, 6);
        sViewsWithIds.put(R.id.tv_alter_city_search_error, 7);
        sViewsWithIds.put(R.id.tv_alter_city_loading, 8);
    }
    // views
    @NonNull
    public final android.widget.EditText etAlterCity;
    @NonNull
    public final android.widget.ListView lvAlterCityAll;
    @NonNull
    public final android.widget.ListView lvAlterCityResult;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.SideLetterBar slbAlterCity;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmAlterCity;
    @NonNull
    public final android.widget.TextView tvAlterCityLoading;
    @NonNull
    public final android.widget.TextView tvAlterCitySearchError;
    @NonNull
    public final android.widget.TextView tvAlterCitySelectedLetter;
    // variables
    @Nullable
    private com.fanc.wheretoplay.activity.AlterCityActivity mEditText;
    // values
    // listeners
    private AfterTextChangedImpl mEditTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged;
    // Inverse Binding Event Handlers

    public ActivityAlterCityBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.etAlterCity = (android.widget.EditText) bindings[1];
        this.etAlterCity.setTag(null);
        this.lvAlterCityAll = (android.widget.ListView) bindings[3];
        this.lvAlterCityResult = (android.widget.ListView) bindings[6];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.slbAlterCity = (com.fanc.wheretoplay.view.SideLetterBar) bindings[5];
        this.tmAlterCity = (com.fanc.wheretoplay.view.TopMenu) bindings[2];
        this.tvAlterCityLoading = (android.widget.TextView) bindings[8];
        this.tvAlterCitySearchError = (android.widget.TextView) bindings[7];
        this.tvAlterCitySelectedLetter = (android.widget.TextView) bindings[4];
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
        if (BR.editText == variableId) {
            setEditText((com.fanc.wheretoplay.activity.AlterCityActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setEditText(@Nullable com.fanc.wheretoplay.activity.AlterCityActivity EditText) {
        this.mEditText = EditText;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.editText);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.activity.AlterCityActivity getEditText() {
        return mEditText;
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
        android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged editTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged = null;
        com.fanc.wheretoplay.activity.AlterCityActivity editText = mEditText;

        if ((dirtyFlags & 0x3L) != 0) {



                if (editText != null) {
                    // read editText::afterTextChanged
                    editTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged = (((mEditTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged == null) ? (mEditTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged = new AfterTextChangedImpl()) : mEditTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged).setValue(editText));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etAlterCity, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)editTextAfterTextChangedAndroidDatabindingAdaptersTextViewBindingAdapterAfterTextChanged, (android.databinding.InverseBindingListener)null);
        }
    }
    // Listener Stub Implementations
    public static class AfterTextChangedImpl implements android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged{
        private com.fanc.wheretoplay.activity.AlterCityActivity value;
        public AfterTextChangedImpl setValue(com.fanc.wheretoplay.activity.AlterCityActivity value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void afterTextChanged(android.text.Editable arg0) {
            this.value.afterTextChanged(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityAlterCityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityAlterCityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityAlterCityBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_alter_city, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityAlterCityBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityAlterCityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_alter_city, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityAlterCityBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityAlterCityBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_alter_city_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityAlterCityBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): editText
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}