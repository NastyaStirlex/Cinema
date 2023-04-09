package com.nastirlex.cinema.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CollectionsListSpacesItemDecoration(
    var topFirst: Int,
    var leftFirst: Int,
    var bottomFirst: Int,
    var left: Int,
    var right: Int,
    var bottom: Int
) :
    RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = left
        outRect.right = right
        outRect.bottom = bottom
        outRect.top = 0
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 0
            outRect.left = leftFirst
            outRect.bottom = bottomFirst
        }
    }
}