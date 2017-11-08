package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemWaiterBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.fl_item_waiter, 5);
        sViewsWithIds.put(R.id.cb_item_waiter_status, 6);
    }
    // views
    @NonNull
    public final android.widget.CheckBox cbItemWaiterStatus;
    @NonNull
    public final android.widget.FrameLayout flItemWaiter;
    @NonNull
    public final android.widget.ImageView ivItemWaiterDistinct;
    @NonNull
    public final android.widget.ImageView ivItemWaiterFuzzy;
    @NonNull
    public final android.widget.ImageView ivItemWaiterLock;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemWaiterName;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo mWaiter;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemWaiterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.cbItemWaiterStatus = (android.widget.CheckBox) bindings[6];
        this.flItemWaiter = (android.widget.FrameLayout) bindings[5];
        this.ivItemWaiterDistinct = (android.widget.ImageView) bindings[1];
        this.ivItemWaiterDistinct.setTag(null);
        this.ivItemWaiterFuzzy = (android.widget.ImageView) bindings[2];
        this.ivItemWaiterFuzzy.setTag(null);
        this.ivItemWaiterLock = (android.widget.ImageView) bindings[3];
        this.ivItemWaiterLock.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemWaiterName = (android.widget.TextView) bindings[4];
        this.tvItemWaiterName.setTag(null);
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
        if (BR.waiter == variableId) {
            setWaiter((com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setWaiter(@Nullable com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo Waiter) {
        updateRegistration(0, Waiter);
        this.mWaiter = Waiter;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.waiter);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo getWaiter() {
        return mWaiter;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeWaiter((com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo) object, fieldId);
        }
        return false;
    }
    private boolean onChangeWaiter(com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo Waiter, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.type) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        boolean int0WaiterType = false;
        int waiterType = 0;
        java.lang.String waiterImage = null;
        int int0WaiterTypeViewVISIBLEViewGONE = 0;
        com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo waiter = mWaiter;
        java.lang.String waiterName = null;
        int int0WaiterTypeViewGONEViewVISIBLE = 0;

        if ((dirtyFlags & 0x7L) != 0) {



                if (waiter != null) {
                    // read waiter.type
                    waiterType = waiter.getType();
                }


                // read 0 == waiter.type
                int0WaiterType = (0) == (waiterType);
            if((dirtyFlags & 0x7L) != 0) {
                if(int0WaiterType) {
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                }
                else {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                }
            }


                // read 0 == waiter.type ? View.VISIBLE : View.GONE
                int0WaiterTypeViewVISIBLEViewGONE = ((int0WaiterType) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read 0 == waiter.type ? View.GONE : View.VISIBLE
                int0WaiterTypeViewGONEViewVISIBLE = ((int0WaiterType) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            if ((dirtyFlags & 0x5L) != 0) {

                    if (waiter != null) {
                        // read waiter.image
                        waiterImage = waiter.getImage();
                        // read waiter.name
                        waiterName = waiter.getName();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.ivItemWaiterDistinct.setVisibility(int0WaiterTypeViewGONEViewVISIBLE);
            this.ivItemWaiterFuzzy.setVisibility(int0WaiterTypeViewVISIBLEViewGONE);
            this.ivItemWaiterLock.setVisibility(int0WaiterTypeViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo.setWaiterImage(this.ivItemWaiterDistinct, waiterImage);
            com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo.setWaiterImage(this.ivItemWaiterFuzzy, waiterImage);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemWaiterName, waiterName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemWaiterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemWaiterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemWaiterBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_waiter, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemWaiterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemWaiterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_waiter, null, false), bindingComponent);
    }
    @NonNull
    public static ItemWaiterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemWaiterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_waiter_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemWaiterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): waiter
        flag 1 (0x2L): waiter.type
        flag 2 (0x3L): null
        flag 3 (0x4L): 0 == waiter.type ? View.VISIBLE : View.GONE
        flag 4 (0x5L): 0 == waiter.type ? View.VISIBLE : View.GONE
        flag 5 (0x6L): 0 == waiter.type ? View.GONE : View.VISIBLE
        flag 6 (0x7L): 0 == waiter.type ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}