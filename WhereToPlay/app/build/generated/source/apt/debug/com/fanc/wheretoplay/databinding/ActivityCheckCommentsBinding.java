package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCheckCommentsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_check_comments, 2);
        sViewsWithIds.put(R.id.cl, 3);
        sViewsWithIds.put(R.id.view_top_bg, 4);
        sViewsWithIds.put(R.id.comment_line, 5);
        sViewsWithIds.put(R.id.tv_comments_score, 6);
        sViewsWithIds.put(R.id.tv_comments_all, 7);
        sViewsWithIds.put(R.id.tv_comments_environment, 8);
        sViewsWithIds.put(R.id.tv_comments_score_one, 9);
        sViewsWithIds.put(R.id.rb_comments_one, 10);
        sViewsWithIds.put(R.id.tv_comments_atmosphere, 11);
        sViewsWithIds.put(R.id.tv_comments_score_two, 12);
        sViewsWithIds.put(R.id.rb_comments_two, 13);
        sViewsWithIds.put(R.id.tv_comments_service, 14);
        sViewsWithIds.put(R.id.tv_comments_score_three, 15);
        sViewsWithIds.put(R.id.rb_comments_three, 16);
        sViewsWithIds.put(R.id.view_bottom_line, 17);
        sViewsWithIds.put(R.id.sv_reserve, 18);
        sViewsWithIds.put(R.id.cl_comments, 19);
        sViewsWithIds.put(R.id.view_bottom_bg, 20);
        sViewsWithIds.put(R.id.bt_comments_one, 21);
        sViewsWithIds.put(R.id.bt_comments_two, 22);
        sViewsWithIds.put(R.id.bt_comments_three, 23);
        sViewsWithIds.put(R.id.bt_comments_four, 24);
        sViewsWithIds.put(R.id.rc_comments, 25);
    }
    // views
    @NonNull
    public final android.widget.TextView btCommentsFour;
    @NonNull
    public final android.widget.TextView btCommentsOne;
    @NonNull
    public final android.widget.TextView btCommentsThree;
    @NonNull
    public final android.widget.TextView btCommentsTwo;
    @NonNull
    public final android.support.constraint.ConstraintLayout cl;
    @NonNull
    public final android.support.constraint.ConstraintLayout clComments;
    @NonNull
    public final android.view.View commentLine;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final com.fanc.wheretoplay.view.PullToRefreshLayout ptrlReserve;
    @NonNull
    public final android.widget.RatingBar rbCommentsOne;
    @NonNull
    public final android.widget.RatingBar rbCommentsThree;
    @NonNull
    public final android.widget.RatingBar rbCommentsTwo;
    @NonNull
    public final android.support.v7.widget.RecyclerView rcComments;
    @NonNull
    public final com.fanc.wheretoplay.view.MyScrollView svReserve;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmCheckComments;
    @NonNull
    public final android.widget.TextView tvCommentsAll;
    @NonNull
    public final android.widget.TextView tvCommentsAtmosphere;
    @NonNull
    public final android.widget.TextView tvCommentsEnvironment;
    @NonNull
    public final android.widget.TextView tvCommentsScore;
    @NonNull
    public final android.widget.TextView tvCommentsScoreOne;
    @NonNull
    public final android.widget.TextView tvCommentsScoreThree;
    @NonNull
    public final android.widget.TextView tvCommentsScoreTwo;
    @NonNull
    public final android.widget.TextView tvCommentsService;
    @NonNull
    public final android.view.View viewBottomBg;
    @NonNull
    public final android.view.View viewBottomLine;
    @NonNull
    public final android.view.View viewTopBg;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCheckCommentsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds);
        this.btCommentsFour = (android.widget.TextView) bindings[24];
        this.btCommentsOne = (android.widget.TextView) bindings[21];
        this.btCommentsThree = (android.widget.TextView) bindings[23];
        this.btCommentsTwo = (android.widget.TextView) bindings[22];
        this.cl = (android.support.constraint.ConstraintLayout) bindings[3];
        this.clComments = (android.support.constraint.ConstraintLayout) bindings[19];
        this.commentLine = (android.view.View) bindings[5];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.ptrlReserve = (com.fanc.wheretoplay.view.PullToRefreshLayout) bindings[1];
        this.ptrlReserve.setTag(null);
        this.rbCommentsOne = (android.widget.RatingBar) bindings[10];
        this.rbCommentsThree = (android.widget.RatingBar) bindings[16];
        this.rbCommentsTwo = (android.widget.RatingBar) bindings[13];
        this.rcComments = (android.support.v7.widget.RecyclerView) bindings[25];
        this.svReserve = (com.fanc.wheretoplay.view.MyScrollView) bindings[18];
        this.tmCheckComments = (com.fanc.wheretoplay.view.TopMenu) bindings[2];
        this.tvCommentsAll = (android.widget.TextView) bindings[7];
        this.tvCommentsAtmosphere = (android.widget.TextView) bindings[11];
        this.tvCommentsEnvironment = (android.widget.TextView) bindings[8];
        this.tvCommentsScore = (android.widget.TextView) bindings[6];
        this.tvCommentsScoreOne = (android.widget.TextView) bindings[9];
        this.tvCommentsScoreThree = (android.widget.TextView) bindings[15];
        this.tvCommentsScoreTwo = (android.widget.TextView) bindings[12];
        this.tvCommentsService = (android.widget.TextView) bindings[14];
        this.viewBottomBg = (android.view.View) bindings[20];
        this.viewBottomLine = (android.view.View) bindings[17];
        this.viewTopBg = (android.view.View) bindings[4];
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
    public static ActivityCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityCheckCommentsBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.activity_check_comments, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityCheckCommentsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.activity_check_comments, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityCheckCommentsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityCheckCommentsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_check_comments_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityCheckCommentsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}