package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRoomTypeDialogBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rb_item_dialog_room, 3);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout llItemDialogRoom;
    @NonNull
    public final android.widget.RadioButton rbItemDialogRoom;
    @NonNull
    public final android.widget.TextView tvItemDialogPrice;
    @NonNull
    public final android.widget.TextView tvItemDialogRoom;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.RoomList.Room mRoom;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRoomTypeDialogBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.llItemDialogRoom = (android.widget.LinearLayout) bindings[0];
        this.llItemDialogRoom.setTag(null);
        this.rbItemDialogRoom = (android.widget.RadioButton) bindings[3];
        this.tvItemDialogPrice = (android.widget.TextView) bindings[2];
        this.tvItemDialogPrice.setTag(null);
        this.tvItemDialogRoom = (android.widget.TextView) bindings[1];
        this.tvItemDialogRoom.setTag(null);
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
        if (BR.room == variableId) {
            setRoom((com.fanc.wheretoplay.datamodel.RoomList.Room) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRoom(@Nullable com.fanc.wheretoplay.datamodel.RoomList.Room Room) {
        this.mRoom = Room;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.room);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.RoomList.Room getRoom() {
        return mRoom;
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
        java.lang.String roomBookPrice = null;
        java.lang.String tvItemDialogPriceAndroidStringCurrencyCharRoomBookPrice = null;
        com.fanc.wheretoplay.datamodel.RoomList.Room room = mRoom;
        java.lang.String roomName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (room != null) {
                    // read room.book_price
                    roomBookPrice = room.getBook_price();
                    // read room.name
                    roomName = room.getName();
                }


                // read (@android:string/currency_char) + (room.book_price)
                tvItemDialogPriceAndroidStringCurrencyCharRoomBookPrice = (tvItemDialogPrice.getResources().getString(R.string.currency_char)) + (roomBookPrice);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemDialogPrice, tvItemDialogPriceAndroidStringCurrencyCharRoomBookPrice);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemDialogRoom, roomName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemRoomTypeDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRoomTypeDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemRoomTypeDialogBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_room_type_dialog, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemRoomTypeDialogBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRoomTypeDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_room_type_dialog, null, false), bindingComponent);
    }
    @NonNull
    public static ItemRoomTypeDialogBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRoomTypeDialogBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_room_type_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemRoomTypeDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): room
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}