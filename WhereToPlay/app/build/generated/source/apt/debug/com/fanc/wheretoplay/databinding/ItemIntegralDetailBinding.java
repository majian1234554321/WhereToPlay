package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemIntegralDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_item_integral_get_way, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemIntegralGetWay;
    @NonNull
    public final android.widget.TextView tvItemIntegralNo;
    @NonNull
    public final android.widget.TextView tvItemIntegralTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.ScoreList.ContentBean.ListBean mScore;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemIntegralDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemIntegralGetWay = (android.widget.TextView) bindings[3];
        this.tvItemIntegralNo = (android.widget.TextView) bindings[2];
        this.tvItemIntegralNo.setTag(null);
        this.tvItemIntegralTitle = (android.widget.TextView) bindings[1];
        this.tvItemIntegralTitle.setTag(null);
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
        if (BR.score == variableId) {
            setScore((com.fanc.wheretoplay.datamodel.ScoreList.ContentBean.ListBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setScore(@Nullable com.fanc.wheretoplay.datamodel.ScoreList.ContentBean.ListBean Score) {
        this.mScore = Score;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.score);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.ScoreList.ContentBean.ListBean getScore() {
        return mScore;
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
        int intDoubleParseDoubleScoreScoreDetail = 0;
        double doubleParseDoubleScoreScoreDetail = 0.0;
        com.fanc.wheretoplay.datamodel.ScoreList.ContentBean.ListBean score = mScore;
        java.lang.String javaLangStringIntDoubleParseDoubleScoreScoreDetail = null;
        java.lang.String scoreScoreDetail = null;
        java.lang.String scoreName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (score != null) {
                    // read score.score_detail
                    scoreScoreDetail = score.score_detail;
                    // read score.name
                    scoreName = score.name;
                }


                // read Double.parseDouble(score.score_detail)
                doubleParseDoubleScoreScoreDetail = java.lang.Double.parseDouble(scoreScoreDetail);


                // read (int) Double.parseDouble(score.score_detail)
                intDoubleParseDoubleScoreScoreDetail = ((int) (doubleParseDoubleScoreScoreDetail));


                // read ("+") + ((int) Double.parseDouble(score.score_detail))
                javaLangStringIntDoubleParseDoubleScoreScoreDetail = ("+") + (intDoubleParseDoubleScoreScoreDetail);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemIntegralNo, javaLangStringIntDoubleParseDoubleScoreScoreDetail);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemIntegralTitle, scoreName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemIntegralDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemIntegralDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemIntegralDetailBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_integral_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemIntegralDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemIntegralDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_integral_detail, null, false), bindingComponent);
    }
    @NonNull
    public static ItemIntegralDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemIntegralDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_integral_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemIntegralDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): score
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}