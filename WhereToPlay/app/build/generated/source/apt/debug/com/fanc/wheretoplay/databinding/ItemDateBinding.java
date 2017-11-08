package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemDateBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    public final android.widget.LinearLayout llDate;
    @NonNull
    public final android.widget.TextView tvDate;
    @NonNull
    public final android.widget.TextView tvWeek;
    // variables
    @Nullable
    private java.util.Date mDate;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemDateBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.llDate = (android.widget.LinearLayout) bindings[0];
        this.llDate.setTag(null);
        this.tvDate = (android.widget.TextView) bindings[1];
        this.tvDate.setTag(null);
        this.tvWeek = (android.widget.TextView) bindings[2];
        this.tvWeek.setTag(null);
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
        if (BR.date == variableId) {
            setDate((java.util.Date) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDate(@Nullable java.util.Date Date) {
        this.mDate = Date;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.date);
        super.requestRebind();
    }
    @Nullable
    public java.util.Date getDate() {
        return mDate;
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
        java.util.Date date = mDate;
        int dateFormatUtilGetCustomDayDate = 0;
        java.lang.String dateFormatUtilGetCustomDayOfWeekDate = null;
        java.lang.String stringValueOfDateFormatUtilGetCustomDayDate = null;

        if ((dirtyFlags & 0x3L) != 0) {



                // read DateFormatUtil.getCustomDay(date)
                dateFormatUtilGetCustomDayDate = com.fanc.wheretoplay.util.DateFormatUtil.getCustomDay(date);
                // read DateFormatUtil.getCustomDayOfWeek(date)
                dateFormatUtilGetCustomDayOfWeekDate = com.fanc.wheretoplay.util.DateFormatUtil.getCustomDayOfWeek(date);


                // read String.valueOf(DateFormatUtil.getCustomDay(date))
                stringValueOfDateFormatUtilGetCustomDayDate = java.lang.String.valueOf(dateFormatUtilGetCustomDayDate);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, stringValueOfDateFormatUtilGetCustomDayDate);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvWeek, dateFormatUtilGetCustomDayOfWeekDate);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemDateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemDateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemDateBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_date, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemDateBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemDateBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_date, null, false), bindingComponent);
    }
    @NonNull
    public static ItemDateBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemDateBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_date_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemDateBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): date
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}