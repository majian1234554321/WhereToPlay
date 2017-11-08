package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMessageDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_message_detail, 3);
        sViewsWithIds.put(R.id.iv_message_detail, 4);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivMessageDetail;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmMessageDetail;
    @NonNull
    public final android.widget.TextView tvMessageDetailContent;
    @NonNull
    public final android.widget.TextView tvMessageDetailTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.MessageDetail.MessageDetailBean mDetail;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMessageDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivMessageDetail = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tmMessageDetail = (com.fanc.wheretoplay.view.TopMenu) bindings[3];
        this.tvMessageDetailContent = (android.widget.TextView) bindings[2];
        this.tvMessageDetailContent.setTag(null);
        this.tvMessageDetailTitle = (android.widget.TextView) bindings[1];
        this.tvMessageDetailTitle.setTag(null);
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
        if (BR.detail == variableId) {
            setDetail((com.fanc.wheretoplay.datamodel.MessageDetail.MessageDetailBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDetail(@Nullable com.fanc.wheretoplay.datamodel.MessageDetail.MessageDetailBean Detail) {
        this.mDetail = Detail;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.detail);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.MessageDetail.MessageDetailBean getDetail() {
        return mDetail;
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
        java.lang.String detailTitle = null;
        com.fanc.wheretoplay.datamodel.MessageDetail.MessageDetailBean detail = mDetail;
        java.lang.String detailContent = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (detail != null) {
                    // read detail.title
                    detailTitle = detail.getTitle();
                    // read detail.content
                    detailContent = detail.getContent();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvMessageDetailContent, detailContent);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvMessageDetailTitle, detailTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentMessageDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMessageDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMessageDetailBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_message_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentMessageDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMessageDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_message_detail, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentMessageDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMessageDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_message_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMessageDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): detail
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}