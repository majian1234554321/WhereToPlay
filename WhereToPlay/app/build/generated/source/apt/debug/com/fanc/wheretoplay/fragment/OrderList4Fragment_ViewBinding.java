// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.view.PullToRefreshLayout;
import com.fanc.wheretoplay.view.PullableRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderList4Fragment_ViewBinding implements Unbinder {
  private OrderList4Fragment target;

  @UiThread
  public OrderList4Fragment_ViewBinding(OrderList4Fragment target, View source) {
    this.target = target;

    target.mRvOrder = Utils.findRequiredViewAsType(source, R.id.rv_pay, "field 'mRvOrder'", PullableRecyclerView.class);
    target.ptrlPayReserve = Utils.findRequiredViewAsType(source, R.id.ptrl_pay_reserve, "field 'ptrlPayReserve'", PullToRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderList4Fragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRvOrder = null;
    target.ptrlPayReserve = null;
  }
}
