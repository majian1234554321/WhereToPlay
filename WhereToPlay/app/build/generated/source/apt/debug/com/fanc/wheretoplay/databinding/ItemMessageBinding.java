package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemMessageBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.sml_delete, 3);
        sViewsWithIds.put(R.id.ll_item_message, 4);
        sViewsWithIds.put(R.id.v_unread, 5);
        sViewsWithIds.put(R.id.btn_item_message_delete, 6);
    }
    // views
    @NonNull
    public final android.widget.Button btnItemMessageDelete;
    @NonNull
    public final android.widget.LinearLayout llItemMessage;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.LinearLayout smlDelete;
    @NonNull
    public final android.widget.TextView tvItemMessageContent;
    @NonNull
    public final android.widget.TextView tvItemMessageTitle;
    @NonNull
    public final android.view.View vUnread;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.MessageList.Message mMessage;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemMessageBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btnItemMessageDelete = (android.widget.Button) bindings[6];
        this.llItemMessage = (android.widget.LinearLayout) bindings[4];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.smlDelete = (android.widget.LinearLayout) bindings[3];
        this.tvItemMessageContent = (android.widget.TextView) bindings[2];
        this.tvItemMessageContent.setTag(null);
        this.tvItemMessageTitle = (android.widget.TextView) bindings[1];
        this.tvItemMessageTitle.setTag(null);
        this.vUnread = (android.view.View) bindings[5];
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
        if (BR.message == variableId) {
            setMessage((com.fanc.wheretoplay.datamodel.MessageList.Message) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setMessage(@Nullable com.fanc.wheretoplay.datamodel.MessageList.Message Message) {
        updateRegistration(0, Message);
        this.mMessage = Message;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.message);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.MessageList.Message getMessage() {
        return mMessage;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeMessage((com.fanc.wheretoplay.datamodel.MessageList.Message) object, fieldId);
        }
        return false;
    }
    private boolean onChangeMessage(com.fanc.wheretoplay.datamodel.MessageList.Message Message, int fieldId) {
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
        java.lang.String messageTitle = null;
        com.fanc.wheretoplay.datamodel.MessageList.Message message = mMessage;
        java.lang.String messageContent = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (message != null) {
                    // read message.title
                    messageTitle = message.getTitle();
                    // read message.content
                    messageContent = message.getContent();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemMessageContent, messageContent);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemMessageTitle, messageTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemMessageBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemMessageBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemMessageBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_message, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemMessageBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemMessageBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_message, null, false), bindingComponent);
    }
    @NonNull
    public static ItemMessageBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemMessageBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_message_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemMessageBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): message
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}