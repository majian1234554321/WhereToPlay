// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.view.OrderetailsItemView;
import com.fanc.wheretoplay.view.TitleBarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailsOrderActivity_ViewBinding implements Unbinder {
  private DetailsOrderActivity target;

  private View view2131297239;

  private View view2131297028;

  private View view2131297204;

  private View view2131297027;

  private View view2131297183;

  private View view2131297022;

  private View view2131296854;

  @UiThread
  public DetailsOrderActivity_ViewBinding(DetailsOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailsOrderActivity_ViewBinding(final DetailsOrderActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_release, "field 'tvRelease' and method 'onViewClicked'");
    target.tvRelease = Utils.castView(view, R.id.tv_release, "field 'tvRelease'", TextView.class);
    view2131297239 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cancel, "field 'tvCancel' and method 'onViewClicked'");
    target.tvCancel = Utils.castView(view, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
    view2131297028 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_pay, "field 'tvPay' and method 'onViewClicked'");
    target.tvPay = Utils.castView(view, R.id.tv_pay, "field 'tvPay'", TextView.class);
    view2131297204 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvStoreName = Utils.findRequiredViewAsType(source, R.id.tv_storeName, "field 'tvStoreName'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.oi1 = Utils.findRequiredViewAsType(source, R.id.oi1, "field 'oi1'", OrderetailsItemView.class);
    target.oi2 = Utils.findRequiredViewAsType(source, R.id.oi2, "field 'oi2'", OrderetailsItemView.class);
    target.oi3 = Utils.findRequiredViewAsType(source, R.id.oi3, "field 'oi3'", OrderetailsItemView.class);
    target.oi4 = Utils.findRequiredViewAsType(source, R.id.oi4, "field 'oi4'", OrderetailsItemView.class);
    target.oi5 = Utils.findRequiredViewAsType(source, R.id.oi5, "field 'oi5'", OrderetailsItemView.class);
    target.oi6 = Utils.findRequiredViewAsType(source, R.id.oi6, "field 'oi6'", OrderetailsItemView.class);
    target.oi7 = Utils.findRequiredViewAsType(source, R.id.oi7, "field 'oi7'", OrderetailsItemView.class);
    target.oi8 = Utils.findRequiredViewAsType(source, R.id.oi8, "field 'oi8'", OrderetailsItemView.class);
    target.oi9 = Utils.findRequiredViewAsType(source, R.id.oi9, "field 'oi9'", OrderetailsItemView.class);
    target.oi10 = Utils.findRequiredViewAsType(source, R.id.oi10, "field 'oi10'", OrderetailsItemView.class);
    target.tv1 = Utils.findRequiredViewAsType(source, R.id.tv1, "field 'tv1'", TextView.class);
    target.tv2 = Utils.findRequiredViewAsType(source, R.id.tv2, "field 'tv2'", TextView.class);
    target.tv3 = Utils.findRequiredViewAsType(source, R.id.tv3, "field 'tv3'", TextView.class);
    target.tv4 = Utils.findRequiredViewAsType(source, R.id.tv4, "field 'tv4'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_call, "field 'tv_call' and method 'onViewClicked'");
    target.tv_call = Utils.castView(view, R.id.tv_call, "field 'tv_call'", TextView.class);
    view2131297027 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_msn, "field 'tv_msn' and method 'onViewClicked'");
    target.tv_msn = Utils.castView(view, R.id.tv_msn, "field 'tv_msn'", TextView.class);
    view2131297183 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tbv = Utils.findRequiredViewAsType(source, R.id.tbv, "field 'tbv'", TitleBarView.class);
    target.llButtom = Utils.findRequiredViewAsType(source, R.id.ll_buttom, "field 'llButtom'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_back, "field 'tvBack' and method 'onViewClicked'");
    target.tvBack = Utils.castView(view, R.id.tv_back, "field 'tvBack'", TextView.class);
    view2131297022 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ll = Utils.findRequiredViewAsType(source, R.id.ll, "field 'll'", RelativeLayout.class);
    target.itemViewLine = Utils.findRequiredView(source, R.id.item_view_line, "field 'itemViewLine'");
    target.itemViewLine2 = Utils.findRequiredView(source, R.id.item_view_line2, "field 'itemViewLine2'");
    view = Utils.findRequiredView(source, R.id.rl, "field 'rl' and method 'onViewClicked'");
    target.rl = Utils.castView(view, R.id.rl, "field 'rl'", RelativeLayout.class);
    view2131296854 = view;
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
    DetailsOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRelease = null;
    target.tvCancel = null;
    target.tvPay = null;
    target.tvStoreName = null;
    target.tvAddress = null;
    target.oi1 = null;
    target.oi2 = null;
    target.oi3 = null;
    target.oi4 = null;
    target.oi5 = null;
    target.oi6 = null;
    target.oi7 = null;
    target.oi8 = null;
    target.oi9 = null;
    target.oi10 = null;
    target.tv1 = null;
    target.tv2 = null;
    target.tv3 = null;
    target.tv4 = null;
    target.tv_call = null;
    target.tv_msn = null;
    target.tbv = null;
    target.llButtom = null;
    target.tvBack = null;
    target.ll = null;
    target.itemViewLine = null;
    target.itemViewLine2 = null;
    target.rl = null;

    view2131297239.setOnClickListener(null);
    view2131297239 = null;
    view2131297028.setOnClickListener(null);
    view2131297028 = null;
    view2131297204.setOnClickListener(null);
    view2131297204 = null;
    view2131297027.setOnClickListener(null);
    view2131297027 = null;
    view2131297183.setOnClickListener(null);
    view2131297183 = null;
    view2131297022.setOnClickListener(null);
    view2131297022 = null;
    view2131296854.setOnClickListener(null);
    view2131296854 = null;
  }
}
