package com.nastirlex.cinema.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CompilationSpacesItemDecoration: RecyclerView.ItemDecoration()  {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val overlap = -100

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 0
            outRect.left = 0
            outRect.bottom = 0
            outRect.right = 0
        } else {
            outRect.top = overlap
            outRect.left = -700
            outRect.bottom = 0
            outRect.right = 0
        }

    }
}