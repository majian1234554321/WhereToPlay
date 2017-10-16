package com.fanc.wheretoplay.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * 购物车列表左滑菜单帮助类
 *
 * @author njmsir
 *         Created by njmsir on 2017/5/22.
 */

public class ItemSlideHelper implements RecyclerView.OnItemTouchListener, GestureDetector.OnGestureListener {
    private final int DEFAULT_DURATION = 200;
    private View targetView;
    private int activePointerId;
    private int lastX;
    private int lastY;
    private boolean isDragging;

    private int maxVelocity;
    private final int minVelocity;

    private Animator expandAndCollapseAnim;
    private final int scaledTouchSlop;

    private GestureDetectorCompat gestureDetector;
    private Callback callback;

    public ItemSlideHelper(Context context, Callback callback) {
        this.callback = callback;
        //手势处理
        gestureDetector = new GestureDetectorCompat(context, this);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        //能够识别的最小滑动举动
        scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        //最大加速度
        maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        //最小加速度
        minVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        int action = MotionEventCompat.getActionMasked(e);
        int x = (int) e.getX();
        int y = (int) e.getY();

        //如果RecyclerView滚动状态不是空闲targetView不是空
        if (rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
            if (targetView != null) {
                //隐藏已经打开
                smoothHorizontalExpandOrCollapse(DEFAULT_DURATION / 2);
                targetView = null;
            }
            return false;
        }

        //如果正在运行动画,直接拦截什么都不做
        if (expandAndCollapseAnim != null && expandAndCollapseAnim.isRunning()) return true;

        boolean needIntercept = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                activePointerId = MotionEventCompat.getPointerId(e, 0);
                lastX = (int) e.getX();
                lastY = (int) e.getY();

                /**
                 * 如果之前有一个已经打开的项目,当此次点击事件没有发生在右侧的菜单中则返回TRUE
                 * 如果点击的是右侧菜单那么返回FALSE这样做的原因是因为菜单需要响应OnClick
                 */
                if (targetView != null) return !inView(x, y);

                //查找需要显示菜单的view
                targetView = callback.findTargetView(x, y);

                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                if (Math.abs(deltaY) > Math.abs(deltaX)) return false;

                //如果移动距离达到要求,则拦截
                needIntercept = isDragging = targetView != null && Math.abs(deltaX) >= scaledTouchSlop;
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                /**
                 * 没有发生过拦截走到这里
                 */
                if (isExpanded()) {
                    if (inView(x, y)) {
                        //如果走到这里这个ACTION_UP的事件会发生在右侧菜单中
                    } else {
                        //拦截事件,防止targetView执行onClick事件
                        needIntercept = true;
                    }

                    //折叠菜单
                    smoothHorizontalExpandOrCollapse(DEFAULT_DURATION / 2);
                }
                targetView = null;
                break;
        }
        return needIntercept;
    }

    private boolean isExpanded() {
        return targetView != null && targetView.getScrollX() == getHorizontalRange();
    }

    private boolean isCollapsed() {
        return targetView != null && targetView.getScrollX() == 0;
    }

    /**
     * 根据targetView的scrollX计算出targetView的偏移量,这样知道这个point是否在右侧的菜单中
     *
     * @param x
     * @param y
     * @return
     */
    private boolean inView(int x, int y) {
        if (targetView == null) return false;

        int scrollX = targetView.getScrollX();
        int left = targetView.getWidth() - scrollX;
        int top = targetView.getTop();
        int right = left + getHorizontalRange();
        int bottom = targetView.getBottom();
        Rect rect = new Rect(left, top, right, bottom);
        return rect.contains(x, y);
    }


    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        if (expandAndCollapseAnim != null && expandAndCollapseAnim.isRunning() || targetView == null)
            return;

        //如果要响应fling事件设置将isDragging设为false
        if (gestureDetector.onTouchEvent(e)) {
            isDragging = false;
            return;
        }

        int x = (int) e.getX();
        int y = (int) e.getY();
        int action = MotionEventCompat.getActionMasked(e);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //RecyclerView不会转发这个Down事件
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (lastX - e.getX());
                if (isDragging) horizontalDrag(deltaX);
                lastX = x;
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isDragging) {
                    if (!smoothHorizontalExpandOrCollapse(0) && isCollapsed()) targetView = null;
                    isDragging = false;
                }
                break;
        }


    }

    /**
     * 根据touch事件来滚动View的scrollX
     *
     * @param deltaX
     */
    private void horizontalDrag(int deltaX) {
        int scrollX = targetView.getScrollX();
        int scrollY = targetView.getScrollY();
        if (scrollX + deltaX <= 0) {
            targetView.scrollTo(0, scrollY);
            return;
        }

        int horizontalRange = getHorizontalRange();
        scrollX += deltaX;
        if (Math.abs(scrollX) < horizontalRange) targetView.scrollTo(scrollX, scrollY);
        else targetView.scrollTo(horizontalRange, scrollY);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (Math.abs(velocityX) > minVelocity && Math.abs(velocityX) < maxVelocity) {
            if (!smoothHorizontalExpandOrCollapse(velocityX)) {
                if (isCollapsed()) targetView = null;
                return true;
            }
        }
        return false;
    }

    /**
     * 根据当前scrollX的位置判断是展开还是折叠
     *
     * @param velocityX 如果不等于0那么这是一次fling事件,否则是一次ACTION_UP或者ACTION_CANCEL
     * @return
     */
    private boolean smoothHorizontalExpandOrCollapse(float velocityX) {
        int scrollX = targetView.getScrollX();
        int scrollRange = getHorizontalRange();

        if (expandAndCollapseAnim != null) return false;

        int to = 0;
        int duration = DEFAULT_DURATION;

        if (velocityX == 0) {
            //如果已经展开一半,平滑展开
            if (scrollX > scrollRange / 2) to = scrollRange;
        } else {
            if (velocityX > 0) to = 0;
            else to = scrollRange;
            duration = (int) ((1.f - Math.abs(velocityX) / maxVelocity) * DEFAULT_DURATION);
        }

        if (to == scrollX) return false;

        expandAndCollapseAnim = ObjectAnimator.ofInt(targetView, "scrollX", to);
        expandAndCollapseAnim.setDuration(duration);
        expandAndCollapseAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                expandAndCollapseAnim = null;
                if (isCollapsed()) targetView = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                expandAndCollapseAnim = null;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        expandAndCollapseAnim.start();
        return true;
    }

    public int getHorizontalRange() {
        RecyclerView.ViewHolder viewHolder = callback.getChildViewHolder(targetView);
        return callback.getHorzontalRange(viewHolder);
    }


    public interface Callback {
        int getHorzontalRange(RecyclerView.ViewHolder holder);

        RecyclerView.ViewHolder getChildViewHolder(View childView);

        View findTargetView(float x, float y);
    }

}
