package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRechargeDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_item_recharge_time, 3);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemRechargeSum;
    @NonNull
    public final android.widget.TextView tvItemRechargeTime;
    @NonNull
    public final android.widget.TextView tvItemRechargeTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.Recharge.RechargeDetail mDetail;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRechargeDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemRechargeSum = (android.widget.TextView) bindings[2];
        this.tvItemRechargeSum.setTag(null);
        this.tvItemRechargeTime = (android.widget.TextView) bindings[3];
        this.tvItemRechargeTitle = (android.widget.TextView) bindings[1];
        this.tvItemRechargeTitle.setTag(null);
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
            setDetail((com.fanc.wheretoplay.datamodel.Recharge.RechargeDetail) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDetail(@Nullable com.fanc.wheretoplay.datamodel.Recharge.RechargeDetail Detail) {
        this.mDetail = Detail;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.detail);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.Recharge.RechargeDetail getDetail() {
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
        java.lang.String charDetailBalance = null;
        java.lang.String detailBalanceTypeEqualsJavaLangString1TvItemRechargeTitleAndroidStringRechargeWayAliTvItemRechargeTitleAndroidStringRechargeWayWeixin = null;
        boolean detailBalanceTypeEqualsJavaLangString1 = false;
        java.lang.String detailBalanceType = null;
        com.fanc.wheretoplay.datamodel.Recharge.RechargeDetail detail = mDetail;
        java.lang.String detailBalance = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (detail != null) {
                    // read detail.balance_type
                    detailBalanceType = detail.getBalance_type();
                    // read detail.balance
                    detailBalance = detail.getBalance();
                }


                if (detailBalanceType != null) {
                    // read detail.balance_type.equals("1")
                    detailBalanceTypeEqualsJavaLangString1 = detailBalanceType.equals("1");
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(detailBalanceTypeEqualsJavaLangString1) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }
                // read ('+') + (detail.balance)
                charDetailBalance = ('+') + (detailBalance);


                // read detail.balance_type.equals("1") ? @android:string/recharge_way_ali : @android:string/recharge_way_weixin
                detailBalanceTypeEqualsJavaLangString1TvItemRechargeTitleAndroidStringRechargeWayAliTvItemRechargeTitleAndroidStringRechargeWayWeixin = ((detailBalanceTypeEqualsJavaLangString1) ? (tvItemRechargeTitle.getResources().getString(R.string.recharge_way_ali)) : (tvItemRechargeTitle.getResources().getString(R.string.recharge_way_weixin)));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRechargeSum, charDetailBalance);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRechargeTitle, detailBalanceTypeEqualsJavaLangString1TvItemRechargeTitleAndroidStringRechargeWayAliTvItemRechargeTitleAndroidStringRechargeWayWeixin);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemRechargeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRechargeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemRechargeDetailBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_recharge_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemRechargeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRechargeDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_recharge_detail, null, false), bindingComponent);
    }
    @NonNull
    public static ItemRechargeDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRechargeDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_recharge_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemRechargeDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): detail
        flag 1 (0x2L): null
        flag 2 (0x3L): detail.balance_type.equals("1") ? @android:string/recharge_way_ali : @android:string/recharge_way_weixin
        flag 3 (0x4L): detail.balance_type.equals("1") ? @android:string/recharge_way_ali : @android:string/recharge_way_weixin
    flag mapping end*/
    //end
}