package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemReserveListBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.v_bg_top, 5);
        sViewsWithIds.put(R.id.v_bg_bottom, 6);
        sViewsWithIds.put(R.id.tv_reserve_list_item_discount, 7);
        sViewsWithIds.put(R.id.tv_reserve_list_item_time, 8);
        sViewsWithIds.put(R.id.iv_reserve_list_item_way, 9);
        sViewsWithIds.put(R.id.tv_reserve_list_item_state, 10);
        sViewsWithIds.put(R.id.iv_reserve_list_item_state, 11);
        sViewsWithIds.put(R.id.btn_reserve_list_item_to_comment, 12);
        sViewsWithIds.put(R.id.btn_reserve_list_item_cancel, 13);
        sViewsWithIds.put(R.id.btn_reserve_list_item_buy, 14);
    }
    // views
    @NonNull
    public final android.widget.Button btnReserveListItemBuy;
    @NonNull
    public final android.widget.Button btnReserveListItemCancel;
    @NonNull
    public final android.widget.Button btnReserveListItemToComment;
    @NonNull
    public final android.widget.ImageView ivReserveListItemState;
    @NonNull
    public final android.widget.ImageView ivReserveListItemWay;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvReserveListItemDiscount;
    @NonNull
    public final android.widget.TextView tvReserveListItemMobile;
    @NonNull
    public final android.widget.TextView tvReserveListItemName;
    @NonNull
    public final android.widget.TextView tvReserveListItemRoom;
    @NonNull
    public final android.widget.TextView tvReserveListItemState;
    @NonNull
    public final android.widget.TextView tvReserveListItemTime;
    @NonNull
    public final android.widget.TextView tvReserveListItemTitle;
    @NonNull
    public final android.view.View vBgBottom;
    @NonNull
    public final android.view.View vBgTop;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.OrderList.Order mOrder;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemReserveListBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.btnReserveListItemBuy = (android.widget.Button) bindings[14];
        this.btnReserveListItemCancel = (android.widget.Button) bindings[13];
        this.btnReserveListItemToComment = (android.widget.Button) bindings[12];
        this.ivReserveListItemState = (android.widget.ImageView) bindings[11];
        this.ivReserveListItemWay = (android.widget.ImageView) bindings[9];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvReserveListItemDiscount = (android.widget.TextView) bindings[7];
        this.tvReserveListItemMobile = (android.widget.TextView) bindings[4];
        this.tvReserveListItemMobile.setTag(null);
        this.tvReserveListItemName = (android.widget.TextView) bindings[3];
        this.tvReserveListItemName.setTag(null);
        this.tvReserveListItemRoom = (android.widget.TextView) bindings[2];
        this.tvReserveListItemRoom.setTag(null);
        this.tvReserveListItemState = (android.widget.TextView) bindings[10];
        this.tvReserveListItemTime = (android.widget.TextView) bindings[8];
        this.tvReserveListItemTitle = (android.widget.TextView) bindings[1];
        this.tvReserveListItemTitle.setTag(null);
        this.vBgBottom = (android.view.View) bindings[6];
        this.vBgTop = (android.view.View) bindings[5];
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
            setOrder((com.fanc.wheretoplay.datamodel.OrderList.Order) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setOrder(@Nullable com.fanc.wheretoplay.datamodel.OrderList.Order Order) {
        this.mOrder = Order;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.order);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.OrderList.Order getOrder() {
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
        java.lang.String orderRoomTypeJavaLangString = null;
        java.lang.String orderRoomNumber = null;
        java.lang.String orderRoomType = null;
        java.lang.String orderMobile = null;
        java.lang.String orderNickname = null;
        com.fanc.wheretoplay.datamodel.OrderList.Order order = mOrder;
        java.lang.String orderStoreName = null;
        java.lang.String orderRoomTypeJavaLangStringOrderRoomNumber = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (order != null) {
                    // read order.room_number
                    orderRoomNumber = order.room_number;
                    // read order.room_type
                    orderRoomType = order.room_type;
                    // read order.mobile
                    orderMobile = order.mobile;
                    // read order.nickname
                    orderNickname = order.nickname;
                    // read order.store_name
                    orderStoreName = order.store_name;
                }


                // read (order.room_type) + (" ")
                orderRoomTypeJavaLangString = (orderRoomType) + (" ");


                // read ((order.room_type) + (" ")) + (order.room_number)
                orderRoomTypeJavaLangStringOrderRoomNumber = (orderRoomTypeJavaLangString) + (orderRoomNumber);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveListItemMobile, orderMobile);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveListItemName, orderNickname);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveListItemRoom, orderRoomTypeJavaLangStringOrderRoomNumber);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReserveListItemTitle, orderStoreName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemReserveListBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemReserveListBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemReserveListBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_reserve_list, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemReserveListBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemReserveListBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_reserve_list, null, false), bindingComponent);
    }
    @NonNull
    public static ItemReserveListBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemReserveListBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_reserve_list_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemReserveListBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): order
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}