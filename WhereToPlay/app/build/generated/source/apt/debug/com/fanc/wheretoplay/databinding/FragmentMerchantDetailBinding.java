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
        sViewsWithIds.put(R.id.ll, 3);
        sViewsWithIds.put(R.id.tv_tel_reserve, 4);
        sViewsWithIds.put(R.id.tv_reserve_online, 5);
        sViewsWithIds.put(R.id.tv_pay, 6);
        sViewsWithIds.put(R.id.tv_merchant_detail_title, 7);
        sViewsWithIds.put(R.id.rb_merchant, 8);
        sViewsWithIds.put(R.id.tv_merchant_detail_discount_sum, 9);
        sViewsWithIds.put(R.id.ll_merchant_detail_comment, 10);
        sViewsWithIds.put(R.id.tv_merchant_detail_grade, 11);
        sViewsWithIds.put(R.id.tv_merchant_detail_comment, 12);
        sViewsWithIds.put(R.id.rv_merchant_detail_head_image, 13);
        sViewsWithIds.put(R.id.iv_merchant_detail_right, 14);
        sViewsWithIds.put(R.id.ll_merchant_detail_image, 15);
        sViewsWithIds.put(R.id.iv_merchant_detail_1, 16);
        sViewsWithIds.put(R.id.iv_merchant_detail_2, 17);
        sViewsWithIds.put(R.id.iv_merchant_detail_3, 18);
        sViewsWithIds.put(R.id.iv_merchant_detail_4, 19);
        sViewsWithIds.put(R.id.iv_merchant_detail_5, 20);
        sViewsWithIds.put(R.id.iv_merchant_detail_6, 21);
        sViewsWithIds.put(R.id.ll_merchant_reserve_address, 22);
        sViewsWithIds.put(R.id.iv_merchant_reserve_address, 23);
        sViewsWithIds.put(R.id.tv_merchant_reserve_address, 24);
        sViewsWithIds.put(R.id.ll_merchant_brief, 25);
        sViewsWithIds.put(R.id.ll_merchant_detail_room, 26);
        sViewsWithIds.put(R.id.ll_merchant_detail_drinks, 27);
        sViewsWithIds.put(R.id.ll_merchant_detail_active, 28);
        sViewsWithIds.put(R.id.wv_merchant_detail, 29);
        sViewsWithIds.put(R.id.rv_merchant_detail_recommend, 30);
    }
    // views
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
    public final android.widget.LinearLayout ll;
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
    public final com.fanc.wheretoplay.view.DrawableCenterLeftTextView tvPay;
    @NonNull
    public final com.fanc.wheretoplay.view.DrawableCenterLeftTextView tvReserveOnline;
    @NonNull
    public final com.fanc.wheretoplay.view.DrawableCenterLeftTextView tvTelReserve;
    @NonNull
    public final android.webkit.WebView wvMerchantDetail;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMerchantDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds);
        this.ivMerchantDetail1 = (android.widget.ImageView) bindings[16];
        this.ivMerchantDetail2 = (android.widget.ImageView) bindings[17];
        this.ivMerchantDetail3 = (android.widget.ImageView) bindings[18];
        this.ivMerchantDetail4 = (android.widget.ImageView) bindings[19];
        this.ivMerchantDetail5 = (android.widget.ImageView) bindings[20];
        this.ivMerchantDetail6 = (android.widget.ImageView) bindings[21];
        this.ivMerchantDetailRight = (android.widget.ImageView) bindings[14];
        this.ivMerchantDetailService = (android.widget.ImageView) bindings[2];
        this.ivMerchantReserveAddress = (android.widget.ImageView) bindings[23];
        this.ll = (android.widget.LinearLayout) bindings[3];
        this.llMerchantBrief = (android.widget.LinearLayout) bindings[25];
        this.llMerchantDetailActive = (android.widget.LinearLayout) bindings[28];
        this.llMerchantDetailComment = (android.widget.LinearLayout) bindings[10];
        this.llMerchantDetailDrinks = (android.widget.LinearLayout) bindings[27];
        this.llMerchantDetailImage = (android.widget.LinearLayout) bindings[15];
        this.llMerchantDetailRoom = (android.widget.LinearLayout) bindings[26];
        this.llMerchantReserveAddress = (android.widget.LinearLayout) bindings[22];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbMerchant = (android.widget.RatingBar) bindings[8];
        this.rvMerchantDetailHeadImage = (android.support.v7.widget.RecyclerView) bindings[13];
        this.rvMerchantDetailRecommend = (com.fanc.wheretoplay.view.MyRecycleView) bindings[30];
        this.tmMerchantDetail = (com.fanc.wheretoplay.view.TopMenu) bindings[1];
        this.tvMerchantDetailComment = (android.widget.TextView) bindings[12];
        this.tvMerchantDetailDiscountSum = (android.widget.TextView) bindings[9];
        this.tvMerchantDetailGrade = (android.widget.TextView) bindings[11];
        this.tvMerchantDetailTitle = (android.widget.TextView) bindings[7];
        this.tvMerchantReserveAddress = (android.widget.TextView) bindings[24];
        this.tvPay = (com.fanc.wheretoplay.view.DrawableCenterLeftTextView) bindings[6];
        this.tvReserveOnline = (com.fanc.wheretoplay.view.DrawableCenterLeftTextView) bindings[5];
        this.tvTelReserve = (com.fanc.wheretoplay.view.DrawableCenterLeftTextView) bindings[4];
        this.wvMerchantDetail = (android.webkit.WebView) bindings[29];
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