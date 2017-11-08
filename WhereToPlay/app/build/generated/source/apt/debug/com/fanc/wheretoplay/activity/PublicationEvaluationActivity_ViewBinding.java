// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.view.TitleBarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PublicationEvaluationActivity_ViewBinding implements Unbinder {
  private PublicationEvaluationActivity target;

  private View view2131296317;

  @UiThread
  public PublicationEvaluationActivity_ViewBinding(PublicationEvaluationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PublicationEvaluationActivity_ViewBinding(final PublicationEvaluationActivity target,
      View source) {
    this.target = target;

    View view;
    target.tbv = Utils.findRequiredViewAsType(source, R.id.tbv, "field 'tbv'", TitleBarView.class);
    target.tvMerchantDetailTitle = Utils.findRequiredViewAsType(source, R.id.tv_merchant_detail_title, "field 'tvMerchantDetailTitle'", TextView.class);
    target.tvMerchantReserveAddress = Utils.findRequiredViewAsType(source, R.id.tv_merchant_reserve_address, "field 'tvMerchantReserveAddress'", TextView.class);
    target.tvMerchantDetailDiscountSum = Utils.findRequiredViewAsType(source, R.id.tv_merchant_detail_discount_sum, "field 'tvMerchantDetailDiscountSum'", TextView.class);
    target.rvImage = Utils.findRequiredViewAsType(source, R.id.rv_image, "field 'rvImage'", RecyclerView.class);
    target.checkbox = Utils.findRequiredViewAsType(source, R.id.checkbox, "field 'checkbox'", CheckBox.class);
    target.ratingbar1 = Utils.findRequiredViewAsType(source, R.id.ratingbar1, "field 'ratingbar1'", RatingBar.class);
    target.ratingbar2 = Utils.findRequiredViewAsType(source, R.id.ratingbar2, "field 'ratingbar2'", RatingBar.class);
    target.ratingbar3 = Utils.findRequiredViewAsType(source, R.id.ratingbar3, "field 'ratingbar3'", RatingBar.class);
    view = Utils.findRequiredView(source, R.id.btn_commit, "field 'btnCommit' and method 'onViewClicked'");
    target.btnCommit = Utils.castView(view, R.id.btn_commit, "field 'btnCommit'", Button.class);
    view2131296317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.et_content = Utils.findRequiredViewAsType(source, R.id.et_content, "field 'et_content'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PublicationEvaluationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tbv = null;
    target.tvMerchantDetailTitle = null;
    target.tvMerchantReserveAddress = null;
    target.tvMerchantDetailDiscountSum = null;
    target.rvImage = null;
    target.checkbox = null;
    target.ratingbar1 = null;
    target.ratingbar2 = null;
    target.ratingbar3 = null;
    target.btnCommit = null;
    target.et_content = null;

    view2131296317.setOnClickListener(null);
    view2131296317 = null;
  }
}
