package com.fanc.wheretoplay.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author admin
 * @date 2018/1/16
 */

public class ItemDecorations extends RecyclerView.ItemDecoration {

    public int value;

    public ItemDecorations(int value) {
        this.value = value;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = value;

        outRect.left = value;
        outRect.right = value;
    }
}
