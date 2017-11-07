package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentReferralsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_referral, 2);
        sViewsWithIds.put(R.id.iv_referral_logo, 3);
        sViewsWithIds.put(R.id.tv_referral_recreation_terrace, 4);
        sViewsWithIds.put(R.id.iv_referral_referral_ad, 5);
        sViewsWithIds.put(R.id.civ_referral_mine_header, 6);
        sViewsWithIds.put(R.id.tv_referral_mine_nickname, 7);
        sViewsWithIds.put(R.id.tv_referral_mine_code, 8);
    }
    // views
    @NonNull
    public final android.widget.Button btnReferralSheared;
    @NonNull
    public final com.fanc.wheretoplay.view.CircleImageView civReferralMineHeader;
    @NonNull
    public final android.widget.ImageView ivReferralLogo;
    @NonNull
    public final android.widget.ImageView ivReferralReferralAd;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmReferral;
    @NonNull
    public final android.widget.TextView tvReferralMineCode;
    @NonNull
    public final android.widget.TextView tvReferralMineNickname;
    @NonNull
    public final android.widget.TextView tvReferralRecreationTerrace;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.ReferralFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentReferralsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.btnReferralSheared = (android.widget.Button) bindings[1];
        this.btnReferralSheared.setTag(null);
        this.civReferralMineHeader = (com.fanc.wheretoplay.view.CircleImageView) bindings[6];
        this.ivReferralLogo = (android.widget.ImageView) bindings[3];
        this.ivReferralReferralAd = (android.widget.ImageView) bindings[5];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tmReferral = (com.fanc.wheretoplay.view.TopMenu) bindings[2];
        this.tvReferralMineCode = (android.widget.TextView) bindings[8];
        this.tvReferralMineNickname = (android.widget.TextView) bindings[7];
        this.tvReferralRecreationTerrace = (android.widget.TextView) bindings[4];
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
            setClick((com.fanc.wheretoplay.fragment.ReferralFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.ReferralFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.ReferralFragment getClick() {
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
        com.fanc.wheretoplay.fragment.ReferralFragment click = mClick;
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

            this.btnReferralSheared.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.ReferralFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.ReferralFragment value) {
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
    public static FragmentReferralsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReferralsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentReferralsBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_referrals, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentReferralsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReferralsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_referrals, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentReferralsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReferralsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_referrals_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentReferralsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}