package com.fanc.wheretoplay.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PullableRecyclerView extends RecyclerView implements Pullable {

    boolean canPullDown, canPullUp;

    public PullableRecyclerView(Context context) {
        this(context, null);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setCanPullDown(boolean canPullDown) {
        this.canPullDown = canPullDown;
    }

    public void setCanPullUp(boolean canPullUp) {
        this.canPullUp = canPullUp;
    }

    @Override
    public boolean canPullDown() {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager.getChildCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return canPullDown;
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (linearLayoutManager.findFirstVisibleItemPosition() == 0
                    && linearLayoutManager.getChildAt(0).getTop() >= 0) {
                // 滑到ListView的顶部了
                return canPullDown;
            }
        }
        return false;

    }

    @Override
    public boolean canPullUp() {
        LayoutManager layoutManager = getLayoutManager();
        if (getChildCount() == 0) {
            // 没有item的时候也可以上拉加载
            return canPullUp;
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (linearLayoutManager.findLastVisibleItemPosition() == (linearLayoutManager.getItemCount() - 1)) {
                View view = getChildAt(linearLayoutManager.findLastVisibleItemPosition() - linearLayoutManager.findFirstVisibleItemPosition());
                if (view != null && view.getBottom() <= getMeasuredHeight()) {
                    // 滑到底部了
                    return canPullUp;
                }
            }
        }
        return false;
    }
}
