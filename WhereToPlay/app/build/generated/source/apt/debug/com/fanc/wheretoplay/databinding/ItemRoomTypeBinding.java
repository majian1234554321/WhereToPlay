package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRoomTypeBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final com.zhy.android.percent.support.PercentLinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemRoomAcoustics;
    @NonNull
    public final android.widget.TextView tvItemRoomCanReserve;
    @NonNull
    public final android.widget.TextView tvItemRoomLittleConsume;
    @NonNull
    public final android.widget.TextView tvItemRoomName;
    @NonNull
    public final android.widget.TextView tvItemRoomNum;
    @NonNull
    public final android.widget.TextView tvItemRoomOther;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.RoomCategory.Room mRoom;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRoomTypeBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.mboundView0 = (com.zhy.android.percent.support.PercentLinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemRoomAcoustics = (android.widget.TextView) bindings[5];
        this.tvItemRoomAcoustics.setTag(null);
        this.tvItemRoomCanReserve = (android.widget.TextView) bindings[4];
        this.tvItemRoomCanReserve.setTag(null);
        this.tvItemRoomLittleConsume = (android.widget.TextView) bindings[3];
        this.tvItemRoomLittleConsume.setTag(null);
        this.tvItemRoomName = (android.widget.TextView) bindings[1];
        this.tvItemRoomName.setTag(null);
        this.tvItemRoomNum = (android.widget.TextView) bindings[2];
        this.tvItemRoomNum.setTag(null);
        this.tvItemRoomOther = (android.widget.TextView) bindings[6];
        this.tvItemRoomOther.setTag(null);
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
            setRoom((com.fanc.wheretoplay.datamodel.RoomCategory.Room) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRoom(@Nullable com.fanc.wheretoplay.datamodel.RoomCategory.Room Room) {
        this.mRoom = Room;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.room);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.RoomCategory.Room getRoom() {
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
        java.lang.String roomAllowNumber = null;
        java.lang.String roomAudio = null;
        java.lang.String roomMinPrice = null;
        java.lang.String roomMans = null;
        java.lang.String roomOther = null;
        com.fanc.wheretoplay.datamodel.RoomCategory.Room room = mRoom;
        java.lang.String roomName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (room != null) {
                    // read room.allow_number
                    roomAllowNumber = room.allow_number;
                    // read room.audio
                    roomAudio = room.audio;
                    // read room.min_price
                    roomMinPrice = room.min_price;
                    // read room.mans
                    roomMans = room.mans;
                    // read room.other
                    roomOther = room.other;
                    // read room.name
                    roomName = room.name;
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRoomAcoustics, roomAudio);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRoomCanReserve, roomAllowNumber);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRoomLittleConsume, roomMinPrice);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRoomName, roomName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRoomNum, roomMans);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemRoomOther, roomOther);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemRoomTypeBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_room_type, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_room_type, null, false), bindingComponent);
    }
    @NonNull
    public static ItemRoomTypeBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRoomTypeBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_room_type_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemRoomTypeBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): room
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}