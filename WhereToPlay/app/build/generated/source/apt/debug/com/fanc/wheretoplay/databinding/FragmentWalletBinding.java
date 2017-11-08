package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentWalletBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_wallet, 5);
        sViewsWithIds.put(R.id.ll_pay_bill_balance, 6);
        sViewsWithIds.put(R.id.tv_wallet_balance, 7);
        sViewsWithIds.put(R.id.et_wallet_sum, 8);
        sViewsWithIds.put(R.id.rb_wallet_weixin, 9);
        sViewsWithIds.put(R.id.rb_wallet_ali, 10);
    }
    // views
    @NonNull
    public final android.widget.Button btnWalletRecharge;
    @NonNull
    public final android.widget.EditText etWalletSum;
    @NonNull
    public final android.widget.LinearLayout llPayBillBalance;
    @NonNull
    public final android.widget.LinearLayout llWalletAli;
    @NonNull
    public final android.widget.LinearLayout llWalletWeixin;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RadioButton rbWalletAli;
    @NonNull
    public final android.widget.RadioButton rbWalletWeixin;
    @NonNull
    public final android.widget.RadioGroup rgWallet;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmWallet;
    @NonNull
    public final android.widget.TextView tvWalletBalance;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.WalletFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    private OnCheckedChangeListenerImpl mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener;
    // Inverse Binding Event Handlers

    public FragmentWalletBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.btnWalletRecharge = (android.widget.Button) bindings[4];
        this.btnWalletRecharge.setTag(null);
        this.etWalletSum = (android.widget.EditText) bindings[8];
        this.llPayBillBalance = (android.widget.LinearLayout) bindings[6];
        this.llWalletAli = (android.widget.LinearLayout) bindings[2];
        this.llWalletAli.setTag(null);
        this.llWalletWeixin = (android.widget.LinearLayout) bindings[1];
        this.llWalletWeixin.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbWalletAli = (android.widget.RadioButton) bindings[10];
        this.rbWalletWeixin = (android.widget.RadioButton) bindings[9];
        this.rgWallet = (android.widget.RadioGroup) bindings[3];
        this.rgWallet.setTag(null);
        this.tmWallet = (com.fanc.wheretoplay.view.TopMenu) bindings[5];
        this.tvWalletBalance = (android.widget.TextView) bindings[7];
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
            setClick((com.fanc.wheretoplay.fragment.WalletFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.WalletFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.WalletFragment getClick() {
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
        com.fanc.wheretoplay.fragment.WalletFragment click = mClick;
        android.view.View.OnClickListener clickOnViewClickAndroidViewViewOnClickListener = null;
        android.widget.RadioGroup.OnCheckedChangeListener clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::onViewClick
                    clickOnViewClickAndroidViewViewOnClickListener = (((mClickOnViewClickAndroidViewViewOnClickListener == null) ? (mClickOnViewClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnViewClickAndroidViewViewOnClickListener).setValue(click));
                    // read click::onCheckedChanged
                    clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = (((mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener == null) ? (mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = new OnCheckedChangeListenerImpl()) : mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.btnWalletRecharge.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.llWalletAli.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.llWalletWeixin.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            android.databinding.adapters.RadioGroupBindingAdapter.setListeners(this.rgWallet, (android.widget.RadioGroup.OnCheckedChangeListener)clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener, (android.databinding.InverseBindingListener)null);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.WalletFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.WalletFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onViewClick(arg0);
        }
    }
    public static class OnCheckedChangeListenerImpl implements android.widget.RadioGroup.OnCheckedChangeListener{
        private com.fanc.wheretoplay.fragment.WalletFragment value;
        public OnCheckedChangeListenerImpl setValue(com.fanc.wheretoplay.fragment.WalletFragment value) {
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
    public static FragmentWalletBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentWalletBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentWalletBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_wallet, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentWalletBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentWalletBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_wallet, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentWalletBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentWalletBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_wallet_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentWalletBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}