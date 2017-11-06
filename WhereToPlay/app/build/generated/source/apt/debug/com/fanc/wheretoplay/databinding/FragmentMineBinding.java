package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMineBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_mine, 10);
        sViewsWithIds.put(R.id.civ_mine, 11);
        sViewsWithIds.put(R.id.tv_mine_nickname, 12);
        sViewsWithIds.put(R.id.tv_mine_mobile, 13);
        sViewsWithIds.put(R.id.tv_mine_integral, 14);
        sViewsWithIds.put(R.id.tv_mine_integral_1, 15);
    }
    // views
    @NonNull
    public final com.fanc.wheretoplay.view.CircleImageView civMine;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineCollection;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineDiscountCoupon;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineDrive;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineIntegral;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineMessage;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineOrder;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineReferral;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivMineWallet;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout rlMineInfo;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmMine;
    @NonNull
    public final android.widget.TextView tvMineIntegral;
    @NonNull
    public final android.widget.TextView tvMineIntegral1;
    @NonNull
    public final android.widget.TextView tvMineMobile;
    @NonNull
    public final android.widget.TextView tvMineNickname;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.MineFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentMineBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds);
        this.civMine = (com.fanc.wheretoplay.view.CircleImageView) bindings[11];
        this.ivMineCollection = (com.fanc.wheretoplay.view.ItemView) bindings[7];
        this.ivMineCollection.setTag(null);
        this.ivMineDiscountCoupon = (com.fanc.wheretoplay.view.ItemView) bindings[4];
        this.ivMineDiscountCoupon.setTag(null);
        this.ivMineDrive = (com.fanc.wheretoplay.view.ItemView) bindings[9];
        this.ivMineDrive.setTag(null);
        this.ivMineIntegral = (com.fanc.wheretoplay.view.ItemView) bindings[5];
        this.ivMineIntegral.setTag(null);
        this.ivMineMessage = (com.fanc.wheretoplay.view.ItemView) bindings[2];
        this.ivMineMessage.setTag(null);
        this.ivMineOrder = (com.fanc.wheretoplay.view.ItemView) bindings[6];
        this.ivMineOrder.setTag(null);
        this.ivMineReferral = (com.fanc.wheretoplay.view.ItemView) bindings[8];
        this.ivMineReferral.setTag(null);
        this.ivMineWallet = (com.fanc.wheretoplay.view.ItemView) bindings[3];
        this.ivMineWallet.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlMineInfo = (android.widget.RelativeLayout) bindings[1];
        this.rlMineInfo.setTag(null);
        this.tmMine = (com.fanc.wheretoplay.view.TopMenu) bindings[10];
        this.tvMineIntegral = (android.widget.TextView) bindings[14];
        this.tvMineIntegral1 = (android.widget.TextView) bindings[15];
        this.tvMineMobile = (android.widget.TextView) bindings[13];
        this.tvMineNickname = (android.widget.TextView) bindings[12];
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
            setClick((com.fanc.wheretoplay.fragment.MineFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.MineFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.MineFragment getClick() {
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
        com.fanc.wheretoplay.fragment.MineFragment click = mClick;
        android.view.View.OnClickListener clickOnViewClickAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::onViewClick
                    clickOnViewClickAndroidViewViewOnClickListener = (((mClickOnViewClickAndroidViewViewOnClickListener == null) ? (mClickOnViewClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnViewClickAndroidViewViewOnClickListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.ivMineCollection.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineDiscountCoupon.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineDrive.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineIntegral.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineMessage.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineOrder.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineReferral.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivMineWallet.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.rlMineInfo.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.MineFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.MineFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onViewClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentMineBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMineBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMineBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_mine, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentMineBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMineBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_mine, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentMineBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMineBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_mine_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMineBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}