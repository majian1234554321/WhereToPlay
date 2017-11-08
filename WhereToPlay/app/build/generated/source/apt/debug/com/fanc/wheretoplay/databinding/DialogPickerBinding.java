package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DialogPickerBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_dialog_picker_title, 1);
        sViewsWithIds.put(R.id.dp_dialog_picker_date, 2);
        sViewsWithIds.put(R.id.ll_dialog_picker_city, 3);
        sViewsWithIds.put(R.id.wv_dialog_province, 4);
        sViewsWithIds.put(R.id.wv_dialog_city, 5);
        sViewsWithIds.put(R.id.btn_dialog_picker_confirm, 6);
        sViewsWithIds.put(R.id.btn_dialog_picker_cancel, 7);
    }
    // views
    @NonNull
    public final android.widget.Button btnDialogPickerCancel;
    @NonNull
    public final android.widget.Button btnDialogPickerConfirm;
    @NonNull
    public final com.fanc.wheretoplay.view.DatePicker dpDialogPickerDate;
    @NonNull
    public final android.widget.LinearLayout llDialogPickerCity;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvDialogPickerTitle;
    @NonNull
    public final com.fanc.wheretoplay.view.WheelView wvDialogCity;
    @NonNull
    public final com.fanc.wheretoplay.view.WheelView wvDialogProvince;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DialogPickerBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnDialogPickerCancel = (android.widget.Button) bindings[7];
        this.btnDialogPickerConfirm = (android.widget.Button) bindings[6];
        this.dpDialogPickerDate = (com.fanc.wheretoplay.view.DatePicker) bindings[2];
        this.llDialogPickerCity = (android.widget.LinearLayout) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDialogPickerTitle = (android.widget.TextView) bindings[1];
        this.wvDialogCity = (com.fanc.wheretoplay.view.WheelView) bindings[5];
        this.wvDialogProvince = (com.fanc.wheretoplay.view.WheelView) bindings[4];
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
    public static DialogPickerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogPickerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogPickerBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.dialog_picker, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DialogPickerBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogPickerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.dialog_picker, null, false), bindingComponent);
    }
    @NonNull
    public static DialogPickerBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogPickerBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_picker_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogPickerBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}