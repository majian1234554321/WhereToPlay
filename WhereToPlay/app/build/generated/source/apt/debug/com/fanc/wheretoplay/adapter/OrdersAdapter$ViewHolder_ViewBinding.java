// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrdersAdapter$ViewHolder_ViewBinding implements Unbinder {
  private OrdersAdapter.ViewHolder target;

  @UiThread
  public OrdersAdapter$ViewHolder_ViewBinding(OrdersAdapter.ViewHolder target, View source) {
    this.target = target;

    target.cbPayItemStatus = Utils.findRequiredViewAsType(source, R.id.cb_pay_item_status, "field 'cbPayItemStatus'", CheckBox.class);
    target.ivPayItem = Utils.findRequiredViewAsType(source, R.id.iv_pay_item, "field 'ivPayItem'", ImageView.class);
    target.tvPayItemTitle = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_title, "field 'tvPayItemTitle'", TextView.class);
    target.tvPayItemTime = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_time, "field 'tvPayItemTime'", TextView.class);
    target.tvPayItemRealTime = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_real_time, "field 'tvPayItemRealTime'", TextView.class);
    target.tvPayItemRoomCategory = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_room_category, "field 'tvPayItemRoomCategory'", TextView.class);
    target.tvPayItemRoom = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_room, "field 'tvPayItemRoom'", TextView.class);
    target.tvPayItemDecorateCategory = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_decorate_category, "field 'tvPayItemDecorateCategory'", TextView.class);
    target.tvPayItemDecorate = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_decorate, "field 'tvPayItemDecorate'", TextView.class);
    target.tvPayItemReserveCode = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_reserve_code, "field 'tvPayItemReserveCode'", TextView.class);
    target.tvPayItemReserveRealCode = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_reserve_real_code, "field 'tvPayItemReserveRealCode'", TextView.class);
    target.tvPayItemPrice = Utils.findRequiredViewAsType(source, R.id.tv_pay_item_price, "field 'tvPayItemPrice'", TextView.class);
    target.tv_storeName = Utils.findRequiredViewAsType(source, R.id.tv_storeName, "field 'tv_storeName'", TextView.class);
    target.tv_payState = Utils.findRequiredViewAsType(source, R.id.tv_payState, "field 'tv_payState'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrdersAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cbPayItemStatus = null;
    target.ivPayItem = null;
    target.tvPayItemTitle = null;
    target.tvPayItemTime = null;
    target.tvPayItemRealTime = null;
    target.tvPayItemRoomCategory = null;
    target.tvPayItemRoom = null;
    target.tvPayItemDecorateCategory = null;
    target.tvPayItemDecorate = null;
    target.tvPayItemReserveCode = null;
    target.tvPayItemReserveRealCode = null;
    target.tvPayItemPrice = null;
    target.tv_storeName = null;
    target.tv_payState = null;
  }
}
