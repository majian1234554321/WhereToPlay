package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DialogModifyGenderBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rb_dialog_male, 2);
        sViewsWithIds.put(R.id.rb_dialog_female, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RadioButton rbDialogFemale;
    @NonNull
    public final android.widget.RadioButton rbDialogMale;
    @NonNull
    public final android.widget.RadioGroup rgDialogGender;
    // variables
    @Nullable
    private com.fanc.wheretoplay.view.ModifyGenderDialog mSelect;
    // values
    // listeners
    private OnCheckedChangeListenerImpl mSelectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener;
    // Inverse Binding Event Handlers

    public DialogModifyGenderBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbDialogFemale = (android.widget.RadioButton) bindings[3];
        this.rbDialogMale = (android.widget.RadioButton) bindings[2];
        this.rgDialogGender = (android.widget.RadioGroup) bindings[1];
        this.rgDialogGender.setTag(null);
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
        if (BR.select == variableId) {
            setSelect((com.fanc.wheretoplay.view.ModifyGenderDialog) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSelect(@Nullable com.fanc.wheretoplay.view.ModifyGenderDialog Select) {
        this.mSelect = Select;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.select);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.view.ModifyGenderDialog getSelect() {
        return mSelect;
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
        android.widget.RadioGroup.OnCheckedChangeListener selectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = null;
        com.fanc.wheretoplay.view.ModifyGenderDialog select = mSelect;

        if ((dirtyFlags & 0x3L) != 0) {



                if (select != null) {
                    // read select::onCheckedChanged
                    selectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = (((mSelectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener == null) ? (mSelectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = new OnCheckedChangeListenerImpl()) : mSelectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener).setValue(select));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.RadioGroupBindingAdapter.setListeners(this.rgDialogGender, (android.widget.RadioGroup.OnCheckedChangeListener)selectOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener, (android.databinding.InverseBindingListener)null);
        }
    }
    // Listener Stub Implementations
    public static class OnCheckedChangeListenerImpl implements android.widget.RadioGroup.OnCheckedChangeListener{
        private com.fanc.wheretoplay.view.ModifyGenderDialog value;
        public OnCheckedChangeListenerImpl setValue(com.fanc.wheretoplay.view.ModifyGenderDialog value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onCheckedChanged(android.widget.RadioGroup arg0, int arg1) {
            this.value.onCheckedChanged(arg0, arg1);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static DialogModifyGenderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogModifyGenderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogModifyGenderBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.dialog_modify_gender, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DialogModifyGenderBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogModifyGenderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.dialog_modify_gender, null, false), bindingComponent);
    }
    @NonNull
    public static DialogModifyGenderBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogModifyGenderBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_modify_gender_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogModifyGenderBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): select
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}