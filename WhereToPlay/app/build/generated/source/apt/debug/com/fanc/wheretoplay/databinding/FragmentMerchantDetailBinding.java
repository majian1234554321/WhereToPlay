package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMerchantDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_merchant_detail, 1);
        sViewsWithIds.put(R.id.iv_merchant_detail_service, 2);
        sViewsWithIds.put(R.id.tv_merchant_detail_title, 3);
        sViewsWithIds.put(R.id.rb_merchant, 4);
        sViewsWithIds.put(R.id.tv_merchant_detail_discount_sum, 5);
        sViewsWithIds.put(R.id.ll_merchant_detail_comment, 6);
        sViewsWithIds.put(R.id.tv_merchant_detail_grade, 7);
        sViewsWithIds.put(R.id.tv_merchant_detail_comment, 8);
        sViewsWithIds.put(R.id.rv_merchant_detail_head_image, 9);
        sViewsWithIds.put(R.id.iv_merchant_detail_right, 10);
        sViewsWithIds.put(R.id.ll_merchant_detail_image, 11);
        sViewsWithIds.put(R.id.iv_merchant_detail_1, 12);
        sViewsWithIds.put(R.id.iv_merchant_detail_2, 13);
        sViewsWithIds.put(R.id.iv_merchant_detail_3, 14);
        sViewsWithIds.put(R.id.iv_merchant_detail_4, 15);
        sViewsWithIds.put(R.id.iv_merchant_detail_5, 16);
        sViewsWithIds.put(R.id.iv_merchant_detail_6, 17);
        sViewsWithIds.put(R.id.ll_merchant_reserve_address, 18);
        sViewsWithIds.put(R.id.iv_merchant_reserve_address, 19);
        sViewsWithIds.put(R.id.tv_merchant_reserve_address, 20);
        sViewsWithIds.put(R.id.ll_merchant_brief, 21);
        sViewsWithIds.put(R.id.ll_merchant_detail_room, 22);
        sViewsWithIds.put(R.id.ll_merchant_detail_drinks, 23);
        sViewsWithIds.put(R.id.ll_merchant_detail_active, 24);
        sViewsWithIds.put(R.id.wv_merchant_detail, 25);
        sViewsWithIds.put(R.id.rv_merchant_detail_recommend, 26);
        sViewsWithIds.put(R.id.cl_reserve, 27);
        sViewsWithIds.put(R.id.view, 28);
        sViewsWithIds.put(R.id.tv_reserve_online, 29);
        sViewsWithIds.put(R.id.tv_tel_reserve, 30);
        sViewsWithIds.put(R.id.tv_detail_tel, 31);
        sViewsWithIds.put(R.id.iv_tel, 32);
    }
    // views
    @NonNull
    public final android.support.constraint.ConstraintLayout clReserve;
    @NonNull
    public final android.widget.ImageView ivMerchantDetail1;
    @NonNull
    public final android.widget.ImageView ivMerchantDetail2;
    @NonNull
    public final android.widget.ImageView ivMerchantDetail3;
    @NonNull
    public final android.widget.ImageView ivMerchantDetail4;
    @NonNull
    public final android.widget.ImageView ivMerchantDetail5;
    @NonNull
    public final android.widget.ImageView ivMerchantDetail6;
    @NonNull
    public final android.widget.ImageView ivMerchantDetailRight;
    @NonNull
    public final android.widget.ImageView ivMerchantDetailService;
    @NonNull
    public final android.widget.ImageView ivMerchantReserveAddress;
    @NonNull
    public final android.widget.ImageView ivTel;
    @NonNull
    public final android.widget.LinearLayout llMerchantBrief;
    @NonNull
    public final android.widget.LinearLayout llMerchantDetailActive;
    @NonNull
    public final android.widget.LinearLayout llMerchantDetailComment;
    @NonNull
    public final android.widget.LinearLayout llMerchantDetailDrinks;
    @NonNull
    public final android.widget.LinearLayout llMerchantDetailImage;
    @NonNull
    public final android.widget.LinearLayout llMerchantDetailRoom;
    @NonNull
    public final android.widget.LinearLayout llMerchantReserveAddress;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.RatingBar rbMerchant;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvMerchantDetailHeadImage;
    @NonNull
    public final com.fanc.wheretoplay.view.MyRecycleView rvMerchantDetailRecommend;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmMerchantDetail;
    @NonNull
    public final android.widget.TextView tvDetailTel;
    @NonNull
    public final android.widget.TextView tvMerchantDetailComment;
    @NonNull
    public final android.widget.TextView tvMerchantDetailDiscountSum;
    @NonNull
    public final android.widget.TextView tvMerchantDetailGrade;
    @NonNull
    public final android.widget.TextView tvMerchantDetailTitle;
    @NonNull
    public final android.widget.TextView tvMerchantReserveAddress;
    @NonNull
    public final android.widget.TextView tvReserveOnline;
    @NonNull
    public final android.widget.TextView tvTelReserve;
    @NonNull
    public final android.view.View view;
    @NonNull
    public final android.webkit.WebView wvMerchantDetail;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMerchantDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds);
        this.clReserve = (android.support.constraint.ConstraintLayout) bindings[27];
        this.ivMerchantDetail1 = (android.widget.ImageView) bindings[12];
        this.ivMerchantDetail2 = (android.widget.ImageView) bindings[13];
        this.ivMerchantDetail3 = (android.widget.ImageView) bindings[14];
        this.ivMerchantDetail4 = (android.widget.ImageView) bindings[15];
        this.ivMerchantDetail5 = (android.widget.ImageView) bindings[16];
        this.ivMerchantDetail6 = (android.widget.ImageView) bindings[17];
        this.ivMerchantDetailRight = (android.widget.ImageView) bindings[10];
        this.ivMerchantDetailService = (android.widget.ImageView) bindings[2];
        this.ivMerchantReserveAddress = (android.widget.ImageView) bindings[19];
        this.ivTel = (android.widget.ImageView) bindings[32];
        this.llMerchantBrief = (android.widget.LinearLayout) bindings[21];
        this.llMerchantDetailActive = (android.widget.LinearLayout) bindings[24];
        this.llMerchantDetailComment = (android.widget.LinearLayout) bindings[6];
        this.llMerchantDetailDrinks = (android.widget.LinearLayout) bindings[23];
        this.llMerchantDetailImage = (android.widget.LinearLayout) bindings[11];
        this.llMerchantDetailRoom = (android.widget.LinearLayout) bindings[22];
        this.llMerchantReserveAddress = (android.widget.LinearLayout) bindings[18];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbMerchant = (android.widget.RatingBar) bindings[4];
        this.rvMerchantDetailHeadImage = (android.support.v7.widget.RecyclerView) bindings[9];
        this.rvMerchantDetailRecommend = (com.fanc.wheretoplay.view.MyRecycleView) bindings[26];
        this.tmMerchantDetail = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvDetailTel = (android.widget.TextView) bindings[31];
        this.tvMerchantDetailComment = (android.widget.TextView) bindings[8];
        this.tvMerchantDetailDiscountSum = (android.widget.TextView) bindings[5];
        this.tvMerchantDetailGrade = (android.widget.TextView) bindings[7];
        this.tvMerchantDetailTitle = (android.widget.TextView) bindings[3];
        this.tvMerchantReserveAddress = (android.widget.TextView) bindings[20];
        this.tvReserveOnline = (android.widget.TextView) bindings[29];
        this.tvTelReserve = (android.widget.TextView) bindings[30];
        this.view = (android.view.View) bindings[28];
        this.wvMerchantDetail = (android.webkit.WebView) bindings[25];
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
    public static FragmentMerchantDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMerchantDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMerchantDetailBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_merchant_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentMerchantDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMerchantDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_merchant_detail, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentMerchantDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMerchantDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_merchant_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMerchantDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}