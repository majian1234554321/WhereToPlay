package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentCommentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tm_comment, 12);
        sViewsWithIds.put(R.id.tv_comment_reserved, 13);
        sViewsWithIds.put(R.id.tv_comment_time, 14);
        sViewsWithIds.put(R.id.tv_comment_room_category, 15);
        sViewsWithIds.put(R.id.tv_comment_decorate_category, 16);
        sViewsWithIds.put(R.id.tv_comment_reserve_code, 17);
        sViewsWithIds.put(R.id.tv_comment_price, 18);
        sViewsWithIds.put(R.id.tv_comment_earnest, 19);
        sViewsWithIds.put(R.id.rv_comment_environment, 20);
        sViewsWithIds.put(R.id.ll_comment_environment_edit, 21);
        sViewsWithIds.put(R.id.et_comment_environment_edit, 22);
        sViewsWithIds.put(R.id.rv_comment_atmosphere, 23);
        sViewsWithIds.put(R.id.ll_comment_atmosphere_edit, 24);
        sViewsWithIds.put(R.id.et_comment_atmosphere_edit, 25);
        sViewsWithIds.put(R.id.rv_comment_service, 26);
        sViewsWithIds.put(R.id.ll_comment_service_edit, 27);
        sViewsWithIds.put(R.id.et_comment_service_edit, 28);
        sViewsWithIds.put(R.id.rv_comment_other, 29);
        sViewsWithIds.put(R.id.ll_comment_other_edit, 30);
        sViewsWithIds.put(R.id.et_comment_other_edit, 31);
    }
    // views
    @NonNull
    public final android.widget.EditText etCommentAtmosphereEdit;
    @NonNull
    public final android.widget.EditText etCommentEnvironmentEdit;
    @NonNull
    public final android.widget.EditText etCommentOtherEdit;
    @NonNull
    public final android.widget.EditText etCommentServiceEdit;
    @NonNull
    public final android.widget.ImageView ivCommentAtmosphere;
    @NonNull
    public final android.widget.ImageView ivCommentEnvironment;
    @NonNull
    public final android.widget.ImageView ivCommentOther;
    @NonNull
    public final android.widget.ImageView ivCommentService;
    @NonNull
    public final android.widget.LinearLayout llCommentAtmosphereEdit;
    @NonNull
    public final android.widget.LinearLayout llCommentEnvironmentEdit;
    @NonNull
    public final android.widget.LinearLayout llCommentOtherEdit;
    @NonNull
    public final android.widget.LinearLayout llCommentServiceEdit;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvCommentAtmosphere;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvCommentEnvironment;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvCommentOther;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvCommentService;
    @NonNull
    public final com.fanc.wheretoplay.view.TopMenu tmComment;
    @NonNull
    public final android.widget.TextView tvCommentDecorate;
    @NonNull
    public final android.widget.TextView tvCommentDecorateCategory;
    @NonNull
    public final android.widget.TextView tvCommentEarnest;
    @NonNull
    public final android.widget.TextView tvCommentPrice;
    @NonNull
    public final android.widget.TextView tvCommentRealEarnest;
    @NonNull
    public final android.widget.TextView tvCommentRealPrice;
    @NonNull
    public final android.widget.TextView tvCommentRealTime;
    @NonNull
    public final android.widget.TextView tvCommentReserveCode;
    @NonNull
    public final android.widget.TextView tvCommentReserveRealCode;
    @NonNull
    public final android.widget.TextView tvCommentReserved;
    @NonNull
    public final android.widget.TextView tvCommentRoom;
    @NonNull
    public final android.widget.TextView tvCommentRoomCategory;
    @NonNull
    public final android.widget.TextView tvCommentTime;
    @NonNull
    public final android.widget.TextView tvCommentTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.fragment.CommentFragment mClick;
    @Nullable
    private com.fanc.wheretoplay.datamodel.CommentPage.Order mOrder;
    // values
    // listeners
    private OnClickListenerImpl mClickOnViewClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentCommentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds);
        this.etCommentAtmosphereEdit = (android.widget.EditText) bindings[25];
        this.etCommentEnvironmentEdit = (android.widget.EditText) bindings[22];
        this.etCommentOtherEdit = (android.widget.EditText) bindings[31];
        this.etCommentServiceEdit = (android.widget.EditText) bindings[28];
        this.ivCommentAtmosphere = (android.widget.ImageView) bindings[9];
        this.ivCommentAtmosphere.setTag(null);
        this.ivCommentEnvironment = (android.widget.ImageView) bindings[8];
        this.ivCommentEnvironment.setTag(null);
        this.ivCommentOther = (android.widget.ImageView) bindings[11];
        this.ivCommentOther.setTag(null);
        this.ivCommentService = (android.widget.ImageView) bindings[10];
        this.ivCommentService.setTag(null);
        this.llCommentAtmosphereEdit = (android.widget.LinearLayout) bindings[24];
        this.llCommentEnvironmentEdit = (android.widget.LinearLayout) bindings[21];
        this.llCommentOtherEdit = (android.widget.LinearLayout) bindings[30];
        this.llCommentServiceEdit = (android.widget.LinearLayout) bindings[27];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvCommentAtmosphere = (android.support.v7.widget.RecyclerView) bindings[23];
        this.rvCommentEnvironment = (android.support.v7.widget.RecyclerView) bindings[20];
        this.rvCommentOther = (android.support.v7.widget.RecyclerView) bindings[29];
        this.rvCommentService = (android.support.v7.widget.RecyclerView) bindings[26];
        this.tmComment = (com.fanc.wheretoplay.view.TopMenu) bindings[12];
        this.tvCommentDecorate = (android.widget.TextView) bindings[4];
        this.tvCommentDecorate.setTag(null);
        this.tvCommentDecorateCategory = (android.widget.TextView) bindings[16];
        this.tvCommentEarnest = (android.widget.TextView) bindings[19];
        this.tvCommentPrice = (android.widget.TextView) bindings[18];
        this.tvCommentRealEarnest = (android.widget.TextView) bindings[7];
        this.tvCommentRealEarnest.setTag(null);
        this.tvCommentRealPrice = (android.widget.TextView) bindings[6];
        this.tvCommentRealPrice.setTag(null);
        this.tvCommentRealTime = (android.widget.TextView) bindings[2];
        this.tvCommentRealTime.setTag(null);
        this.tvCommentReserveCode = (android.widget.TextView) bindings[17];
        this.tvCommentReserveRealCode = (android.widget.TextView) bindings[5];
        this.tvCommentReserveRealCode.setTag(null);
        this.tvCommentReserved = (android.widget.TextView) bindings[13];
        this.tvCommentRoom = (android.widget.TextView) bindings[3];
        this.tvCommentRoom.setTag(null);
        this.tvCommentRoomCategory = (android.widget.TextView) bindings[15];
        this.tvCommentTime = (android.widget.TextView) bindings[14];
        this.tvCommentTitle = (android.widget.TextView) bindings[1];
        this.tvCommentTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.click == variableId) {
            setClick((com.fanc.wheretoplay.fragment.CommentFragment) variable);
        }
        else if (BR.order == variableId) {
            setOrder((com.fanc.wheretoplay.datamodel.CommentPage.Order) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.fanc.wheretoplay.fragment.CommentFragment Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.fragment.CommentFragment getClick() {
        return mClick;
    }
    public void setOrder(@Nullable com.fanc.wheretoplay.datamodel.CommentPage.Order Order) {
        this.mOrder = Order;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.order);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.CommentPage.Order getOrder() {
        return mOrder;
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
        java.lang.String orderArrivalTime = null;
        java.lang.String orderTotal = null;
        java.lang.String orderPrepay = null;
        java.lang.String dateFormatUtilGetDateFormatMMDDHHmmOrderArrivalTime = null;
        java.lang.String tvCommentRealEarnestAndroidStringCurrencyCharOrderPrepay = null;
        java.lang.String orderRoomtype = null;
        java.lang.String tvCommentRealPriceAndroidStringCurrencyCharOrderTotal = null;
        java.lang.String orderDecoratetype = null;
        com.fanc.wheretoplay.fragment.CommentFragment click = mClick;
        com.fanc.wheretoplay.datamodel.CommentPage.Order order = mOrder;
        java.lang.String orderStoreName = null;
        android.view.View.OnClickListener clickOnViewClickAndroidViewViewOnClickListener = null;
        java.lang.String orderBookSn = null;
        java.lang.String dateFormatUtilGetDateFormatMMDDHHmmOrderArrivalTimeTvCommentRealTimeAndroidStringBefore = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (click != null) {
                    // read click::onViewClick
                    clickOnViewClickAndroidViewViewOnClickListener = (((mClickOnViewClickAndroidViewViewOnClickListener == null) ? (mClickOnViewClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickOnViewClickAndroidViewViewOnClickListener).setValue(click));
                }
        }
        if ((dirtyFlags & 0x6L) != 0) {



                if (order != null) {
                    // read order.arrival_time
                    orderArrivalTime = order.getArrival_time();
                    // read order.total
                    orderTotal = order.getTotal();
                    // read order.prepay
                    orderPrepay = order.getPrepay();
                    // read order.roomtype
                    orderRoomtype = order.getRoomtype();
                    // read order.decoratetype
                    orderDecoratetype = order.getDecoratetype();
                    // read order.store_name
                    orderStoreName = order.getStore_name();
                    // read order.book_sn
                    orderBookSn = order.getBook_sn();
                }


                // read DateFormatUtil.getDateFormatMMDDHHmm(order.arrival_time)
                dateFormatUtilGetDateFormatMMDDHHmmOrderArrivalTime = com.fanc.wheretoplay.util.DateFormatUtil.getDateFormatMMDDHHmm(orderArrivalTime);
                // read (@android:string/currency_char) + (order.total)
                tvCommentRealPriceAndroidStringCurrencyCharOrderTotal = (tvCommentRealPrice.getResources().getString(R.string.currency_char)) + (orderTotal);
                // read (@android:string/currency_char) + (order.prepay)
                tvCommentRealEarnestAndroidStringCurrencyCharOrderPrepay = (tvCommentRealEarnest.getResources().getString(R.string.currency_char)) + (orderPrepay);


                // read (DateFormatUtil.getDateFormatMMDDHHmm(order.arrival_time)) + (@android:string/before)
                dateFormatUtilGetDateFormatMMDDHHmmOrderArrivalTimeTvCommentRealTimeAndroidStringBefore = (dateFormatUtilGetDateFormatMMDDHHmmOrderArrivalTime) + (tvCommentRealTime.getResources().getString(R.string.before));
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            this.ivCommentAtmosphere.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivCommentEnvironment.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivCommentOther.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
            this.ivCommentService.setOnClickListener(clickOnViewClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentDecorate, orderDecoratetype);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentRealEarnest, tvCommentRealEarnestAndroidStringCurrencyCharOrderPrepay);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentRealPrice, tvCommentRealPriceAndroidStringCurrencyCharOrderTotal);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentRealTime, dateFormatUtilGetDateFormatMMDDHHmmOrderArrivalTimeTvCommentRealTimeAndroidStringBefore);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentReserveRealCode, orderBookSn);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentRoom, orderRoomtype);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvCommentTitle, orderStoreName);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.fanc.wheretoplay.fragment.CommentFragment value;
        public OnClickListenerImpl setValue(com.fanc.wheretoplay.fragment.CommentFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onViewClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentCommentBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.fragment_comment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentCommentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.fragment_comment, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentCommentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCommentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_comment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentCommentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): order
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}