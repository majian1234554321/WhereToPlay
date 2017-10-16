package com.fanc.wheretoplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.util.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PageHorizontalScrollView extends HorizontalScrollView {

    private int subChildCount = 0;
    private ViewGroup firstChild = null;
    private int downX = 0;
    private int currentPage = 0;
    private ArrayList<Integer> pointList = new ArrayList<Integer>();
    HashMap<Integer, TextView> children = new HashMap<>();
    boolean noScroll;

    OnPageSelectedListener listener;

    public PageHorizontalScrollView(Context context) {
        this(context, null);
    }

    public PageHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        receiveChildInfo();
    }

    public void receiveChildInfo() {

        firstChild = (ViewGroup) getChildAt(0);
        if (firstChild != null) {
            subChildCount = firstChild.getChildCount();
            children.clear();
            for (int i = 0; i < subChildCount; i++) {
                // 设置长度，与边距
                View child = firstChild.getChildAt(i);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (UIUtils.getScreenWidth() * 0.6);
                if (i == subChildCount - 1) {
                    layoutParams.setMargins(UIUtils.dp2Px(10), 0, (int) (UIUtils.getScreenWidth() * 0.4 - UIUtils.dp2Px(10)), 0);
                }
                child.setLayoutParams(layoutParams);
                children.put(i, (TextView) child);
                // 获取左边的坐标
                if (((View) firstChild.getChildAt(i)).getWidth() > 0) {
                    pointList.add(((View) firstChild.getChildAt(i)).getLeft());
                }
            }
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (noScroll) {
            return false;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE: {

            }
            break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                if (Math.abs((ev.getX() - downX)) > UIUtils.getScreenWidth() / 4) {
                    if (ev.getX() - downX > 0) {
                        smoothScrollToPrePage();
                    } else {
                        smoothScrollToNextPage();
                    }
                } else {
                    smoothScrollToCurrent();
                }
                return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    private GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            for (int i = 0; i < children.size(); i++) {
                TextView tv = (TextView) children.get(i);
                int x = (int) e.getX();
                int y = (int) e.getY();
                if (x > tv.getLeft() - getScrollX() && x < tv.getRight() - getScrollX()) {
                    if (y > tv.getTop() && y < tv.getBottom()) {
                        if (currentPage != i) {
                            gotoPage(i);
                        }
                        break;
                    }
                }
            }
            return super.onSingleTapConfirmed(e);
        }
    });

    private void smoothScrollToCurrent() {
        // 减去10do边距，增加界面体现效果,界面布局边距给的多少就减多少
        smoothScrollTo(pointList.get(currentPage) - UIUtils.dp2Px(10), 0);
        // 选中item的背景
        setSelectedBackGround(currentPage);
        // 选中监听
        if (listener != null) {
            listener.onPageSelected(currentPage, children.get(currentPage));
        }

    }

    private void smoothScrollToNextPage() {
        if (currentPage < subChildCount - 1) {
            currentPage++;
            smoothScrollTo(pointList.get(currentPage) - UIUtils.dp2Px(10), 0);
            // 选中item的背景
            setSelectedBackGround(currentPage);
            // 选中监听
            if (listener != null) {
                listener.onPageSelected(currentPage, children.get(currentPage));
            }
        }
    }

    private void smoothScrollToPrePage() {
        if (currentPage > 0) {
            currentPage--;
            smoothScrollTo(pointList.get(currentPage) - UIUtils.dp2Px(10), 0);
            // 选中item的背景
            setSelectedBackGround(currentPage);
            // 选中监听
            if (listener != null) {
                listener.onPageSelected(currentPage, children.get(currentPage));
            }
        }
    }

    private void setSelectedBackGround(int position) {
        for (int i = 0; i < children.size(); i++) {
            TextView view = children.get(i);
            if (i == position) {
                view.setBackgroundResource(R.drawable.shape_comment_detail_label_selected);
//                view.setSelected(true);
            } else {
                view.setBackgroundResource(R.drawable.shape_comment_detail_label_unselected);
//                view.setSelected(false);
            }
        }
    }

    /**
     * 下一页
     */
    public void nextPage() {
        smoothScrollToNextPage();
    }

    /**
     * 上一页
     */
    public void prePage() {
        smoothScrollToPrePage();
    }

    /**
     * 跳转到指定的页面
     *
     * @param page
     * @return
     */
    public boolean gotoPage(int page) {
        if (page >= 0 && page <= subChildCount - 1) {
            smoothScrollTo(pointList.get(page) - UIUtils.dp2Px(10), 0);
            currentPage = page;
            // 选中item的背景
            setSelectedBackGround(currentPage);
            // 选中监听
            if (listener != null) {
                listener.onPageSelected(currentPage, children.get(currentPage));
            }
            return true;
        }
        return false;
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public View getSelectedItem() {
        return children.get(currentPage);
    }

    public int getPageCount() {
        return subChildCount;
    }

    public void setOnPageSelectedListener(OnPageSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnPageSelectedListener {
        void onPageSelected(int position, View view);
    }

}
