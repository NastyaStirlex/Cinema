package com.nastirlex.cinema.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FramesListSpacesItemDecoration(var bottom: Int, var start: Int) :
    RecyclerView.ItemDecoration() {
    @Override
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = 0
        outRect.left = start
        outRect.bottom = bottom

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 0
            outRect.left = start
            outRect.bottom = bottom
        } else {
            outRect.top = 0;
        }
    }
}