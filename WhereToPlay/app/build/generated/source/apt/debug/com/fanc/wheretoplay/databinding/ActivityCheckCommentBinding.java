package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCheckCommentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.abl_check_comment, 1);
        sViewsWithIds.put(R.id.tm_check_comment, 2);
        sViewsWithIds.put(R.id.fl_check_comment_tab, 3);
        sViewsWithIds.put(R.id.phsv_check_comment, 4);
        sViewsWithIds.put(R.id.ll_phsv_check_comment, 5);
        sViewsWithIds.put(R.id.tv_comment_tab_label_environment, 6);
        sViewsWithIds.put(R.id.tv_comment_tab_label_atmosphere, 7);
        sViewsWithIds.put(R.id.tv_comment_tab_label_service, 8);
        sViewsWithIds.put(R.id.tv_comment_tab_label_other, 9);
        sViewsWithIds.put(R.id.iv_comment_tab_label_back, 10);
        sViewsWithIds.put(R.id.mvp_check_comment, 11);
    }
    // views
    @NonNull
    public final android.support.design.widget.AppBarLayout ablCheckComment;
    @NonNull
    public final android.support.design.widget.CoordinatorLayout cdlCheckItem;
    @NonNull
    public final android.widget.FrameLayout flCheckCommentTab;
    @NonNull
    public final android.widget.ImageView ivCommentTabLabelBack;
    @NonNull
    public final android.widget.LinearLayout llPhsvCheckComment;
    @NonNull
    public final com.fanc.wheretoplay.view.MyViewPager mvpCheckComment;
    @NonNull
    public final com.fanc.wheretoplay.view.PageHorizontalScrollView phsvCheckComment;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmCheckComment;
    @NonNull
    public final android.widget.TextView tvCommentTabLabelAtmosphere;
    @NonNull
    public final android.widget.TextView tvCommentTabLabelEnvironment;
    @NonNull
    public final android.widget.TextView tvCommentTabLabelOther;
    @NonNull
    public final android.widget.TextView tvCommentTabLabelService;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCheckCommentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.ablCheckComment = (android.support.design.widget.AppBarLayout) bindings[1];
        this.cdlCheckItem = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.cdlCheckItem.setTag(null);
        this.flCheckCommentTab = (android.widget.FrameLayout) bindings[3];
        this.ivCommentTabLabelBack = (android.widget.ImageView) bindings[10];
        this.llPhsvCheckComment = (android.widget.LinearLayout) bindings[5];
        this.mvpCheckComment = (com.fanc.wheretoplay.view.MyViewPager) bindings[11];
        this.phsvCheckComment = (com.fanc.wheretoplay.view.PageHorizontalScrollView) bindings[4];
        this.tmCheckComment = (com.fanc.wheretoplay.view.TopMenu) bindings[2];
        this.tvCommentTabLabelAtmosphere = (android.widget.TextView) bindings[7];
        this.tvCommentTabLabelEnvironment = (android.widget.TextView) bindings[6];
        this.tvCommentTabLabelOther = (android.widget.TextView) bindings[9];
        this.tvCommentTabLabelService = (android.widget.TextView) bindings[8];
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
    public static ActivityCheckCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityCheckCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityCheckCommentBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_check_comment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityCheckCommentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityCheckCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_check_comment, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityCheckCommentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityCheckCommentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_check_comment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityCheckCommentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}