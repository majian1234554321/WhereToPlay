// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.view.TitleBarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderFragments_ViewBinding implements Unbinder {
  private OrderFragments target;

  @UiThread
  public OrderFragments_ViewBinding(OrderFragments target, View source) {
    this.target = target;

    target.tbv = Utils.findRequiredViewAsType(source, R.id.tbv, "field 'tbv'", TitleBarView.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderFragments target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tbv = null;
    target.tabLayout = null;
    target.viewpager = null;
  }
}
