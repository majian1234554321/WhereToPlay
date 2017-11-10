package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPayBillBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_pay_bill, 3);
        sViewsWithIds.put(R.id.relativeLayout, 4);
        sViewsWithIds.put(R.id.tv_pay_bill_store, 5);
        sViewsWithIds.put(R.id.tv_pay_bill_discount_real, 6);
        sViewsWithIds.put(R.id.tv_pay_bill_address, 7);
        sViewsWithIds.put(R.id.tv_pay_bill_distance, 8);
        sViewsWithIds.put(R.id.et_pay_bill_consume_sum, 9);
        sViewsWithIds.put(R.id.cb_not_participation, 10);
        sViewsWithIds.put(R.id.ll_not_participation, 11);
        sViewsWithIds.put(R.id.et_pay_bill_not_participation_discount_sum, 12);
        sViewsWithIds.put(R.id.ll_pay_bill_discount_coupon, 13);
        sViewsWithIds.put(R.id.tv_pay_bill_discount_coupon, 14);
        sViewsWithIds.put(R.id.iv_pay_bill_discount_coupon, 15);
        sViewsWithIds.put(R.id.ll_pay_bill_weixin, 16);
        sViewsWithIds.put(R.id.ll_pay_bill_ali, 17);
        sViewsWithIds.put(R.id.ll_pay_bill_balance, 18);
        sViewsWithIds.put(R.id.rb_pay_bill_weixin, 19);
        sViewsWithIds.put(R.id.rb_pay_bill_ali, 20);
        sViewsWithIds.put(R.id.rb_pay_bill_balance, 21);
        sViewsWithIds.put(R.id.tv_pay_bill_pay_sum_real, 22);
        sViewsWithIds.put(R.id.btn_pay_bill, 23);
    }
    // views
    @NonNull
    public final android.widget.Button btnPayBill;
    @NonNull
    public final android.widget.CheckBox cbNotParticipation;
    @NonNull
    public final android.widget.EditText etPayBillConsumeSum;
    @NonNull
    public final android.widget.EditText etPayBillNotParticipationDiscountSum;
    @NonNull
    public final android.widget.ImageView ivPayBillDiscountCoupon;
    @NonNull
    public final android.widget.LinearLayout llNotParticipation;
    @NonNull
    public final android.widget.LinearLayout llPayBillAli;
    @NonNull
    public final android.widget.LinearLayout llPayBillBalance;
    @NonNull
    public final android.widget.LinearLayout llPayBillDiscountCoupon;
    @NonNull
    public final android.widget.LinearLayout llPayBillWeixin;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RadioButton rbPayBillAli;
    @NonNull
    public final android.widget.RadioButton rbPayBillBalance;
    @NonNull
    public final android.widget.RadioButton rbPayBillWeixin;
    @NonNull
    public final android.widget.RelativeLayout relativeLayout;
    @NonNull
    public final android.widget.RadioGroup rgPayBill;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmPayBill;
    @NonNull
    public final android.widget.TextView tvPayBillAddress;
    @NonNull
    public final android.widget.TextView tvPayBillDiscountCoupon;
    @NonNull
    public final android.widget.TextView tvPayBillDiscountReal;
    @NonNull
    public final android.widget.TextView tvPayBillDistance;
    @NonNull
    public final android.widget.TextView tvPayBillDownPaymentSum;
    @NonNull
    public final android.widget.TextView tvPayBillPaySumReal;
    @NonNull
    public final android.widget.TextView tvPayBillStore;
    // variables
    @Nullable
    private com.fanc.wheretoplay.activity.PayBillActivity mClick;
    @Nullable
    private com.fanc.wheretoplay.datamodel.OrderDone.Order mOrder;
    // values
    // listeners
    private OnCheckedChangeListenerImpl mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener;
    // Inverse Binding Event Handlers

    public ActivityPayBillBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds);
        this.btnPayBill = (android.widget.Button) bindings[23];
        this.cbNotParticipation = (android.widget.CheckBox) bindings[10];
        this.etPayBillConsumeSum = (android.widget.EditText) bindings[9];
        this.etPayBillNotParticipationDiscountSum = (android.widget.EditText) bindings[12];
        this.ivPayBillDiscountCoupon = (android.widget.ImageView) bindings[15];
        this.llNotParticipation = (android.widget.LinearLayout) bindings[11];
        this.llPayBillAli = (android.widget.LinearLayout) bindings[17];
        this.llPayBillBalance = (android.widget.LinearLayout) bindings[18];
        this.llPayBillDiscountCoupon = (android.widget.LinearLayout) bindings[13];
        this.llPayBillWeixin = (android.widget.LinearLayout) bindings[16];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbPayBillAli = (android.widget.RadioButton) bindings[20];
        this.rbPayBillBalance = (android.widget.RadioButton) bindings[21];
        this.rbPayBillWeixin = (android.widget.RadioButton) bindings[19];
        this.relativeLayout = (android.widget.RelativeLayout) bindings[4];
        this.rgPayBill = (android.widget.RadioGroup) bindings[2];
        this.rgPayBill.setTag(null);
        this.tmPayBill = (com.fanc.wheretoplay.view.TopMenu) bindings[3];
        this.tvPayBillAddress = (android.widget.TextView) bindings[7];
        this.tvPayBillDiscountCoupon = (android.widget.TextView) bindings[14];
        this.tvPayBillDiscountReal = (android.widget.TextView) bindings[6];
        this.tvPayBillDistance = (android.widget.TextView) bindings[8];
        this.tvPayBillDownPaymentSum = (android.widget.TextView) bindings[1];
        this.tvPayBillDownPaymentSum.setTag(null);
        this.tvPayBillPaySumReal = (android.widget.TextView) bindings[22];
        this.tvPayBillStore = (android.widget.TextView) bindings[5];
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
            setClick((com.fanc.wheretoplay.activity.PayBillActivity) variable);
        }
        else if (BR.order == variableId) {
            setOrder((com.fanc.wheretoplay.datamodel.OrderDone.Order) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.activity.PayBillActivity Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.activity.PayBillActivity getClick() {
        return mClick;
    }
    public void setOrder(@Nullable com.fanc.wheretoplay.datamodel.OrderDone.Order Order) {
        this.mOrder = Order;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.order);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.OrderDone.Order getOrder() {
        return mOrder;
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
        java.lang.String orderPrepay = null;
        com.fanc.wheretoplay.activity.PayBillActivity click = mClick;
        com.fanc.wheretoplay.datamodel.OrderDone.Order order = mOrder;
        android.widget.RadioGroup.OnCheckedChangeListener clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (click != null) {
                    // read click::onCheckedChanged
                    clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = (((mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener == null) ? (mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener = new OnCheckedChangeListenerImpl()) : mClickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener).setValue(click));
                }
        }
        if ((dirtyFlags & 0x6L) != 0) {



                if (order != null) {
                    // read order.prepay
                    orderPrepay = order.prepay;
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            android.databinding.adapters.RadioGroupBindingAdapter.setListeners(this.rgPayBill, (android.widget.RadioGroup.OnCheckedChangeListener)clickOnCheckedChangedAndroidWidgetRadioGroupOnCheckedChangeListener, (android.databinding.InverseBindingListener)null);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvPayBillDownPaymentSum, orderPrepay);
        }
    }
    // Listener Stub Implementations
    public static class OnCheckedChangeListenerImpl implements android.widget.RadioGroup.OnCheckedChangeListener{
        private com.fanc.wheretoplay.activity.PayBillActivity value;
        public OnCheckedChangeListenerImpl setValue(com.fanc.wheretoplay.activity.PayBillActivity value) {
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
    public static ActivityPayBillBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityPayBillBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityPayBillBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_pay_bill, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityPayBillBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityPayBillBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_pay_bill, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityPayBillBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityPayBillBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_pay_bill_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityPayBillBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): order
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}