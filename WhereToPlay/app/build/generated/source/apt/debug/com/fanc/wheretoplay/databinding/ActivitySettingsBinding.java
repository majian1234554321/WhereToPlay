package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivitySettingsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_settings, 1);
        sViewsWithIds.put(R.id.iv_settings_set_pay_pwd, 2);
        sViewsWithIds.put(R.id.iv_settings_advice_feedback, 3);
        sViewsWithIds.put(R.id.iv_settings_system_notify, 4);
        sViewsWithIds.put(R.id.iv_settings_check_to_update, 5);
        sViewsWithIds.put(R.id.iv_settings_clear_cache, 6);
        sViewsWithIds.put(R.id.iv_settings_about_us, 7);
        sViewsWithIds.put(R.id.iv_change_environment, 8);
        sViewsWithIds.put(R.id.btn_settings_sign_out, 9);
    }
    // views
    @NonNull
    public final android.widget.Button btnSettingsSignOut;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivChangeEnvironment;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivSettingsAboutUs;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivSettingsAdviceFeedback;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivSettingsCheckToUpdate;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivSettingsClearCache;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivSettingsSetPayPwd;
    @NonNull
    public final com.fanc.wheretoplay.view.ItemView ivSettingsSystemNotify;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmSettings;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySettingsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnSettingsSignOut = (android.widget.Button) bindings[9];
        this.ivChangeEnvironment = (com.fanc.wheretoplay.view.ItemView) bindings[8];
        this.ivSettingsAboutUs = (com.fanc.wheretoplay.view.ItemView) bindings[7];
        this.ivSettingsAdviceFeedback = (com.fanc.wheretoplay.view.ItemView) bindings[3];
        this.ivSettingsCheckToUpdate = (com.fanc.wheretoplay.view.ItemView) bindings[5];
        this.ivSettingsClearCache = (com.fanc.wheretoplay.view.ItemView) bindings[6];
        this.ivSettingsSetPayPwd = (com.fanc.wheretoplay.view.ItemView) bindings[2];
        this.ivSettingsSystemNotify = (com.fanc.wheretoplay.view.ItemView) bindings[4];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tmSettings = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
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
    public static ActivitySettingsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySettingsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivitySettingsBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_settings, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivitySettingsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySettingsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_settings, null, false), bindingComponent);
    }
    @NonNull
    public static ActivitySettingsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySettingsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_settings_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivitySettingsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}