package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentOrderToCompleteBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_order_to_complete, 11);
        sViewsWithIds.put(R.id.rl_store_info, 12);
        sViewsWithIds.put(R.id.tv_order_to_complete_discount_real, 13);
        sViewsWithIds.put(R.id.tv_order_to_complete_distance, 14);
        sViewsWithIds.put(R.id.ll_order_info, 15);
        sViewsWithIds.put(R.id.tv_order_to_complete_name_label, 16);
        sViewsWithIds.put(R.id.tv_order_to_complete_mobile_label, 17);
        sViewsWithIds.put(R.id.tv_order_to_complete_room_label, 18);
        sViewsWithIds.put(R.id.tv_order_to_complete_time_label, 19);
        sViewsWithIds.put(R.id.tv_order_to_complete_car_label, 20);
        sViewsWithIds.put(R.id.tv_order_to_complete_number_label, 21);
        sViewsWithIds.put(R.id.tv_order_to_complete_waiter_label, 22);
        sViewsWithIds.put(R.id.tv_order_to_complete_remark_label, 23);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout llOrderInfo;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout rlStoreInfo;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmOrderToComplete;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteAddress;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteCar;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteCarLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteDiscountReal;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteDistance;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteMobile;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteMobileLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteName;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteNameLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteNumber;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteNumberLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteRemark;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteRemarkLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteRoom;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteRoomLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteTime;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteTimeLabel;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteTitle;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteWaiter;
    @NonNull
    public final android.widget.TextView tvOrderToCompleteWaiterLabel;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.OrderReserved.OrderInfo mOrder;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentOrderToCompleteBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds);
        this.llOrderInfo = (android.widget.LinearLayout) bindings[15];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlStoreInfo = (android.widget.RelativeLayout) bindings[12];
        this.tmOrderToComplete = (com.fanc.wheretoplay.view.TopMenu) bindings[11];
        this.tvOrderToCompleteAddress = (android.widget.TextView) bindings[2];
        this.tvOrderToCompleteAddress.setTag(null);
        this.tvOrderToCompleteCar = (android.widget.TextView) bindings[7];
        this.tvOrderToCompleteCar.setTag(null);
        this.tvOrderToCompleteCarLabel = (android.widget.TextView) bindings[20];
        this.tvOrderToCompleteDiscountReal = (android.widget.TextView) bindings[13];
        this.tvOrderToCompleteDistance = (android.widget.TextView) bindings[14];
        this.tvOrderToCompleteMobile = (android.widget.TextView) bindings[4];
        this.tvOrderToCompleteMobile.setTag(null);
        this.tvOrderToCompleteMobileLabel = (android.widget.TextView) bindings[17];
        this.tvOrderToCompleteName = (android.widget.TextView) bindings[3];
        this.tvOrderToCompleteName.setTag(null);
        this.tvOrderToCompleteNameLabel = (android.widget.TextView) bindings[16];
        this.tvOrderToCompleteNumber = (android.widget.TextView) bindings[8];
        this.tvOrderToCompleteNumber.setTag(null);
        this.tvOrderToCompleteNumberLabel = (android.widget.TextView) bindings[21];
        this.tvOrderToCompleteRemark = (android.widget.TextView) bindings[10];
        this.tvOrderToCompleteRemark.setTag(null);
        this.tvOrderToCompleteRemarkLabel = (android.widget.TextView) bindings[23];
        this.tvOrderToCompleteRoom = (android.widget.TextView) bindings[5];
        this.tvOrderToCompleteRoom.setTag(null);
        this.tvOrderToCompleteRoomLabel = (android.widget.TextView) bindings[18];
        this.tvOrderToCompleteTime = (android.widget.TextView) bindings[6];
        this.tvOrderToCompleteTime.setTag(null);
        this.tvOrderToCompleteTimeLabel = (android.widget.TextView) bindings[19];
        this.tvOrderToCompleteTitle = (android.widget.TextView) bindings[1];
        this.tvOrderToCompleteTitle.setTag(null);
        this.tvOrderToCompleteWaiter = (android.widget.TextView) bindings[9];
        this.tvOrderToCompleteWaiter.setTag(null);
        this.tvOrderToCompleteWaiterLabel = (android.widget.TextView) bindings[22];
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
            setOrder((com.fanc.wheretoplay.datamodel.OrderReserved.OrderInfo) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setOrder(@Nullable com.fanc.wheretoplay.datamodel.OrderReserved.OrderInfo Order) {
        this.mOrder = Order;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.order);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.OrderReserved.OrderInfo getOrder() {
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
        java.lang.String orderAddress = null;
        java.lang.String orderRoomType = null;
        java.lang.String orderMobile = null;
        java.lang.String javaLangStringEqualsOrderRemarkTvOrderToCompleteRemarkAndroidStringNotHaveRemarkOrderRemark = null;
        java.lang.String orderNickname = null;
        java.lang.String orderRemark = null;
        java.lang.String orderCarNum = null;
        java.lang.String orderWaiterName = null;
        boolean javaLangStringEqualsOrderRemark = false;
        com.fanc.wheretoplay.datamodel.OrderReserved.OrderInfo order = mOrder;
        java.lang.String orderStoreName = null;
        java.lang.String orderMans = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (order != null) {
                    // read order.arrival_time
                    orderArrivalTime = order.arrival_time;
                    // read order.address
                    orderAddress = order.address;
                    // read order.room_type
                    orderRoomType = order.room_type;
                    // read order.mobile
                    orderMobile = order.mobile;
                    // read order.nickname
                    orderNickname = order.nickname;
                    // read order.remark
                    orderRemark = order.remark;
                    // read order.car_num
                    orderCarNum = order.car_num;
                    // read order.waiter_name
                    orderWaiterName = order.waiter_name;
                    // read order.store_name
                    orderStoreName = order.store_name;
                    // read order.mans
                    orderMans = order.mans;
                }


                // read DateFormatUtil.getYYYYMMDDHHmm(order.arrival_time)
                dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime = com.fanc.wheretoplay.util.DateFormatUtil.getYYYYMMDDHHmm(orderArrivalTime);
                // read "".equals(order.remark)
                javaLangStringEqualsOrderRemark = "".equals(orderRemark);
            if((dirtyFlags & 0x3L) != 0) {
                if(javaLangStringEqualsOrderRemark) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x3L) != 0) {

                // read "".equals(order.remark) ? @android:string/not_have_remark : order.remark
                javaLangStringEqualsOrderRemarkTvOrderToCompleteRemarkAndroidStringNotHaveRemarkOrderRemark = ((javaLangStringEqualsOrderRemark) ? (tvOrderToCompleteRemark.getResources().getString(R.string.not_have_remark)) : (orderRemark));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteAddress, orderAddress);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteCar, orderCarNum);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteMobile, orderMobile);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteName, orderNickname);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteNumber, orderMans);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteRemark, javaLangStringEqualsOrderRemarkTvOrderToCompleteRemarkAndroidStringNotHaveRemarkOrderRemark);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteRoom, orderRoomType);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteTime, dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteTitle, orderStoreName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvOrderToCompleteWaiter, orderWaiterName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentOrderToCompleteBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentOrderToCompleteBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentOrderToCompleteBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_order_to_complete, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentOrderToCompleteBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentOrderToCompleteBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_order_to_complete, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentOrderToCompleteBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentOrderToCompleteBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_order_to_complete_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentOrderToCompleteBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): order
        flag 1 (0x2L): null
        flag 2 (0x3L): "".equals(order.remark) ? @android:string/not_have_remark : order.remark
        flag 3 (0x4L): "".equals(order.remark) ? @android:string/not_have_remark : order.remark
    flag mapping end*/
    //end
}