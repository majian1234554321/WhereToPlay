package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemConsumeDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_item_consume_subscription, 6);
        sViewsWithIds.put(R.id.tv_item_consume_subscription, 7);
        sViewsWithIds.put(R.id.tv_item_consume_subscription_time, 8);
        sViewsWithIds.put(R.id.ll_item_consume_order, 9);
        sViewsWithIds.put(R.id.tv_item_consume_order, 10);
        sViewsWithIds.put(R.id.tv_item_consume_order_time, 11);
        sViewsWithIds.put(R.id.ll_item_consume_discount, 12);
        sViewsWithIds.put(R.id.tv_item_consume_discount, 13);
        sViewsWithIds.put(R.id.tv_item_consume_total, 14);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout llItemConsumeDiscount;
    @NonNull
    public final android.widget.LinearLayout llItemConsumeOrder;
    @NonNull
    public final android.widget.LinearLayout llItemConsumeSubscription;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemConsumeDiscount;
    @NonNull
    public final android.widget.TextView tvItemConsumeDiscountSum;
    @NonNull
    public final android.widget.TextView tvItemConsumeOrder;
    @NonNull
    public final android.widget.TextView tvItemConsumeOrderSum;
    @NonNull
    public final android.widget.TextView tvItemConsumeOrderTime;
    @NonNull
    public final android.widget.TextView tvItemConsumeSubscription;
    @NonNull
    public final android.widget.TextView tvItemConsumeSubscriptionSum;
    @NonNull
    public final android.widget.TextView tvItemConsumeSubscriptionTime;
    @NonNull
    public final android.widget.TextView tvItemConsumeTitle;
    @NonNull
    public final android.widget.TextView tvItemConsumeTotal;
    @NonNull
    public final android.widget.TextView tvItemConsumeTotalSum;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.Consume.ConsumeDetail mDetail;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemConsumeDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.llItemConsumeDiscount = (android.widget.LinearLayout) bindings[12];
        this.llItemConsumeOrder = (android.widget.LinearLayout) bindings[9];
        this.llItemConsumeSubscription = (android.widget.LinearLayout) bindings[6];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemConsumeDiscount = (android.widget.TextView) bindings[13];
        this.tvItemConsumeDiscountSum = (android.widget.TextView) bindings[4];
        this.tvItemConsumeDiscountSum.setTag(null);
        this.tvItemConsumeOrder = (android.widget.TextView) bindings[10];
        this.tvItemConsumeOrderSum = (android.widget.TextView) bindings[3];
        this.tvItemConsumeOrderSum.setTag(null);
        this.tvItemConsumeOrderTime = (android.widget.TextView) bindings[11];
        this.tvItemConsumeSubscription = (android.widget.TextView) bindings[7];
        this.tvItemConsumeSubscriptionSum = (android.widget.TextView) bindings[2];
        this.tvItemConsumeSubscriptionSum.setTag(null);
        this.tvItemConsumeSubscriptionTime = (android.widget.TextView) bindings[8];
        this.tvItemConsumeTitle = (android.widget.TextView) bindings[1];
        this.tvItemConsumeTitle.setTag(null);
        this.tvItemConsumeTotal = (android.widget.TextView) bindings[14];
        this.tvItemConsumeTotalSum = (android.widget.TextView) bindings[5];
        this.tvItemConsumeTotalSum.setTag(null);
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
        if (BR.detail == variableId) {
            setDetail((com.fanc.wheretoplay.datamodel.Consume.ConsumeDetail) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDetail(@Nullable com.fanc.wheretoplay.datamodel.Consume.ConsumeDetail Detail) {
        this.mDetail = Detail;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.detail);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.Consume.ConsumeDetail getDetail() {
        return mDetail;
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
        java.lang.String detailPrepay = null;
        java.lang.String detailAccount = null;
        java.lang.String detailDiscount = null;
        com.fanc.wheretoplay.datamodel.Consume.ConsumeDetail detail = mDetail;
        java.lang.String detailName = null;
        double detailTotal = 0.0;
        java.lang.String stringValueOfDetailTotal = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (detail != null) {
                    // read detail.prepay
                    detailPrepay = detail.getPrepay();
                    // read detail.account
                    detailAccount = detail.getAccount();
                    // read detail.discount
                    detailDiscount = detail.getDiscount();
                    // read detail.name
                    detailName = detail.getName();
                    // read detail.total
                    detailTotal = detail.getTotal();
                }


                // read String.valueOf(detail.total)
                stringValueOfDetailTotal = java.lang.String.valueOf(detailTotal);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemConsumeDiscountSum, detailDiscount);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemConsumeOrderSum, detailAccount);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemConsumeSubscriptionSum, detailPrepay);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemConsumeTitle, detailName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemConsumeTotalSum, stringValueOfDetailTotal);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemConsumeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemConsumeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemConsumeDetailBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_consume_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemConsumeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemConsumeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_consume_detail, null, false), bindingComponent);
    }
    @NonNull
    public static ItemConsumeDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemConsumeDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_consume_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemConsumeDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): detail
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}