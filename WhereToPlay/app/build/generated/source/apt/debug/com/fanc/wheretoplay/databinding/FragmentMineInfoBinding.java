package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMineInfoBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_mine_info, 6);
        sViewsWithIds.put(R.id.civ_mine_info_photo, 7);
        sViewsWithIds.put(R.id.et_mine_info_nickname, 8);
        sViewsWithIds.put(R.id.iv_mine_info_gender, 9);
        sViewsWithIds.put(R.id.tv_mine_info_gender, 10);
        sViewsWithIds.put(R.id.iv_mine_info_birthday, 11);
        sViewsWithIds.put(R.id.tv_mine_infor_birthday, 12);
        sViewsWithIds.put(R.id.iv_mine_info_location, 13);
        sViewsWithIds.put(R.id.tv_mine_info_location, 14);
        sViewsWithIds.put(R.id.et_mine_info_mobile, 15);
        sViewsWithIds.put(R.id.et_mine_info_sign, 16);
    }
    // views
    @NonNull
    public final android.widget.Button btnMineInfoConfirm;
    @NonNull
    public final com.fanc.wheretoplay.view.CircleImageView civMineInfoPhoto;
    @NonNull
    public final android.widget.EditText etMineInfoMobile;
    @NonNull
    public final android.widget.EditText etMineInfoNickname;
    @NonNull
    public final android.widget.EditText etMineInfoSign;
    @NonNull
    public final android.widget.ImageView ivMineInfoBirthday;
    @NonNull
    public final android.widget.ImageView ivMineInfoGender;
    @NonNull
    public final android.widget.ImageView ivMineInfoLocation;
    @NonNull
    public final android.widget.LinearLayout llMineInfoPhoto;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout rlMineInfoBirthday;
    @NonNull
    public final android.widget.RelativeLayout rlMineInfoGender;
    @NonNull
    public final android.widget.RelativeLayout rlMineInfoLocation;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmMineInfo;
    @NonNull
    public final android.widget.TextView tvMineInfoGender;
    @NonNull
    public final android.widget.TextView tvMineInfoLocation;
    @NonNull
    public final android.widget.TextView tvMineInforBirthday;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.MineInfoFragment mClick;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentMineInfoBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.btnMineInfoConfirm = (android.widget.Button) bindings[5];
        this.btnMineInfoConfirm.setTag(null);
        this.civMineInfoPhoto = (com.fanc.wheretoplay.view.CircleImageView) bindings[7];
        this.etMineInfoMobile = (android.widget.EditText) bindings[15];
        this.etMineInfoNickname = (android.widget.EditText) bindings[8];
        this.etMineInfoSign = (android.widget.EditText) bindings[16];
        this.ivMineInfoBirthday = (android.widget.ImageView) bindings[11];
        this.ivMineInfoGender = (android.widget.ImageView) bindings[9];
        this.ivMineInfoLocation = (android.widget.ImageView) bindings[13];
        this.llMineInfoPhoto = (android.widget.LinearLayout) bindings[1];
        this.llMineInfoPhoto.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlMineInfoBirthday = (android.widget.RelativeLayout) bindings[3];
        this.rlMineInfoBirthday.setTag(null);
        this.rlMineInfoGender = (android.widget.RelativeLayout) bindings[2];
        this.rlMineInfoGender.setTag(null);
        this.rlMineInfoLocation = (android.widget.RelativeLayout) bindings[4];
        this.rlMineInfoLocation.setTag(null);
        this.tmMineInfo = (com.fanc.wheretoplay.view.TopMenu) bindings[6];
        this.tvMineInfoGender = (android.widget.TextView) bindings[10];
        this.tvMineInfoLocation = (android.widget.TextView) bindings[14];
        this.tvMineInforBirthday = (android.widget.TextView) bindings[12];
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
            setClick((com.fanc.wheretoplay.fragment.MineInfoFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.MineInfoFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.MineInfoFragment getClick() {
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
        com.fanc.wheretoplay.fragment.MineInfoFragment click = mClick;
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

            this.btnMineInfoConfirm.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.llMineInfoPhoto.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.rlMineInfoBirthday.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.rlMineInfoGender.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.rlMineInfoLocation.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.MineInfoFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.MineInfoFragment value) {
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
    public static FragmentMineInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMineInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMineInfoBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_mine_info, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentMineInfoBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMineInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_mine_info, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentMineInfoBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMineInfoBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_mine_info_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMineInfoBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}