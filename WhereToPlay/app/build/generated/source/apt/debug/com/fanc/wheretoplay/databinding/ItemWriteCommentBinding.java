package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemWriteCommentBinding extends android.databinding.ViewDataBinding  {

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
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvWriteComment;
    // variables
    @Nullable
    private java.lang.String mComment;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemWriteCommentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvWriteComment = (android.widget.TextView) bindings[1];
        this.tvWriteComment.setTag(null);
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
        if (BR.comment == variableId) {
            setComment((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setComment(@Nullable java.lang.String Comment) {
        this.mComment = Comment;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.comment);
        super.requestRebind();
    }
    @Nullable
    public java.lang.String getComment() {
        return mComment;
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
        java.lang.String comment = mComment;

        if ((dirtyFlags & 0x3L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvWriteComment, comment);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemWriteCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemWriteCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemWriteCommentBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_write_comment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemWriteCommentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemWriteCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_write_comment, null, false), bindingComponent);
    }
    @NonNull
    public static ItemWriteCommentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemWriteCommentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_write_comment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemWriteCommentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): comment
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}