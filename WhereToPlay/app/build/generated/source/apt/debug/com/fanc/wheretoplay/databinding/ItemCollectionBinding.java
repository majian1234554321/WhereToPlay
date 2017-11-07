package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemCollectionBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cb_collection_item_status, 7);
        sViewsWithIds.put(R.id.tv_item_collection_rise, 8);
    }
    // views
    @NonNull
    public final android.widget.CheckBox cbCollectionItemStatus;
    @NonNull
    public final android.widget.ImageView ivItemCollection;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemCollectionArea;
    @NonNull
    public final android.widget.TextView tvItemCollectionDecorate;
    @NonNull
    public final android.widget.TextView tvItemCollectionDistance;
    @NonNull
    public final android.widget.TextView tvItemCollectionPrice;
    @NonNull
    public final android.widget.TextView tvItemCollectionRise;
    @NonNull
    public final android.widget.TextView tvItemCollectionTitle;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.CollectionList.Collection mCollection;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemCollectionBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.cbCollectionItemStatus = (android.widget.CheckBox) bindings[7];
        this.ivItemCollection = (android.widget.ImageView) bindings[1];
        this.ivItemCollection.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemCollectionArea = (android.widget.TextView) bindings[4];
        this.tvItemCollectionArea.setTag(null);
        this.tvItemCollectionDecorate = (android.widget.TextView) bindings[3];
        this.tvItemCollectionDecorate.setTag(null);
        this.tvItemCollectionDistance = (android.widget.TextView) bindings[5];
        this.tvItemCollectionDistance.setTag(null);
        this.tvItemCollectionPrice = (android.widget.TextView) bindings[6];
        this.tvItemCollectionPrice.setTag(null);
        this.tvItemCollectionRise = (android.widget.TextView) bindings[8];
        this.tvItemCollectionTitle = (android.widget.TextView) bindings[2];
        this.tvItemCollectionTitle.setTag(null);
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
        if (BR.collection == variableId) {
            setCollection((com.fanc.wheretoplay.datamodel.CollectionList.Collection) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCollection(@Nullable com.fanc.wheretoplay.datamodel.CollectionList.Collection Collection) {
        updateRegistration(0, Collection);
        this.mCollection = Collection;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.collection);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.CollectionList.Collection getCollection() {
        return mCollection;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeCollection((com.fanc.wheretoplay.datamodel.CollectionList.Collection) object, fieldId);
        }
        return false;
    }
    private boolean onChangeCollection(com.fanc.wheretoplay.datamodel.CollectionList.Collection Collection, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        java.lang.String collectionPath = null;
        java.lang.String collectionMinPriceTvItemCollectionPriceAndroidStringCurrency = null;
        java.lang.String collectionDecorateJavaLangString = null;
        float floatParseFloatCollectionDistanceInt1000 = 0f;
        java.lang.String collectionDistance = null;
        java.lang.String collectionDistanceJavaLangStringM = null;
        float floatParseFloatCollectionDistance = 0f;
        boolean javaLangString1EqualsCollectionDistance = false;
        java.lang.String collectionDistrict = null;
        java.lang.String collectionMinPrice = null;
        java.lang.String collectionGrade = null;
        java.lang.String floatParseFloatCollectionDistanceInt1000JavaLangStringKm = null;
        java.lang.String javaLangString1EqualsCollectionDistanceIntegerParseIntCollectionDistanceInt1000FloatParseFloatCollectionDistanceInt1000JavaLangStringKmCollectionDistanceJavaLangStringMJavaLangString = null;
        boolean integerParseIntCollectionDistanceInt1000 = false;
        java.lang.String integerParseIntCollectionDistanceInt1000FloatParseFloatCollectionDistanceInt1000JavaLangStringKmCollectionDistanceJavaLangStringM = null;
        java.lang.String collectionDecorateJavaLangStringCollectionGrade = null;
        java.lang.String collectionName = null;
        boolean JavaLangString1EqualsCollectionDistance1 = false;
        com.fanc.wheretoplay.datamodel.CollectionList.Collection collection = mCollection;
        int integerParseIntCollectionDistance = 0;
        java.lang.String collectionDecorate = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (collection != null) {
                    // read collection.path
                    collectionPath = collection.getPath();
                    // read collection.distance
                    collectionDistance = collection.getDistance();
                    // read collection.district
                    collectionDistrict = collection.getDistrict();
                    // read collection.min_price
                    collectionMinPrice = collection.getMin_price();
                    // read collection.grade
                    collectionGrade = collection.getGrade();
                    // read collection.name
                    collectionName = collection.getName();
                    // read collection.decorate
                    collectionDecorate = collection.getDecorate();
                }


                // read "-1".equals(collection.distance)
                JavaLangString1EqualsCollectionDistance1 = "-1".equals(collectionDistance);
                // read (collection.min_price) + (@android:string/currency)
                collectionMinPriceTvItemCollectionPriceAndroidStringCurrency = (collectionMinPrice) + (tvItemCollectionPrice.getResources().getString(R.string.currency));
                // read (collection.decorate) + (", ")
                collectionDecorateJavaLangString = (collectionDecorate) + (", ");


                // read !"-1".equals(collection.distance)
                javaLangString1EqualsCollectionDistance = !JavaLangString1EqualsCollectionDistance1;
                // read ((collection.decorate) + (", ")) + (collection.grade)
                collectionDecorateJavaLangStringCollectionGrade = (collectionDecorateJavaLangString) + (collectionGrade);
            if((dirtyFlags & 0x3L) != 0) {
                if(javaLangString1EqualsCollectionDistance) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x8L) != 0) {



                if (collection != null) {
                    // read collection.distance
                    collectionDistance = collection.getDistance();
                }


                // read Integer.parseInt(collection.distance)
                integerParseIntCollectionDistance = java.lang.Integer.parseInt(collectionDistance);


                // read Integer.parseInt(collection.distance) > 1000
                integerParseIntCollectionDistanceInt1000 = (integerParseIntCollectionDistance) > (1000);
            if((dirtyFlags & 0x8L) != 0) {
                if(integerParseIntCollectionDistanceInt1000) {
                        dirtyFlags |= 0x20L;
                }
                else {
                        dirtyFlags |= 0x10L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x10L) != 0) {

                // read (collection.distance) + ("m")
                collectionDistanceJavaLangStringM = (collectionDistance) + ("m");
        }
        if ((dirtyFlags & 0x20L) != 0) {

                // read Float.parseFloat(collection.distance)
                floatParseFloatCollectionDistance = java.lang.Float.parseFloat(collectionDistance);


                // read (Float.parseFloat(collection.distance)) / (1000)
                floatParseFloatCollectionDistanceInt1000 = (floatParseFloatCollectionDistance) / (1000);


                // read ((Float.parseFloat(collection.distance)) / (1000)) + ("km")
                floatParseFloatCollectionDistanceInt1000JavaLangStringKm = (floatParseFloatCollectionDistanceInt1000) + ("km");
        }

        if ((dirtyFlags & 0x8L) != 0) {

                // read Integer.parseInt(collection.distance) > 1000 ? ((Float.parseFloat(collection.distance)) / (1000)) + ("km") : (collection.distance) + ("m")
                integerParseIntCollectionDistanceInt1000FloatParseFloatCollectionDistanceInt1000JavaLangStringKmCollectionDistanceJavaLangStringM = ((integerParseIntCollectionDistanceInt1000) ? (floatParseFloatCollectionDistanceInt1000JavaLangStringKm) : (collectionDistanceJavaLangStringM));
        }

        if ((dirtyFlags & 0x3L) != 0) {

                // read !"-1".equals(collection.distance) ? Integer.parseInt(collection.distance) > 1000 ? ((Float.parseFloat(collection.distance)) / (1000)) + ("km") : (collection.distance) + ("m") : ""
                javaLangString1EqualsCollectionDistanceIntegerParseIntCollectionDistanceInt1000FloatParseFloatCollectionDistanceInt1000JavaLangStringKmCollectionDistanceJavaLangStringMJavaLangString = ((javaLangString1EqualsCollectionDistance) ? (integerParseIntCollectionDistanceInt1000FloatParseFloatCollectionDistanceInt1000JavaLangStringKmCollectionDistanceJavaLangStringM) : (""));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.fanc.wheretoplay.datamodel.WaiterList.WaiterInfo.setWaiterImage(this.ivItemCollection, collectionPath);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCollectionArea, collectionDistrict);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCollectionDecorate, collectionDecorateJavaLangStringCollectionGrade);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCollectionDistance, javaLangString1EqualsCollectionDistanceIntegerParseIntCollectionDistanceInt1000FloatParseFloatCollectionDistanceInt1000JavaLangStringKmCollectionDistanceJavaLangStringMJavaLangString);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCollectionPrice, collectionMinPriceTvItemCollectionPriceAndroidStringCurrency);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCollectionTitle, collectionName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemCollectionBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCollectionBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemCollectionBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_collection, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemCollectionBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCollectionBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_collection, null, false), bindingComponent);
    }
    @NonNull
    public static ItemCollectionBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCollectionBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_collection_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemCollectionBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): collection
        flag 1 (0x2L): null
        flag 2 (0x3L): !"-1".equals(collection.distance) ? Integer.parseInt(collection.distance) > 1000 ? ((Float.parseFloat(collection.distance)) / (1000)) + ("km") : (collection.distance) + ("m") : ""
        flag 3 (0x4L): !"-1".equals(collection.distance) ? Integer.parseInt(collection.distance) > 1000 ? ((Float.parseFloat(collection.distance)) / (1000)) + ("km") : (collection.distance) + ("m") : ""
        flag 4 (0x5L): Integer.parseInt(collection.distance) > 1000 ? ((Float.parseFloat(collection.distance)) / (1000)) + ("km") : (collection.distance) + ("m")
        flag 5 (0x6L): Integer.parseInt(collection.distance) > 1000 ? ((Float.parseFloat(collection.distance)) / (1000)) + ("km") : (collection.distance) + ("m")
    flag mapping end*/
    //end
}