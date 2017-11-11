package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemCheckCommentsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_check_comments_avatar, 1);
        sViewsWithIds.put(R.id.tv_check_comments_nickname, 2);
        sViewsWithIds.put(R.id.rb_comments, 3);
        sViewsWithIds.put(R.id.tv_check_comments_time, 4);
        sViewsWithIds.put(R.id.tv_check_comments_content, 5);
        sViewsWithIds.put(R.id.rc_cardview, 6);
    }
    // views
    @NonNull
    public final com.fanc.wheretoplay.view.CircleImageView ivCheckCommentsAvatar;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.RatingBar rbComments;
    @NonNull
    public final android.support.v7.widget.RecyclerView rcCardview;
    @NonNull
    public final android.widget.TextView tvCheckCommentsContent;
    @NonNull
    public final android.widget.TextView tvCheckCommentsNickname;
    @NonNull
    public final android.widget.TextView tvCheckCommentsTime;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemCheckCommentsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivCheckCommentsAvatar = (com.fanc.wheretoplay.view.CircleImageView) bindings[1];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbComments = (android.widget.RatingBar) bindings[3];
        this.rcCardview = (android.support.v7.widget.RecyclerView) bindings[6];
        this.tvCheckCommentsContent = (android.widget.TextView) bindings[5];
        this.tvCheckCommentsNickname = (android.widget.TextView) bindings[2];
        this.tvCheckCommentsTime = (android.widget.TextView) bindings[4];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
    public static ItemCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemCheckCommentsBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_check_comments, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_check_comments, null, false), bindingComponent);
    }
    @NonNull
    public static ItemCheckCommentsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCheckCommentsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_check_comments_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemCheckCommentsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}