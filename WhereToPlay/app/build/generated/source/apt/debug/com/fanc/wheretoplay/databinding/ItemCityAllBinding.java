package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemCityAllBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_item_city_all_letter, 2);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout llItemCityAll;
    @NonNull
    public final android.widget.TextView tvItemCityAllLetter;
    @NonNull
    public final android.widget.TextView tvItemCityAllName;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.CityResource.City mCity;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemCityAllBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.llItemCityAll = (android.widget.LinearLayout) bindings[0];
        this.llItemCityAll.setTag(null);
        this.tvItemCityAllLetter = (android.widget.TextView) bindings[2];
        this.tvItemCityAllName = (android.widget.TextView) bindings[1];
        this.tvItemCityAllName.setTag(null);
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
        if (BR.city == variableId) {
            setCity((com.fanc.wheretoplay.datamodel.CityResource.City) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCity(@Nullable com.fanc.wheretoplay.datamodel.CityResource.City City) {
        this.mCity = City;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.city);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.CityResource.City getCity() {
        return mCity;
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
        com.fanc.wheretoplay.datamodel.CityResource.City city = mCity;
        java.lang.String cityName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (city != null) {
                    // read city.name
                    cityName = city.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCityAllName, cityName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemCityAllBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCityAllBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemCityAllBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_city_all, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemCityAllBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCityAllBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_city_all, null, false), bindingComponent);
    }
    @NonNull
    public static ItemCityAllBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCityAllBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_city_all_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemCityAllBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): city
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}