// Generated code from Butter Knife. Do not modify!
package com.fanc.wheretoplay.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fanc.wheretoplay.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareActivity_ViewBinding implements Unbinder {
  private ShareActivity target;

  private View view2131296347;

  private View view2131296344;

  private View view2131296348;

  private View view2131296345;

  private View view2131296346;

  private View view2131296350;

  @UiThread
  public ShareActivity_ViewBinding(ShareActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareActivity_ViewBinding(final ShareActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_share_wechat, "field 'btnShareWechat' and method 'onClick'");
    target.btnShareWechat = Utils.castView(view, R.id.btn_share_wechat, "field 'btnShareWechat'", TextView.class);
    view2131296347 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_share_pyq, "field 'btnSharePyq' and method 'onClick'");
    target.btnSharePyq = Utils.castView(view, R.id.btn_share_pyq, "field 'btnSharePyq'", TextView.class);
    view2131296344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_share_weibo, "field 'btnShareWeibo' and method 'onClick'");
    target.btnShareWeibo = Utils.castView(view, R.id.btn_share_weibo, "field 'btnShareWeibo'", TextView.class);
    view2131296348 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_share_qq, "field 'btnShareQq' and method 'onClick'");
    target.btnShareQq = Utils.castView(view, R.id.btn_share_qq, "field 'btnShareQq'", TextView.class);
    view2131296345 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_share_qzone, "field 'btnShareQzone' and method 'onClick'");
    target.btnShareQzone = Utils.castView(view, R.id.btn_share_qzone, "field 'btnShareQzone'", TextView.class);
    view2131296346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.popLayout2 = Utils.findRequiredViewAsType(source, R.id.pop_layout2, "field 'popLayout2'", LinearLayout.class);
    target.popLayout = Utils.findRequiredViewAsType(source, R.id.pop_layout, "field 'popLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_sms, "method 'onClick'");
    view2131296350 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnShareWechat = null;
    target.btnSharePyq = null;
    target.btnShareWeibo = null;
    target.btnShareQq = null;
    target.btnShareQzone = null;
    target.popLayout2 = null;
    target.popLayout = null;

    view2131296347.setOnClickListener(null);
    view2131296347 = null;
    view2131296344.setOnClickListener(null);
    view2131296344 = null;
    view2131296348.setOnClickListener(null);
    view2131296348 = null;
    view2131296345.setOnClickListener(null);
    view2131296345 = null;
    view2131296346.setOnClickListener(null);
    view2131296346 = null;
    view2131296350.setOnClickListener(null);
    view2131296350 = null;
  }
}
