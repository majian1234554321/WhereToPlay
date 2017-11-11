package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentResetPwdBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_reset_pwd, 3);
        sViewsWithIds.put(R.id.rl_reset_pwd_user_input, 4);
        sViewsWithIds.put(R.id.tv_reset_pwd_mobile, 5);
        sViewsWithIds.put(R.id.et_reset_pwd_mobile, 6);
        sViewsWithIds.put(R.id.tv_reset_pwd_password, 7);
        sViewsWithIds.put(R.id.et_reset_pwd_password, 8);
        sViewsWithIds.put(R.id.tv_reset_pwd_verification, 9);
        sViewsWithIds.put(R.id.et_reset_pwd_verification, 10);
    }
    // views
    @NonNull
    public final android.widget.Button btnResetPwd;
    @NonNull
    public final android.widget.Button btnResetPwdVerification;
    @NonNull
    public final android.widget.EditText etResetPwdMobile;
    @NonNull
    public final android.widget.EditText etResetPwdPassword;
    @NonNull
    public final android.widget.EditText etResetPwdVerification;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout rlResetPwdUserInput;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmResetPwd;
    @NonNull
    public final android.widget.TextView tvResetPwdMobile;
    @NonNull
    public final android.widget.TextView tvResetPwdPassword;
    @NonNull
    public final android.widget.TextView tvResetPwdVerification;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.ResetPwdFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnBtnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentResetPwdBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.btnResetPwd = (android.widget.Button) bindings[2];
        this.btnResetPwd.setTag(null);
        this.btnResetPwdVerification = (android.widget.Button) bindings[1];
        this.btnResetPwdVerification.setTag(null);
        this.etResetPwdMobile = (android.widget.EditText) bindings[6];
        this.etResetPwdPassword = (android.widget.EditText) bindings[8];
        this.etResetPwdVerification = (android.widget.EditText) bindings[10];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlResetPwdUserInput = (android.widget.RelativeLayout) bindings[4];
        this.tmResetPwd = (com.fanc.wheretoplay.view.TopMenu) bindings[3];
        this.tvResetPwdMobile = (android.widget.TextView) bindings[5];
        this.tvResetPwdPassword = (android.widget.TextView) bindings[7];
        this.tvResetPwdVerification = (android.widget.TextView) bindings[9];
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
            setClick((com.fanc.wheretoplay.fragment.ResetPwdFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.ResetPwdFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.ResetPwdFragment getClick() {
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
        com.fanc.wheretoplay.fragment.ResetPwdFragment click = mClick;
        android.view.View.OnClickListener clickOnBtnClickAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::onBtnClick
                    clickOnBtnClickAndroidViewViewOnClickListener = (((mClickOnBtnClickAndroidViewViewOnClickListener == null) ? (mClickOnBtnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnBtnClickAndroidViewViewOnClickListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.btnResetPwd.setOnClickListener(clickOnBtnClickAndroidViewViewOnClickListener);
            this.btnResetPwdVerification.setOnClickListener(clickOnBtnClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.ResetPwdFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.ResetPwdFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onBtnClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentResetPwdBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_reset_pwd, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_reset_pwd, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentResetPwdBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentResetPwdBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_reset_pwd_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentResetPwdBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}