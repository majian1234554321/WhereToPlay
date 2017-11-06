package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemSystemNotifyBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_item_system_notify, 3);
        sViewsWithIds.put(R.id.tv_item_system_notify_title, 4);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivItemSystemNotify;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemSystemNotifyContent;
    @NonNull
    public final android.widget.TextView tvItemSystemNotifyTime;
    @NonNull
    public final android.widget.TextView tvItemSystemNotifyTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.NotificationList.Notification mNotification;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemSystemNotifyBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivItemSystemNotify = (android.widget.ImageView) bindings[3];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemSystemNotifyContent = (android.widget.TextView) bindings[1];
        this.tvItemSystemNotifyContent.setTag(null);
        this.tvItemSystemNotifyTime = (android.widget.TextView) bindings[2];
        this.tvItemSystemNotifyTime.setTag(null);
        this.tvItemSystemNotifyTitle = (android.widget.TextView) bindings[4];
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
        if (BR.notification == variableId) {
            setNotification((com.fanc.wheretoplay.datamodel.NotificationList.Notification) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setNotification(@Nullable com.fanc.wheretoplay.datamodel.NotificationList.Notification Notification) {
        this.mNotification = Notification;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.notification);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.NotificationList.Notification getNotification() {
        return mNotification;
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
        com.fanc.wheretoplay.datamodel.NotificationList.Notification notification = mNotification;
        java.lang.String notificationCreatedTime = null;
        java.lang.String notificationContent = null;
        java.lang.String dateFormatUtilGetDateFormatMMDDHHmmNotificationCreatedTime = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (notification != null) {
                    // read notification.created_time
                    notificationCreatedTime = notification.getCreated_time();
                    // read notification.content
                    notificationContent = notification.getContent();
                }


                // read DateFormatUtil.getDateFormatMMDDHHmm(notification.created_time)
                dateFormatUtilGetDateFormatMMDDHHmmNotificationCreatedTime = com.fanc.wheretoplay.util.DateFormatUtil.getDateFormatMMDDHHmm(notificationCreatedTime);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemSystemNotifyContent, notificationContent);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemSystemNotifyTime, dateFormatUtilGetDateFormatMMDDHHmmNotificationCreatedTime);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemSystemNotifyBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemSystemNotifyBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemSystemNotifyBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_system_notify, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemSystemNotifyBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemSystemNotifyBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_system_notify, null, false), bindingComponent);
    }
    @NonNull
    public static ItemSystemNotifyBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemSystemNotifyBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_system_notify_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemSystemNotifyBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): notification
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}