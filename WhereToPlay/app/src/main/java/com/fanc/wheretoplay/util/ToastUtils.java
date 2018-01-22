package com.fanc.wheretoplay.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanc.wheretoplay.R;


/**
 * @author tylz
 * @time 2016/3/18 0018 16:05
 * @des
 * @updateAuthor
 * @updateDate 2016/3/18 0018
 * @updateDes
 */

public class ToastUtils {
    /**
     * 弹出一个时间较短的图文Toast
     *
     * @param activity 上下文
     * @param resId    图片资源id
     * @param tipResId      提示文字
     */
    public static void makePicTextShortToast(Context activity, int resId, int tipResId) {
        View view = View.inflate(activity, R.layout.view_toast_pic, null);
        ImageView ivPic = view.findViewById(R.id.toast_iv_pic);
        ivPic.setImageResource(resId);
        TextView tvTip = view.findViewById(R.id.toast_tv_tip);
        tvTip.setText(UIUtils.getString(tipResId));
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * 弹出一个时间较短的图文Toast
     *
     * @param activity 上下文
     * @param resId    图片资源id
     * @param tip     提示文字
     */
    public static void makePicTextShortToast(Context activity, int resId, String tip) {
        View view = View.inflate(activity,R.layout.view_toast_pic, null);
        ImageView ivPic = view.findViewById(R.id.toast_iv_pic);
        ivPic.setImageResource(resId);
        TextView tvTip = view.findViewById(R.id.toast_tv_tip);
        tvTip.setText(tip);
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * 弹出一个时间较短的图文Toast
     * @param activity 上下文
     * @param tip     提示文字
     */
    public static void makePicTextShortToast(Context activity,  String tip) {
        View view = View.inflate(activity,R.layout.view_toast_pic, null);
        ImageView ivPic = view.findViewById(R.id.toast_iv_pic);
        ivPic.setVisibility(View.GONE);
        view.setPadding(UIUtils.dp2Px(10), UIUtils.dp2Px(5), UIUtils.dp2Px(10), UIUtils.dp2Px(5));
        TextView tvTip = view.findViewById(R.id.toast_tv_tip);
        tvTip.setText(tip);
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * 弹出一个时间较长的图文Toast
     *
     * @param activity 上下文
     * @param resId    图片资源id
     * @param tip      提示文字
     */
    public static void makePicTextLongToast(Activity activity, int resId, String tip) {
        View view = activity.getLayoutInflater().inflate(R.layout.view_toast_pic, null);
        ImageView ivPic = view.findViewById(R.id.toast_iv_pic);
        ivPic.setImageResource(resId);
        TextView tvTip = view.findViewById(R.id.toast_tv_tip);
        tvTip.setText(tip);
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static Toast mToast;
    public static void showShortToast(Context context,String message){
        if (mToast == null){
            //只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, message,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(message);
        }
        mToast.show();
    }
}
