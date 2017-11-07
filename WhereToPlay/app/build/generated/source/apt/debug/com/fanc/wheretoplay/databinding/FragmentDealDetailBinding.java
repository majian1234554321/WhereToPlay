package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDealDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_deal_detail, 2);
        sViewsWithIds.put(R.id.rb_deal_detail_consume, 3);
        sViewsWithIds.put(R.id.rb_deal_detail_recharge, 4);
        sViewsWithIds.put(R.id.mvp_deal_detail, 5);
        sViewsWithIds.put(R.id.view, 6);
    }
    // views
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.MyViewPager mvpDealDetail;
    @NonNull
    public final android.widget.RadioButton rbDealDetailConsume;
    @NonNull
    public final android.widget.RadioButton rbDealDetailRecharge;
    @NonNull
    public final android.widget.RadioGroup rgDealDetail;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmDealDetail;
    @NonNull
    public final android.view.View view;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.DealDetailFragment mClcik;
    // values
    // listeners
    private OnCheckedChangeListenerImpl mClcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener;
    // Inverse Binding Event Handlers

    public FragmentDealDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mvpDealDetail = (com.fanc.wheretoplay.view.MyViewPager) bindings[5];
        this.rbDealDetailConsume = (android.widget.RadioButton) bindings[3];
        this.rbDealDetailRecharge = (android.widget.RadioButton) bindings[4];
        this.rgDealDetail = (android.widget.RadioGroup) bindings[1];
        this.rgDealDetail.setTag(null);
        this.tmDealDetail = (com.fanc.wheretoplay.view.TopMenu) bindings[2];
        this.view = (android.view.View) bindings[6];
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
        if (BR.clcik == variableId) {
            setClcik((com.fanc.wheretoplay.fragment.DealDetailFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClcik(@Nullable com.fanc.wheretoplay.fragment.DealDetailFragment Clcik) {
        this.mClcik = Clcik;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.clcik);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.DealDetailFragment getClcik() {
        return mClcik;
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
        android.widget.RadioGroup.OnCheckedChangeListener clcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = null;
        com.fanc.wheretoplay.fragment.DealDetailFragment clcik = mClcik;

        if ((dirtyFlags & 0x3L) != 0) {



                if (clcik != null) {
                    // read clcik::onCheckedChanged
                    clcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = (((mClcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener == null) ? (mClcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = new OnCheckedChangeListenerImpl()) : mClcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener).setValue(clcik));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.RadioGroupBindingAdapter.setListeners(this.rgDealDetail, (android.widget.RadioGroup.OnCheckedChangeListener)clcikOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener, (android.databinding.InverseBindingListener)null);
        }
    }
    // Listener Stub Implementations
    public static class OnCheckedChangeListenerImpl implements android.widget.RadioGroup.OnCheckedChangeListener{
        private com.fanc.wheretoplay.fragment.DealDetailFragment value;
        public OnCheckedChangeListenerImpl setValue(com.fanc.wheretoplay.fragment.DealDetailFragment value) {
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
    public static FragmentDealDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDealDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentDealDetailBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_deal_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentDealDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDealDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_deal_detail, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentDealDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentDealDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_deal_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentDealDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): clcik
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}