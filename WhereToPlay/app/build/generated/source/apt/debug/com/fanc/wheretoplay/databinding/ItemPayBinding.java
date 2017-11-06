package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemPayBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cb_pay_item_status, 7);
        sViewsWithIds.put(R.id.tv_pay_item_reserved, 8);
        sViewsWithIds.put(R.id.tv_pay_item_time, 9);
        sViewsWithIds.put(R.id.tv_pay_item_room_category, 10);
        sViewsWithIds.put(R.id.tv_pay_item_decorate_category, 11);
        sViewsWithIds.put(R.id.tv_pay_item_reserve_code, 12);
        sViewsWithIds.put(R.id.tv_pay_item_price, 13);
        sViewsWithIds.put(R.id.btn_check_comment, 14);
        sViewsWithIds.put(R.id.btn_to_comment, 15);
        sViewsWithIds.put(R.id.btn_pay_cancel_reserve, 16);
        sViewsWithIds.put(R.id.btn_pay_consume, 17);
    }
    // views
    @NonNull
    public final android.widget.Button btnCheckComment;
    @NonNull
    public final android.widget.Button btnPayCancelReserve;
    @NonNull
    public final android.widget.Button btnPayConsume;
    @NonNull
    public final android.widget.Button btnToComment;
    @NonNull
    public final android.widget.CheckBox cbPayItemStatus;
    @NonNull
    public final android.widget.ImageView ivPayItem;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvPayItemDecorate;
    @NonNull
    public final android.widget.TextView tvPayItemDecorateCategory;
    @NonNull
    public final android.widget.TextView tvPayItemPrice;
    @NonNull
    public final android.widget.TextView tvPayItemRealTime;
    @NonNull
    public final android.widget.TextView tvPayItemReserveCode;
    @NonNull
    public final android.widget.TextView tvPayItemReserveRealCode;
    @NonNull
    public final android.widget.TextView tvPayItemReserved;
    @NonNull
    public final android.widget.TextView tvPayItemRoom;
    @NonNull
    public final android.widget.TextView tvPayItemRoomCategory;
    @NonNull
    public final android.widget.TextView tvPayItemTime;
    @NonNull
    public final android.widget.TextView tvPayItemTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.BookList.Book mOrder;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemPayBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds);
        this.btnCheckComment = (android.widget.Button) bindings[14];
        this.btnPayCancelReserve = (android.widget.Button) bindings[16];
        this.btnPayConsume = (android.widget.Button) bindings[17];
        this.btnToComment = (android.widget.Button) bindings[15];
        this.cbPayItemStatus = (android.widget.CheckBox) bindings[7];
        this.ivPayItem = (android.widget.ImageView) bindings[1];
        this.ivPayItem.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvPayItemDecorate = (android.widget.TextView) bindings[5];
        this.tvPayItemDecorate.setTag(null);
        this.tvPayItemDecorateCategory = (android.widget.TextView) bindings[11];
        this.tvPayItemPrice = (android.widget.TextView) bindings[13];
        this.tvPayItemRealTime = (android.widget.TextView) bindings[3];
        this.tvPayItemRealTime.setTag(null);
        this.tvPayItemReserveCode = (android.widget.TextView) bindings[12];
        this.tvPayItemReserveRealCode = (android.widget.TextView) bindings[6];
        this.tvPayItemReserveRealCode.setTag(null);
        this.tvPayItemReserved = (android.widget.TextView) bindings[8];
        this.tvPayItemRoom = (android.widget.TextView) bindings[4];
        this.tvPayItemRoom.setTag(null);
        this.tvPayItemRoomCategory = (android.widget.TextView) bindings[10];
        this.tvPayItemTime = (android.widget.TextView) bindings[9];
        this.tvPayItemTitle = (android.widget.TextView) bindings[2];
        this.tvPayItemTitle.setTag(null);
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
            setOrder((com.fanc.wheretoplay.datamodel.BookList.Book) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setOrder(@Nullable com.fanc.wheretoplay.datamodel.BookList.Book Order) {
        updateRegistration(0, Order);
        this.mOrder = Order;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.order);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.BookList.Book getOrder() {
        return mOrder;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeOrder((com.fanc.wheretoplay.datamodel.BookList.Book) object, fieldId);
        }
        return false;
    }
    private boolean onChangeOrder(com.fanc.wheretoplay.datamodel.BookList.Book Order, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        java.lang.String dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTimeTvPayItemRealTimeAndroidStringBefore = null;
        java.lang.String orderArrivalTime = null;
        java.lang.String orderCover = null;
        java.lang.String orderName = null;
        java.lang.String dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime = null;
        java.lang.String orderRoomType = null;
        com.fanc.wheretoplay.datamodel.BookList.Book order = mOrder;
        java.lang.String orderDecorateType = null;
        java.lang.String orderBookSn = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (order != null) {
                    // read order.arrival_time
                    orderArrivalTime = order.getArrival_time();
                    // read order.cover
                    orderCover = order.getCover();
                    // read order.name
                    orderName = order.getName();
                    // read order.room_type
                    orderRoomType = order.getRoom_type();
                    // read order.decorate_type
                    orderDecorateType = order.getDecorate_type();
                    // read order.book_sn
                    orderBookSn = order.getBook_sn();
                }


                // read DateFormatUtil.getYYYYMMDDHHmm(order.arrival_time)
                dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime = com.fanc.wheretoplay.util.DateFormatUtil.getYYYYMMDDHHmm(orderArrivalTime);


                // read (DateFormatUtil.getYYYYMMDDHHmm(order.arrival_time)) + (@android:string/before)
                dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTimeTvPayItemRealTimeAndroidStringBefore = (dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTime) + (tvPayItemRealTime.getResources().getString(R.string.before));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo.setWaiterImage(this.ivPayItem, orderCover);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvPayItemDecorate, orderDecorateType);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvPayItemRealTime, dateFormatUtilGetYYYYMMDDHHmmOrderArrivalTimeTvPayItemRealTimeAndroidStringBefore);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvPayItemReserveRealCode, orderBookSn);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvPayItemRoom, orderRoomType);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvPayItemTitle, orderName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemPayBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemPayBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemPayBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_pay, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemPayBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemPayBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_pay, null, false), bindingComponent);
    }
    @NonNull
    public static ItemPayBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemPayBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_pay_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemPayBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): order
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}