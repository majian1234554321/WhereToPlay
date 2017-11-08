package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DialogRoomTypeBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_dialog_room_type_title, 1);
        sViewsWithIds.put(R.id.rv_room_type, 2);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvRoomType;
    @NonNull
    public final android.widget.TextView tvDialogRoomTypeTitle;
    // variables
    @Nullable
    private java.util.List<com.fanc.wheretoplay.datamodel.RoomList.Room> mRooms;
    @Nullable
    private com.fanc.wheretoplay.view.RoomTypeDialog mClick;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DialogRoomTypeBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvRoomType = (android.support.v7.widget.RecyclerView) bindings[2];
        this.tvDialogRoomTypeTitle = (android.widget.TextView) bindings[1];
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
        if (BR.rooms == variableId) {
            setRooms((java.util.List<com.fanc.wheretoplay.datamodel.RoomList.Room>) variable);
        }
        else if (BR.click == variableId) {
            setClick((com.fanc.wheretoplay.view.RoomTypeDialog) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRooms(@Nullable java.util.List<com.fanc.wheretoplay.datamodel.RoomList.Room> Rooms) {
        this.mRooms = Rooms;
    }
    @Nullable
    public java.util.List<com.fanc.wheretoplay.datamodel.RoomList.Room> getRooms() {
        return mRooms;
    }
    public void setClick(@Nullable com.fanc.wheretoplay.view.RoomTypeDialog Click) {
        this.mClick = Click;
    }
    @Nullable
    public com.fanc.wheretoplay.view.RoomTypeDialog getClick() {
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static DialogRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogRoomTypeBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.dialog_room_type, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DialogRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogRoomTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.dialog_room_type, null, false), bindingComponent);
    }
    @NonNull
    public static DialogRoomTypeBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogRoomTypeBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_room_type_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogRoomTypeBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): rooms
        flag 1 (0x2L): click
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}