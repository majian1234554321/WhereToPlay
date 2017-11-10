package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentReserveInfoBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_reserve_info, 9);
        sViewsWithIds.put(R.id.relativeLayout2, 10);
        sViewsWithIds.put(R.id.tv_reserve_info_discount_real, 11);
        sViewsWithIds.put(R.id.tv_reserve_info_address, 12);
        sViewsWithIds.put(R.id.tv_reserve_info_distance, 13);
        sViewsWithIds.put(R.id.tv_reserve_little_consume, 14);
        sViewsWithIds.put(R.id.iv_reserve_info_service, 15);
        sViewsWithIds.put(R.id.rb_reserve_info_credit, 16);
        sViewsWithIds.put(R.id.rb_reserve_info_prepay, 17);
        sViewsWithIds.put(R.id.reserve_cl, 18);
        sViewsWithIds.put(R.id.tv_reserve_info_name, 19);
        sViewsWithIds.put(R.id.et_reserve_info_name, 20);
        sViewsWithIds.put(R.id.view2, 21);
        sViewsWithIds.put(R.id.tv_reserve_info_mobile, 22);
        sViewsWithIds.put(R.id.et_reserve_info_mobile, 23);
        sViewsWithIds.put(R.id.tv_reserve_info_room_category, 24);
        sViewsWithIds.put(R.id.tv_reserve_info_reserve_time, 25);
        sViewsWithIds.put(R.id.tv_reserve_info_carport, 26);
        sViewsWithIds.put(R.id.et_reserve_info_carport, 27);
        sViewsWithIds.put(R.id.tv_reserve_info_number_of_people, 28);
        sViewsWithIds.put(R.id.et_reserve_info_number_of_people, 29);
        sViewsWithIds.put(R.id.tv_reserve_info_remark, 30);
        sViewsWithIds.put(R.id.et_reserve_info_remark, 31);
    }
    // views
    @NonNull
    public final android.widget.Button btnReserveInfoPay;
    @NonNull
    public final android.widget.EditText etReserveInfoCarport;
    @NonNull
    public final android.widget.EditText etReserveInfoMobile;
    @NonNull
    public final android.widget.EditText etReserveInfoName;
    @NonNull
    public final android.widget.EditText etReserveInfoNumberOfPeople;
    @NonNull
    public final android.widget.EditText etReserveInfoRemark;
    @NonNull
    public final android.widget.ImageView ivReserveInfo;
    @NonNull
    public final android.widget.ImageView ivReserveInfoDate;
    @NonNull
    public final android.widget.ImageView ivReserveInfoRight;
    @NonNull
    public final android.widget.ImageView ivReserveInfoService;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RadioButton rbReserveInfoCredit;
    @NonNull
    public final android.widget.RadioButton rbReserveInfoPrepay;
    @NonNull
    public final android.widget.RelativeLayout relativeLayout2;
    @NonNull
    public final android.support.constraint.ConstraintLayout reserveCl;
    @NonNull
    public final android.widget.RadioGroup rgReserveInfo;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmReserveInfo;
    @NonNull
    public final android.widget.TextView tvReserveInfoAddress;
    @NonNull
    public final android.widget.TextView tvReserveInfoCarport;
    @NonNull
    public final android.widget.TextView tvReserveInfoDiscountReal;
    @NonNull
    public final android.widget.TextView tvReserveInfoDistance;
    @NonNull
    public final android.widget.TextView tvReserveInfoMobile;
    @NonNull
    public final android.widget.TextView tvReserveInfoName;
    @NonNull
    public final android.widget.TextView tvReserveInfoNumberOfPeople;
    @NonNull
    public final android.widget.TextView tvReserveInfoRemark;
    @NonNull
    public final android.widget.TextView tvReserveInfoReserveTime;
    @NonNull
    public final android.widget.TextView tvReserveInfoRoom;
    @NonNull
    public final android.widget.TextView tvReserveInfoRoomCategory;
    @NonNull
    public final android.widget.TextView tvReserveInfoTime;
    @NonNull
    public final android.widget.TextView tvReserveInfoTitle;
    @NonNull
    public final android.widget.TextView tvReserveLittleConsume;
    @NonNull
    public final android.view.View view2;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.ReserveInfoFragment mClick;
    @Nullable
    private com.fanc.wheretoplay.datamodel.StoreDescribe.Store mStore;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    private OnCheckedChangeListenerImpl mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener;
    // Inverse Binding Event Handlers

    public FragmentReserveInfoBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds);
        this.btnReserveInfoPay = (android.widget.Button) bindings[8];
        this.btnReserveInfoPay.setTag(null);
        this.etReserveInfoCarport = (android.widget.EditText) bindings[27];
        this.etReserveInfoMobile = (android.widget.EditText) bindings[23];
        this.etReserveInfoName = (android.widget.EditText) bindings[20];
        this.etReserveInfoNumberOfPeople = (android.widget.EditText) bindings[29];
        this.etReserveInfoRemark = (android.widget.EditText) bindings[31];
        this.ivReserveInfo = (android.widget.ImageView) bindings[1];
        this.ivReserveInfo.setTag(null);
        this.ivReserveInfoDate = (android.widget.ImageView) bindings[7];
        this.ivReserveInfoDate.setTag(null);
        this.ivReserveInfoRight = (android.widget.ImageView) bindings[5];
        this.ivReserveInfoRight.setTag(null);
        this.ivReserveInfoService = (android.widget.ImageView) bindings[15];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbReserveInfoCredit = (android.widget.RadioButton) bindings[16];
        this.rbReserveInfoPrepay = (android.widget.RadioButton) bindings[17];
        this.relativeLayout2 = (android.widget.RelativeLayout) bindings[10];
        this.reserveCl = (android.support.constraint.ConstraintLayout) bindings[18];
        this.rgReserveInfo = (android.widget.RadioGroup) bindings[3];
        this.rgReserveInfo.setTag(null);
        this.tmReserveInfo = (com.fanc.wheretoplay.view.TopMenu) bindings[9];
        this.tvReserveInfoAddress = (android.widget.TextView) bindings[12];
        this.tvReserveInfoCarport = (android.widget.TextView) bindings[26];
        this.tvReserveInfoDiscountReal = (android.widget.TextView) bindings[11];
        this.tvReserveInfoDistance = (android.widget.TextView) bindings[13];
        this.tvReserveInfoMobile = (android.widget.TextView) bindings[22];
        this.tvReserveInfoName = (android.widget.TextView) bindings[19];
        this.tvReserveInfoNumberOfPeople = (android.widget.TextView) bindings[28];
        this.tvReserveInfoRemark = (android.widget.TextView) bindings[30];
        this.tvReserveInfoReserveTime = (android.widget.TextView) bindings[25];
        this.tvReserveInfoRoom = (android.widget.TextView) bindings[4];
        this.tvReserveInfoRoom.setTag(null);
        this.tvReserveInfoRoomCategory = (android.widget.TextView) bindings[24];
        this.tvReserveInfoTime = (android.widget.TextView) bindings[6];
        this.tvReserveInfoTime.setTag(null);
        this.tvReserveInfoTitle = (android.widget.TextView) bindings[2];
        this.tvReserveInfoTitle.setTag(null);
        this.tvReserveLittleConsume = (android.widget.TextView) bindings[14];
        this.view2 = (android.view.View) bindings[21];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setClick((com.fanc.wheretoplay.fragment.ReserveInfoFragment) variable);
        }
        else if (BR.store == variableId) {
            setStore((com.fanc.wheretoplay.datamodel.StoreDescribe.Store) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.ReserveInfoFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.ReserveInfoFragment getClick() {
        return mClick;
    }
    public void setStore(@Nullable com.fanc.wheretoplay.datamodel.StoreDescribe.Store Store) {
        this.mStore = Store;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.store);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.StoreDescribe.Store getStore() {
        return mStore;
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
        java.lang.String storeCover = null;
        com.fanc.wheretoplay.fragment.ReserveInfoFragment click = mClick;
        java.lang.String storeName = null;
        com.fanc.wheretoplay.datamodel.StoreDescribe.Store store = mStore;
        android.view.View.OnClickListener clickOnViewClickAndroidViewViewOnClickListener = null;
        android.widget.RadioGroup.OnCheckedChangeListener clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (click != null) {
                    // read click::onViewClick
                    clickOnViewClickAndroidViewViewOnClickListener = (((mClickOnViewClickAndroidViewViewOnClickListener == null) ? (mClickOnViewClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnViewClickAndroidViewViewOnClickListener).setValue(click));
                    // read click::onCheckedChanged
                    clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = (((mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener == null) ? (mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = new OnCheckedChangeListenerImpl()) : mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener).setValue(click));
                }
        }
        if ((dirtyFlags & 0x6L) != 0) {



                if (store != null) {
                    // read store.cover
                    storeCover = store.cover;
                    // read store.name
                    storeName = store.name;
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            this.btnReserveInfoPay.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivReserveInfoDate.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivReserveInfoRight.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            android.databinding.adapters.RadioGroupBindingAdapter.setListeners(this.rgReserveInfo, (android.widget.RadioGroup.OnCheckedChangeListener)clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener, (android.databinding.InverseBindingListener)null);
            this.tvReserveInfoRoom.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.tvReserveInfoTime.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.fanc.wheretoplay.datamodel.Url.setImage(this.ivReserveInfo, storeCover);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveInfoTitle, storeName);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.ReserveInfoFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.ReserveInfoFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onViewClick(arg0);
        }
    }
    public static class OnCheckedChangeListenerImpl implements android.widget.RadioGroup.OnCheckedChangeListener{
        private com.fanc.wheretoplay.fragment.ReserveInfoFragment value;
        public OnCheckedChangeListenerImpl setValue(com.fanc.wheretoplay.fragment.ReserveInfoFragment value) {
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
    public static FragmentReserveInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReserveInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentReserveInfoBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_reserve_info, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentReserveInfoBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReserveInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_reserve_info, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentReserveInfoBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReserveInfoBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_reserve_info_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentReserveInfoBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): store
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}