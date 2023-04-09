package com.nastirlex.cinema.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TrendListSpacesItemDecoration(
    var startFirst: Int,
    var bottom: Int,
    var start: Int,
    var end: Int
) :
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
        outRect.right = end

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 0
            outRect.left = startFirst
            outRect.bottom = 0
            outRect.right = 0
        }
    }

}