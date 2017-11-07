package com.fanc.wheretoplay.databinding;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemCommentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    public final com.fanc.wheretoplay.view.CircleImageView civItemComment;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvItemCommentContent;
    @NonNull
    public final android.widget.TextView tvItemCommentDate;
    @NonNull
    public final android.widget.TextView tvItemCommentLabel;
    @NonNull
    public final android.widget.TextView tvItemCommentNickname;
    // variables
    @Nullable
    private com.fanc.wheretoplay.datamodel.CommentDetail.Comment mComment;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemCommentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.civItemComment = (com.fanc.wheretoplay.view.CircleImageView) bindings[1];
        this.civItemComment.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemCommentContent = (android.widget.TextView) bindings[5];
        this.tvItemCommentContent.setTag(null);
        this.tvItemCommentDate = (android.widget.TextView) bindings[3];
        this.tvItemCommentDate.setTag(null);
        this.tvItemCommentLabel = (android.widget.TextView) bindings[4];
        this.tvItemCommentLabel.setTag(null);
        this.tvItemCommentNickname = (android.widget.TextView) bindings[2];
        this.tvItemCommentNickname.setTag(null);
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
        if (BR.comment == variableId) {
            setComment((com.fanc.wheretoplay.datamodel.CommentDetail.Comment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setComment(@Nullable com.fanc.wheretoplay.datamodel.CommentDetail.Comment Comment) {
        updateRegistration(0, Comment);
        this.mComment = Comment;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.comment);
        super.requestRebind();
    }
    @Nullable
    public com.fanc.wheretoplay.datamodel.CommentDetail.Comment getComment() {
        return mComment;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeComment((com.fanc.wheretoplay.datamodel.CommentDetail.Comment) object, fieldId);
        }
        return false;
    }
    private boolean onChangeComment(com.fanc.wheretoplay.datamodel.CommentDetail.Comment Comment, int fieldId) {
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
        java.lang.String commentCreatedTime = null;
        java.lang.String commentSys = null;
        java.lang.String commentAvatar = null;
        java.lang.String commentNickname = null;
        int javaLangStringEqualsCommentCusViewGONEViewVISIBLE = 0;
        java.lang.String dateFormatUtilGetYYYYMMDDStringCommentCreatedTime = null;
        boolean javaLangStringEqualsCommentCus = false;
        int javaLangStringEqualsCommentSysViewGONEViewVISIBLE = 0;
        java.lang.String commentCus = null;
        com.fanc.wheretoplay.datamodel.CommentDetail.Comment comment = mComment;
        boolean javaLangStringEqualsCommentSys = false;

        if ((dirtyFlags & 0x3L) != 0) {



                if (comment != null) {
                    // read comment.created_time
                    commentCreatedTime = comment.created_time;
                    // read comment.sys
                    commentSys = comment.sys;
                    // read comment.avatar
                    commentAvatar = comment.avatar;
                    // read comment.nickname
                    commentNickname = comment.nickname;
                    // read comment.cus
                    commentCus = comment.cus;
                }


                // read DateFormatUtil.getYYYY_MM_DDString(comment.created_time)
                dateFormatUtilGetYYYYMMDDStringCommentCreatedTime = com.fanc.wheretoplay.util.DateFormatUtil.getYYYY_MM_DDString(commentCreatedTime);
                // read "".equals(comment.sys)
                javaLangStringEqualsCommentSys = "".equals(commentSys);
                // read "".equals(comment.cus)
                javaLangStringEqualsCommentCus = "".equals(commentCus);
            if((dirtyFlags & 0x3L) != 0) {
                if(javaLangStringEqualsCommentSys) {
                        dirtyFlags |= 0x20L;
                }
                else {
                        dirtyFlags |= 0x10L;
                }
            }
            if((dirtyFlags & 0x3L) != 0) {
                if(javaLangStringEqualsCommentCus) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read "".equals(comment.sys) ? View.GONE : View.VISIBLE
                javaLangStringEqualsCommentSysViewGONEViewVISIBLE = ((javaLangStringEqualsCommentSys) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read "".equals(comment.cus) ? View.GONE : View.VISIBLE
                javaLangStringEqualsCommentCusViewGONEViewVISIBLE = ((javaLangStringEqualsCommentCus) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.fanc.wheretoplay.datamodel.CommentDetail.Comment.setUserImage(this.civItemComment, commentAvatar);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCommentContent, commentCus);
            this.tvItemCommentContent.setVisibility(javaLangStringEqualsCommentCusViewGONEViewVISIBLE);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCommentDate, dateFormatUtilGetYYYYMMDDStringCommentCreatedTime);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCommentLabel, commentSys);
            this.tvItemCommentLabel.setVisibility(javaLangStringEqualsCommentSysViewGONEViewVISIBLE);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemCommentNickname, commentNickname);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemCommentBinding>inflate(inflater, com.fanc.wheretoplay.R.layout.item_comment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemCommentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCommentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.fanc.wheretoplay.R.layout.item_comment, null, false), bindingComponent);
    }
    @NonNull
    public static ItemCommentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemCommentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_comment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemCommentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): comment
        flag 1 (0x2L): null
        flag 2 (0x3L): "".equals(comment.cus) ? View.GONE : View.VISIBLE
        flag 3 (0x4L): "".equals(comment.cus) ? View.GONE : View.VISIBLE
        flag 4 (0x5L): "".equals(comment.sys) ? View.GONE : View.VISIBLE
        flag 5 (0x6L): "".equals(comment.sys) ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}