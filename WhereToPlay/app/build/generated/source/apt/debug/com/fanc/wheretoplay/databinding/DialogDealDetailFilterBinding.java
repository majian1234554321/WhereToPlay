package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DialogDealDetailFilterBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_deal_detail_filter_store_name, 1);
        sViewsWithIds.put(R.id.et_dialog_deal_detail_filter, 2);
        sViewsWithIds.put(R.id.tv_dialog_deal_detail_consume_date, 3);
        sViewsWithIds.put(R.id.iv_dialog_deal_detail_filter, 4);
        sViewsWithIds.put(R.id.dp_dialog_filter_date, 5);
        sViewsWithIds.put(R.id.btn_dialog_filter_confirm, 6);
        sViewsWithIds.put(R.id.btn_dialog_filter_cancel, 7);
    }
    // views
    @NonNull
    public final android.widget.Button btnDialogFilterCancel;
    @NonNull
    public final android.widget.Button btnDialogFilterConfirm;
    @NonNull
    public final com.fanc.wheretoplay.view.DatePicker dpDialogFilterDate;
    @NonNull
    public final android.widget.EditText etDialogDealDetailFilter;
    @NonNull
    public final android.widget.ImageView ivDialogDealDetailFilter;
    @NonNull
    public final android.widget.LinearLayout llDealDetailFilterStoreName;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvDialogDealDetailConsumeDate;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DialogDealDetailFilterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnDialogFilterCancel = (android.widget.Button) bindings[7];
        this.btnDialogFilterConfirm = (android.widget.Button) bindings[6];
        this.dpDialogFilterDate = (com.fanc.wheretoplay.view.DatePicker) bindings[5];
        this.etDialogDealDetailFilter = (android.widget.EditText) bindings[2];
        this.ivDialogDealDetailFilter = (android.widget.ImageView) bindings[4];
        this.llDealDetailFilterStoreName = (android.widget.LinearLayout) bindings[1];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDialogDealDetailConsumeDate = (android.widget.TextView) bindings[3];
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
    public static DialogDealDetailFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogDealDetailFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogDealDetailFilterBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.dialog_deal_detail_filter, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DialogDealDetailFilterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogDealDetailFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.dialog_deal_detail_filter, null, false), bindingComponent);
    }
    @NonNull
    public static DialogDealDetailFilterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogDealDetailFilterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_deal_detail_filter_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogDealDetailFilterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}