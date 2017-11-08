// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.view.TopMenu;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderPayActivity_ViewBinding implements Unbinder {
  private OrderPayActivity target;

  private View view2131296329;

  private View view2131296659;

  private View view2131296660;

  private View view2131296657;

  private View view2131296658;

  @UiThread
  public OrderPayActivity_ViewBinding(OrderPayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderPayActivity_ViewBinding(final OrderPayActivity target, View source) {
    this.target = target;

    View view;
    target.tmPayBill = Utils.findRequiredViewAsType(source, R.id.tm_pay_bill, "field 'tmPayBill'", TopMenu.class);
    target.tvPayBillStore = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_store, "field 'tvPayBillStore'", TextView.class);
    target.tvPayBillDiscountReal = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_discount_real, "field 'tvPayBillDiscountReal'", TextView.class);
    target.tvPayBillAddress = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_address, "field 'tvPayBillAddress'", TextView.class);
    target.tvPayBillDistance = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_distance, "field 'tvPayBillDistance'", TextView.class);
    target.etPayBillConsumeSum = Utils.findRequiredViewAsType(source, R.id.et_pay_bill_consume_sum, "field 'etPayBillConsumeSum'", EditText.class);
    target.cbNotParticipation = Utils.findRequiredViewAsType(source, R.id.cb_not_participation, "field 'cbNotParticipation'", CheckBox.class);
    target.etPayBillNotParticipationDiscountSum = Utils.findRequiredViewAsType(source, R.id.et_pay_bill_not_participation_discount_sum, "field 'etPayBillNotParticipationDiscountSum'", EditText.class);
    target.tvPayBillDownPaymentSum = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_down_payment_sum, "field 'tvPayBillDownPaymentSum'", TextView.class);
    target.tvPayBillDiscountCoupon = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_discount_coupon, "field 'tvPayBillDiscountCoupon'", TextView.class);
    target.ivPayBillDiscountCoupon = Utils.findRequiredViewAsType(source, R.id.iv_pay_bill_discount_coupon, "field 'ivPayBillDiscountCoupon'", ImageView.class);
    target.rbPayBillWeixin = Utils.findRequiredViewAsType(source, R.id.rb_pay_bill_weixin, "field 'rbPayBillWeixin'", RadioButton.class);
    target.rbPayBillAli = Utils.findRequiredViewAsType(source, R.id.rb_pay_bill_ali, "field 'rbPayBillAli'", RadioButton.class);
    target.rbPayBillBalance = Utils.findRequiredViewAsType(source, R.id.rb_pay_bill_balance, "field 'rbPayBillBalance'", RadioButton.class);
    target.rgPayBill = Utils.findRequiredViewAsType(source, R.id.rg_pay_bill, "field 'rgPayBill'", RadioGroup.class);
    target.tvPayBillPaySumReal = Utils.findRequiredViewAsType(source, R.id.tv_pay_bill_pay_sum_real, "field 'tvPayBillPaySumReal'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_pay_bill, "field 'btnPayBill' and method 'onViewClicked'");
    target.btnPayBill = Utils.castView(view, R.id.btn_pay_bill, "field 'btnPayBill'", Button.class);
    view2131296329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.relativeLayout, "field 'relativeLayout'", RelativeLayout.class);
    target.llNotParticipation = Utils.findRequiredViewAsType(source, R.id.ll_not_participation, "field 'llNotParticipation'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_pay_bill_discount_coupon, "field 'llPayBillDiscountCoupon' and method 'onViewClicked'");
    target.llPayBillDiscountCoupon = Utils.castView(view, R.id.ll_pay_bill_discount_coupon, "field 'llPayBillDiscountCoupon'", LinearLayout.class);
    view2131296659 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_pay_bill_weixin, "field 'llPayBillWeixin' and method 'onViewClicked'");
    target.llPayBillWeixin = Utils.castView(view, R.id.ll_pay_bill_weixin, "field 'llPayBillWeixin'", LinearLayout.class);
    view2131296660 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_pay_bill_ali, "field 'llPayBillAli' and method 'onViewClicked'");
    target.llPayBillAli = Utils.castView(view, R.id.ll_pay_bill_ali, "field 'llPayBillAli'", LinearLayout.class);
    view2131296657 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_pay_bill_balance, "field 'llPayBillBalance' and method 'onViewClicked'");
    target.llPayBillBalance = Utils.castView(view, R.id.ll_pay_bill_balance, "field 'llPayBillBalance'", LinearLayout.class);
    view2131296658 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderPayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tmPayBill = null;
    target.tvPayBillStore = null;
    target.tvPayBillDiscountReal = null;
    target.tvPayBillAddress = null;
    target.tvPayBillDistance = null;
    target.etPayBillConsumeSum = null;
    target.cbNotParticipation = null;
    target.etPayBillNotParticipationDiscountSum = null;
    target.tvPayBillDownPaymentSum = null;
    target.tvPayBillDiscountCoupon = null;
    target.ivPayBillDiscountCoupon = null;
    target.rbPayBillWeixin = null;
    target.rbPayBillAli = null;
    target.rbPayBillBalance = null;
    target.rgPayBill = null;
    target.tvPayBillPaySumReal = null;
    target.btnPayBill = null;
    target.relativeLayout = null;
    target.llNotParticipation = null;
    target.llPayBillDiscountCoupon = null;
    target.llPayBillWeixin = null;
    target.llPayBillAli = null;
    target.llPayBillBalance = null;

    view2131296329.setOnClickListener(null);
    view2131296329 = null;
    view2131296659.setOnClickListener(null);
    view2131296659 = null;
    view2131296660.setOnClickListener(null);
    view2131296660 = null;
    view2131296657.setOnClickListener(null);
    view2131296657 = null;
    view2131296658.setOnClickListener(null);
    view2131296658 = null;
  }
}
