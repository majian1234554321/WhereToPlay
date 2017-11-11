package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentRegisterBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_register, 4);
        sViewsWithIds.put(R.id.rl_register_user_input, 5);
        sViewsWithIds.put(R.id.tv_register_area, 6);
        sViewsWithIds.put(R.id.tv_register_mobile, 7);
        sViewsWithIds.put(R.id.et_register_mobile, 8);
        sViewsWithIds.put(R.id.tv_register_nickname, 9);
        sViewsWithIds.put(R.id.et_register_nickname, 10);
        sViewsWithIds.put(R.id.tv_register_password, 11);
        sViewsWithIds.put(R.id.et_register_password, 12);
        sViewsWithIds.put(R.id.tv_register_verification, 13);
        sViewsWithIds.put(R.id.et_register_verification, 14);
        sViewsWithIds.put(R.id.tv_redeem_code, 15);
        sViewsWithIds.put(R.id.et_redeem_code, 16);
        sViewsWithIds.put(R.id.tv_agreement_left, 17);
        sViewsWithIds.put(R.id.tv_agreement_right, 18);
    }
    // views
    @NonNull
    public final android.widget.Button btnRegister;
    @NonNull
    public final android.widget.Button btnRegisterVerification;
    @NonNull
    public final android.widget.EditText etRedeemCode;
    @NonNull
    public final android.widget.EditText etRegisterMobile;
    @NonNull
    public final android.widget.EditText etRegisterNickname;
    @NonNull
    public final android.widget.EditText etRegisterPassword;
    @NonNull
    public final android.widget.EditText etRegisterVerification;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout rlRegisterUserInput;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmRegister;
    @NonNull
    public final android.widget.TextView tvAgreementLeft;
    @NonNull
    public final android.widget.TextView tvAgreementRight;
    @NonNull
    public final android.widget.TextView tvRedeemCode;
    @NonNull
    public final android.widget.TextView tvRegisterArea;
    @NonNull
    public final android.widget.TextView tvRegisterCity;
    @NonNull
    public final android.widget.TextView tvRegisterMobile;
    @NonNull
    public final android.widget.TextView tvRegisterNickname;
    @NonNull
    public final android.widget.TextView tvRegisterPassword;
    @NonNull
    public final android.widget.TextView tvRegisterVerification;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.RegisterFragment mDoClick;
    // values
    // listeners
    private OnClickListenerImpl mDoClickOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentRegisterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds);
        this.btnRegister = (android.widget.Button) bindings[3];
        this.btnRegister.setTag(null);
        this.btnRegisterVerification = (android.widget.Button) bindings[2];
        this.btnRegisterVerification.setTag(null);
        this.etRedeemCode = (android.widget.EditText) bindings[16];
        this.etRegisterMobile = (android.widget.EditText) bindings[8];
        this.etRegisterNickname = (android.widget.EditText) bindings[10];
        this.etRegisterPassword = (android.widget.EditText) bindings[12];
        this.etRegisterVerification = (android.widget.EditText) bindings[14];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlRegisterUserInput = (android.widget.RelativeLayout) bindings[5];
        this.tmRegister = (com.fanc.wheretoplay.view.TopMenu) bindings[4];
        this.tvAgreementLeft = (android.widget.TextView) bindings[17];
        this.tvAgreementRight = (android.widget.TextView) bindings[18];
        this.tvRedeemCode = (android.widget.TextView) bindings[15];
        this.tvRegisterArea = (android.widget.TextView) bindings[6];
        this.tvRegisterCity = (android.widget.TextView) bindings[1];
        this.tvRegisterCity.setTag(null);
        this.tvRegisterMobile = (android.widget.TextView) bindings[7];
        this.tvRegisterNickname = (android.widget.TextView) bindings[9];
        this.tvRegisterPassword = (android.widget.TextView) bindings[11];
        this.tvRegisterVerification = (android.widget.TextView) bindings[13];
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
        if (BR.doClick == variableId) {
            setDoClick((com.fanc.wheretoplay.fragment.RegisterFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDoClick(@Nullable com.fanc.wheretoplay.fragment.RegisterFragment DoClick) {
        this.mDoClick = DoClick;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.doClick);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.RegisterFragment getDoClick() {
        return mDoClick;
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
        android.view.View.OnClickListener doClickOnClickAndroidViewViewOnClickListener = null;
        com.fanc.wheretoplay.fragment.RegisterFragment doClick = mDoClick;

        if ((dirtyFlags & 0x3L) != 0) {



                if (doClick != null) {
                    // read doClick::onClick
                    doClickOnClickAndroidViewViewOnClickListener = (((mDoClickOnClickAndroidViewViewOnClickListener == null) ? (mDoClickOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mDoClickOnClickAndroidViewViewOnClickListener).setValue(doClick));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.btnRegister.setOnClickListener(doClickOnClickAndroidViewViewOnClickListener);
            this.btnRegisterVerification.setOnClickListener(doClickOnClickAndroidViewViewOnClickListener);
            this.tvRegisterCity.setOnClickListener(doClickOnClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.RegisterFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.RegisterFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentRegisterBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_register, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_register, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentRegisterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentRegisterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_register_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentRegisterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): doClick
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}