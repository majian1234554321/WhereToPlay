package com.fanc.wheretoplay.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Administrator on 2017/6/20.
 */

public class GridDivider extends RecyclerView.ItemDecoration {


    private Drawable mDividerDarwable;
    private int mDividerHight = 1;
    private Paint mColorPaint;
    private int column;// 显示的列数


    public final int[] ATRRS = new int[]{android.R.attr.listDivider};

    public GridDivider(Context context) {
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDividerDarwable = ta.getDrawable(0);
        ta.recycle();
    }

    /*
     int dividerHight  分割线的线宽
     int dividerColor  分割线的颜色
     */
    public GridDivider(Context context, int column, int dividerHight, int dividerColor) {
        this(context);
        this.column = column;
        mDividerHight = dividerHight;
        mColorPaint = new Paint();
        mColorPaint.setColor(dividerColor);
    }

    /*
     int dividerHight  分割线的线宽
     Drawable dividerDrawable  图片分割线
     */
    public GridDivider(Context context, int column, int dividerHight, Drawable dividerDrawable) {
        this(context);
        this.column = column;
        mDividerHight = dividerHight;
        mDividerDarwable = dividerDrawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //画水平和垂直分割线
        drawHorizontalDivider(c, parent);
        drawVerticalDivider(c, parent);
    }

    public void drawVerticalDivider(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;

            int left = 0;
            int right = 0;

            //左边第一列
            if ((i % column) == 0) {
                //item左边分割线
                left = child.getLeft();
                right = left + mDividerHight;
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
                if (mColorPaint != null) {
                    c.drawRect(left, top, right, bottom, mColorPaint);
                }
                //item右边分割线
                left = child.getRight() + params.rightMargin - mDividerHight;
                right = left + mDividerHight;
            } else {
                //非左边第一列
                left = child.getRight() + params.rightMargin - mDividerHight;
                right = left + mDividerHight;
                //画分割线
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
            }
            // 4  计算中间item的divider情况
            //item左边分割线
            left = child.getLeft();//该处一开始是left = 0，没考虑到布局中recyclerview的padding情况
            right = left + mDividerHight;
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            //item右边分割线
            left = child.getRight() + params.rightMargin;
            right = left + mDividerHight;
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }

        }
    }

    public void drawHorizontalDivider(Canvas c, RecyclerView parent) {

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin - mDividerHight;
            final int right = child.getRight() + params.rightMargin;
            int top = 0;
            int bottom = 0;

            // 最上面一行
            if ((i / column) == 0) {
                //当前item最上面的分割线
                top = child.getTop();
                //当前item下面的分割线
                bottom = top + mDividerHight;
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
                if (mColorPaint != null) {
                    c.drawRect(left, top, right, bottom, mColorPaint);
                }
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerHight;
            } else {
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerHight;
                //画分割线
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
            }
            // 2  计算中间item的divider情况
            //画item最上面的分割线
            top = child.getTop(); //该处一开始是top = 0，没考虑到布局中recyclerview的padding情况
            bottom = top + mDividerHight;
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            //画item下面的分割线
            top = child.getBottom() + params.bottomMargin;
            bottom = top + mDividerDarwable.getIntrinsicHeight();
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }
        }
    }

    // 获取列数
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    // 是否是最后一列
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0){
                // 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0) {
                    // 如果是最后一列，则不需要绘制右边
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    // 是否是最后一行
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
            // 如果是最后一行，则不需要绘制底部
            outRect.set(0, 0, mDividerHight, 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount)) {
            // 如果是最后一列，则不需要绘制右边
            outRect.set(0, 0, 0, mDividerHight);
        } else {
            outRect.set(0, 0, mDividerHight, mDividerHight);
        }
    }
}
