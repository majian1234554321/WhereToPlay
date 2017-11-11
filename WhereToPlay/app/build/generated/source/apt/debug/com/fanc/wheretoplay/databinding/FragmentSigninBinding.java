package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSigninBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_signin, 4);
        sViewsWithIds.put(R.id.relativeLayout, 5);
        sViewsWithIds.put(R.id.tv_signin_mobile, 6);
        sViewsWithIds.put(R.id.et_signin_mobile, 7);
        sViewsWithIds.put(R.id.tv_signin_password, 8);
        sViewsWithIds.put(R.id.et_signin_password, 9);
    }
    // views
    @NonNull
    public final android.widget.Button btnSignin;
    @NonNull
    public final android.widget.EditText etSigninMobile;
    @NonNull
    public final android.widget.EditText etSigninPassword;
    @NonNull
    public final android.widget.ImageView ivSignin;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout relativeLayout;
    @NonNull
    public final android.widget.TextView tvSigninForgetPwd;
    @NonNull
    public final android.widget.TextView tvSigninMobile;
    @NonNull
    public final android.widget.TextView tvSigninPassword;
    @NonNull
    public final android.widget.TextView tvSigninRegister;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.SignInFragment mDoClick;
    // values
    // listeners
    private OnClickListenerImpl mDoClickDoClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentSigninBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnSignin = (android.widget.Button) bindings[3];
        this.btnSignin.setTag(null);
        this.etSigninMobile = (android.widget.EditText) bindings[7];
        this.etSigninPassword = (android.widget.EditText) bindings[9];
        this.ivSignin = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relativeLayout = (android.widget.RelativeLayout) bindings[5];
        this.tvSigninForgetPwd = (android.widget.TextView) bindings[1];
        this.tvSigninForgetPwd.setTag(null);
        this.tvSigninMobile = (android.widget.TextView) bindings[6];
        this.tvSigninPassword = (android.widget.TextView) bindings[8];
        this.tvSigninRegister = (android.widget.TextView) bindings[2];
        this.tvSigninRegister.setTag(null);
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
            setDoClick((com.fanc.wheretoplay.fragment.SignInFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDoClick(@Nullable com.fanc.wheretoplay.fragment.SignInFragment DoClick) {
        this.mDoClick = DoClick;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.doClick);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.SignInFragment getDoClick() {
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
        com.fanc.wheretoplay.fragment.SignInFragment doClick = mDoClick;
        android.view.View.OnClickListener doClickDoClickAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (doClick != null) {
                    // read doClick::doClick
                    doClickDoClickAndroidViewViewOnClickListener = (((mDoClickDoClickAndroidViewViewOnClickListener == null) ? (mDoClickDoClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mDoClickDoClickAndroidViewViewOnClickListener).setValue(doClick));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.btnSignin.setOnClickListener(doClickDoClickAndroidViewViewOnClickListener);
            this.tvSigninForgetPwd.setOnClickListener(doClickDoClickAndroidViewViewOnClickListener);
            this.tvSigninRegister.setOnClickListener(doClickDoClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.SignInFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.SignInFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.doClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentSigninBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSigninBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentSigninBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_signin, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentSigninBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSigninBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_signin, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentSigninBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSigninBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_signin_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentSigninBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): doClick
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}