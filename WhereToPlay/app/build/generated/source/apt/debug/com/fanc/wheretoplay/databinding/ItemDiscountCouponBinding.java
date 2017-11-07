package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemDiscountCouponBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_item_discount_coupon_text, 3);
        sViewsWithIds.put(R.id.rb_item_discount_status, 4);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.RadioButton rbItemDiscountStatus;
    @NonNull
    public final android.widget.TextView tvItemDiscountCouponDeadline;
    @NonNull
    public final android.widget.TextView tvItemDiscountCouponSum;
    @NonNull
    public final android.widget.TextView tvItemDiscountCouponText;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.DiscountCouponList.DiscountCoupon mDiscount;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemDiscountCouponBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbItemDiscountStatus = (android.widget.RadioButton) bindings[4];
        this.tvItemDiscountCouponDeadline = (android.widget.TextView) bindings[2];
        this.tvItemDiscountCouponDeadline.setTag(null);
        this.tvItemDiscountCouponSum = (android.widget.TextView) bindings[1];
        this.tvItemDiscountCouponSum.setTag(null);
        this.tvItemDiscountCouponText = (android.widget.TextView) bindings[3];
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
        if (BR.discount == variableId) {
            setDiscount((com.fanc.wheretoplay.datamodel.DiscountCouponList.DiscountCoupon) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDiscount(@Nullable com.fanc.wheretoplay.datamodel.DiscountCouponList.DiscountCoupon Discount) {
        this.mDiscount = Discount;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.discount);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.DiscountCouponList.DiscountCoupon getDiscount() {
        return mDiscount;
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
        java.lang.String discountGetStartTime = null;
        java.lang.String dateFormatUtilGetDateFormatStringDiscountGetStartTime = null;
        java.lang.String dateFormatUtilGetDateFormatStringDiscountGetStartTimeJavaLangStringDateFormatUtilGetDateFormatStringDiscountGetEndTime = null;
        java.lang.String discountGetPrice = null;
        int intDoubleParseDoubleDiscountGetPrice = 0;
        java.lang.String dateFormatUtilGetDateFormatStringDiscountGetEndTime = null;
        java.lang.String stringValueOfIntDoubleParseDoubleDiscountGetPrice = null;
        java.lang.String dateFormatUtilGetDateFormatStringDiscountGetStartTimeJavaLangString = null;
        java.lang.String discountGetEndTime = null;
        com.fanc.wheretoplay.datamodel.DiscountCouponList.DiscountCoupon discount = mDiscount;
        double doubleParseDoubleDiscountGetPrice = 0.0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (discount != null) {
                    // read discount.getStart_time()
                    discountGetStartTime = discount.getStart_time();
                    // read discount.getPrice()
                    discountGetPrice = discount.getPrice();
                    // read discount.getEnd_time()
                    discountGetEndTime = discount.getEnd_time();
                }


                // read DateFormatUtil.getDateFormatString(discount.getStart_time())
                dateFormatUtilGetDateFormatStringDiscountGetStartTime = com.fanc.wheretoplay.util.DateFormatUtil.getDateFormatString(discountGetStartTime);
                // read Double.parseDouble(discount.getPrice())
                doubleParseDoubleDiscountGetPrice = java.lang.Double.parseDouble(discountGetPrice);
                // read DateFormatUtil.getDateFormatString(discount.getEnd_time())
                dateFormatUtilGetDateFormatStringDiscountGetEndTime = com.fanc.wheretoplay.util.DateFormatUtil.getDateFormatString(discountGetEndTime);


                // read (DateFormatUtil.getDateFormatString(discount.getStart_time())) + ("-")
                dateFormatUtilGetDateFormatStringDiscountGetStartTimeJavaLangString = (dateFormatUtilGetDateFormatStringDiscountGetStartTime) + ("-");
                // read (int) Double.parseDouble(discount.getPrice())
                intDoubleParseDoubleDiscountGetPrice = ((int) (doubleParseDoubleDiscountGetPrice));


                // read ((DateFormatUtil.getDateFormatString(discount.getStart_time())) + ("-")) + (DateFormatUtil.getDateFormatString(discount.getEnd_time()))
                dateFormatUtilGetDateFormatStringDiscountGetStartTimeJavaLangStringDateFormatUtilGetDateFormatStringDiscountGetEndTime = (dateFormatUtilGetDateFormatStringDiscountGetStartTimeJavaLangString) + (dateFormatUtilGetDateFormatStringDiscountGetEndTime);
                // read String.valueOf((int) Double.parseDouble(discount.getPrice()))
                stringValueOfIntDoubleParseDoubleDiscountGetPrice = java.lang.String.valueOf(intDoubleParseDoubleDiscountGetPrice);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemDiscountCouponDeadline, dateFormatUtilGetDateFormatStringDiscountGetStartTimeJavaLangStringDateFormatUtilGetDateFormatStringDiscountGetEndTime);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemDiscountCouponSum, stringValueOfIntDoubleParseDoubleDiscountGetPrice);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemDiscountCouponBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemDiscountCouponBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemDiscountCouponBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_discount_coupon, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemDiscountCouponBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemDiscountCouponBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_discount_coupon, null, false), bindingComponent);
    }
    @NonNull
    public static ItemDiscountCouponBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemDiscountCouponBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_discount_coupon_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemDiscountCouponBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): discount
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}