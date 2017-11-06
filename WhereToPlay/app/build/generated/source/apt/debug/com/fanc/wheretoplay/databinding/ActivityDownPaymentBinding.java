package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDownPaymentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_down_payment, 5);
        sViewsWithIds.put(R.id.tv_down_payment_time_1, 6);
        sViewsWithIds.put(R.id.tv_down_payment_room_category, 7);
        sViewsWithIds.put(R.id.tv_down_payment_1, 8);
        sViewsWithIds.put(R.id.tv_down_payment_pay_way, 9);
        sViewsWithIds.put(R.id.rl_down_payment_pay_way, 10);
        sViewsWithIds.put(R.id.ll_down_payment_weixin, 11);
        sViewsWithIds.put(R.id.ll_down_payment_ali, 12);
        sViewsWithIds.put(R.id.ll_down_payment_balance, 13);
        sViewsWithIds.put(R.id.rg_down_payment, 14);
        sViewsWithIds.put(R.id.rb_down_payment_weixin, 15);
        sViewsWithIds.put(R.id.rb_down_payment_ali, 16);
        sViewsWithIds.put(R.id.rb_down_payment_balance, 17);
        sViewsWithIds.put(R.id.btn_down_payment_pay, 18);
    }
    // views
    @NonNull
    public final android.widget.Button btnDownPaymentPay;
    @NonNull
    public final android.widget.LinearLayout llDownPaymentAli;
    @NonNull
    public final android.widget.LinearLayout llDownPaymentBalance;
    @NonNull
    public final android.widget.LinearLayout llDownPaymentWeixin;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RadioButton rbDownPaymentAli;
    @NonNull
    public final android.widget.RadioButton rbDownPaymentBalance;
    @NonNull
    public final android.widget.RadioButton rbDownPaymentWeixin;
    @NonNull
    public final android.widget.RadioGroup rgDownPayment;
    @NonNull
    public final android.widget.RelativeLayout rlDownPaymentPayWay;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmDownPayment;
    @NonNull
    public final android.widget.TextView tvDownPayment1;
    @NonNull
    public final android.widget.TextView tvDownPaymentPayWay;
    @NonNull
    public final android.widget.TextView tvDownPaymentRoom;
    @NonNull
    public final android.widget.TextView tvDownPaymentRoomCategory;
    @NonNull
    public final android.widget.TextView tvDownPaymentSum;
    @NonNull
    public final android.widget.TextView tvDownPaymentTime;
    @NonNull
    public final android.widget.TextView tvDownPaymentTime1;
    @NonNull
    public final android.widget.TextView tvDownPaymentTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.OrderInfo.Order mOrder;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDownPaymentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds);
        this.btnDownPaymentPay = (android.widget.Button) bindings[18];
        this.llDownPaymentAli = (android.widget.LinearLayout) bindings[12];
        this.llDownPaymentBalance = (android.widget.LinearLayout) bindings[13];
        this.llDownPaymentWeixin = (android.widget.LinearLayout) bindings[11];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbDownPaymentAli = (android.widget.RadioButton) bindings[16];
        this.rbDownPaymentBalance = (android.widget.RadioButton) bindings[17];
        this.rbDownPaymentWeixin = (android.widget.RadioButton) bindings[15];
        this.rgDownPayment = (android.widget.RadioGroup) bindings[14];
        this.rlDownPaymentPayWay = (android.widget.RelativeLayout) bindings[10];
        this.tmDownPayment = (com.fanc.wheretoplay.view.TopMenu) bindings[5];
        this.tvDownPayment1 = (android.widget.TextView) bindings[8];
        this.tvDownPaymentPayWay = (android.widget.TextView) bindings[9];
        this.tvDownPaymentRoom = (android.widget.TextView) bindings[3];
        this.tvDownPaymentRoom.setTag(null);
        this.tvDownPaymentRoomCategory = (android.widget.TextView) bindings[7];
        this.tvDownPaymentSum = (android.widget.TextView) bindings[4];
        this.tvDownPaymentSum.setTag(null);
        this.tvDownPaymentTime = (android.widget.TextView) bindings[2];
        this.tvDownPaymentTime.setTag(null);
        this.tvDownPaymentTime1 = (android.widget.TextView) bindings[6];
        this.tvDownPaymentTitle = (android.widget.TextView) bindings[1];
        this.tvDownPaymentTitle.setTag(null);
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
        if (BR.order == variableId) {
            setOrder((com.fanc.wheretoplay.datamodel.OrderInfo.Order) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setOrder(@Nullable com.fanc.wheretoplay.datamodel.OrderInfo.Order Order) {
        this.mOrder = Order;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.order);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.OrderInfo.Order getOrder() {
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
        java.lang.String orderArrivalTime = null;
        java.lang.String dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime = null;
        java.lang.String orderPrepay = null;
        java.lang.String orderRoomType = null;
        java.lang.String dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTimeTvDownPaymentTimeAndroidStringBefore = null;
        java.lang.String tvDownPaymentSumAndroidStringCurrencyCharOrderPrepay = null;
        com.fanc.wheretoplay.datamodel.OrderInfo.Order order = mOrder;
        java.lang.String orderStoreName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (order != null) {
                    // read order.arrival_time
                    orderArrivalTime = order.arrival_time;
                    // read order.prepay
                    orderPrepay = order.prepay;
                    // read order.room_type
                    orderRoomType = order.room_type;
                    // read order.store_name
                    orderStoreName = order.store_name;
                }


                // read DateFormatUtil.getYYYYMMDDHHmm(order.arrival_time)
                dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime = com.fanc.wheretoplay.util.DateFormatUtil.getYYYYMMDDHHmm(orderArrivalTime);
                // read (@android:string/currency_char) + (order.prepay)
                tvDownPaymentSumAndroidStringCurrencyCharOrderPrepay = (tvDownPaymentSum.getResources().getString(R.string.currency_char)) + (orderPrepay);


                // read (DateFormatUtil.getYYYYMMDDHHmm(order.arrival_time)) + (@android:string/before)
                dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTimeTvDownPaymentTimeAndroidStringBefore = (dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime) + (tvDownPaymentTime.getResources().getString(R.string.before));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDownPaymentRoom, orderRoomType);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDownPaymentSum, tvDownPaymentSumAndroidStringCurrencyCharOrderPrepay);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDownPaymentTime, dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTimeTvDownPaymentTimeAndroidStringBefore);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDownPaymentTitle, orderStoreName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityDownPaymentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDownPaymentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityDownPaymentBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_down_payment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityDownPaymentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDownPaymentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_down_payment, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityDownPaymentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDownPaymentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_down_payment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityDownPaymentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): order
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}